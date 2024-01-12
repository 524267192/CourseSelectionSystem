package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
//���ݿ����Ӻ͹رչ����ࣺ ���������ݿ����Ӻ͹رմ�����ظ�ʹ�ã������޸�
public class DbUtil {
	private String dbUserName="root"; // �û���
	private String dbPassword="123456"; // ����
	
//	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useUnicode=true&characterEncoding=utf8"; // ���ݿ����ӵ�ַ
//	private String jdbcName="com.mysql.jdbc.Driver"; // ��������
	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&characterEncoding=utf8"; // ���ݿ����ӵ�ַ

//	private String dbUrl="jdbc:mysql://localhost:3306/db_student?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC"; // ���ݿ����ӵ�ַ
	private String jdbcName="com.mysql.cj.jdbc.Driver"; // ��������
	
	/**
	 * ��ȡ���ݿ�����
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
	 * �ر����ݿ�����
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
			System.out.println("���ݿ����ӳɹ���");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("���ݿ�����ʧ��");
		}
	}
}
