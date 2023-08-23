/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package benchmarksorts;

/**
 *
 * @author danielle moore
 */


import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;


public class BenchmarkSorts {
    private static final int[] SIZES = { 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000, 1100, 1200 };
    private static final int RUNS = 40;
    private static final Random RANDOM = new Random();
    public static void main(String[] args) {
     try {
            FileWriter selectionSortWriter = new FileWriter("selection_sort_data.txt");
            FileWriter pancakeSortWriter = new FileWriter("pancake_sort_data.txt");
            for (int size : SIZES) {
                long[][] selectionSortData = new long[RUNS][4];
                long[][] pancakeSortData = new long[RUNS][4];
                for (int i = 0; i < RUNS; i++) {
                    int[] data = generateData(size);
                    selectionSortData[i] = benchmarkSort(new SelectionSort(data));
                    pancakeSortData[i] = benchmarkSort(new PancakeSort(data));
                    assert isSorted(data); 
                    
                }
                double[] selectionSortStats = calculateStats(selectionSortData);
                double[] pancakeSortStats = calculateStats(pancakeSortData);
                
                selectionSortWriter.write(String.format("%s\n", size, Arrays.deepToString(selectionSortData)));
                pancakeSortWriter.write(String.format("%s\n", size, Arrays.deepToString(pancakeSortData)));
                System.out.printf("Size=%d S-count=%.2f S-time=%.2fms S-coefCount=%.2f%% S-coefTime=%.2f%% P-count=%.2f P-time=%.2fms P-coefCount=%.2f%% P-coefTime=%.2f%%\n",
                        size, selectionSortStats[0], selectionSortStats[1] / 1_000_000.0, selectionSortStats[4], selectionSortStats[5],
                        pancakeSortStats[0], pancakeSortStats[1] / 1_000_000.0, pancakeSortStats[4], pancakeSortStats[5]);
            }
            selectionSortWriter.close();
            pancakeSortWriter.close();
        } catch (IOException e) {
        }
    }
    
    private static int[] generateData(int size) {
        int[] data = new int[size];
        for (int i = 0; i < size; i++) {
            data[i] = RANDOM.nextInt(size);
        }
        return data;
    }
    
    private static long[] benchmarkSort(AbstractSort sorter) {
        sorter.startSort();
        sorter.sort();
        sorter.endSort();
        assert isSorted(sorter.getData());
        return new long[] { sorter.getCount(), sorter.getTime() };
    }
    
    private static boolean isSorted(int[] data) {
        for (int i = 1; i < data.length; i++) {
            if (data[i] < data[i - 1]) {
                throw new RuntimeException("Data not sorted: " + Arrays.toString(data));
            }
        }
        return true;
    }
    
    private static double[] calculateStats(long[][] data) {
        double countSum = 0;
        double timeSum = 0;
        double[] Data;
        double cfCount = 0;
        double cfTime = 0;
        double sdCount=0;
        double sdTime = 0;
        double countMean;
        double timeMean;
        
        Data = new double[data.length];
        for (long[] data1 : data) {
            countSum += data1[0];
            timeSum += data1[1];
        }
                countMean = countSum / (double) RUNS;
                timeMean = timeSum / (double) RUNS;
                Data[0] = countMean;
                Data[1] = timeMean;
                for(int i = 0; i < data.length; i++){
            sdCount += Math.sqrt(Math.pow((Data[i]-countMean), 2))/Data.length;
            Data[2] += sdCount;
            sdTime += Math.sqrt(Math.pow((Data[i]-timeMean), 2));
            sdTime = sdTime/Data.length;
            Data[3] += sdTime;
                }
            cfCount = (sdCount/countMean);
            cfTime = (sdTime/timeMean);
            Data[4] += cfCount;
            Data[5] += cfTime;
       return Data; 
      }
}
