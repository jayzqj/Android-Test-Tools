# Android-Test-Tools
通过APP内的悬浮小工具，可随意修改APP服务器的地址
新开发的Activity无需入扣依赖，即可直接测试


![pic1](https://github.com/zenqingjie/Android-Test-Tools/blob/master/app/image/1.png)


![pic1](https://github.com/zenqingjie/Android-Test-Tools/blob/master/app/image/2.png)

引入

Android Studio

将Android-Test-Tools引入

dependencies {
    compile project(':FloatToolsLib')
}

也可以直接
dependencies {
   compile 'com.jay.amateur:testTools:1.0.2'
}


使用：

在程序的入口处，如果是debug开发，调用初始化方法
我这里是在程序的Application的onCreate()方法中调用：
  //初始化测试工具
  if (BuildConfig.ENABLE_DEBUG_LOG) {
   FloatToolsUtil.init(this);
  }


public class FloatToolsUtil {
    public static synchronized void init(final Application application) {
        //配置服务器地址
        List<String> serverData = new ArrayList<>();
        serverData.add("http://XXX.XXX.XXX.XXX/XXX/");
        serverData.add("http://XXX.XXX.XXX.XXX/XXX/");
        //配置功能测试项
        List<ActionBean> data = new ArrayList<>();
        data.add(new ActionBean("二维码扫描1", ActivityTest1.class.getCanonicalName()));
        data.add(new ActionBean("二维码扫描2", ActivityTest2.class.getCanonicalName()));
        data.add(new ActionBean("二维码扫描3", ActivityTest3.class.getCanonicalName()));
        data.add(new ActionBean("二维码扫描4", ActivityTest4.class.getCanonicalName()));
        data.add(new ActionBean("二维码扫描5", ActivityTest5.class.getCanonicalName()));
        data.add(new ActionBean("二维码扫描6", ActivityTest6.class.getCanonicalName()));
        Config config = new Config.Build(serverData, data).build();
        //初始化工具数据
        TestTools.init(application, config);
        //设置当前服务器地址
        TestTools.updateServerUrl(Configs.BASE_URL);
        //设置地址切换回调
        TestTools.setListener(new OperatorViewCallBack() {
            @Override
            public void switchServer(String url) {
                //Toast.makeText(application, url, Toast.LENGTH_SHORT).show();
                if (!Configs.BASE_URL.toLowerCase().equals(url.toLowerCase())) {
                    //更新当前服务器地址
                    TestTools.updateServerUrl(url);
                    Configs.BASE_URL = url;
                    //切换不同服务器地址后，执行用户登出操作
                    User.userLogout();
                }
            }
        });
    }
}
