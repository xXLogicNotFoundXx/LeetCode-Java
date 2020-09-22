Inteface : Deque

Impl Classes: ArrayDeque, ConcurrentLinkedDeque, LinkedBlockingDeque, LinkedList


ArrayDeque Performs better than linked list bcz of overhead of creating memory for linked list in long run. 
For best performance, you'll probably want to give it an initial capacity large enough 
to hold the number of elements it's likely to hold at a time to avoid many resizes.

Deque's adding elemnts beahviour is different 

Stack :
push -> inserts at the front of the Deque
pop -> removes from the front of the Deque

Queue : 
offer -> inserts at the end of the Deque
poll -> remove from front of Deque


So removeLast() function alwasy gives you the last element of the deque.
So doesnt matter how you use the deque removeLast() always removes from end of Deque.


***********************
Notes on removeLast()
***********************


// Deque using queue 
void test(){
        // Creating an empty ArrayDeque 
        Deque<String> de_que = new ArrayDeque<String>(); 
  
        // Use add() method to add elements into the Deque 
        de_que.offer("1");  // add is same 
        de_que.offer("2"); 
        de_que.offer("3"); 
        de_que.offer("4"); 
        de_que.offer("5"); 

        System.out.println("Initial ArrayDeque: " + de_que); 

        de_que.removeLast(); 
        de_que.removeLast(); 
        de_que.removeLast(); 
  
        System.out.println("ArrayDeque after removing "
                           + "elements: " + de_que); 
    } 
 
Initial ArrayDeque: [1, 2, 3, 4, 5]
ArrayDeque after removing elements: [1, 2]


// Deque Using Stack : 
    void test2(){
        // Creating an empty ArrayDeque 
        Deque<String> de_que = new ArrayDeque<String>(); 
  
        // Use add() method to add elements into the Deque 
        de_que.push("1"); 
        de_que.push("2"); 
        de_que.push("3"); 
        de_que.push("4"); 
        de_que.push("5"); 
  
        System.out.println("Initial ArrayDeque: " + de_que); 
  
        de_que.removeLast(); 
        de_que.removeLast(); 
        de_que.removeLast(); 
  
        // Displaying the ArrayDeque after removal 
        System.out.println("ArrayDeque after removing "
                           + "elements: " + de_que); 
    } 
Initial ArrayDeque: [5, 4, 3, 2, 1]
ArrayDeque after removing elements: [5, 4]
