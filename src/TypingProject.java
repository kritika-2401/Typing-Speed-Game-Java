import javax.sound.sampled.*;
import java.io.File;
import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.awt.event.*;

public class TypingProject {

    // ================= TEXT DATA =================
    static String easyText =
            "Rain lashed against the rusted wire fence as Codename: Dhurandhar slipped into the shadows "
          + "of the zero-point border. The signal was dark, and the trackers were dead. "
          + "I stepped into the lion's den under a moonless sky. "
          + "One wrong move meant the end of the mission, but a true agent thrives in the silence. "
          + "The distant lights of Lahore flickered like a trap, waiting for a ghost to arrive. "
          + "In this game of shadows, there are no save points and no second chances. "
          + "The real mission has officially begun.";

    static String mediumText =
            "I logged into the game like a hero entering the final arc of an anime. "
          + "The background music felt dramatic, as if a Dhurandhar-style entry scene was about to begin. "
          + "My character stood on the battlefield, cape moving in slow motion, while enemies gathered like a villain's army. "
          + "When the boss appeared, the screen flashed like a cinematic movie trailer. "
          + "Still, I refused to quit. A true Dhurandhar does not retreat after one mistake. "
          + "The final strike landed, and victory filled the screen with shining rewards. "
          + "For a moment, I felt like the main character of both a blockbuster movie and a legendary game. "
          + "Then the next match loaded, and the story began again.";

    // ================= GLOBAL STATE =================
    static String selectedText = easyText;
    static int testTime = 60, timeLeft;
    static boolean timerStarted = false;
    static long startTime;

    // ================= BACKGROUND MUSIC =================
    static Clip bgClip;

    static void playBackgroundMusic() {
        try {
            AudioInputStream audio =
                    AudioSystem.getAudioInputStream(new File("sounds/ramba_ho.wav"));
            bgClip = AudioSystem.getClip();
            bgClip.open(audio);
            bgClip.loop(Clip.LOOP_CONTINUOUSLY);
            bgClip.start();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    static void stopBackgroundMusic() {
        if (bgClip != null && bgClip.isRunning()) {
            bgClip.stop();
            bgClip.close();
        }
    }

    // ================= MAIN =================
    public static void main(String[] args) {
        showStartScreen();
    }

    // ================= START SCREEN =================
    static void showStartScreen() {

        JFrame frame = new JFrame("Typing Speed Test");
        frame.setSize(700, 500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel bg = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(255, 140, 150),
                        0, getHeight(), new Color(255, 190, 210)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };
        bg.setLayout(null);

        // // Simple Background Panel
        // JPanel bg = new JPanel();
        // bg.setLayout(null);
        // bg.setBackground(new Color(255, 182, 193));  // Light pink background

        JPanel panel = new JPanel(null);
        panel.setBounds(100, 60, 500, 350);
        panel.setBackground(new Color(255, 255, 255, 180)); //White with transparency (glass effect)
        panel.setBorder(BorderFactory.createLineBorder(new Color(220, 220, 220), 1));//thin border
        bg.add(panel);
        //frame.add(panel);

        JLabel title = new JLabel(" TYPING SPEED TEST ");
        title.setBounds(90, 20, 350, 40);
        title.setFont(new Font("Segoe UI", Font.BOLD, 26));
        title.setForeground(new Color(180, 60, 90));
        panel.add(title);

        JRadioButton oneMin = new JRadioButton("1 Minute", true);
        JRadioButton twoMin = new JRadioButton("2 Minute");
        oneMin.setBounds(60, 110, 120, 25);
        twoMin.setBounds(60, 140, 120, 25);

        ButtonGroup timeGroup = new ButtonGroup();
        timeGroup.add(oneMin);
        timeGroup.add(twoMin);
        panel.add(oneMin);
        panel.add(twoMin);

        JRadioButton easyBtn = new JRadioButton("Easy Text", true);
        JRadioButton mediumBtn = new JRadioButton("Medium Text");
        easyBtn.setBounds(290, 110, 120, 25);
        mediumBtn.setBounds(290, 140, 150, 25);

        ButtonGroup textGroup = new ButtonGroup();
        textGroup.add(easyBtn);
        textGroup.add(mediumBtn);
        panel.add(easyBtn);
        panel.add(mediumBtn);

        JButton startBtn = new JButton("START BATTLE");
        startBtn.setBounds(150, 220, 200, 45);
        startBtn.setFont(new Font("Segoe UI", Font.BOLD, 18));
        startBtn.setBackground(new Color(220, 80, 100));
        startBtn.setForeground(Color.WHITE);
        startBtn.setFocusPainted(false);
        startBtn.setBorder(BorderFactory.createEmptyBorder());
        startBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
        panel.add(startBtn);

        startBtn.addActionListener(e -> {
            testTime = twoMin.isSelected() ? 120 : 60;
            selectedText = mediumBtn.isSelected() ? mediumText : easyText;
            frame.dispose();
            showTypingTest();
        });

        frame.add(bg);
        frame.setVisible(true);
    }

    // ================= TYPING TEST =================
    static void showTypingTest() {

        JFrame frame = new JFrame("Typing Test");
        frame.setSize(900, 650);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);

        JPanel panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(170, 210, 255),
                        0, getHeight(), new Color(255, 190, 225)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JLabel heading = new JLabel("Typing Test");
        heading.setBounds(350, 20, 300, 40);
        heading.setFont(new Font("Segoe UI", Font.BOLD, 28));
        heading.setForeground(new Color(70, 90, 140));
        panel.add(heading);

        JLabel timerLabel = new JLabel("Time: " + testTime);
        timerLabel.setBounds(750, 25, 120, 30);
        timerLabel.setFont(new Font("Segoe UI", Font.BOLD, 18));
        timerLabel.setForeground(new Color(90, 70, 150));
        panel.add(timerLabel);

        JTextPane referencePane = new JTextPane();
        referencePane.setBounds(100, 100, 700, 300);
        referencePane.setFont(new Font("Consolas", Font.PLAIN, 20));
        referencePane.setText(selectedText);
        referencePane.setEditable(false);
        referencePane.setBackground(new Color(225, 235, 255));
        referencePane.setForeground(new Color(60, 60, 90));
        panel.add(referencePane);

        JTextField typingField = new JTextField();
        typingField.setBounds(100, 430, 700, 50);
        typingField.setFont(new Font("Consolas", Font.PLAIN, 22));
        typingField.setBackground(new Color(255, 225, 240));
        typingField.setForeground(new Color(50, 50, 80));
        typingField.setTransferHandler(null);
        panel.add(typingField);

        timeLeft = testTime;
        timerStarted = false;

        Timer timer = new Timer(1000, e -> {        // Timer
            timerLabel.setText("Time: " + --timeLeft);
            if (timeLeft <= 0) {
                ((Timer) e.getSource()).stop();
                typingField.setEditable(false);
                stopBackgroundMusic();
                showResult(frame, typingField.getText());
            }
        });

        typingField.addKeyListener(new KeyAdapter() {       //Typing logic
            public void keyReleased(KeyEvent e) {

                if (!timerStarted) {
                    startTime = System.currentTimeMillis();
                    timer.start();
                    timerStarted = true;
                }

                StyledDocument doc = referencePane.getStyledDocument();
                Style def = referencePane.addStyle("def", null);
                Style cor = referencePane.addStyle("cor", null);
                Style wrg = referencePane.addStyle("wrg", null);

                StyleConstants.setForeground(def, new Color(60, 60, 90));  //dark gray
                StyleConstants.setForeground(cor, new Color(0, 150, 120));  //green
                StyleConstants.setForeground(wrg, new Color(220, 90, 120)); //red

                doc.setCharacterAttributes(0, selectedText.length(), def, true);

                String userText = typingField.getText();
                for (int i = 0; i < userText.length() && i < selectedText.length(); i++){
                    if (userText.charAt(i) == selectedText.charAt(i)) {
                        doc.setCharacterAttributes(i, 1, cor, true);   // correct → green
                    } else {
                        doc.setCharacterAttributes(i, 1, wrg, true);   // wrong → red
                    }
                }
            }
        });

        frame.add(panel);
        frame.setVisible(true);
        playBackgroundMusic();
    }

    // ================= RESULT SCREEN =================
    static void showResult(JFrame frame, String userText) {

        int correctChars = 0;  //counts corrcet characters
        for (int i = 0; i < userText.length() && i < selectedText.length(); i++)
            if (userText.charAt(i) == selectedText.charAt(i)) correctChars++;

        double accuracy;    //accuracy calculation
        if (userText.length() == 0) {
            accuracy = 0;
        } else {
            accuracy = (correctChars * 100.0) / userText.length();
        }

        double minutes = (System.currentTimeMillis() - startTime) / 60000.0;  //calculates time in minutes
        int wpm;      //calculates words per minute
        if (minutes <= 0 || correctChars == 0) {
            wpm = 0;
        } else {
            double words = correctChars / 5.0;  //coverts char into words
            wpm = (int) (words / minutes);
        }

        String level;
        if (wpm < 35) {
            level = "🐢 Turtle";
        } else if (wpm < 60) {
            level = "🦊 Fox";
        } else if (wpm < 85) {
            level = "🦖 T-Rex";
        } else {
            level = "👑 Legend";
        }

        JPanel panel = new JPanel(null) {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.setPaint(new GradientPaint(
                        0, 0, new Color(210, 230, 255),
                        0, getHeight(), new Color(255, 210, 235)));
                g2.fillRect(0, 0, getWidth(), getHeight());
            }
        };

        JDialog dialog = new JDialog(frame, "Result", true);
        dialog.setSize(500, 350);
        dialog.setLocationRelativeTo(frame);

        JLabel title = new JLabel("Typing Test Complete!");
        title.setBounds(100, 20, 300, 30);
        title.setFont(new Font("Segoe UI", Font.BOLD, 22));
        title.setForeground(new Color(90, 70, 160));
        panel.add(title);

        JLabel wpmLabel = new JLabel("Speed: " + wpm + " WPM");
        wpmLabel.setBounds(150, 80, 250, 30);
        wpmLabel.setFont(new Font("Segoe UI", Font.BOLD, 20));
        wpmLabel.setForeground(new Color(0, 150, 120));
        panel.add(wpmLabel);

        JLabel acc = new JLabel("Accuracy: " + String.format("%.2f", accuracy) + "%");
        acc.setBounds(150, 120, 250, 30);
        acc.setFont(new Font("Segoe UI", Font.BOLD, 20));
        acc.setForeground(new Color(220, 90, 120));
        panel.add(acc);

        JLabel lvl = new JLabel("Level: " + level);
        lvl.setBounds(150, 160, 250, 30);
        lvl.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lvl.setForeground(new Color(70, 60, 120));
        panel.add(lvl);

        JButton retry = new JButton("Try Again");
        retry.setBounds(170, 220, 150, 40);
        retry.setBackground(new Color(200, 180, 255));
        retry.setForeground(new Color(70, 50, 120));
        retry.setFocusPainted(false);
        retry.addActionListener(e -> {
            dialog.dispose();
            frame.dispose();
            showStartScreen();
        });
        panel.add(retry);

        dialog.add(panel);
        dialog.setVisible(true);
    }
}
