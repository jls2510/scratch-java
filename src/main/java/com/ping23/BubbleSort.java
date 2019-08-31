package com.ping23;

public class BubbleSort
{
    private static int swapCounter = 0;
    private static int unswappedCounter = 0;
    
    public static void main(String[] args)
    {
        int[] arrayToSort = new int[100];
        for (int index = 0; index < arrayToSort.length; index++)
        {
            arrayToSort[index] = (int) Math.round(Math.random() * 100);
        }
        
        System.out.println("*** arrayToSort prior to sort");
        for (int index = 0; index < arrayToSort.length; index++) {
            System.out.print(arrayToSort[index] + " ");
        }
        System.out.println();
        
        swapCounter = 0;
        unswappedCounter = 0;
        bubbleSort(arrayToSort);
        
        System.out.println("*** arrayToSort after sort");
        for (int index = 0; index < arrayToSort.length; index++) {
            System.out.print(arrayToSort[index] + " ");
        }
        System.out.println();

        System.out.println("swapCounter = " + swapCounter);
        System.out.println("unswappedCounter = " + unswappedCounter);
        
        

    }
    
    public static int[] bubbleSort(int[] array) {
        
        boolean swapped = true;
        int upperBound = array.length - 1;
        int tmp;

        // continue until no more swaps occur
        while (swapped) {
            swapped = false;

            // move forwards thru the array
            for (int index = 0; index < upperBound; index++) {
                // move largest value to end of array
                if (array[index] > array[index + 1]) {
                    tmp = array[index];
                    array[index] = array[index + 1];
                    array[index + 1] = tmp;
                    swapped = true;
                    swapCounter++;
                } // if
                else {
                    unswappedCounter++;
                }
            } // for

            // decrement upperBound: work backwards from end of array
            upperBound--;

        } // while
        
        return array;
    } // bubbleSort()

}
