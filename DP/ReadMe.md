	******** Lesson Learnt *********
	Usually I go and try to define a state with '-ve' number OR some INT_MAX constant. 
		- This is almost all the time ends up being messy and a lot state checking
			- results into not solving the problem at all or taking too much time. 
		- Ends up being lot of if and elses 
		
		^ Avoid it .... 
	come up with the algorithm .... and 
		- try zero value would that work with genral algorithm? ( wors if you adding or subtracting 
			- yes then try some corner cases
		- try one that may help in division/multiplication 
			- yes then try some corner cases
			
	******** Lesson learnt ********* 
	If you think extra "for" loops can make your life easy, do it. (extra pass OR extra simple loop to avoid if elses in the 'main loop')
	just do it ... later you can improve it 
	**Dont try to get that logic in the main logic the code becomes messy**
		- Then you end up being stuck around how to manupulate it with different indexes
		  and make sure that we put in values correctly. Resulting endup working 
	You could also just write some extra function 'init()' to do that work. 
