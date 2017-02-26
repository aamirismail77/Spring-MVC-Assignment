package com.bellinf.batch4.mvc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bellinf.batch4.mvc.model.Student;


public class StudentRepo {

	Connection con = null;
	public static final String CREATE_TABLE = "Create table student(name character varying(40) NOT NULL, course character varying(40) NOT NULL, fee integer NOT NULL, date character varying(40) NOT NULL)";
	public static final String INSERT_REGISTER = "INSERT INTO student (name, course, fee, date) values(?,?,?,?)";
	public static final String SELECT_USER_INFO = "select * from student where name= ?";
	public void getConnection(){
		try {
		System.out.println("Before Postgresql Driver Registered");
		//Load the drive class
		Class.forName("org.postgresql.Driver");
		System.out.println("Postgresql Driver Registered");
		//Establish the connection
		con = DriverManager.getConnection("jdbc:postgresql://127.0.0.1:5432/BellInfo", "postgres", "root");
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public boolean createRegistration(){
		boolean result = false;
		Statement stmt = null;
		Statement stmt2 = null;
		boolean isTableExsist=false;
		try{
			getConnection();
			stmt2 = con.createStatement();
			stmt = con.createStatement();
			ResultSet rs = stmt2.executeQuery("SELECT EXISTS ( SELECT 1  FROM   pg_tables WHERE  schemaname = 'public' AND    tablename = 'student')");
			while(rs.next()){
				isTableExsist = rs.getBoolean(1);
			}			
			if(!isTableExsist){
				
				result = stmt.execute(CREATE_TABLE);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public int saveRegistraionDetails(Student userInfo){
		int result =0;
		PreparedStatement ps = null;
		try{
			getConnection();
			ps = con.prepareStatement(INSERT_REGISTER);
			ps.setString(1,  userInfo.getName());
			ps.setString(2, userInfo.getCourse());
			ps.setLong(3, userInfo.getFee());
			ps.setString(4, userInfo.getDt());
			result =ps.executeUpdate();
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return result;
	}
	
	public String getUserDetails(String sname){
		boolean result=false;
		PreparedStatement ps =null;
		try{
			getConnection();
			ps =con.prepareStatement(SELECT_USER_INFO);
			ps.setString(1, sname);
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				return sname;
			}else{
				System.out.println("Your're not in the system, Please register first");
				return null;
			}
			
			
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			try {
				ps.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;

		
		
	}
	
	
}
