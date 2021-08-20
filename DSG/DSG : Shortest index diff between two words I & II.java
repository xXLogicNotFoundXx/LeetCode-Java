/*
EASY
⭐️ LinkedIn13 Amazon4 GoldmanSachs4 Microsoft2 Uber2 Salesforce2 DiDi2
https://leetcode.com/problems/shortest-word-distance/

Given an array of strings wordsDict and two different strings that already exist in the array word1 and word2, 
return the shortest distance between these two words in the list. (Index difference) .

Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"]
word1 = "coding", word2 = "practice"
Output: 3


Input: wordsDict = ["practice", "makes", "perfect", "coding", "makes"]
word1 = "makes", word2 = "coding"
Output: 1

Only one call is made. So following solution is ok. 
This is easy question

Time : O(NxM)
Space : O(1)
*/
public int shortestDistance(String[] words, String word1, String word2) {
    int p1 = -1, p2 = -1, min = Integer.MAX_VALUE;
    
    for (int i = 0; i < words.length; i++) {
        if (words[i].equals(word1)) 
            p1 = i;

        if (words[i].equals(word2)) 
            p2 = i;
            
        if (p1 != -1 && p2 != -1)
            min = Math.min(min, Math.abs(p1 - p2));
    }
    
    return min;
}


/* 
Follow Up - method is clled 5000 times over same list of words. could you improve this? 

MEDIUM 
⭐️ LinkedIn-39 + some compnies but not much 
https://leetcode.com/articles/shortest-word-distance-ii/

Design a class which receives a list of words in the constructor, and implements a method 
that takes two words word1 and word2 and return the shortest distance between these two words in the list.

WordList = ["practice", "makes", "perfect", "coding", "makes"]		-> Duplicate words are allowed and their indexes gonna be different. 

Distance beetween "practice" & "coding" is 3.   (min index difference) 
		   "coding" & "perfect" is 1.   (min index difference) 
		   "makes" & "coding"   is 1.   (min index difference)  


int shortest(String word1, String word2) would be called at most 5000 times. 
 
 
 Time : O(N) constructor + O(max(k,l)) -> k & l represents the number of occurrences of the two words.
 		why max and not min? 
			bcz we may end up traversing the longest list. 
			
 Space : O(N) indices and words. 
*/
class WordDistance {
        Map<String, ArrayList<Integer>> locationsMap = new HashMap<String, ArrayList<Integer>>();
        
	public WordDistance(String[] words) {
		for (int i = 0; i < words.length; i++) {
			locationsMap.putIfAbsent(words[i], new ArrayList<Integer>());

			ArrayList<Integer> loc = locationsMap.get(words[i]);
			loc.add(i);
		}
	}
	
	public int shortest(String word1, String word2) {
		ArrayList<Integer> indexList1, indexList2;
		// Location lists for both the words the indices will be in SORTED order by default
		indexList1 = locationsMap.get(word1);
		indexList2 = locationsMap.get(word2);

		int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
		
		while (l1 < indexList1.size() && l2 < indexList2.size()) {
			
			minDiff = Math.min(minDiff, Math.abs(indexList1.get(l1) - indexList2.get(l2)));

			// who moves forward? -> lower index move forward inorder to get minimum distance 
			if (indexList1.get(l1) < indexList2.get(l2))
				l1++;
			else
				l2++;
		}

		return minDiff;
	}
}
