/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing.Model;

/**
 *
 * @author Tran Khanh
 */
public class HoaDon {

    String maHD, maKT, maPhong, tenKT;

    public String getTenKT() {
        return tenKT;
    }

    public void setTenKT(String tenKT) {
        this.tenKT = tenKT;
    }
    Long giaThue, tienDien, tongTien, tienNuoc, tienDV;
    String date;

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return  "Mã Hoá Đơn : " + maHD + "\t\tNgày Tạo Hoá Đơn : " + date + "\nMã Khách Thuê : " + maKT + "\nMã Phòng : " + maPhong + "\nGiá Thuê" + giaThue + "\nTiền Điện" + tienDien + "\nTiền Nước : " + tienNuoc + "\nTiền Dịch Vụ" + tienDV + "\nTổng Tiền" + tongTien  ;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public Long getGiaThue() {
        return giaThue;
    }

    public void setGiaThue(Long giaThue) {
        this.giaThue = giaThue;
    }

    public Long getTienDien() {
        return tienDien;
    }

    public void setTienDien(Long tienDien) {
        this.tienDien = tienDien;
    }

    public Long getTongTien() {
        return tongTien;
    }

    public void setTongTien(Long tongTien) {
        this.tongTien = tongTien;
    }

    public Long getTienNuoc() {
        return tienNuoc;
    }

    public void setTienNuoc(Long tienNuoc) {
        this.tienNuoc = tienNuoc;
    }

    public Long getTienDV() {
        return tienDV;
    }

    public void setTienDV(Long tienDV) {
        this.tienDV = tienDV;
    }

    public String getMaHD() {
        return maHD;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public String getMaKT() {
        return maKT;
    }

    public void setMaKT(String maKT) {
        this.maKT = maKT;
    }

    public String getMaPhong() {
        return maPhong;
    }

    public void setMaPhong(String maPhong) {
        this.maPhong = maPhong;
    }

}
