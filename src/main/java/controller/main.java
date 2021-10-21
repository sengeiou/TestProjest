package controller;


import service.Uservice;

import java.io.UnsupportedEncodingException;

import java.util.*;

/**
 * ss://method:password@server:port
 */


public class main {
    public static void main(String[] args) throws UnsupportedEncodingException {
        Uservice uservice = new Uservice();
        List<String> substring = substring();
        for (int i = 0; i < substring.size(); i++) {
            String link =  Base64.getEncoder().encodeToString(substring.get(i).getBytes("utf-8"));
            System.out.println("ss://"+link);
        }

    }
    
    public static List<String> substring() throws UnsupportedEncodingException {
        ArrayList<String> arr = new ArrayList<String>();
        Scanner scanner = new Scanner(System.in);
        System.out.print("请输入服务地址:");
        String server = scanner.next();
        String substring = server.trim().substring(0, server.length());
        String[] split = substring.split(",");//以逗号分割
        String i = ":";
        for (int a = 0; a < split.length; a++) {
           String text = "chacha20-ietf-poly1305"+i+"8ZDndBVFtoao5A7k9EVMPFRHBmUrFVa"+"@"+split[a]+i+"8388";
           arr.add(text);
        }
        return arr;
    }



    
}
