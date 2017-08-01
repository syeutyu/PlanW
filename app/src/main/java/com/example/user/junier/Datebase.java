package com.example.user.junier;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.UUID;

import static com.example.user.junier.MainActivity.tag;

/**
 * Created by user on 2017-07-29.
 */

public class Datebase extends SQLiteOpenHelper {
    public static final String Schema = "test.db";

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
        String sql = "CREATE TABLE test(num TEXT UNIQUE , id TEXT , password TEXT , name TEXT , date TEXT , purpose TEXT ); "; //이부분 수정이 필요합니다
        try {
            db.execSQL(sql);
        } catch (SQLException e) {
            e.getMessage();
        }
    }

    public void updateDate(String key, String data, String date) { //수정하기 부분으로 변경하고
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "UPDATE  test SET date = '" + date + "', purpose = '" + data + "' WHERE id = '" + key + "';";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void insertDate(String name, String data, String date) { //저장사항부분
        SQLiteDatabase db = getWritableDatabase();
        try {
            String sql = "INSERT INTO test (name ,date, purpose) VALUES('" + name + "','" + date + "','" + data + "');";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }


    public void insertSign(String id, String password, String name) { //회원가입시 들어가는 정보
        SQLiteDatabase db = getWritableDatabase();
        String num = makenum();
        Log.d(tag, num);
        try {
            String sql = "INSERT INTO test (num,id, password,name) VALUES('" + num + "','" + id + "','" + password + "','" + name + "');";
            Log.d(tag, sql);
            db.execSQL(sql);
        } catch (Exception e) {
            e.getMessage();
        }
    }

    public void drop() {
        SQLiteDatabase db = getWritableDatabase();
        String sql = "DROP TABLE test";
        Log.d(tag, "DROP TABLE");
        db.execSQL(sql);
    }

    public JSONObject getSign(String id, String password) { //로그인시 정보 비교와 정보 제공
        SQLiteDatabase db = getReadableDatabase();
//WHERE id ='" + id + "' AND " + "password = '" + password + "';"
        JSONObject jsonObject = new JSONObject();
        Cursor cursor = db.rawQuery("SELECT * FROM test WHERE id = '" + id + "' AND " + " password = '" + password + "';", null);

        while (cursor.moveToNext()) {
            try {
                jsonObject.put("num", cursor.getString(0));
                jsonObject.put("id", cursor.getString(1));
                jsonObject.put("password", cursor.getString(2));
                jsonObject.put("name", cursor.getString(3));
                jsonObject.put("date", cursor.getString(4));
                jsonObject.put("purpose", cursor.getString(5));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        Log.d(tag, String.valueOf(jsonObject));
        return jsonObject;
    }

    public JSONArray getPlan(String id) { //메인 페이지에서 데이터 갱신시 JsonArray로 해당하는 이름의사용자에게 데이터줌
        SQLiteDatabase db = getReadableDatabase();
        JSONArray jsonObject = new JSONArray();
        int i = 1;
        Cursor cursor = db.rawQuery("SELECT * FROM test WHERE name = '" + id + "';", null);
        while (cursor.moveToNext()) {
            try {
                String count = "count" + i;
                JSONObject jsono = new JSONObject();
                jsono.put("num", cursor.getString(0));
                jsono.put("id", cursor.getString(1));
                jsono.put("password", cursor.getString(2));
                jsono.put("name", cursor.getString(3));
                jsono.put("date", cursor.getString(4));
                jsono.put("purpose", cursor.getString(5));
                jsonObject.put(jsono);
                Log.d(tag + "Jsonarray ", String.valueOf(jsonObject));
                i++;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;

    }

    public String makenum() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }


}