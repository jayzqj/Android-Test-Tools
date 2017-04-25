package com.jay.amateur.pref;

import android.content.Context;
import android.content.SharedPreferences;


public class PreUtil implements IPre {

    private static final String PREFERENCE_FILE_NAME = "xx_ss_ss_pr_kk";

    private SharedPreferences mPrefInternal;

    private Context mCtx;

    private static PreUtil instance;

    @Override
    public void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPrefInternal.registerOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener) {
        mPrefInternal.unregisterOnSharedPreferenceChangeListener(listener);
    }

    @Override
    public int getKeyInt(String key, int defaultVal) {
        return mPrefInternal.getInt(key, defaultVal);
    }

    @Override
    public int getKeyIntRes(String key, int defaultValRes) {
        return mPrefInternal.getInt(key,
                mCtx.getResources().getInteger(defaultValRes));
    }

    @Override
    public boolean getKeyBoolean(String key, boolean defaultVal) {
        return mPrefInternal.getBoolean(key, defaultVal);
    }

    @Override
    public boolean getKeyBooleanRes(String key, int defaultValRes) {
        return mPrefInternal.getBoolean(key,
                mCtx.getResources().getBoolean(defaultValRes));
    }

    @Override
    public String getKeyString(String key, String defaultVal) {
        return mPrefInternal.getString(key, defaultVal);
    }

    @Override
    public String getKeyStringRes(String key, int defaultValRes) {
        return mPrefInternal.getString(key,
                mCtx.getResources().getString(defaultValRes));
    }

    @Override
    public long getKeyLong(String key, long defaultVal) {
        return mPrefInternal.getLong(key, defaultVal);
    }

    @Override
    public long getKeyLongRes(String key, int defaultValRes) {
        return mPrefInternal.getLong(key,
                mCtx.getResources().getInteger(defaultValRes));
    }

    @Override
    public float getKeyFloat(String key, float defaultVal) {
        return mPrefInternal.getFloat(key,
                defaultVal);
    }

    @Override
    public void setKey(String key, int value) {
        SharedPreferences.Editor editor = mPrefInternal.edit();
        editor.putInt(key, value);
        commitEditor(editor);
    }

    @Override
    public void setKey(String key, boolean value) {
        SharedPreferences.Editor editor = mPrefInternal.edit();
        editor.putBoolean(key, value);
        commitEditor(editor);
    }

    @Override
    public void setKey(String key, String value) {
        SharedPreferences.Editor editor = mPrefInternal.edit();
        editor.putString(key, value);
        commitEditor(editor);
    }

    @Override
    public void setKey(String key, long value) {
        SharedPreferences.Editor editor = mPrefInternal.edit();
        editor.putLong(key, value);
        commitEditor(editor);
    }

    @Override
    public void deleteKey(String key) {
        SharedPreferences.Editor editor = mPrefInternal.edit();
        editor.remove(key);
        commitEditor(editor);
    }

    @Override
    public boolean containsKey(String key) {
        return mPrefInternal.contains(key);
    }

    private static void commitEditor(SharedPreferences.Editor editor) {
        editor.apply();
    }


    private PreUtil(Context ctx) {
        mPrefInternal = ctx.getSharedPreferences(PREFERENCE_FILE_NAME, 0);
        mCtx = ctx;
    }


    public static IPre getInst(Context ctx) {
        if (instance == null) {
            synchronized (PreUtil.class) {
                if (instance == null) {
                    instance = new PreUtil(ctx.getApplicationContext());
                }
            }
        }
        return instance;
    }


}
