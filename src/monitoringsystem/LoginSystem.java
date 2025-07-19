/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;
import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
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
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

/**
 *
 * @author mel
 */
public class LoginSystem extends javax.swing.JFrame implements Runnable {
    int hour,second,minute;
    int day,month,year;
    String timestr,datestr;
    int xMouse;
    int yMouse;
    
    int attempt = 1;
    
    MusicHelper mh = new MusicHelper();
    
    URL sounds, err, warnings, logouts,unsus, success, attemptload;
    

    /**
     * Creates new form LoginSystem
     */
    public LoginSystem() {
        Thread t = new Thread(this);
        t.start();
        
        initComponents();
        
        
        sounds = getClass().getResource("1lofi.wav");
        err = getClass().getResource("ERROR 1.4 SEC.wav");
        warnings = getClass().getResource("WARNING 1.1 SEC.wav");
        logouts = getClass().getResource("l_o.wav");
        unsus = getClass().getResource("unsuspicous.wav");
        success = getClass().getResource("final success.wav");
        attemptload = getClass().getResource("attemptloading.wav");
        
        
        this.setIconImage(new ImageIcon(getClass().getResource("log.png")).getImage());
        
        
        
        this.con2.setVisible(false);
        
        
        Action cancelAction = new AbstractAction("cancel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnCancelActionPerformed(ae);
               
            }
        };
        String cancel = "cancel";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F3, 0), cancel);
         btnLogin.getActionMap().put(cancel, cancelAction);
         
         
         Action exitAction = new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);

            }
        };
        String exit = "exit";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), exit);
         btnLogin.getActionMap().put(exit, exitAction);
         
         
         Action loginAction = new AbstractAction("login") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnLoginActionPerformed(ae);
               
            }
        };
        String key = "login";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), key);
         btnLogin.getActionMap().put(key, loginAction);
         
         
          Action aboutAction = new AbstractAction("about") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new AboutUs().setVisible(true);
                dispose();
               
            }
        };
        String abouts = "about";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0), abouts);
         btnLogin.getActionMap().put(abouts, aboutAction);
         
         Action homeAction = new AbstractAction("home") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(LoginSystem.ICONIFIED);
              }
        };  
         String home = "home";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0), home);
         jLabel1.getActionMap().put(home, homeAction);
         
         Action unhideAction = new AbstractAction("unhide") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                con2.setVisible(true);
                con1.setVisible(false);
        
                txtPass.setEchoChar((char)0);
                Font f = new Font("Traditional Arabic",Font.PLAIN,17);
                txtPass.setFont(f);
                
              }
        };  
         String unhide = "unhide";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F9, 0), unhide);
         jLabel1.getActionMap().put(unhide, unhideAction);
         
         Action hideAction = new AbstractAction("hide") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                con1.setVisible(true);
                con2.setVisible(false);
        
               txtPass.setEchoChar('*');
               Font f = new Font("Cooper Black",Font.BOLD,17);
               txtPass.setFont(f);
                
              }
        };  
         String hide = "hide";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F10, 0), hide);
         jLabel1.getActionMap().put(hide, hideAction);
         
         Action signUpAction = new AbstractAction("signUp") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new SignUp().setVisible(true);
                dispose();
                
              }
        };  
         String signUp = "signUp";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), signUp);
         jLabel1.getActionMap().put(signUp, signUpAction);
         
         Action helpAction = new AbstractAction("help") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new Help().setVisible(true);
                dispose();
                
              }
        };  
         String help = "help";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_H, KeyEvent.ALT_DOWN_MASK), help);
         jLabel1.getActionMap().put(help, helpAction);
        
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
         about.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         about.getActionMap().put(play, playAction); 
         
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
         about.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         about.getActionMap().put(pause, pauseAction); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        time = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        frameDrag = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btnCancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        lbl_SignUp = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        con2 = new javax.swing.JLabel();
        con1 = new javax.swing.JLabel();
        help = new javax.swing.JLabel();
        click = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(630, 540));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(70, 70, 70));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        time.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        time.setForeground(new java.awt.Color(0, 255, 204));
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 5, 200, 40));

        date.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        date.setForeground(new java.awt.Color(0, 255, 204));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 5, 160, 40));

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/cancel1.png"))); // NOI18N
        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 16, -1, -1));

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/home.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 15, -1, -1));

        about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/in2.png"))); // NOI18N
        about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });
        jPanel1.add(about, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 15, -1, -1));

        frameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                frameDragMouseDragged(evt);
            }
        });
        frameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                frameDragMousePressed(evt);
            }
        });
        jPanel1.add(frameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(-480, 0, 1110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 630, 50));

        jPanel2.setBackground(new java.awt.Color(70, 70, 70));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.black, java.awt.Color.cyan, java.awt.Color.cyan, java.awt.Color.cyan), "Attendance Login System", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel1.setText("Username");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 230, -1, 40));

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 300, -1, 40));

        txtUser.setBackground(new java.awt.Color(70, 70, 70));
        txtUser.setFont(new java.awt.Font("Traditional Arabic", 0, 17)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 255)));
        txtUser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUserActionPerformed(evt);
            }
        });
        jPanel2.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 220, 240, 40));

        txtPass.setBackground(new java.awt.Color(70, 70, 70));
        txtPass.setFont(new java.awt.Font("Cooper Black", 1, 17)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 255)));
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 290, 240, 40));

        btnLogin.setBackground(new java.awt.Color(0, 153, 204));
        btnLogin.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        btnLogin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/login1.png"))); // NOI18N
        btnLogin.setText("Login");
        btnLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, 120, 40));

        btnCancel.setBackground(new java.awt.Color(0, 153, 204));
        btnCancel.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        btnCancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/back.png"))); // NOI18N
        btnCancel.setText("Cancel");
        btnCancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelActionPerformed(evt);
            }
        });
        jPanel2.add(btnCancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 400, 120, 40));

        btnExit.setBackground(new java.awt.Color(0, 153, 204));
        btnExit.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/logout.png"))); // NOI18N
        btnExit.setText("Exit");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel2.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 400, 120, 40));

        lbl_SignUp.setBackground(new java.awt.Color(0, 255, 204));
        lbl_SignUp.setFont(new java.awt.Font("Simplified Arabic", 1, 16)); // NOI18N
        lbl_SignUp.setForeground(new java.awt.Color(102, 255, 204));
        lbl_SignUp.setText("Sign Up Here");
        lbl_SignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        lbl_SignUp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbl_SignUpMouseClicked(evt);
            }
        });
        jPanel2.add(lbl_SignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(396, 347, -1, 30));

        jLabel4.setFont(new java.awt.Font("Simplified Arabic", 1, 16)); // NOI18N
        jLabel4.setText("Forgot Password ? ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 350, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/login.png"))); // NOI18N
        jPanel2.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 70, -1, -1));

        con2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/Eye1 (1).png"))); // NOI18N
        con2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        con2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                con2MousePressed(evt);
            }
        });
        jPanel2.add(con2, new org.netbeans.lib.awtextra.AbsoluteConstraints(507, 307, -1, -1));

        con1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/showeye (3).png"))); // NOI18N
        con1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        con1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                con1MousePressed(evt);
            }
        });
        jPanel2.add(con1, new org.netbeans.lib.awtextra.AbsoluteConstraints(509, 308, -1, -1));

        help.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        help.setForeground(new java.awt.Color(204, 204, 204));
        help.setText("Need Help ?");
        jPanel2.add(help, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 454, -1, -1));

        click.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        click.setForeground(new java.awt.Color(102, 255, 204));
        click.setText("Click Here");
        click.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        click.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickMouseClicked(evt);
            }
        });
        jPanel2.add(click, new org.netbeans.lib.awtextra.AbsoluteConstraints(99, 453, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 630, 480));

        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/lock.g.gif"))); // NOI18N
        getContentPane().add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, -1));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 30, 630, 50));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        try {
            frame = new JFrame("Exit");
            mh.errorBg(err);
            if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit","Attendance Login System",JOptionPane.WARNING_MESSAGE,
                    JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
                System.exit(0);
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(LoginSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void lbl_SignUpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_SignUpMouseClicked
       new SignUp().setVisible(true);
       this.dispose();

    }//GEN-LAST:event_lbl_SignUpMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        
        
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
           java.sql.Connection con=DriverManager.getConnection("jdbc:derby:C:\\Users\\mel\\AppData\\Roaming\\NetBeans\\Derby\\DB", "Admin03", "Admin03");
            java.sql.Statement stmt=con.createStatement();
            String DBQ="Select * from Admin03.LOGIN where UserId='"+txtUser.getText()+"'AND Password='"+txtPass.getText()+"'";
            
           ResultSet rs=stmt.executeQuery(DBQ);
            if(rs.next())
            {  
                mh.successBg(success);
                JOptionPane.showMessageDialog(null, "Access Granted","Attendance Login System",JOptionPane.INFORMATION_MESSAGE);
                   
                    StudentDatabase info = new StudentDatabase();
                    StudentAttendance att = new StudentAttendance();
                    this.setVisible(false);
                    att.setVisible(true);
                    info.setVisible(false);
                    
            if("Admin".equals(rs.getString("UserId")) || "UPHADMIN".equals(rs.getString("UserId")) || "ADMIN3".equals(rs.getString("UserId"))){
                
                CloseMe();
                info.setVisible(true); 
                att.setVisible(false);
                mh.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "Administrator Access, Please leave if you are Student","Attendance Login System",JOptionPane.ERROR_MESSAGE);
                    }
            }
            else if (attempt <=3){
                mh.errorBg(err);
                JOptionPane.showMessageDialog(null, "Connection Failed : Attempt " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                    txtUser.setText("");
                    txtPass.setText("");
                    txtUser.grabFocus();
            }else if(attempt == 4){
                mh.errorBg(err);
                JOptionPane.showMessageDialog(null, "Attempt Exceeded : Attempt " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                txtUser.setEditable(false);
                txtPass.setEditable(false);
                
                txtUser.setText("Attempt Excedded : Intruder Alert");
                txtUser.setForeground(Color.cyan);
                txtUser.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                Font f = new Font("Tahoma",Font.BOLD,12);
                txtUser.setFont(f);
                
                txtPass.setEchoChar((char)0);
                Font ft = new Font("Tahoma",Font.BOLD,12);
                txtPass.setFont(ft);
                txtPass.setText("Attempt Excedded : Intruder Alert");
                txtPass.setForeground(Color.cyan);
                txtPass.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                
                
            }
            else{
                MusicHelper.errorBg(err);
                JOptionPane.showMessageDialog(null, "Attempt Excedded : Attempt  " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                    txtUser.setText("");
                    txtPass.setText("");
                    txtUser.grabFocus();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.err.println(e);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(LoginSystem.class.getName()).log(Level.SEVERE, null, ex);
        }
            if(attempt == 5){
                
                new Load3().setVisible(true);
                this.dispose();
            }
            attempt++;
    }            
              private void CloseMe()
        {

            WindowEvent meClose = new WindowEvent(this, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(meClose);  
        
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelActionPerformed
        txtPass.setText(null);
        txtUser.setText(null);
        txtUser.grabFocus();
    }//GEN-LAST:event_btnCancelActionPerformed

    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        
        if(evt.getKeyCode() ==KeyEvent.VK_ENTER){
                            try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:derby:C:\\Users\\mel\\AppData\\Roaming\\NetBeans\\Derby\\DB", "Admin03", "Admin03");
            java.sql.Statement stmt=con.createStatement();
            String DBQ="Select * from Admin03.LOGIN where UserId='"+txtUser.getText()+"'AND Password='"+txtPass.getText()+"'";
            
           ResultSet rs=stmt.executeQuery(DBQ);
            if(rs.next())
            {  
                MusicHelper.successBg(success);
                JOptionPane.showMessageDialog(null, "Access Granted","Attendance Login System",JOptionPane.INFORMATION_MESSAGE);
                    
                    StudentAttendance att = new StudentAttendance();
                    this.setVisible(false);
                    StudentDatabase info = new StudentDatabase();
                    info.setVisible(false);
                    att.setVisible(true);
                    
                    
            if("Admin".equals(rs.getString("UserId")) || "UPHADMIN".equals(rs.getString("UserId")) || "ADMIN3".equals(rs.getString("UserId"))){
               
                CloseMe();
                info.setVisible(true); 
                att.setVisible(false);
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "Administrator Access, Please leave if you are Student","Attendance Login System",JOptionPane.ERROR_MESSAGE);         
                    }
            }
            else if (attempt <=3){
                MusicHelper.errorBg(err);
                JOptionPane.showMessageDialog(null, "Connection Failed : Attempt " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                    txtUser.setText("");
                    txtPass.setText("");
                    txtUser.grabFocus();
            }else if(attempt == 4){
                MusicHelper.errorBg(err);
                JOptionPane.showMessageDialog(null, "Attempt Exceeded : Attempt " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                txtUser.setEditable(false);
                txtPass.setEditable(false);
                
                txtUser.setText("Attempt Excedded : Intruder Alert");
                txtUser.setForeground(Color.cyan);
                txtUser.setHorizontalAlignment((int) CENTER_ALIGNMENT);
                Font f = new Font("Tahoma",Font.BOLD,12);
                txtUser.setFont(f);
                
                txtPass.setEchoChar((char)0);
                Font ft = new Font("Tahoma",Font.BOLD,12);
                txtPass.setFont(ft);
                txtPass.setText("Attempt Excedded : Intruder Alert");
                txtPass.setForeground(Color.cyan);
                txtPass.setHorizontalAlignment((int) CENTER_ALIGNMENT);
            }
            else{
                MusicHelper.errorBg(err);
                JOptionPane.showMessageDialog(null, "Attempt Exceeded : Attempt  " + attempt,"Attendance Login System",JOptionPane.ERROR_MESSAGE);
                    txtUser.setText("");
                    txtPass.setText("");
                    txtUser.grabFocus();
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            System.err.println(e);
        }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(LoginSystem.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(attempt == 5){
                new Load3().setVisible(true);
                this.dispose();      
            }
            attempt++;
        }    
    }//GEN-LAST:event_txtPassKeyPressed

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel3MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        setState(LoginSystem.ICONIFIED);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void con2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_con2MousePressed
        
        con1.setVisible(true);
        con2.setVisible(false);
        
        txtPass.setEchoChar('*');
        Font f = new Font("Cooper Black",Font.BOLD,17);
        txtPass.setFont(f);
    }//GEN-LAST:event_con2MousePressed

    private void con1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_con1MousePressed
       
        con2.setVisible(true);
        con1.setVisible(false);
        
        txtPass.setEchoChar((char)0);
        Font f = new Font("Traditional Arabic",Font.PLAIN,17);
        txtPass.setFont(f);
    }//GEN-LAST:event_con1MousePressed

    private void frameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMouseDragged
        
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_frameDragMouseDragged

    private void frameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_frameDragMousePressed
       
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_frameDragMousePressed

    private void txtUserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUserActionPerformed
        // TODO add your handling code here:
        
    }//GEN-LAST:event_txtUserActionPerformed

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
        
        new AboutUs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutMouseClicked

    private void clickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickMouseClicked
      
        
        new Help().setVisible(true);
        this.setVisible(false);
       
        
    }//GEN-LAST:event_clickMouseClicked

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
            java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(LoginSystem.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new LoginSystem().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel about;
    private javax.swing.JButton btnCancel;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel click;
    private javax.swing.JLabel con1;
    private javax.swing.JLabel con2;
    private javax.swing.JLabel date;
    private javax.swing.JLabel frameDrag;
    private javax.swing.JLabel help;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel lbl_SignUp;
    private javax.swing.JLabel time;
    private javax.swing.JPasswordField txtPass;
    private javax.swing.JTextField txtUser;
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


