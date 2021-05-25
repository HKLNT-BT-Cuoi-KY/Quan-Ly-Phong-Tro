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
import javaswing.Model.KhachThue;

public class khachThueDao {
     static Connection con = ConnectDB.getConnectDB();
    public boolean insert(KhachThue nhan) throws Exception
    {
        String sql = "insert into KhachThue(maKH,HoTen,NgaySinh,NgheNghiep,GioiTinh,Sdt,QueQuan,maPH) values(?,?,?,?,?,?,?,?)"; 
        try(
            PreparedStatement    pstmt = con.prepareStatement(sql); 
                ){
            pstmt.setString(1, nhan.getMaKT());
            pstmt.setString(2, nhan.getHoTen());
            pstmt.setString(3, nhan.getNgaySinh());
            pstmt.setString(4, nhan.getNgheNghiep());
            pstmt.setString(5, nhan.getGioiTinh());
            pstmt.setString(6, nhan.getSdt());
            pstmt.setString(7, nhan.getQueQuan());
            pstmt.setString(8, nhan.getMaPH());
            return pstmt.executeUpdate()> 0;
        }               
             
}     
    public List<KhachThue> getAllUsersKT(){
        List<KhachThue> kts = new ArrayList<KhachThue>();
        String sql  = "select * from KhachThue";
        try { 
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                KhachThue kt  = new KhachThue();
                
                kt.setMaKT(rs.getString("MaKT"));
                kt.setHoTen(rs.getString("TenNV"));
                kt.setNgaySinh(rs.getString("NgaySinh"));
                kt.setNgheNghiep(rs.getString("NgheNghiep"));
                kt.setGioiTinh(rs.getString("GioiTinh"));
                kt.setSdt(rs.getString("Sdt"));
                kt.setQueQuan(rs.getString("QueQuan"));
                kt.setMaPH(rs.getString("maPH"));
                kts.add(kt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(khachThueDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return kts;
    }
}
