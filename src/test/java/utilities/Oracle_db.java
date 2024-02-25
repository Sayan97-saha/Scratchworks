package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.HashMap;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.jdbc.SqlResultSetReader;

public class Oracle_db {

	public static ArrayList<HashMap<String, String>> get_details_from_db(String hostname, String port,
			String service_name, String username, String password, String sql_query) throws Throwable {
		ArrayList<HashMap<String, String>> result_data = new ArrayList<HashMap<String, String>>();
		
		ResultSet rs = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Establishing Connection
			String db_url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + service_name;
            System.out.println("DB URL = " + db_url);
			Connection con = DriverManager.getConnection(db_url, username, password);
 
            if (con != null)            
                System.out.println("Connected");           
            else           
                System.out.println("Not Connected");
			PreparedStatement ps = con.prepareStatement(sql_query);
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
	
	public static ResultSet query_oracle_db(String hostname, String port,
			String service_name, String username, String password, String sql_query)throws Throwable{
		ResultSet result_data = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
            
            // Establishing Connection
			String db_url = "jdbc:oracle:thin:@" + hostname + ":" + port + ":" + service_name;
            System.out.println("DB URL = " + db_url);
			Connection con = DriverManager.getConnection(db_url, username, password);
 
            if (con != null)            
                System.out.println("Connected");           
            else           
                System.out.println("Not Connected");
			PreparedStatement ps = con.prepareStatement(sql_query);
			result_data = ps.executeQuery();
			System.out.println("Query Execution Successful!");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result_data;
	}
	
	
	public static Table query_oracle_db_tbl(String hostname, String port,
			String service_name, String username, String password, String sql_query)throws Throwable{
		Table result_data = null;
		try {
			result_data = SqlResultSetReader.read(query_oracle_db(hostname, port, service_name, username, password, sql_query));
		}
		catch(Exception e) {
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
		//get_details_from_db("localhost", "1522", "XE", "SYSTEM", "pdbadmin", "select * from employees");
		
		//Table table_data = SqlResultSetReader.read(query_oracle_db("localhost", "1522", "XE", "SYSTEM", "pdbadmin", "select * from employees"));
		Table table_data = query_oracle_db_tbl("localhost", "1522", "XE", "SYSTEM", "pdbadmin", "select * from employees");
		table_data.print();
		System.out.println("table_data = \n" + table_data.print());
	}

}
