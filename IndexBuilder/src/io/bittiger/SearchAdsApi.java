package io.bittiger;

import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


//import io.bittiger.ads.AdsEngine;

/**
 * Servlet implementation class SearchAdsApi
 */
@WebServlet("/SearchAds")
public class SearchAdsApi extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private IndexBuilder indexBuilder = null;
 
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchAdsApi() {
        super();
        // TODO Auto-generated constructor stub
    }
	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
		ServletContext application = config.getServletContext();
		String adsDataFilePath = application.getInitParameter("adsDataFilePath");
		System.out.println("adsDataFilePath:" + adsDataFilePath);
		String budgetDataFilePath = application.getInitParameter("budgetDataFilePath");
	    String memcachedServer = application.getInitParameter("memcachedServer");
	    String mysqlHost = application.getInitParameter("mysqlHost");
	    String mysqlDb = application.getInitParameter("mysqlDB");
	    String mysqlUser = application.getInitParameter("mysqlUser");
	    String mysqlPass = application.getInitParameter("mysqlPass");
	    int memcachedPortal = Integer.parseInt(application.getInitParameter("memcachedPortal"));
	    this.indexBuilder = new IndexBuilder(adsDataFilePath, budgetDataFilePath, memcachedServer, memcachedPortal, mysqlHost, mysqlDb, mysqlUser, mysqlPass);
		System.out.println("indexBuilder finished");
		this.indexBuilder.execute();  
		this.indexBuilder.Close();	
	}
	/** 
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response) 
	 */ 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		// TODO Auto-generated method stub 
		
 
	} 
}
