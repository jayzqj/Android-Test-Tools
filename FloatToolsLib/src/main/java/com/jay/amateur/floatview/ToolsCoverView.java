package com.jay.amateur.floatview;

import android.content.Context;
import android.content.Intent;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;

import com.jay.amateur.Check;
import com.jay.amateur.Config;
import com.jay.amateur.OperatorViewCallBack;
import com.jay.amateur.R;


public class ToolsCoverView extends FrameLayout {

    private FloatView mFloatView;
    private OperatorView mOperatorView;

    private GridView mGridView;

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
        mGridView = (GridView) mOperatorView.findViewById(R.id.container);
        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActionBean bean = (ActionBean) adapterView.getItemAtPosition(i);
                if (TextUtils.isEmpty(bean.className)) {
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(getContext().getApplicationContext(), bean.className);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (intent.resolveActivity(getContext().getPackageManager()) != null) {
                    mOperatorView.popupDismiss();
                    mOperatorView.setVisibility(GONE);
                    getContext().getApplicationContext().startActivity(intent);
                } else {
                    Toast.makeText(getContext().getApplicationContext(), "对应的Activity不存在", Toast.LENGTH_SHORT).show();
                }

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
