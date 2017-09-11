#Day01 UI框架和整体框架
###学习目的
####1.提高设计程序能力
	组长（中级工程师）、架构师
	提高薪水
####2.程序框架
	2.1传统框架
 		定义来自建筑学
 		框架：地基和钢筋混凝土
 		非框架：门窗、非承重墙

	2.2.软件框架
		分层模式：MVC、MVP、MVVM 
		 	项目代码管理
		 	解耦不同层次	
		项目主体：BaseActivity、BaseFragment 
		 	对相似页面封装
		 	常用的工具类
		常规组件：Volley+SqliteOpenHelper+Gson+(再次封装Volley)+BaseAdapter+Picasso
		  	常规功能(`网络访问`,`网络缓存`,`数据解析`,`网络协议的封装`,									`图片加载以及缓存`,`UI展现`)
		  	不常规模块(`聊天模块`,`支付模块`,`安全模块`)

####3谷歌市场框架
		组件：fragment+ viewpager，
		设计模式：MVC
		组件：OKhttpclient + gson文件 + Gson + (封装okhttpclient) + (封装BaseAdapter) + Picasso
		能力：独立开发，架构
		 JD是HR和猎头用语，job description,职位信息描述
		 http://jobs.zhaopin.com/535029621250240.htm?ssidkey=y&ss=201&ff=03

####4什么是好的框架?
		 选的技术解决方案靠谱;
		 代码封装合理,写更少的代码完成更多的功能;
		 易于分工,方便协同开发;
		 可扩展性，方便修改
		
		热门框架：Retrofit + GreenDao + GsonConvert + Rxjava + RecycleViewAdapter + Glide



####学习内容

		1.actionbar导航、抽屉效果drawlayout、 indicator指示器、自定义AsyncTask(初始化view/加载数据/刷新UI)
		2.fragmentPagerAdapter，多页面BasicAdpater抽取，okhttpclient，线程池
		3.加载更多，协议封装，json缓存，无限轮播
		4.其他页面View组件（图片自适应、飞出效果、分类页面）
		5.详情页面展示、属性动画
		6.多线程下载、源码拓展(指示器源码、fragmentpagerAdapter源码、线程池源码、瀑布流源码、okhttpclient源码)
		2学习重点
		1.面向对象
			封装、继承、多态、
		2.设计模式
			监听者、工厂、单例

####服务器搭建
#1文件说明
		GooglePlayServer:java ee工程,我们的服务器
		GooglePlayServer.war:java ee工程的war包形式
		WebInfos:资源文件，类似数据库
		GooglePlayServerAndroid:手机端的服务器,可以运行servlet

#2.源码形式.
		 1. 用java ee 版eclipse导入工程GooglePlayServer.
		 2. 修改工程目录下system.properties为WebInfos所在的目录,如老师的电脑为  	dir=D:\Lesson_GooglePlay+6\day01\Server`，需要注意要么用"/"或者"\\"
		 3. 部署java ee工程到tomcat,然后运行
		 4. 在pc和手机上分别验证 http://localhost:8080/GooglePlayServer/home?index=0
				
#3.war包方式:
		 1.把war包放到D:\java_web\apachetomcat7.0.54windowsx64\apachetomcat7.0.54\webapps目录下面就可以,然后启动tomcat会自动解压war包.
		 2. 启动tomcat,自动解压war包,并运行程序 
		 3. 修改工程目录下system.properties为WebInfos所在的目录,如me的电脑为  	dir=D:\Lesson_GooglePlay+6\day01\Server`，需要注意要么用"/"或者"\\"
		 4. 在pc和手机上分别验证 
		 5. `注意:tomcat必须使用7以上版本`

