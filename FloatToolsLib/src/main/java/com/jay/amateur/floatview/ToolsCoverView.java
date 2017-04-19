package com.jay.amateur.floatview;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.jay.amateur.Check;
import com.jay.amateur.Config;
import com.jay.amateur.OperatorViewCallBack;
import com.jay.amteur.floatview.R;


public class ToolsCoverView extends FrameLayout {

    private FloatView mFloatView;
    private OperatorView mOperatorView;

    private EditText mEditText;
    private Config mConfig;

    private OperatorViewCallBack mCallBack;

    public ToolsCoverView(Context context, Config config) {
        super(context);
        mConfig = config;
        init();
    }

    public void setListener(OperatorViewCallBack callBack) {
        mCallBack = callBack;
    }

    public void updateServerUrl(String url) {
        if (mEditText != null)
            mEditText.setText(url);

    }

    private void init() {

        inflate(getContext(), R.layout.sak_container_layout, this);


        mOperatorView = (OperatorView) findViewById(R.id.operatorView);
        mEditText = (EditText) mOperatorView.findViewById(R.id.server);
        mOperatorView.setData(mConfig.getMaction());
        mOperatorView.setServerData(mConfig.getServerData());

        mFloatView = (FloatView) findViewById(R.id.floatView);

        mFloatView.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                mOperatorView.setVisibility(VISIBLE);
            }
        });

        mOperatorView.findViewById(R.id.ok).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = mEditText.getText().toString();
                if (Check.checkURL(url)) {
                    if (mCallBack != null)
                        mCallBack.switchServer(url);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "URL不合法", Toast.LENGTH_SHORT).show();
                    return;
                }
                mOperatorView.setVisibility(GONE);
            }
        });


    }


}
