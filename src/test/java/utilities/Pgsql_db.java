package utilities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import tech.tablesaw.api.Table;
import tech.tablesaw.io.jdbc.SqlResultSetReader;

public class Pgsql_db {
	
	public static ResultSet query_pgsql_db(String hostname, String port,
			String db_name, String username, String password, String sql_query)throws Throwable{
		ResultSet result_data = null;
		try {
			Class.forName("org.postgresql.Driver");
            
            // Establishing Connection
			String db_url = "jdbc:postgresql://" + hostname + ":" + port + "/"  + db_name;
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
	
	public static Table query_pgsql_db_tbl(String hostname, String port,
			String db_name, String username, String password, String sql_query)throws Throwable{
		Table result_data = null;
		try {
			result_data = SqlResultSetReader.read(query_pgsql_db(hostname, port, db_name, username, password, sql_query));
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		return result_data;
	}
	
	public static void main(String[] args) throws Throwable {
		Table tbl_data = query_pgsql_db_tbl("localhost", "5432", "dvdrental", "postgres", "password", "select * from actor");
		System.out.println("tbl_data = \n" + tbl_data);
	}

}
