package service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.Qweather;
import entity.User;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class AddressQueryService {
    private final String privatekey = "506a100a3d344a6e8a72a62b0af505d2";

    @Test
    /*
     *
     *功能描述
     * @author dnslin
     * @date  https://devapi.qweather.com/v7/weather/now?location=101010100&key=你的KEY
     * @param
     * @return
     */
    public void getinquireAddress() throws IOException {
        String address = "北京";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://geoapi.qweather.com/v2/city/lookup?location=" + address + "&key=" + privatekey);
        HttpEntity entity = httpClient.execute(httpGet).getEntity();
        String result = EntityUtils.toString(entity);
        JSONObject jsonObject = JSONObject.parseObject(result);
        String location = jsonObject.getString("location");
        List<Qweather> parseArray = JSON.parseArray(location, Qweather.class);
        List<String> collect = parseArray.stream().map(Qweather::getId).collect(Collectors.toList());
        for (int i = 0; i < collect.size(); i++) {
            String id = collect.get(i);
            System.out.println(id);
            HttpGet httpGet1 = new HttpGet("https://devapi.qweather.com/v7/weather/now?location=" + id + "&key=" + privatekey);
            HttpEntity entitys = httpClient.execute(httpGet1).getEntity();
            String consequence = EntityUtils.toString(entitys);

        }


//        https://devapi.qweather.com/v7/weather/now?/location=id&key=privatekey

    }

}
