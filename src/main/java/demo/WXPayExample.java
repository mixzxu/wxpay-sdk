package demo;

import com.github.wxpay.sdk.WXPay;
import com.github.wxpay.sdk.WXPayConstants;
import com.github.wxpay.sdk.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: esportingplus
 * @description:
 * @author: mixzxu
 * @create: 2018-12-12 16:48
 **/

public class WXPayExample {

    public static final Logger logger = LoggerFactory.getLogger(WXPayExample.class);

    public void testCreate(WechatPaySetting wechatPaySetting) throws Exception {
        MyConfig config = new MyConfig(wechatPaySetting);
        if (wechatPaySetting.getSandboxEnable()) {
            config.setKey(getSignKey(wechatPaySetting));
        }
        WXPay wxpay = new WXPay(config, false, wechatPaySetting.getSandboxEnable());
        Map<String, String> data = new HashMap<String, String>();
        data.put("body", "腾讯充值中心-QQ会员充值");
        data.put("out_trade_no", "20160909105951233012");
        data.put("fee_type", "CNY");
        data.put("total_fee", "101");
        data.put("spbill_create_ip", "123.12.12.123");
        data.put("notify_url", "http://回调地址");
        // 此处指定为扫码支付
        data.put("trade_type", "APP");
        data.put("product_id", "12");

        try {
            System.out.println("data : " + data);
            Map<String, String> resp = wxpay.unifiedOrder(data);
            System.out.println("resp : " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static String getSignKey(WechatPaySetting wechatPaySetting) throws Exception {
        MyConfig config = new MyConfig(wechatPaySetting);
        WXPay wxpay = new WXPay(config, true, wechatPaySetting.getSandboxEnable());
        Map<String, String> data = new HashMap<String, String>(3);
        data.put("mch_id", wechatPaySetting.getMchId());
        data.put("nonce_str", WXPayUtil.generateNonceStr());
        WXPayConstants.SignType signType = WXPayConstants.SignType.valueOf(wechatPaySetting.getSignType());
        data.put("sign", WXPayUtil.generateSignature(data, wechatPaySetting.getApiSecret(), signType));
        Map<String, String> response = wxpay.getSignKey(data);
        String signKey = response.get("sandbox_signkey");
        return signKey;
    }

    public static void main(String[] args) throws Exception {

        WechatPaySetting wechatPaySetting = new WechatPaySetting();
        //例如C:\Users\admin\Desktop\java_cert\wechat\apiclient_cert.p12
        wechatPaySetting.setApiCaertificatePath("证书所在地址");
        wechatPaySetting.setApiSecret("你的API秘钥");
        wechatPaySetting.setAppId("应用ID");
        wechatPaySetting.setMchId("商户号");
        wechatPaySetting.setSandboxEnable("true");
        wechatPaySetting.setSignType("MD5");
        wechatPaySetting.setNotifyUrl("通知地址");

        WXPayExample example = new WXPayExample();

        example.testCreate(wechatPaySetting);

        //example.queryOrder(WechatPaySetting);

        //example.refundQuery(WechatPaySetting);

        //example.closeOrder(WechatPaySetting);

        //example.handleNotify(WechatPaySetting);

        // example.getSignKeyTest(WechatPaySetting);
    }

    public void queryOrder(WechatPaySetting wechatPaySetting) throws Exception {
        MyConfig config = new MyConfig(wechatPaySetting);
        WXPay wxpay = new WXPay(config, true, wechatPaySetting.getSandboxEnable());

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "201609091059512330122");

        try {
            System.out.println("data : " + data);
            Map<String, String> resp = wxpay.orderQuery(data);
            System.out.println("resp : " + data);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void refundQuery(WechatPaySetting wechatPaySetting) throws Exception {
        MyConfig config = new MyConfig(wechatPaySetting);
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            System.out.println("data : " + data);
            Map<String, String> resp = wxpay.refundQuery(data);
            System.out.println("resp : " + resp);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void closeOrder(WechatPaySetting wechatPaySetting) throws Exception {
        MyConfig config = new MyConfig(wechatPaySetting);
        WXPay wxpay = new WXPay(config);

        Map<String, String> data = new HashMap<String, String>();
        data.put("out_trade_no", "2016090910595900000012");

        try {
            System.out.println("data : " + data);
            Map<String, String> resp = wxpay.closeOrder(data);
            System.out.println("resp : " + resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handleNotify(WechatPaySetting wechatPaySetting) throws Exception {
        // 支付结果通知的xml格式数据

        String notifyData = "<xml>\n" +
                "<appid><![CDATA[wx2421b1c4370ec43b]]></appid>\n" +
                "<is_subscribe><![CDATA[Y]]></is_subscribe>\n" +
                "<mch_id><![CDATA[10000100]]></mch_id>\n" +
                "<nonce_str><![CDATA[5d2b6c2a8db53831f7eda20af46e531c]]></nonce_str>\n" +
                "<openid><![CDATA[oUpF8uMEb4qRXf22hE3X68TekukE]]></openid>\n" +
                "<out_trade_no><![CDATA[1409811653]]></out_trade_no>\n" +
                "<result_code><![CDATA[SUCCESS]]></result_code>\n" +
                "<return_code><![CDATA[SUCCESS]]></return_code>\n" +
                "<sign><![CDATA[B552ED6B279343CB493C5DD0D78AB241]]></sign>\n" +
                "<sub_mch_id><![CDATA[10000100]]></sub_mch_id>\n" +
                "<time_end><![CDATA[20140903131540]]></time_end>\n" +
                "<total_fee>102</total_fee>\n" +
                " <cash_fee>99</cash_fee>\n" +
                "<coupon_fee>3</coupon_fee>\n" +
                "<coupon_count>2</coupon_count>    \n" +
                "<coupon_type_0>CASH</coupon_type_0>\n" +
                "<coupon_fee_0>1</coupon_fee_0> \n" +
                "<coupon_type_1>NOCASH</coupon_type_1>\n" +
                "<coupon_fee_1>2</coupon_fee_1>\n" +
                "<trade_type><![CDATA[JSAPI]]></trade_type>\n" +
                "<transaction_id><![CDATA[1004400740201409030005092168]]></transaction_id>\n" +
                "</xml>";

        MyConfig config = new MyConfig(wechatPaySetting);
        WXPay wxpay = new WXPay(config);
        // 转换成map
        Map<String, String> notifyMap = WXPayUtil.xmlToMap(notifyData);

        if (wxpay.isPayResultNotifySignatureValid(notifyMap)) {
            System.out.println("success");
            // 签名正确
            // 进行处理。
            // 注意特殊情况：订单已经退款，但收到了支付结果成功的通知，不应把商户侧订单状态从退款改成支付成功
        } else {
            // 签名错误，如果数据里没有sign字段，也认为是签名错误
            System.out.println("fail");
        }
    }

}
