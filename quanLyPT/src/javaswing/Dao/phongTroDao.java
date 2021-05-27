/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing.Dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javaswing.Model.PhongTro;

/**
 *
 * @author HP
 */
public class phongTroDao {

    static Connection con = ConnectDB.getConnectDB();
    static int rs = 0;

    public List<PhongTro> getInFoPhongTro() {
        List<PhongTro> phongTros = new ArrayList<PhongTro>();
        String sql = "select * from tblQlyPhongTro";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhongTro pt = new PhongTro();

                pt.setMaPhong(rs.getString("maPhong"));
                pt.setDienTich(rs.getDouble("dienTich"));
                pt.setSoNguoi(rs.getInt("soNguoi"));
                pt.setGiaThue(rs.getDouble("giaThue"));
                pt.setDoiTuong(rs.getString("DoiTuongThue"));
                pt.setTinhTrang(rs.getString("TinhTrang"));
                pt.setCsDienMoi(rs.getInt("chiSoDienMoi"));
                pt.setCsDienCu(rs.getInt("chiSoDienCu"));
                pt.setCsNuocCu(rs.getInt("chiSoNuocCu"));
                pt.setCsNuocMoi(rs.getInt("chiSoNuocMoi"));
                phongTros.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return phongTros;
    }

    public void addPhongTro(PhongTro pt) {
        String sql = "insert into tblQlyPhongTro( dienTich, soNguoi, giaThue, DoiTuongThue, TinhTrang,chiSoDienMoi,"
                + "chiSoDienCu,chiSoNuocCu,chiSoNuocMoi) values(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement ps = con.prepareStatement(sql);
            //ps.setString(1, pt.getMaPhong());
            ps.setDouble(1, pt.getDienTich());
            ps.setInt(2, pt.getSoNguoi());
            ps.setDouble(3, pt.getGiaThue());
            ps.setString(4, pt.getDoiTuong());
            ps.setString(5, pt.getTinhTrang());
            ps.setInt(6, 0);
            ps.setInt(7, 0);
            ps.setInt(8, 0);
            ps.setInt(9, 0);
            int rs = ps.executeUpdate();
            //pt.setKq(rs);
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public PhongTro getPhongTroByID(String maPhong) {
        String sql = "select * from tblQlyPhongTro where maPhong= ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhong);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhongTro pt = new PhongTro();

                pt.setMaPhong(rs.getString("maPhong"));
                pt.setDienTich(rs.getDouble("dienTich"));
                pt.setSoNguoi(rs.getInt("soNguoi"));
                pt.setGiaThue(rs.getDouble("giaThue"));
                pt.setDoiTuong(rs.getString("DoiTuongThue"));
                pt.setTinhTrang(rs.getString("TinhTrang"));
                pt.setCsDienMoi(rs.getInt("chiSoDienMoi"));
                pt.setCsDienCu(rs.getInt("chiSoDienCu"));
                pt.setCsNuocCu(rs.getInt("chiSoNuocCu"));
                pt.setCsNuocMoi(rs.getInt("chiSoNuocMoi"));

                return pt;
            }
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

    public void updatePhongTro(PhongTro pt) {
        String sql = "update tblQlyPhongTro set  dienTich = ?, soNguoi= ?, giaThue= ?, DoiTuongThue= ?, TinhTrang= ? where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(6, pt.getMaPhong());
            ps.setDouble(1, pt.getDienTich());
            ps.setInt(2, pt.getSoNguoi());
            ps.setDouble(3, pt.getGiaThue());
            ps.setString(4, pt.getDoiTuong());
            ps.setString(5, pt.getTinhTrang());
            int rs = ps.executeUpdate();
            //laptop.setKq(rs);
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void updateCSDN(PhongTro pt) {
        String sql = "update tblQlyPhongTro set  chiSoDienCu = ?, chiSoDienMoi= ?, chiSoNuocCu= ?, chiSoNuocMoi= ? where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(5, pt.getMaPhong());
            ps.setInt(1, pt.getCsDienCu());
            ps.setInt(2, pt.getCsDienMoi());
            ps.setInt(3, pt.getCsNuocCu());
            ps.setInt(4, pt.getCsNuocMoi());
            int rs = ps.executeUpdate();
            //laptop.setKq(rs);
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deletePhongTro(String maPhong) {
        String sql = "delete from tblQlyPhongTro where maPhong = ?";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setString(1, maPhong);
            int rs = ps.executeUpdate();
            System.out.println(rs);
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public List<PhongTro> getCSDienNuoc() {
        List<PhongTro> phongTros = new ArrayList<PhongTro>();
        String sql = "select * from tblQlyPhongTro";
        try {
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                PhongTro pt = new PhongTro();

                pt.setMaPhong(rs.getString("maPhong"));
                pt.setCsDienMoi(rs.getInt("chiSoDienMoi"));
                pt.setCsDienCu(rs.getInt("chiSoDienCu"));
                pt.setCsNuocCu(rs.getInt("chiSoNuocCu"));
                pt.setCsNuocMoi(rs.getInt("chiSoNuocMoi"));
                phongTros.add(pt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(phongTroDao.class.getName()).log(Level.SEVERE, null, ex);
        }

        return phongTros;
    }
}
