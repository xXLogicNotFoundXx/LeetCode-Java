/*
Medium - IMP
Uber12 Facebook7 Amazon6 Google4 Bloomberg2 JPMorgan2

https://leetcode.com/problems/evaluate-division/
399. Evaluate Division
Given a / b = 2.0, b / c = 3.0.
queries are: a / c = ?, b / a = ?, a / e = ?, a / a = ?, x / x = ? .
return [6.0, 0.5, -1.0, 1.0, -1.0 ].
----------------------------------------------------------------------------------------

equations = [ ["a", "b"], ["b", "c"] ]
values = [2.0, 3.0]
a/b=2 => b/a=1/2  =0.5
b/c=3 => c/b=1/3  =0.333
To get a/c = a/b * b/c = 6

---------------------------------------------------------------------------------------
so the path sould exist from a->b->c and you multiply the numbers till the end node.
The map that gets constructed is :
[a: [b:2.0]
b: [a:0.5], [c:3.0]
c: [b:0.333]]

*/
class Solution {

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {

        Map<String, Map<String,Double>> map = new HashMap<>();
        buildEquations(map, equations, values);

        double []res = new double[queries.size()];

        int i=0;
        for(List<String> query : queries){
                                                  // new visited 
            Double dRes = calcEquationHelper(map, new HashSet<String>(), query.get(0), query.get(1));
            res[i] = dRes==null ? -1 : dRes;
            i++;
        }
        return res;
    }

    // Here we learned that if we want invalid value if primitive type int, double etc.
    // use Object and use null as invalid or else it is a valid value.
    Double calcEquationHelper( Map<String, Map<String,Double>> map, HashSet<String> visited, String var1, String var2){

        if(!map.containsKey(var1) || !map.containsKey(var2))
            return null;

        if(visited.contains(var1))
            return null;

        if(var1.equals(var2))
            return 1.0;   // IMP

        visited.add(var1);
        if(map.containsKey(var1)){

            // if direct path
            if( map.get(var1).containsKey(var2) )
                return map.get(var1).get(var2);

            for(Map.Entry<String,Double> e : map.get(var1).entrySet()){

                Double r = calcEquationHelper(map, visited, e.getKey(), var2);
                if(r!=null)
                    return r * e.getValue();
            }
        }
        visited.remove(var1);
        return null;
    }

    void buildEquations(Map<String, Map<String,Double>> map, List<List<String>> equations, double[] values){

        for(int i=0;i<equations.size();i++) {

            List<String> eq = equations.get(i);

            String var1=eq.get(0);
            String var2=eq.get(1);
            double res=values[i];

            map.putIfAbsent(var1,new HashMap<String,Double>());
            map.putIfAbsent(var2,new HashMap<String,Double>());

            map.get(var1).put(var2,res);
            map.get(var2).put(var1,1.0/res);
        }
    }
    /*
    class DenomResult {
        String den;
        double res;
        DenomResult(String d, double res){
            this.den = d;
            this.res = res;
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String,List<DenomResult>> map = new HashMap<>();
        for(int i=0;i<equations.size();i++) {
            List<String> eq = equations.get(i);
            addEq(map,eq.get(0),eq.get(1),values[i]);
        }

        double []res = new double[queries.size()];
        for(int i=0;i<queries.size();i++){
            List<String> query = queries.get(i);
            Double dRes = calcEquationHelper(map, new HashSet<String>(), query.get(0), query.get(1));
            res[i] = dRes==null ? -1 : dRes;
        }
        return res;
    }

    Double calcEquationHelper( Map<String,List<DenomResult>> map, HashSet<String> visited, String var1, String var2){
        if(!map.containsKey(var1) || !map.containsKey(var2)) return null;
        if(visited.contains(var1)) return null;
        if(var1.equals(var2)) return 1.0;

        visited.add(var1);
        if(map.containsKey(var1)){
            for(DenomResult dr : map.get(var1)){
                if(dr.den.equals(var2))
                    return dr.res;

                Double r = calcEquationHelper(map,visited,dr.den,var2);
                if(r!=null)
                    return r * dr.res;
            }
        }
        visited.remove(var1);
        return null;
    }

    void addEq(Map<String,List<DenomResult>> map, String var1, String var2, double res){
        map.putIfAbsent(var1,new ArrayList<DenomResult>());
        map.putIfAbsent(var2,new ArrayList<DenomResult>());
        map.get(var1).add(new DenomResult(var2,res));
        map.get(var2).add(new DenomResult(var1,1.0/res));
    }
    */
}
