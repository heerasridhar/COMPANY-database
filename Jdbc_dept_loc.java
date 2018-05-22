/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package klab1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author dell
 */
public class Jdbc_dept_loc {
    
    public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:1521:xe", "system", "system");
                 //conn = DriverManager.getConnection("jdbc:oracle:thin:vro8522/Apple123@omega:1521:cse1");
                 
                 BufferedReader br3 = new BufferedReader(new FileReader("C:\\Users\\dell\\Downloads\\datafiles\\DEPT_LOCATIONS.txt"));
                 /* code for loading data to the dept_locations table*/      
		    String text3,Dlocation;
                    int Dnum;
                    while ((text3 = br3.readLine()) != null) {
			String spltline3[] = text3.split(",");
                            Dnum = Integer.parseInt(spltline3[0].replaceAll(" ",""));
                            Dlocation = spltline3[1].replaceAll("'","");
                            Dlocation = Dlocation.replaceAll(" ","");
                         System.out.println(Dnum+Dlocation);
                         String query3 = "INSERT INTO DEPT_LOCATIONS values(?,?)";
                                PreparedStatement ps3 = conn.prepareStatement(query3);
                                ps3.setInt(1,Dnum);
                                ps3.setString(2,Dlocation);
                    try {
                        ps3.execute();
                        }catch (SQLException e ) {
		            System.out.println("catch block ps3");
		            e.printStackTrace();
		        } 
                }   
    
                    
    }
}
