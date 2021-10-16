package service;

import cn.hutool.core.lang.Console;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import entity.IPAPI;
import entity.IPAPIS;
import entity.Ipstack;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;

// TODO : 客户端子网和 DNS 服务器 API  http://edns.ip-api.com/json
public class IpPositionService {

    private final String Ipstackkey = "b6a76bd77214348a9a60ad29b51cac9a";

    /*
     *
     *功能描述
     * @author dnslin
     * @date http://api.ipstack.com/104.222.246.242?access_key={access_key}
     * @param  Ipstack
     * @return Json
     * @Key b6a76bd77214348a9a60ad29b51cac9a
     */
    public Ipstack getIpstack(String ipaddress) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://api.ipstack.com/" + ipaddress + "?access_key=" + Ipstackkey);
        Ipstack ipstack = null;
        try {
            HttpEntity entity = httpClient.execute(get).getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                if (result != null) {
                    ipstack = JSON.parseObject(result, Ipstack.class);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            Console.log("io异常");
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                Console.log("io异常");
            }
        }
        return ipstack;
    }

    /*
     *
     *功能描述
     * @author dnslin
     * @date https://ipapi.co/8.8.8.8/json/
     * @param  IPAPI
     * @return json , jsonp, xml, csv, yaml
     */

    public IPAPI getIPAPI(String ipaddress, String format, String a) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("https://ipapi.co/" + ipaddress + "/" + format + "/" + a);
        IPAPI ipapi = null;
        try {
            HttpEntity entity = httpClient.execute(get).getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                if (result != null) {
                    ipapi = JSON.parseObject(result, IPAPI.class);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipapi;
    }

    /*
     *
     *功能描述
     * @author dnslin
     * @date http://ip-api.com/json/24.48.0.1
     * http://ip-api.com/json/24.48.0.1?fields=66846719&&lang=zh-CN
     * @param  ip-api
     * @return JSON XML CSV Newline PHP
     */
    public IPAPIS getIpapi(String format, String ipaddress, String lang) {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        HttpGet get = new HttpGet("http://ip-api.com/" + format + "/" + ipaddress + "?fields=66846719&&lang=" + lang);
        IPAPIS ipapis = null;
        try {
            HttpEntity entity = httpClient.execute(get).getEntity();
            if (entity != null) {
                String result = EntityUtils.toString(entity, "UTF-8");
                if (result != null) {
                    ipapis = JSON.parseObject(result, IPAPIS.class);
                }
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ipapis;
    }


    @Test
    public void test() {
        IpPositionService ipPositionService = new IpPositionService();
        Ipstack ipstack = ipPositionService.getIpstack("66.249.64.247");
        IPAPI ipapi = ipPositionService.getIPAPI("66.249.64.247", "json", "");
        IPAPIS ipapis = ipPositionService.getIpapi("json", "66.249.64.247", "zh-CN");
        System.out.println("=====================================================");
        ArrayList<Object> objects = new ArrayList<>();
        objects.add(ipapis);
        objects.add(ipstack);
        objects.add(ipapi);
        System.out.println(objects);

    }

    @Test
    public void postRequestInterface() throws IOException {
        String ipaddress = "1.1.1.1,8.8.8.8,223.5.5.5,223.6.6.6";
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String substring = ipaddress.substring(0, ipaddress.length());
        String[] split = substring.split(",");//以逗号分割
        HttpPost post = new HttpPost("http://ip-api.com/batch?fields=66842623&&lang=zh-CN");
        String jsonString2 = JSON.toJSONString(split);
        System.out.println(jsonString2);
        post.setEntity(new StringEntity(jsonString2, "UTF-8"));
        HttpEntity entity = httpClient.execute(post).getEntity();
        String result = EntityUtils.toString(entity);
        JSONArray objects = JSON.parseArray(result);
        System.out.println(objects);
    }
}

