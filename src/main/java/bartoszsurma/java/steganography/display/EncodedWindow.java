package bartoszsurma.java.steganography.display;

import bartoszsurma.java.steganography.common.CurrentColor;
import bartoszsurma.java.steganography.fileutilities.FileManager;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class EncodedWindow implements ActionListener {
    private int width, height;
    private String title;

    private JFrame frame;

    //Panels
    private JPanel optionsPanel;
    private JPanel sourceImagePanel;
    private JPanel encodedImagePanel;
    private JPanel textPanel;
    private JPanel slidersPanel;

    //Options to optionsPanel
    private JButton openFile;
    private JButton encodeFile;
    private JButton saveEncodedFile;

    //Options to sourceImagePanel
    private JLabel sourceImageLabel;
    private JLabel sourceImageHolder;
    private JScrollPane sourceImageScroll;

    //Options to encodedImagePanel
    private JLabel encodedImageLabel;
    private JLabel encodedImageHolder;
    private JScrollPane encodedImageScroll;

    //Image
    private BufferedImage sourceImage = null;
    private BufferedImage encodedImage = null;

    //Text
    private JTextArea text;
    private JButton textLoad;
    private JScrollPane textScroll;

    //Sliders
    private JLabel sliderLabel;
    private JLabel sliderLabelR;
    private JLabel sliderLabelG;
    private JLabel sliderLabelB;
    private JSlider sliderR;
    private JSlider sliderG;
    private JSlider sliderB;


    public EncodedWindow(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        prepareMainWindow();
    }

    public void prepareMainWindow(){

        //Initialize panels
        optionsPanel = new JPanel();
        sourceImagePanel = new JPanel();
        encodedImagePanel = new JPanel();
        textPanel = new JPanel();
        slidersPanel = new JPanel();

        //Initialize option buttons
        openFile = new JButton("Wczytaj plik");
        openFile.setBounds(50, height - 160, 200, 50);
        openFile.addActionListener(this);

        encodeFile = new JButton("Zakoduj plik");
        encodeFile.setBounds(320, height - 160, 200, 50);
        encodeFile.addActionListener(this);

        saveEncodedFile = new JButton("Zapisz zakodowany plik");
        saveEncodedFile.setBounds(185, height - 100, 200, 50);
        saveEncodedFile.addActionListener(this);

        optionsPanel.add(openFile);
        optionsPanel.add(encodeFile);
        optionsPanel.add(saveEncodedFile);
        optionsPanel.setBounds(0, height - 175, width, 175);
        optionsPanel.setBackground(new Color(57, 59, 61));


        //Initialize Source image items
        sourceImageLabel = new JLabel("Zdjęcie źródłowe");
        sourceImageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        sourceImageLabel.setForeground(new Color(240, 240, 240));
        sourceImagePanel.add(sourceImageLabel);

        sourceImageHolder = new JLabel();
        sourceImageScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        sourceImageScroll.getViewport().add(sourceImageHolder);
        sourceImageScroll.setBounds(5, 320, width / 2 - 20, 300);
        sourceImagePanel.add(sourceImageScroll);

        sourceImagePanel.setBounds(5, 320, width / 2 - 20, 300);
        sourceImagePanel.setBackground(new Color(83, 86, 89));

        //Encoded Image
        encodedImageLabel = new JLabel("Zdjęcie zakodowane");
        encodedImageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));
        encodedImageLabel.setForeground(new Color(240, 240, 240));
        encodedImagePanel.add(encodedImageLabel);

        encodedImageHolder = new JLabel();
        encodedImageScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        encodedImageScroll.getViewport().add(encodedImageHolder);
        encodedImageScroll.setBounds(width / 2, 320, width / 2 - 20, 300);
        encodedImagePanel.add(encodedImageScroll);

        encodedImagePanel.setBounds(width / 2, 320, width / 2 - 20, 300);
        encodedImagePanel.setBackground(new Color(83, 86, 89));

        //Text panel
        text = new JTextArea();
        text.setBackground(new Color(83, 86, 89));
        text.setForeground(new Color(240,240,240));
        text.setFont(new Font("Times New Roman", Font.BOLD, 20));
        text.setMargin(new Insets(10, 10, 10, 10));
        textScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        textScroll.getViewport().add(text);
        textScroll.setBounds(5, 5, width - 160, 310);

        textLoad = new JButton("Wczytaj tekst");
        textLoad.setBounds(width - 150, 5, 130, 100);
        textLoad.addActionListener(this);

        textPanel.add(textLoad);
        textPanel.add(textScroll);
        //textPanel.setBounds(5, 5, width - 145, 310);


        //Sliders
        sliderLabel = new JLabel("Wybierz kanały RGB");
        sliderLabel.setForeground(new Color(240,240,240));

        sliderLabelR = new JLabel("R");
        sliderLabelR.setBounds(width - 142, 170, 10, 30);
        sliderLabelR.setForeground(new Color(240,240,240));
        sliderR = new JSlider(0, 7, 0);
        sliderR.setBounds(width - 130, 170, 100, 30);
        sliderR.setBackground(new Color(57, 59, 61));
        sliderR.setForeground(new Color(240,240,240));
        sliderR.setMajorTickSpacing(1);
        sliderR.setPaintLabels(true);

        sliderLabelG = new JLabel("G");
        sliderLabelG.setBounds(width - 142, 210, 10, 30);
        sliderLabelG.setForeground(new Color(240,240,240));
        sliderG = new JSlider(0, 7, 0);
        sliderG.setBounds(width - 130, 210, 100, 30);
        sliderG.setBackground(new Color(57, 59, 61));
        sliderG.setForeground(new Color(240,240,240));
        sliderG.setMajorTickSpacing(1);
        sliderG.setPaintLabels(true);

        sliderLabelB = new JLabel("B");
        sliderLabelB.setBounds(width - 142, 250, 10, 30);
        sliderLabelB.setForeground(new Color(240,240,240));
        sliderB = new JSlider(0, 7, 0);
        sliderB.setBounds(width - 130, 250, 100, 30);
        sliderB.setBackground(new Color(57, 59, 61));
        sliderB.setForeground(new Color(240,240,240));
        sliderB.setMajorTickSpacing(1);
        sliderB.setPaintLabels(true);

        slidersPanel.setBounds(width - 150, 115, 130, 200);
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

        //Buttons
        frame.add(openFile);
        frame.add(encodeFile);
        frame.add(saveEncodedFile);
        frame.add(optionsPanel);

        //Images
        frame.add(sourceImagePanel);
        frame.add(encodedImagePanel);
        frame.add(sourceImageScroll);
        frame.add(encodedImageScroll);

        //Text
        frame.add(textLoad);
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
        if(e.getSource() == openFile)
            openImage();

        if(e.getSource() == encodeFile)
            embedMessage();

        if(e.getSource() == saveEncodedFile)
            saveImage();

        if(e.getSource() == textLoad)
            openText();
    }

    private void openImage() {
        File fileDialog = FileManager.showFileDialog(frame, true);
        if(fileDialog == null)
            return;
        try {
            sourceImage = ImageIO.read(fileDialog);
            sourceImageHolder.setIcon(new ImageIcon(sourceImage));
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    private void openText(){
        File textDialog = FileManager.showTextDialog(frame);
        try {
            BufferedReader br = new BufferedReader(new FileReader(textDialog));
            StringBuilder fileText = new StringBuilder();

            String line = "";
            while((line = br.readLine()) != null) {
                fileText.append(line + "\n");
            }
            text.setText(fileText.toString());
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    private void embedMessage() {
        String textMessage = text.getText();

        if(sourceImage == null)
            return;

        if(sourceImage.getHeight() < 32){
            JOptionPane.showMessageDialog(frame, "Obraz musi mieć przynajmniej 32 piksele wysokości.",
                    "Btmapa - błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        encodedImage = sourceImage.getSubimage(0,0,
                sourceImage.getWidth(),sourceImage.getHeight());

        embedMessage(encodedImage, textMessage);
        JLabel l = new JLabel(new ImageIcon(encodedImage));
        encodedImageScroll.getViewport().add(l);
    }

    private void embedMessage(BufferedImage image, String textMessage) {
        int messageLength = textMessage.length();
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();
        int imageSize = imageWidth * imageHeight;

        if(messageLength * 8 > imageSize - 56) { //
            JOptionPane.showMessageDialog(frame, "Wiadomość jest zbyt długa.",
                    "Wiadomość - błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        int channelR = sliderR.getValue();
        int channelG = sliderG.getValue();
        int channelB = sliderB.getValue();

        String textMessageBit = "";

        for(int i = 0; i < textMessage.length(); i++) {

            textMessageBit += String.format("%8s", Integer.toBinaryString(textMessage.charAt(i))).replace(" ", "0");
        }

        textMessageBit += "001000110010001100100011"; //Znaki ### binarnie
        String encodedSign = "01000010011000010111001001110100"; // Podpis "Bart"

        embedInteger(image, textMessageBit, channelR, channelG, channelB);
        codeSign(encodedSign, image, 7);
    }

    private void codeSign(String sign, BufferedImage image, int channel){
        int counter = 0;
        for(int i = image.getHeight() - sign.length(); i < image.getHeight(); i++){
            int pixel = image.getRGB(image.getWidth() - 1, i);
            int bit = sign.charAt(counter);

            int R = (pixel) & (255 << 16);
            int G = (pixel) & (255 << 8);
            int B = 0;

            if(bit == '1')
                B = pixel | (1 << channel);
            else
                B = (pixel & ~(1 << channel));

            pixel = (255 << 24) | R | G | B;
            image.setRGB(image.getWidth() - 1, i, pixel);
            counter++;
        }
    }

    private void embedInteger(BufferedImage image, String textBits, int channelR, int channelG, int channelB) {
        int bitsCounter = 0;
        CurrentColor color = CurrentColor.red;

        for(int i=0; i < image.getWidth(); i++) {
            for(int j=0; j < image.getHeight(); j++) {

                int pixel = image.getRGB(i, j);
                int bit = textBits.charAt(bitsCounter);

                if(color == CurrentColor.red) {

                    int R = 0;
                    int G = (pixel) & (255 << 8);
                    int B = (pixel) & (255);

                    if(bit == '1')
                        R = pixel | (1 << (16 + channelR));
                    else {
                        R = ((pixel & ~(1 << (16 + channelR))));
                    }

                    pixel = (255 << 24) | R | G | B;
                    color = CurrentColor.green;

                }else if(color == CurrentColor.green) {
                    int R = (pixel) & (255 << 16);
                    int G = 0;
                    int B = (pixel) & (255);

                    if(bit == '1')
                        G = pixel | (1 << (8 + channelG));
                    else
                        G = ((pixel & ~(1 << (8 + channelG))));

                    pixel = (255 << 24) | R | G | B;
                    color = CurrentColor.blue;

                }else {
                    int R = (pixel) & (255 << 16);
                    int G = (pixel) & (255 << 8);
                    int B = 0;

                    if(bit == '1')
                        B = pixel | (1 << channelB);
                    else
                        B = (pixel & ~(1 << channelB));

                    pixel = (255 << 24) | R | G | B;
                    color = CurrentColor.red;

                }

                image.setRGB(i, j, pixel);

                if(bitsCounter == textBits.length() - 1){
                    return;
                }
                bitsCounter++;
            }
        }
    }

    private void saveImage() {
        if(encodedImage == null) {
            JOptionPane.showMessageDialog(frame, "Wybierz bitmapę.",
                    "Zapis - błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }
        File fileDialog = FileManager.showFileDialog(frame, false);
        String name = fileDialog.getName();
        String extention = name.substring(name.lastIndexOf(".")+1).toLowerCase();
        if(!extention.equals("png") && !extention.equals("bmp")) {
            extention = "png";
            fileDialog = new File(fileDialog.getAbsolutePath()+".png");
        }
        try {
            if(fileDialog.exists()) fileDialog.delete();
            ImageIO.write(encodedImage, extention.toUpperCase(), fileDialog);
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    public JFrame getFrame(){
        return frame;
    }
}
