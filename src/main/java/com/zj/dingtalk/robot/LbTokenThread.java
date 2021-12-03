package com.zj.dingtalk.robot;
 
 
import com.dingtalk.api.DefaultDingTalkClient;
import com.dingtalk.api.request.OapiGettokenRequest;
import com.dingtalk.api.response.OapiGettokenResponse;
import com.taobao.api.ApiException;
 
public class LbTokenThread{
    
    private static String APPKEY = "dingyw7zqfyjjhqgywwx"; // 小程序APPKEY
    private static String APPSECRET = "uILrq1YyH1XCWc3I_nLIBONbd9J_H6ysHDmURg8n0BvIDQZtTj-b2U9EUumBElL1"; // 小程序APPSECRET
    
    public static String accessToken = null;

    public static void main(String[] args) {
        DefaultDingTalkClient client = new DefaultDingTalkClient("https://oapi.dingtalk.com/gettoken");
        OapiGettokenRequest request = new OapiGettokenRequest();
        request.setAppkey(APPKEY);
        request.setAppsecret(APPSECRET);
        request.setHttpMethod("GET");
        String accessToken = null;
        try {
            OapiGettokenResponse response = client.execute(request);
            accessToken = response.getAccessToken();
            System.out.println(response);
        } catch (ApiException e) {
            e.printStackTrace();
        }
    }
}