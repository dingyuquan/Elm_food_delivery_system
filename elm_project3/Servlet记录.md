## Servlet记录

[TOC]

#### 1. 导入别人已经写好的web项目

##### 步骤

1. File→import→如下页面→next

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918000349363.png" alt="image-20220918000349363" style="zoom:50%;" />

2. 选择路径，需要选择如图所示的工程的那个文件夹

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918225152727.png" alt="image-20220918225152727" style="zoom:50%;" />

选择路径 → finish

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918225012254.png" alt="image-20220918225012254" style="zoom:50%;" />

#### 2. Tomcat启动失败

##### 问题：指定的主资源集无效

解决：将server.xml → Source中的 <Context DocBase ...> 注释掉

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220917235925376.png" alt="image-20220917235925376" style="zoom:70%;" />

(不是主要问题)

#### 3. 新建一个Servers

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918213038306.png" alt="image-20220918213038306" style="zoom:50%;" />

右键单击，选择add and remove

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918213210157.png" alt="image-20220918213210157" style="zoom:50%;" />

选中elm，添加

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220918213321532.png" alt="image-20220918213321532" style="zoom:50%;" />



#### 4. STS包分层显示

Package Presentation → Hierarchical

 <img src="C:\Users\郭璇\AppData\Roaming\Typora\typora-user-images\image-20220919102440392.png" alt="image-20220919102440392" style="zoom:50%;" />



#### 5. 端口释放

> netstat -ano|findstr 被占用端口号
>
> taskkill /t /f /im 被占用端口对应的TCP号

出现无法终止PID的进程，拒绝访问

用管理员身份打开命令提示符



#### 6. 报错The project cannot be built until build path errors are resolved

解决：参考[(54条消息) eclipse解决The project cannot be built until build path errors are resolved错误_培根芝士的博客-CSDN博客](https://blog.csdn.net/watson2017/article/details/86504220)



#### 7. 打开项目后出小红曲线

右键 -> properties ->UTF-8



#### 8. servers可运行但404
