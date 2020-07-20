
package email2;

import java.awt.AWTException;
import java.awt.Image;
import java.awt.Menu;
import java.awt.MenuItem;
import java.awt.PopupMenu;
import java.awt.SystemTray;
import java.awt.TrayIcon;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class DisplayinTray extends JFrame {
    
    static TrayIcon trayIcon;
    
    public DisplayinTray(JFrame frame) {
        
        ShowTrayIcon(frame);
        
    }
    
    //static String filename = "E:\\ANDROID\\NetBeans\\Email2.0\\logo.png";
      final static String fileName = "src/activities/logo.png";
      
    public void ShowTrayIcon(JFrame frame) {
        if(!SystemTray.isSupported()) {
            System.out.println("Not supported");
            System.exit(0);
            return;
        } 
        
        //Image image = Toolkit.getDefaultToolkit().getImage(filename);
        
        Image sortByIcon = new ImageIcon(getClass().getResource("logo.png")).getImage();
       // Image image;
 //       frame.setIconImage(Toolkit.getDefaultToolkit().getImage("logo.png"));
//        image = new ImageIcon(this.getClass().getResource("logo.png")).getImage();
        
        trayIcon = new TrayIcon(sortByIcon);
        trayIcon.setImageAutoSize(true);
        
        final PopupMenu popup = new PopupMenu();
        final SystemTray systemTray = SystemTray.getSystemTray();
        
        Menu displayMenu = new Menu("Menu");

        MenuItem open = new MenuItem("Open");
        MenuItem hide = new MenuItem("Hide");
        MenuItem exit = new MenuItem("Exit");
        
        MenuItem error = new MenuItem("Error");
        MenuItem email = new MenuItem("Email");
        
        displayMenu.add(error);
        displayMenu.add(email);
        
        popup.add(open);
        popup.addSeparator();
        popup.add(hide);
        popup.addSeparator();
        popup.add(exit);
        
        
        trayIcon.setToolTip("Email 2.0");
        trayIcon.setPopupMenu(popup);
        
        exit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                systemTray.remove(trayIcon);
                System.exit(0);
            }
        });
        
        hide.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
               frame.dispose();
            }
        });
        
        open.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(!frame.isActive()) {
                    frame.setVisible(true);
                } else {
                    System.out.println("already opened");
                }
            }
        });
        
        try {
            systemTray.add(trayIcon);
            
        } catch(AWTException e) {
            
        }
        
        frame.setDefaultCloseOperation(HIDE_ON_CLOSE);
    }
    
    
    
    
    
}
