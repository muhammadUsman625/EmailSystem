package email2;

import java.awt.TrayIcon;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class OracleConn {
    
//    public static String ipAddress = "jdbc:oracle:thin:@" + "192.168.100.110" + ":1521:orcl";
//    public static String user = "noor15";
//    public static String pass = "noor";
    
    public static String ipAddress = "";
    public static String user = "";
    public static String pass = "";
    
    Actions ac = new Actions();
    
    public Connection OracleConnection() {
        
        Connection con;
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found");
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        String to[] = ac.readFile();
        
        ipAddress = "jdbc:oracle:thin:@"+to[2]+":1521:orcl";
        //to[2]
        user = to[0];
        pass = to[1];
        
        try{
            con = DriverManager.getConnection(ipAddress, user, pass);
            System.out.println("Successfully Connected"); 
            //DisplayinTray.trayIcon.displayMessage("Info","Successfuly Connected to : " + ipAddress, TrayIcon.MessageType.INFO);
            
            return con;  
            
        } catch (SQLException ex) {
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Not Connected!!"); 
            DisplayinTray.trayIcon.displayMessage("CONNECTION ERROR","Not Connected To Database", TrayIcon.MessageType.ERROR);
            return null;
        }
        
        
    } 
    
    public Connection OracleConnection2(){
        
        Connection con;
        
        //DisplayinTray.trayIcon.displayMessage("Connecting2", "Connecting to second database", TrayIcon.MessageType.INFO);
        
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (ClassNotFoundException ex) {
            System.out.println("Driver Not Found");
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        String to[] = ac.readFile2();
        
        ipAddress = "jdbc:oracle:thin:@"+to[2]+":1521:orcl";
        //to[2]
        user = to[0];
        pass = to[1];
        
        try{
            con = DriverManager.getConnection(ipAddress, user, pass);
            System.out.println("Successfully Connected"); 
            //DisplayinTray.trayIcon.displayMessage("Info","Successfuly Connected to : " + ipAddress, TrayIcon.MessageType.INFO);
            
            return con;  
            
        } catch (SQLException ex) {
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Not Connected yaaar"); 
            DisplayinTray.trayIcon.displayMessage("CONNECTION ERROR","Not Connected To Database", TrayIcon.MessageType.ERROR);
            return null;
        }

    }
    
    
    
}


//----------- Oracle Connection Initiated -----------