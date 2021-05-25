/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javaswing.Model.HoaDon;

/**
 *
 * @author TranKhanh
 */
public class hoaDonDao {

    static Connection con = ConnectDB.getConnectDB();

    public static String getTienDien(String maPhong) {
        Long tienDien = 0l;
        long sm, sc;
        String sql = "select * from tblQlyPhongTro where maPhong = 'PH001'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sm = Long.parseLong(rs.getString("chiSoDienMoi"));
                sc = Long.parseLong(rs.getString("chiSoDienCu"));
                tienDien = (sm - sc) * 3500;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + tienDien;
    }

    public static String getTienNuoc(String maPhong) {
        Long tienNuoc = 0l;
        long sm, sc;
        String sql = "select * from tblQlyPhongTro where maPhong = 'PH001'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sm = Long.parseLong(rs.getString("chiSoNuocMoi"));
                sc = Long.parseLong(rs.getString("chiSoNuocCu"));
                tienNuoc = (sm - sc) * 2500;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "" + tienNuoc;
    }

    public List<HoaDon> getAllHoaHon() {
        List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
        String sql = "select * from tblHoaDon";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                HoaDon hoadon = new HoaDon();
                hoadon.setMaHD(rs.getString("maHD"));
                hoadon.setMaKT(rs.getString("maKT"));
                hoadon.setMaPhong(rs.getString("maPhong"));
                hoadon.setTienDien(getTienDien("maPhong"));
                hoadon.setTienNuoc(getTienNuoc("maPhong"));
                list_hoadon.add(hoadon);
            }
        } catch (Exception e) {
        }
        return list_hoadon;
    }

    public static void Add_HoaDon(HoaDon hoadon) {
        String sql = "Insert Into tblHoaDon(maKT, maPhong, tienDien, tienNuoc, tienDV)"
                + "Values (?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hoadon.getMaKT());
            ps.setString(2, hoadon.getMaPhong());
            ps.setString(3, hoadon.getTienDien());
            ps.setString(4, hoadon.getTienNuoc());
            ps.setString(5, hoadon.getTienDV());
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }

    public void Del_HoaDon(String maHD) {
        String sql = "delete * from tblHoaDon where maHD = " + maHD + "";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.executeUpdate();
        } catch (Exception e) {
        }
    }
}
