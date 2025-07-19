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
public class Help extends javax.swing.JFrame {
    
    int xMouse;
    int yMouse;
    
    MusicHelper mh = new MusicHelper();
    
    URL sounds, err, warnings, logouts,unsus, success, attemptload;

    /**
     * Creates new form Help
     */
    public Help() {
        initComponents();
        this.setIconImage(new ImageIcon(getClass().getResource("log.png")).getImage());
        
        sounds = getClass().getResource("1lofi.wav");
        err = getClass().getResource("ERROR 1.4 SEC.wav");
        warnings = getClass().getResource("WARNING 1.1 SEC.wav");
        logouts = getClass().getResource("l_o.wav");
        unsus = getClass().getResource("unsuspicous.wav");
        success = getClass().getResource("final success.wav");
        attemptload = getClass().getResource("attemptloading.wav");
        
        
        
        Action loginAction = new AbstractAction("log") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new  LoginSystem().setVisible(true);
                dispose();
               
            }
        };
        String login = "log";
         Login.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), login);
         Login.getActionMap().put(login, loginAction);
        
        Action signupAction = new AbstractAction("sign") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new  SignUp().setVisible(true);
                dispose();
               
            }
        };
        String signup = "sign";
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), signup);
         Signup.getActionMap().put(signup, signupAction);
        
        Action homeAction = new AbstractAction("home") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new  Home().setVisible(true);
                dispose();
               
            }
        };
        String home = "home";
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_E, 0), home);
         Signup.getActionMap().put(home, homeAction);
        
         Action aboutAction = new AbstractAction("abouts") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new  AboutUs().setVisible(true);
                dispose();
               
            }
        };
        String abouts = "abouts";
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0), abouts);
         Signup.getActionMap().put(abouts, aboutAction);
         
         Action miniAction = new AbstractAction("minimize") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                setState(Help.ICONIFIED); 
            }
        };
        String minimize = "minimize";
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0), minimize);
         Signup.getActionMap().put(minimize, miniAction);
         
         Action exitAction = new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                dispose();
               
            }
        };
        String exit = "exit";
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), exit);
         Signup.getActionMap().put(exit, exitAction);
         
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
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         Signup.getActionMap().put(play, playAction); 
         
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
         Signup.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         Signup.getActionMap().put(pause, pauseAction); 
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
        Login = new javax.swing.JLabel();
        Signup = new javax.swing.JLabel();
        home = new javax.swing.JLabel();
        about = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        framedrag = new javax.swing.JLabel();
        Key = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setType(java.awt.Window.Type.POPUP);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Login.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Login.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LoginMouseClicked(evt);
            }
        });
        jPanel1.add(Login, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 7, 50, 22));

        Signup.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        Signup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                SignupMouseClicked(evt);
            }
        });
        jPanel1.add(Signup, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 7, 65, 22));

        home.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        home.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                homeMouseClicked(evt);
            }
        });
        jPanel1.add(home, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 7, 53, 22));

        about.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        about.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                aboutMouseClicked(evt);
            }
        });
        jPanel1.add(about, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 7, 53, 22));

        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jPanel1.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(570, 10, 33, 20));

        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        jPanel1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(608, 8, 30, 20));

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
        jPanel1.add(framedrag, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 650, 45));

        Key.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/Shortcut Keys.png"))); // NOI18N
        jPanel1.add(Key, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        
        this.dispose();
        
    }//GEN-LAST:event_exitMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
       
        setState(Help.ICONIFIED);
        
    }//GEN-LAST:event_minimizeMouseClicked

    private void aboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_aboutMouseClicked
       
        new AboutUs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_aboutMouseClicked

    private void homeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_homeMouseClicked
        
        new Home().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_homeMouseClicked

    private void SignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_SignupMouseClicked
       
       new SignUp().setVisible(true);
       this.dispose();
    }//GEN-LAST:event_SignupMouseClicked

    private void LoginMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LoginMouseClicked
        
        new  LoginSystem().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_LoginMouseClicked

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
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Help.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Help().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Key;
    private javax.swing.JLabel Login;
    private javax.swing.JLabel Signup;
    private javax.swing.JLabel about;
    private javax.swing.JLabel exit;
    private javax.swing.JLabel framedrag;
    private javax.swing.JLabel home;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel minimize;
    // End of variables declaration//GEN-END:variables
}
