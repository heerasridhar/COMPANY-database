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
 * @author 
 */
public class project {
    public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:", "system", "system");
                 BufferedReader br4 = new BufferedReader(new FileReader("C:\\Users\\Downloads\\datafiles\\PROJECT.txt"));

                  String text4,Pname,Plocation,Pnum_temp;
                    int Pnumber,Dnumb;
                        while ((text4 = br4.readLine()) != null) {
                            String spltline4[] = text4.split(",");
                                Pname = spltline4[0].replaceAll("'","");
                                Pname = Pname.replaceAll(" ","");
                                Pnum_temp = spltline4[1].replaceAll(" ","");
                                Pnumber = Integer.parseInt(Pnum_temp);
                                Plocation = spltline4[2].replaceAll("'","");
                                Plocation = Plocation.replaceAll(" ","");
                                Dnumb = Integer.parseInt(spltline4[3].replaceAll(" ",""));
                               System.out.println(Pname+Pnumber+Plocation+Dnumb);
                               String query4 = "INSERT INTO PROJECT values(?,?,?,?)";
                               PreparedStatement ps4 = conn.prepareStatement(query4);
                                ps4.setString(1,Pname);
                                ps4.setInt(2,Pnumber);
                                ps4.setString(3,Plocation);
                                ps4.setInt(4,Dnumb);
                       try {
                        ps4.execute();
                        //System.out.println("record inserted");
                        }catch (SQLException e ) {
		            System.out.println("catch block ps4");
		            e.printStackTrace();
                            }
                        }
    
    }
}
