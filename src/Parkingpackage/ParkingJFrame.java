/*
Author: Llibert Matarredona
Higher degree in multiplatform application development
*/

package Parkingpackage;

import java.awt.Color;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.KeyEvent;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import com.sun.speech.freetts.VoiceManager;
import com.sun.speech.freetts.Voice;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.Timer;

public class ParkingJFrame extends javax.swing.JFrame {

    private Timer clockTimer;
    private Thread voiceThread;
    private Color[] color;
    private JButton[][] jb;
    private JTextField[] txtFieldRow;
    private JTextField[] txtFieldColumn;
    private int[][] matrix;
    private final int RowMaxNum = 8, ColumnMaxNum = 10;
    private String speakingText;
    private boolean isKeyPressed = false;

    public ParkingJFrame() {   
        initComponents();
        initConfigScreen();
        initConfig();
        startClockThread();

        jLabelDate.setText(date());
        placesAvailableRow();
        placesAvailableColumn();
        speakingText = capacity();
    }

    private void initConfigScreen() {
        getContentPane().setBackground(Color.cyan);
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    private void initConfig() {

        System.setProperty("freetts.voices","com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory");
        
        keyboardlistener();

        color = new Color[]{Color.BLUE, Color.RED, Color.YELLOW};

        jb = new JButton[][]{
            {jButton1and1, jButton1and2, jButton1and3, jButton1and4, jButton1and5, jButton1and6, jButton1and7, jButton1and8, jButton1and9, jButton1and10},
            {jButton2and1, jButton2and2, jButton2and3, jButton2and4, jButton2and5, jButton2and6, jButton2and7, jButton2and8, jButton2and9, jButton2and10},
            {jButton3and1, jButton3and2, jButton3and3, jButton3and4, jButton3and5, jButton3and6, jButton3and7, jButton3and8, jButton3and9, jButton3and10},
            {jButton4and1, jButton4and2, jButton4and3, jButton4and4, jButton4and5, jButton4and6, jButton4and7, jButton4and8, jButton4and9, jButton4and10},
            {jButton5and1, jButton5and2, jButton5and3, jButton5and4, jButton5and5, jButton5and6, jButton5and7, jButton5and8, jButton5and9, jButton5and10},
            {jButton6and1, jButton6and2, jButton6and3, jButton6and4, jButton6and5, jButton6and6, jButton6and7, jButton6and8, jButton6and9, jButton6and10},
            {jButton7and1, jButton7and2, jButton7and3, jButton7and4, jButton7and5, jButton7and6, jButton7and7, jButton7and8, jButton7and9, jButton7and10},
            {jButton8and1, jButton8and2, jButton8and3, jButton8and4, jButton8and5, jButton8and6, jButton8and7, jButton8and8, jButton8and9, jButton8and10}
        };

        txtFieldRow = new JTextField[]{jTextField1, jTextField2, jTextField3, jTextField4, jTextField5, jTextField6, jTextField7, jTextField8, jTextField9, jTextField10};
        txtFieldColumn = new JTextField[]{jTextField12, jTextField13, jTextField14, jTextField15, jTextField16, jTextField17, jTextField18, jTextField19};

        matrix = new int[RowMaxNum + 1][ColumnMaxNum + 1];
        try {
            readFile();
        } catch (Exception e) {
            System.out.println("The file could not be read: " + e);
        }

        for (int r = 0; r < RowMaxNum; r++) {
            for (int c = 0; c < ColumnMaxNum; c++) {
                jb[r][c].setName(r + "" + c);
                jb[r][c].setBackground(color[0]);
                if (matrix[r][c] == 1) {
                    jb[r][c].setBackground(color[matrix[r][c]]);
                    jb[r][c].setText(matrix[r][c] + "");
                }
            }
        }
    }
    
    private void startClockThread() {
        clockTimer = new Timer(1000, e -> {
            LocalDateTime now = LocalDateTime.now();
            
            jLabelHours.setText(String.format("%02d", now.getHour()));
            jLabelMinutes.setText(String.format("%02d", now.getMinute()));
            jLabelSeconds.setText(String.format("%02d", now.getSecond()));
        });

        clockTimer.setInitialDelay(0);
        clockTimer.start();
    }

   
    private void startVoiceThread(String speakingText) {
    voiceThread = new Thread(() -> {
        try {
            VoiceManager manager = VoiceManager.getInstance();
            Voice voice = manager.getVoice("kevin16");

            if (voice != null) {
                voice.allocate();
                voice.speak(speakingText);
                voice.deallocate();
            } else {
                System.err.println("'kevin16' was not found");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    });

    voiceThread.start();
}

    private void keyboardlistener() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {
            @Override
            public boolean dispatchKeyEvent(final KeyEvent e) {
                if (e.getID() == KeyEvent.KEY_PRESSED && e.getKeyChar() == 'a' && !isKeyPressed) {
                    isKeyPressed = true;
                    readKeyBoard(e);
                } else if (e.getID() == KeyEvent.KEY_RELEASED && e.getKeyChar() == 'a') {
                    isKeyPressed = false;
                }
                return false;
            }
        });
    }

    public void readKeyBoard(KeyEvent event) {
        try {
            jButtonAdd.doClick();   
            event.consume();        
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jButton1and2 = new javax.swing.JButton();
        jButton1and1 = new javax.swing.JButton();
        jButton1and3 = new javax.swing.JButton();
        jButton1and5 = new javax.swing.JButton();
        jButton1and4 = new javax.swing.JButton();
        jButton1and6 = new javax.swing.JButton();
        jButton1and8 = new javax.swing.JButton();
        jButton1and7 = new javax.swing.JButton();
        jButton1and10 = new javax.swing.JButton();
        jButton1and9 = new javax.swing.JButton();
        jButton2and10 = new javax.swing.JButton();
        jButton2and2 = new javax.swing.JButton();
        jButton2and1 = new javax.swing.JButton();
        jButton2and3 = new javax.swing.JButton();
        jButton2and5 = new javax.swing.JButton();
        jButton2and4 = new javax.swing.JButton();
        jButton2and6 = new javax.swing.JButton();
        jButton2and8 = new javax.swing.JButton();
        jButton2and7 = new javax.swing.JButton();
        jButton2and9 = new javax.swing.JButton();
        jButton3and10 = new javax.swing.JButton();
        jButton4and10 = new javax.swing.JButton();
        jButton4and2 = new javax.swing.JButton();
        jButton4and1 = new javax.swing.JButton();
        jButton4and3 = new javax.swing.JButton();
        jButton4and5 = new javax.swing.JButton();
        jButton4and4 = new javax.swing.JButton();
        jButton4and6 = new javax.swing.JButton();
        jButton4and8 = new javax.swing.JButton();
        jButton4and7 = new javax.swing.JButton();
        jButton4and9 = new javax.swing.JButton();
        jButton3and2 = new javax.swing.JButton();
        jButton3and1 = new javax.swing.JButton();
        jButton3and3 = new javax.swing.JButton();
        jButton3and5 = new javax.swing.JButton();
        jButton3and4 = new javax.swing.JButton();
        jButton3and6 = new javax.swing.JButton();
        jButton3and8 = new javax.swing.JButton();
        jButton3and7 = new javax.swing.JButton();
        jButton3and9 = new javax.swing.JButton();
        jButton8and8 = new javax.swing.JButton();
        jButton8and7 = new javax.swing.JButton();
        jButton5and10 = new javax.swing.JButton();
        jButton8and9 = new javax.swing.JButton();
        jButton6and10 = new javax.swing.JButton();
        jButton6and2 = new javax.swing.JButton();
        jButton7and2 = new javax.swing.JButton();
        jButton6and1 = new javax.swing.JButton();
        jButton7and1 = new javax.swing.JButton();
        jButton6and3 = new javax.swing.JButton();
        jButton7and3 = new javax.swing.JButton();
        jButton6and5 = new javax.swing.JButton();
        jButton7and5 = new javax.swing.JButton();
        jButton6and4 = new javax.swing.JButton();
        jButton7and4 = new javax.swing.JButton();
        jButton6and6 = new javax.swing.JButton();
        jButton7and6 = new javax.swing.JButton();
        jButton6and8 = new javax.swing.JButton();
        jButton6and7 = new javax.swing.JButton();
        jButton7and8 = new javax.swing.JButton();
        jButton7and7 = new javax.swing.JButton();
        jButton6and9 = new javax.swing.JButton();
        jButton7and9 = new javax.swing.JButton();
        jButton5and2 = new javax.swing.JButton();
        jButton7and10 = new javax.swing.JButton();
        jButton5and1 = new javax.swing.JButton();
        jButton8and10 = new javax.swing.JButton();
        jButton5and3 = new javax.swing.JButton();
        jButton8and2 = new javax.swing.JButton();
        jButton5and5 = new javax.swing.JButton();
        jButton8and1 = new javax.swing.JButton();
        jButton5and4 = new javax.swing.JButton();
        jButton8and3 = new javax.swing.JButton();
        jButton5and6 = new javax.swing.JButton();
        jButton8and5 = new javax.swing.JButton();
        jButton5and8 = new javax.swing.JButton();
        jButton8and4 = new javax.swing.JButton();
        jButton5and7 = new javax.swing.JButton();
        jButton8and6 = new javax.swing.JButton();
        jButton5and9 = new javax.swing.JButton();
        jTextField1 = new javax.swing.JTextField();
        jTextField2 = new javax.swing.JTextField();
        jTextField3 = new javax.swing.JTextField();
        jTextField4 = new javax.swing.JTextField();
        jTextField5 = new javax.swing.JTextField();
        jTextField6 = new javax.swing.JTextField();
        jTextField7 = new javax.swing.JTextField();
        jTextField8 = new javax.swing.JTextField();
        jTextField9 = new javax.swing.JTextField();
        jTextField10 = new javax.swing.JTextField();
        jTextField12 = new javax.swing.JTextField();
        jTextField13 = new javax.swing.JTextField();
        jTextField14 = new javax.swing.JTextField();
        jTextField15 = new javax.swing.JTextField();
        jTextField16 = new javax.swing.JTextField();
        jTextField17 = new javax.swing.JTextField();
        jTextField18 = new javax.swing.JTextField();
        jTextField19 = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jLabelDate = new javax.swing.JLabel();
        jLabelMinutes = new javax.swing.JLabel();
        jLabelHours = new javax.swing.JLabel();
        jLabelSeconds = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButtonAdd = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        jButtonTalk = new javax.swing.JButton();
        jButtonReset = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        jButton1and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and2.setText("0");
        jButton1and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and1.setText("0");
        jButton1and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and3.setText("0");
        jButton1and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and5.setText("0");
        jButton1and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and4.setText("0");
        jButton1and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and6.setText("0");
        jButton1and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and8.setText("0");
        jButton1and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and7.setText("0");
        jButton1and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and10.setText("0");
        jButton1and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton1and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1and9.setText("0");
        jButton1and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton1and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton1and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and10.setText("0");
        jButton2and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and2.setText("0");
        jButton2and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and1.setText("0");
        jButton2and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and3.setText("0");
        jButton2and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and5.setText("0");
        jButton2and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and4.setText("0");
        jButton2and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and6.setText("0");
        jButton2and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and8.setText("0");
        jButton2and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and7.setText("0");
        jButton2and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton2and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2and9.setText("0");
        jButton2and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton2and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton2and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and10.setText("0");
        jButton3and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and10.setText("0");
        jButton4and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and2.setText("0");
        jButton4and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and1.setText("0");
        jButton4and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and3.setText("0");
        jButton4and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and5.setText("0");
        jButton4and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and4.setText("0");
        jButton4and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and6.setText("0");
        jButton4and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and8.setText("0");
        jButton4and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and7.setText("0");
        jButton4and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton4and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4and9.setText("0");
        jButton4and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton4and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton4and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and2.setText("0");
        jButton3and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and1.setText("0");
        jButton3and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and3.setText("0");
        jButton3and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and5.setText("0");
        jButton3and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and4.setText("0");
        jButton3and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and6.setText("0");
        jButton3and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and8.setText("0");
        jButton3and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and7.setText("0");
        jButton3and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton3and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton3and9.setText("0");
        jButton3and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton3and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton3and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and8.setText("0");
        jButton8and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and7.setText("0");
        jButton8and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and10.setText("0");
        jButton5and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and9.setText("0");
        jButton8and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and10.setText("0");
        jButton6and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and2.setText("0");
        jButton6and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and2.setText("0");
        jButton7and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and1.setText("0");
        jButton6and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and1.setText("0");
        jButton7and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and3.setText("0");
        jButton6and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and3.setText("0");
        jButton7and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and5.setText("0");
        jButton6and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and5.setText("0");
        jButton7and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and4.setText("0");
        jButton6and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and4.setText("0");
        jButton7and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and6.setText("0");
        jButton6and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and6.setText("0");
        jButton7and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and8.setText("0");
        jButton6and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and7.setText("0");
        jButton6and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and8.setText("0");
        jButton7and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and7.setText("0");
        jButton7and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton6and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6and9.setText("0");
        jButton6and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton6and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton6and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and9.setText("0");
        jButton7and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and2.setText("0");
        jButton5and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton7and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton7and10.setText("0");
        jButton7and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton7and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton7and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and1.setText("0");
        jButton5and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and10.setText("0");
        jButton8and10.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and3.setText("0");
        jButton5and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and2.setText("0");
        jButton8and2.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and5.setText("0");
        jButton5and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and1.setText("0");
        jButton8and1.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and4.setText("0");
        jButton5and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and3.setText("0");
        jButton8and3.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and6.setText("0");
        jButton5and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and5.setText("0");
        jButton8and5.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and8.setText("0");
        jButton5and8.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and4.setText("0");
        jButton8and4.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and7.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and7.setText("0");
        jButton5and7.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton8and6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8and6.setText("0");
        jButton8and6.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton8and6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton8and6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jButton5and9.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton5and9.setText("0");
        jButton5and9.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                jButton1and1MouseMoved(evt);
            }
        });
        jButton5and9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jButton1and1(evt);
            }
        });
        jButton5and9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1and1ActionPerformed(evt);
            }
        });

        jTextField1.setEditable(false);
        jTextField1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField1.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField2.setEditable(false);
        jTextField2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField2.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField3.setEditable(false);
        jTextField3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField3.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField4.setEditable(false);
        jTextField4.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField4.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField5.setEditable(false);
        jTextField5.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField5.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField6.setEditable(false);
        jTextField6.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField6.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField7.setEditable(false);
        jTextField7.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField7.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField8.setEditable(false);
        jTextField8.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField8.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField9.setEditable(false);
        jTextField9.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField9.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField10.setEditable(false);
        jTextField10.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField10.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField12.setEditable(false);
        jTextField12.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField12.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField13.setEditable(false);
        jTextField13.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField13.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField14.setEditable(false);
        jTextField14.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField14.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField15.setEditable(false);
        jTextField15.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField15.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField16.setEditable(false);
        jTextField16.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField16.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField17.setEditable(false);
        jTextField17.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField17.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField18.setEditable(false);
        jTextField18.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField18.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        jTextField19.setEditable(false);
        jTextField19.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jTextField19.setHorizontalAlignment(javax.swing.JTextField.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1and1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1and2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField3)
                            .addComponent(jButton1and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1and4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton1and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton1and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField7)
                            .addComponent(jButton1and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField8)
                            .addComponent(jButton1and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextField9)
                            .addComponent(jButton1and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButton1and10, javax.swing.GroupLayout.DEFAULT_SIZE, 65, Short.MAX_VALUE)
                            .addComponent(jTextField10)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton3and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton3and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton2and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton2and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton8and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton8and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton7and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton6and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton5and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(jPanel1Layout.createSequentialGroup()
                            .addComponent(jButton4and1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and2, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and3, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and5, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and6, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and7, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and8, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and9, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jButton4and10, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(118, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField7, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField12, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton2and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField13, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton3and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton3and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField14, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton4and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField15, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField16, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField17, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField18, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton8and2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and3, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and1, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and5, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and6, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and4, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and8, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and9, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and7, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8and10, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextField19, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(53, 53, 53))
        );

        jButton4and4.getAccessibleContext().setAccessibleDescription("");
        jButton4and8.getAccessibleContext().setAccessibleDescription("");
        jButton7and3.getAccessibleContext().setAccessibleDescription("");

        jPanel2.setBackground(new java.awt.Color(0, 102, 102));

        jLabelDate.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabelDate.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelDate.setText("DD/MM/YYYY");

        jLabelMinutes.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelMinutes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelMinutes.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelHours.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelHours.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelHours.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabelSeconds.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabelSeconds.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabelSeconds.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText(":");

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText(":");

        jButtonAdd.setBackground(new java.awt.Color(0, 102, 0));
        jButtonAdd.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonAdd.setText("ADD");
        jButtonAdd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButtonAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonAddActionPerformed(evt);
            }
        });

        jTextArea3.setEditable(false);
        jTextArea3.setBackground(new java.awt.Color(0, 0, 0));
        jTextArea3.setColumns(20);
        jTextArea3.setFont(new java.awt.Font("Monospaced", 1, 14)); // NOI18N
        jTextArea3.setForeground(new java.awt.Color(255, 255, 255));
        jTextArea3.setRows(5);
        jScrollPane3.setViewportView(jTextArea3);

        jButtonTalk.setBackground(new java.awt.Color(255, 255, 0));
        jButtonTalk.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonTalk.setText("TALK");
        jButtonTalk.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButtonTalk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonTalkActionPerformed(evt);
            }
        });

        jButtonReset.setBackground(new java.awt.Color(153, 0, 0));
        jButtonReset.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jButtonReset.setText("RESET");
        jButtonReset.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0), 3));
        jButtonReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonResetActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(173, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelDate, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 215, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelHours, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(30, 30, 30)
                                .addComponent(jLabelMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabelSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(60, 60, 60)
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(60, 60, 60)))
                .addGap(162, 162, 162))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 398, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButtonTalk, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButtonReset, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(jLabelDate, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabelHours, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelMinutes, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelSeconds, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(32, 32, 32)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 471, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButtonReset, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButtonAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonTalk, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(193, 193, 193)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(304, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1and1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1and1ActionPerformed
        JButton jbt = (JButton) evt.getSource();
        String name = jbt.getName(); //f+"and"+c
        int c = Integer.parseInt(name.charAt(1) + ""); // 0          48
        int f = name.charAt(0) - '0'; //o -48
        matrix[f][c] = 1 - matrix[f][c]; //CHECK
        jb[f][c].setBackground(color[matrix[f][c]]);
        jb[f][c].setText(matrix[f][c] + "");
        placesAvailableRow();
        placesAvailableColumn();
        speakingText = capacity();
        try {
            writeToFile();
        } catch (Exception ex) {
            System.out.println("Failed to write file: " + ex);
        }
    }//GEN-LAST:event_jButton1and1ActionPerformed

    private void writeToFile() throws IOException {
        FileWriter fw = new FileWriter("pk.txt");
        BufferedWriter bw = new BufferedWriter(fw);

        for (int f = 0; f < RowMaxNum + 1; f++) {
            for (int c = 0; c < ColumnMaxNum + 1; c++) {
                bw.write(matrix[f][c] + "\t");
            }
            bw.write("\n");
        }
        bw.flush();
        bw.close();
    }

    File file;

    private void readFile() throws FileNotFoundException, IOException {
        String[] datos;
        String tx;
        int r = 0;

        file = new File("pk.txt");
        FileReader fr = new FileReader(file);
        BufferedReader br = new BufferedReader(fr);

        while ((tx = br.readLine()) != null) {
            datos = tx.split("\t");
            for (int c = 0; c < datos.length; c++) {
                matrix[r][c] = Integer.parseInt(datos[c]);
            }
            r++;
        }
        fr.close();
    }

    private void jButton1and1MouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1and1MouseMoved
        JButton jbt = (JButton) evt.getSource();
        String name = jbt.getName(); //f+"and"+c
        int c = Integer.parseInt(name.charAt(1) + ""); // 0          48
        int r = name.charAt(0) - '0'; //or -48
        jb[r][c].setBackground(color[2]);
    }//GEN-LAST:event_jButton1and1MouseMoved

    private void jButton1and1(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton1and1
        JButton jbt = (JButton) evt.getSource();
        String name = jbt.getName(); //f+"and"+c
        int c = Integer.parseInt(name.charAt(1) + ""); // 0          48
        int r = name.charAt(0) - '0'; //o -48
        jb[r][c].setBackground(color[matrix[r][c]]);
    }//GEN-LAST:event_jButton1and1

    private void jButtonAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonAddActionPerformed
        try {
            int r = Integer.parseInt(JOptionPane.showInputDialog("Row? (0-7)"));
            int c = Integer.parseInt(JOptionPane.showInputDialog("Column? (0-9)"));

            // Validar rango
            if (r < 0 || r > 7) {
                JOptionPane.showMessageDialog(null, "Row must be between 0 and 7.");
                return;  // Salir del método
            }
            if (c < 0 || c > 9) {
                JOptionPane.showMessageDialog(null, "Column must be between 0 and 9.");
                return;  // Salir del método
            }

            // Si pasa validación, actualiza la matriz
            matrix[r][c] = 1 - matrix[r][c];
            jb[r][c].setBackground(color[matrix[r][c]]);
            jb[r][c].setText(String.valueOf(matrix[r][c]));

            placesAvailableRow();
            placesAvailableColumn();
            speakingText = capacity();

            try {
                writeToFile();
            } catch (Exception ex) {
                System.out.println("Failed to write file: " + ex);
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Enter a valid number: " + e.getMessage());
        }
    }//GEN-LAST:event_jButtonAddActionPerformed

    private void jButtonTalkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonTalkActionPerformed
        startVoiceThread(speakingText);
    }//GEN-LAST:event_jButtonTalkActionPerformed

    private void jButtonResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonResetActionPerformed
        for (int f = 0; f < RowMaxNum; f++) {
            for (int c = 0; c < ColumnMaxNum; c++) {
                jb[f][c].setName(f + "" + c);
                if (matrix[f][c] == 0) {
                    //matrix[r][c] = 1 - matrix[r][c];
                    jb[f][c].setBackground(color[matrix[f][c]]);
                    jb[f][c].setText(matrix[f][c] + "");
                } else {
                    matrix[f][c] = 1 - matrix[f][c];
                    jb[f][c].setBackground(color[matrix[f][c]]);
                    jb[f][c].setText(matrix[f][c] + "");
                }
            }
            placesAvailableRow();
            placesAvailableColumn();
            speakingText = capacity();
        }
        file.delete();
    }//GEN-LAST:event_jButtonResetActionPerformed

    public static String date() {
        Date date = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/YYYY");
        return dateFormat.format(date);
    }

    private void placesAvailableRow() {
        for (int r = 0; r < RowMaxNum; r++) {
            for (int c = 0; c < ColumnMaxNum; c++) {
                if (matrix[r][c] == 0) {
                    matrix[r][ColumnMaxNum]++;
                }
            }
            txtFieldColumn[r].setText(String.valueOf(matrix[r][ColumnMaxNum]));
            if (matrix[r][ColumnMaxNum] >= 6) {
                txtFieldColumn[r].setForeground(Color.GREEN);
            } else if (matrix[r][ColumnMaxNum] == 4 || matrix[r][ColumnMaxNum] == 5) {
                txtFieldColumn[r].setForeground(Color.ORANGE);
            } else {
                txtFieldColumn[r].setForeground(Color.RED);
            }
            matrix[r][ColumnMaxNum] = 0;
        }
    }

    private void placesAvailableColumn() {
        int placesAvailableColumn = 0;
        String txt = "";
        for (int c = 0; c < ColumnMaxNum; c++) {
            for (int r = 0; r < RowMaxNum; r++) {
                if (matrix[r][c] == 0) {
                    placesAvailableColumn++;
                }
            }
            txt = "" + placesAvailableColumn;
            txtFieldRow[c].setText(txt);
            if (placesAvailableColumn >= 5) {
                txtFieldRow[c].setForeground(Color.GREEN);
            } else if (placesAvailableColumn >= 3 && placesAvailableColumn <= 4) {
                txtFieldRow[c].setForeground(Color.ORANGE);
            } else {
                txtFieldRow[c].setForeground(Color.RED);
            }
            placesAvailableColumn = 0;
        }
    }

    private String capacity() {
        String speakingText;
        int totalPlaces = (RowMaxNum * ColumnMaxNum);
        float percentageOccupation;
        int enteredCars;
        String txt = "";
        txt += "The number of parking spaces is " + totalPlaces + "\n\n";
        int totalPlacesAvailable = 0;
        for (int row = 0; row < RowMaxNum; row++) {
            for (int column = 0; column < ColumnMaxNum; column++) { 
                if (matrix[row][column] == 0) {
                    totalPlacesAvailable++;
                }
            }
        }
        txt += "There is a total of " + totalPlacesAvailable + " available parking spaces\n\n";

        percentageOccupation = ((float)totalPlaces - (float)totalPlacesAvailable) / (float)totalPlaces * 100;
        txt += "The % occupancy is " + String.format("%.2f", percentageOccupation) + " percent\n\n";

        enteredCars = (int) (totalPlaces - totalPlacesAvailable);

        txt += "A total of " + enteredCars + " cars in the parking lot\n\n";
        jTextArea3.setText(txt);
        speakingText = txt;
        return speakingText;
    }

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
            java.util.logging.Logger.getLogger(ParkingJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ParkingJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ParkingJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);

        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ParkingJFrame.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ParkingJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1and1;
    private javax.swing.JButton jButton1and10;
    private javax.swing.JButton jButton1and2;
    private javax.swing.JButton jButton1and3;
    private javax.swing.JButton jButton1and4;
    private javax.swing.JButton jButton1and5;
    private javax.swing.JButton jButton1and6;
    private javax.swing.JButton jButton1and7;
    private javax.swing.JButton jButton1and8;
    private javax.swing.JButton jButton1and9;
    private javax.swing.JButton jButton2and1;
    private javax.swing.JButton jButton2and10;
    private javax.swing.JButton jButton2and2;
    private javax.swing.JButton jButton2and3;
    private javax.swing.JButton jButton2and4;
    private javax.swing.JButton jButton2and5;
    private javax.swing.JButton jButton2and6;
    private javax.swing.JButton jButton2and7;
    private javax.swing.JButton jButton2and8;
    private javax.swing.JButton jButton2and9;
    private javax.swing.JButton jButton3and1;
    private javax.swing.JButton jButton3and10;
    private javax.swing.JButton jButton3and2;
    private javax.swing.JButton jButton3and3;
    private javax.swing.JButton jButton3and4;
    private javax.swing.JButton jButton3and5;
    private javax.swing.JButton jButton3and6;
    private javax.swing.JButton jButton3and7;
    private javax.swing.JButton jButton3and8;
    private javax.swing.JButton jButton3and9;
    private javax.swing.JButton jButton4and1;
    private javax.swing.JButton jButton4and10;
    private javax.swing.JButton jButton4and2;
    private javax.swing.JButton jButton4and3;
    private javax.swing.JButton jButton4and4;
    private javax.swing.JButton jButton4and5;
    private javax.swing.JButton jButton4and6;
    private javax.swing.JButton jButton4and7;
    private javax.swing.JButton jButton4and8;
    private javax.swing.JButton jButton4and9;
    private javax.swing.JButton jButton5and1;
    private javax.swing.JButton jButton5and10;
    private javax.swing.JButton jButton5and2;
    private javax.swing.JButton jButton5and3;
    private javax.swing.JButton jButton5and4;
    private javax.swing.JButton jButton5and5;
    private javax.swing.JButton jButton5and6;
    private javax.swing.JButton jButton5and7;
    private javax.swing.JButton jButton5and8;
    private javax.swing.JButton jButton5and9;
    private javax.swing.JButton jButton6and1;
    private javax.swing.JButton jButton6and10;
    private javax.swing.JButton jButton6and2;
    private javax.swing.JButton jButton6and3;
    private javax.swing.JButton jButton6and4;
    private javax.swing.JButton jButton6and5;
    private javax.swing.JButton jButton6and6;
    private javax.swing.JButton jButton6and7;
    private javax.swing.JButton jButton6and8;
    private javax.swing.JButton jButton6and9;
    private javax.swing.JButton jButton7and1;
    private javax.swing.JButton jButton7and10;
    private javax.swing.JButton jButton7and2;
    private javax.swing.JButton jButton7and3;
    private javax.swing.JButton jButton7and4;
    private javax.swing.JButton jButton7and5;
    private javax.swing.JButton jButton7and6;
    private javax.swing.JButton jButton7and7;
    private javax.swing.JButton jButton7and8;
    private javax.swing.JButton jButton7and9;
    private javax.swing.JButton jButton8and1;
    private javax.swing.JButton jButton8and10;
    private javax.swing.JButton jButton8and2;
    private javax.swing.JButton jButton8and3;
    private javax.swing.JButton jButton8and4;
    private javax.swing.JButton jButton8and5;
    private javax.swing.JButton jButton8and6;
    private javax.swing.JButton jButton8and7;
    private javax.swing.JButton jButton8and8;
    private javax.swing.JButton jButton8and9;
    private javax.swing.JButton jButtonAdd;
    private javax.swing.JButton jButtonReset;
    private javax.swing.JButton jButtonTalk;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabelDate;
    private javax.swing.JLabel jLabelHours;
    private javax.swing.JLabel jLabelMinutes;
    private javax.swing.JLabel jLabelSeconds;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField12;
    private javax.swing.JTextField jTextField13;
    private javax.swing.JTextField jTextField14;
    private javax.swing.JTextField jTextField15;
    private javax.swing.JTextField jTextField16;
    private javax.swing.JTextField jTextField17;
    private javax.swing.JTextField jTextField18;
    private javax.swing.JTextField jTextField19;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    private javax.swing.JTextField jTextField7;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables


}
