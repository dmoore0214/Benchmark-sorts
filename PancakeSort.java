/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/File.java to edit this template
 */
package benchmarksorts;

/**
 *
 * @author daniellegrodi
 */
public class PancakeSort extends AbstractSort{
  private int[] arr;
  private long time;
  private long startTime, endTime;
  private int count = 0;
  public PancakeSort(int [] arr){
      this.arr = arr;
  }
  
  @Override
  public void sort(){
      startTime = System.nanoTime();
      int n = arr.length;
      for(int curr_size = n; curr_size > 1; --curr_size) {
      int mi = findMax(arr, curr_size);
      if (mi != curr_size-1){
          flip(arr,mi);
          count++;
          flip(arr, curr_size-1);
      }

    }
      endTime = System.nanoTime();
  }
  
  private void flip(int array[], int i){
      int temp, start = 0;
      while(start < i){
          temp = array[start];
          array[start] = array[i];
          array[i]=temp;
          start++;
          i--;
      }
  }
  private int findMax(int array[], int n){
      int mi, i;
      for(mi=0, i=0; i<n; i++){
          if(array[i] > array[mi])
              mi=i;  
      }
      return mi;
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
    public int getCount() {
        return count;
    }

}
