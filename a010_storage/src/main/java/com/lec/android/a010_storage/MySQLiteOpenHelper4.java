package com.lec.android.a010_storage;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;
//SQLiteOpenHelper
//안드로이드에서 SQLite3 DB를 좀 더 쉽게 사용할 수 있도록 제공되는 클래스

public class MySQLiteOpenHelper4 extends SQLiteOpenHelper {
    public MySQLiteOpenHelper4(@Nullable Context context, @Nullable String name,
                               @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        Log.d("myapp","SQLiteOpenHelper 생성");
    }

    // 앱 설치 후 ''''''최초''''''에 ''''''데이터베이스가 없는 경우'''''',
    // 데이터베이스 생성을 위해 호출되는 callback
    // 주로 DDL등 테이블 생성하는 코드를 작성한다.

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.d("myapp","SQLiteOpenHelper] onCreate 호출");

        String sql = "CREATE TABLE student ("+"id INTEGER PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT, age INTEGER, address TEXT" + ")"
                ;
        db.execSQL(sql);
    }//end onCreate

    //데이터 베이스의 '버전' 이 바뀌었을 때 호출되는 callback 메소드
    //버전이 바뀌었을때 기존에 설치 운영되고 있는 데이터베이스를 어떻게 변경할 것인지 작성
    //각 버전의 변경 내용들을 버전마다 작성해야함



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d("myapp","SQLiteOpenHelper] onUpgrade 호출: "+oldVersion +" -> "+newVersion);

        String sql = "DROP TABLE IF EXISTS mytable"; //만약 테이블이 존재 한다면 삭제

        db.execSQL(sql);
        onCreate(db);  //다시 테이블 생성 하는 메서드 호출
    }//end onUpgrade
}
