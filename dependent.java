package klab1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author dell
 */
public class dependent {
    public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:1521:xe", "system", "system");
                 BufferedReader br6 = new BufferedReader(new FileReader("C:\\Users\\dell\\Downloads\\datafiles\\DEPENDENT.txt"));
                 
                 
                 String text6,Essn_temp,Dependent_name,sex,Bdate,Relationship;
                 int Essn;
                 while ((text6 = br6.readLine()) != null) {
                     if(br6.readLine() == " "  ){
                     text6 = br6.readLine();
                     }
                     System.out.println(text6);
                            String spltline6[] = text6.split(",");
                            Essn_temp = spltline6[0].replaceAll("'","");
                            System.out.println(Essn_temp+" :Essn_temp");
                            Essn = Integer.parseInt(Essn_temp.replaceAll(" ",""));
                            System.out.println(Essn+" :Essn");
                            Dependent_name = spltline6[1].replaceAll("'","");
                            Dependent_name = Dependent_name.replaceAll(" ","");
                            sex = spltline6[2].replaceAll("'","");
                            sex = sex.replaceAll(" ","");
                            Bdate = spltline6[3].replaceAll("'","");
                            Bdate = Bdate.replaceAll(" ","");
                            Relationship = spltline6[4].replaceAll("'","");
                            Relationship = Relationship.replaceAll(" ","");
                         System.out.println(Essn+Dependent_name+sex+Bdate+Relationship);
                            String query6 = "INSERT INTO DEPENDENT values(?,?,?,?,?)";
                            PreparedStatement ps6 = conn.prepareStatement(query6);
                                    ps6.setInt(1,Essn);
                                    ps6.setString(2,Dependent_name);
                                    ps6.setString(3,sex);
                                    ps6.setString(4,Bdate);
                                    ps6.setString(5,Relationship);
                                    
                        try {
                        ps6.execute();
                        }catch (SQLException e ) {
		            System.out.println("catch block ps5");
		            e.printStackTrace();
                            }
                 }
}
    
}