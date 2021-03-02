/*

https://leetcode.com/problems/product-of-the-last-k-numbers/
Implement the class ProductOfNumbers that supports two methods:

1. add(int num)

Adds the number num to the back of the current list of numbers.
2. getProduct(int k)

Returns the product of the last k numbers in the current list.
You can assume that always the current list has at least k numbers.
At any time, the product of any contiguous sequence of numbers will fit into a single 32-bit integer without overflowing.

*/
class ProductOfNumbers {
    List<Integer> prefixProd;
    
    public ProductOfNumbers() {
          prefixProd = new ArrayList<Integer>();  
    }
    
    public void add(int num) {
        if(num==0){
            prefixProd.clear();
            return; 
        }
        
        if(prefixProd.size()==0){
            prefixProd.add(num);
        } else{
            int prod = prefixProd.get(prefixProd.size()-1) * num; 
            prefixProd.add(prod);
        }  
    }
    
    public int getProduct(int k) {
        if(k>prefixProd.size())
            return 0;
        
        if(k==prefixProd.size())
            return prefixProd.get(prefixProd.size()-1);
        
        int prod = prefixProd.get(prefixProd.size()-1);
        int div  = prefixProd.get(prefixProd.size()-k-1);
        
        return prod/div;
    }
}
