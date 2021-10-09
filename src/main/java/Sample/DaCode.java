package Sample;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jsoup.Jsoup;
import sun.misc.BASE64Encoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class DaCode {
    public static void main(String[] args) throws IOException {

        //用户名
        String username = "dnslin";
        //密码
        String password = "dnslin";
        //备注字段: 可以不写
        String remark = "输出计算结果";

        //本地图片地址
        String url = "C:\\Users\\Dnsion\\Downloads\\222.png";

        String str = ImageToBase64ByLocal(url);

        Map<String, String> data = new HashMap<>();
        data.put("username", username);
        data.put("password", password);
        //一、图片文字类型(默认 3 数英混合)：
        //1 : 纯数字
        //1001：纯数字2
        //2 : 纯英文
        //1002：纯英文2
        //3 : 数英混合
        //1003：数英混合2
        //4 : 闪动GIF
        //7 : 无感学习(独家)
        //11 : 计算题
        //1005:  快速计算题
        //16 : 汉字
        //32 : 通用文字识别(证件、单据)
        //66:  问答题
        //49 :recaptcha图片识别 参考 https://shimo.im/docs/RPGcTpxdVgkkdQdY
        //二、图片旋转角度类型：
        //29 :  旋转类型

        //三、图片坐标点选类型：
        //19 :  1个坐标
        //20 :  3个坐标
        //21 :  3 ~ 5个坐标
        //22 :  5 ~ 8个坐标
        //27 :  1 ~ 4个坐标
        //48 : 轨迹类型
        //四、缺口识别
        //18：缺口识别
        //五、拼图识别
        //53：拼图识别
        data.put("typeid", "3");
        data.put("remark", remark);
        data.put("image", str);

        String resultString = Jsoup.connect("http://api.ttshitu.com/predict")
                .requestBody(JSON.toJSONString(data))
                .header("Content-Type", "application/json")
                .ignoreContentType(true).timeout(120000).post().text();
        JSONObject jsonObject = JSONObject.parseObject(resultString);
        if (jsonObject.getBoolean("success")) {
            String result = jsonObject.getJSONObject("data").getString("result");
            System.out.println("识别成功结果为:" + result);
        } else {
            System.out.println("识别失败原因为:" + jsonObject.getString("message"));
        }
    }

    /**
     * 本地图片转换成base64字符串
     *
     * @param imgFile 图片本地路径
     * @return
     * @author ZHANGJL
     * @dateTime 2018-02-23 14:40:46
     */
    public static String ImageToBase64ByLocal(String imgFile) {// 将图片文件转化为字节数组字符串，并对其进行Base64编码处理


        InputStream in = null;
        byte[] data = null;

        // 读取图片字节数组
        try {
            in = new FileInputStream(imgFile);

            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        // 对字节数组Base64编码
        BASE64Encoder encoder = new BASE64Encoder();

        return encoder.encode(data);// 返回Base64编码过的字节数组字符串

    }
}