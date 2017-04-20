package com.jay.mapper;

import android.app.Application;
import android.widget.Toast;

import com.jay.amateur.Config;
import com.jay.amateur.OperatorViewCallBack;
import com.jay.amateur.TestTools;
import com.jay.amateur.floatview.ActionBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zengqingjie on 17/4/18.
 */

public class FloatToolsUtil {
    public static synchronized void init(final Application application) {
        //配置服务器地址
        List<String> serverData = new ArrayList<>();
        serverData.add("https://www.baidu.com");
        serverData.add("https://www.google.com");
        serverData.add("https://www.12345.com");
        //配置功能测试项
        List<ActionBean> data = new ArrayList<>();
        data.add(new ActionBean("二维码扫描", MainActivity.class.getCanonicalName()));
        data.add(new ActionBean("交易详情", MainActivity.class.getCanonicalName()));
        data.add(new ActionBean("密码设置", MainActivity.class.getCanonicalName()));
        data.add(new ActionBean("产品详情", MainActivity.class.getCanonicalName()));
        data.add(new ActionBean("登录页面", MainActivity.class.getCanonicalName()));
        data.add(new ActionBean("订单页面", MainActivity.class.getCanonicalName()));
        Config config = new Config.Build(serverData, data).build();
        TestTools.init(application, config);
        TestTools.setListener(new OperatorViewCallBack() {
            @Override
            public void switchServer(String url) {
                Toast.makeText(application, url, Toast.LENGTH_SHORT).show();
                TestTools.updateServerUrl(url);
            }
        });
    }
}
