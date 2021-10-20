package controller;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import okhttp3.RequestBody;
import org.junit.Test;
import service.Uservice;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.util.Base64;
import java.util.Scanner;

/**
 * ss://method:password@server:port
 */


public class main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Uservice uservice = new Uservice();
        String substring = substring();
        String link = Base64.getEncoder().encodeToString(substring.getBytes("utf-8"));
        System.out.println("ss://"+link);
    }
    
    public static String substring() throws UnsupportedEncodingException {
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入密码:");
        String password = scanner.next();
        System.out.print("请输入服务地址:");
        String server = scanner.next();
        String i = ":";
        return "chacha20-ietf-poly1305"+i+password+"@"+server+i+"8388";
    }



    
}
