/*
332. Reconstruct Itinerary
Medium
https://leetcode.com/problems/reconstruct-itinerary/
Given a list of airline tickets represented by pairs of departure and arrival airports [from, to], reconstruct the itinerary in order. All of the tickets belong to a man who departs from JFK. Thus, the itinerary must begin with JFK.

Note:
If there are multiple valid itineraries, you should return the itinerary that has the smallest lexical order when read as a single string. 
        For example, the itinerary ["JFK", "LGA"] has a smaller lexical order than ["JFK", "LGB"].
        All airports are represented by three capital letters (IATA code).
        You may assume all tickets form at least one valid itinerary.

Input: [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
Output: ["JFK","ATL","JFK","SFO","ATL","SFO"]
Explanation: Another possible reconstruction is ["JFK","SFO","ATL","JFK","ATL","SFO"].
             But it is larger in lexical order.
             
here we learn how to remove the node from the list and add into the same list while traversing the list.
The good thing in the list we can remove the node at ith and add the node at ith postion before loop ends so list remain intact 
during traversal. 

Also, map.map.putIfAbsent() is good to know method. 
*/
class Solution {
    /*
            [["JFK","SFO"],["JFK","ATL"],["SFO","ATL"],["ATL","JFK"],["ATL","SFO"]]
     ans->  ["JFK","ATL","JFK","SFO","ATL","SFO"]

            [["JFK","KUL"],["JFK","NRT"],["NRT","JFK"]]
     ans -> ["JFK","NRT","JFK","KUL"]

        There is an answer using priorty queue but its hard to come up with in the interview. 
        Called "Hierholzer's Algorithm"
        
        Time Complexity : V be the airports and E be the edge.
        in each DFS call we could max end up processing all Edges ( each edge is removed and added n)
        max depth we are going is V and fan out is V 
        V^2*E^2
        
    */

    public List<String> findItinerary1(List<List<String>> tickets) {
        List<String> res = new ArrayList<String>();
        if(tickets == null || tickets.size() == 0)
          return res;
        Map<String, List<String>> map = new HashMap<>();
        int numTickets = tickets.size();
        for(List<String> ticket: tickets){
          map.putIfAbsent(ticket.get(0), new ArrayList<String>()); // Important
          map.get(ticket.get(0)).add(ticket.get(1));
        }

        for(List<String> dest: map.values())
          Collections.sort(dest);

        backtrack(map, "JFK", numTickets, res);
        return res;
    }
  
    boolean backtrack(Map<String, List<String>> map, String cur, int numTickets, List<String> res){
        res.add(cur);
        if(res.size() == numTickets+1){
            return true;
        }

        List<String> destList = map.getOrDefault(cur,new ArrayList<>());
        
        // This is not a concurrent modification as we remove and add at the same location in a list 
        for(int i=0; i<destList.size(); i++){
            String dest = destList.get(i);
            
            // Smart trick remove this and then we add at the same location. 
            destList.remove(i); // Remove this destination when we come back on JFK we should not process this one.
              
            if(backtrack(map, dest, numTickets, res))
                return true;
            destList.add(i,dest); // add destination at ith location 
        }
        
        res.remove(res.size()-1);
        return false;
    }
    
    /*  This is using my own data structure to have a flag saying if we used the detination
        Same logic as above but no we wont be modifying a list. 
    */ 
    class Pair {
        String toCity;
        boolean used;
        Pair(String to){
            toCity = to;
            used = false;
        }
    }
    
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> ans = new ArrayList<>();
        if(tickets==null || tickets.size()==0)
            return ans; 
        
        int totalDest = tickets.size()+1;
        Map<String,List<Pair>> map = new HashMap<>();
        for(List<String> tic : tickets){
            map.putIfAbsent(tic.get(0), new ArrayList<Pair>());
            map.get(tic.get(0)).add(new Pair(tic.get(1)));
        }
        
        for(List<Pair> dest: map.values())
          Collections.sort(dest, (a,b) -> a.toCity.compareTo(b.toCity) );
        
        
        findItinerary("JFK", map, ans, totalDest);
        return ans;
        
    }
    
    boolean findItinerary(String from, Map<String,List<Pair>> map, List<String> ans, int totalDest){
        
        
        ans.add(from);
        if(ans.size() == totalDest){
            return true; 
        }
        
        List<Pair> to = map.getOrDefault(from, new ArrayList<Pair>());
        
        for(Pair des : to){
            if(des.used == false){
                des.used = true;
                
                if(findItinerary(des.toCity, map, ans, totalDest))
                    return true;
                
                des.used = false;
            }
        }
        
        ans.remove(ans.size()-1);
        return false;
    }
}
