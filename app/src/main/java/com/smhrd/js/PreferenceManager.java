package com.smhrd.js;

import android.content.Context;
import android.content.SharedPreferences;

public class PreferenceManager {
    private static final String PREFERENCES_NAME = "my_app";
    private static final String DEFAULT_VALUE_STRING = "";
    private static final boolean DEFAULT_VALUE_BOOLEAN = false;
    private static final int DEFAULT_VALUE_INT = -1;
    private static final long DEFAULT_VALUE_LONG = -1L;
    private static final float DEFAULT_VALUE_FLOAT = -1F;

    private static SharedPreferences getPreferences(Context context) { //context 어플에 대한 정보
        return context.getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
    }

    ;

    // String 값 저장
    public static void setString(Context context, String key, String value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit(); //에디터 가져오기
        editor.putString(key, value); // 에디터사용해서 넣기
        editor.commit();
    }

    public static void setBoolean(Context context, String key, Boolean value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit(); //에디터 가져오기
        editor.putBoolean(key, value); // 에디터사용해서 넣기
        editor.commit();
    }

    public static void setInt(Context context, String key, int value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit(); //에디터 가져오기
        editor.putInt(key, value); // 에디터사용해서 넣기
        editor.commit();
    }

    public static void setLong(Context context, String key, long value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit(); //에디터 가져오기
        editor.putLong(key, value); // 에디터사용해서 넣기
        editor.commit();
    }

    public static void setFloat(Context context, String key, float value) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit(); //에디터 가져오기
        editor.putFloat(key, value); // 에디터사용해서 넣기
        editor.commit();
    }

    //String값 꺼내기
    public static String getString(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        String value = prefs.getString(key, DEFAULT_VALUE_STRING);
        return value;
    }

    //Boolean값 꺼내기
    public static Boolean getBoolean(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        boolean value = prefs.getBoolean(key, DEFAULT_VALUE_BOOLEAN);
        return value;
    }

    public static int getInt(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        int value = prefs.getInt(key, DEFAULT_VALUE_INT);
        return value;
    }

    public static long getLong(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        Long value = prefs.getLong(key, DEFAULT_VALUE_LONG);
        return value;
    }

    public static float getFloat(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        float value = prefs.getFloat(key, DEFAULT_VALUE_FLOAT);
        return value;
    }

    // key 값과 일치하는 것 삭제
    public static void removekey(Context context, String key) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(key);
        editor.commit();
    }

    // 모든 저장 데이터 삭제
    public static void clear(Context context) {
        SharedPreferences prefs = getPreferences(context);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.commit();
    }
}
