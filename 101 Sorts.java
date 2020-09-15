// Quick sort avg case NLogN worst case N^2
    void QuickSort(int []nums,int start,int end){
        if(start<end){
            int pIndex = quickSelectPartition(nums,start,end);
            QuickSort(nums,start,pIndex-1);
            QuickSort(nums,pIndex+1,end);
        }
    }
    
    int quickSelectPartition(int []nums,int start,int end){
        int pivot = nums[end];
        int pIndex = start;   // very IMP
        for(int i=start;i<end;i++){
            if(nums[i]<=pivot){
                swap(nums,pIndex,i);
                pIndex++;
            }
        }
        swap(nums,pIndex,end);
        return pIndex;
    }

    void swap(int []nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
