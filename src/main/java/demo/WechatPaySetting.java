package demo;

import com.github.wxpay.sdk.WXPayConstants;

import java.io.*;

/**
 * @program: wxpay-sdk
 * @description: 微信支付配置信息
 * @author: mixzxu
 * @create: 2018-12-19 21:56
 **/
public class WechatPaySetting {

    private static final long serialVersionUID = -337474606692439261L;

    private String appId;

    private String notifyUrl;

    private String mchId;

    private String apiSecret;

    private String signType;

    private String apiCaertificatePath;

    private String sandboxEnable;

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getNotifyUrl() {
        return notifyUrl;
    }

    public void setNotifyUrl(String notifyUrl) {
        this.notifyUrl = notifyUrl;
    }

    public String getMchId() {
        return mchId;
    }

    public void setMchId(String mchId) {
        this.mchId = mchId;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public void setApiSecret(String apiSecret) {
        this.apiSecret = apiSecret;
    }

    public String getSignType() {
        return WXPayConstants.SignType.valueOf(signType).name();
    }

    public void setSignType(String signType) {
        this.signType = signType;
    }

    public String getApiCaertificatePath() {
        return apiCaertificatePath;
    }

    public void setApiCaertificatePath(String apiCaertificatePath) {
        this.apiCaertificatePath = apiCaertificatePath;
    }

    public boolean getSandboxEnable() {
        boolean sanboxEnable = Boolean.valueOf(this.sandboxEnable);
        return sanboxEnable;
    }

    public void setSandboxEnable(String sandboxEnable) {
        this.sandboxEnable = sandboxEnable;
    }

    /**
     * 获得证书文件
     */
    public InputStream getApiCaertificatePathStream() {
        ByteArrayInputStream byteArrayInputStream = null;
        try {
            File file = new File(apiCaertificatePath);
            InputStream certStream = new FileInputStream(file);
            byte[] bytes = new byte[(int) file.length()];
            certStream.read(bytes);
            certStream.close();
            byteArrayInputStream = new ByteArrayInputStream(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                byteArrayInputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return byteArrayInputStream;
    }

}
