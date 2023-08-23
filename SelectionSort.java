package benchmarksorts;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */

/**
 *
 * @author Danielle Moore
 */

public class SelectionSort extends AbstractSort{
    private int[] arr;
    private long time;
    private long startTime, endTime;
    private int count =0;
    public SelectionSort(int[]arr){
        this.arr = arr;
    }
    
    @Override
    public void sort(){
        startTime = System.nanoTime();
         int n = arr.length;
        for(int i = 0; i <n; i++){
            int minIndex = i;
            for(int j = i+1; j<n; j++){
                if(arr[j] < arr[minIndex])
                    minIndex =j;
                
            }
            int temp = arr[minIndex];
            arr[minIndex]=arr[i];
            arr[i]=temp;
            count++;
        }
        endTime = System.nanoTime();
    }

    @Override
    public void setTime(long time) {
        this.time = endTime-startTime;
    }

    @Override
    public long getTime() {
        return time;
    }
    
    @Override
    public int getCount(){
        return count;
    }
    
}

   

