package com.example.lab3_1;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ADD = 100;
    public static final int  RESULT_CODE_SAVE = 0;
    public static final int RESULT_CODE_CANCEL = 112;

    SQLiteDatabase database;
    RecyclerView recyclerView;
    EmployeeAdapter adapter;
    ArrayList<employee> mArrayList;
    private Button btnThemNhanVien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.studentsList);
        mArrayList = new ArrayList<employee>();

        adapter = new EmployeeAdapter(mArrayList, this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(linearLayoutManager);

        btnThemNhanVien = findViewById(R.id.btnThemNhanVien);
        btnThemNhanVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, them_nhan_vien.class);
                startActivityForResult(intent, REQUEST_CODE_ADD);
            }
        });
        //  Sử dụng SQLite
        MySQLiteOpenHelper mySQLiteOpenHelper = new MySQLiteOpenHelper(this);
        database = mySQLiteOpenHelper.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put(MySQLiteOpenHelper.TEN_PHONGBAN,"IT");

        long result = database.insert(MySQLiteOpenHelper.TABLE_PHONGBAN,null,contentValues);

        if (result != 0){
            Toast.makeText(this, "Thêm Thành Công",Toast.LENGTH_LONG).show();
        }else  {
            Toast.makeText(this, "Thêm không Thành Công",Toast.LENGTH_LONG).show();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE_ADD){
            switch (resultCode){
                case RESULT_CODE_SAVE:

                    employee obj = (employee) data.getSerializableExtra("newEmployee");
                    mArrayList.add(obj);
                    adapter.notifyDataSetChanged();
                    break;
                case RESULT_CODE_CANCEL:
                    Toast.makeText(this, "bỏ qua", Toast.LENGTH_LONG).show();
                    break;
            }
        }

    }
}
