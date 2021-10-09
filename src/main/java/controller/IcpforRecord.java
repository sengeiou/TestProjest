package controller;

import com.alibaba.fastjson.JSONArray;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import entity.parameter;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.collections4.Get;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.net.URISyntaxException;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/*
*
 *功能描述 
 * @author dnslin
 * @date  https://hlwicpfwc.miit.gov.cn/icpproject_query/api/icpAbbreviateInfo/queryByCondition
 * @param  
 * @return 
 */
public class IcpforRecord {

    /*
    *
     *功能描述
     * @author dnslin
     * @date 获得cookie
     * @param
     * @return
     */
    public static String getCookie(){
         CloseableHttpClient httpClient = HttpClients.createDefault();
         HttpClientContext context = HttpClientContext.create();
        String cookie;
        HttpGet get=new HttpGet("https://beian.miit.gov.cn/");
        try {
            CloseableHttpResponse response = httpClient.execute(get, context);
            try{
               return cookie = context.getCookieStore().getCookies().get(0).getValue();
            }
            finally {
                response.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /*
    *
     *功能描述
     * @author dnslin
     * @date 请求获取Token
     * @param
     * @return
     */
    public static String requestToGetToken(String cookie) throws IOException, NoSuchAlgorithmException {
        HttpClient client = getHttpClient();
        String token = null;
        HttpPost post=new HttpPost("https://hlwicpfwc.miit.gov.cn/icpproject_query/api/auth");

        // 设置请求头
        post.setHeader("Host", "hlwicpfwc.miit.gov.cn");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("Accept", "*/*");
        post.setHeader("DNT", "1");
        post.setHeader("sec-ch-ua-mobile", "?0");
        post.setHeader("Cookie",  "__jsluid_s=" + cookie);
        post.setHeader("Origin",  "https://beian.miit.gov.cn");
        post.setHeader("Referer",  "https://beian.miit.gov.cn/");
        post.setHeader("Accept-Encoding",  "gzip, deflate, br");
        post.setHeader("Accept-Language",  "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.85 Safari/537.36 Edg/90.0.818.46");
        // 准备 构造AuthKey
        Long Msec = System.currentTimeMillis();
        String timeStamp = Msec.toString();
        String authSecret = "testtest" + timeStamp;
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(new String(authSecret.getBytes(),"UTF-8").getBytes());
        String authKey = new BigInteger(1, md.digest()).toString(16);
        // 创建请求参数
        List<NameValuePair> list = new LinkedList<>();
        BasicNameValuePair param1 = new BasicNameValuePair("authKey", authKey);
        BasicNameValuePair param2 = new BasicNameValuePair("timeStamp", timeStamp);
        list.add(param1);
        list.add(param2);
        // 使用URL实体转换工具
        UrlEncodedFormEntity entityParam = new UrlEncodedFormEntity(list, "UTF-8");
        post.setEntity(entityParam);
        // 发起请求
        HttpEntity entity = client.execute(post).getEntity();
        // 使用Apache提供的工具类进行转换成字符串
        String entityStr = EntityUtils.toString(entity, "UTF-8");


        ObjectMapper mapper = new ObjectMapper();
        //mapper.getNodeFactory();
        JsonNode node = mapper.readTree(entityStr);

        String params = node.get("params").toString();
        JsonNode nodetoken = mapper.readTree(params);
        String bussiness = nodetoken.get("bussiness").toString().replace("\"", "");
        return bussiness;
    }

    public static CloseableHttpClient getHttpClient() {
        try {
            SSLContextBuilder builder = new SSLContextBuilder();
            builder.loadTrustMaterial(null, new TrustSelfSignedStrategy());
            //不进行主机名验证
            SSLConnectionSocketFactory sslConnectionSocketFactory = new SSLConnectionSocketFactory(builder.build(),
                    NoopHostnameVerifier.INSTANCE);
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory> create()
                    .register("http", new PlainConnectionSocketFactory())
                    .register("https", sslConnectionSocketFactory)
                    .build();

            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(100);
            CloseableHttpClient httpclient = HttpClients.custom()
                    .setSSLSocketFactory(sslConnectionSocketFactory)
                    .setDefaultCookieStore(new BasicCookieStore())
                    .setConnectionManager(cm).build();
            return httpclient;
        } catch (KeyManagementException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }
        return HttpClients.createDefault();
    }

    public static String initiateRequest(String cookie, String token) throws IOException {
        ObjectMapper MAPPER = new ObjectMapper();
        HttpClient client = getHttpClient();
        parameter parameter = new parameter();
        HttpPost post=new HttpPost("https://hlwicpfwc.miit.gov.cn/icpproject_query/api/icpAbbreviateInfo/queryByCondition");
        post.setHeader("Host", "hlwicpfwc.miit.gov.cn");
//        post.setHeader("Content-Length", "51");
        post.setHeader("Connection", "keep-alive");
        post.setHeader("sec-ch-ua", "\" Not A;Brand\";v=\"99\", \"Chromium\";v=\"90\", \"Microsoft Edge\";v=\"90\"");
        post.setHeader("Accept", "application/json, text/plain, */*");
        post.setHeader("DNT", "1");
        post.setHeader("sec-ch-ua-platform", "Windows");
        post.setHeader("Content-Type", "application/json");
        post.setHeader("token", token);
        post.setHeader("Cookie",  "__jsluid_s=" + cookie);
        post.setHeader("Origin",  "https://beian.miit.gov.cn");
        post.setHeader("Sec-Fetch-Site",  "same-site");
        post.setHeader("Sec-Fetch-Mode",  "cors");
        post.setHeader("Sec-Fetch-Dest",  "empty");
        post.setHeader("Referer",  "https://beian.miit.gov.cn/");
        post.setHeader("Accept-Encoding",  "gzip, deflate, br");
        post.setHeader("Accept-Language",  "zh-CN,zh;q=0.9,en;q=0.8,en-GB;q=0.7,en-US;q=0.6");
        post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36");
        parameter.setPageNum("");
        parameter.setPageSize("");
        parameter.setUnitName("baidu.com");
        String ljson = MAPPER.writeValueAsString(parameter);
        System.out.println(ljson);
        post.setEntity(new StringEntity(ljson,"UTF-8"));
        // 发起请求
        HttpEntity entity = client.execute(post).getEntity();
        // 使用Apache提供的工具类进行转换成字符串
        String entityStr = EntityUtils.toString(entity, "UTF-8");

        return entityStr;
    }

    public static void main(String[] args) throws IOException, NoSuchAlgorithmException {
        String cookie = getCookie();
        String token = requestToGetToken(cookie);
        System.out.println(initiateRequest(cookie, token));
    }



}
