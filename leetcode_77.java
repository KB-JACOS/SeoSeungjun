class leetcode_77 {

    List<List<Integer>> result =  new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        recur(n, k, 0, new ArrayList<Integer>(), 0);
        return result;
    }

    public void recur(int n, int k, int depth, List<Integer> answer, int last) {
        
        if(depth == k) {
            result.add(new ArrayList<>(answer));
            return;
        }

        for(int i=last+1;i<=n;i++) {
            answer.add(i);
            recur(n, k, depth+1, answer, i);
            answer.remove(answer.size() - 1 );
        }
    }
}