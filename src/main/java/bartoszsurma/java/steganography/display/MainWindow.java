package bartoszsurma.java.steganography.display;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainWindow implements ActionListener {
    private int width, height;
    private String title;

    private JFrame frame;
    private JButton buttonEncoded;
    private JButton buttonDecoded;
    private JButton testButton;

    //Other windows
    private EncodedWindow encodedWindow;
    private DecodedWindow decodedWindow;
    private TestWindow testWindow;

    public MainWindow(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        encodedWindow = new EncodedWindow(600, 800, "Kodowanie wiadomości");
        decodedWindow = new DecodedWindow(800, 400, "Dekodowanie wiadomości");
        testWindow = new TestWindow(700, 500, "Testowanie bitmapy");

        prepareMainWindow();
    }

    public void prepareMainWindow(){
        buttonEncoded = new JButton("Zakoduj wiadomość");
        buttonEncoded.setBounds(50, 50, 200, 50);
        buttonEncoded.addActionListener(this);

        buttonDecoded = new JButton("Dekoduj wiadomość");
        buttonDecoded.setBounds(300, 50, 200, 50);
        buttonDecoded.addActionListener(this);

        testButton = new JButton("Sprawdź bitmapę");
        testButton.setBounds(175, 130, 200, 50);
        testButton.addActionListener(this);

        frame = new JFrame(title);
        frame.setBackground(new Color(57, 59, 61));
        frame.setSize(new Dimension(width, height));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.add(buttonEncoded);
        frame.add(buttonDecoded);
        frame.add(testButton);
        frame.getContentPane().setBackground(new Color(57, 59, 61));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == buttonEncoded)
            encodedWindow.getFrame().setVisible(true);

        if(e.getSource() == buttonDecoded)
            decodedWindow.getFrame().setVisible(true);

        if(e.getSource() == testButton)
            testWindow.getFrame().setVisible(true);
    }
}
