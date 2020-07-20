package email2;

import activities.mainJframe;
import java.awt.TrayIcon;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Email20 {

    static int duration = 10;

    public static void main(String[] args) {
        //try{

        //
        Actions action = new Actions();

        System.out.println(System.getProperty("user.name"));

        DisplayinTray displayInTray = new DisplayinTray(new mainJframe());

        action.checkIfRunning();
        try {
            //    new Email20();
            // new HideToSystemTray();
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
        }
        new mainJframe().setVisible(true);
//        Emailing em = new Emailing();
//            em.emailsend();
//      

        while (true) {
            //DisplayinTray.trayIcon.displayMessage("Notification","The loop starts again.", TrayIcon.MessageType.INFO);

            System.out.println("Loop starts again");
            Emailing emailing = new Emailing();
            DisplayinTray.trayIcon.displayMessage("Notification", "Email Sending Started", TrayIcon.MessageType.INFO);
            if (action.filesChecking() == false) {
                break;
            }
                emailing.emailsend();
                System.out.println("Completed emails from first database");

            if (action.readDis() == false) {
                System.out.println("Condition Chal raha ky nahi");

                emailing.emailSend2();
                System.out.println("Completed emails from second database");
                try {
                    Thread.sleep(5 * 60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
                }

            } else {
                System.out.println("Skipping DB2");
                try {
                    Thread.sleep(5 * 60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

        }

    }

}
