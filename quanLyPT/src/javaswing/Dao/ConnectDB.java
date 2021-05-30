package javaswing.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import javaswing.view.Login;
import javax.swing.JOptionPane;

public class ConnectDB {

    static Connection con = null;
    public static String Name_PC;
    public static boolean Check_conn = true;

    public static Connection getConnectDB() {
        try {
//            Class.forName("net.sourceforge.jtds.jdbc.Driver");
//            String dbUrl = "jdbc:jtds:sqlserver://"
//                    + Name_PC
//                    + "/QlyNhaTro";
            String dbUrl = "jdbc:jtds:sqlserver://DESKTOP-3H4LPE0/QlyNhaTro";
            con = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
//            e.printStackTrace();
            JOptionPane.showConfirmDialog( new Login(), "Lỗi Tên Máy, Kiểm Tra ConnectDB");
            Check_conn = false;
        }
        return con;
    }

}
