package activities;

import email2.Actions;
import email2.Email20;
import email2.OracleConn;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.table.DefaultTableModel;

public class mainJframe extends javax.swing.JFrame {

    //DisplayinTray DTI = new DisplayinTray(this);
    public mainJframe() {
        initComponents();
        this.getContentPane().setBackground(Color.white);
        this.setDefaultCloseOperation(HIDE_ON_CLOSE);
//        Image image = Toolkit.getDefaultToolkit().getImage(filename);
//        this.setIconImage(Toolkit.getDefaultToolkit().getImage(filename));
        this.setIconImage(new ImageIcon(getClass().getResource("logo.png")).getImage());

        // Now create a new TextAreaOutputStream to write to our JTextArea control and wrap a
        // PrintStream around it to support the println/printf methods.
        PrintStream out = new PrintStream(new TextAreaOutputStream(jTextConsole));

        // redirect standard output stream to the TextAreaOutputStream
        System.setOut(out);

        // redirect standard error stream to the TextAreaOutputStream
        System.setErr(out);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btn_data = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        btn_login = new javax.swing.JButton();
        btn_minimize = new javax.swing.JButton();
        btn_help = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btn_exit = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        txt_min = new javax.swing.JTextField();
        btn_duration = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        btn_data1 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextConsole = new javax.swing.JTextArea();

        setBackground(new java.awt.Color(255, 255, 255));

        btn_data.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_data.setText("DB1");
        btn_data.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dataActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Malgun Gothic", 0, 24)); // NOI18N
        jLabel1.setText("Email Management");

        btn_login.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_login.setText("Login");
        btn_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_loginActionPerformed(evt);
            }
        });

        btn_minimize.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_minimize.setText("Minimize");
        btn_minimize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_minimizeActionPerformed(evt);
            }
        });

        btn_help.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_help.setText("Help");
        btn_help.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_helpActionPerformed(evt);
            }
        });

        jLabel2.setText("© 2016  Burhani IT Solutions");

        jLabel3.setText(" All rights reserved. ");

        jLabel4.setText("Distribution without permission will be liable to legal action. ");

        btn_exit.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_exit.setText("Exit");
        btn_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_exitActionPerformed(evt);
            }
        });

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/activities/photo.png"))); // NOI18N

        txt_min.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_minActionPerformed(evt);
            }
        });

        btn_duration.setText("OK");
        btn_duration.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_durationActionPerformed(evt);
            }
        });

        jLabel6.setText("minutes");

        btn_data1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        btn_data1.setText("DB2");
        btn_data1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_data1ActionPerformed(evt);
            }
        });

        jTextConsole.setColumns(20);
        jTextConsole.setRows(5);
        jScrollPane1.setViewportView(jTextConsole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel3))
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txt_min, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_duration))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_data, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_data1, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_help, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 380, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(btn_minimize, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_minimize, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txt_min, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btn_duration)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btn_help, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(btn_data, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_login, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(btn_data1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btn_exit, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel7))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    JFrame frame1;
    static JTable table;
    String[] columnNames = {"SRNO", "ENAME", "CONAM", "FLAG", "SNADD", "EMADD", "CCADD", "FNAME", "SDATE", "FDATE", "TYPER"};
    PreparedStatement pst;

    private void btn_dataActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dataActionPerformed
        // TODO add your handling code here:

        frame1 = new JFrame("Database Search Result");
        frame1.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

        String srno = "";
        String ename = "";
        String conam = "";
        String flag = "";
        String snadd = "";
        String emadd = "";
        String ccadd = "";
        String fname = "";
        String sdate = "";
        String fdate = "";
        String typer = "";

        try {
            OracleConn oc = new OracleConn();
            Connection con = oc.OracleConnection();
            ResultSet set = null;
            if (con == null) {
                System.err.println("Connection not made. In DataRetrievel.emailData");
            }

            try {

                Statement statement = con.createStatement();

                set = statement.executeQuery("select SRNO,ENAME,FLAG,CONAM,SNADD,EMADD,CCADD,FNAME,FDATE,SDATE,TYPER from EMAIL_INFO where FLAG = 'S'");

            } catch (SQLException ex) {
                System.out.println("Error in data retrievel");
            }

            int i = 0;
            while (set.next()) {

                srno = set.getString("SRNO");
                ename = set.getString("ENAME");
                conam = set.getString("CONAM");
                flag = set.getString("FLAG");
                snadd = set.getString("SNADD");
                emadd = set.getString("EMADD");
                ccadd = set.getString("CCADD");
                fname = set.getString("FNAME");
                sdate = set.getString("SDATE");
                fdate = set.getString("FDATE");
                typer = set.getString("TYPER");

                model.addRow(new Object[]{srno, ename, conam, flag, snadd, emadd, ccadd, fname, sdate, fdate, typer});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Info", JOptionPane.INFORMATION_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error", "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(900, 300);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


    }//GEN-LAST:event_btn_dataActionPerformed

    private void btn_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_loginActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new loginJframe().setVisible(true);


    }//GEN-LAST:event_btn_loginActionPerformed

    private void btn_minimizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_minimizeActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_btn_minimizeActionPerformed

    private void btn_helpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_helpActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
        new helpJframe().setVisible(true);

    }//GEN-LAST:event_btn_helpActionPerformed

    private void btn_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_exitActionPerformed
        // TODO add your handling code here:
        System.exit(1);
    }//GEN-LAST:event_btn_exitActionPerformed

    private void txt_minActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_minActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_minActionPerformed

    private void btn_durationActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_durationActionPerformed
        // TODO add your handling code here:

        Actions ac = new Actions();
        ac.saveDuration(txt_min.getText());

        try {
            int dur = Integer.parseInt(txt_min.getText());
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Error: Enter Numbers Only");
        }

    }//GEN-LAST:event_btn_durationActionPerformed

    private void btn_data1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_data1ActionPerformed
        // TODO add your handling code here:
        frame1 = new JFrame("Database Search Result");
        frame1.setLayout(new BorderLayout());

        DefaultTableModel model = new DefaultTableModel();
        model.setColumnIdentifiers(columnNames);

        table = new JTable();
        table.setModel(model);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        table.setFillsViewportHeight(true);
        JScrollPane scroll = new JScrollPane(table);
        scroll.setHorizontalScrollBarPolicy(
                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        scroll.setVerticalScrollBarPolicy(
                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

//String textvalue = textbox.getText();
        String srno = "";
        String ename = "";
        String conam = "";
        String flag = "";
        String emadd = "";
        String ccadd = "";
        String fname = "";
        String sdate = "";
        String fdate = "";
        String typer = "";

        try {
            OracleConn oc = new OracleConn();
            Connection con = oc.OracleConnection2();
            ResultSet set = null;
            if (con == null) {
                System.err.println("Connection not made. In DataRetrievel.emailData");
            }

            try {

                Statement statement = con.createStatement();

                set = statement.executeQuery("select SRNO,ENAME,FLAG,CONAM,EMADD,CCADD,FNAME,FDATE,SDATE,TYPER from EMAIL_INFO where FLAG = 'S'");

            } catch (SQLException ex) {
                System.out.println("Error in data retrievel");
            }

            int i = 0;
            while (set.next()) {

                srno = set.getString("SRNO");
                ename = set.getString("ENAME");
                conam = set.getString("CONAM");
                flag = set.getString("FLAG");
                emadd = set.getString("EMADD");
                ccadd = set.getString("CCADD");
                fname = set.getString("FNAME");
                sdate = set.getString("SDATE");
                fdate = set.getString("FDATE");
                typer = set.getString("TYPER");

                model.addRow(new Object[]{srno, ename, conam, flag, emadd, ccadd, fname, sdate, fdate, typer});
                i++;
            }
            if (i < 1) {
                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
            }
            if (i == 1) {
                System.out.println(i + " Record Found");
            } else {
                System.out.println(i + " Records Found");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        frame1.add(scroll);
        frame1.setVisible(true);
        frame1.setSize(700, 300);
        frame1.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }//GEN-LAST:event_btn_data1ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Windows".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(mainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(mainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(mainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(mainJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
        try {
            //    new Email20();
            // new HideToSystemTray();
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(Email20.class.getName()).log(Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
//                try {
//                    Thread.sleep(55000);
//                } catch (InterruptedException ex) {
//                    Logger.getLogger(mainJframe.class.getName()).log(Level.SEVERE, null, ex);
//                }

                new mainJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_data;
    private javax.swing.JButton btn_data1;
    private javax.swing.JButton btn_duration;
    private javax.swing.JButton btn_exit;
    private javax.swing.JButton btn_help;
    private javax.swing.JButton btn_login;
    private javax.swing.JButton btn_minimize;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jTextConsole;
    private javax.swing.JTextField txt_min;
    // End of variables declaration//GEN-END:variables
}
