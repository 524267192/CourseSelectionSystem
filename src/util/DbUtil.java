package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//数据库连接和关闭工具类： 避免了数据库连接和关闭代码的重复使用，方便修改
public class DbUtil {
	private String dbUserName="root"; // 用户名
	private String dbPassword="123456"; // 密码
	
//	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useUnicode=true&characterEncoding=utf8"; // 数据库连接地址
//	private String jdbcName="com.mysql.jdbc.Driver"; // 驱动名称
	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf8"; // 数据库连接地址

//	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; // 数据库连接地址
	private String jdbcName="com.mysql.cj.jdbc.Driver"; // 驱动名称
	
	/**
	 * 获取数据库连接
	 * @return
	 * @throws Exception
	 */
	public Connection getCon(){
		try {
			Class.forName(jdbcName);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Connection con = null;
		try {
			con = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	/**
	 * 关闭数据库连接
	 * @param con
	 * @throws Exception
	 */
	public void closeCon(Connection con)throws Exception{
		if(con!=null){
			con.close();
		}
	}
	
	public static void main(String[] args) {
		DbUtil dbUtil=new DbUtil();
		try {
			dbUtil.getCon();
			System.out.println("数据库连接成功！");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("数据库连接失败");
		}
	}
}
