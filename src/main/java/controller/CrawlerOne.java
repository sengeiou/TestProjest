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
        Connection.Response res = Jsoup.connect("http://www.glidedsky.com/login").timeout(30000).execute();
        cookies = res.cookies();
        String token = null;
        Document doc = Jsoup.connect("http://www.glidedsky.com/login").cookies(cookies).get();
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
        post.setHeader("Cookie", cookies.toString().replace("{","").replace("}",""));
//        post.setHeader("Cookie","XSRF-TOKEN=eyJpdiI6InByV3FiZWNQbmpBeVwvNHZUZlhPZWp3PT0iLCJ2YWx1ZSI6IlV1VkllODZrMkFVWHNkdEhZVGVZZHZmdndyT0FIY1M0T0VTc05JZHlHTWozcjlHNnVTTTF0cCtVWHhkYWpVcGoiLCJtYWMiOiI0MzZlNmUzMWNlNDNjZWQ1OTk3NTA1MWYyZTlmYTEzOWQ2ZjQ5NzdmOTk5ZWZjMjhjOGVhOTA3MjhlZWNjYTI5In0%3D; glidedsky_session=eyJpdiI6IjRtVTFZTEpXTWdhNzNUdXZLTmpKZ3c9PSIsInZhbHVlIjoidVhIUzNtUjZrK1dpMGFLRzFDbHlrVlNYVkhTNHgrTzFLTVR3WjNES05sVkpzbU0zdlRJS21kekNoaWordXVWTyIsIm1hYyI6ImVhYTQ5MjMwYzE5YjNmZWFkMGNiNzE3M2Y5ZDZjZjBjOGEzOTQwNWYwMzk2OTUxZDQ4ODM4MzBlZDFmNDA3MTgifQ%3D%3D; footprints=eyJpdiI6IjA1dVNWV2xyRVBBR090Rng4ZWJUUUE9PSIsInZhbHVlIjoicE5RcGFKU0FpNDk0bjVxWGZEVTlpbFhqaUVub2tTOW9zMDFjK05HVTBJTHBXXC8xakdjTXptOUZqUWdaZFh3VksiLCJtYWMiOiJhZDE3YWYwNWNjMWU3NTRlMTNlZTY1ZjEyN2UyOGY5MzAwYTdiZWM0ZTg1Nzg3MjM0YzAzYWE2NmEwMmNjNTg0In0%3D; Hm_lvt_020fbaad6104bcddd1db12d6b78812f6=1634392262; Hm_lpvt_020fbaad6104bcddd1db12d6b78812f6=1634392262; _ga=GA1.2.537626033.1634392262; _gid=GA1.2.1752073277.1634392262; __gads=ID=7003b63ce2464e26-22c283279ecc0080:T=1634392263:RT=1634392263:S=ALNI_MZ1zSJInGnYHEWBz-Uy1A9fwUXt7A");
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
