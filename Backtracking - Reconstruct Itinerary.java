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
    public List<String> findItinerary(List<List<String>> tickets) {
            
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
        for(int i=0; i<destList.size(); i++){
            String dest = destList.get(i);
            destList.remove(i);
            if(backtrack(map, dest, numTickets, res))
                return true;
            destList.add(i,dest);
        }
        res.remove(res.size()-1);
        return false;
    }
}
