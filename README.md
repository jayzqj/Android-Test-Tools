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
