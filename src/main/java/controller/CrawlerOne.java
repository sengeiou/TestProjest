package controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import entity.Crawler;
import jdk.nashorn.internal.parser.Token;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;

import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import service.CrawlerLogin;
import utils.SSLUtils;


import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class CrawlerOne {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static String getTheData() throws IOException {
        Map<String, String> cookies = null;
        Connection.Response res = Jsoup.connect("http://www.glidedsky.com").timeout(30000).execute();
        cookies = res.cookies();
        String token = null;
        Document doc = Jsoup.connect("http://www.glidedsky.com").cookies(cookies).get();
        for (int i = 0; i < doc.getElementsByTag("meta").size(); i++) {
            if (doc.getElementsByTag("meta").get(i).attr("content").length() <= 40){
                token = doc.getElementsByTag("meta").get(i).attr("content");
            }
        }
        String cookie = cookies.toString().replace("{", "").replace("}", "");
        Crawler crawler = new Crawler();
//        ObjectMapper MAPPER = new ObjectMapper();
        HttpPost post=new HttpPost("http://www.glidedsky.com/login");
        // 设置头信息
        post.setHeader("Host",  "www.glidedsky.com");
        post.setHeader("Connection",  "keep-alive");
        post.setHeader("Origin",  "www.glidedsky.com");
        post.setHeader("Accept",  "text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        post.setHeader("Referer",  "http://www.glidedsky.com/login");
        post.setHeader("Accept-Encoding",  "gzip, deflate");
        post.setHeader("Content-Type", "application/x-www-form-urlencoded");
        post.setHeader("Cookie", cookie);
        post.setHeader("Accept-Language", "zh-CN,zh;q=0.9,en;q=0.8,zh-TW;q=0.7,zh-HK;q=0.6");
        // 设置参数实体信息
//        crawler.set_token("IHTmRkjTxU2VPMAW8jAXowY2lN6dmiI425i9uwrr");
//        crawler.setEmail("i@zwm.me");
//        crawler.setPassword("catchers");
//        // 对象转json
//        String json = MAPPER.writeValueAsString(crawler);
//        System.out.println(json);
        // 设置实体信息
//        post.setEntity(new StringEntity(json,"UTF-8"));
        // 发出请求得到实体信息
        System.out.println("token-------》"+token);
        System.out.println("cookie-------》"+cookie);
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("_token", token);
        BasicNameValuePair param2 = new BasicNameValuePair("email", "i@zwm.me");
        BasicNameValuePair param3 = new BasicNameValuePair("password", "catchers");
        list.add(param1);
        list.add(param2);
        list.add(param3);
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        post.setEntity(entityParam);
        HttpEntity entity = httpclient.execute(post).getEntity();
        return EntityUtils.toString(entity);
    }

    public static void main(String[] args) throws IOException {
        String theData = getTheData();
        System.out.println(theData);
    }
}
