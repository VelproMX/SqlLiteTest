package com.example.database;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddBookActivity extends AppCompatActivity {
    private EditText editText;
    private EditText editText2;
    private EditText editText3;
    private EditText editText4;
    private EditText editText5;
    private MyDatabaseHelper dbHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_book);
        dbHelper = new MyDatabaseHelper(this, "Library.db", null, 2);
        Button button=(Button) findViewById(R.id.submit);
        this.editText=findViewById(R.id.editText);
        this.editText2=findViewById(R.id.editText2);
        this.editText3=findViewById(R.id.editText3);
        this.editText4=findViewById(R.id.editText4);
        this.editText5=findViewById(R.id.editText5);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SQLiteDatabase db = dbHelper.getWritableDatabase();

                ContentValues values = new ContentValues();
                String author=editText.getText().toString();
                String price=editText2.getText().toString();
                String pages=editText3.getText().toString();
                String name=editText4.getText().toString();
                String category_id=editText5.getText().toString();

                values.put("author",author);
                values.put("price",price);
                values.put("pages",pages);
                values.put("name",name);
                values.put("category_id",category_id);
                db.insert("Book",null,values);
                Intent intent=new Intent(AddBookActivity.this,Success.class);
                startActivity(intent);


            }
        });

    }
}