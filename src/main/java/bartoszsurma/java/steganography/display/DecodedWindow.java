package bartoszsurma.java.steganography.display;

import bartoszsurma.java.steganography.common.CurrentColor;
import bartoszsurma.java.steganography.fileutilities.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class DecodedWindow implements ActionListener{
    private int width, height;
    private String title;

    private JFrame frame;

    //Panels
    private JPanel optionsPanel;
    private JPanel decodedImagePanel;
    private JPanel textPanel;
    private JPanel slidersPanel;

    //Options to optionsPanel
    private JButton openedFile;
    private JButton decodedFile;


    //Options to encodedImagePanel
    private JLabel decodedImageLabel;
    private JLabel decodedImageHolder;
    private JScrollPane decodedImageScroll;

    //Image
    private BufferedImage decodedImage = null;

    //Text
    private JTextArea text;
    private JScrollPane textScroll;

    //Sliders
    private JLabel sliderLabel;
    private JLabel sliderLabelR;
    private JLabel sliderLabelG;
    private JLabel sliderLabelB;
    private JSlider sliderR;
    private JSlider sliderG;
    private JSlider sliderB;

    public DecodedWindow(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        prepareMainWindow();
    }

    public void prepareMainWindow(){

        //Initialize panels
        optionsPanel = new JPanel();
        decodedImagePanel = new JPanel();
        textPanel = new JPanel();
        slidersPanel = new JPanel();

        //Initialize option buttons
        openedFile = new JButton("Wczytaj obraz");
        openedFile.setBounds(100, height - 160, 250, 100);
        openedFile.addActionListener(this);

        decodedFile = new JButton("Dekoduj");
        decodedFile.setBounds(420, height - 160, 250, 100);
        decodedFile.addActionListener(this);

        optionsPanel.add(openedFile);
        optionsPanel.add(decodedFile);
        optionsPanel.setBounds(0, height - 175, width, 175);
        optionsPanel.setBackground(new Color(57, 59, 61));

        //Decoded Image
        decodedImageLabel = new JLabel("Zdjęcie zakodowane");
        decodedImageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        decodedImageLabel.setForeground(new Color(240, 240, 240));
        decodedImagePanel.add(decodedImageLabel);

        decodedImageHolder = new JLabel();
        decodedImageScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        decodedImageScroll.getViewport().add(decodedImageHolder);
        decodedImageScroll.setBounds(width / 3, 5, width / 3 - 20, height/2 + 20);
        decodedImagePanel.add(decodedImageScroll);

        decodedImagePanel.setBounds(width / 3, 5, width / 3 - 20, height/2 + 20);
        decodedImagePanel.setBackground(new Color(83, 86, 89));

        //Text panel
        text = new JTextArea();
        text.setBackground(new Color(83, 86, 89));
        text.setForeground(new Color(240,240,240));
        text.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setMargin(new Insets(10, 10, 10, 10));

        textScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScroll.getViewport().add(text);
        textScroll.setBounds(5, 5, width / 3 - 10, height/2 + 20);
        textPanel.add(textScroll);
        textPanel.setBounds(5, 5, width / 3 - 10, height/2 + 20);


        //Sliders
        sliderLabel = new JLabel("Wybierz kanały RGB");
        sliderLabel.setForeground(new Color(240,240,240));

        sliderLabelR = new JLabel("R");
        sliderLabelR.setBounds(width - width / 3, 50, 10, 30);
        sliderLabelR.setForeground(new Color(240,240,240));
        sliderR = new JSlider(0, 7, 0);
        sliderR.setBounds(width - width / 3 + 30, 50, 200, 40);
        sliderR.setBackground(new Color(57, 59, 61));
        sliderR.setForeground(new Color(240,240,240));
        sliderR.setMajorTickSpacing(1);
        sliderR.setPaintLabels(true);

        sliderLabelG = new JLabel("G");
        sliderLabelG.setBounds(width - width / 3, 100, 10, 30);
        sliderLabelG.setForeground(new Color(240,240,240));
        sliderG = new JSlider(0, 7, 0);
        sliderG.setBounds(width - width / 3 + 30, 100, 200, 40);
        sliderG.setBackground(new Color(57, 59, 61));
        sliderG.setForeground(new Color(240,240,240));
        sliderG.setMajorTickSpacing(1);
        sliderG.setPaintLabels(true);

        sliderLabelB = new JLabel("B");
        sliderLabelB.setBounds(width - width / 3, 150, 10, 30);
        sliderLabelB.setForeground(new Color(240,240,240));
        sliderB = new JSlider(0, 7, 0);
        sliderB.setBounds(width - width / 3 + 30, 150, 200, 40);
        sliderB.setBackground(new Color(57, 59, 61));
        sliderB.setForeground(new Color(240,240,240));
        sliderB.setMajorTickSpacing(1);
        sliderB.setPaintLabels(true);

        slidersPanel.setBounds(width - width / 3 - 20, 5, width / 3, height / 2 + 20);
        slidersPanel.setBackground(new Color(83, 86, 89));

        slidersPanel.add(sliderLabel);
        slidersPanel.add(sliderR);
        slidersPanel.add(sliderG);
        slidersPanel.add(sliderB);

        frame = new JFrame(title);
        frame.setBackground(new Color(57, 59, 61));
        frame.setSize(new Dimension(width, height));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        frame.getContentPane().setBackground(new Color(57, 59, 61));
        frame.setVisible(false);

        frame.add(openedFile);
        frame.add(decodedFile);
        frame.add(optionsPanel);

        frame.add(decodedImagePanel);
        frame.add(decodedImageScroll);
        frame.add(decodedImageScroll);

        frame.add(textScroll);
        frame.add(textPanel);

        //Slider
        frame.add(sliderR);
        frame.add(sliderLabelR);
        frame.add(sliderG);
        frame.add(sliderLabelG);
        frame.add(sliderB);
        frame.add(sliderLabelB);
        frame.add(slidersPanel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == openedFile)
            openImage();

        if(e.getSource() == decodedFile)
            decodeMessage();
    }

    private void openImage() {
        File fileDialog = FileManager.showFileDialog(frame, true);
        if(fileDialog == null)
            return;
        try {
            decodedImage = ImageIO.read(fileDialog);
            decodedImageHolder.setIcon(new ImageIcon(decodedImage));
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    private void decodeMessage() {

        int channelR = sliderR.getValue();
        int channelG = sliderG.getValue();
        int channelB = sliderB.getValue();

        String textMessageBits = "";
        String textMessage = "";
        String endMessage = "";
        CurrentColor color = CurrentColor.red;

        int bitsCounter = 0;
        int checker = 0;

        if(decodedImage == null) {
            JOptionPane.showMessageDialog(frame, "Wybierz bitmapę.",
                    "Dekodowanie - błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for(int i = 0; i < decodedImage.getWidth(); i++) {
            for (int j = 0; j < decodedImage.getHeight(); j++) {

                int pixel = decodedImage.getRGB(i, j);


                if (color == CurrentColor.red) {
                    textMessageBits += (1 & (pixel >> (16 + channelR)));
                    color = CurrentColor.green;
                } else if (color == CurrentColor.green) {
                    textMessageBits += (1 & (pixel >> (8 + channelG)));
                    color = CurrentColor.blue;
                } else {
                    textMessageBits += (1 & (pixel >> channelB));
                    color = CurrentColor.red;
                }

                if ((bitsCounter + 1)%8==0) {

                    int charCode = Integer.parseInt(textMessageBits, 2);
                    char letter = (char)charCode;

                    if(endMessage.equals("###")){
                        text.setText(textMessage);
                        return;
                    }

                    if(letter == '#' && checker == 0){
                        endMessage += letter;
                        checker++;
                    }else if(letter == '#' && checker == 1){
                        endMessage += letter;
                        checker++;
                    }else if(letter == '#' && checker == 2) {
                        endMessage += letter;
                        checker++;
                    }else{
                        checker = 0;
                        endMessage = "";
                    }

                    if(letter != '#')
                        textMessage += (char)(letter);

                    textMessageBits = "";
                }
                bitsCounter++;
            }
        }
    }

    public JFrame getFrame(){
        return frame;
    }
}
