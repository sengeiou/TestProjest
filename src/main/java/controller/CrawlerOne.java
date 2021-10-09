package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Crawler;
import jdk.nashorn.internal.parser.Token;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import service.CrawlerLogin;
import utils.SSLUtils;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class CrawlerOne {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String getTheData() throws IOException {
        String token = null;
        Document doc = Jsoup.connect("http://www.glidedsky.com/login").get();
        for (int i = 0; i < doc.getElementsByTag("meta").size(); i++) {
            if (doc.getElementsByTag("meta").get(i).attr("content").length() == 40){
                token = doc.getElementsByTag("meta").get(i).attr("content");
            }
        }
        Crawler crawler = new Crawler();
        ObjectMapper MAPPER = new ObjectMapper();

        HttpPost post=new HttpPost("http://www.glidedsky.com/login");
        // 设置头信息
        post.setHeader("Host",  "www.glidedsky.com");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        post.setHeader("Referer",  "http://www.glidedsky.com/login");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        // 设置参数实体信息
        crawler.set_token(token);
        crawler.setEmail("i@zwm.me");
        crawler.setPassword("catchers");
        // 对象转json
        String json = MAPPER.writeValueAsString(crawler);
        System.out.println(json);
        // 设置实体信息
        post.setEntity(new StringEntity(json,"UTF-8"));
        // 发出请求得到实体信息
        HttpEntity entity = httpclient.execute(post).getEntity();
        return EntityUtils.toString(entity);
    }

    public static void main(String[] args) throws IOException {
        String theData = getTheData();
        System.out.println(theData);
    }
}
