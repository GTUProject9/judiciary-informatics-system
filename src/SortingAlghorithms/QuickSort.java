package SortingAlghorithms;

import java.util.List;

/** Implements the quicksort algorithm. */
public class QuickSort {

    /** Sort the table using the quicksort algorithm.
     table contains Comparable objects.
     table is sorted.
     @param table The array to be sorted
     @param <T> type of array
     */
    public static <T extends Comparable<T>> void sort(List<T> table) {
        // Sort the whole table.
        quickSort(table, 0, table.size() - 1);
    }

    /** Sort a part of the table using the quicksort algorithm.
     The part of table from first through last is sorted.
     @param table The array to be sorted
     @param first The index of the low bound
     @param last The index of the high bound
     @param <T> type of array
     */
    private static <T extends Comparable<T>> void quickSort(List<T> table,
                                                            int first, int last) {
        if (first < last) { // There is data to be sorted.
            // Partition the table.
            int pivIndex = partition(table, first, last);
            // Sort the left half.
            quickSort(table, first, pivIndex - 1);
            // Sort the right half.
            quickSort(table, pivIndex + 1, last);
        }
    }

    /** Partition the table so that values from first to pivIndex
     are less than or equal to the pivot value, and values from
     pivIndex to last are greater than the pivot value.
     @param table The table to be partitioned
     @param first The index of the low bound
     @param last The index of the high bound
     @param <T> type of array
     @return The location of the pivot value
     */
    private static <T extends Comparable<T>> int partition(List<T> table,
                                                           int first, int last){
        // Select the first item as the pivot value.
        T pivot = table.get(first);
        int up = first;
        int down = last;
        do {
            while ((up < last) && (pivot.compareTo(table.get(up)) >= 0)) {
                up++;
            }
            // assert: up equals last or table[up] > pivot.
            while (pivot.compareTo(table.get(down)) < 0) {
                down--;
            }
            // assert: down equals first or table[down] <= pivot.
            if (up < down) { // if up is to the left of down.
                // Exchange table[up] and table[down].
                swap(table, up, down);
            }
        }while (up < down); // Repeat while up is left of down.
        // Exchange table[first] and table[down] thus putting the
        // pivot value where it belongs.
        swap(table, first, down);
        // Return the index of the pivot value.
        return down;
    }

    /**
     * Method swap
     * @param table array
     * @param i index1
     * @param j index2
     * @param <T> type of array
     */
    private static <T extends Comparable<T>>  void swap(List<T> table, int i, int j) {
        T temp = table.get(i);
        table.set(i, table.get(j));
        table.set(j, temp);
    }
}
