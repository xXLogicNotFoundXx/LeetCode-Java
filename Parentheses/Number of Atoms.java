/*
https://leetcode.com/problems/number-of-atoms/

Input: 
formula = "H2O"
Output: "H2O"
Explanation: 
The count of elements are {'H': 2, 'O': 1}.

Input: 
formula = "Mg(OH)2"
Output: "H2MgO2"
Explanation: 
The count of elements are {'H': 2, 'Mg': 1, 'O': 2}.

Input: 
formula = "K4(ON(SO3)2)2"
Output: "K4N2O14S4"
Explanation: 
The count of elements are {'K': 4, 'N': 2, 'O': 14, 'S': 4}.
*/

/*
We used TreeMap here so that we could have entries sorted by String[Formula]
A Red-Black tree based NavigableMap implementation. The map is sorted according to the natural ordering of its keys, 
or by a Comparator provided at map creation time, depending on which constructor is used.
This implementation provides guaranteed log(n) time cost for the containsKey, get, put and remove operations. 

if(capital_letter) -> 
                1. put prevFormula and its count in the map
                2. prevFormula=capital_letter & reset the count
if(small_letter) -> 
                1. append to pre-computed formula
if('(') -> 
        1. put prevFormula and its count in the map & reset prevFormula & count 
        2. make recursive call 
        3. add returned map result to current map
if(')') -> 
        1. put prevFormula and its count & reset those 
        2. Compute count if there is any 
        3. Multiply the count in the map 
        4. return the map 
*/
class Solution {
    int i=0;
    
    public String countOfAtoms(String formula) {
        if(formula == null || formula.isEmpty())
            return "";
        
        TreeMap<String,Integer> map = countOfAtomsHelper(formula);

        StringBuilder sb = new StringBuilder();
        for(Map.Entry<String,Integer> e : map.entrySet())
            sb.append(e.getKey() + (e.getValue()==1 ? "" : e.getValue()) ) ;
        
        return sb.toString();
    }
    
    public TreeMap<String,Integer> countOfAtomsHelper(String formula) {
        
        TreeMap<String,Integer> map = new TreeMap<>();
        String prev = "";
        int num = 0; 
        
        while(i<formula.length()){
            
            char ch = formula.charAt(i);
            
            if(isBigChar(ch)){
                putPrev(map, prev, num);
                prev = ""+ch;
                num = 0;
            } 
            else if(Character.isDigit(ch)){
               num = num*10 + ch -'0'; 
            } 
            else if(isSmallChar(ch)){
                prev = prev + ch;      
            }
            else if(ch=='('){
                putPrev(map, prev, num);        // ==> IMP
                prev = ""; num = 0;             // ==> IMP
                
                i++;
                
                TreeMap<String,Integer> subMap = countOfAtomsHelper(formula);
                // add this subMap to main Map 
                for(Map.Entry<String,Integer> e : subMap.entrySet()){
                    map.put(e.getKey(), map.getOrDefault(e.getKey(), 0) + e.getValue());
                }
                
                i--; // when we returned from ) i is already pointing to next element    ===> VERY IMP
            } 
            else { // ch==')'
                putPrev(map, prev, num);       // ==> IMP
                prev = ""; num = 0;            // ==> IMP
                
                i++;
                
                // there could be a number then compute that number 
                int count=0;
                while(i<formula.length() && Character.isDigit(formula.charAt(i))){
                    count = count*10 + formula.charAt(i) - '0';
                    i++;
                }
                
                count = count==0 ? 1 : count; 
                
                // multiply all entries in the map by count
                for(Map.Entry<String,Integer> e : map.entrySet()){
                     map.put(e.getKey(), e.getValue()*count);
                }
                
                return map;
            }
            
            i++;
        }
        
        putPrev(map, prev, num);
        
        return map;
    }
    

    boolean isBigChar(char ch){
        if(ch>='A' && ch<='Z')
            return true;
        return false; 
    }
    
    boolean isSmallChar(char ch){
        if(ch>='a' && ch<='z')
            return true;
        return false; 
    }
    
    void putPrev(Map<String,Integer> map, String prev, int num){
        if(prev.isEmpty())
            return;
        num = num==0 ? 1 : num;
        map.put(prev,map.getOrDefault(prev,0) + num);
    }
}
