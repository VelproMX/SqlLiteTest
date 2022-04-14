package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {
    private ListView listView;
    private MyDatabaseHelper dbHelper;
    private View head;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView) findViewById(R.id.book_listview);
        head= View.inflate(this,R.layout.head_title,null);
        listView.addHeaderView(head);
        dbHelper = new MyDatabaseHelper(this, "Library.db", null, 2);
//        Button createDatabase = (Button) findViewById(R.id.create_database);
//        createDatabase.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                dbHelper.getWritableDatabase();
//            }
//        });
//        Button addData = (Button) findViewById(R.id.add_data);
//        addData.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                ContentValues values = new ContentValues();
////                values.put("category_name", "经济类");
////                values.put("category_code", 1);
////                db.insert("Category",null,values);
////                values.clear();
////
////                values.put("category_name", "文学类");
////                values.put("category_code", 2);
////                db.insert("Category",null,values);
////                values.clear();
//
//                // 开始组装第一条数据
//                values.put("name", "The Da Vinci Code");
//                values.put("author", "Dan Brown");
//                values.put("pages", 454);
//                values.put("price", 16.96);
//                values.put("category_id",1);
//                db.insert("Book", null, values); // 插入第一条数据
//                values.clear();
//                // 开始组装第二条数据
//                values.put("name", "The Lost Symbol");
//                values.put("author", "Dan Brown");
//                values.put("pages", 510);
//                values.put("price", 19.95);
//                values.put("category_id",2);
//                db.insert("Book", null, values); // 插入第二条数据
//
//
//            }
//        });

//        Button deleteButton = (Button) findViewById(R.id.delete_data);
//        deleteButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                SQLiteDatabase db = dbHelper.getWritableDatabase();
//                db.delete("Book", "price = ?", new String[] { "56.74" });
//            }
//        });
        Button queryButton = (Button) findViewById(R.id.query_data);
        queryButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ArrayList<HashMap<String, Object>> listItem = new ArrayList<HashMap<String,Object>>();
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                // 查询Book表中所有的数据
                Cursor cursor = db.query("Book", null, null, null, null, null, null);
                if (cursor.moveToFirst()) {
                    do {
                        // 遍历Cursor对象，取出数据并打印
                        @SuppressLint("Range") String name = cursor.getString(cursor.getColumnIndex("name"));
                        @SuppressLint("Range") String author = cursor.getString(cursor.getColumnIndex("author"));
                        @SuppressLint("Range") int pages = cursor.getInt(cursor.getColumnIndex("pages"));
                        @SuppressLint("Range") double price = cursor.getDouble(cursor.getColumnIndex("price"));
                        @SuppressLint("Range") int category_id=cursor.getInt(cursor.getColumnIndex("category_id"));
                        HashMap<String, Object> map = new HashMap<String, Object>();
                        map.put("name",name);
                        map.put("price",price);
                        map.put("category_id",category_id);
                        listItem.add(map);
                        SimpleAdapter simpleAdapter=new SimpleAdapter(MainActivity.this,listItem,R.layout.books_item,
                                new String[]{"name","price","category_id"},new int[]{R.id.book_name,R.id.book_price,R.id.book_category});
                        listView.setAdapter(simpleAdapter);
//                        Log.d("MainActivity", "book name is " + name);
//                        Log.d("MainActivity", "book author is " + author);
//                        Log.d("MainActivity", "book pages is " + pages);
//                        Log.d("MainActivity", "book price is " + price);
//                        Log.d("MainActivity", "book category_id is " + category_id);
                    } while (cursor.moveToNext());
                }
                cursor.close();
            }
        });
        Button addData2=(Button) findViewById(R.id.add_data2);
        addData2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,AddBookActivity.class);
                startActivity(intent);

            }
        });
    }
}