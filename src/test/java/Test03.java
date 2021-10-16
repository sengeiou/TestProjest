import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

public class Test03 {
    @Test
    public void test() {
        String url = "https://idea.medeming.com/a/jihuoma1.zip";
        String fileName = url.substring(url.lastIndexOf('/') + 1, url.length());

        String fileNameWithoutExtn = fileName.substring(0, fileName.lastIndexOf('.'));

        System.out.println(fileName);
        System.out.println(fileNameWithoutExtn);
    }

    @Test
    public void test02() {
        int i = 0;
        while (i != 100) {
            i++;
            System.out.println(i);
        }
    }

    @Test
    public void Test03() {
        int i = 0;

        while (i != 5) {
            i++;
            System.out.println(i + "1");
            int j = 0;
            while (j != 5) {
                j++;
                System.out.println(j);
            }
        }
    }

    @Test
    public void test04() {
        String json = "{\"unitName\":\"北京百度网讯科技有限公司\",\"limitAccess\":\"否\",\"natureName\":\"企业\",\"serviceName\":\"百度\",\"updateRecordTime\":\"2021-08-30 13:10:24\",\"domainId\":10000245113,\"homeUrl\":\"www.baidu.com\",\"serviceLicence\":\"京ICP证030173号-1\",\"leaderName\":\"\",\"domain\":\"baidu.com\",\"mainLicence\":\"京ICP证030173号\",\"mainId\":282751,\"serviceId\":282911,\"contentTypeName\":\"\"}";
        JSONObject jsonObject = JSONObject.parseObject(json);
        String unitName = jsonObject.getString("unitName");
        System.out.println(unitName);
    }
}