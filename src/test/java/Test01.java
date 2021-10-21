import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.lang.Console;
import controller.Choosebuydomainname;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class Test01 {

    @Test
    public  void DateU(){
        String [] arr = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","t","u","v","w","x","y","z"};
        List<String> Letters = new ArrayList<String>();
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < arr.length; k++) {
                    for (int l = 0; l < arr.length; l++) {
                        String letter = arr[i] +arr[j]+arr[k]+arr[l];
                        if (Letters.contains(letter)){
                            continue;
                        }
                        Letters.add(letter);
                    }
                }
            }
        }
        System.out.println(Letters.size());
    }

    @Test
    public void str(){
         String surl = "14Ygqvn973umU-ZXTMh-uLg";
         String surl_1 = surl.substring(0);
        System.out.println(surl_1);
    }

    @Test
    public void time(){
        System.out.println(System.currentTimeMillis());
    }


    @Test
    public void test06(){
        String string = "张三,李四,王五,马六,小气";
        String substring = string.trim().substring(0, string.length());
        System.out.println(substring);
        String[] split = substring.split(",");//以逗号分割
        for (String string2 : split) {
            System.out.println("数据-->>>" + string2);
        }
    }
}
