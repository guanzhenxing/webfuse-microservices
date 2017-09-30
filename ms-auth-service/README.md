OAuth2中的角色：

- Resource Server:被授权访问的资源
- Authorization Server：OAUTH2认证授权中心
- Resource Owner： 用户
- Client：使用API的客户端(如Android 、IOS、web app)

Grant Type：

- Authorization Code:用在服务端应用之间
- Implicit:用在移动app或者web app(这些app是在用户的设备上的，如在手机上调起微信来进行认证授权)
- Resource Owner Password Credentials(password):应用直接都是受信任的(都是由一家公司开发的，本例子使用)
- Client Credentials:用在应用API访问。