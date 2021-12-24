/*
170. Two Sum III - Data structure design

Input
["TwoSum", "add", "add", "add", "find", "find"]
[[], [1], [3], [5], [4], [7]]
Output
[null, null, null, null, true, false]

Explanation
TwoSum twoSum = new TwoSum();
twoSum.add(1);   // [] --> [1]
twoSum.add(3);   // [1] --> [1,3]
twoSum.add(5);   // [1,3] --> [1,3,5]
twoSum.find(4);  // 1 + 3 = 4, return true
twoSum.find(7);  // No two integers sum up to 7, return false

*/
class TwoSum {

  private Map<Integer, Integer> map = new HashMap<Integer, Integer>();

  // Add the number to an internal data structure.
	public void add(int number) {
        map.put(number, map.getOrDefault(number,0) + 1);
	}

  // Find if there exists any pair of numbers which sum is equal to the value.
	public boolean find(int value) {
	    
        for(Map.Entry<Integer, Integer> e : map.entrySet()){
            int num1 = e.getKey();
            int num2 = value - num1;
            
            if(num1==num2 && map.get(num1)>=2)
                return true;
            
            if(num1!=num2 && map.containsKey(num2))
                return true;
        }
	    return false;
	}
}
