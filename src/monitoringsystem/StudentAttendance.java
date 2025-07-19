/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package monitoringsystem;

import com.mysql.cj.jdbc.result.ResultSetMetaData;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Vector;
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
import javax.swing.JSpinner;
import javax.swing.JTable;
import javax.swing.KeyStroke;
import javax.swing.SpinnerDateModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mel
 */
public class StudentAttendance extends javax.swing.JFrame implements Runnable{
    
    private static final String username="root";
    private static final String password="";
    private static final String dataConn="jdbc:mysql://localhost:3306/studentdata";
    

    Connection sqlConn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    private Date Date;
    
    int hour,second,minute;
    int day,month,year;
    String timestr,datestr;
    
    String game = "UPHMO - ";
    
    
    MusicHelper mh = new MusicHelper();
    
    URL sounds, err, warnings, logouts,unsus, success, attemptload;

    /**
     * Creates new form StudentAttendance
     */
    public StudentAttendance() {
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
        
        
        updateDB();
        this.setIconImage(new ImageIcon(getClass().getResource("log.png")).getImage());
        
       Action addAction = new AbstractAction("add") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnAddnewActionPerformed(ae);
               
            }
        };
        String add = "add";
         btnAddnew.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F1, 0), add);
         btnAddnew.getActionMap().put(add, addAction);
         
         Action printAction = new AbstractAction("print") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnPrintActionPerformed(ae);
               
            }
        };
        String print = "print";
         btnPrint.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK), print);
         btnPrint.getActionMap().put(print, printAction);
         
         Action updateAction = new AbstractAction("updated") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnUpdateActionPerformed(ae);
               
            }
        };
        String updated = "updated";
         btnUpdate.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F2, 0), updated);
         btnUpdate.getActionMap().put(updated, updateAction);
         
         Action resetAction = new AbstractAction("reset") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                btnResetActionPerformed(ae);
               
            }
        };
        String reset = "reset";
         btnReset.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F3, 0), reset);
         btnReset.getActionMap().put(reset, resetAction);

         Action aboutAction = new AbstractAction("abouts") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                new AboutUs().setVisible(true);
                dispose();
            }
        };
        String abouts = "abouts";
         jLabel17.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F5, 0), abouts);
         jLabel17.getActionMap().put(abouts, aboutAction);
         
         Action exitsAction = new AbstractAction("exit") {
            @Override
            public void actionPerformed(ActionEvent ae) {
               System.exit(0);
              }
        };  
         String exit = "exit";
         btnReset.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_ESCAPE, 0), exit);
         btnReset.getActionMap().put(exit, exitsAction);
         
         Action homeAction = new AbstractAction("home") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                setState(StudentDatabase.ICONIFIED);
              }
        };  
         String home = "home";
         jLabel20.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F6, 0), home);
         jLabel20.getActionMap().put(home, homeAction);
         
         Action helpAction = new AbstractAction("help") {
            @Override
            public void actionPerformed(ActionEvent ae) {
                
                new Help().setVisible(true);
              }
        };  
         String help = "help";
         jLabel20.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_H, KeyEvent.ALT_DOWN_MASK), help);
         jLabel20.getActionMap().put(help, helpAction);   
         
         
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
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F11, 0), play);
         About.getActionMap().put(play, playAction); 
         
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
         About.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(KeyStroke.getKeyStroke( KeyEvent.VK_F12, 0), pause);
         About.getActionMap().put(pause, pauseAction); 
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    
    //=======================================Function Declaration=============================================
    public void updateDB()
    {
        int q,i;
                try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("select * from studentdata");
            
            rs =pst.executeQuery();
            ResultSetMetaData StData = (ResultSetMetaData) rs.getMetaData();
            
            q = StData.getColumnCount();
            
            DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
            RecordTable.setRowCount(0);
            
            while(rs.next()){
                
                Vector columnData = new Vector ();
                
                for (i = 1; i <= q; i++)
                {
                    columnData.add(rs.getString("id"));
                    columnData.add(rs.getString("studentid"));
                    columnData.add(rs.getString("Firstname"));
                    columnData.add(rs.getString("Surname"));
                    columnData.add(rs.getString("Section"));
                    columnData.add(rs.getString("Gender"));
                    columnData.add(rs.getString("Mobile"));
                    columnData.add(rs.getString("Date"));
                    columnData.add(rs.getString("TimeIn"));
                    columnData.add(rs.getString("FCL"));
                    columnData.add(rs.getString("PRACRES"));
                    columnData.add(rs.getString("EMPTECH"));
                    columnData.add(rs.getString("PHILOSOPHY"));
                    columnData.add(rs.getString("ANIMATION"));
                    columnData.add(rs.getString("PROGRAMMING1"));
                    columnData.add(rs.getString("WIRCACA"));
                    columnData.add(rs.getString("PEH"));
                    columnData.add(rs.getString("Timeout"));
                }
                    
                    RecordTable.addRow(columnData);
                    
            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        } 
        
        
    }
    
    
    
    //====================================================================================================================
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Header = new javax.swing.JPanel();
        txt_search = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        okay = new javax.swing.JLabel();
        rimuru = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        About = new javax.swing.JLabel();
        Title = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        Body = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        txtStudentID = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        txtFirstName = new javax.swing.JTextField();
        txtSurname = new javax.swing.JTextField();
        txtGrade = new javax.swing.JTextField();
        cboGENDER = new javax.swing.JComboBox<>();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jdaTe = new javax.swing.JLabel();
        txtPhone = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        cboANIMATION = new javax.swing.JComboBox<>();
        jLabel13 = new javax.swing.JLabel();
        cboCOMPROG = new javax.swing.JComboBox<>();
        jLabel14 = new javax.swing.JLabel();
        cboWIRCACA = new javax.swing.JComboBox<>();
        jLabel15 = new javax.swing.JLabel();
        cboPEH = new javax.swing.JComboBox<>();
        jPanel8 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        cboFCL = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        cboPRACRES = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        cboPHILOSOPHY = new javax.swing.JComboBox<>();
        jLabel11 = new javax.swing.JLabel();
        cboEMPTECH = new javax.swing.JComboBox<>();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        Date dates = new Date();
        SpinnerDateModel sm =
        new SpinnerDateModel(dates, null, null, Calendar.HOUR_OF_DAY);
        jSpinner1 = new javax.swing.JSpinner(sm);
        jLabel18 = new javax.swing.JLabel();
        Date = new Date();
        SpinnerDateModel sdm =
        new SpinnerDateModel(dates, null, null, Calendar.HOUR_OF_DAY);
        jSpinner2 = new javax.swing.JSpinner(sdm);
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        generate = new javax.swing.JButton();
        ForAll = new javax.swing.JLabel();
        checkAbs = new javax.swing.JCheckBox();
        checkPres = new javax.swing.JCheckBox();
        checkSick = new javax.swing.JCheckBox();
        checkExcused = new javax.swing.JCheckBox();
        jPanel5 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jPanel6 = new javax.swing.JPanel();
        btnExit = new javax.swing.JButton();
        jPanel7 = new javax.swing.JPanel();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        btnReset = new javax.swing.JButton();
        btnUpdate = new javax.swing.JButton();
        btnPrint = new javax.swing.JButton();
        btnAddnew = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Header.setBackground(new java.awt.Color(51, 51, 51));
        Header.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txt_search.setFont(new java.awt.Font("Cambria Math", 1, 14)); // NOI18N
        txt_search.setForeground(new java.awt.Color(153, 153, 153));
        txt_search.setText(" Search Database");
        txt_search.setFocusTraversalPolicyProvider(true);
        txt_search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txt_searchFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txt_searchFocusLost(evt);
            }
        });
        txt_search.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txt_searchMouseClicked(evt);
            }
        });
        txt_search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_searchKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txt_searchKeyReleased(evt);
            }
        });
        Header.add(txt_search, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 10, 200, 35));

        jLabel1.setFont(new java.awt.Font("Cambria", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/loupe.png"))); // NOI18N
        jLabel1.setText(" Search Student Attendance");
        Header.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 12, -1, -1));

        okay.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        okay.setForeground(new java.awt.Color(102, 255, 204));
        Header.add(okay, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 5, 200, 40));

        rimuru.setFont(new java.awt.Font("Century Gothic", 1, 18)); // NOI18N
        rimuru.setForeground(new java.awt.Color(102, 255, 204));
        Header.add(rimuru, new org.netbeans.lib.awtextra.AbsoluteConstraints(175, 5, 110, 40));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/cancel1.png"))); // NOI18N
        jLabel19.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel19.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel19MouseClicked(evt);
            }
        });
        Header.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(1540, 16, -1, -1));

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/home.png"))); // NOI18N
        jLabel20.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel20.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel20MouseClicked(evt);
            }
        });
        Header.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(1489, 15, -1, -1));

        jLabel21.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/RED.png"))); // NOI18N
        Header.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 15, -1, -1));

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/orange.png"))); // NOI18N
        Header.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(75, 15, -1, -1));

        jLabel23.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/Green.png"))); // NOI18N
        Header.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(125, 15, -1, -1));

        About.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/in2.png"))); // NOI18N
        About.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        About.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                AboutMouseClicked(evt);
            }
        });
        Header.add(About, new org.netbeans.lib.awtextra.AbsoluteConstraints(1430, 15, -1, -1));

        getContentPane().add(Header, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1600, 50));

        Title.setBackground(new java.awt.Color(0, 153, 153));
        Title.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        Title.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(102, 102, 102));
        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, null, java.awt.Color.gray, java.awt.Color.white, java.awt.Color.gray));

        jLabel5.setFont(new java.awt.Font("Times New Roman", 1, 48)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("  DAILY STUDENT ATTENDANCE  MONITORING  SYSTEMS");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addContainerGap(52, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        Title.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 10, 1440, 63));

        getContentPane().add(Title, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 1600, 80));

        Body.setBackground(new java.awt.Color(102, 102, 102));
        Body.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        Body.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 158, 160), 4));
        jPanel4.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        txtStudentID.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        txtStudentID.setForeground(new java.awt.Color(153, 153, 153));
        txtStudentID.setText(" Enter Student ID");
        txtStudentID.setToolTipText("Student ID");
        txtStudentID.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtStudentID.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtStudentIDFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtStudentIDFocusLost(evt);
            }
        });
        txtStudentID.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtStudentIDKeyPressed(evt);
            }
        });
        jPanel4.add(txtStudentID, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 23, 170, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Student ID");
        jPanel4.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 23, -1, 30));

        txtFirstName.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        txtFirstName.setForeground(new java.awt.Color(153, 153, 153));
        txtFirstName.setText(" Enter Student Firstname");
        txtFirstName.setToolTipText("Student First Name");
        txtFirstName.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtFirstNameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtFirstNameFocusLost(evt);
            }
        });
        txtFirstName.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtFirstNameKeyPressed(evt);
            }
        });
        jPanel4.add(txtFirstName, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 65, 260, -1));

        txtSurname.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        txtSurname.setForeground(new java.awt.Color(153, 153, 153));
        txtSurname.setText(" Enter Student Surname");
        txtSurname.setToolTipText("Student Surname");
        txtSurname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtSurnameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtSurnameFocusLost(evt);
            }
        });
        txtSurname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtSurnameKeyPressed(evt);
            }
        });
        jPanel4.add(txtSurname, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 108, 260, -1));

        txtGrade.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        txtGrade.setForeground(new java.awt.Color(153, 153, 153));
        txtGrade.setText(" Enter Student Section");
        txtGrade.setToolTipText(" Student Section");
        txtGrade.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtGradeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtGradeFocusLost(evt);
            }
        });
        txtGrade.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtGradeKeyPressed(evt);
            }
        });
        jPanel4.add(txtGrade, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 260, -1));

        cboGENDER.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboGENDER.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Choose", "Male", "Female" }));
        cboGENDER.setToolTipText("Student Gender");
        cboGENDER.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboGENDER.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboGENDERActionPerformed(evt);
            }
        });
        cboGENDER.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cboGENDERKeyPressed(evt);
            }
        });
        jPanel4.add(cboGENDER, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, -1, 30));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setText("First Name");
        jPanel4.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 65, -1, 30));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setText("Surname");
        jPanel4.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 110, -1, 30));

        jdaTe.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jdaTe.setText("Date");
        jPanel4.add(jdaTe, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 190, 40, 30));

        txtPhone.setFont(new java.awt.Font("Traditional Arabic", 0, 18)); // NOI18N
        txtPhone.setForeground(new java.awt.Color(153, 153, 153));
        txtPhone.setText(" Enter Phone No.");
        txtPhone.setToolTipText("Student Phone Number");
        txtPhone.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtPhoneFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtPhoneFocusLost(evt);
            }
        });
        txtPhone.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtPhoneKeyPressed(evt);
            }
        });
        jPanel4.add(txtPhone, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 260, -1));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setText("Gender");
        jPanel4.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 190, -1, 30));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setText("Grade & Section");
        jPanel4.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 150, 140, 30));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel12.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel12.setText("ANIMATION");
        jPanel1.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 20, -1, 30));

        cboANIMATION.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboANIMATION.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboANIMATION.setToolTipText("Pick Student Attendance");
        cboANIMATION.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboANIMATION.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboANIMATIONActionPerformed(evt);
            }
        });
        jPanel1.add(cboANIMATION, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 20, 80, 30));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel13.setText("COMPROG");
        jPanel1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 80, -1, 30));

        cboCOMPROG.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboCOMPROG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboCOMPROG.setToolTipText("Pick Student Attendance");
        cboCOMPROG.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboCOMPROG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboCOMPROGActionPerformed(evt);
            }
        });
        jPanel1.add(cboCOMPROG, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 80, 30));

        jLabel14.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel14.setText("WIRCACA");
        jPanel1.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 140, -1, 30));

        cboWIRCACA.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboWIRCACA.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboWIRCACA.setToolTipText("Pick Student Attendance");
        cboWIRCACA.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboWIRCACA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboWIRCACAActionPerformed(evt);
            }
        });
        jPanel1.add(cboWIRCACA, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 140, 80, 30));

        jLabel15.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel15.setText("PEH");
        jPanel1.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(12, 200, -1, 30));

        cboPEH.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboPEH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboPEH.setToolTipText("Pick Student Attendance");
        cboPEH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboPEH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPEHActionPerformed(evt);
            }
        });
        jPanel1.add(cboPEH, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 200, 80, 30));

        jPanel4.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 370, 200, 260));

        jPanel8.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel8.setText("FCL");
        jPanel8.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 20, -1, 30));

        cboFCL.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboFCL.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboFCL.setToolTipText("Pick Student Attendance");
        cboFCL.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboFCL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFCLActionPerformed(evt);
            }
        });
        jPanel8.add(cboFCL, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 20, 80, 30));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel9.setText("PRACRES");
        jPanel8.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 80, -1, 30));

        cboPRACRES.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboPRACRES.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboPRACRES.setToolTipText("Pick Student Attendance");
        cboPRACRES.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboPRACRES.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPRACRESActionPerformed(evt);
            }
        });
        jPanel8.add(cboPRACRES, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 80, 80, 30));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel10.setText("PHILOSOPHY");
        jPanel8.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 200, -1, 30));

        cboPHILOSOPHY.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboPHILOSOPHY.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboPHILOSOPHY.setToolTipText("Pick Student Attendance");
        cboPHILOSOPHY.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboPHILOSOPHY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboPHILOSOPHYActionPerformed(evt);
            }
        });
        jPanel8.add(cboPHILOSOPHY, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 200, 80, 30));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        jLabel11.setText("EMPTECH");
        jPanel8.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 140, -1, 30));

        cboEMPTECH.setFont(new java.awt.Font("Traditional Arabic", 1, 15)); // NOI18N
        cboEMPTECH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { " ", "Present", "Absent ", "Excused", "Sick" }));
        cboEMPTECH.setToolTipText("Pick Student Attendance");
        cboEMPTECH.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cboEMPTECH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboEMPTECHActionPerformed(evt);
            }
        });
        jPanel8.add(cboEMPTECH, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 140, 80, 30));

        jPanel4.add(jPanel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 370, 210, 260));

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel16.setText("Time Out");
        jPanel4.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 329, -1, 30));

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel17.setText("Phone Number");
        jPanel4.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 230, -1, 30));

        JSpinner.DateEditor de = new JSpinner.DateEditor(jSpinner1, "        hh:mm aa      ");
        jSpinner1.setEditor(de);
        jSpinner1.setToolTipText("Student Time In");
        jSpinner1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(jSpinner1, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 280, 130, 30));

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel18.setText("Time In");
        jPanel4.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(15, 280, -1, 30));

        JSpinner.DateEditor sde = new JSpinner.DateEditor(jSpinner2,"        hh:mm aa");
        jSpinner2.setEditor(sde);
        jSpinner2.setToolTipText("Student Time Out");
        jSpinner2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jPanel4.add(jSpinner2, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 329, 130, 30));

        jDateChooser1.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        jPanel4.add(jDateChooser1, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 190, 140, 30));

        generate.setBackground(new java.awt.Color(0, 102, 102));
        generate.setFont(new java.awt.Font("Traditional Arabic", 1, 14)); // NOI18N
        generate.setForeground(new java.awt.Color(255, 255, 255));
        generate.setText("Generate");
        generate.setToolTipText("Generate Today's ID");
        generate.setBorderPainted(false);
        generate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        generate.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });
        jPanel4.add(generate, new org.netbeans.lib.awtextra.AbsoluteConstraints(338, 25, 90, 33));

        ForAll.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ForAll.setText("Check if applied :");
        jPanel4.add(ForAll, new org.netbeans.lib.awtextra.AbsoluteConstraints(275, 280, -1, -1));

        checkAbs.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkAbs.setText("Absent");
        checkAbs.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkAbs.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkAbsMouseClicked(evt);
            }
        });
        checkAbs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkAbsActionPerformed(evt);
            }
        });
        jPanel4.add(checkAbs, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 308, -1, -1));

        checkPres.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkPres.setText("Present");
        checkPres.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkPres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkPresMouseClicked(evt);
            }
        });
        checkPres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkPresActionPerformed(evt);
            }
        });
        jPanel4.add(checkPres, new org.netbeans.lib.awtextra.AbsoluteConstraints(269, 338, -1, -1));

        checkSick.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkSick.setText("Sick");
        checkSick.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkSick.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkSickMouseClicked(evt);
            }
        });
        checkSick.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkSickActionPerformed(evt);
            }
        });
        jPanel4.add(checkSick, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 308, -1, -1));

        checkExcused.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        checkExcused.setText("Excused");
        checkExcused.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        checkExcused.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                checkExcusedMouseClicked(evt);
            }
        });
        checkExcused.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkExcusedActionPerformed(evt);
            }
        });
        jPanel4.add(checkExcused, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 338, -1, -1));

        Body.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 13, 440, 645));

        jPanel5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 158, 160), 4));
        jPanel5.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jTable1.setFont(new java.awt.Font("Times New Roman", 0, 14)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "STUDENT ID", "FIRST NAME", "SURNAME", "Grade & Section", "GENDER", "PHONE NO.", "Date", "Time In", "FCL", "PRACRES", "EMPTECH", "PHILOSOPHY", "ANIMATION", "PROGRAMMING", "WIRCACA", "PEH", "Time Out"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class, java.lang.Object.class, java.lang.Object.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jTable1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jTable1.setRowHeight(26);
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jTable1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                jTable1KeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel5.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 10, 1110, 630));

        Body.add(jPanel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 13, 1130, 645));

        jPanel6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 158, 160), 4));
        jPanel6.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnExit.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        btnExit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/exit 55.png"))); // NOI18N
        btnExit.setText("Logout");
        btnExit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExitActionPerformed(evt);
            }
        });
        jPanel6.add(btnExit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1300, 11, 230, 70));

        jPanel7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(95, 158, 160), 4));
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton3.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        jButton3.setText("Exit");
        jPanel7.add(jButton3, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 10, 190, -1));

        jButton4.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        jButton4.setText("Exit");
        jPanel7.add(jButton4, new org.netbeans.lib.awtextra.AbsoluteConstraints(1210, 10, 190, -1));

        jPanel6.add(jPanel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 600, 1410, 70));

        btnReset.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        btnReset.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/reset 55.png"))); // NOI18N
        btnReset.setText("Reset");
        btnReset.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetActionPerformed(evt);
            }
        });
        jPanel6.add(btnReset, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 11, 230, 70));

        btnUpdate.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        btnUpdate.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/update 55.png"))); // NOI18N
        btnUpdate.setText("Update");
        btnUpdate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });
        jPanel6.add(btnUpdate, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 11, 230, 70));

        btnPrint.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        btnPrint.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/print 80.png"))); // NOI18N
        btnPrint.setText("Print");
        btnPrint.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnPrint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrintActionPerformed(evt);
            }
        });
        jPanel6.add(btnPrint, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, 11, 240, 70));

        btnAddnew.setFont(new java.awt.Font("Tahoma", 1, 35)); // NOI18N
        btnAddnew.setIcon(new javax.swing.ImageIcon(getClass().getResource("/monitoringsystem/icons/png/add 55.png"))); // NOI18N
        btnAddnew.setText("Add New");
        btnAddnew.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnAddnew.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddnewActionPerformed(evt);
            }
        });
        jPanel6.add(btnAddnew, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 11, 260, 70));

        Body.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 670, 1580, 90));

        getContentPane().add(Body, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 130, 1600, 770));

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void txt_searchFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusGained

        if (txt_search.getText().equals(" Search Database"))
        {

            txt_search.setText("");
            txt_search.setForeground(new Color(51,51,51));

        }
    }//GEN-LAST:event_txt_searchFocusGained

    private void txt_searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txt_searchFocusLost

        if (txt_search.getText().equals(""))
        {

            txt_search.setText(" Search Database");
            txt_search.setForeground(new Color(153,153,153));
        }
    }//GEN-LAST:event_txt_searchFocusLost

    private void txt_searchMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txt_searchMouseClicked

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
        checkSick.setSelected(false);

        clearFields();

        String quer = txt_search.getText();
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

    }//GEN-LAST:event_txt_searchMouseClicked

    private void txt_searchKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyPressed

        switch(evt.getKeyCode())

        {
            case 40:txtStudentID.requestFocus();
            txtStudentID.setForeground(new Color (0,0,0));
            break;

        }
    }//GEN-LAST:event_txt_searchKeyPressed

    private void txt_searchKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_searchKeyReleased

        String quer = txt_search.getText();
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
    }//GEN-LAST:event_txt_searchKeyReleased

    private void jLabel19MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel19MouseClicked
        System.exit(0);
    }//GEN-LAST:event_jLabel19MouseClicked

    private void jLabel20MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel20MouseClicked
        setState(LoginSystem.ICONIFIED);
    }//GEN-LAST:event_jLabel20MouseClicked

    private void AboutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_AboutMouseClicked
        new AboutUs().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_AboutMouseClicked

    private void txtStudentIDFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStudentIDFocusGained

        if (txtStudentID.getText().equals(" Enter Student ID"))
        {

            txtStudentID.setText("");
            txtStudentID.setForeground(new Color(0,0,0));

        }
    }//GEN-LAST:event_txtStudentIDFocusGained

    private void txtStudentIDFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtStudentIDFocusLost

        if (txtStudentID.getText().equals(""))
        {

            txtStudentID.setText(" Enter Student ID");
            txtStudentID.setForeground(new Color(153,153,153));

        }
    }//GEN-LAST:event_txtStudentIDFocusLost

    private void txtStudentIDKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtStudentIDKeyPressed

        char c = evt.getKeyChar();

        if(Character.isLetterOrDigit(c) || Character.isWhitespace(c) || Character.isISOControl(c))
        {
            txtStudentID.setEditable(true);

        }else{
            txtFirstName.setEditable(false);
        }

        switch(evt.getKeyCode())

        {
            case 40:txtFirstName.requestFocus();
            txtFirstName.setForeground(new Color (0,0,0));
            break;

            case 38:txt_search.requestFocus();
            txt_search.setForeground(new Color (0,0,0));
            break;

        }
    }//GEN-LAST:event_txtStudentIDKeyPressed

    private void txtFirstNameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameFocusGained

        if (txtFirstName.getText().equals(" Enter Student Firstname"))
        {

            txtFirstName.setText("");
            txtFirstName.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txtFirstNameFocusGained

    private void txtFirstNameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtFirstNameFocusLost

        if (txtFirstName.getText().equals(""))
        {

            txtFirstName.setText(" Enter Student Firstname");
            txtFirstName.setForeground(new Color(153,153,153));

        }
    }//GEN-LAST:event_txtFirstNameFocusLost

    private void txtFirstNameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtFirstNameKeyPressed

        char c = evt.getKeyChar();

        if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))
        {
            txtFirstName.setEditable(true);

        }else{
            txtFirstName.setEditable(false);
        }

        switch(evt.getKeyCode())

        {
            case 40:txtSurname.requestFocus();
            txtSurname.setForeground(new Color (0,0,0));
            break;

            case 38:txtStudentID.requestFocus();
            txtStudentID.setForeground(new Color (0,0,0));
            break;

        }
    }//GEN-LAST:event_txtFirstNameKeyPressed

    private void txtSurnameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurnameFocusGained

        if (txtSurname.getText().equals(" Enter Student Surname"))
        {

            txtSurname.setText("");
            txtSurname.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txtSurnameFocusGained

    private void txtSurnameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtSurnameFocusLost

        if (txtSurname.getText().equals(""))
        {

            txtSurname.setText(" Enter Student Surname");
            txtSurname.setForeground(new Color(153,153,153));

        }
    }//GEN-LAST:event_txtSurnameFocusLost

    private void txtSurnameKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtSurnameKeyPressed

        char c = evt.getKeyChar();

        if(Character.isLetter(c) || Character.isWhitespace(c) || Character.isISOControl(c))
        {
            txtSurname.setEditable(true);

        }else{
            txtSurname.setEditable(false);
        }

        switch(evt.getKeyCode())

        {
            case 40:txtGrade.requestFocus();
            txtGrade.setForeground(new Color (0,0,0));
            break;

            case 38:txtFirstName.requestFocus();
            txtFirstName.setForeground(new Color (0,0,0));
            break;

        }
    }//GEN-LAST:event_txtSurnameKeyPressed

    private void txtGradeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradeFocusGained

        if (txtGrade.getText().equals(" Enter Student Section"))
        {

            txtGrade.setText("");
            txtGrade.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txtGradeFocusGained

    private void txtGradeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtGradeFocusLost

        if (txtGrade.getText().equals(""))
        {

            txtGrade.setText(" Enter Student Section");
            txtGrade.setForeground(new Color(153,153,153));

        }
    }//GEN-LAST:event_txtGradeFocusLost

    private void txtGradeKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtGradeKeyPressed

        switch(evt.getKeyCode())

        {
            case 40:txtPhone.requestFocus();
            cboGENDER.setForeground(new Color (0,0,0));
            break;

            case 38:txtSurname.requestFocus();
            txtSurname.setForeground(new Color (0,0,0));
            break;

        }
    }//GEN-LAST:event_txtGradeKeyPressed

    private void cboGENDERActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboGENDERActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboGENDERActionPerformed

    private void cboGENDERKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cboGENDERKeyPressed

    }//GEN-LAST:event_cboGENDERKeyPressed

    private void txtPhoneFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneFocusGained

        if (txtPhone.getText().equals(" Enter Phone No."))
        {

            txtPhone.setText("");
            txtPhone.setForeground(new Color(0,0,0));
        }
    }//GEN-LAST:event_txtPhoneFocusGained

    private void txtPhoneFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtPhoneFocusLost

        if (txtPhone.getText().equals(""))
        {

            txtPhone.setText(" Enter Phone No.");
            txtPhone.setForeground(new Color(153,153,153));

        }
    }//GEN-LAST:event_txtPhoneFocusLost

    private void txtPhoneKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtPhoneKeyPressed

        String phoneNumber = txtPhone.getText();
        int length = phoneNumber.length();

        char c = evt.getKeyChar();
        if (evt.getKeyChar()>='0' && evt.getKeyChar()<='9')
        {
            if(length<20){
                txtPhone.setEditable(true);
            }else{
                txtPhone.setEditable(false);

            }
        }else
        {
            if(evt.getExtendedKeyCode()==KeyEvent.VK_BACK_SPACE || evt.getExtendedKeyCode()==KeyEvent.VK_DELETE)
            {
                txtPhone.setEditable(true);
            }else{
                txtPhone.setEditable(false);
            }
        }
        if(evt.getKeyCode()== 38){
            txtGrade.requestFocus();
        }

    }//GEN-LAST:event_txtPhoneKeyPressed

    private void cboANIMATIONActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboANIMATIONActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboANIMATIONActionPerformed

    private void cboCOMPROGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboCOMPROGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboCOMPROGActionPerformed

    private void cboWIRCACAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboWIRCACAActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboWIRCACAActionPerformed

    private void cboPEHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPEHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPEHActionPerformed

    private void cboFCLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFCLActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboFCLActionPerformed

    private void cboPRACRESActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPRACRESActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPRACRESActionPerformed

    private void cboPHILOSOPHYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboPHILOSOPHYActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboPHILOSOPHYActionPerformed

    private void cboEMPTECHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboEMPTECHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboEMPTECHActionPerformed

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed

        java.util.Random r = new  java.util.Random();
        int start = 300000;
        int end = 9000000;
        int result = r.nextInt(end - start)+start;
        txtStudentID.setText(String.valueOf(game +result));
        txtStudentID.setForeground(Color.BLACK);

    }//GEN-LAST:event_generateActionPerformed

    private void checkAbsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkAbsMouseClicked

        checkExcused.setSelected(false);
        checkSick.setSelected(false);
        checkPres.setSelected(false);
    }//GEN-LAST:event_checkAbsMouseClicked

    private void checkAbsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkAbsActionPerformed

        if(checkAbs.isSelected()){
            cboFCL.setSelectedIndex(2);
            cboPRACRES.setSelectedIndex(2);
            cboEMPTECH.setSelectedIndex(2);
            cboPHILOSOPHY.setSelectedIndex(2);
            cboANIMATION.setSelectedIndex(2);
            cboCOMPROG.setSelectedIndex(2);
            cboWIRCACA.setSelectedIndex(2);
            cboPEH.setSelectedIndex(2);

            Date def = null;
            SimpleDateFormat formatterDefault = new SimpleDateFormat("HH:mm:ss");
            try{
                def = formatterDefault.parse("00:00:00");
            }catch(ParseException ex){

            }
            jSpinner1.setValue(def);
            jSpinner2.setValue(def);

        }else{
            cboFCL.setSelectedIndex(0);
            cboPRACRES.setSelectedIndex(0);
            cboEMPTECH.setSelectedIndex(0);
            cboPHILOSOPHY.setSelectedIndex(0);
            cboANIMATION.setSelectedIndex(0);
            cboCOMPROG.setSelectedIndex(0);
            cboWIRCACA.setSelectedIndex(0);
            cboPEH.setSelectedIndex(0);
        }
    }//GEN-LAST:event_checkAbsActionPerformed

    private void checkPresMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkPresMouseClicked

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkSick.setSelected(false);
    }//GEN-LAST:event_checkPresMouseClicked

    private void checkPresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkPresActionPerformed

        if(checkPres.isSelected()){
            cboFCL.setSelectedIndex(1);
            cboPRACRES.setSelectedIndex(1);
            cboEMPTECH.setSelectedIndex(1);
            cboPHILOSOPHY.setSelectedIndex(1);
            cboANIMATION.setSelectedIndex(1);
            cboCOMPROG.setSelectedIndex(1);
            cboWIRCACA.setSelectedIndex(1);
            cboPEH.setSelectedIndex(1);
        }else{
            cboFCL.setSelectedIndex(0);
            cboPRACRES.setSelectedIndex(0);
            cboEMPTECH.setSelectedIndex(0);
            cboPHILOSOPHY.setSelectedIndex(0);
            cboANIMATION.setSelectedIndex(0);
            cboCOMPROG.setSelectedIndex(0);
            cboWIRCACA.setSelectedIndex(0);
            cboPEH.setSelectedIndex(0);
        }
    }//GEN-LAST:event_checkPresActionPerformed

    private void checkSickMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkSickMouseClicked

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
    }//GEN-LAST:event_checkSickMouseClicked

    private void checkSickActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkSickActionPerformed

        if(checkSick.isSelected()){
            cboFCL.setSelectedIndex(4);
            cboPRACRES.setSelectedIndex(4);
            cboEMPTECH.setSelectedIndex(4);
            cboPHILOSOPHY.setSelectedIndex(4);
            cboANIMATION.setSelectedIndex(4);
            cboCOMPROG.setSelectedIndex(4);
            cboWIRCACA.setSelectedIndex(4);
            cboPEH.setSelectedIndex(4);

            Date def = null;
            SimpleDateFormat formatterDefault = new SimpleDateFormat("HH:mm:ss");
            try{
                def = formatterDefault.parse("00:00:00");
            }catch(ParseException ex){

            }
            jSpinner1.setValue(def);
            jSpinner2.setValue(def);

        }else{
            cboFCL.setSelectedIndex(0);
            cboPRACRES.setSelectedIndex(0);
            cboEMPTECH.setSelectedIndex(0);
            cboPHILOSOPHY.setSelectedIndex(0);
            cboANIMATION.setSelectedIndex(0);
            cboCOMPROG.setSelectedIndex(0);
            cboWIRCACA.setSelectedIndex(0);
            cboPEH.setSelectedIndex(0);
        }
    }//GEN-LAST:event_checkSickActionPerformed

    private void checkExcusedMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_checkExcusedMouseClicked

        checkSick.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
    }//GEN-LAST:event_checkExcusedMouseClicked

    private void checkExcusedActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_checkExcusedActionPerformed

        if(checkExcused.isSelected()){
            cboFCL.setSelectedIndex(3);
            cboPRACRES.setSelectedIndex(3);
            cboEMPTECH.setSelectedIndex(3);
            cboPHILOSOPHY.setSelectedIndex(3);
            cboANIMATION.setSelectedIndex(3);
            cboCOMPROG.setSelectedIndex(3);
            cboWIRCACA.setSelectedIndex(3);
            cboPEH.setSelectedIndex(3);
        }else{
            cboFCL.setSelectedIndex(0);
            cboPRACRES.setSelectedIndex(0);
            cboEMPTECH.setSelectedIndex(0);
            cboPHILOSOPHY.setSelectedIndex(0);
            cboANIMATION.setSelectedIndex(0);
            cboCOMPROG.setSelectedIndex(0);
            cboWIRCACA.setSelectedIndex(0);
            cboPEH.setSelectedIndex(0);
        }
    }//GEN-LAST:event_checkExcusedActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
        checkSick.setSelected(false);

        txt_search.setText("");

        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        int SelectedRows = jTable1.getSelectedRow();

        txtStudentID.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
        txtStudentID.setForeground(new Color(0,0,0));

        txtFirstName.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
        txtFirstName.setForeground(new Color(0,0,0));

        txtSurname.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
        txtSurname.setForeground(new Color(0,0,0));

        txtGrade.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
        txtGrade.setForeground(new Color(0,0,0));

        cboGENDER.setSelectedItem(RecordTable.getValueAt(SelectedRows, 5).toString());

        txtPhone.setText(RecordTable.getValueAt(SelectedRows, 6).toString());
        txtPhone.setForeground(new Color(0,0,0));

        cboFCL.setSelectedItem(RecordTable.getValueAt(SelectedRows, 9).toString());
        cboPRACRES.setSelectedItem(RecordTable.getValueAt(SelectedRows, 10).toString());
        cboEMPTECH.setSelectedItem(RecordTable.getValueAt(SelectedRows, 11).toString());
        cboPHILOSOPHY.setSelectedItem(RecordTable.getValueAt(SelectedRows, 12).toString());
        cboANIMATION.setSelectedItem(RecordTable.getValueAt(SelectedRows, 13).toString());
        cboCOMPROG.setSelectedItem(RecordTable.getValueAt(SelectedRows, 14).toString());
        cboWIRCACA.setSelectedItem(RecordTable.getValueAt(SelectedRows, 15).toString());
        cboPEH.setSelectedItem(RecordTable.getValueAt(SelectedRows, 16).toString());
        try{
            int srow =jTable1.getSelectedRow();
            Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)RecordTable.getValueAt(srow, 7));
            jDateChooser1.setDate(date);

        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);
        }

        try{
            int row = jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 8).toString());
            String sql = "SELECT * FROM studentdata WHERE TimeIn='"+table_click+"'";
            pst=sqlConn.prepareStatement(sql);
            rs=pst.executeQuery();

            if(rs.next()){

                // I GUESS THIS GIVES THE ERROR
                Object value = rs.getTime("TimeIn"); // "time" is the column name from the database

                jSpinner1.setValue(value);

            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
        try{
            int row = jTable1.getSelectedRow();
            String table_click=(jTable1.getModel().getValueAt(row, 17).toString());
            String sql = "SELECT * FROM studentdata WHERE Timeout='"+table_click+"'";
            pst=sqlConn.prepareStatement(sql);
            rs=pst.executeQuery();

            if(rs.next()){

                // I GUESS THIS GIVES THE ERROR
                Object value = rs.getTime("Timeout"); // "time" is the column name from the database

                jSpinner2.setValue(value);

            }
        }
        catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex);

        }
    }//GEN-LAST:event_jTable1MouseClicked

    private void jTable1KeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jTable1KeyReleased
        if(evt.getKeyCode()==KeyEvent.VK_DOWN || evt.getKeyCode()==KeyEvent.VK_UP )

        {
            DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
            int SelectedRows = jTable1.getSelectedRow();

            txtStudentID.setText(RecordTable.getValueAt(SelectedRows, 1).toString());
            txtStudentID.setForeground(new Color(0,0,0));

            txtFirstName.setText(RecordTable.getValueAt(SelectedRows, 2).toString());
            txtFirstName.setForeground(new Color(0,0,0));

            txtSurname.setText(RecordTable.getValueAt(SelectedRows, 3).toString());
            txtSurname.setForeground(new Color(0,0,0));

            txtGrade.setText(RecordTable.getValueAt(SelectedRows, 4).toString());
            txtGrade.setForeground(new Color(0,0,0));

            cboGENDER.setSelectedItem(RecordTable.getValueAt(SelectedRows, 5).toString());

            txtPhone.setText(RecordTable.getValueAt(SelectedRows, 6).toString());
            txtPhone.setForeground(new Color(0,0,0));

            try{
                int srow =jTable1.getSelectedRow();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse((String)RecordTable.getValueAt(srow, 7));
                jDateChooser1.setDate(date);

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            try{
                int row = jTable1.getSelectedRow();
                String table_click=(jTable1.getModel().getValueAt(row, 8).toString());
                String sql = "SELECT * FROM studentdata WHERE TimeIn='"+table_click+"'";
                pst=sqlConn.prepareStatement(sql);
                rs=pst.executeQuery();

                if(rs.next()){

                    // I GUESS THIS GIVES THE ERROR
                    Object value = rs.getTime("TimeIn"); // "time" is the column name from the database

                    jSpinner1.setValue(value);

                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            cboFCL.setSelectedItem(RecordTable.getValueAt(SelectedRows, 9).toString());
            cboPRACRES.setSelectedItem(RecordTable.getValueAt(SelectedRows, 10).toString());
            cboEMPTECH.setSelectedItem(RecordTable.getValueAt(SelectedRows, 11).toString());
            cboPHILOSOPHY.setSelectedItem(RecordTable.getValueAt(SelectedRows, 12).toString());
            cboANIMATION.setSelectedItem(RecordTable.getValueAt(SelectedRows, 13).toString());
            cboCOMPROG.setSelectedItem(RecordTable.getValueAt(SelectedRows, 14).toString());
            cboWIRCACA.setSelectedItem(RecordTable.getValueAt(SelectedRows, 15).toString());
            cboPEH.setSelectedItem(RecordTable.getValueAt(SelectedRows, 16).toString());

            try{
                int row = jTable1.getSelectedRow();
                String table_click=(jTable1.getModel().getValueAt(row, 17).toString());
                String sql = "SELECT * FROM studentdata WHERE Timeout='"+table_click+"'";
                pst=sqlConn.prepareStatement(sql);
                rs=pst.executeQuery();

                if(rs.next()){

                    // I GUESS THIS GIVES THE ERROR
                    Object value = rs.getTime("Timeout"); // "time" is the column name from the database

                    jSpinner2.setValue(value);

                }
            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
        }
    }//GEN-LAST:event_jTable1KeyReleased
private JFrame frame;
    private void btnExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnExitActionPerformed
        try {
           
            
            frame = new JFrame("Exit");
            MusicHelper.errorBg(err);
            if (JOptionPane.showConfirmDialog(frame,"Confirm if you want to Logout","Database Management Systems",JOptionPane.WARNING_MESSAGE,
                    JOptionPane.YES_NO_OPTION)==JOptionPane.YES_NO_OPTION){
                logLoad l = new logLoad();
                l.setVisible(true);
                this.dispose();
            }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(StudentAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnExitActionPerformed

    private void btnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetActionPerformed

        txtStudentID.setText(" Enter Student ID");
        txtStudentID.setForeground(new Color(153,153,153));

        txtFirstName.setText(" Enter Student Firstname");
        txtFirstName.setForeground(new Color(153,153,153));

        txtSurname.setText(" Enter Student Surname");
        txtSurname.setForeground(new Color(153,153,153));

        txtGrade.setText(" Enter Student Section");
        txtGrade.setForeground(new Color(153,153,153));

        cboGENDER.setSelectedIndex(0);

        txtPhone.setText(" Enter Phone No.");
        txtPhone.setForeground(new Color(153,153,153));

        jDateChooser1.actionPerformed(evt);
        jDateChooser1.setCalendar(null);
        cboFCL.setSelectedIndex(0);
        cboPRACRES.setSelectedIndex(0);
        cboEMPTECH.setSelectedIndex(0);
        cboPHILOSOPHY.setSelectedIndex(0);
        cboANIMATION.setSelectedIndex(0);
        cboCOMPROG.setSelectedIndex(0);
        cboWIRCACA.setSelectedIndex(0);
        cboPEH.setSelectedIndex(0);
        
        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
        checkSick.setSelected(false);
        

        txt_search.setText(" Search Database");
        txt_search.setForeground(new Color(153,153,153));

        Date def = null;
        SimpleDateFormat formatterDefault = new SimpleDateFormat("HH:mm:ss");

        try{
            def = formatterDefault.parse("00:00:00");
        }catch(ParseException ex){
        }

        jSpinner1.setValue(def);
        jSpinner2.setValue(def);

        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        RecordTable.setRowCount(0);
    }//GEN-LAST:event_btnResetActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
        checkSick.setSelected(false);
        
        
        DefaultTableModel RecordTable = (DefaultTableModel)jTable1.getModel();
        int SelectedRows = jTable1.getSelectedRow();

        try
        {
            int id = Integer.parseInt(RecordTable.getValueAt(SelectedRows, 0).toString());

            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("update studentdata set studentid =?,Firstname=?,"
                + "Surname =?,Section =?,Gender =?,Mobile =?,Date =?,TimeIn =?,FCL =?,PRACRES =?,EMPTECH =?,PHILOSOPHY =?,"
                + "ANIMATION =?,PROGRAMMING1 =?,WIRCACA =?,PEH =?,Timeout =? where id =?");
            pst.setString(1,txtStudentID.getText());
            pst.setString(2,txtFirstName.getText());
            pst.setString(3,txtSurname.getText());
            pst.setString(4,txtGrade.getText());
            pst.setString(5,(String) cboGENDER.getSelectedItem());
            pst.setString(6,txtPhone.getText());
            pst.setString(7,(String) jDateChooser1.getDateFormatString());

            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String d = null;
            String date;
            date = d;
            try {
                date = sdf.format(jDateChooser1.getDate());
            } catch (Exception ex) {
            }
            pst.setString(7, date);

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String t = null;
            String time;
            time = t;
            try{

                time= format.format(jSpinner1.getValue());

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }
            pst.setString(8, time);

            pst.setString(9,(String) cboFCL.getSelectedItem());
            pst.setString(10,(String) cboPRACRES.getSelectedItem());
            pst.setString(11,(String) cboEMPTECH.getSelectedItem());
            pst.setString(12,(String) cboPHILOSOPHY.getSelectedItem());
            pst.setString(13,(String) cboANIMATION.getSelectedItem());
            pst.setString(14,(String) cboCOMPROG.getSelectedItem());
            pst.setString(15,(String) cboWIRCACA.getSelectedItem());
            pst.setString(16,(String) cboPEH.getSelectedItem());

            SimpleDateFormat formats = new SimpleDateFormat("HH:mm:ss");
            String l = null;
            String lime;
            lime = l;
            try{

                lime= formats.format(jSpinner2.getValue());

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

            pst.setString(17, lime);

            pst.setInt(18, id);

            pst.executeUpdate();
            MusicHelper.successBg(success);
            JOptionPane.showMessageDialog(this,"Student Record Updated");
            updateDB();
            clearFields();

        }

        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex)  {
            System.err.println(ex);

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException ex) {
            Logger.getLogger(StudentAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            String studentid = txtStudentID.getText();
            sqlConn = DriverManager.getConnection(dataConn, username, password);

            String sql = "SELECT * FROM studentdata WHERE studentid ='"+studentid+"'";
            pst=sqlConn.prepareStatement(sql);
            rs=pst.executeQuery(sql);

            if(rs.next())
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "This Student ID already existed", "Read Me !!",JOptionPane.WARNING_MESSAGE);
                txtStudentID.grabFocus();
            }else{
                pst.executeUpdate();
                updateDB();
                MusicHelper.successBg(success);
                clearFields();
                JOptionPane.showMessageDialog(this, "Student Record Added");
            }

        }catch(Exception e)
        {

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnPrintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrintActionPerformed
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
    }//GEN-LAST:event_btnPrintActionPerformed

    private void btnAddnewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddnewActionPerformed

        checkExcused.setSelected(false);
        checkAbs.setSelected(false);
        checkPres.setSelected(false);
        checkSick.setSelected(false);
        
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");

            sqlConn = DriverManager.getConnection(dataConn, username, password);
            pst = sqlConn.prepareStatement("insert into studentdata(studentid,Firstname,Surname,Section,Gender,Mobile,"
                + "Date,TimeIn,FCL,PRACRES,EMPTECH,PHILOSOPHY,ANIMATION,PROGRAMMING1,WIRCACA,PEH,Timeout)values"
                + "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" );
            pst.setString(1,txtStudentID.getText());
            pst.setString(2,txtFirstName.getText());
            pst.setString(3,txtSurname.getText());
            pst.setString(4,txtGrade.getText());
            pst.setString(5,(String) cboGENDER.getSelectedItem());
            pst.setString(6,txtPhone.getText());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

            String date = "yyyy-MM-dd";
            try {
                date = sdf.format(jDateChooser1.getDate());
            } catch (Exception ex) {
            }

            pst.setString(7, date);

            SimpleDateFormat format = new SimpleDateFormat("HH:mm:ss");
            String t = null;
            String time;
            time = t;
            try{

                time= format.format(jSpinner1.getValue());

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

            pst.setString(8, time);

            pst.setString(9,(String) cboFCL.getSelectedItem());
            pst.setString(10,(String) cboPRACRES.getSelectedItem());
            pst.setString(11,(String) cboEMPTECH.getSelectedItem());
            pst.setString(12,(String) cboPHILOSOPHY.getSelectedItem());
            pst.setString(13,(String) cboANIMATION.getSelectedItem());
            pst.setString(14,(String) cboCOMPROG.getSelectedItem());
            pst.setString(15,(String) cboWIRCACA.getSelectedItem());
            pst.setString(16,(String) cboPEH.getSelectedItem());

            SimpleDateFormat formats = new SimpleDateFormat("HH:mm:ss");
            String l = null;
            String lime;
            lime = l;
            try{

                lime= formats.format(jSpinner2.getValue());

            }
            catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);

            }

            pst.setString(17, lime);

            String id = txtStudentID.getText();
            String name = txtFirstName.getText();
            String surname = txtSurname.getText();
            String phone = txtPhone.getText();
            String gender = cboGENDER.getSelectedItem().toString();
            String grade= txtGrade.getText();
            String dated = String.valueOf(date);

            if(id.equals("") || id.equals(" Enter Student ID"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "Please Enter Student ID", "Read Me !!",JOptionPane.WARNING_MESSAGE);
                txtStudentID.grabFocus();
            }else if(name.equals("") || name.equals(" Enter Student Firstname"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null," Please Enter First Name", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else if(surname.equals("") || surname.equals(" Enter Student Surname"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, " Please Enter Surname", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else if(grade.equals("Choose") || grade.equals(" Enter Student Section"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, " Please Enter Grade and Section", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else if(phone.equals("") || phone.equals(" Enter Phone No."))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, " Please Enter Phone Number", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else if(gender.equals("Choose"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, " Please Choose Gender", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else if(dated.equals("yyyy-MM-dd"))
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, " Please Enter Date", "Read Me !!",JOptionPane.WARNING_MESSAGE);
            }else{
                pst.executeUpdate();
                updateDB();
                MusicHelper.successBg(success);
                clearFields();
                JOptionPane.showMessageDialog(this, "Student Record Added");
            }
        }

        catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(StudentDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (SQLException ex)  {
            //System.err.println(ex);
            java.util.logging.Logger.getLogger(StudentDatabase.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(StudentAttendance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(StudentAttendance.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LineUnavailableException ex) {
            Logger.getLogger(StudentAttendance.class.getName()).log(Level.SEVERE, null, ex);
        }

        try{
            String studentid = txtStudentID.getText();
            sqlConn = DriverManager.getConnection(dataConn, username, password);

            String sql = "SELECT * FROM studentdata WHERE studentid ='"+studentid+"'";
            pst=sqlConn.prepareStatement(sql);
            rs=pst.executeQuery(sql);

            if(rs.next())
            {
                MusicHelper.warningBg(warnings);
                JOptionPane.showMessageDialog(null, "This Student ID already existed", "Read Me !!",JOptionPane.WARNING_MESSAGE);
                txtStudentID.grabFocus();
            }else{
                pst.executeUpdate();
                updateDB();
                MusicHelper.successBg(success);
                clearFields();
                JOptionPane.showMessageDialog(this, "Student Record Added");
            }

        }catch(Exception e)
        {

        }

    }//GEN-LAST:event_btnAddnewActionPerformed

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
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(StudentAttendance.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new StudentAttendance().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel About;
    private javax.swing.JPanel Body;
    private javax.swing.JLabel ForAll;
    private javax.swing.JPanel Header;
    private javax.swing.JPanel Title;
    private javax.swing.JButton btnAddnew;
    private javax.swing.JButton btnExit;
    private javax.swing.JButton btnPrint;
    private javax.swing.JButton btnReset;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JComboBox<String> cboANIMATION;
    private javax.swing.JComboBox<String> cboCOMPROG;
    private javax.swing.JComboBox<String> cboEMPTECH;
    private javax.swing.JComboBox<String> cboFCL;
    private javax.swing.JComboBox<String> cboGENDER;
    private javax.swing.JComboBox<String> cboPEH;
    private javax.swing.JComboBox<String> cboPHILOSOPHY;
    private javax.swing.JComboBox<String> cboPRACRES;
    private javax.swing.JComboBox<String> cboWIRCACA;
    private javax.swing.JCheckBox checkAbs;
    private javax.swing.JCheckBox checkExcused;
    private javax.swing.JCheckBox checkPres;
    private javax.swing.JCheckBox checkSick;
    private javax.swing.JButton generate;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSpinner jSpinner1;
    private javax.swing.JSpinner jSpinner2;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel jdaTe;
    private javax.swing.JLabel okay;
    private javax.swing.JLabel rimuru;
    private javax.swing.JTextField txtFirstName;
    private javax.swing.JTextField txtGrade;
    private javax.swing.JTextField txtPhone;
    private javax.swing.JTextField txtStudentID;
    private javax.swing.JTextField txtSurname;
    private javax.swing.JTextField txt_search;
    // End of variables declaration//GEN-END:variables

    private void clearFields() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        
        txtStudentID.setText("");
        txtFirstName.setText("");
        txtSurname.setText("");
        txtGrade.setText("");
        cboGENDER.setSelectedIndex(0);
        txtPhone.setText("");
        jDateChooser1.setCalendar(null);
        cboFCL.setSelectedIndex(0);
        cboPRACRES.setSelectedIndex(0);
        cboEMPTECH.setSelectedIndex(0);
        cboPHILOSOPHY.setSelectedIndex(0);
        cboANIMATION.setSelectedIndex(0);
        cboCOMPROG.setSelectedIndex(0);
        cboWIRCACA.setSelectedIndex(0);
        cboPEH.setSelectedIndex(0);
        
         Date def = null;
         SimpleDateFormat formatterDefault = new SimpleDateFormat("HH:mm:ss");
            try{
                def = formatterDefault.parse("00:00:00");
            }catch(ParseException ex){
         
        }
            jSpinner1.setValue(def);
            jSpinner2.setValue(def);
    }

    @Override
    public void run() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
                okay.setText(timestr);
                rimuru.setText(datestr);

            }catch(Exception e){
 
            } 
        }
    }
}
