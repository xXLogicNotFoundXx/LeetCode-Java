Here there is a problem of longest subarray in two arrays problem.
It is same as classic DP - Finding longest substring in two strings problem.

Subarray problems can be solved by :

Prefix_sum/Prefix_product startegy.
2 PASS works great too.
binary search for some.



A set of size 'n' can have a total of [n*(n+1)] / 2 Subarrays.
Let the array be [1,2,3].
Subarrays are contiguous elements i.e 1,2,3,12,23,123 here.
For n =3 , total number of subsets / subarrays are  ( [3* 4] / 2) = 6.

Also,
If [5,2,6] is a subarray with product less than K, then all subarrays of [5,2,6] have product less than K.
The number of subarrays can be calculated with an arithmetic series, in this case 1 + 2 + 3 = 6

Set of size n elements can form 2^n subsets.
