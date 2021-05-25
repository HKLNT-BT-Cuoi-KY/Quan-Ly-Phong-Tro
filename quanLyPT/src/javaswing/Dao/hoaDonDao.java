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
import static javaswing.Dao.ConnectDB.con;
import javaswing.Model.HoaDon;

/**
 *
 * @author TranKhanh
 */
public class hoaDonDao {

    static Connection con = ConnectDB.getConnectDB();

    public static Long getTien(String maPhong, String loaiChiSoCu, String loaiChiSoMoi) {
        Long tien = 0l;
        long sm, sc;
        String sql = "select * from tblQlyPhongTro where maPhong = 'PH001'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                sm = Long.parseLong(rs.getString("chiSoDienMoi"));
                sc = Long.parseLong(rs.getString("chiSoDienCu"));
                tien = sm - sc;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tien;
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
                hoadon.setTienDien(sql);
            }
        } catch (Exception e) {
        }
        return list_hoadon;
    }

}
