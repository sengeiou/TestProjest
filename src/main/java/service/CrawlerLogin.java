package service;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;

public class CrawlerLogin {

    /*
     *
     *功能描述
     * @author dnslin
     * @date 拿到token
     * @param
     * @return
     */


    public static String getTheToken() throws IOException {
        String token = null;
        Document doc = Jsoup.connect("http://www.glidedsky.com/login").get();
        for (int i = 0; i < doc.getElementsByTag("meta").size(); i++) {
            if (doc.getElementsByTag("meta").get(i).attr("content").length() == 40){
                token = doc.getElementsByTag("meta").get(i).attr("content");
            }
        }
        return token;
    }


    public static void main(String[] args) throws IOException {
        String theToken = getTheToken();
        System.out.println(theToken);
    }
}
