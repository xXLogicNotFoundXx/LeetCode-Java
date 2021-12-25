/*
https://leetcode.com/problems/find-duplicate-file-in-system/
Given PATH filename.txt(content) return the list of PATH/filename.txt by their content which are same. 
Input:
["root/a 1.txt(abcd) 2.txt(efgh)", "root/c 3.txt(abcd)", "root/c/d 4.txt(efgh)", "root 4.txt(efgh)"]
Output:  
[["root/a/2.txt","root/c/d/4.txt","root/4.txt"],["root/a/1.txt","root/c/3.txt"]]
*/
class Solution {
    public List<List<String>> findDuplicate(String[] paths) {
        
        Map<String,List<String>> map = new HashMap<>();
        for(String str : paths){
            String[] path_files = str.split(" ");
            
            for(int i=1;i<path_files.length;i++){
                int open = path_files[i].indexOf("(");
                int end = path_files[i].indexOf(")");
                
                String fileName = path_files[i].substring(0,open);
                String content = path_files[i].substring(open+1,end);
                
                map.putIfAbsent(content, new ArrayList<String>());
                map.get(content).add(path_files[0]+"/"+fileName);;
            }
        }
        List<List<String>> ans = new ArrayList<List<String>>();
        for(Map.Entry<String,List<String>> e : map.entrySet()){
            if(e.getValue().size()>1)
                ans.add(e.getValue());
        }
        return ans;
    }
}

Follow-up beyond contest:

Imagine you are given a real file system, how will you search files? DFS or BFS?
    core idea: DFS
    Reason: if depth of directory is not too deeper, which is suitable to use DFS, comparing with BFS. Compute Hash of whole dile like MD5.
        
If the file content is very large (GB level), how will you modify your solution?
   DFS to map each size to a set of paths that have that size: Map<Integer, Set>
   For each size, if there are more than 2 files there, compute hashCode of every file by MD5, if any files with the same size have the same hash, then
   they are identical files: Map<String, Set>, mapping each hash to the Set of filepaths+filenames. This hash id's are very very big, so we use the
   Java library BigInteger.
     
If you can only read the file by 1kb each time, how will you modify your solution?
    if we have memory then read all the file in while loop ans store it in memorry and calculate hash.
        
What is the time complexity of your modified solution? What is the most time-consuming part and memory consuming part of it? How to optimize?
    Computing hash is the one takes time. 
        
How to make sure the duplicated files you find are not false positive?
    When the hash and size of the files are same then you check it chunk by chunk. 
        
