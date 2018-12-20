这是基于微信支付 Java SDK做了一小点修改以后的版本
------

对[微信支付开发者文档](https://pay.weixin.qq.com/wiki/doc/api/index.html)中给出的API进行了封装。

com.github.wxpay.sdk.WXPay类下提供了对应的方法：

|方法名 | 说明 |
|--------|--------|
|microPay| 刷卡支付 |
|unifiedOrder | 统一下单|
|orderQuery | 查询订单 |
|reverse | 撤销订单 |
|closeOrder|关闭订单|
|refund|申请退款|
|refundQuery|查询退款|
|downloadBill|下载对账单|
|report|交易保障|
|shortUrl|转换短链接|
|authCodeToOpenid|授权码查询openid|
|getSignKey|获取验签密钥接口|

* 注意:
* 证书文件不能放在web服务器虚拟目录，应放在有访问权限控制的目录中，防止被他人下载
* 建议将证书文件名改为复杂且不容易猜测的文件名
* 商户服务器要做好病毒和木马防护工作，不被非法侵入者窃取证书文件
* 请妥善保管商户支付密钥、公众帐号SECRET，避免密钥泄露
* 参数为`Map<String, String>`对象，返回类型也是`Map<String, String>`
* 方法内部会将参数会转换成含有`appid`、`mch_id`、`nonce_str`、`sign\_type`和`sign`的XML
* 可选HMAC-SHA256算法和MD5算法签名
* 通过HTTPS请求得到返回数据后会对其做必要的处理（例如验证签名，签名错误则抛出异常）
* 对于downloadBill，无论是否成功都返回Map，且都含有`return_code`和`return_msg`，若成功，其中`return_code`为`SUCCESS`，另外`data`对应对账单数据


## 示例可以参考demo包下的文件
具体验收用例可以参考官方验收文档，有了这个文档可以避免大家走很多的冤枉路，

