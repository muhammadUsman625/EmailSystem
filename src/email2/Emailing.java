package email2;

import java.awt.TrayIcon;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Emailing {

    final String USER_NAME = "social.burhanisolutions"; //@gmail.com
    final String PASSWORD = "social123!@#";

    public static String disclaimer = "";
    boolean fileCheck;

    public void emailsend() {

        Actions action = new Actions();
      
        //disclaimer = action.readDescription();

        DataRetrieval dataRetrivel = new DataRetrieval();
        ResultSet resultSet = dataRetrivel.emailData();

        if (resultSet == null) {
            System.err.println("The result set is null. Emailing.emailsend");
        }
        
        String to[] = action.readPort();

        Properties props = new Properties();
        if (to[1].equals("465")) {
            props.put("mail.smtp.host", to[0]); //SMTP Host
            props.put("mail.smtp.socketFactory.port", to[1]); //SSL Port
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", to[2]); //Enabling SMTP Authentication
            props.put("mail.smtp.port", to[1]); //SMTP Port
        } else {
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", to[2]);
            props.put("mail.smtp.starttls.enable", to[3]);
            props.put("mail.smtp.host", to[0]);
            props.put("mail.smtp.port", to[1]);
        }

        try {
            while (resultSet.next()) {
                String serialNo = resultSet.getString("SRNO");
                String emailSenderName = resultSet.getString("ENAME");
                String FLAG = resultSet.getString("FLAG");
                String companyName = resultSet.getString("CONAM");
                String receiverEmail = resultSet.getString("EMADD");
                String cCEmail = resultSet.getString("CCADD");
                String fileName = resultSet.getString("FNAME");
                String senderEmail = resultSet.getString("SNADD");
                String fromDate = resultSet.getString("FDATE");
                String senderDate = resultSet.getString("TDATE");
                String typer = resultSet.getString("TYPER");
                // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  

                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = null;
                try {
                    date = simpleDateFormat.parse(fromDate);
                    SimpleDateFormat simplerDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                    fromDate = simplerDateFormat1.format(date);

                    date = simpleDateFormat.parse(senderDate);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
                    senderDate = simpleDateFormat2.format(date);
                } catch (ParseException ex) {
                    Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                }

                String[] toList = null;
                if (cCEmail != null) {
                    toList = cCEmail.split(",\\s*");
                }
                if (Objects.equals(FLAG, "S")) {

                    System.out.println("got here in if");
                    boolean ch = false;
                    while (ch = false) {
                        InternetAddress emailAddr;
                        try {
                            emailAddr = new InternetAddress(receiverEmail);
                            emailAddr.validate();

                            for (int i = 0; i < toList.length; i++) {
                                emailAddr = new InternetAddress(toList[i]);
                                emailAddr.validate();
                            }
                            ch = true;
                        } catch (AddressException ex) {
                            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Invalid Email");
                        }
                    }

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(to[4], to[5]);
                        }
                    });

                    String body = "Attention " + emailSenderName + "\n" + "\n" + "Please Find Attached File of "
                            + typer + "\n" + "\n" + "FROM" + " " + fromDate + " TO" + " " + senderDate + "\n" + "\n \n \n" + disclaimer;
                    // Task Two
                    if (sendEmail(senderEmail, receiverEmail, toList, typer, body, fileName, session) == false) {
                        if (fileCheck == true) {
                            continue;
                        }

                    }
                }
                 else {
                    System.out.println("in else");
                    System.out.println("No emails to be sent");
                }
                   
                OracleConn oracleConn = new OracleConn();
                Connection connection = oracleConn.OracleConnection();
                Statement statement = connection.createStatement();
                int duration = action.getDuration();
                if (fileCheck == true) {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'T' WHERE SRNO = " + serialNo);
                    System.out.println("Updated for  " + emailSenderName);
                    try {
                        System.out.println("Waiting for " + duration + " second(s)");
                        TimeUnit.SECONDS.sleep(duration);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'P' WHERE SRNO = " + serialNo);
                    System.out.println("Pending for  " + emailSenderName);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //--------------------------------------------------------------------------------------------------- 

    boolean sendEmail(String senderEmail, String receiverEmail, String[] toList, String typer, String body, String fileName, Session session) {
        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);
        try {

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(senderEmail));

            // Set Subject: header field
            message.setSubject("eReport: " + typer);

            // Create the message part
            BodyPart messageBodyPart = new MimeBodyPart();

            // Now set the actual message
            messageBodyPart.setText(body);

            // Create a multipar message
            Multipart multipart = new MimeMultipart();

            // Set text message part
            multipart.addBodyPart(messageBodyPart);

            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            fileCheck = false;
            if (fileName.contains(",")) {
                System.out.println("203 error");

                String sp = ",";
                String temp[] = fileName.split(sp);
                for (int i = 0; i < temp.length; i++) {
                    attachFile(temp[i], multipart);
                }
            } else {
                attachFile(fileName, multipart);
            }

            // Send the complete message parts
            message.setContent(multipart);

            message.saveChanges();

            //message.setText(multipart);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(receiverEmail));

            if (toList != null) {
                for (int i = 0; i < toList.length; i++) {
                    try {
                        message.addRecipients(Message.RecipientType.CC,
                                InternetAddress.parse(toList[i]));
                    } catch (MessagingException ex) {
                        Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (fileCheck == true) {

                //         Transport transport = session.getTransport("smtp");
//         transport.connect("smtp.gmail.com", username, password);
                Transport.send(message);

                DisplayinTray.trayIcon.displayMessage("Notification", "Email Sent to: " + receiverEmail, TrayIcon.MessageType.INFO);
                System.out.println("send email wala dhaiko");
                return true;
                //for checking purpose unrechable
                //System.out.println(FNAME);
            } else {
                fileCheck = false;
                return false;
            }
        } catch (MessagingException ex) {

            ex.printStackTrace();
        }
        return false;

    }

    //---------------------------------------------------------------------------------------------------   
    boolean attachFile(String file_Name, Multipart multipart) {
        File file = new File(file_Name);
        if (file.exists()) {

            BodyPart attachment = new MimeBodyPart();

            DataSource source = new FileDataSource(file_Name);
            try {
                attachment.setDataHandler(new DataHandler(source));
            } catch (MessagingException ex) {
                Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
            }

            //String[] filename = FNAME.split(":");
            String filename = file_Name.substring(3);
            try {
                attachment.setFileName(filename + ".pdf");
            } catch (MessagingException ex) {
                Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println(filename);

            fileCheck = true;

            try {
                multipart.addBodyPart(attachment);
            } catch (MessagingException ex) {
                Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
            }

            return true;
        } else {
            DisplayinTray.trayIcon.displayMessage("IOException", "File Not Found", TrayIcon.MessageType.ERROR);
            fileCheck = false;
            return false;
        }
    }

    //---------------------------------SECOND DATABASE-----------------------------------------------------------
    //*
    public void emailSend2() {
        Actions action = new Actions();

        disclaimer = action.readDesc2();

        System.out.println("Retrieving data from second database");

        DataRetrieval dataRetrivel = new DataRetrieval();
        ResultSet resultSet = dataRetrivel.emailData2();
        if (resultSet == null) {
            System.err.println("The result set is null. Emailing.emailsend");
        }
        String to[] = action.readPort2();
        Properties props = new Properties();
        if (to[1].equals("465")) {
            props.put("mail.smtp.host", to[0]); //SMTP Host
            props.put("mail.smtp.socketFactory.port", to[1]); //SSL Port
            props.put("mail.smtp.socketFactory.class",
                    "javax.net.ssl.SSLSocketFactory"); //SSL Factory Class
            props.put("mail.smtp.auth", to[2]); //Enabling SMTP Authentication
            props.put("mail.smtp.port", to[1]); //SMTP Port
        } else {
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.auth", to[2]);
            props.put("mail.smtp.starttls.enable", to[3]);
            props.put("mail.smtp.host", to[0]);
            props.put("mail.smtp.port", to[1]);
        }
        try {
            while (resultSet.next()) {
                String serialNo = resultSet.getString("SRNO");
                String emailSenderName = resultSet.getString("ENAME");
                String FLAG = resultSet.getString("FLAG");
                String companyName = resultSet.getString("CONAM");
                String receiverEmail = resultSet.getString("EMADD");
                String cCEmail = resultSet.getString("CCADD");
                String fileName = resultSet.getString("FNAME");
                String senderEmail = resultSet.getString("SNADD");
                String fromDate = resultSet.getString("FDATE");
                String senderDate = resultSet.getString("TDATE");
                String typer = resultSet.getString("TYPER");
                // *** note that it's "yyyy-MM-dd hh:mm:ss" not "yyyy-mm-dd hh:mm:ss"  
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date date = null;
                try {
                    date = simpleDateFormat.parse(fromDate);
                    SimpleDateFormat simplerDateFormat1 = new SimpleDateFormat("dd-MM-yyyy");
                    fromDate = simplerDateFormat1.format(date);

                    date = simpleDateFormat.parse(senderDate);
                    SimpleDateFormat simpleDateFormat2 = new SimpleDateFormat("dd-MM-yyyy");
                    senderDate = simpleDateFormat2.format(date);
                } catch (ParseException ex) {
                    Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                }

                String[] toList = null;
                if (cCEmail != null) {
                    toList = cCEmail.split(",\\s*");
                }
                if (Objects.equals(FLAG, "S")) {

                    System.out.println("got here in if");
                    boolean ch = false;
                    while (ch = false) {
                        InternetAddress emailAddr;
                        try {
                            emailAddr = new InternetAddress(receiverEmail);
                            emailAddr.validate();

                            for (int i = 0; i < toList.length; i++) {
                                emailAddr = new InternetAddress(toList[i]);
                                emailAddr.validate();
                            }
                            ch = true;
                        } catch (AddressException ex) {
                            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Invalid Email");
                        }
                    }

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(to[4], to[5]);
                        }
                    });

                    String body = "Attention " + emailSenderName + "\n" + "\n" + "Please Find Attached File of "
                            + typer + "\n" + "\n" + "FROM" + " " + fromDate + " TO" + " " + senderDate + "\n" + "\n \n \n" + disclaimer;
                    
                    if (sendEmail(senderEmail, receiverEmail, toList, typer, body, fileName, session) == false) {
                        System.out.println("388 error");
                        if (fileCheck == true) {
                            continue;
                        }

                    }

                } else {
                    System.out.println("in else");
                    System.out.println("No emails to be sent");
                }
                OracleConn oracleConn = new OracleConn();
                Connection connection = oracleConn.OracleConnection2();
                Statement statement = connection.createStatement();
                int duration = action.getDuration();
                if (fileCheck == true) {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'T' WHERE SRNO = " + serialNo);
                    System.out.println("Updated for  " + emailSenderName);
                    try {
                        System.out.println("Waiting for " + duration + " second(s)");
                        TimeUnit.SECONDS.sleep(duration);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'P' WHERE SRNO = " + serialNo);
                    System.out.println("Pending for  " + emailSenderName);
                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//*/

    /*
    public int emailSend2() {

        Actions ac = new Actions();

        disclaimer = ac.readDesc2();

        System.out.println("Retrieving data from second database");

        DataRetrieval data = new DataRetrieval();
        ResultSet set = data.emailData2();

        if (set == null) {
            System.err.println("The result set is null. Emailing.emailsend");
        }

        String to[] = ac.readPort2();

        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", to[2]);
        props.put("mail.smtp.starttls.enable", to[3]);
        props.put("mail.smtp.host", to[0]);
        props.put("mail.smtp.port", to[1]);

        try {

            while (set.next()) {

                String SRNO = set.getString("SRNO");
                String ENAME = set.getString("ENAME");
                String FLAG = set.getString("FLAG");
                String CONAM = set.getString("CONAM");
                String TOEMAIL = set.getString("EMADD");
                String CCEMAIL = set.getString("CCADD");
                String FNAME = set.getString("FNAME");
                String SNADD = set.getString("SNADD");
                String FDATE = set.getString("FDATE");
                String SDATE = set.getString("TDATE");
                String TYPER = set.getString("TYPER");

//                System.out.println("\n" + SRNO + "\n" + ENAME + "\n" + FLAG + 
//                                    "\n" + CONAM + "\n" + TOEMAIL + "\n"
//                                    + CCEMAIL + "\n" + FNAME + "\n" + FDATE + "\n" + SDATE + "\n" + TYPER + "\n");
                String[] TOLIST = null;
                if (CCEMAIL != null) {
                    TOLIST = CCEMAIL.split(",\\s*");
                }
                if (Objects.equals(FLAG, "S")) {

                    System.out.println("got here in if");
                    boolean ch = false;
                    while (ch = false) {
                        InternetAddress emailAddr;
                        try {
                            emailAddr = new InternetAddress(TOEMAIL);
                            emailAddr.validate();

                            for (int i = 0; i < TOLIST.length; i++) {
                                emailAddr = new InternetAddress(TOLIST[i]);
                                emailAddr.validate();
                            }
                            ch = true;
                        } catch (AddressException ex) {
                            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
                            System.out.println("Sahi wala likho paji");
                        }
                    }

                    Session session = Session.getInstance(props,
                            new javax.mail.Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(to[4], to[5]);
                        }
                    });

                    SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    Date date = null;
                    try {
                        date = dt.parse(FDATE);
                        SimpleDateFormat dt1 = new SimpleDateFormat("dd-MM-yyyy");
                        FDATE = dt1.format(date);

                        date = dt.parse(SDATE);
                        SimpleDateFormat dt2 = new SimpleDateFormat("dd-MM-yyyy");
                        SDATE = dt2.format(date);
                    } catch (ParseException e) {

                    }
                    try {
                        // Create a default MimeMessage object.
                        Message message = new MimeMessage(session);

                        // Set From: header field of the header.
                        message.setFrom(new InternetAddress(SNADD));

                        // Set Subject: header field
                        message.setSubject("eReports: " + TYPER);

                        // Create the message part
                        BodyPart messageBodyPart = new MimeBodyPart();

                        // Now set the actual message
                        messageBodyPart.setText("Attention " + ENAME + "\n" + "\n" + "Please Find Attached File of "
                                + TYPER + "\n" + "\n" + "FROM" + " " + FDATE + " TO" + " " + SDATE + "\n" + "\n \n \n" + disclaimer);

                        // Create a multipar message
                        Multipart multipart = new MimeMultipart();

                        // Set text message part
                        multipart.addBodyPart(messageBodyPart);

                        // Part two is attachment
                        messageBodyPart = new MimeBodyPart();

                        fileCheck = false;
                        File s = new File(FNAME);
                        if (s.exists()) {
                            DataSource source = new FileDataSource(FNAME);
                            messageBodyPart.setDataHandler(new DataHandler(source));
                            messageBodyPart.setFileName(FNAME);
                            multipart.addBodyPart(messageBodyPart);
                            fileCheck = true;
                        } else {
                            DisplayinTray.trayIcon.displayMessage("IOException", "File Not Found", TrayIcon.MessageType.ERROR);
                            fileCheck = false;
                        }

//
//              filecheck = false;
//              File s = new File(FNAME);
//              if(s.exists()){
//                    EmailAttachment emailAttachment = new EmailAttachment();
//                    emailAttachment.setPath(FNAME);
//                    emailAttachment.setDisposition(EmailAttachment.ATTACHMENT);
//                    
//              filecheck = true;
//              } else {
//                  DisplayinTray.trayIcon.displayMessage("IOException", "File Not Found", TrayIcon.MessageType.ERROR);
//                 filecheck = false;
//              }
                        // Send the complete message parts
                        message.setContent(multipart);

                        message.setRecipients(Message.RecipientType.TO,
                                InternetAddress.parse(TOEMAIL));

                        if (TOLIST != null) {
                            for (int i = 0; i < TOLIST.length; i++) {
                                message.addRecipients(Message.RecipientType.CC,
                                        InternetAddress.parse(TOLIST[i]));
                            }
                        }
                        if (fileCheck == true) {
                            Transport.send(message);
                            DisplayinTray.trayIcon.displayMessage("Notification", "Email Sent to: " + TOEMAIL, TrayIcon.MessageType.INFO);
                        } else {
                            DisplayinTray.trayIcon.displayMessage("IOException", "Message not sent to", TrayIcon.MessageType.ERROR);
                        }
                    } catch (MessagingException e) {
                        System.out.println("Exception in Messaging Exception");
                        //throw new RuntimeException(e);
                        e.printStackTrace();
                        continue;
                    }

                } else {
                    System.out.println("in else");
                    System.out.println("No emails to be sent");
                }
                OracleConn oc = new OracleConn();
                Connection con = oc.OracleConnection2();
                Statement statement = con.createStatement();
                int dur = ac.getDuration();
                if (fileCheck == true) {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'T' WHERE SRNO = " + SRNO);
                    try {
                        System.out.println("Waiting for " + dur + " minute(s)");
                        TimeUnit.MINUTES.sleep(dur);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    System.out.println("Updated for  " + ENAME);
                } else {
                    statement.executeUpdate("UPDATE email_info "
                            + "SET FLAG = 'P' WHERE SRNO = " + SRNO);
                    System.out.println("Pending for  " + ENAME);

                }

            }

        } catch (SQLException ex) {
            Logger.getLogger(OracleConn.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 1;
    }
     */

    //---------------------------------------------------------------------------------------------------
    public void report_email(String report) {

        try {
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(USER_NAME, PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(USER_NAME));
            message.setSubject("Error Report");
            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText(report);
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(USER_NAME));
            Transport.send(message);
            System.out.println("\n" + "Error Report sent to: " + " " + USER_NAME);
        } catch (MessagingException ex) {
            Logger.getLogger(Emailing.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
