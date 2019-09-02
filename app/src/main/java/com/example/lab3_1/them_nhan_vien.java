package com.example.lab3_1;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import java.io.Serializable;


public class them_nhan_vien extends AppCompatActivity {

    EditText edId, edTen, edChucvu, edPhongban, edGioithieu;
    Button btnOk , btnCancel;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.them_nhan_vien);

        edId = findViewById(R.id.edId);
        edTen = findViewById(R.id.edTen);
        edChucvu = findViewById(R.id.edChucvu);
        edPhongban = findViewById(R.id.edPhongban);
        edGioithieu = findViewById(R.id.edGioithieu);

        btnOk = findViewById(R.id.btnOK);
        btnCancel = findViewById(R.id.btnCancel);

        btnOk.setOnClickListener(ok);
        btnCancel.setOnClickListener(cancel);
    }

    public void sendToMain(int resultcode ){
        Intent intent = new Intent();

        employee obj = new employee();
        obj.setId(edId.getText().toString());
        obj.setName(edTen.getText().toString());
        obj.setChucVu(edChucvu.getText().toString());
        obj.setPhongBan(edPhongban.getText().toString());
        obj.setMoTa(edPhongban.getText().toString());

        intent.putExtra("newEmployee", (Serializable) obj);
        setResult(resultcode, intent);

        finish();
    }

    private void btnOK(){

        sendToMain(MainActivity.RESULT_CODE_SAVE);
        finish();

    }

    private void  btnCancel(){
        sendToMain(MainActivity.RESULT_CODE_CANCEL);
        finish();
    }

    private View.OnClickListener ok =new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnOK:
                btnOK();
                break;
            }
        }
    };
    private View.OnClickListener cancel = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.btnCancel:
                    btnCancel();
                    break;
            }
        }
    };


}
