package taomao_ssm;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class TestMaotao {

	public static void main(String[] args) {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			
			Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tmall_ssm?useUnicode=true&characterEncoding=utf-8","root","admin");
			Statement createStatement = connection.createStatement();
			for(int i =0;i<10;i++){
				//格式化字符串String.format();类似于c语言中的printf
				String sqlformat = "insert into category value(null,'测试一下%d')";
				String sql = String.format(sqlformat,i);
				createStatement.execute(sql);
			}
			System.out.println("成功创建十条分类测试");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
