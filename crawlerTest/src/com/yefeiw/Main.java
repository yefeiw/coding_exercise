package com.yefeiw;

public class Main {

    public static void main(String[] args) {
	// write your code here
        String url =  "https://www.amazon.com/AmazonBasics-Portable-Bluetooth-Speaker-Red/dp/B00JZVPC08";

        DemoCrawler demo  = new DemoCrawler();
        demo.getAmazonProd(url);
    }
}
