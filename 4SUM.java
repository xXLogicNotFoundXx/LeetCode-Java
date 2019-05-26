class Solution {
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        List<List<Integer>> result = new ArrayList<List<Integer>>();
        Arrays.sort(numbers);
        for (int i = 0; i < numbers.length - 3; i++) {
            if(i!=0 && numbers[i]==numbers[i-1]) continue;  // avoid duplicates 
            
            for (int j = i + 1; j < numbers.length - 2; j++) {
                if(j!=i+1 && numbers[j]==numbers[j-1]) continue; // avoid duplicates 
                
                int left = j + 1;
                int right = numbers.length - 1;
                
                while (left < right) {
                    int sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
                    if (sum == target) {
                        List<Integer> tuple = new ArrayList<Integer>();
                        tuple.add(numbers[i]);
                        tuple.add(numbers[j]);
                        tuple.add(numbers[left]);
                        tuple.add(numbers[right]);
                        result.add(tuple);
                        
                        while(left<right && numbers[left]==numbers[left+1])left++; //skipping over duplicate on left right will move automatically 
                        left++; // Very important or else goes in infinite loop 
                    } else if (sum < target) {
                        left++;
                    } else {
                        right--;
                    }
                }
            }
        }
        return result;
    }
}

class Solution2MySolution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        Set<List<Integer>>  ans = new HashSet<List<Integer>>(); // set is initialized by HashSet
        if(nums.length == 0)
            return new ArrayList<List<Integer>>(ans);
        List<Integer> set = new ArrayList<Integer>();
        
        fourSum(nums,target,0,ans,set,0);
        return new ArrayList<List<Integer>>(ans); // List can take set in the constructor
    }
    public void fourSum(int[] nums, int target, int index, Set<List<Integer>> ans, List<Integer> set,int sum){
        if(set.size() == 4 && sum==target){
            List<Integer> setAns = new ArrayList<Integer>(set);
            // sort so that same combination becomes sorted and 
            // set would not add that same list
            Collections.sort(setAns); 
            ans.add(setAns);
        }

        if(set.size()==4)
            return;
        
        for(int i=index;i<nums.length;i++){
            set.add(nums[i]);
            fourSum(nums,target,i+1,ans,set,sum+nums[i]);
            set.remove(set.size()-1);
        }
    }
}