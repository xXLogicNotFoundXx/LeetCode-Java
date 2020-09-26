Usually when we use : left <= right
End Condition becomes : left > right

  Thats when we get out of the loop and the we didnt find the answer. (Basic Binary Search like search an element)
  Initial Condition: left = 0, right = length-1
  Termination: left > right
  Searching Left: right = mid-1
  Searching Right: left = mid+1
  
Usually when we use : left < right
End Condition: left == right

  We use it when we want to come to one index and do post processing or return that index (Insert position, Cap to ship within D days, Split array)
  Initial Condition: left = 0, right = length
  Termination: left == right
  Searching Left: right = mid
  Searching Right: left = mid+1
