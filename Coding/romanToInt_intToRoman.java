/*
Easy - so many companies
https://leetcode.com/problems/roman-to-integer/

Roman numerals are represented by seven different symbols: I, V, X, L, C, D and M.
Symbol       Value
I             1
V             5
X             10
L             50
C             100
D             500
M             1000
Example 1:

Input: "III"
Output: 3
Input: "IV"
Output: 4
Input: "IX"
Output: 9
Input: "LVIII"
Output: 58
Explanation: L = 50, V= 5, III = 3.
Input: "MCMXCIV"
Output: 1994
Explanation: M = 1000, CM = 900, XC = 90 and IV = 4.
*/
class Solution {
    public void init(Map<Character,Integer> map){
      map.put('I',1);
      map.put('V',5);
      map.put('X',10);
      map.put('L',50);
      map.put('C',100);
      map.put('D',500);
      map.put('M',1000);
    }

    public int romanToInt(String s) {
        if(s==null || s.isEmpty())
            return 0;

        int []nums = new int[s.length()];
        int total = 0;

        Map<Character,Integer> map = new HashMap<>();
        init(map);

        for(int i=0; i<s.length(); i++)
            nums[i] = map.getOrDefault(s.charAt(i), 0);

        for(int i=0; i<s.length(); i++){
            total += nums[i];

            if(i!=0 && nums[i]>nums[i-1])   // consider input IV we added 1 and then 5 which is 6 ... so we have to do minus (2*1)
                total = total - (2 * nums[i-1]);
        }
        return total;
    }
}


// max number as input will be 3999
class Solution {
    public String intToRoman(int num) {
        int []val = new int[]{1000,900,500,400,100,90,50,40,10,9,5,4,1};
        String []roman = new String[]{"M","CM","D","CD","C","XC","L","XL","X","IX","V","IV","I"};
        StringBuffer str = new StringBuffer();
        for(int i=0;i<val.length;i++){
            while(num>=val[i]){
                num = num - val[i];
                str.append(roman[i]);
            }
        }
        return str.toString();
    }
}
