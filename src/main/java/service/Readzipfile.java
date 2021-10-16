package service;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import entity.Cdkey;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Readzipfile {

    private final CloseableHttpClient httpclient = HttpClients.createDefault();

    /*
     *
     *功能描述
     * @author dnslin
     * @date 从URL获取文件
     * @param
     * @return
     */
    public String getTheFileFromTheURL() throws IOException {
        String url = "https://idea.medeming.com/a/jihuoma1.zip";
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());
        File theFileObject = new File("G:\\move\\" + fileName+"1");
        try {
            HttpGet httpget = new HttpGet(url);
            HttpResponse response = httpclient.execute(httpget);
            HttpEntity entity = response.getEntity();
            InputStream in = entity.getContent();
            File file = new File("D:/temp/123.jpg");//这里为存储路径/xx/xx..
            FileOutputStream fout = new FileOutputStream(theFileObject);
            int i = -1;
            byte[] tmp = new byte[1024];
            while ((i = in.read(tmp)) != -1) {
                fout.write(tmp, 0, i);
                //注意这里如果用OutputStream.write(buff)的话，图片会失真
            }
            fout.flush();
            fout.close();
            in.close();
            Console.log("下载成功");
            return fileName;
        } catch (IOException e) {
            e.printStackTrace();
            Console.log("io流异常,下载失败");
        } catch (UnsupportedOperationException e) {
            e.printStackTrace();
        } finally {
            httpclient.close();
        }
        return fileName;
    }

    //
    public static void readTheFile(String fileName) throws IOException {
        Cdkey cdkey = new Cdkey();

        ArrayList<Cdkey> cdkeys = new ArrayList<>();

        //获取文件输入流
        FileInputStream input = new FileInputStream("G:\\move\\"+ fileName);

        //获取ZIP输入流(一定要指定字符集Charset.forName("GBK")否则会报java.lang.IllegalArgumentException: MALFORMED)
        ZipInputStream zipInputStream = new ZipInputStream(new BufferedInputStream(input), StandardCharsets.UTF_8);

        //定义ZipEntry置为null,避免由于重复调用zipInputStream.getNextEntry造成的不必要的问题
        ZipEntry ze = null;
        //循环遍历
        while ((ze = zipInputStream.getNextEntry()) != null) {
            System.out.println("文件名：" + ze.getName() + " 文件大小：" + ze.getSize() + " bytes");
            System.out.println("文件内容：");
            //读取
            BufferedReader br = new BufferedReader(new InputStreamReader(zipInputStream, StandardCharsets.UTF_8));
            String line;
            //内容不为空，输出
            int i=1;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                cdkey.setId(i++);
                String now = DateUtil.today();
                cdkey.setDate(now);
                cdkey.setKey(line);
                cdkeys.add(cdkey);
            }
            System.out.println(cdkeys);
        }
        //一定记得关闭流
        zipInputStream.closeEntry();
        input.close();
    }

   @Test
   public void test() throws IOException {
       String theFileFromTheURL = getTheFileFromTheURL();
       try {
           readTheFile(theFileFromTheURL);
       } catch (IOException e) {
           e.printStackTrace();
       }
   }
}
