package demo;

import com.github.wxpay.sdk.IWXPayDomain;
import com.github.wxpay.sdk.WXPayConfig;
import com.github.wxpay.sdk.WXPayConstants;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

/**
 * @program: wxpay-sdk
 * @description: 微信支付配置转换类
 * @author: mixzxu
 * @create: 2018-12-19 21:52
 **/
public class MyConfig extends WXPayConfig {

    private String appID;

    private String mchID;

    private String key;

    private byte[] certData;

    public MyConfig(WechatPaySetting wechatPaySetting) throws Exception {
        this.appID = wechatPaySetting.getAppId();
        this.mchID = wechatPaySetting.getMchId();
        this.key = wechatPaySetting.getApiSecret();
        String certPath = wechatPaySetting.getApiCaertificatePath();
        File file = new File(certPath);
        InputStream certStream = new FileInputStream(file);
        this.certData = new byte[(int) file.length()];
        certStream.read(this.certData);
        certStream.close();
    }

    public void setKey(String key) {
        this.key = key;
    }

    @Override
    public IWXPayDomain getWXPayDomain() {

        IWXPayDomain iwxPayDomain = new IWXPayDomain() {
            public void report(String domain, long elapsedTimeMillis, Exception ex) {

            }

            public DomainInfo getDomain(WXPayConfig config) {
                return new IWXPayDomain.DomainInfo(WXPayConstants.DOMAIN_API, true);
            }
        };
        return iwxPayDomain;

    }

    @Override
    public String getAppID() {
        return this.appID;
    }

    @Override
    public String getMchID() {
        return this.mchID;
    }

    @Override
    public String getKey() {
        return this.key;
    }

    @Override
    public InputStream getCertStream() {
        ByteArrayInputStream certBis = new ByteArrayInputStream(this.certData);
        return certBis;
    }
}
