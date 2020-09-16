/*
https://leetcode.com/problems/two-sum-less-than-k/
Given an array of integers, how many pairs are there whose sum is less than K.
Time : n
*/
int findSmallerPairs(int arr[], int K) { 
    Arrays.sort(arr);
    int left = 0, right = n - 1; 
    int result = 0; 

    while (left < right){ 
    
        if (arr[left] + arr[right] < K){ 
        
            result += (right - left); 
            left++; 
        } else{
            right--; 
        }
    } 

    return result; 
} 
