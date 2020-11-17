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

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;

public class TestWindow implements ActionListener {
    private final int width, height;
    private final String title;

    private JFrame frame;

    //Panels
    private JPanel optionsPanel;
    private JPanel informationsPanel;
    private JPanel imagePanel;
    private JPanel slidersPanel;

    //Options
    private JButton openedImage;
    private JButton testCapacity;
    private JButton checkAccessImage;
    private JButton circleDiagram;

    //Informations
    private JLabel informationLabel;
    private JLabel imageStateLabel;
    private JLabel imageState;

    private JLabel entireSpaceLabel;
    private JLabel entireSpaceSigns;
    private JLabel entireSpaceBytes;
    private JLabel entireSpaceKiloBytes;
    private JLabel entireSpaceMegaBytes;

    private JLabel busySpaceLabel;
    private JLabel busySpaceSigns;
    private JLabel busySpaceBytes;
    private JLabel busySpaceKiloBytes;
    private JLabel busySpaceMegaBytes;
    private JLabel busySpacePercents;

    private JLabel freeSpaceLabel;
    private JLabel freeSpaceSigns;
    private JLabel freeSpaceBytes;
    private JLabel freeSpaceKiloBytes;
    private JLabel freeSpaceMegaBytes;
    private JLabel freeSpacePercents;

    //Options to imagePanel
    private JLabel imageLabel;
    private JLabel imageHolder;
    private JScrollPane imageScroll;

    //Image
    private BufferedImage loadedImage;

    //Sliders
    private JLabel sliderLabel;
    private JLabel sliderLabelR;
    private JLabel sliderLabelG;
    private JLabel sliderLabelB;
    private JSlider sliderR;
    private JSlider sliderG;
    private JSlider sliderB;

    //Chart
    private DefaultPieDataset dataChart;
    private double chartBusySpace;
    private double chartFreeSpace;


    public TestWindow(int width, int height, String title){
        this.width = width;
        this.height = height;
        this.title = title;

        prepareMainWindow();
    }

    public void prepareMainWindow(){

        //Initialize panels
        optionsPanel = new JPanel();
        informationsPanel = new JPanel();
        imagePanel = new JPanel();
        slidersPanel = new JPanel();

        //Initialize options
        openedImage = new JButton("Wczytaj obraz");
        openedImage.setBounds(20, height - 160, 260, 50);
        openedImage.addActionListener(this);

        testCapacity = new JButton("Sprawdź pojemność nośnika");
        testCapacity.setBounds(400, height - 160, 260, 50);
        testCapacity.addActionListener(this);

        checkAccessImage = new JButton("Sprawdź dostępność nośnika");
        checkAccessImage.setBounds(20, height - 100, 260, 50);
        checkAccessImage.addActionListener(this);

        circleDiagram = new JButton("Wyświetl diagram kołowy");
        circleDiagram.setBounds(400, height - 100, 260, 50);
        circleDiagram.addActionListener(this);

        optionsPanel.add(openedImage);
        optionsPanel.add(testCapacity);
        optionsPanel.add(checkAccessImage);
        optionsPanel.add(circleDiagram);
        optionsPanel.setBounds(0, height - 170, width, 170);
        optionsPanel.setBackground(new Color(57, 59, 61));


        //Initialize informations
        informationLabel = new JLabel("Informacje");
        informationLabel.setForeground(new Color(240, 240, 240));
        informationLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        imageStateLabel = new JLabel("Stan obrazu: ");
        imageStateLabel.setBounds(width / 3 + 35, 40, 100, 30);
        imageStateLabel.setForeground(new Color(240, 240, 240));
        imageStateLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        imageState = new JLabel();
        imageState.setBounds(width / 3 + 130, 40, 100, 30);
        imageState.setForeground(new Color(240, 240, 240));
        imageState.setFont(new Font("Times New Roman", Font.BOLD, 14));

        entireSpaceLabel = new JLabel("Całkowita pojemność obrazu: ");
        entireSpaceLabel.setBounds(width / 3 + 35, 60, 190, 30);
        entireSpaceLabel.setForeground(new Color(240, 240, 240));
        entireSpaceLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        entireSpaceSigns = new JLabel("... znaki");
        entireSpaceSigns.setBounds(width / 3 + 65, 80, 100, 30);
        entireSpaceSigns.setForeground(new Color(240, 240, 240));
        entireSpaceSigns.setFont(new Font("Times New Roman", Font.BOLD, 12));

        entireSpaceBytes = new JLabel("... B");
        entireSpaceBytes.setBounds(width / 3 + 65, 92, 100, 30);
        entireSpaceBytes.setForeground(new Color(240, 240, 240));
        entireSpaceBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        entireSpaceKiloBytes = new JLabel("... kB");
        entireSpaceKiloBytes.setBounds(width / 3 + 65, 104, 100, 30);
        entireSpaceKiloBytes.setForeground(new Color(240, 240, 240));
        entireSpaceKiloBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        entireSpaceMegaBytes = new JLabel("... MB");
        entireSpaceMegaBytes.setBounds(width / 3 + 65, 116, 100, 30);
        entireSpaceMegaBytes.setForeground(new Color(240, 240, 240));
        entireSpaceMegaBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        busySpaceLabel = new JLabel("Zajęte miejsce: ");
        busySpaceLabel.setBounds(width / 3 + 35, 148, 100, 30);
        busySpaceLabel.setForeground(new Color(240, 240, 240));
        busySpaceLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        busySpaceSigns = new JLabel("... znaki");
        busySpaceSigns.setBounds(width / 3 + 65, 168, 100, 30);
        busySpaceSigns.setForeground(new Color(240, 240, 240));
        busySpaceSigns.setFont(new Font("Times New Roman", Font.BOLD, 12));

        busySpaceBytes = new JLabel("... B");
        busySpaceBytes.setBounds(width / 3 + 65, 180, 100, 30);
        busySpaceBytes.setForeground(new Color(240, 240, 240));
        busySpaceBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        busySpaceKiloBytes = new JLabel("... kB");
        busySpaceKiloBytes.setBounds(width / 3 + 65, 192, 100, 30);
        busySpaceKiloBytes.setForeground(new Color(240, 240, 240));
        busySpaceKiloBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        busySpaceMegaBytes = new JLabel("... MB");
        busySpaceMegaBytes.setBounds(width / 3 + 65, 204, 100, 30);
        busySpaceMegaBytes.setForeground(new Color(240, 240, 240));
        busySpaceMegaBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        busySpacePercents = new JLabel("... %");
        busySpacePercents.setBounds(width / 3 + 65, 216, 100, 30);
        busySpacePercents.setForeground(new Color(240, 240, 240));
        busySpacePercents.setFont(new Font("Times New Roman", Font.BOLD, 12));

        freeSpaceLabel = new JLabel("Wolne miejsce: ");
        freeSpaceLabel.setBounds(width / 3 + 35, 236, 100, 30);
        freeSpaceLabel.setForeground(new Color(240, 240, 240));
        freeSpaceLabel.setFont(new Font("Times New Roman", Font.BOLD, 14));

        freeSpaceSigns = new JLabel("... znaki");
        freeSpaceSigns.setBounds(width / 3 + 65, 256, 100, 30);
        freeSpaceSigns.setForeground(new Color(240, 240, 240));
        freeSpaceSigns.setFont(new Font("Times New Roman", Font.BOLD, 12));

        freeSpaceBytes = new JLabel("... B");
        freeSpaceBytes.setBounds(width / 3 + 65, 268, 100, 30);
        freeSpaceBytes.setForeground(new Color(240, 240, 240));
        freeSpaceBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        freeSpaceKiloBytes = new JLabel("... kB");
        freeSpaceKiloBytes.setBounds(width / 3 + 65, 280, 100, 30);
        freeSpaceKiloBytes.setForeground(new Color(240, 240, 240));
        freeSpaceKiloBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        freeSpaceMegaBytes = new JLabel("... MB");
        freeSpaceMegaBytes.setBounds(width / 3 + 65, 292, 100, 30);
        freeSpaceMegaBytes.setForeground(new Color(240, 240, 240));
        freeSpaceMegaBytes.setFont(new Font("Times New Roman", Font.BOLD, 12));

        freeSpacePercents = new JLabel("... %");
        freeSpacePercents.setBounds(width / 3 + 65, 304, 100, 30);
        freeSpacePercents.setForeground(new Color(240, 240, 240));
        freeSpacePercents.setFont(new Font("Times New Roman", Font.BOLD, 12));

        informationsPanel.add(informationLabel);
        informationsPanel.add(imageStateLabel);
        informationsPanel.add(imageState);

        informationsPanel.add(entireSpaceLabel);
        informationsPanel.add(entireSpaceSigns);
        informationsPanel.add(entireSpaceBytes);
        informationsPanel.add(entireSpaceKiloBytes);
        informationsPanel.add(entireSpaceMegaBytes);

        informationsPanel.add(busySpaceLabel);
        informationsPanel.add(busySpaceSigns);
        informationsPanel.add(busySpaceBytes);
        informationsPanel.add(busySpaceKiloBytes);
        informationsPanel.add(busySpaceMegaBytes);
        informationsPanel.add(busySpacePercents);

        informationsPanel.add(freeSpaceLabel);
        informationsPanel.add(freeSpaceSigns);
        informationsPanel.add(freeSpaceBytes);
        informationsPanel.add(freeSpaceKiloBytes);
        informationsPanel.add(freeSpaceMegaBytes);
        informationsPanel.add(freeSpacePercents);

        informationsPanel.setBounds(width / 3 + 30, 5, width - width / 3 - 250, height - 175);
        informationsPanel.setBackground(new Color(83, 86, 89));


        //Initialize image
        imageLabel = new JLabel("Wczytany obraz");
        imageLabel.setForeground(new Color(240, 240, 240));
        imageLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        imageHolder = new JLabel();
        imageScroll = new JScrollPane(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        imageScroll.getViewport().add(imageHolder);
        imageScroll.setBounds(5, 5, width / 3 + 20, height - 180);
        imagePanel.add(imageScroll);

        imagePanel.add(imageLabel);
        imagePanel.setBounds(5, 5, width / 3 + 20, height - 175);
        imagePanel.setBackground(new Color(83, 86, 89));


        //Sliders
        sliderLabel = new JLabel("Wybierz RGB");
        sliderLabel.setForeground(new Color(240,240,240));
        sliderLabel.setFont(new Font("Times New Roman", Font.BOLD, 20));

        sliderLabelR = new JLabel("Red");
        sliderLabelR.setBounds(width - width / 3 + 40, 55, 100, 30);
        sliderLabelR.setForeground(new Color(240,240,240));
        sliderR = new JSlider(0, 7, 0);
        sliderR.setBounds(width - width / 3 + 30, 80, 170, 40);
        sliderR.setBackground(new Color(57, 59, 61));
        sliderR.setForeground(new Color(240,240,240));
        sliderR.setMajorTickSpacing(1);
        sliderR.setPaintLabels(true);

        sliderLabelG = new JLabel("Green");
        sliderLabelG.setBounds(width - width / 3 + 40, 135, 100, 30);
        sliderLabelG.setForeground(new Color(240,240,240));
        sliderG = new JSlider(0, 7, 0);
        sliderG.setBounds(width - width / 3 + 30, 160, 170, 40);
        sliderG.setBackground(new Color(57, 59, 61));
        sliderG.setForeground(new Color(240,240,240));
        sliderG.setMajorTickSpacing(1);
        sliderG.setPaintLabels(true);

        sliderLabelB = new JLabel("Blue");
        sliderLabelB.setBounds(width - width / 3 + 40, 215, 100, 30);
        sliderLabelB.setForeground(new Color(240,240,240));
        sliderB = new JSlider(0, 7, 0);
        sliderB.setBounds(width - width / 3 + 30, 240, 170, 40);
        sliderB.setBackground(new Color(57, 59, 61));
        sliderB.setForeground(new Color(240,240,240));
        sliderB.setMajorTickSpacing(1);
        sliderB.setPaintLabels(true);

        slidersPanel.setBounds(width - width / 3 + 18, 5, width / 3 - 40, height - 175);
        slidersPanel.setBackground(new Color(83, 86, 89));

        slidersPanel.add(sliderLabel);
        slidersPanel.add(sliderR);
        slidersPanel.add(sliderG);
        slidersPanel.add(sliderB);

        //Chart
        dataChart = new DefaultPieDataset();


        frame = new JFrame(title);
        frame.setBackground(new Color(57, 59, 61));
        frame.setSize(new Dimension(width, height));
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(new Color(57, 59, 61));
        frame.setVisible(false);

        //Add options
        frame.add(openedImage);
        frame.add(testCapacity);
        frame.add(checkAccessImage);
        frame.add(circleDiagram);
        frame.add(optionsPanel);

        //Add Informations
        frame.add(imageStateLabel);
        frame.add(imageState);

        frame.add(entireSpaceLabel);
        frame.add(entireSpaceSigns);
        frame.add(entireSpaceBytes);
        frame.add(entireSpaceKiloBytes);
        frame.add(entireSpaceMegaBytes);

        frame.add(busySpaceLabel);
        frame.add(busySpaceSigns);
        frame.add(busySpaceBytes);
        frame.add(busySpaceKiloBytes);
        frame.add(busySpaceMegaBytes);
        frame.add(busySpacePercents);

        frame.add(freeSpaceLabel);
        frame.add(freeSpaceSigns);
        frame.add(freeSpaceBytes);
        frame.add(freeSpaceKiloBytes);
        frame.add(freeSpaceMegaBytes);
        frame.add(freeSpacePercents);

        frame.add(informationsPanel);

        //Add image
        frame.add(imagePanel);
        frame.add(imageScroll);

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
        if(e.getSource() == openedImage)
            openImage();

        if(e.getSource() == testCapacity)
            testImageCapacity();

        if(e.getSource() == checkAccessImage)
            checkAccessibility();

        if(e.getSource() == circleDiagram)
            showCircleDiagram();
    }

    private void openImage(){
        File fileDialog = FileManager.showFileDialog(frame, true);
        if(fileDialog == null)
            return;
        try {
            loadedImage = ImageIO.read(fileDialog);
            imageHolder.setIcon(new ImageIcon(loadedImage));
        } catch(Exception ex) { ex.printStackTrace(); }
    }

    private void testImageCapacity(){
        checkAccessibility();
        if(loadedImage == null)
            return;
        double fileDimension = (loadedImage.getWidth() * loadedImage.getHeight() - 56) / 8; // 32 bits - sign; 24 bits - endMessage
        if(loadedImage != null && imageState.getText() == "Zakodowany") {
            entireSpaceSigns.setText(fileDimension + " znaki");
            entireSpaceBytes.setText(fileDimension + " B");
            entireSpaceKiloBytes.setText(Double.toString(fileDimension / 1000) + " kB");
            entireSpaceMegaBytes.setText(Double.toString( fileDimension / 1000000) + " MB");
        }

        if(checkBusyCapacity() > 0){
            double busySpace = (checkBusyCapacity() - 24) / 8;
            busySpaceSigns.setText(busySpace + " znaki");
            busySpaceBytes.setText(busySpace + " B");
            busySpaceKiloBytes.setText(Double.toString(busySpace / 1000) + " kB");
            busySpaceMegaBytes.setText(Double.toString(busySpace / 1000000) + " MB");
            busySpacePercents.setText(String.format("%.2f", (busySpace * 8 * 100) / (loadedImage.getWidth() * loadedImage.getHeight())) + "%");

            freeSpaceSigns.setText((fileDimension - busySpace) + " znaki");
            freeSpaceBytes.setText((fileDimension - busySpace) + " B");
            freeSpaceKiloBytes.setText(Double.toString((fileDimension - busySpace) / 1000) + " kB");
            freeSpaceMegaBytes.setText(Double.toString((fileDimension - busySpace) / 1000000) + " MB");
            freeSpacePercents.setText(String.format("%.2f", (fileDimension - busySpace) * 8 * 100 / (loadedImage.getWidth() * loadedImage.getHeight())) + "%");
            chartBusySpace = busySpace;
            chartFreeSpace = fileDimension - busySpace;
        }
    }

    private int checkBusyCapacity(){
        int channelR = sliderR.getValue();
        int channelG = sliderG.getValue();
        int channelB = sliderB.getValue();

        String textMessageBits = "";
        int checker = 0;
        String endMessage = "";

        int bitsCounter = 0;
        CurrentColor color = CurrentColor.red;

        for(int i = 0; i < loadedImage.getWidth(); i++) {
            for (int j = 0; j < loadedImage.getHeight(); j++) {

                int pixel = loadedImage.getRGB(i, j);

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

                if ((bitsCounter + 1) % 8 == 0) {

                    int charCode = Integer.parseInt(textMessageBits, 2);
                    char letter = (char) charCode;

                    if(endMessage.equals("###")){
                        return bitsCounter;
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

                    textMessageBits = "";
                }
                bitsCounter++;
            }
        }
        return 0;
    }

    private void checkAccessibility() {
        int channel = 7;
        String decodedSign = "";

        String sign = "01000010011000010111001001110100"; // Podpis "Bart"

        if(loadedImage == null){
            JOptionPane.showMessageDialog(frame, "Wybierz bitmapę.",
                    "Sprawdzanie - błąd", JOptionPane.ERROR_MESSAGE);
            return;
        }

        for (int i = loadedImage.getHeight() - sign.length(); i < loadedImage.getHeight(); i++) {
            int pixel = loadedImage.getRGB(loadedImage.getWidth() - 1, i);

            decodedSign += (1 & (pixel >> channel));
        }

        if(sign.equals(decodedSign)){
            imageState.setText("Zakodowany");
        }else{
            imageState.setText("Brak kodowania");
        }
    }

    private void showCircleDiagram(){
        dataChart.setValue("Zajęta pamięć", chartBusySpace);
        dataChart.setValue("Wolna pamięć", chartFreeSpace);

        if(chartBusySpace <= 0)
            return;

        JFreeChart chart = ChartFactory.createPieChart("Pamięć nośnika", dataChart, true, true, true);

        ChartFrame chartFrame = new ChartFrame("Pamięć nośnika - wykres kołowy", chart);
        chartFrame.setVisible(true);
        chartFrame.setSize(500, 500);
    }

    public JFrame getFrame(){
        return frame;
    }
}
