package com.yefeiw;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by jiayangan on 6/24/17.
 */
public class Crawler {
    private static final String USER_AGENT = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_11_3) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/55.0.2883.95 Safari/537.36";
    private final String authUser = "bittiger";
    private final String authPassword = "cs504";
    private static final String AMAZON_QUERY_URL = "https://www.amazon.com/s/ref=nb_sb_noss?field-keywords=";
    private static final String AMAZON_DP_URL = "https://www.amazon.com/dp/";
    private static final Logger logger = Logger.getLogger("crawler");
    public void initProxy() {
        //System.setProperty("socksProxyHost", "199.101.97.161"); // set socks proxy server
        //System.setProperty("socksProxyPort", "61336"); // set socks proxy port

        System.setProperty("http.proxyHost", "199.101.97.159"); // set proxy server
        System.setProperty("http.proxyPort", "60099"); // set proxy port
        //System.setProperty("http.proxyUser", authUser);
        //System.setProperty("http.proxyPassword", authPassword);
        Authenticator.setDefault(
                new Authenticator() {
                    @Override
                    public PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                authUser, authPassword.toCharArray());
                    }
                }
        );
    }
    public void testProxy() {

        String test_url = "http://www.toolsvoid.com/what-is-my-ip-address";
        try {
            HashMap<String,String> headers = new HashMap<String,String>();
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "en-US,en;q=0.8");
            Document doc = Jsoup.connect(test_url).headers(headers).userAgent(USER_AGENT).timeout(10000).get();
            String iP = doc.select("body > section.articles-section > div > div > div > div.col-md-8.display-flex > div > div.table-responsive > table > tbody > tr:nth-child(1) > td:nth-child(2) > strong").first().text(); //get used IP.
            System.out.println("IP-Address: " + iP);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void getAmazonProds(String[] input, List<Ad> ads, int pageNum) {
        //variables
        String query = input[0].replace(' ','+'); //text of the query
        String bid = input[1].trim();
        String campaignId = input[2].trim();
        String queryGroupId = input[3].trim();

        String url = AMAZON_QUERY_URL + query;
        System.out.println("url" + url);
        try {
            HashMap<String,String> headers = new HashMap<String,String>();
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate");
            headers.put("Accept-Language", "en-US,en;q=0.8");
            Document doc = Jsoup.connect(url).maxBodySize(0).headers(headers).userAgent(USER_AGENT).timeout(10000).get();
            Integer docSize = doc.text().length();
            System.out.println("page size: " + docSize);
            Elements prods = doc.getElementsByClass("s-result-item celwidget ");
            //DOM
            System.out.println("number of prod: " + prods.size());
            //#leftNavContainer > ul:nth-child(2) > div > li:nth-child(1) > span > a > h4
            Element category = doc.select("#leftNavContainer > ul:nth-child(2) > div > li:nth-child(1) > span > a > h4").first();
            String categoryStr = category.text();
            System.out.println("prod category: " + categoryStr);
            for(Integer i = 0;i < prods.size();i++){
                Ad ad = new Ad();
                try {
                    String id = "result_" + new Integer(i).toString();
                    Element prodsById = doc.getElementById(id);
                    String asin = prodsById.attr("data-asin");

                    //init ad fields
                    ad.campaignId = Integer.parseInt(campaignId);
                    ad.bidPrice = Double.parseDouble(bid);
                    ad.adId = Integer.parseInt(queryGroupId);
                    ad.category = categoryStr;
                    ad.query = input[0];//original query;
                    Elements titleEleList = prodsById.getElementsByAttribute("title");
                    ad.title = titleEleList.attr("title");
                    //furnish fields
                    ad.detail_url = AMAZON_DP_URL+asin;
                    Element brand = doc.select("#result_" + Integer.toString(i) + " > div > div > div > div.a-fixed-left-grid-col.a-col-right > div.a-row.a-spacing-small > div > span:nth-child(2)").first();
                    ad.brand = (brand != null) ? brand.text() : "";

                    //add the new ad to the results
                    ads.add(ad);
                } catch( NullPointerException e) {
                    logger.warning("product not found, continuing");
                }
            }

        }catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }
    public void parseAmazonProdPage(String url) {
        try {
            HashMap<String,String> headers = new HashMap<String,String>();
            headers.put("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
            headers.put("Accept-Encoding", "gzip, deflate, br");
            headers.put("Accept-Language", "en-US,en;q=0.8");
            Document doc = Jsoup.connect(url).headers(headers).userAgent(USER_AGENT).timeout(10000).get();
            Element titleEle = doc.getElementById("productTitle");
            String title = titleEle.text();
            System.out.println("title: " + title);

            Element priceEle =doc.getElementById("priceblock_ourprice");
            String price = priceEle.text();
            System.out.println("price: " + price);

            //review
            //#cm-cr-dp-review-list
            Elements reviews = doc.getElementsByClass("a-expander-content a-expander-partial-collapse-content");
            System.out.println("number of reviews: " + reviews.size());
            for (Element review : reviews) {
                System.out.println("review content: " + review.text());
            }

            //#customer_review-R188VC0CBW8NLR > div:nth-child(4) > span > div > div.a-expander-content.a-expander-partial-collapse-content



        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}
