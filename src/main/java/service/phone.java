package service;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

public class phone {
    public static void main(String[] args) {
        String iurl = "https://tcc.taobao.com/cc/json/mobile_tel_segment.htm?tel=17674722393";
        String result;
        try {
            result = doHttpget(iurl);
            String jsonString = result.split("=")[1].trim();
            ObjectMapper objectMapper = new ObjectMapper();

            objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);//设置可用单引号

            objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);//设置字段可以不用双引号包括

            JsonNode root = objectMapper.readTree(jsonString);
            System.out.println("归属地=" + root.path("province").asText());
            System.out.println("运营商=" + root.path("catName").asText());
        } catch (Exception e) {
            e.toString();
        }
    }

    /**
     * http请求
     *
     * @return
     * @throws Exception
     */
    public static String doHttpget(String url) throws Exception {
        HttpGet post = null;
        CloseableHttpResponse response = null;
        try {
            CloseableHttpClient httpClient = HttpClients.createDefault();
            post = new HttpGet(url);
            // 构造消息头
            post.setHeader("Content-Type", "application/json; charset=utf-8");
            post.setHeader("Connection", "Close");
            response = httpClient.execute(post);
            if (response != null) {
                HttpEntity entity = response.getEntity();
                String str = EntityUtils.toString(entity);
                return str;
            }
            return null;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                response.getEntity().getContent().close();
            }
            if (post != null) {
                try {
                    post.releaseConnection();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}