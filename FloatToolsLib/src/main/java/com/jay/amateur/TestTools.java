package com.jay.amateur;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.text.TextUtils;

import com.jay.amateur.pref.PreUtil;


public class TestTools {

    public static final int INFO_KEY = R.layout.sak_container_layout;
    private static Manager sManager;


    public static synchronized void init(Application application) {
        Check.isNull(application, "application");
        init(application, null);
    }


    public static synchronized void init(Application application, Config config) {
        Check.isNull(application, "application");
        if (sManager == null) {
            if (config == null)
                config = new Config.Build(null, null).build();
            sManager = new Manager(application, config);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.ICE_CREAM_SANDWICH) {
                application.registerActivityLifecycleCallbacks(new LifecycleCallbacksAdapter() {
                    @Override
                    public void onActivityResumed(Activity activity) {
                        super.onActivityResumed(activity);
                        resume(activity);
                    }

                    @Override
                    public void onActivityPaused(Activity activity) {
                        super.onActivityPaused(activity);
                        pause(activity);
                    }
                });
            }
        }

    }

    public static synchronized void setListener(OperatorViewCallBack callBack) {
        if (sManager == null) {
            throw new RuntimeException("you should first call method:init()!");
        }
        sManager.setListener(callBack);
    }

//    public static synchronized void updateServerUrl(String url) {
//        if (sManager == null) {
//            throw new RuntimeException("you should first call method:init()!");
//        }
//        sManager.updateServerUrl(url);
//    }

    public static void resume(Activity activity) {
        if (sManager == null) {
            throw new RuntimeException("init first!");
        }
        Check.isNull(activity, "activity");
        sManager.attach(activity);
    }

    public static void pause(Activity activity) {
        Check.isNull(sManager, "init first!");
        Check.isNull(activity, "activity");
        sManager.detach(activity);
    }

//    public static void unInstall(Activity activity) {
//        Check.isNull(activity, "activity");
//        detch(activity);
//    }

    public static String setURL(Context context, String url) {
        String cacheUrl = PreUtil.getInst(context).getKeyString("url", null);
        if (TextUtils.isEmpty(cacheUrl)) {
            PreUtil.getInst(context).setKey("url", url);
            cacheUrl=url;
        }
        return cacheUrl;
    }
}
