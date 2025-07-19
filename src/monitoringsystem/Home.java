/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;

/**
 *
 * @author mel
 */
public class Home extends javax.swing.JFrame implements Runnable {
   
   int hour,second,minute;
   int day,month,year;
   String timestr,datestr;
   
   
   MusicHelper mh = new MusicHelper();
    
   URL sounds, err, warnings, logouts,unsus, success, attemptload;

    /**
     * Creates new form Home
     */
    public Home() {
        
        initComponents();
        
        sounds = getClass().getResource("1lofi.wav");
        err = getClass().getResource("ERROR 1.4 SEC.wav");
        warnings = getClass().getResource("WARNING 1.1 SEC.wav");
        logouts = getClass().getResource("l_o.wav");
        unsus = getClass().getResource("unsuspicous.wav");
        success = getClass().getResource("final success.wav");
        attemptload = getClass().getResource("attemptloading.wav");
        

        this.setIconImage(new ImageIcon(getClass().getResource("log.png")).getImage());
        
        Thread t = new Thread(this);
        t.start();
        
        txtKeyReleased(null);
        
        
        JTableHeader theader = jTable1.getTableHeader();
        
        theader.setForeground(Color.BLACK);
        
        
        theader.setFont(new Font("Yu Gothic", Font.PLAIN,14));
        ((DefaultTableCellRenderer)theader.getDefaultRenderer()).setHorizontalAlignment(JLabel.CENTER);
        
        jTable1.setBackground(new Color(0,0,0,0));
        
        ((DefaultTableCellRenderer)jTable1.getDefaultRenderer(Object.class)).setBackground(new Color(0,0,0,0));
        
        jTable1.setGridColor(Color.LIGHT_GRAY);
        jTable1.setRowHeight(30);
        jTable1.setSelectionForeground(Color.white);
        
        
        jTable1.setForeground(Color.LIGHT_GRAY);
        
        jScrollPane1.setBackground(new Color(0,0,0,0));
        
        jScrollPane1.setOpaque(false);
        jTable1.setOpaque(false);
        
        ((DefaultTableCellRenderer)jTable1.getDefaultRenderer(Object.class)).setOpaque(false);
        
        jScrollPane1.getViewport().setOpaque(false);
        
        jTable1.setShowGrid(true);
        
        Action exitAction = new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);

            }
        };
        String exit = "exit";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), exit);
         About.getActionMap().put(exit, exitAction);
         
         Action homeAction = new AbstractAction("home") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(LoginSystem.ICONIFIED);
              }
        };  
         String home = "home";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0), home);
         jLabel1.getActionMap().put(home, homeAction);
         
         Action loginAction = new AbstractAction("login") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                LOGINActionPerformed(ae);
                

            }
        };
        String login = "login";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), login);
         About.getActionMap().put(login, loginAction);
         
         Action signupAction = new AbstractAction("signup") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                SignUpActionPerformed(ae);           

            }
        };
        String signup = "signup";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), signup);
         About.getActionMap().put(signup, signupAction);
         
          Action printAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                PrintActionPerformed(ae);

            }
        };
        String print = "print";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK), print);
         About.getActionMap().put(print, printAction);
         
         Action aboutAction = new AbstractAction("about") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                AboutActionPerformed(ae);
            }
        };
        String about = "about";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0), about);
         About.getActionMap().put(about, aboutAction);
         
         Action helpAction = new AbstractAction("help") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Help().setVisible(true);
            }
        };
        String help = "help";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_H, KeyEvent.ALT_DOWN_MASK), help);
         About.getActionMap().put(help, helpAction);
         
        Action playAction = new AbstractAction("play") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    mh.playbg1(sounds);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        String play = "play";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         About.getActionMap().put(play, playAction); 
         
         Action pauseAction = new AbstractAction("pause") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    mh.bgStop();
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        String pause = "pause";
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         About.getActionMap().put(pause, pauseAction); 
         
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        minimize = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        head = new javax.swing.JLabel();
        bg1 = new javax.swing.JPanel();
        heading1 = new javax.swing.JPanel();
        txt = new javax.swing.JTextField();
        Print = new javax.swing.JButton();
        About = new javax.swing.JButton();
        LOGIN = new javax.swing.JButton();
        SignUp = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        flower = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        getContentPane().add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 10, 50, 50));

        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        getContentPane().add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1515, 10, 50, 49));

        date.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        date.setForeground(new java.awt.Color(0, 255, 204));
        getContentPane().add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 267, 150, 40));

        time.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(0, 255, 204));
        getContentPane().add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(1440, 267, 150, 40));

        head.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/home1.png"))); // NOI18N
        getContentPane().add(head, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        bg1.setBackground(new java.awt.Color(204, 204, 204));
        bg1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        heading1.setBackground(new java.awt.Color(0, 153, 153));
        heading1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt.setBackground(new java.awt.Color(0, 153, 153));
        txt.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        txt.setForeground(new java.awt.Color(255, 255, 255));
        txt.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txt.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 3, 0, new java.awt.Color(0, 0, 0)));
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFocusLost(evt);
            }
        });
        txt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtMouseClicked(evt);
            }
        });
        txt.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtKeyReleased(evt);
            }
        });
        heading1.add(txt, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 15, 200, 40));

        Print.setBackground(new java.awt.Color(0, 51, 51));
        Print.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        Print.setForeground(new java.awt.Color(255, 255, 255));
        Print.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/print 55.png"))); // NOI18N
        Print.setText("Print Data");
        Print.setToolTipText("");
        Print.setBorder(null);
        Print.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Print.setOpaque(false);
        Print.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PrintActionPerformed(evt);
            }
        });
        heading1.add(Print, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 10, 145, 52));

        About.setBackground(new java.awt.Color(0, 51, 51));
        About.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        About.setForeground(new java.awt.Color(255, 255, 255));
        About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/in2.png"))); // NOI18N
        About.setText("  About");
        About.setBorder(null);
        About.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        About.setOpaque(false);
        About.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AboutActionPerformed(evt);
            }
        });
        heading1.add(About, new org.netbeans.lib.awtextra.AbsoluteConstraints(1410, 10, 142, 52));

        LOGIN.setBackground(new java.awt.Color(0, 51, 51));
        LOGIN.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        LOGIN.setForeground(new java.awt.Color(255, 255, 255));
        LOGIN.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/login1.png"))); // NOI18N
        LOGIN.setText("  Login");
        LOGIN.setBorder(null);
        LOGIN.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LOGIN.setOpaque(false);
        LOGIN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LOGINActionPerformed(evt);
            }
        });
        heading1.add(LOGIN, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 10, 140, 52));

        SignUp.setBackground(new java.awt.Color(0, 51, 51));
        SignUp.setFont(new java.awt.Font("Verdana", 0, 15)); // NOI18N
        SignUp.setForeground(new java.awt.Color(255, 255, 255));
        SignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/account.png"))); // NOI18N
        SignUp.setText("  Sign Up");
        SignUp.setBorder(null);
        SignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        SignUp.setOpaque(false);
        SignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SignUpActionPerformed(evt);
            }
        });
        heading1.add(SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 10, 140, 52));

        jLabel1.setFont(new java.awt.Font("Verdana", 0, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/loupe.png"))); // NOI18N
        jLabel1.setText("  SEARCH STUDENT DATABASE");
        jLabel1.setToolTipText("");
        heading1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 20, 320, 40));

        bg1.add(heading1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 8, 1600, 70));

        jTable1.setFont(new java.awt.Font("Constantia", 0, 18)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Student ID", "First Name", "Surname", " Section", "Gender", "Phone No.", "Date", "Time In", "FCL", "PRACRES", "EMPTECH", "PHILOSOPHY", "ANIMATION", "PROGRAMMING", "WIRCACA", "PEH", "Time Out"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(25);
        jTable1.setSelectionBackground(new java.awt.Color(255, 153, 153));
        jScrollPane1.setViewportView(jTable1);

        bg1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 110, 1540, 450));

        flower.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/Background.png"))); // NOI18N
        bg1.add(flower, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 87, -1, -1));

        getContentPane().add(bg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 320, 1600, 580));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
       System.exit(0);
       
    }//GEN-LAST:event_exitMouseClicked

    private void txtKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtKeyReleased

        String quer = txt.getText();
        DefaultTableModel dtm = (DefaultTableModel) jTable1.getModel();

        int r = jTable1.getRowCount();
        while(r-- > 0)
        {
            dtm.removeRow(r);
        }

        try {
            ResultSet rs = DBHelper.getData(quer, quer, quer, quer, quer);
            while(rs.next()){
                java.util.Vector v = new java.util.Vector();

                v.add(rs.getString("id"));
                v.add(rs.getString("studentid"));
                v.add(rs.getString("Firstname"));
                v.add(rs.getString("Surname"));
                v.add(rs.getString("Section"));
                v.add(rs.getString("Gender"));
                v.add(rs.getString("Mobile"));
                v.add(rs.getString("Date"));
                v.add(rs.getString("TimeIn"));
                v.add(rs.getString("FCL"));
                v.add(rs.getString("PRACRES"));
                v.add(rs.getString("EMPTECH"));
                v.add(rs.getString("PHILOSOPHY"));
                v.add(rs.getString("ANIMATION"));
                v.add(rs.getString("PROGRAMMING1"));
                v.add(rs.getString("WIRCACA"));
                v.add(rs.getString("PEH"));
                v.add(rs.getString("Timeout"));

                dtm.addRow(v);
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Database Error");
        }
    }//GEN-LAST:event_txtKeyReleased

    private void txtMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtMouseClicked

        txt.setText("");

    }//GEN-LAST:event_txtMouseClicked

    private void txtFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusLost

        if (txt.getText().equals(""))
        {

            txt.setText("Search Database .....");
            txt.setForeground(new Color(255,255,255));

        }

    }//GEN-LAST:event_txtFocusLost

    private void txtFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFocusGained

        if (txt.getText().equals("Search Database ....."))
        {

            txt.setText("");
            txt.setForeground(new Color(255,255,255));

        }
    }//GEN-LAST:event_txtFocusGained

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
       
        setState(Home.ICONIFIED);
        
        
    }//GEN-LAST:event_minimizeMouseClicked

    private void AboutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AboutActionPerformed
        
        new AboutUs().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_AboutActionPerformed

    private void LOGINActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LOGINActionPerformed
        
        new LoginSystem().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_LOGINActionPerformed

    private void SignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SignUpActionPerformed
        
        new SignUp().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_SignUpActionPerformed

    private void PrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PrintActionPerformed
        
        MessageFormat header = new MessageFormat("Attendance Database Hard Copy");
        MessageFormat footer = new MessageFormat("Page {0, number, integer} of Student Attendance Sheet");

        try
        {
            boolean complete = jTable1.print(JTable.PrintMode.FIT_WIDTH,header,footer);
            if (complete){
                /* show a success message*/
            }else{
                /*show a message indicating prnting was cancelled*/
            }
        }
        catch(java.awt.print.PrinterException e)
        {
            System.err.format("No Printer found", e.getMessage());
        }
        
    }//GEN-LAST:event_PrintActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Home.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Home().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton About;
    private javax.swing.JButton LOGIN;
    private javax.swing.JButton Print;
    private javax.swing.JButton SignUp;
    private javax.swing.JPanel bg1;
    private javax.swing.JLabel date;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel flower;
    private javax.swing.JLabel head;
    private javax.swing.JPanel heading1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel time;
    private javax.swing.JTextField txt;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        
        
            while(true){
            try{
            Calendar c= Calendar.getInstance();
            hour=c.get(Calendar.HOUR_OF_DAY);
            if (hour >12)
            hour=hour-12;
            minute=c.get(Calendar.MINUTE);
            second=c.get(Calendar.SECOND);
            year=c.get(Calendar.YEAR);
            month=c.get(Calendar.MONTH);
            day=c.get(Calendar.DAY_OF_MONTH);
            SimpleDateFormat sdf = new SimpleDateFormat("hh:mm:ss a");
            SimpleDateFormat df = new SimpleDateFormat("MM:dd:yyyy");
            Date dat = c.getTime();
            timestr= sdf.format(dat);
            datestr = df.format(dat);
            time.setText(timestr);
            date.setText(datestr);

            }catch(Exception e){

            } 
        }
    }
}
