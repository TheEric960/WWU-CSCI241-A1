
package sort;

public class Sorts {

   // maintains a count of comparisons performed by this Sorts object
  private int comparisonCount;

  public int getComparisonCount() {
    return comparisonCount;
  }

  public void resetComparisonCount() {
    comparisonCount = 0;
  }

  /** Sorts A[start..end] in place using insertion sort
    * Precondition: 0 <= start <= end <= A.length */
  public void insertionSort(int[] A, int start, int end) {
    for (int i = 1; i < A.length; i++) {
      int j = i;

      while (j > 0 && A[j - 1] > A[j]) {
          swap(A, j, j - 1);
          j--;
      }
    }
  }

  /** Partitions A[start..end] around the pivot A[pivIndex]; returns the
   *  pivot's new index.
   *  Precondition: start <= pivIndex < end
   *  Postcondition: If partition returns i, then
   *  A[start..i] <= A[i] <= A[i+1..end] 
   **/
  public int partition(int[] A, int start, int end, int pivIndex) {
    swap(A, pivIndex, start);  // get pivot to start to partition
    int j = start + 1;

    for (int i = start + 1; i < end; i++) {
      if (A[i] < A[start]) {
        swap(A, j, i);
        j++;
      }
    }

    swap(A, start, j - 1);
    return j - 1;
  }

  /** use quicksort to sort the subarray A[start..end] */
  public void quickSort(int[] A, int start, int end) {
    // TODO
  }

  /** merge the sorted subarrays A[start..mid] and A[mid..end] into
   *  a single sorted array in A. */
  public void merge(int[] A, int start, int mid, int end) {
      // TODO
  }

  /** use mergesort to sort the subarray A[start..end] */
  public void mergeSort(int[] A, int start, int end) {
    // TODO
  }

  /** Sort A using LSD radix sort. Uses counting sort to sort on each digit*/
  public void radixSort(int[] A) {
    // TODO
  }

  /* return the 10^d's place digit of n */
  private int getDigit(int n, int d) {
    return (n / ((int)Math.pow(10, d))) % 10;
  }

  /** swap a[i] and a[j]
   *  pre: 0 < i, j < a.size
   *  post: values in a[i] and a[j] are swapped */
  public void swap(int[] a, int i, int j) {
    int tmp = a[i];
    a[i] = a[j];
    a[j] = tmp;
  }

}
