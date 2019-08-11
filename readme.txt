SportingMallDesk为系统前台文件
SportingMallManager为系统后台文件

调试前注意自己电脑的Tomcat服务器版本及JDK版本

前台测试用户：zhazha  012345（如需其他测试用户，密码请采用MD5解密）
后台管理员用户: admin admin(超级管理员权限)  huahua 012345 (商品管理权限)

启动系统前将images文件夹放置D盘中（images文件夹中存放的数据库商品表图片所存放的地址）
同时在Tomcat服务器的server.xml文件中添加
<Context debug="0" docBase="D:/images" path="/upload" reloadable="true"/>
如图片中显示的那样