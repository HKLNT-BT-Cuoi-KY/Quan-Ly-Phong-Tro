/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaswing.Dao;


import java.sql.Connection;
import javaswing.Model.ThongKe;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javaswing.Dao.hoaDonDao.con;
import static javaswing.Dao.hoaDonDao.getTienDien;
import static javaswing.Dao.hoaDonDao.getTienNuoc;
import static javaswing.Dao.hoaDonDao.getTienPhong;
import static javaswing.Dao.phongTroDao.con;
import javaswing.Model.HoaDon;
import javaswing.Model.PhongTro;




public class thongKeDao {
    static ConnectDB cn = new ConnectDB();
    static Connection con = cn.getConnectDB();
    public static List<ThongKe> Count_dh(){
        List<ThongKe> users = new ArrayList<ThongKe>();
        String sql = "select  count(maHD) as tong \n" +
                        "from tblHoaDon as HD, tblKhachThue as KT, tblQlyPhongTro as PT\n" +
                        "where KT.maKT = HD.maKT and PT.maPhong = HD.maPhong";
        try {
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ThongKe acc = new ThongKe();
                acc.setCount_dh(rs.getInt("tong"));
                users.add(acc);
            }
        } catch (SQLException ex) {}
        return users;
    }
    public static List<ThongKe> Count_kh(){
        List<ThongKe> users = new ArrayList<ThongKe>();
        String sql = "select count(KT.maKT) as Tong\n" +
                     "from  tblKhachThue as KT";
        try {
            
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ThongKe acc = new ThongKe();
                acc.setCount_kh(rs.getInt("tong"));
                users.add(acc);
            }
        } catch (SQLException ex) {}
        return users;
    }
    public static List<ThongKe> Count_Pt_Trong(){
        List<ThongKe> users = new ArrayList<ThongKe>();
        String sql = "select count(maPhong) as tong\n" +
                    "from tblQlyPhongTro\n" +
                    "where TinhTrang = 'Trong'";
        try {           
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ThongKe acc = new ThongKe();
                acc.setCount_pt_t(rs.getInt("tong"));
                users.add(acc);
            }
        } catch (SQLException ex) {}
        return users;
    }
     public List<PhongTro> getInFoPhongTroIf() {
        List<PhongTro> phongTros = new ArrayList<PhongTro>();
        String sql = "select *\n" +
                     "from tblQlyPhongTro\n" +
                     "where TinhTrang = 'Trong'";
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
      public List<PhongTro> getInFoPhongTroIf2() {
        List<PhongTro> phongTros = new ArrayList<PhongTro>();
        String sql = "select * from tblQlyPhongTro\n" +
                    "where TinhTrang = 'Da Thue' and\n" +
                    "maPhong NOT IN (select maPhong from tblHoaDon)";
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
     public static List<ThongKe> Count_Phong_No(){
        List<ThongKe> users = new ArrayList<ThongKe>();
        String sql = "select count(maPhong) as tong\n" +
                    "from tblQlyPhongTro\n" +
                    "where TinhTrang = 'Da Thue' and\n" +
                        "maPhong NOT IN (select maPhong from tblHoaDon)";
        try {           
            PreparedStatement pstm = con.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                ThongKe acc = new ThongKe();
                acc.setCount_kh_no(rs.getInt("tong"));
                users.add(acc);
            }
        } catch (SQLException ex) {}
        return users;
    }
     public int Count_DoanhThu() {
        int tong = 0;
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
//                hoadon.setDate(rs.getString("tgian"));
                list_hoadon.add(hoadon);
                tong += hoadon.getTongTien();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }         
        return tong;
    }
}
