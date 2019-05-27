
 
/* Name of the class has to be "Main" only if the class is public. */

    private static void ZigZagDiagonalTraversal(int[][] a){
        if(a.length == 0) return;
 
        int n=a.length;
        int m=a[0].length;
        int layer = 0;
        System.out.print("ZigZagDiagonal = ");
        while (true) {
            if (layer % 2 == 0){ // layer 0,2,4 is moving up
                int i = layer < n ? layer%n : n-1;
                int j = layer < n ? 0 :layer%n+1;
                for(; j>=0 && j<m && i>=0 && i<n;j++,i--){
                    System.out.print(a[i][j] + " ");
                    if(i==n-1 && j==m-1)
                        return;
                }
            } else {// layer 1,3,4is moving down
                int j = layer < m ? layer%m : m-1;
                int i = layer < m ? 0 :layer%m+1;
                for(; j>=0 && j<m && i>=0 && i<n;i++,j--){
                    System.out.print(a[i][j] + " ");
                    if(i==n-1 && j==m-1)
                        return;
                }
            }
            layer++;
        }
    }
 
 
