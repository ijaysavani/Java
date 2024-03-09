import java.util.ArrayList;

public class Heap<T extends Comparable<T>> {

    private ArrayList<T> elements; // ArrayList to store the elements of the heap
    private int size; // Current size of the heap
    private final int childCount; // Number of children each node can have

    /**
     * Constructs a new heap with initial capacity calculated as x^2.
     *
     * @param x the base value used to calculate the initial capacity of the heap.
     */
    public Heap(int childCount) {
        elements = new ArrayList<>(); // Initialize the elements ArrayList
        size = 0; // Initialize the size of the heap to 0
        this.childCount = childCount;
        validateChildCount(childCount);
    }

    /**
     * Validates that the childCount is a power of 2.
     *
     * @param childCount the number of children each node can have.
     * @throws IllegalArgumentException if the childCount is not a power of 2 or is less than 1.
     */
    private void validateChildCount(int childCount) {
        if (childCount <= 0 || (childCount & (childCount - 1)) != 0) {
            throw new IllegalArgumentException("childCount must be a power of 2 and greater than 0");
        }
    }

    /**
     * Inserts a new element into the heap.
     *
     * @param current the element to be inserted into the heap.
     * @throws HeapFullException if the heap is full and cannot accommodate more elements.
     */
    public void insert(T current) {
        elements.add(current); // Inserts the element into the heap
        size++; // Increase the size of the heap

        int index = size - 1; // Index of the newly inserted element
        while (index > 0) { // Maintains the heap property by swapping with parent if necessary
            int parent = (index - 1) / childCount; // Parent index of the current element
            if (elements.get(parent).compareTo(elements.get(index)) < 0) {
                swap(parent, index); // Swap elements if parent is less than the current element
                index = parent; // Update index to parent
            } else {
                break; // Break the loop if heap property is satisfied
            }
        }
    }

    /**
     * Removes and returns the maximum element from the heap.
     *
     * @return the maximum element in the heap.
     * @throws IllegalArgumentException if the heap is empty.
     */
    public T maxOut() {
        if (isEmpty()) {
            throw new IllegalArgumentException("Heap is Empty"); // Throws exception if the heap is empty
        }
        T toRemove = elements.get(0); // Maximum element to be removed
        elements.set(0, elements.get(size - 1)); // Move the last element to the root
        elements.remove(size - 1); // Remove the last element
        size--; // Decrease the size of the heap

        int index = 0; // Index of the root element
        while (index < size) { // Maintains the heap property by swapping with children if necessary
            int largestElement = index; // Index of the largest element among parent and its children
            for (int i = 1; i <= childCount; i++) { // Loop through each child of the parent
                int childIndex = index * childCount + i; // Calculate the child index
                if (childIndex < size && elements.get(childIndex).compareTo(elements.get(largestElement)) > 0) {
                    largestElement = childIndex; // Update largest element index if child is larger
                }
            }
            if (largestElement != index) {
                swap(index, largestElement); // Swap elements if necessary
                index = largestElement; // Update index to the largest child
            } else {
                break; // Break the loop if heap property is satisfied
            }
        }
        return toRemove; // Return the maximum element
    }

    /**
     * Swaps two elements in the heap.
     *
     * @param first  the index of the first element.
     * @param second the index of the second element.
     */
    public void swap(int first, int second) {
        T temp = elements.get(first); // Temporary variable to hold the value of the first element
        elements.set(first, elements.get(second)); // Assign the value of the second element to the first element
        elements.set(second, temp); // Assign the value of the temporary variable to the second element
    }

    /**
     * Checks if the heap is empty.
     *
     * @return true if the heap is empty, false otherwise.
     */
    public boolean isEmpty() {
        return size == 0; // Returns true if the size of the heap is 0, false otherwise
    }

    /**
     * Custom exception class to represent a situation where the heap is full.
     */
    public class HeapFullException {

        /**
         * Constructs a new HeapFullException with the specified detail message.
         *
         * @param message the detail message.
         */
        public HeapFullException(String message) {
            super(); // Call the constructor of the superclass with the specified detail message
        }
    }
}
