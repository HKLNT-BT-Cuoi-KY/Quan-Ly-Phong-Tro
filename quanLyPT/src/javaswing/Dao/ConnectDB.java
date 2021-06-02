package javaswing.Dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javaswing.view.Splash;
import javax.swing.JOptionPane;

public class ConnectDB {

    static Connection con_DB = null;
    static Connection con_Server = null;
    private static String nameDB = "QlyNhaTro";

    public static Connection getConnectServer() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String dbUrl = "jdbc:jtds:sqlserver://localhost:1433";
            con_Server = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con_Server;
    }

    public static Connection getConnectDB() {
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            String dbUrl = "jdbc:jtds:sqlserver://localhost:1433/"
                    + nameDB;
            con_DB = DriverManager.getConnection(dbUrl);
        } catch (Exception e) {
        }
        return con_DB;
    }

    public static void Init_DB() {
        if (getConnectDB() == null) {
            CreateDB();
            Init_Table();
        }
    }

    public static void Init_Table() {
        ConnectDB.createTable();
        ConnectDB.createFunc_AutoID();
        ConnectDB.AlterTable();
        ConnectDB.InsertValues();
    }

    private static void DropDB() {
        String sql = "drop database "
                + nameDB;
        try {
            Statement stmt = getConnectServer().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("drop database");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
    }

    private static void CreateDB() {
        String sql = "create database "
                + nameDB;
        try {
            Statement stmt = getConnectServer().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("create database");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

    }

    private static void createTable() {
        String create_tblQlyPhongTro = "create table tblQlyPhongTro( "
                + "maPhong char(5) primary key,"
                + "dienTich float,"
                + "soNguoi int,"
                + "giaThue money,"
                + "DoiTuongThue varchar(10),"
                + "TinhTrang varchar(20),"
                + "chiSoDienMoi int,"
                + "chiSoDienCu int,"
                + "chiSoNuocMoi int,"
                + "chiSoNuocCu int"
                + ")";
        String create_tblKhachThue = "create table tblKhachThue("
                + "maKT char(5) primary key,"
                + "HoTen varchar(50),"
                + "NgaySinh Date,"
                + "NgheNghiep varchar(50),"
                + "Gioitinh varchar(10),"
                + "Sdt varchar(10),"
                + "QueQuan varchar(50),"
                + "maPhong char(5) foreign key references tblQlyPhongTro(maPhong)"
                + ")";
        String create_tblHoaDon = "create table tblHoaDon("
                + "maHD char(5) primary key,"
                + "maKT char(5) foreign key references tblKhachThue(maKT)"
                + "on update cascade\n"
                + "on delete cascade,"
                + "maPhong char(5) foreign key references tblQlyPhongTro(maPhong)"
                + "on update cascade\n"
                + "on delete cascade,"
                + "tgian date"
                + ")";
        String create_tblAccAdmin = "create table acc_admin ("
                + "UserName varchar(30), Pass varchar(10)"
                + ")";
        String query = create_tblQlyPhongTro + "\n"
                + create_tblKhachThue + "\n"
                + create_tblHoaDon + "\n"
                + create_tblAccAdmin;
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(query);
            System.out.println("create table");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void createFunc_AutoID() {
        String sql = "CREATE FUNCTION AUTO_ID\n"
                + "(@x VARCHAR(3))\n"
                + "RETURNS CHAR(5)\n"
                + "AS\n"
                + "BEGIN\n"
                + "    DECLARE @ID VARCHAR(5)\n"
                + "    IF @x = 'PH'\n"
                + "         BEGIN\n"
                + "             SELECT @ID = MAX(RIGHT(maPhong, 3)) FROM tblQlyPhongTro\n"
                + "		IF (SELECT COUNT(maPhong) FROM tblQlyPhongTro) = 0\n"
                + "                 SET @ID = '0'\n"
                + "         END\n"
                + "     IF @x = 'KH'\n"
                + "         BEGIN\n"
                + "		SELECT @ID = MAX(RIGHT(maKT, 3)) FROM tblKhachThue\n"
                + "		IF (SELECT COUNT(maKT) FROM tblKhachThue) = 0\n"
                + "                 SET @ID = '0'\n"
                + "         END\n"
                + "	IF @x = 'HD'\n"
                + "         BEGIN\n"
                + "		SELECT @ID = MAX(RIGHT(maHD, 3)) FROM tblHoaDon\n"
                + "		IF (SELECT COUNT(maHD) FROM tblHoaDon) = 0\n"
                + "                 SET @ID = '0'\n"
                + "         END\n"
                + "set @ID = @x + format(@ID+1,'D3')\n"
                + "RETURN @ID\n"
                + "END";
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("create func");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void AlterTable() {
        String sql1 = "ALTER TABLE tblHoaDon \n"
                + "ADD CONSTRAINT dfHD default dbo.AUTO_ID('HD') for maHD with VALUES";
        String sql2 = "ALTER TABLE tblQlyPhongTro \n"
                + "ADD CONSTRAINT dfPH default dbo.AUTO_ID('PH') for maPhong with VALUES";
        String sql3 = "ALTER TABLE tblKhachThue \n"
                + "ADD CONSTRAINT dfKH default dbo.AUTO_ID('KH') for maKT with VALUES";
        String sql = sql1 + sql2 + sql3;
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql1);
            stmt.executeUpdate(sql2);
            stmt.executeUpdate(sql3);
            System.out.println("alter table");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Splash(), e.getMessage());
        }
    }

    private static void InsertValues() {
        InsertValues_tblQlyPhongTro();
        InsertValues_tblKhachThue();
        InsertValues_tblHoaDon();
        InsertValues_AccAdmin();
    }

    private static void InsertValues_tblQlyPhongTro() {
        String sql_insert = "INSERT INTO tblQlyPhongTro ";
        String values = "(default, 35,4,3000000,'Nam & Nu','Trong',5,0,0,0),\n"
                + "(default, 25,3,3000000,'Nam','Trong',0,0,0,0),\n"
                + "(default, 25,3,3000000,'Nu','Da Thue',5,0,0,0),\n"
                + "(default, 30,4,4000000,'Nu','Trong',5,0,0,0),\n"
                + "(default, 30,4,3000000,'Nu','Da Thue',5,0,0,0),\n"
                + "(default, 20,3,3000000,'Nam','Trong',0,0,0,0),\n"
                + "(default, 25,3,3000000,'Nam & Nu','Da Thue',5,0,0,0),\n"
                + "(default, 30,3,3000000,'Nam & Nu','Trong',0,0,0,0),\n"
                + "(default, 35,4,4000000,'Nam & Nu','Da Thue',5,0,0,0)";
        String sql = sql_insert + "\n VALUES " + values;
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("insert1");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Splash(), e.getMessage());
        }
    }

    private static void InsertValues_tblKhachThue() {
        String sql_Insert = "INSERT INTO tblKhachThue ";
        String values = "(default, 'Nguyen Van A','2001-08-01','Sinh Vien','Nam','0917654321','Da Nang','PH007'),\n"
                + "(default, 'Tran Van B','1999-05-02','Sinh Vien','Nam','0387654321','Da Nang','PH007'),\n"
                + "(default, 'Le Thi C','1999-02-01','Sinh Vien','Nu','0947654321','Da Nang','PH007'),\n"
                + "(default, 'Nguyen Van D','2000-03-20','Sinh Vien','Nam','0977654321','Da Nang','PH009'),\n"
                + "(default, 'Nguyen Hoang Ke L','2002-06-30','Sinh Vien','Nam','0987654321','Da Nang','PH009'),\n"
                + "(default, 'Phan Thi Thanh T','2001-06-20','Sinh Vien','Nu','0988654321','Da Nang','PH003'),\n"
                + "(default, 'Tuong Thi My Ng','1999-06-12','Cong Nhan','Nu','0997654321','Da Nang','PH005'),\n"
                + "(default, 'Le Thi C','2002-07-30','Sinh Vien','Nu','0987656321','Da Nang','PH005'),\n"
                + "(default, 'Le Van D','2002-08-10','Cong Nhan','Nam','0587654321','Da Nang','PH009'),\n"
                + "(default, 'Le Thi C','2001-12-12','Sinh Vien','Nu','0987654341','Da Nang','PH003'),\n"
                + "(default, 'Nguyen Van Hoa','2001-01-07','Sinh Vien','Nam','0987654321','Quang Nam','PH009')";
        String sql = sql_Insert + "\n VALUES " + values;
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("insert2");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Splash(), e.getMessage());
        }
    }

    private static void InsertValues_tblHoaDon() {
        String sql_Insert = "INSERT INTO tblHoaDon ";
        String values = "(default, 'KH011', 'PH009', getdate()),\n"
                + "(default, 'KH007', 'PH005', getdate()),\n"
                + "(default, 'KH002', 'PH007', '2021-04-01'),\n"
                + "(default, 'KH003', 'PH005', getdate())";
        String sql = sql_Insert + "\n VALUES " + values;
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("insert3");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Splash(), e.getMessage());
        }
    }

    private static void InsertValues_AccAdmin() {
        String sql = "insert into acc_admin\n"
                + "values ('admin', 'admin')";
        try {
            Statement stmt = getConnectDB().createStatement();
            stmt.executeUpdate(sql);
            System.out.println("insert4");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(new Splash(), e.getMessage());
        }
    }
}
