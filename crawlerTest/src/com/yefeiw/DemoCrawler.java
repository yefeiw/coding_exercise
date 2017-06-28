package com.yefeiw;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * Created by vagrant on 6/26/17.
 */
public class DemoCrawler {
    public void getAmazonProd(String url) {
        try {
            Document doc = Jsoup.connect(url).timeout(1000).get();
            String fullText = doc.body().text();
            //Get full text and title text
            String title = doc.title();
            System.out.println("title: "+title);
            System.out.println("FullText size"+ fullText.length());
            System.out.println("fullText:" + fullText);


            //get title Element
            Element titleEle = doc.getElementById("productTitle");
            String titleStr = titleEle.text().trim();

            System.out.println("productTitle " + titleStr);

            //get element by Class (a list of elements)
            Elements reviews = doc.getElementsByClass("a-section review");
            System.out.println("size of all elements: "+reviews.size());
            for(Element review : reviews) {
                System.out.println("review: "+review.text());
            }
//selector #comparison_top_reviews > td.comparison_baseitem_column > div > div.a-expander-content.a-expander-partial-collapse-content > span:nth-child(2)
            //get element by id or selector


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
