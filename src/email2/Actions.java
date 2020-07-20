package email2;

import java.awt.TrayIcon;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.Reader;
import java.net.BindException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Actions {

    static int duration;

//    private static String FILE_PATH = "C:\\Users\\" + System.getProperty("user.name") + "\\email\\";
    private static String FILE_PATH = "..\\email\\";

    public int getDuration() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Duration.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);

            text = bufferReader.readLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        duration = Integer.parseInt(text);
        return duration;

    }

    public void saveDuration(String t) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "Duration.txt"));

            writer.write(t);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();
        }

    }

    public boolean logincheck(String user, String pass) {

        if ("admin".equals(user) && "bits123!@#".equals(pass)) {
            return true;
        } else {
            return false;
        }

    }

    public void saveto(String user, String pass, String ip) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "Default.txt"));

            writer.write(user + "," + pass + "," + ip);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();
        }
    }

    public void saveto2(String user, String pass, String ip) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "Default2.txt"));

            writer.write(user + "," + pass + "," + ip);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();;
        }

    }

    public void saveDesc(String disc) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(
                    new File(FILE_PATH + "Disclaimer.txt")));

            writer.write(disc);

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void saveDesc2(String disc) {

        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(new FileWriter(
                    new File(FILE_PATH + "Disclaimer2.txt")));

            writer.write(disc);

        } catch (IOException ex) {
            // report
        } finally {
            try {
                writer.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private static final int PORT = 9999;
    private static ServerSocket socket;

    public void checkIfRunning() {
        try {
            //Bind to localhost adapter with a zero connection queue 
            socket = new ServerSocket(PORT, 0, InetAddress.getByAddress(new byte[]{127, 0, 0, 1}));
        } catch (BindException e) {
            System.err.println("Already running.");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Unexpected error.");
            e.printStackTrace();
            System.exit(2);
        }
    }

    public String[] readFile() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Default.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);

            text = bufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
//DisplayinTray.trayIcon.displayMessage("Notification", "Default file not Found ok" , TrayIcon.MessageType.INFO);                
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] toList;
        toList = text.split(",");

        for (int i = 0; i < toList.length; i++) {
            System.out.println(toList[i]);
        }
        return toList;
    }

    public String readDescription() {

        StringBuilder stringBuilder = new StringBuilder(512);
        try {
            Reader reader = new InputStreamReader(new FileInputStream(
                    new File(FILE_PATH + "Disclaimer.txt")), "UTF-8");
            int c = 0;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            //DisplayinTray.trayIcon.displayMessage("Notification", "Setting files not found " , TrayIcon.MessageType.INFO);
            System.out.println("Setting files not found ");
//throw new RuntimeException(e);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();
    }

    public String[] readFile2() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Default2.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);

            text = bufferReader.readLine();
        } catch (FileNotFoundException e) {
            DisplayinTray.trayIcon.displayMessage("Notification", "Default2 file not Found ", TrayIcon.MessageType.INFO);
            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] toList;
        toList = text.split(",");

        for (int i = 0; i < toList.length; i++) {
            System.out.println(toList[i]);
        }
        return toList;
    }

    public String readDesc2() {

        StringBuilder stringBuilder = new StringBuilder(512);
        try {
            Reader reader = new InputStreamReader(new FileInputStream(
                    new File(FILE_PATH + "Disclaimer2.txt")), "UTF-8");
            int c = 0;
            while ((c = reader.read()) != -1) {
                stringBuilder.append((char) c);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(stringBuilder.toString());
        return stringBuilder.toString();

    }

    public boolean filesChecking() {
        String text, text2, text3, text4 = "";
        InputStreamReader isReader = null;
        InputStreamReader isReader2 = null;
        InputStreamReader isReader3 = null;
        InputStreamReader isReader4 = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Port.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);
            text = bufferReader.readLine();
            isReader2
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "checkDB.txt")));
            BufferedReader bufferReader2 = new BufferedReader(isReader2);
            text2 = bufferReader2.readLine();
            isReader3
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Default.txt")));
            BufferedReader bufferReader3 = new BufferedReader(isReader3);
            text3 = bufferReader3.readLine();
            isReader4
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Default.txt")));
            BufferedReader bufferReader4 = new BufferedReader(isReader4);
            text4 = bufferReader4.readLine();

        } catch (FileNotFoundException ex) {
            DisplayinTray.trayIcon.displayMessage("Notification", "Settings not Found ", TrayIcon.MessageType.INFO);
            System.out.println("Settings not Found");
            return false;
            
            //e.printStackTrace();
        } catch (NullPointerException exx) {
            DisplayinTray.trayIcon.displayMessage("Notification", "Null Pointer Exception", TrayIcon.MessageType.INFO);

            //Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException e) {
            DisplayinTray.trayIcon.displayMessage("Notification", "IO  Exception", TrayIcon.MessageType.INFO);

            //Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                isReader.close();
                isReader2.close();
                isReader3.close();
                isReader4.close();
            } catch (Exception exv) {
               // DisplayinTray.trayIcon.displayMessage("Notification", "IO Exception in Finally Block", TrayIcon.MessageType.INFO);

                // Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, exv);
            }

        }

        return true;
    }

    public String[] readPort() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Port.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);

            text = bufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                //DisplayinTray.trayIcon.displayMessage("Notification", "Null Pointer Exception", TrayIcon.MessageType.INFO);
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] toList;
        toList = text.split(",");

        for (int i = 0; i < toList.length; i++) {
            System.out.println(toList[i]);
        }
        return toList;

    }

    public String[] readPort2() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "Port2.txt")));
            BufferedReader bufferReader = new BufferedReader(isReader);

            text = bufferReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        String[] toList;
        toList = text.split(",");

        for (int i = 0; i < toList.length; i++) {
            System.out.println(toList[i]);
        }
        return toList;

    }

    public void savePort(String domail, String port, String host, String tls, String userId, String userPassword) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "Port.txt"));

            writer.write(domail + "," + port + "," + host + "," + tls + "," + userId + "," + userPassword);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();
        }
    }

    public void savePort2(String domain, String port, String host, String tls, String userId, String userPassword) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "Port2.txt"));

            writer.write(domain + "," + port + "," + host + "," + tls + "," + userId + "," + userPassword);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();
        }
    }

    public void saveDis(String t) {

        PrintWriter writer = null;
        try {
            writer = new PrintWriter(
                    new File(FILE_PATH + "checkDB.txt"));

            writer.write(t);

        } catch (IOException ex) {
            // report
        } finally {
            writer.close();
        }
    }

    public Boolean readDis() {

        String text = "";
        InputStreamReader isReader = null;
        try {
            isReader
                    = new InputStreamReader(
                            new FileInputStream(
                                    new File(FILE_PATH + "checkDB.txt")));
            BufferedReader br = new BufferedReader(isReader);

            text = br.readLine();
        } catch (FileNotFoundException e) {
            DisplayinTray.trayIcon.displayMessage("Notification", "Checkdb file not Found You need to save the port file which is not located ", TrayIcon.MessageType.INFO);

            //e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                isReader.close();
            } catch (IOException ex) {
                Logger.getLogger(Actions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return Boolean.parseBoolean(text);
    }

}
