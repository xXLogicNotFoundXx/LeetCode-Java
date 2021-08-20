LinkedIn13 Amazon4 Goldman Sachs4 Microsoft2 Uber2 Salesforce2☺️ DiDi2
/*
LinkedIn-39 + some compnies but not much 

https://leetcode.com/articles/shortest-word-distance-ii/

Design a class which receives a list of words in the constructor, and implements a method 
that takes two words word1 and word2 and return the shortest distance between these two words in the list.

WordList = ["practice", "makes", "perfect", "coding", "makes"]		-> Duplicate words are allowed and their indexes gonna be different. 

Distance beetween "practice" & "coding" is 3.   (min index difference) 
		   "coding" & "perfect" is 1.   (min index difference) 
		   "makes" & "coding"   is 1.   (min index difference)  


*/
class WordDistance {
        Map<String, ArrayList<Integer>> locationsMap = new HashMap<String, ArrayList<Integer>>();
        
	public WordDistance(String[] words) {
            for (int i = 0; i < words.length; i++) {
                ArrayList<Integer> loc = locationsMap.getOrDefault(words[i], new ArrayList<Integer>());   // getOrDefault
                loc.add(i);
                locationsMap.put(words[i], loc);
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
                if (indexList1.get(l1) < indexList2.get(l2)) {
                    l1++;
                } else {
                    l2++;
                }
            }

            return minDiff;
        }
    }
