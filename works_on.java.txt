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
public class works_on {
    
     public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:1521:xe", "system", "system");
                 BufferedReader br5 = new BufferedReader(new FileReader("C:\\Users\\dell\\Downloads\\datafiles\\WORKS_ON.txt"));

                 /* code for loading data to the works_on table*/     
                   String text5,Essn_temp;
                   int Essn,Pno;
                   float Hours;
                   while ((text5 = br5.readLine()) != null) {
                            String spltline5[] = text5.split(",");
                            Essn_temp = spltline5[0].replaceAll("'","");
                            Essn = Integer.parseInt(Essn_temp.replaceAll(" ",""));
                            //System.out.println(Essn+" :Essn");
                            Pno = Integer.parseInt(spltline5[1].replaceAll(" ",""));
                            //System.out.println(Pno+" :Pno");
                            Hours = Float.parseFloat(spltline5[2].replaceAll(" ",""));
                            //System.out.println(Hours+" :Hours");
                            System.out.println(Essn+Pno+Hours);
                            String query5 = "INSERT INTO WORKS_ON values(?,?,?)";
                            PreparedStatement ps5 = conn.prepareStatement(query5);
                                    ps5.setInt(1,Essn);
                                    ps5.setInt(2,Pno);
                                    ps5.setFloat(3,Hours);
                        try {
                        ps5.execute();
                        }catch (SQLException e ) {
		            System.out.println("catch block ps5");
		            e.printStackTrace();
                            }        
                       }
    }
}