
package email2;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataRetrieval {
    
    public ResultSet emailData() {
      
        OracleConn oracleConn = new OracleConn();
        Connection connection = oracleConn.OracleConnection();

        if(connection == null) {
            System.err.println("Connection not made. In DataRetrievel.emailData");
        }

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select SRNO,ENAME,FLAG,CONAM,EMADD,CCADD,FNAME,SNADD,FDATE,TDATE,TYPER from EMAIL_INFO where FLAG = 'S'");

            return resultSet;
            
        } catch(SQLException ex) {
            System.out.println("Error in data retrievel");
            return null;
        }
            
    }
    public ResultSet emailData2() {
        
        OracleConn oracleConnection = new OracleConn();
        Connection connection = oracleConnection.OracleConnection2();

        if(connection == null) {
            System.err.println("Connection not made. In DataRetrievel.emailData");
        }

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select SRNO,ENAME,FLAG,CONAM,EMADD,CCADD,FNAME,SNADD,FDATE,TDATE,TYPER from EMAIL_INFO where FLAG = 'S'");

            return resultSet;
            
        } catch(SQLException ex) {
            System.out.println("Error in data retrievel");
            return null;
        }
        
    }
    
    
    
    
//---------------Email-Data--------------

    public ResultSet userData() {
        
        OracleConn oracleConnection = new OracleConn();
        Connection connection = oracleConnection.OracleConnection();

        if(connection == null) {
            System.err.println("Connection not made. In DataRetrievel.userData");
        }

        try {

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("Select UNAME,PASWD from ONLINE_USER where UCODE = 4");

            return resultSet;
            
        } catch(SQLException ex) {
            System.out.println("Error in data retrievel");
            return null;
        }
           
    }
    
//---------User Password Data-----------
    
}


