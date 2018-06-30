模仿天猫商城
====
效果预览前台：http://47.96.230.228:8080/my_ssm/forehome
        后台：http://47.96.230.228:8080/my_ssm/admin_category_list
        
如何使用
---
1.使用IDEA或eclipse打开              
2.idea中打开               
3.配置tomcat项目路径/my_ssm 默认路径http://localhost:8080/my_ssm          
4.打开application-context.xml,替换数据源，配置自己的mysql库，用户名和密码            
5.将sql文件导入到数据库          
6.添加WEB-INF下的lib包到项目下           
7.启动tomcat              
8.输入路径http://localhost:8080/my_ssm              

#jdk为1.8

实现功能
---
模仿天猫商城，实现了注册，登录，产品页，购物车，搜索，排序等功能。

技术介绍
---
   1.Maven构建项目    
   2.使用的Mysql的数据库   
   3.Tomcat作用应用服务器   
   4.Dao层采用Mybatis，Controller层采用SpringMVC，Spring对Mybatis和SpringMVC进行整合和事务管理    
   5.前端使用Bootstrap风格框架          
   6.使用 MybatisGenerator逆向工程插件
