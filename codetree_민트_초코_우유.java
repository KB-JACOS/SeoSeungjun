import java.util.*;
import java.io.*;

public class Main {

    static final int T = 1, C = 2, M = 3, CM = 4, TM = 5, TC = 6, TCM = 7;

    static final boolean[][] food = {
            {false, false, false},
            {true, false, false},
            {false, true, false},
            {false, false, true},
            {false, true, true},
            {true, false, true},
            {true, true, false},
            {true, true, true}
    };

    static int N, Tcase;
    static int[][] map, trust;

    static int[] dr = {-1, 1, 0, 0}, dc = {0, 0, -1, 1};

    public static void main(String[] args) throws Exception {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        Tcase = Integer.parseInt(st.nextToken());

        map = new int[N][N];
        trust = new int[N][N];

        for(int i=0;i<N;i++){
            char[] c = br.readLine().toCharArray();
            for(int j=0;j<N;j++){
                if(c[j]=='T') map[i][j]=T;
                else if(c[j]=='C') map[i][j]=C;
                else map[i][j]=M;
            }
        }

        for(int i=0;i<N;i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0;j<N;j++){
                trust[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        StringBuilder sb = new StringBuilder();

        while(Tcase-- > 0){
            morning();
            List<Group> groups = lunch();
            dinner(groups);

            int[] res = calc();
            for(int i=7;i>=1;i--) sb.append(res[i]).append(" ");
            sb.append("\n");
        }

        System.out.print(sb);
    }

    static void morning(){
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                trust[i][j]++;
    }

    static List<Group> lunch(){

        int[][] visited = new int[N][N];
        List<Group> list = new ArrayList<>();

        int gid = 1;

        for(int i=0;i<N;i++){
            for(int j=0;j<N;j++){
                if(visited[i][j]!=0) continue;
                list.add(makeGroup(i,j,visited,gid++));
            }
        }

        return list;
    }

    static Group makeGroup(int sr,int sc,int[][] visited,int gid){

        Queue<int[]> q = new LinkedList<>();
        q.add(new int[]{sr,sc});
        visited[sr][sc]=gid;

        List<int[]> members = new ArrayList<>();
        members.add(new int[]{sr,sc});

        while(!q.isEmpty()){
            int[] cur=q.poll();

            for(int d=0;d<4;d++){
                int nr=cur[0]+dr[d];
                int nc=cur[1]+dc[d];

                if(nr<0||nc<0||nr>=N||nc>=N) continue;
                if(visited[nr][nc]!=0) continue;
                if(map[nr][nc]!=map[sr][sc]) continue;

                visited[nr][nc]=gid;
                q.add(new int[]{nr,nc});
                members.add(new int[]{nr,nc});
            }
        }

        // leader 선정 (감소 전 기준!)
        members.sort((a,b)->{
            if(trust[a[0]][a[1]]!=trust[b[0]][b[1]])
                return trust[b[0]][b[1]]-trust[a[0]][a[1]];
            if(a[0]!=b[0]) return a[0]-b[0];
            return a[1]-b[1];
        });

        int[] leader = members.get(0);

        // 신앙심 이동
        for(int[] m: members){
            if(m[0]==leader[0] && m[1]==leader[1]) continue;
            trust[m[0]][m[1]]--;
            trust[leader[0]][leader[1]]++;
        }

        return new Group(leader[0],leader[1]);
    }

    static void dinner(List<Group> list){

        boolean[][] attacked = new boolean[N][N];

        list.sort((a,b)->{
            if(type(map[a.r][a.c])!=type(map[b.r][b.c]))
                return type(map[a.r][a.c])-type(map[b.r][b.c]);

            if(trust[a.r][a.c]!=trust[b.r][b.c])
                return trust[b.r][b.c]-trust[a.r][a.c];

            if(a.r!=b.r) return a.r-b.r;
            return a.c-b.c;
        });

        for(Group g:list){

            int r=g.r, c=g.c;

            if(attacked[r][c]) continue;

            int dir = trust[r][c]%4;
            int x = trust[r][c]-1;
            trust[r][c]=1;

            while(true){
                int nr=r+dr[dir];
                int nc=c+dc[dir];

                if(nr<0||nc<0||nr>=N||nc>=N) break;

                if(map[nr][nc]==map[g.r][g.c]){
                    r=nr; c=nc;
                    continue;
                }

                int y=trust[nr][nc];

                if(x>y){ // strong
                    map[nr][nc]=map[g.r][g.c];
                    x -= (y+1);
                    trust[nr][nc]+=1;
                    attacked[nr][nc]=true;
                }
                else{ // weak
                    map[nr][nc]=union(map[g.r][g.c], map[nr][nc]);
                    trust[nr][nc]+=x;
                    attacked[nr][nc]=true;
                    x=0;
                }

                if(x==0) break;

                r=nr; c=nc;
            }
        }
    }

    static int union(int a,int b){
        boolean m = food[a][0]||food[b][0];
        boolean c = food[a][1]||food[b][1];
        boolean milk = food[a][2]||food[b][2];

        if(m&&c&&milk) return 7;
        if(c&&milk) return 4;
        if(m&&milk) return 5;
        if(m&&c) return 6;
        if(m) return 1;
        if(c) return 2;
        return 3;
    }

    static int type(int t){
        if(t<=3) return 1;
        if(t<=6) return 2;
        return 3;
    }

    static int[] calc(){
        int[] res = new int[8];
        for(int i=0;i<N;i++)
            for(int j=0;j<N;j++)
                res[map[i][j]] += trust[i][j];
        return res;
    }

    static class Group{
        int r,c;
        Group(int r,int c){this.r=r; this.c=c;}
    }
}