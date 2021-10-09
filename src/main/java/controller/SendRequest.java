package controller;


import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class SendRequest {
    private static final CloseableHttpClient httpclient = HttpClients.createDefault();

    public static Map sendGet(String url) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入邮箱:");
        HttpGet httpGet = new HttpGet(url + scanner.next());
        CloseableHttpResponse response = null;
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            response = httpclient.execute(httpGet);
        } catch (Exception e) {
            e.printStackTrace();
        }
        String result = null;
        try {
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                result = EntityUtils.toString(entity);
                Gson gson = new Gson();
                map = gson.fromJson(result, map.getClass());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                response.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return map;
    }

    public static void main(String[] args) {
        Map map = sendGet("https://signup-api.cloud.oracle.com/20200828/promotionGuests?email=");
        Object items = map.get("items");
        System.out.println(items);
    }



}
