/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing.Model;
public class KhachThue {
    String maKT,HoTen,NgaySinh,NgheNghiep,GioiTinh,Sdt,QueQuan,maPH;

    public KhachThue() {
    }
    
    public KhachThue(String maKT, String HoTen, String NgaySinh, String NgheNghiep, String GioiTinh, String Sdt, String QueQuan, String maPH) {
        this.maKT = maKT;
        this.HoTen = HoTen;
        this.NgaySinh = NgaySinh;
        this.NgheNghiep = NgheNghiep;
        this.GioiTinh = GioiTinh;
        this.Sdt = Sdt;
        this.QueQuan = QueQuan;
        this.maPH = maPH;
    }

    public String getMaKT() {
        return maKT;
    }

    public String getHoTen() {
        return HoTen;
    }

    public String getNgaySinh() {
        return NgaySinh;
    }

    public String getNgheNghiep() {
        return NgheNghiep;
    }

    public String getGioiTinh() {
        return GioiTinh;
    }

    public String getSdt() {
        return Sdt;
    }

    public String getQueQuan() {
        return QueQuan;
    }

    public String getMaPH() {
        return maPH;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public void setHoTen(String HoTen) {
        this.HoTen = HoTen;
    }

    public void setNgaySinh(String NgaySinh) {
        this.NgaySinh = NgaySinh;
    }

    public void setNgheNghiep(String NgheNghiep) {
        this.NgheNghiep = NgheNghiep;
    }

    public void setGioiTinh(String GioiTinh) {
        this.GioiTinh = GioiTinh;
    }

    public void setSdt(String Sdt) {
        this.Sdt = Sdt;
    }

    public void setQueQuan(String QueQuan) {
        this.QueQuan = QueQuan;
    }

    public void setMaPH(String maPH) {
        this.maPH = maPH;
    }
    
}
