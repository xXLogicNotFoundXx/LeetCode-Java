
 
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
 
    private static void spiralTraversal(int[][] a) {
        if( a.length == 0 ) return;
 
        int dir = 0;
        int L = 0; int T =0;
        int R= a[0].length-1; int B = a.length-1;
        System.out.print("Spiral = ");
 
        while(T<=B && L<=R){
 
            if(dir == 0){
                for(int k=L;k<=R;k++)
                    System.out.print(a[T][k]+" ");
                T++;
            }
            else if(dir == 1){
                for(int k=T;k<=B;k++)
                    System.out.print(a[k][R]+" ");
                R--;
            }
            else if(dir == 2){
                for(int k=R;k>=L;k--)
                    System.out.print(a[B][k]+" ");
                B--;
            }
            else if(dir == 3){
                for(int k=B;k>=T;k--)
                    System.out.print(a[k][L]+" ");
                L++;
            }
            dir = (dir+1)%4;
        }
    }
 
