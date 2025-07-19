/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;
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
public class SignUp extends javax.swing.JFrame implements Runnable{
    int hour,second,minute;
    int day,month,year;
    String timestr,datestr;
    int xMouse;
    int yMouse;
    
    MusicHelper mh = new MusicHelper();
    
    URL sounds, err, warnings, logouts,unsus, success, attemptload;

    /**
     * Creates new form SignUp
     */
    public SignUp() {
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
        
        
        Action loginAction = new AbstractAction("login") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnLoginActionPerformed(ae);
               
            }
        };
        String key = "login";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), key);
         btnLogin.getActionMap().put(key, loginAction);
         
         Action signUpAction = new AbstractAction("signup") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnSignUpActionPerformed(ae);
               
            }
        };
        String signup = "signup";
         btnLogin.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), signup);
         btnLogin.getActionMap().put(signup, signUpAction);
         
         Action cancelAction = new AbstractAction("cancel") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btncancelActionPerformed(ae);
               
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
                setState(SignUp.ICONIFIED);
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
                txtConfirm.setEchoChar((char)0);
                Font ft = new Font("Traditional Arabic",Font.PLAIN,17);
                txtConfirm.setFont(ft);
                
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
               txtConfirm.setEchoChar('*');
               Font ft = new Font("Cooper Black",Font.BOLD,17);
               txtConfirm.setFont(ft);
                
              }
        };  
         String hide = "hide";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F10, 0), hide);
         jLabel1.getActionMap().put(hide, hideAction);
         
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
                    MusicHelper.playbg1(sounds);
                } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        String play = "play";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         jLabel1.getActionMap().put(play, playAction); 
         
         Action pauseAction = new AbstractAction("pause") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    MusicHelper.bgStop();
                } catch (IOException ex) {
                    Logger.getLogger(Home.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        };
        String pause = "pause";
         jLabel1.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         jLabel1.getActionMap().put(pause, pauseAction); 
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
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        FrameDrag = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        dice = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtUser = new javax.swing.JTextField();
        txtPass = new javax.swing.JPasswordField();
        btnLogin = new javax.swing.JButton();
        btncancel = new javax.swing.JButton();
        btnExit = new javax.swing.JButton();
        txtConfirm = new javax.swing.JPasswordField();
        jLabel3 = new javax.swing.JLabel();
        btnSignUp = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        check = new javax.swing.JCheckBox();
        con2 = new javax.swing.JLabel();
        con1 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        clickme = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(1109, 540));
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

        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/cancel1.png"))); // NOI18N
        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 16, -1, -1));

        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/home.png"))); // NOI18N
        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(545, 15, -1, -1));

        about.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/in2.png"))); // NOI18N
        about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });
        jPanel1.add(about, new org.netbeans.lib.awtextra.AbsoluteConstraints(497, 15, -1, -1));

        FrameDrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                FrameDragMouseDragged(evt);
            }
        });
        FrameDrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                FrameDragMousePressed(evt);
            }
        });
        jPanel1.add(FrameDrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(-480, 0, 1110, 50));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 0, 630, 50));

        jPanel2.setBackground(new java.awt.Color(70, 70, 70));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.LOWERED, java.awt.Color.black, java.awt.Color.cyan, java.awt.Color.cyan, java.awt.Color.cyan), "Sign Up Registration", javax.swing.border.TitledBorder.LEFT, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Times New Roman", 1, 36), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dice.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/ran 35.png"))); // NOI18N
        dice.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        dice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                diceMouseClicked(evt);
            }
        });
        jPanel2.add(dice, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 235, -1, -1));

        jLabel1.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel1.setText("Username");
        jPanel2.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 180, -1, 40));

        jLabel2.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel2.setText("Password");
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 250, -1, 40));

        txtUser.setBackground(new java.awt.Color(70, 70, 70));
        txtUser.setFont(new java.awt.Font("Traditional Arabic", 0, 17)); // NOI18N
        txtUser.setForeground(new java.awt.Color(255, 255, 255));
        txtUser.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 255)));
        jPanel2.add(txtUser, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 170, 240, 40));

        txtPass.setBackground(new java.awt.Color(70, 70, 70));
        txtPass.setFont(new java.awt.Font("Cooper Black", 1, 17)); // NOI18N
        txtPass.setForeground(new java.awt.Color(255, 255, 255));
        txtPass.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 255)));
        txtPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtPassActionPerformed(evt);
            }
        });
        txtPass.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPassKeyPressed(evt);
            }
        });
        jPanel2.add(txtPass, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 240, 240, 40));

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
        jPanel2.add(btnLogin, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 415, 120, 40));

        btncancel.setBackground(new java.awt.Color(0, 153, 204));
        btncancel.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        btncancel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/back.png"))); // NOI18N
        btncancel.setText("Cancel");
        btncancel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btncancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btncancelActionPerformed(evt);
            }
        });
        jPanel2.add(btncancel, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 415, 120, 40));

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
        jPanel2.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 415, 120, 40));

        txtConfirm.setBackground(new java.awt.Color(70, 70, 70));
        txtConfirm.setFont(new java.awt.Font("Cooper Black", 1, 17)); // NOI18N
        txtConfirm.setForeground(new java.awt.Color(255, 255, 255));
        txtConfirm.setBorder(javax.swing.BorderFactory.createMatteBorder(0, 0, 2, 0, new java.awt.Color(0, 255, 255)));
        txtConfirm.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtConfirmKeyPressed(evt);
            }
        });
        jPanel2.add(txtConfirm, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 310, 240, 40));

        jLabel3.setFont(new java.awt.Font("Book Antiqua", 1, 20)); // NOI18N
        jLabel3.setText("Confirm Password");
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 320, -1, 40));

        btnSignUp.setBackground(new java.awt.Color(0, 153, 204));
        btnSignUp.setFont(new java.awt.Font("Simplified Arabic", 1, 18)); // NOI18N
        btnSignUp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/account.png"))); // NOI18N
        btnSignUp.setText("Sign Up");
        btnSignUp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnSignUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSignUpActionPerformed(evt);
            }
        });
        jPanel2.add(btnSignUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(185, 415, 130, 40));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/login.png"))); // NOI18N
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 40, -1, -1));

        check.setFont(new java.awt.Font("Trebuchet MS", 1, 13)); // NOI18N
        check.setText(" Show Password");
        check.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        check.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkActionPerformed(evt);
            }
        });
        jPanel2.add(check, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 370, -1, -1));

        con2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/Eye1 (1).png"))); // NOI18N
        con2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        con2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                con2MousePressed(evt);
            }
        });
        jPanel2.add(con2, new org.netbeans.lib.awtextra.AbsoluteConstraints(518, 243, -1, -1));

        con1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/showeye (3).png"))); // NOI18N
        con1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        con1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                con1MousePressed(evt);
            }
        });
        jPanel2.add(con1, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 243, -1, -1));

        jLabel7.setFont(new java.awt.Font("Tw Cen MT", 0, 15)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Need Help ?");
        jPanel2.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 370, 90, -1));

        clickme.setFont(new java.awt.Font("Franklin Gothic Book", 1, 14)); // NOI18N
        clickme.setForeground(new java.awt.Color(102, 255, 204));
        clickme.setText("Click Here");
        clickme.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        clickme.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                clickmeMouseClicked(evt);
            }
        });
        jPanel2.add(clickme, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 370, -1, -1));

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 60, 630, 480));

        jPanel3.setBackground(new java.awt.Color(204, 204, 204));
        jPanel3.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());
        getContentPane().add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 40, 630, 30));

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/lock.g.gif"))); // NOI18N
        getContentPane().add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 540));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
             frame = new JFrame("Exit");
        try {     
            MusicHelper.errorBg(err);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        if(JOptionPane.showConfirmDialog(frame,"Confirm if you want to exit","Attendance Login System",JOptionPane.WARNING_MESSAGE,
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_NO_OPTION){
            System.exit(0);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btncancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btncancelActionPerformed
        txtPass.setText(null);
        txtUser.setText(null);
        txtConfirm.setText(null);
        
        txtUser.grabFocus();
    }//GEN-LAST:event_btncancelActionPerformed

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        
       new LoginSystem().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_btnLoginActionPerformed

    private void btnSignUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSignUpActionPerformed
       
        try {
            String idd = txtUser.getText();
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:derby:C:\\Users\\mel\\AppData\\Roaming\\NetBeans\\Derby\\DB", "Admin03", "Admin03");
            java.sql.Statement stmt=con.createStatement();
            String DBQ="Insert into ADMIN03.LOGIN Values('"+txtUser.getText()+"','"+txtPass.getText()+"')";
            String B="SELECT * FROM LOGIN WHERE UserId='"+idd+"'";
            
            String id =txtUser.getText();
            String Password=txtPass.getText();
            String confirm=txtConfirm.getText();
            
            java.sql.ResultSet rs = stmt.executeQuery(B);
            if(rs.next())
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "This User Name already exist", " Read Me !",JOptionPane.WARNING_MESSAGE);
                txtUser.grabFocus();
            }
            
            if(id.equals(""))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "User Name is not Valid", " Read Me !",JOptionPane.WARNING_MESSAGE);
                txtUser.grabFocus();
            }else if(Password.equals("") || !confirm.equals(confirm))
            {
                MusicHelper.warningBg(warnings);
                txtPass.grabFocus();
                JOptionPane.showMessageDialog(null, "Password Typed Incorrectly", "Read Me !",JOptionPane.WARNING_MESSAGE);
            }else if(confirm.equals("") || !confirm.equals(Password))
            {
                MusicHelper.warningBg(warnings);
                txtConfirm.grabFocus();
                JOptionPane.showMessageDialog(null, "Confirm Password Typed Incorrectly", "Read Me !",JOptionPane.WARNING_MESSAGE);
            }else{
            MusicHelper.successBg(success);
            stmt.executeUpdate(DBQ);
            ClearFields();
            JOptionPane.showMessageDialog(null, "Sign Up Done");
            
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnSignUpActionPerformed

    private void txtConfirmKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtConfirmKeyPressed
        if(evt.getKeyCode() ==KeyEvent.VK_ENTER){

        try {
            String idd = txtUser.getText();
            
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            java.sql.Connection con=DriverManager.getConnection("jdbc:derby:C:\\Users\\mel\\AppData\\Roaming\\NetBeans\\Derby\\DB", "Admin03", "Admin03");
            java.sql.Statement stmt=con.createStatement();
            String DBQ="Insert into ADMIN03.LOGIN Values('"+txtUser.getText()+"','"+txtPass.getText()+"')";
            String B="SELECT * FROM LOGIN WHERE UserId='"+idd+"'";
            
            String id =txtUser.getText();
            String Password=txtPass.getText();
            String confirm=txtConfirm.getText();
            
            
            
            java.sql.ResultSet rs = stmt.executeQuery(B);
            if(rs.next())
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "This User Name already exist", " Read Me !",JOptionPane.WARNING_MESSAGE);
                txtUser.grabFocus();
            }
            if(id.equals(""))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "User Name is not Valid", " Read Me !",JOptionPane.WARNING_MESSAGE);
                txtUser.grabFocus();
            }else if(Password.equals("") || !confirm.equals(confirm))
            {
                MusicHelper.warningBg(warnings);
                ClearFields();
                JOptionPane.showMessageDialog(null, "Password Typed Incorrectly", "Read Me !",JOptionPane.WARNING_MESSAGE);
            }else if(confirm.equals("") || !confirm.equals(Password))
            {
                MusicHelper.warningBg(warnings);
                ClearFields();
                JOptionPane.showMessageDialog(null, "Confirm Password Typed Incorrectly", "Read Me !",JOptionPane.WARNING_MESSAGE);
            }else{
            MusicHelper.errorBg(err);
            stmt.executeUpdate(DBQ);
            ClearFields();
            JOptionPane.showMessageDialog(null, "Sign Up Done");
            
            }
        } catch (HeadlessException | ClassNotFoundException | SQLException e) {
            
        }   catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
                Logger.getLogger(SignUp.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_txtConfirmKeyPressed

    private void con2MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_con2MousePressed

        check.setSelected(false);
        
        
        con1.setVisible(true);
        con2.setVisible(false);

        txtPass.setEchoChar('*');
        Font f = new Font("Cooper Black",Font.BOLD,17);
        txtPass.setFont(f);
        txtConfirm.setEchoChar('*');
        Font fr = new Font("Cooper Black",Font.BOLD,17);
        txtConfirm.setFont(fr);
    }//GEN-LAST:event_con2MousePressed

    private void con1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_con1MousePressed

        check.setSelected(false);
        
        
        con2.setVisible(true);
        con1.setVisible(false);

        txtPass.setEchoChar((char)0);
        Font f = new Font("Traditional Arabic",Font.PLAIN,17);
        txtPass.setFont(f);
        txtConfirm.setEchoChar((char)0);
        Font ft = new Font("Traditional Arabic",Font.PLAIN,17);
        txtConfirm.setFont(ft);
    }//GEN-LAST:event_con1MousePressed

    private void checkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkActionPerformed

   
    
        if(check.isSelected())
        
        {
            txtPass.setEchoChar((char)0);
            Font f = new Font("Traditional Arabic",Font.PLAIN,17);
            txtPass.setFont(f);
            txtConfirm.setEchoChar((char)0);
            Font ft = new Font("Traditional Arabic",Font.PLAIN,17);
            txtConfirm.setFont(ft);
            
            
        }else{
            txtPass.setEchoChar('*');
            Font f = new Font("Cooper Black",Font.BOLD,17);
            txtPass.setFont(f);
            txtConfirm.setEchoChar('*');
            Font fr = new Font("Cooper Black",Font.BOLD,17);
            txtConfirm.setFont(fr);
            
        }      
        
    }//GEN-LAST:event_checkActionPerformed

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        setState(LoginSystem.ICONIFIED);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void FrameDragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_FrameDragMouseDragged

    private void FrameDragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_FrameDragMousePressed
       
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_FrameDragMousePressed

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
        
        new AboutUs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutMouseClicked

    private void clickmeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_clickmeMouseClicked
        
        new Help().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_clickmeMouseClicked

    private void diceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_diceMouseClicked

        check.setSelected(false);
        
        
        int len = 7;
        String validChar="OAWpnq0123456789";
        StringBuilder st = new StringBuilder();
        Random r = new Random();
        
        while(0<len--){
            st.append(validChar.charAt(r.nextInt(validChar.length())));
        }
            txtPass.setText("UPHMO"+st.toString()); 
            
    }//GEN-LAST:event_diceMouseClicked

    private void txtPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtPassActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtPassActionPerformed
final Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
    private void txtPassKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPassKeyPressed
        // TODO add your handling code here:
       if(evt.getKeyCode() ==KeyEvent.VK_C | evt.getKeyCode() ==KeyEvent.CTRL_DOWN_MASK){
        String s = txtPass.getSelectedText();
        StringSelection data = new StringSelection(s);
        clip.setContents(data, data);
       }  
    }//GEN-LAST:event_txtPassKeyPressed

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
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SignUp.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SignUp().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel FrameDrag;
    private javax.swing.JLabel about;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnLogin;
    private javax.swing.JButton btnSignUp;
    private javax.swing.JButton btncancel;
    private javax.swing.JCheckBox check;
    private javax.swing.JLabel clickme;
    private javax.swing.JLabel con1;
    private javax.swing.JLabel con2;
    private javax.swing.JLabel date;
    private javax.swing.JLabel dice;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JLabel time;
    private javax.swing.JPasswordField txtConfirm;
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

    private void ClearFields() {
        txtConfirm.setText("");
        txtPass.setText("");
        txtUser.setText("");
        txtUser.grabFocus();
    }
}
