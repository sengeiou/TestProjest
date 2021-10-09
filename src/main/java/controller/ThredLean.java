package controller;


import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.junit.Test;

import java.util.function.Consumer;

public class ThredLean {
    public static void main(String[] args) {
        Runnable runnable = () -> System.out.println("runtime");
        Thread thread = new Thread(runnable,"runnable");
        thread.start();
    }

    @Test
    public void test1(){
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpPut httpPut = new HttpPut();
        httpPut.setHeader("Accept", "application/octet-stream");
        httpPut.setHeader("Content-Type", "application/octet-stream");

        Consumer<String> con1 = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        con1.accept("hello!");
    }


    @Test
    public void test2(){
        Consumer<String> con2 = s -> System.out.println(s);
        con2.accept("world");
    }

    @Test
    public void test3(){
    }
}
