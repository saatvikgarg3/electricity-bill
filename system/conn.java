
package electricity.billing.system;

import java.sql.*;

public class conn {
    Connection c;
    Statement s;
    
     conn() throws SQLException{
         //add jar file to libraries must
         //register the driver class
//         Class.forName("conn.mysql.cj.jdbc.Driver");    this syntax no needed in neweer versions
           
          c= DriverManager.getConnection("jdbc:mysql:///ebs","root","saatvik@123456");
          //jbh query hit krani hogi toh c connection k through s statement mein convert krdega
          s=c.createStatement();
          //executing my sql queries
          
     }
}
