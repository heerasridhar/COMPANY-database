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
public class employee {
    public static void main(String[] args) throws IOException, SQLException{
		 Connection conn;
		
		 try {
	         Class.forName("oracle.jdbc.OracleDriver");
	      }
	      catch (ClassNotFoundException e) {
	         System.out.println("Could not load the driver.");
	      }
		 
		 conn = DriverManager.getConnection("jdbc:oracle:oci:@localhost:", "system", "system");
                 BufferedReader br1 = new BufferedReader(new FileReader("C:\\Users\\Downloads\\datafiles\\EMPLOYEE.txt"));

                  String text,temp;
		 String Fname, Minit, Lname, addr, sex, Bdate,ssn_temp,super_ssn_temp,salary_temp,dno_temp;
		 int salary, dno,ssn,super_ssn,super_ssn_temp2;
		 while ((text = br1.readLine()) != null) {
			String spltline1[] = text.split(",");
				Fname = spltline1[0].replaceAll("'","");
                                    Fname = Fname.replaceAll(" ","");
				Minit = spltline1[1].replaceAll("'","");
                                    Minit = Minit.replaceAll(" ","");
				Lname = spltline1[2].replaceAll("'","");
                                    Lname = Lname.replaceAll(" ","");
				ssn_temp = spltline1[3].replaceAll("'","");
                                    ssn_temp = ssn_temp.replaceAll(" ","");
                                    ssn = Integer.parseInt(ssn_temp);
				Bdate = spltline1[4].replaceAll("'","");
                                    Bdate = Bdate.replaceAll(" ","");
                                temp = spltline1[5]+","+spltline1[6]+","+spltline1[7];
                                    addr = temp.replaceAll("'","");
                                    addr = addr.replaceAll(" ","");
				sex = spltline1[8].replaceAll("'","");
                                    sex = sex.replaceAll(" ","");
                                salary_temp = spltline1[9].replaceAll(" ","");
                                    salary = Integer.parseInt(salary_temp);
				super_ssn_temp = spltline1[10].replaceAll("'","");
                                    super_ssn_temp = super_ssn_temp.replaceAll(" ","");
                                    //System.out.println("outside if super_ssn_temp:"+super_ssn_temp);
                                    if(!("null".equals(super_ssn_temp)) )
                                    {
                                    super_ssn_temp2 = Integer.parseInt(super_ssn_temp);
                                    //System.out.println("if super_ssn_temp2:"+super_ssn_temp2);

                                    super_ssn = super_ssn_temp2;
                                         //System.out.println("if super_ssn:"+super_ssn);
                                    }
                                    else
                                        {   super_ssn = 0; 
                                          //System.out.println("else:"+super_ssn);
                                        }
				dno_temp = spltline1[11].replaceAll("'","");
                                    dno = Integer.parseInt(dno_temp);
                        
                                System.out.println(Fname+Minit+Lname+ssn+Bdate+addr+sex+salary+"  "+super_ssn+" "+dno);
	                        String query1 = "INSERT INTO EMPLOYEE values(?,?,?,?,?,?,?,?,?,?)";
                                PreparedStatement ps1 = conn.prepareStatement(query1);
                                ps1.setString(1,Fname);
                                ps1.setString(2,Minit);
                                ps1.setString(3,Lname);
                                ps1.setInt(4,ssn);
                                ps1.setString(5,Bdate);
                                ps1.setString(6,addr);
                                ps1.setString(7,sex);
                                ps1.setInt(8,salary);
                                
                                if (super_ssn == 0) {
                                    ps1.setNull(9, Types.INTEGER);                           
                                     } 
                                else {
                                    ps1.setInt(9,super_ssn);
                                     }
                                ps1.setInt(10,dno);
                                
                 try {
                        ps1.execute();
                        }catch (SQLException e ) {
		            System.out.println("catch block ps1");
		            e.printStackTrace();
		        } 
                 }
        
}
    
}
