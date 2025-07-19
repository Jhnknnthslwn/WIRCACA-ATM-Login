/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
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
import javax.swing.KeyStroke;

/**
 *
 * @author mel
 */
public class Logout extends javax.swing.JFrame implements Runnable {
    
    int hour,second,minute;
    int day,month,year;
    String timestr,datestr;
    
    int xMouse;
    int yMouse;
    
    MusicHelper mh = new MusicHelper();
    
    URL sounds, err, warnings, logouts,unsus, success, attemptload;

    /**
     * Creates new form Logout
     */
    public Logout() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("log.png")).getImage());
        
        Thread t = new Thread(this);
        t.start();
        
        sounds = getClass().getResource("1lofi.wav");
        err = getClass().getResource("ERROR 1.4 SEC.wav");
        warnings = getClass().getResource("WARNING 1.1 SEC.wav");
        logouts = getClass().getResource("l_o.wav");
        unsus = getClass().getResource("unsuspicous.wav");
        success = getClass().getResource("final success.wav");
        attemptload = getClass().getResource("attemptloading.wav");
        
        
        
        Action exitAction = new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);

            }
        };
        String exit = "exit";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), exit);
         log.getActionMap().put(exit, exitAction);
         
         Action minimizeAction = new AbstractAction("minimize") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                setState(Logout.ICONIFIED);

            }
        };
        String minimize = "minimize";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0), minimize);
         log.getActionMap().put(minimize, minimizeAction);

         
         Action loginAction = new AbstractAction("login") {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                new LoginSystem().setVisible(true);
                dispose();
               
            }
        };
        String key = "login";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), key);
         log.getActionMap().put(key, loginAction);
        
        Action signupAction = new AbstractAction("signup") {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                new SignUp().setVisible(true);
                dispose();
               
            }
        };
        String signup = "signup";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), signup);
         log.getActionMap().put(signup, signupAction);
         
         Action avoutAction = new AbstractAction("about") {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                new AboutUs().setVisible(true);
                dispose();
               
            }
        };
        String about = "about";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0), about);
         log.getActionMap().put(about, avoutAction);
         
         
         Action helpAction = new AbstractAction("help") {
            @Override
            public void actionPerformed(ActionEvent ae) {
              
                new Help().setVisible(true);
                dispose();
               
            }
        };
        String help = "help";
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_H, KeyEvent.ALT_DOWN_MASK), help);
         log.getActionMap().put(help, helpAction);   
         
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
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         log.getActionMap().put(play, playAction); 
         
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
         log.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         log.getActionMap().put(pause, pauseAction); 
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
        help = new javax.swing.JLabel();
        signup = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        login = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        date = new javax.swing.JLabel();
        time = new javax.swing.JLabel();
        framedrag = new javax.swing.JLabel();
        log = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        help.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        help.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helpMouseClicked(evt);
            }
        });
        jPanel1.add(help, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 390, 140, 70));

        signup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                signupMouseClicked(evt);
            }
        });
        jPanel1.add(signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 390, 140, 70));

        about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });
        jPanel1.add(about, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 310, 140, 70));

        login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                loginMouseClicked(evt);
            }
        });
        jPanel1.add(login, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 140, 70));

        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jPanel1.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(560, 5, 38, 30));

        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(605, 5, 40, 30));

        date.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        date.setForeground(new java.awt.Color(0, 255, 204));
        jPanel1.add(date, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 464, 130, 30));

        time.setFont(new java.awt.Font("Century Gothic", 1, 15)); // NOI18N
        time.setForeground(new java.awt.Color(0, 255, 204));
        jPanel1.add(time, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 464, 90, 30));

        framedrag.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                framedragMouseDragged(evt);
            }
        });
        framedrag.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                framedragMousePressed(evt);
            }
        });
        jPanel1.add(framedrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 35));

        log.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/final logout.png"))); // NOI18N
        jPanel1.add(log, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
      
        setState(Logout.ICONIFIED);
        
    }//GEN-LAST:event_minimizeMouseClicked

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        
        System.exit(0);
        
    }//GEN-LAST:event_exitMouseClicked

    private void loginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_loginMouseClicked
        
        new LoginSystem().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_loginMouseClicked

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
       
        new AboutUs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutMouseClicked

    private void signupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_signupMouseClicked
        
        new SignUp().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_signupMouseClicked

    private void helpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helpMouseClicked
        
        new Help().setVisible(true);
        this.dispose();
        
    }//GEN-LAST:event_helpMouseClicked

    private void framedragMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_framedragMousePressed
        
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_framedragMousePressed

    private void framedragMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_framedragMouseDragged
        
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        
        this.setLocation(x - xMouse, y - yMouse);
        
    }//GEN-LAST:event_framedragMouseDragged

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
            java.util.logging.Logger.getLogger(Logout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Logout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Logout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Logout.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Logout().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel about;
    private javax.swing.JLabel date;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel framedrag;
    private javax.swing.JLabel help;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel log;
    private javax.swing.JLabel login;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel signup;
    private javax.swing.JLabel time;
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
