Usually when we use : left <= right
End Condition becomes : left > right
Initial Condition: left = 0, right = length-1
Termination: left > right
Searching Left: right = mid-1
Searching Right: left = mid+1

  We use it when we have to find an element or se if there is something exist in a search space; 
  we get out of the loop that means we didnt find the answer. 
  (Basic Binary Search like search an element)
  
  
Usually when we use : left < right
End Condition: left == right
Initial Condition: left = 0, right = length(some max)
Termination: left == right
Searching Left: right = mid   (mid would never be calculated as same as right and there will be never be an infinite loop)
Searching Right: left = mid+1
  
  We use it when we want to come to one index and do post processing or return that index. 
  Or there is something mimumum of something asked. 
      (Insert position, Cap to ship within D days, Split array, Find minimum(or start index) in rorated sorted array)
  We keep right=mid , even if we find the satisfying condition and keep moving right to the left side to find absolute minimum. 
  Which satisfies the condition. 
  
  
  As a rule of thumb, use : 
  
  m = l + (r-l)/2 
  with 
  l = m + 1 
  r = m 
  
  and use 
  
  m = r - (r-l)/2 
  with 
  l = m  
  r = m - 1. 
  
  This can prevent m from getting stuck at r (or l) after the updating step.
  
