package com.jay.amateur.pref;

import android.content.SharedPreferences;

/**
 * Created by junhzhan on 5/5/16.
 */
public interface IPre {

    void registerOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
    void unregisterOnSharedPreferenceChangeListener(SharedPreferences.OnSharedPreferenceChangeListener listener);
    int getKeyInt(final String key, final int defaultVal);

    int getKeyIntRes(final String key, final int defaultValRes);

    boolean getKeyBoolean(final String key,
                          final boolean defaultVal);
    boolean getKeyBooleanRes(final String key,
                             final int defaultValRes);

    String getKeyString(final String key, final String defaultVal);


    String getKeyStringRes(final String key,
                           final int defaultValRes);

    long getKeyLong(final String key, final long defaultVal);

    long getKeyLongRes(final String key, final int defaultValRes);

    float getKeyFloat(final String key, final float defaultVal);

    void setKey(final String key, final int value);

    void setKey(final String key, final boolean value);

    void setKey(final String key, final String value);

    void setKey(final String key, final long value);

    void deleteKey(final String key);

    boolean containsKey(String key);
}
