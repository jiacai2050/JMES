JMES
=================
一款轻巧简单的邮件群发软件

使用说明Usage 
=========
1. 在conf文件夹下创建jmes.properties文件
2. jmes.properties支持以下属性
－　email=your email<必选>
－　pwd=your email密码<必选>
－　recipients=制定收件人的文件，可以是网页幺<必选>
－　msgFile=存放正文的文件路径<必选>
－　attachFolder=存放正文中图片的文件夹路径<可选>
－　picFolder=存放附件的文件夹夹<可选>
3. 运行bin/jmes.sh <邮件主题> 来发送邮件
4. 本项目正在开发中



关于About
=========
- JMES是JiacaiMassEmailSender的单词首字母简写
- 本项目基于[Apache Common Email](http://commons.apache.org/proper/commons-email/index.html)之上开发，感谢开源社区。
- 有问题或建议请反馈到：
    - 微博 ：[@LJC_class](http://weibo.com/liujiacai/)
    - 邮箱 ：jiacai2050@gmail.com  
- Have fun! 

License
-------
Copyright © 2014 Jiacai

本项目选用LGPL协议<a href="http://www.gnu.org/licenses/lgpl.html"><img src="https://www.gnu.org/graphics/lgplv3-147x51.png"/></a>

一张图让你看清各种协议的区别
<img src="http://image.beekka.com/blog/201105/free_software_licenses.png" width="600px" height="400px"/>
