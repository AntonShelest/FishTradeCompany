package com.bionic.edu;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

public class DBCreator {
	private static StringBuilder sb = new StringBuilder();
	
	public static void readDBScript() throws IOException{
		try(BufferedReader bf = new BufferedReader(
				new FileReader("DBCreateScript.txt"))){
			String s;
			while((s = bf.readLine()) != null){
				sb.append(s);
			}
		}
	}
	
	public static void executeDBScript(){
		Connection conn = null;
		
		try{
			readDBScript();
			
			Properties props = new Properties();
			InputStreamReader in = new InputStreamReader(
					new FileInputStream("DBProperties") , "UTF-8");
			props.load(in);
			in.close();
			String connectionString = props.getProperty("DBConnectionString");
			conn = DriverManager.getConnection(connectionString);
			Statement stmt = conn.createStatement();
			List<String> sqlStatements = Arrays.asList(sb.toString().split(";"));
			for (String sql: sqlStatements)		
				stmt.executeLargeUpdate(sql);
		}
		catch (SQLException e){
			System.out.println("DB error: " + e.getMessage());
		}
		catch (IOException ioe){
			System.out.println("Can not read DBCreateScript: " + ioe.getMessage());
		}
		finally {
			if (conn != null) 
				try{
					conn.close();
				}
				catch (SQLException e){
					System.out.println("Can not close DB connection");
				}
		}
	}
}
