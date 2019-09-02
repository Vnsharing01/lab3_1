package com.example.lab3_1;

import java.io.Serializable;

public class employee implements Serializable {
    private String Id;
    private String Name;
    private String ChucVu;
    private String PhongBan;
    private String MoTa;

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getChucVu() {
        return ChucVu;
    }

    public void setChucVu(String chucVu) {
        ChucVu = chucVu;
    }

    public String getPhongBan() {
        return PhongBan;
    }

    public void setPhongBan(String phongBan) {
        PhongBan = phongBan;
    }

    public String getMoTa() {
        return MoTa;
    }

    public void setMoTa(String moTa) {
        MoTa = moTa;
    }

    public String toString(){

        return "ID: "+getId()+"\n Name: "+getName()+"\n Chức Vụ: "+getChucVu()
                +"\n Phòng Ban: "+getPhongBan()+"\n Mô Tả: "+getMoTa();
    }
}
