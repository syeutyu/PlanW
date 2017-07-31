package com.example.user.junier;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import static com.example.user.junier.MainActivity.tag;

/**
 * Created by user on 2017-07-29.
 */

public class Datebase extends SQLiteOpenHelper {
    public static final String Schema = "PlanWT.db";

    public Datebase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        createDB(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void createDB(SQLiteDatabase db) { //DB생성
        Log.d(tag, "생성완료");
        String sql = "CREATE TABLE PlanWT (id TEXT DEFAULT ' ' ,password TEXT DEFAULT ' ',name TEXT DEFAULT ' ',date TEXT DEFAULT ' ',purpose TEXT DEFAULT ' ')"; //이부분 수정이 필요합니다
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateDate(String key, String data, String date) { //수정하기 부분으로 변경하고
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "UPDATE  PlanWT SET date = '" + date + "', purpose = '" + data + "' WHERE id = '" + key + "';";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void insertDate(String key, String data, String date) { //저장사항부분
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "INSERT INTO PlanWT (id ,date, purpose) VALUES('" + key + "','" + date + "','" + data + "');";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public void insertSign(String id, String password, String name) { //회원가입시 들어가는 정보
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "INSERT INTO PlanWT (id, password,name) VALUES('" + id + "','" + password + "','" + name + "')";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public JSONObject getSign(String id, String password) { //로그인시 정보 비교와 정보 제공
        SQLiteDatabase db = getReadableDatabase();
        JSONObject jsonObject = new JSONObject();
        Cursor cursor = db.rawQuery("SELECT * FROM PlanWT WHERE id ='" + id + "' AND " + "password = '" + password + "';", null);

        while (cursor.moveToNext()) {
            try {
                jsonObject.put("id", cursor.getString(0));
                jsonObject.put("password", cursor.getString(1));
                jsonObject.put("name", cursor.getString(2));
                jsonObject.put("date", cursor.getString(3));
                jsonObject.put("purpose", cursor.getString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(tag, String.valueOf(jsonObject));
        return jsonObject;
    }

    public JSONObject getPlan(String id) {
        SQLiteDatabase db = getReadableDatabase();
        JSONObject jsonObject = new JSONObject();
        Cursor cursor = db.rawQuery("SELECT * FROM PlanWT WHERE id = '" + id + "';", null);
        while (cursor.moveToNext()) {
            try {
                jsonObject.put("id", cursor.getString(0));
                jsonObject.put("password", cursor.getString(1));
                jsonObject.put("name", cursor.getString(2));
                jsonObject.put("date", cursor.getString(3));
                jsonObject.put("purpose", cursor.getString(4));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(tag, String.valueOf(jsonObject));
        return jsonObject;
    }
}