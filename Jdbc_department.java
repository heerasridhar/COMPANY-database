package klab1;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Types;


/**
 *
 * @author 
 */
public class Jdbc_department {
    public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:", "system", "system");
                
                 
                 BufferedReader br2 = new BufferedReader(new FileReader("C:\\Users\\Downloads\\datafiles\\DEPARTMENT.txt"));
    
     /* code for loading data to the department table*/
                   String text2,Dname,Mgr_strt_date,Mgr_ssn_temp;
                   int Dnumber,Mgr_ssn,Mgr_ssn_temp2;
                
                    while ((text2 = br2.readLine()) != null) {
			String spltline2[] = text2.split(",");
                            	Dname = spltline2[0].replaceAll("'","");
                                    Dname = Dname.replaceAll(" ","");
                                Dnumber = Integer.parseInt(spltline2[1].replaceAll(" ",""));
                                
                                Mgr_ssn_temp = spltline2[2].replaceAll("'","");
                                    Mgr_ssn_temp = Mgr_ssn_temp.replaceAll(" ","");
                                    if(!("null".equals(Mgr_ssn_temp)) )
                                    {
                                    Mgr_ssn_temp2 = Integer.parseInt(Mgr_ssn_temp);
                                    Mgr_ssn = Mgr_ssn_temp2;
                                    }
                                    else
                                        {   Mgr_ssn = 0; 
                                        }
                                    
                                Mgr_strt_date = spltline2[3].replaceAll("'","");
                                    Mgr_strt_date = Mgr_strt_date.replaceAll(" ","");
                                
                                
                            System.out.println(Dname+Dnumber+Mgr_ssn+Mgr_strt_date);
                                 String query2 = "INSERT INTO DEPARTMENT values(?,?,?,?)";
                                PreparedStatement ps2 = conn.prepareStatement(query2);
                                ps2.setString(1,Dname);
                                ps2.setInt(2,Dnumber);
                                if (Mgr_ssn == 0) {
                                    ps2.setNull(3, Types.INTEGER);                           
                                     } 
                                else {
                                    ps2.setInt(3,Mgr_ssn);
                                     }
                                ps2.setString(4,Mgr_strt_date);
                        try {
                        ps2.execute();
                        }catch (SQLException e ) {
		            System.out.println("catch block ps2");
		            e.printStackTrace();
		        } 
                                
                    }
    
}
}
