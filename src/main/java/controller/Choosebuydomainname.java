package controller;

import cn.hutool.core.lang.Console;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import java.io.IOException;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;


public class Choosebuydomainname {


    private static final CloseableHttpClient httpclient = HttpClients.createDefault();
    /*
    *
     *功能描述
     * @author dnslin
     * @date 生成所有三位 字母
     * @param
     * @return
     */

    public static List threeLetters(){
        String [] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        List<String> Letters = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                        String letter = arr[i] +arr[j]+arr[k];
                        if (Letters.contains(letter)){
                            continue;
                        }
                        Letters.add(letter);

                }
            }
        }
        return  Letters;
    }


    /*
    *
     *功能描述
     * @author dnslin
     * @date  发送get请求
     * @param
     * @return
     */

    public static List<String> sendGet(List<String> Letters) {
        Gson gson = new Gson();
        ArrayList<String> TureLetters = new ArrayList<>();
        ExecutorService executorService = Executors.newFixedThreadPool(100);
        executorService.execute(
            () -> {
                long count = 0;
                for (int i = 0; i < 2; i++) {
//                    String url = "https://kr.godaddy.com/domainfind/v1/search/smartbundles?key=dpp_leaf_me&partialQuery=llbqq.me&q=llbqq.me";
                    String url = "https://kr.godaddy.com/domainfind/v1/search/smartbundles?key=dpp_leaf_me&partialQuery="+Letters.get(i)+".me"+"+&q="+Letters.get(i)+".me";
                    HttpGet httpGet = new HttpGet(url);
                    CloseableHttpResponse response = null;
                    Map<String, Object> map = new HashMap<String, Object>();
                    try {
                        response = httpclient.execute(httpGet);
                        Console.log("接收请求完毕");
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String result = null;
                    try {
                        HttpEntity entity = response.getEntity();
                        if (entity != null) {
                            result = EntityUtils.toString(entity);
                            ObjectMapper mapper = new ObjectMapper();
                            map= mapper.readValue(result, Map.class);
                            System.out.println(map);
                            Console.log("map转换成功");
                            if (map.containsKey("smart") == false){
                                TureLetters.add(".me");
                                Console.log("打印可用域名");
                                System.out.println(TureLetters);
                            }
                            Console.log("本次查询结束--------------------");
                            count++;
                            Console.log("现在是第"+count+"次可用域名个数为--->"+TureLetters.size());
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
                }
        });
        executorService.shutdown();

        return TureLetters;
    }


    public static void main(String[] args) {
        List list = sendGet(threeLetters());
        System.out.println(list);
        ExcelWriter writer = ExcelUtil.getWriter("G:/writeTest.xlsx");
        writer.passCurrentRow();
        writer.merge(list.size() - 1, "测试标题");
        writer.write(list, true);
        writer.close();
    }
}
