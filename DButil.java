package DButil;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class DButil {
	private List<Connection> list = null;
	/*1.数据库对象以私有的形式封装在里面*/
	private Connection conn = null;
	/*2.数据库执行载体*/
	private Statement sta = null;
	private ResultSet rs = null;
	/*4.私有的连接数据库的方法*/
	private Connection getConnection() {
		Connection myconn = null;
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");//类的描述器
			String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=JinDianXiaoShouGL";
			myconn = DriverManager.getConnection(url, "11", "11");
			System.out.println("数据库连接成功");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return myconn;
	}
	public DButil() {
		this.list = new ArrayList<Connection>();
		for (int i = 0; i < 2; i++) {
			/*加getConnection()返回值*/
			this.list.add(this.getConnection());
		}
		this.conn = list.get(0);
	}
	public int update(String sql) {
		int n = -1;
		try {
			this.sta = this.conn.createStatement();
			n = this.sta.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return n;
	}
	public ResultSet query(String sql) {
		try {
			this.sta = this.conn.createStatement();
			this.rs = this.sta.executeQuery(sql);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rs;
	}
	
	public void close() {
		try {//关闭连接要一层一层关rs.sta.conn
			if(rs != null) {
				rs.close();
				rs = null;
			}
			if(sta != null) {
				sta.close();
				sta= null;
			}
			if(conn != null) {
				conn.close();
				conn = null;
			}
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
