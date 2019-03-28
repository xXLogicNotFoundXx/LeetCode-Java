// An abstract class with constructor 
abstract class Base { 
    Base() { System.out.println("Base Constructor Called"); } 
    abstract void fun(); 
} 
class Derived extends Base { 
    Derived() { System.out.println("Derived Constructor Called"); } 
    void fun() { System.out.println("Derived fun() called"); } 
} 
class Main { 
    public static void main(String args[]) {  
       Derived d = new Derived(); 
    } 
} 

Output:
Base Constructor Called
Derived Constructor Called

A concrete example of an abstract class would be a class called Animal. 
The Animal is the abstract class and Duck/Pig/Cat are all classes that derive from that base class.
Animals might provide a function called "Age" that adds 1 year of life to the animals.
    
    
Where as Interface is a contract with a class.
