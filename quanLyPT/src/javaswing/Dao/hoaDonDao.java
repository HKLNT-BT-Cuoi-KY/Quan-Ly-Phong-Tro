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
import javaswing.view.QlyHoaDon;

/**
 *
 * @author TranKhanh
 */
public class hoaDonDao {

    static Connection con = ConnectDB.getConnectDB();
    public static int rs = 0;
    public static Long getTienDien(String maPhong) {
        return soChuDien(maPhong) * 3500;
    }

    public static Long getTienNuoc(String maPhong) {
        return soChuNuoc(maPhong) * 2500;
    }

    public static Long getTienPhong(String maPhong) {
        String sql = "select giaThue "
                + "from tblQlyPhongTro "
                + "where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhong);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                return rs.getLong("giaThue");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long soChuDien(String ID) {
        String sql = "select chiSoDienMoi -  chiSoDienCu  sochu "
                + "from tblQlyPhongTro "
                + "where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("sochu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static Long soChuNuoc(String ID) {
        String sql = "select chiSoNuocMoi -  chiSoNuocCu sochu "
                + "from tblQlyPhongTro "
                + "where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, ID);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getLong("sochu");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0l;
    }

    public static void AllHoaHon() {

        String sql = "select * from tblHoaDon";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println(getTienDien(rs.getString("maPhong")));
                System.out.println(getTienNuoc(rs.getString("maPhong")));
                System.out.println(getTienPhong(rs.getString("maPhong")));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<HoaDon> getAllHoaHon() {
        List<HoaDon> list_hoadon = new ArrayList<HoaDon>();
        String sql = "select * from tblHoaDon";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Long tienphong = getTienPhong(rs.getString("maPhong"));
                Long tiendien = getTienDien(rs.getString("maPhong"));
                Long tiennuoc = getTienNuoc(rs.getString("maPhong"));
                Long tiendv = 70000l;
                Long tongtien = tienphong + tiendien + tiennuoc + tiendv;
                HoaDon hoadon = new HoaDon();
                hoadon.setMaHD(rs.getString("maHD"));
                hoadon.setMaKT(rs.getString("maKT"));
                hoadon.setMaPhong(rs.getString("maPhong"));
                hoadon.setGiaThue(tienphong);
                hoadon.setTienDien(tiendien);
                hoadon.setTienNuoc(tiennuoc);
                hoadon.setTienDV(tiendv);
                hoadon.setTongTien(tongtien);
                list_hoadon.add(hoadon);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list_hoadon;
    }

    public static void Add_HoaDon(HoaDon hoadon) {
        
        String sql = "Insert Into tblHoaDon(maKT, maPhong, tienPhong, tienDien, tienNuoc, tienDV)"
                + "Values (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, hoadon.getMaKT());
            ps.setString(2, hoadon.getMaPhong());
            ps.setLong(3, hoadon.getGiaThue());
            ps.setLong(4, hoadon.getTienDien());
            ps.setLong(5, hoadon.getTienNuoc());
            ps.setLong(6, hoadon.getTienDV());
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void Del_HoaDon(String maHD) {
        String sql = "delete from tblHoaDon where maHD = '"
                + maHD
                + "'";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            rs = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //        getAllHoaHon();
//        Del_HoaDon("HD003");
    }
}
