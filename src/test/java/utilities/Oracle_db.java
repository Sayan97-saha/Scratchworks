package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

public class Oracle_db {

	public static ArrayList<HashMap<String, String>> get_details_from_db(String hostname, String port,
			String service_name, String username, String password, String sql_query) throws Throwable {
		ArrayList<HashMap<String, String>> result_data = new ArrayList<HashMap<String, String>>();
		
		ResultSet rs = null;
		try {
			String conn_url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + service_name;
			System.out.println("conn_url = " + conn_url);
			Connection conn = DriverManager.getConnection(conn_url, username, password);
			System.out.println("Connection Successful!");
			PreparedStatement ps = conn.prepareStatement(sql_query);
			rs = ps.executeQuery();
			System.out.println("Query Execution Successful!");
			result_data = resultset_to_hashmap(rs);
			System.out.println("result_data = \n" + result_data);
		} 
		catch (Exception e) {
			e.printStackTrace();
		}
		return result_data;
	}
	
	
	public static ArrayList<HashMap<String,String>> resultset_to_hashmap(ResultSet rs) throws Throwable{
		ArrayList<HashMap<String, String>> result_data = new ArrayList<HashMap<String, String>>();
		try {
			if (rs != null) {
				ResultSetMetaData rs_md = rs.getMetaData();
				int columns = rs_md.getColumnCount();
				while (rs.next()) {
					HashMap<String, String> row_data = new HashMap<String, String>();
					for (int i = 1; i <= columns; i++) {
						row_data.put(rs_md.getColumnName(i), String.valueOf(rs.getObject(i)));
					}
					result_data.add(row_data);
				}
			}
			else {
				System.out.println("ResulSet is empty!");
				throw new Exception("ResulSet is empty!");
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result_data;
	}
	

	public static void main(String[] args) throws Throwable {
		get_details_from_db("localhost", "1522", "XE", "SYSTEM", "pdbadmin", "select * from employees");
	}

}
