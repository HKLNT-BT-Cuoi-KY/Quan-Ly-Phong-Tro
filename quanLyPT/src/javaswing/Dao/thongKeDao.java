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
import static javaswing.Dao.phongTroDao.con;
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
}
