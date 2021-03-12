/*
Given two integer arrays a and b, and an integer value d, your task is to find the comparator value between these arrays.

The comparator value is defined as the number of elements x ∈ a such that there are no elements y ∈ b where |x - y| ≤ d.
In other words, it's the number of elements in a that are more than d away from any element of b.

*/
https://leetcode.com/problems/find-the-distance-value-between-two-arrays/submissions/



// Product - sum 
public int subtractProductAndSum(int n) {
	int product=1, sum=0;

	while(n>0){
	    int i = n%10;
	    n = n/10;
	    product *= i;
	    sum += i;
	}

	return product-sum;
}


/*
Easy:
Given an array A return an output array B of size [A.length - 2] where B[i] = 1 if sides A[i],A[i+1] and A[i+2] form a triangle. Otherwise, set B[i] = 0.
ex. A = [1, 2, 2, 5, 5, 4] should return B = [1,0,0,1]
*/
// The sum of the length of two sides of a triangle is always greater than the length of the third side.
for(int i=0;i<A.length-2;i++){
  int a=A[i],b=A[i+1]c=A[i+2];
  
  if(a+b>c && b+c>a && c+a>b)
    b[i]=1;
  else
    b[i]=0;
}

/*
Given two strings a and b, merge the strings so that the letters are added in alternating order starting with string a. If one string is longer than the other, then append the letters to the end of the merged string.
ex. "abcd", "efghi" -> "aebfcgdhi"
ex. "", "abcd" -> "abcd"
ex. "abcdefg", "zxy" -> "azbxycdefg"
*/

int i=0;
int j=0;
while(i<=str.length()-1 || j<=str2.length()-1);
  char a = i<=str.length()-1 ? str.chat(i) : "";
  char b = j<=str2.length()-1 ? str2.chat(j) : "";
  append(a);
  append(b);
  i++;j++;


/*
Given a string s, return the longest and lexicographically smallest palindromic string that can be formed from the characters.
ex. "abbaa" -> "abba"
ex. "adeadeadevue" -> "adeeaeeda"
*/
public class Main {
    public static String lexLongestPalimdrome(String str){
        if(str==null || str.isEmpty())
            return "";
        
        int[] count = new int[26];
        for(char ch : str.toCharArray())
            count[ch-'a']++;
        
        StringBuilder sb = new StringBuilder();
        for(int i=0;i<count.length;i++){
            if(count[i]==0 || count[i]==1)
                continue;
            
            int mid = sb.length()/2;
            int c = count[i];
            char ch = (char) ((int)'a'+i);
            String s = Character.toString(ch);
            if(c%2==0){
                sb.insert(mid,s.repeat(c));
            } else {
                sb.insert(mid,s.repeat(c-1));
            }
        }
        
        for(int i=0;i<count.length;i++){
            if(count[i]%2==1){
                int mid = sb.length()/2; 
                char ch = (char) ((int)'a'+i);
                sb.insert(mid,ch);
                break;
            }
        }
            
        return sb.toString();
    }
    public static void main(String[] args) {
        System.out.println(lexLongestPalimdrome(""));
        System.out.println(lexLongestPalimdrome("adeadeadevue"));
        System.out.println(lexLongestPalimdrome("adskassda"));
        System.out.println(lexLongestPalimdrome("aahslqjjd"));
    }
}

/*
Given a string S, Count number of ways of splitting S into 3 non-empty a,b,c such 
that a+b, b+c, c+a are all different.

ex. xzxzx OP: 5
   x, z, xzx
	x, zx, zx
	xz, x, zx
	xz, xz, x
	xzx, z, x

// This is different NASTY problem but here is the solution 
this probem says there are only 2 characteres like above x and z and each substring must have specific number of x and z. 
https://leetcode.com/problems/number-of-ways-to-split-a-string/
https://leetcode.com/problems/number-of-ways-to-split-a-string/discuss/830455/JavaPython-3-Multiplication-of-the-ways-of-1st-and-2nd-cuts-w-explanation-and-analysis./685436
*/
// this is easy one if they are not similar like nasty problem one 
public class Main {
    public static int numberOfThreeWayCuts(String S){
        if(S==null || S.length()<3)
            return 0;
        
        int count=0;
        for(int i = 1; i<S.length()-1;i++){
            for(int j = i+1; j<S.length();j++){
                String a = S.substring(0,i);
                String b = S.substring(i,j);
                String c = S.substring(j);
                
                System.out.println(a +" "+b+" "+c);
                if(!((a+b).equals(b+c) || (b+c).equals(c+a) || (a+b).equals(c+a)))
                count++;
            }
        }
        return count;
    }
    
    public static void main(String[] args) {
        System.out.println(numberOfThreeWayCuts("xzxzx"));
        System.out.println(numberOfThreeWayCuts("XXX"));
    }
}

/*
We define a subarray of size m in an n-element array to be the contiguous block of elements in the inclusive range from index i to index j, where j − i + 1 = m and 0 ≤ i ≤ j < n. For example, given array [8, 2, 4], the subarrays of size m = 2 would be [8, 2] and [2, 4] (but not [8, 4] since these elements aren't contiguous).

Given an array of integers arr, and an integer m, your task is the following:

Find the minimum value in each of the contiguous subarrays of size m;
Return the maximum value among these minimums.

This is like sliding window minimum problem ... and then finxidn max in it. 
*/
public int[] minSlidingWindow(int[] nums, int k) {
        
        if(nums==null||nums.length==0 ) 
            return new int[0];
        
        Deque<Integer> queue = new ArrayDeque<>();
    
        int[] ans = new int[nums.length-k+1];
        int ians=0;
        
        for(int i=0;i<nums.length;i++){
            // 1. if first index in  Q(Deque) < i-k+1
            if(!queue.isEmpty() && queue.peek() < i-k+1){
                queue.poll();
            }    
            
            // 2. loop: nums[i] < last element of the Q(Deque)?
            while(!queue.isEmpty() && nums[i] < nums[queue.peekLast()]){
                queue.pollLast();
            }
            
            // 3. offer(i)
            queue.offer(i);
            
            // 4. if window is complete then ouput nums[peek()]
            if(i>= k-1){ // OR   i-k+1 >= 0
                ans[ians++] = nums[queue.peek()];
            }
        }
        
        return ans;
    }


/*
Spiral matrix  you should sort elements in border. 

Traverse Spiral matrix and put it in HEAP ... make a list of heaps. 
second pass add those numbers in spiral.
*/
public List<Integer> spiralOrder(int[][] a) {
       
        List<Integer> list = new ArrayList<Integer>();
        if(a.length == 0) return list;

        int left=0, right=a[0].length-1;
        int top=0,  bottom=a.length-1;
        
        while(left<=right && top<=bottom){
            for(int i=left;i<=right;i++) 
                list.add(a[top][i]); 
            top++;
            for(int i=top;i<=bottom;i++) 
                list.add(a[i][right]);
            right--;
            
            if(top<=bottom){    // IMP 
                for(int i=right;i>=left;i--) 
                    list.add(a[bottom][i]);
                bottom--;
            }
            if(left<=right){   // IMP 
                for(int i=bottom;i>=top;i--) 
                    list.add(a[i][left]);
                left++;
            }
        }
        return list;
    }


/*
Q Given a string str and an integer k, your task is to split str into a minimal possible number of 
substrings so that there are no more than k different symbols in each of them. Return the minimal possible number of such substrings.

eg: s = "aabeefegeeccrr" k = 3 Output = 3
*/
int solve(String string) {
    int ans = 1;
    Set<Character> set = new HashSet<>();
    for(int i = 0; i < string.length(); i++) {
        
	if(set.contains(string.charAt(i))
	   continue; 
	   
        if(!set.contains(string.charAt(i)) && set.size() == K) {
           set = new HashSet<>();
           ans += 1;
        }
	   
        set.add(string.charAt(i));
    }
    return ans;
}
/*
 Prefix String - given two string arrays A & B, find if all strings in B are prefixes of a concatenation of strings in A. 
 For example if A = {"one", "two", "three"} B = {"onetwo", "one"}, return True
 
 .. this is a word break problem called m times. for B strings. 
 
 https://leetcode.com/problems/word-break/solution/
*/
	   
/*
Roatet matrix K time but dont rotate diagonals 
https://github.com/xXLogicNotFoundXx/LeetCode-Java/blob/master/2D/101.java
*/
public int[][] rotate_without_extra_space(int[][] matrix) {
	int n = matrix.length;
	// transpose matrix
	for (int i = 0; i < n; i++) {
		for (int j = i; j < n; j++) {
			if (i == j || i + j == matrix.length - 1) {
				//Diagonal
			} else {
				int tmp = matrix[j][i];
				matrix[j][i] = matrix[i][j];
				matrix[i][j] = tmp;
			}
		}
	}
	// reverse each row
	for (int i = 0; i < n; i++) {
		for (int j = 0; j < n / 2; j++) {
			if (i == j || i + j == matrix.length - 1) {
				//Diagonal
			} else {
				int tmp = matrix[i][j];
				matrix[i][j] = matrix[i][n - j - 1];
				matrix[i][n - j - 1] = tmp;
			}
		}
	}
	return matrix;
}

	   
/*
Implement Increment function for all values in hashmap .. this one is easy 
Implement Increment function for all keys in hashmap ... 
1. extra o(n) space we can just craet another map 
2. for no extra map we can sort keys in reverse order and start putting key,value pairs  
*/
1.
HashMap<Integer,Integer> newMap = new HashMap<>();
for(int key: map.keySet()){
   newMap.put(key+1, map.get(key));
}
map = newMap;

2.
List list = new ArrayList(map.keySet());
Collections.sort(list, Collections.reverseOrder);
for(Integer i: list) {
	map.put(i+1, map.get(i));
	map.remove(i);
}

	   /*
Group array by mean values 
*/
private static int[][] meanGroups(int[][] nums) {
        
        Map<Integer, List<Integer>> map = new HashMap<>();
        
        for(int i = 0; i < nums.length; i++) {
            
            int mean = getMean(nums[i]);
            if(!map.containsKey(mean)) map.put(mean, new ArrayList<>());
            map.get(mean).add(i);
            
        }
        
        int[][] ret = new int[map.size()][];
        
        int j = 0;
        
        for(int key: map.keySet()) 
            ret[i++] = map.get(key).stream().mapToInt(j -> j).toArray();
           
        
        Arrays.sort(ret, (a,b) -> a[0] - b[0]);
        return ret;
    }
    
    private static int getMean(int[] A) {
        int sum = 0;
        for(int a: A) {
            sum += a;
        }
        
        return sum/A.length;
    }

