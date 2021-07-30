/*
You can put all the matrix in an 1D Array and user Arrays.sort()
OR
https://stackoverflow.com/questions/1066589/iterate-through-a-hashmap/1066603#1066603
*/
public static void sortMatrix(final int[][] matrix) {
    // Assuming the matrix is rectangular
    final int n = matrix.length;
    final int m = matrix[0].length;

    List<Integer> list = new AbstractList<Integer>() {
        @Override
        public Integer set(int index, Integer element) {
            return matrix[index/m][index%m] = element;   // se we only used m here 
        }

        @Override
        public Integer get(int index) {
            return matrix[index/m][index%m];
        }

        @Override
        public int size() {
            return n*m;         // nice! 
        }
    };
    Collections.sort(list);
}
