package io.bittiger;

import net.spy.memcached.MemcachedClient;
import net.spy.memcached.AddrUtil;
import net.spy.memcached.ConnectionFactoryBuilder;
import net.spy.memcached.FailureMode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import org.json.JSONArray;
import org.json.JSONObject;

import com.sun.istack.internal.logging.Logger;

import io.bittiger.Ad;
//import io.bittiger.Campaign;
import io.bittiger.MySQLAccess;
import io.bittiger.Utility;

public class IndexBuilder {
	private int EXP = 0; // 0: never expire
	private String mMemcachedServer;
	private int mMemcachedPortal;
	private String mysql_host;
	private String mysql_db;
	private String mysql_user;
	private String mysql_pass;
	private MySQLAccess mysql;
	private MemcachedClient cache;
	private String mAdsDataFilePath;
	private String mBudgetFilePath;
	private static final Logger logger = Logger.getLogger(IndexBuilder.class);

	public void Close() {
		if (mysql != null) {
			try {
				mysql.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public IndexBuilder(String adsDataFilePath, String budgetFilePath, String memcachedServer, int memcachedPortal,
			String mysqlHost, String mysqlDb, String user, String pass) {
		mAdsDataFilePath = adsDataFilePath;
		mBudgetFilePath = budgetFilePath;
		mMemcachedServer = memcachedServer;
		mMemcachedPortal = memcachedPortal;
		mysql_host = mysqlHost;
		mysql_db = mysqlDb;
		mysql_user = user;
		mysql_pass = pass;
		try {
			mysql = new MySQLAccess(mysql_host, mysql_db, mysql_user, mysql_pass);
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String address = mMemcachedServer + ":" + mMemcachedPortal;
		try {
			cache = new MemcachedClient(
					new ConnectionFactoryBuilder().setDaemon(true).setFailureMode(FailureMode.Retry).build(),
					AddrUtil.getAddresses(address));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Boolean buildInvertIndex(Ad ad) {
		logger.log(Level.INFO, "building inverted index for " + ad.title);
		String keyWords = Utility.strJoin(ad.keyWords, ",");
		List<String> tokens = Utility.cleanedTokenize(keyWords);
		for (int i = 0; i < tokens.size(); i++) {
			String key = tokens.get(i);
			if (cache.get(key) instanceof Set) {
				@SuppressWarnings("unchecked")
				Set<Long> adIdList = (Set<Long>) cache.get(key);
				adIdList.add(ad.adId);
				cache.set(key, EXP, adIdList);
			} else {
				Set<Long> adIdList = new HashSet<Long>();
				adIdList.add(ad.adId);
				cache.set(key, EXP, adIdList);
			}
		}
		return true;
	}

	public Boolean buildForwardIndex(Ad ad) {
		logger.log(Level.CONFIG, "building forward index for " + ad.title);

		try {
			mysql.addAdData(ad);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public Boolean updateBudget(Campaign camp) {
		try {
			mysql.addCampaignData(camp);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public void execute() {
		// load ads data
		try (BufferedReader brAd = new BufferedReader(new FileReader(mAdsDataFilePath))) {
			String line;
			while ((line = brAd.readLine()) != null) {
				JSONObject adJson = new JSONObject(line);
				Ad ad = new Ad();
				if (adJson.isNull("adId") || adJson.isNull("campaignId")) {
					continue;
				}
				ad.adId = adJson.getLong("adId");
				ad.campaignId = adJson.getLong("campaignId");
				ad.brand = adJson.isNull("brand") ? "" : adJson.getString("brand");
				ad.price = adJson.isNull("price") ? 100.0 : adJson.getDouble("price");
				ad.thumbnail = adJson.isNull("thumbnail") ? "" : adJson.getString("thumbnail");
				ad.title = adJson.isNull("title") ? "" : adJson.getString("title");
				ad.detail_url = adJson.isNull("detail_url") ? "" : adJson.getString("detail_url");
				ad.bidPrice = adJson.isNull("bidPrice") ? 1.0 : adJson.getDouble("bidPrice");
				ad.pClick = adJson.isNull("pClick") ? 0.0 : adJson.getDouble("pClick");
				ad.category = adJson.isNull("category") ? "" : adJson.getString("category");
				ad.description = adJson.isNull("description") ? "" : adJson.getString("description");
				ad.keyWords = new ArrayList<String>();
				JSONArray keyWords = adJson.isNull("keyWords") ? null : adJson.getJSONArray("keyWords");
				for (int j = 0; j < keyWords.length(); j++) {
					ad.keyWords.add(keyWords.getString(j));
				}
				
				if (!buildInvertIndex(ad)) {
					logger.log(Level.WARNING, "nothing seems to have been built, continuing");
				}
				
				if (!buildForwardIndex(ad)) {
					logger.log(Level.WARNING, "nothing seems to have been built, continuing");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		} catch (NullPointerException e) {
			e.printStackTrace();
		}
		

		// load budget data
		try (BufferedReader brBudget = new BufferedReader(new FileReader(mBudgetFilePath))) {
			String line;
			while ((line = brBudget.readLine()) != null) {
				JSONObject campaignJson = new JSONObject(line);
				Long campaignId = campaignJson.getLong("campaignId");
				double budget = campaignJson.getDouble("budget");
				Campaign camp = new Campaign();
				camp.campaignId = campaignId;
				camp.budget = budget;
				if (!updateBudget(camp)) {
					// log
					logger.log(Level.INFO, "budge update has problems, continuing");
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
