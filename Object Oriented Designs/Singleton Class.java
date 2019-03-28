public class SingleTon {
     
    private static MySingleTon myObj;
 
    private SingleTon(){   
    }
    
    public static SingleTon getInstance(){
        if(myObj == null)
            myObj = new SingleTon();
        
        return myObj;
    }
}
