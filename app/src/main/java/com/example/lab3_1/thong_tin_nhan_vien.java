package com.example.lab3_1;

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class thong_tin_nhan_vien extends AppCompatActivity {

        TextView tvHoten, tvChucVu, tvPhongBan, tvMoTa;
        SQLiteDatabase database;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chi_tiet_nhan_vien);

        employee obj = (employee) getIntent().getExtras().getSerializable("chi tiết nhân viên");
        tvHoten = findViewById(R.id.tvHoten);
        tvChucVu = findViewById(R.id.tvChucvu);
        tvPhongBan = findViewById(R.id.tvPhongban);
        tvMoTa = findViewById(R.id.tvMota);


        tvHoten.setText(obj.getName());
        tvChucVu.setText(obj.getChucVu());
        tvPhongBan.setText(obj.getPhongBan());
        tvMoTa.setText(obj.getMoTa());

    }
}
