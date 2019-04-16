https://leetcode.com/articles/shortest-word-distance-ii/
Design a class which receives a list of words in the constructor, and implements a method 
that takes two words word1 and word2 and return the shortest distance between these two words in the list.
Your method shortest(String word1, String word2 will be called repeatedly many times with different parameters. 

class WordDistance {
        Map<String, ArrayList<Integer>> locations = new HashMap<String, ArrayList<Integer>>();
        
	public WordDistance(String[] words) {
            for (int i = 0; i < words.length; i++) {
                ArrayList<Integer> loc = locations.getOrDefault(words[i], new ArrayList<Integer>());   // getOrDefault
                loc.add(i);
                locations.put(words[i], loc);
            }
        }
	
        public int shortest(String word1, String word2) {
            ArrayList<Integer> loc1, loc2;
            // Location lists for both the words the indices will be in SORTED order by default
            loc1 = locations.get(word1);
            loc2 = locations.get(word2);

            int l1 = 0, l2 = 0, minDiff = Integer.MAX_VALUE;
            while (l1 < loc1.size() && l2 < loc2.size()) {
                minDiff = Math.min(minDiff, Math.abs(loc1.get(l1) - loc2.get(l2)));
                if (loc1.get(l1) < loc2.get(l2)) {
                    l1++;
                } else {
                    l2++;
                }
            }

            return minDiff;
        }
    }
