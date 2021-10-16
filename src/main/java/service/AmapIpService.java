package service;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import java.math.BigInteger;
import java.security.MessageDigest;

public class AmapIpService {

    private final String key = "04f8bd06a2a5390ba589d6c0fb9bd3cf";
    private final String privateKey = "9acba11d0c58ea7c10cad6ecbe19880b";
    /*
    *
     *功能描述 
     * @author dnslin
     * @date 制作令牌签名
     * @param  签名格式：sig=MD5(请求参数键值对（按参数名的升序排序），加（请注意“加”字无需输入）私钥)。
     * @return  sig=md5(a=23&b=12&c=67&d=48&f=8bbbbb)
     * ip   key   sig type
     * https://restapi.amap.com/v5/ip?parameters
     */
    @Test
    public void makeASignature( ) throws Exception {
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String ip = "223.5.5.5";
        String type= "4";
        String centre = "ip="+ip+"&key="+key+"&type="+type+privateKey;
        System.out.println(centre);
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] digest = md5.digest(centre.getBytes("utf-8"));
        String sig = new BigInteger(1, digest).toString(16);
        System.out.println(sig);
        String url = "https://restapi.amap.com/v5/ip?key="+key+"&ip="+ip+"&type="+type+"&sig="+sig;
        System.out.println(url);
        HttpGet httpGet = new HttpGet(url);
        HttpEntity entity = httpClient.execute(httpGet).getEntity();
        String s = EntityUtils.toString(entity);
        System.out.println(s);

    }
}
