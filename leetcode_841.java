class Solution {
    boolean[] visited;
    int cnt = 0;
    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        
        int n = rooms.size();
        visited = new boolean[n];

        visited[0] = true;
        visitRoom(rooms, 0);

        return (cnt == n) ? true : false;
    }

    public void visitRoom(List<List<Integer>> rooms,int roomNum) {
        cnt++;
        List<Integer> keyList = rooms.get(roomNum);
        
        for(Integer key : keyList){
            if(visited[key] == false){
                visited[key] = true;
                visitRoom(rooms, key);
            }
        }

    }
}