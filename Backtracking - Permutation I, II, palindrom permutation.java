https://leetcode.com/problems/permutations/submissions/
class Solution {
    public List<List<Integer>> permute(int[] nums) {
       List<List<Integer>> list = new ArrayList<>();
       backtrack(list, new ArrayList<>(), nums);
       return list;
    }

    private void backtrack(List<List<Integer>> list, List<Integer> tempList, int [] nums){
       if(tempList.size() == nums.length){
          list.add(new ArrayList<>(tempList));
       } else{
          for(int i = 0; i < nums.length; i++){ 
             if(tempList.contains(nums[i])) continue; // element already exists, skip
             tempList.add(nums[i]);
             backtrack(list, tempList, nums);
             tempList.remove(tempList.size() - 1);
          }
       }
    } 
}

//Backtracking - Permutation_II.java
//Duplicates. 
    
class Solution {
    public List<List<Integer>> permuteUnique(int[] nums) {
        List<List<Integer>>  ans = new ArrayList<List<Integer>>();
        if(nums.length == 0)
            return ans;
        
        Map<Integer,Integer> map = new<Integer,Integer>HashMap();
        for(int n : nums)
            map.put(n,map.getOrDefault(n,0)+1);
        
        int[] count = new int[map.size()], numbers = new int[map.size()];
        int i=0;
        for(Map.Entry<Integer,Integer> e : map.entrySet()){
            numbers[i] = e.getKey().intValue();
            count[i++] = e.getValue().intValue();  
        }
        
        List<Integer> subAns = new ArrayList<Integer>();
        permuteAvoidDuplicates(ans, subAns, numbers, count, nums.length); 
        return ans;
    }
    
    private void permuteAvoidDuplicates(List<List<Integer>> ans,  List<Integer> subAns, int[] numbers, int[] count, int ansSize){
        if(subAns.size()==ansSize)
            ans.add(new ArrayList<Integer>(subAns));
        
        for(int i=0;i<count.length;i++){
            if(count[i]==0)
                continue;
            
            subAns.add(numbers[i]);
            count[i]--;
            permuteAvoidDuplicates(ans, subAns, numbers, count, ansSize);
            count[i]++;
            subAns.remove(subAns.size()-1); 
            
        }
    }
}

/*
https://leetcode.com/problems/palindrome-permutation-ii/
Given a string s, return all the palindromic permutations (without duplicates) of it. 
Return an empty list if no palindromic permutation could be form.
We only need to generate the first part of palindrome string, 
and the remaining part will be a middle character with the reverse of first part.
*/
class Solution {
    public List<String> generatePalindromes(String s) {
        Map<Character,Integer>  map = new HashMap<>();
        Set<Character> set = new HashSet<>();
        List<String> list = new LinkedList<>();
        
        if(s==null || s.isEmpty()) 
            return list;
        for(char c : s.toCharArray()){
            map.put(c,map.getOrDefault(c,0)+1);
            if(map.get(c)%2==1)
                set.add(c);
            else
                set.remove(c);
        }
        
        if( s.length()%2==1 && set.size()!=1 ||         // IMP 
            s.length()%2==0 && set.size()!=0)
            return list;
        
        int resLength = s.length()/2;
        String mid = "";
        if(set.size()==1){
            char c = set.iterator().next();
            mid = ""+c;
            map.put(c,map.get(c)-1);
        }
        
        generatePalindromesHelper(map,mid,new StringBuffer(),resLength,list);
        return list;
    }
    
    void generatePalindromesHelper(Map<Character,Integer>  map, String mid, StringBuffer sb, int resLength, List<String> list){
        if(sb.length()==resLength){
            String ans = sb.toString()+mid+sb.reverse().toString();
            sb.reverse(); // VERY IMPORTANT 
            list.add(ans);
            
            return;
        }
        
        for(char ch : map.keySet()){
            if(map.get(ch)>1){
                map.put(ch, map.get(ch)-2); // IMP -2 
                sb.append(ch);
                
                generatePalindromesHelper(map,mid,sb,resLength,list);
                
                sb.deleteCharAt(sb.length()-1);
                map.put(ch, map.get(ch)+2); // IMP +2 
            }
        }
    }
}
