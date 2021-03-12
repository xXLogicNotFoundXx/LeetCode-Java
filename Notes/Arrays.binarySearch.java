    /*
Arrays.binarySearch Returns:
index of the search key, if it is contained in the array; otherwise, (-(insertion point) - 1). The insertion point is defined as the point at which the key would be inserted into the array: the index of the first element greater than the key, or a.length if all elements in the array are less than the specified key. Note that this guarantees that the return value will be >= 0 if and only if the key is found.
    */
    // Driver Code
    public static void main(String[] args)
    {
        // Given array arr[]
        int[] arr = new int[]{1, 3, 5, 7, 9};
        
        System.out.println(Arrays.binarySearch(arr,0));
        System.out.println(Arrays.binarySearch(arr,10));
        System.out.println(Arrays.binarySearch(arr,2));
        System.out.println(Arrays.binarySearch(arr,7));
    }
    /*
    -1
    -6
    -2
    3
    */
}
