/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package benchmarksorts;

/**
 *
 * @author daniellegrodi
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class MyGUI extends JFrame implements ActionListener {
    private JButton chooseButton;
    private JFileChooser fileChooser;
    private JTable table;
    private static final int[] SIZES = {100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200 };
    double [] stats;
    private static final int RUNS = 40;

    
    public MyGUI() {
        // Set up the JFrame
        setTitle("My GUI");
        setSize(500, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Create the components
        chooseButton = new JButton("Choose File");
        chooseButton.addActionListener(this);
        fileChooser = new JFileChooser();
        table = new JTable();
        
        // Add the components to a JPanel
        JPanel panel = new JPanel();
        panel.add(chooseButton);
        
        // Add the JPanel and JTable to the JFrame
        add(panel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        
        // Make the JFrame visible
        setVisible(true);

    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        // Handle the button click
        if (e.getSource() == chooseButton) {
            int result = fileChooser.showOpenDialog(this);
            if (result == JFileChooser.APPROVE_OPTION) {
                File file = fileChooser.getSelectedFile();
               ArrayList<String> dataList = new ArrayList<String>();
               int [][] dataArray = new int[40][2];

        try {
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                data = data.replaceAll("\\[", "");
                data = data.replaceAll("\\]", "");
                data = data.replaceAll(",", "");
                data = data.replaceAll("  ", "");
                dataList.add(data);
            }
            scanner.close();
        } catch (FileNotFoundException t) {
            System.out.println("File not found!");
        }
        String[] objectSpace;
        Object[] objects = dataList.toArray();      
        for(int i=0; i < dataList.size(); i++){
            for(int j=0; j < 2; j++){
                objectSpace = ((String) objects[i]).split("\\s");
                dataArray[i][j] = Integer.parseInt(objectSpace[j]);
            }
        }
        //System.out.println(Arrays.deepToString(dataArray));
        stats = calculateStats(dataArray);
        
        for(int size: SIZES){
            System.out.printf("Size=%d Count=%.2f Time=%.2fms CoefCount=%.2f%% CoefTime=%.2f%%\n",size, stats[1],stats[2]/1000000.0, stats[5], stats[6]);
    }   }
            displayReport();
            }
        }
    
    public double[] calculateStats(int[][] dataArray){
        double countSum =0;
        double timeSum = 0;
        double cfCount;
        double cfTime;
        double sdCount =0;
        double sdTime = 0;
        double countMean;
        double timeMean;
        
        double[] Data = new double[dataArray.length];
        for( int[] data1: dataArray){
            countSum +=data1[0];
            timeSum += data1[1];
    }

        countMean = countSum/(double)RUNS;
        timeMean = timeSum/(double) RUNS;
        Data[1] = countMean;
        Data[2] = timeMean;
        for(int i =0; i< dataArray.length; i++){
            sdCount += Math.sqrt(Math.pow((Data[i]-countMean),2))/Data.length;
            Data[3] += sdCount;
            sdTime += Math.sqrt(Math.pow((Data[i]-timeMean),2))/Data.length;
            Data[4] += sdTime;
        }
        cfCount=(sdCount/countMean);
        cfTime = (sdTime/timeMean);
        Data[5] += cfCount;
        Data[6] += cfTime;
        return Data;
    }
    

    public void displayReport(){
        String[] columnNames = {"Data Set Size", "Avg Count", "Coef of Count", "Avg Time", "Coef of Time"};
        JPanel jpanel = new JPanel(new GridLayout(1,0));
        JTable tables = new JTable();
        DefaultTableModel model = (DefaultTableModel)tables.getModel();
        
        
        JScrollPane scrollpane = new JScrollPane(tables);
        tables.setFillsViewportHeight(true);
    
        jpanel.add(scrollpane);    
        jpanel.setOpaque(true);
    
        JFrame frame = new JFrame("Test Results");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(jpanel);
    
        frame.pack();
        frame.setVisible(true);          
    }
    public static void main(String[] args) {
        new MyGUI();
        
    }
}
