package com.jay.amateur.floatview;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.jay.amteur.floatview.R;

import java.util.ArrayList;
import java.util.List;


public class OperatorView extends LinearLayout {
    private GridView container;
    private LayoutInflater mInflater;
    private GirdViewAdapter mAdapter;
    private MyAdapter listAdapter;
    private EditText mServer;
    private ImageButton mImagebut;
    private List<String> serverData = new ArrayList<>();
    private PopupWindow popup;
    private ListView listView;
    private List<ActionBean> data = new ArrayList<>();


    public void setServerData(List<String> serverData) {
        this.serverData = serverData;
        if (listAdapter != null)
            listAdapter.notifyDataSetChanged();

    }


    public void setData(List<ActionBean> data) {
        this.data = data;
        if (mAdapter != null)
            mAdapter.notifyDataSetChanged();
    }

    public OperatorView(Context context) {
        super(context);
        init(context);
    }

    public OperatorView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }


    private void init(final Context context) {
        setGravity(Gravity.CENTER);
        setOrientation(VERTICAL);
        mInflater = LayoutInflater.from(context);
        inflate(getContext(), R.layout.sak_operator_layout, this);
        container = (GridView) findViewById(R.id.container);
        mServer = (EditText) findViewById(R.id.server);
        mImagebut = (ImageButton) findViewById(R.id.imagebut);
        mAdapter = new GirdViewAdapter();
        listAdapter = new MyAdapter();
        container.setAdapter(mAdapter);
        listView = new ListView(context.getApplicationContext());
        listView.setDivider(getResources().getDrawable(R.drawable.gray));//设置分割线
        listView.setDividerHeight(1);
        listView.setVerticalScrollBarEnabled(false);//不显示滑动条
        listView.setAdapter(listAdapter);
        mImagebut.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                popupWindow(context);
            }
        });
        mServer.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mServer.setSelection(editable.length());
            }
        });
        container.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ActionBean bean = (ActionBean) adapterView.getItemAtPosition(i);
                if (TextUtils.isEmpty(bean.className)) {
                    return;
                }
                Intent intent = new Intent();
                intent.setClassName(context.getApplicationContext(), bean.className);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                if (intent.resolveActivity(context.getPackageManager()) != null) {
                    popup.dismiss();//关闭窗口
                    OperatorView.this.setVisibility(GONE);
                    context.startActivity(intent);
                } else {
                    Toast.makeText(context, "对应的Activity不存在", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    /**
     * 显示窗口
     */
    private void popupWindow(Context context) {
        hideSoftInputMethod(context);
        popup = new PopupWindow(this);
        popup.setWidth(this.getWidth());
        popup.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        // 实例化一个ColorDrawable颜色
        ColorDrawable dw = new ColorDrawable(Color.parseColor("#b0000000"));
        // 设置弹出窗体的背景
        popup.setBackgroundDrawable(dw);
        popup.setContentView(listView);//设置显示内容
        popup.setOutsideTouchable(true);//点击PopupWindow以外的区域自动关闭该窗口
        popup.showAsDropDown(mServer, 0, 0);//显示在edit控件的下面0,0代表偏移量
    }


    //适配器
    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return serverData.size();
        }

        @Override
        public Object getItem(int position) {
            return position;
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {
            HolderView holderView = null;
            if (convertView == null) {
                convertView = mInflater.inflate(R.layout.server_item, null);
                holderView = new HolderView(convertView);
                convertView.setTag(holderView);
            } else {
                holderView = (HolderView) convertView.getTag();
            }

            holderView.text.setText(serverData.get(position));
//            //为listView的每一个子条目设置监听,以区分每个删除按钮
//            holderView.delete.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    serverData.remove(position);//删除listview的这行数据
//                    MyAdapter.this.notifyDataSetChanged();//更新ListView的数据
//                }
//            });
            //listview的单击事件
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //将单机的这项的文本显示在EditText中，并关闭窗口
                    mServer.setText(serverData.get(position));
                    popup.dismiss();//关闭窗口
                }
            });
            return convertView;
        }

        class HolderView {
            TextView text;
            // ImageButton delete;

            public HolderView(View view) {
                text = (TextView) view.findViewById(R.id.textView);
                // delete = (ImageButton) view.findViewById(R.id.delete);
            }
        }
    }

    class GirdViewAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            if (data == null) {
                return 0;
            }
            return data.size();
        }

        @Override
        public Object getItem(int position) {
            if (data == null || position >= data.size()) {
                return null;
            }
            return data.get(position);
        }

        @Override
        public long getItemId(int position) {
            if (data == null || position >= data.size()) {
                return 0;
            }
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder viewHolder = null;
            if (convertView == null || convertView.getTag() == null) {
                convertView = mInflater.inflate(R.layout.list_item, null);
                viewHolder = new ViewHolder(convertView);
                convertView.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) convertView.getTag();
            }
            ActionBean bean = (ActionBean) getItem(position);

            viewHolder.mTxt.setText(bean.name);
            return convertView;
        }

        class ViewHolder {
            TextView mTxt;

            ViewHolder(View view) {
                mTxt = (TextView) view.findViewById(R.id.txt);
            }
        }
    }

    /**
     * 隐藏输入法
     */
    public void hideSoftInputMethod(Context context) {

        InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
//隐藏软键盘 //
        imm.hideSoftInputFromWindow(mServer.getWindowToken(), 0);

    }
}
