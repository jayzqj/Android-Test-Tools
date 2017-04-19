package com.jay.amateur;

import android.app.Activity;
import android.content.Context;
import android.view.ViewGroup;

import com.jay.amateur.floatview.ToolsCoverView;


class Manager {

    private ToolsCoverView mCoverView;

    private Context mContext;

    Manager(Context context, Config config) {
        mCoverView = new ToolsCoverView(context.getApplicationContext(), config);
    }

    public synchronized void setListener(OperatorViewCallBack callBack) {
        if (callBack == null) return;
        mCoverView.setListener(callBack);
    }
    public  synchronized void updateServerUrl(String url) {
        mCoverView.updateServerUrl(url);
    }

    void detach(Activity activity) {
        ((ViewGroup) activity.getWindow().getDecorView()).removeView(mCoverView);
    }

    void attach(Activity activity) {
        if (mCoverView.getParent() != null) {
            ((ViewGroup) mCoverView.getParent()).removeView(mCoverView);
        }

        ViewGroup dectorView = ((ViewGroup) activity.getWindow().getDecorView());
        dectorView.addView(mCoverView);
    }
}
