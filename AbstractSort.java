package benchmarksorts;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */


/**
 *
 * @author daniellegrodi
 */
public abstract class AbstractSort {
    private int count;
    private long startTime;
    
    public abstract void sort();
    
    public void startSort(){
        count =0;
        startTime = System.nanoTime();
    }
    
    public void endSort(){
        long endTime = System.nanoTime();
        setTime(endTime - startTime);
    }
    
    public void incrementCount() {
        count++;
    }
    
    public int getCount(){
        return count;
    }
    
    public abstract void setTime(long time);
    
    public abstract long getTime();

    int[] getData() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
