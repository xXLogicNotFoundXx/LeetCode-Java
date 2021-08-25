/*
Medium 
Not many companies 2-3 times 
BUt it is a good problem to solve. 

https://leetcode.com/problems/design-snake-game/
Design a Snake game that is played on a device with screen size = width x height. 
Play the game online if you are not familiar with the game.
Given width = 3, height = 2, and food = [[1,2],[0,1]].

Snake snake = new Snake(width, height, food);
Initially the snake appears at position (0,0) and the food at (1,2).
|S| | |
| | |F|
snake.move("R"); -> Returns 0
| |S| |
| | |F|
snake.move("D"); -> Returns 0
| | | |
| |S|F|
snake.move("R"); -> Returns 1 (Snake eats the first food and right after that, the second food appears at (0,1) )
| |F| |
| |S|S|
snake.move("U"); -> Returns 1
| |F|S|
| | |S|
snake.move("L"); -> Returns 2 (Snake eats the second food)
| |S|S|
| | |S|
snake.move("U"); -> Returns -1 (Game over because snake collides with border)

Here you should realize Head and Tail position is must..
Whener there is food that becomes your head. 
The real question is
   - how you move the whole snake and direction changes withing the snakes?
   - May be Linked list. 
   - May be deque can contain all the position object ... first is head and end is tail.
Another one is Snake eating itelf.
   may be have Set<position> and compare if exist.

*/
class SnakeGame {

   class Position{
        int x;
        int y;
        public Position(int x,int y){
            this.x = x;
            this.y = y;
        }
        public boolean isEqual(Position p){
            return this.x==p.x && this.y == p.y ;
        }
    }
    
    int len;
    int rows ,cols;
    int[][] food;
    LinkedList<Position> snake;
   
    public SnakeGame(int width, int height, int[][] food) {
        this.rows = height;
        this.cols = width;
        this.food = food;
   
        snake = new LinkedList<Position>();
        snake.add(new Position(0,0));
        len = 0;
    }
    
    public int move(String direction) {
    	//if(len>=food.length) return len;
    
        Position cur = new Position(snake.get(0).x,snake.get(0).y);
        
        switch(direction){
            case "U": 
                cur.x--;  break;
            case "L": 
                cur.y--; break;
            case "R": 
                cur.y++;   break;
            case "D": 
                cur.x++;   break;
        }
        
        // boundaries check 
        if(cur.x<0 || cur.x>= rows || cur.y<0 || cur.y>=cols) return -1;
        
        // snake eating itself 
        // VERY IMP :  we are not checking the tail of the snake (last element of)
        for(int i=0;i<snake.size()-1;i++){  
            Position next = snake.get(i);
            if(next.isEqual(cur)) return -1;	       
        }
        
        // is it food? 
        snake.addFirst(cur); 
        boolean isFood = false;
        if(len<food.length){
            Position p = new Position(food[len][0],food[len][1]);	        
            if(cur.isEqual(p)){	            
                len++;
                isFood=true;
            }
        }
        
        if(!isFood)
           snake.removeLast();

        return len;
    }
}
