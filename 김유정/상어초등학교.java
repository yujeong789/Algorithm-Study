
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.StringTokenizer;

public class 상어초등학교 {
    static int N;
    static int[][] map;
    static int[][] student;
    static Map<Integer, Integer> satisfy = new HashMap<>();
    static int sum;
    
    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        student = new int[N*N][5];
        satisfy.put(0, 0);
        satisfy.put(1, 1);
        satisfy.put(2, 10);
        satisfy.put(3, 100);
        satisfy.put(4, 1000);

        for(int i = 0; i < N*N; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < 5; j++) {
                student[i][j] = Integer.parseInt(st.nextToken());                
            }
        }// 입력
        
        for(int i = 0; i < N*N; i++) {
//            System.out.print(i);
            bfs(student[i]);
        }
        
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }System.out.println();
        
        sum = 0;
        find_satisfy();
        System.out.println(sum);
    }
    
    private static void find_satisfy() {
		for(int i = 0; i < N; i++) {//map
			for(int j = 0; j < N; j++) {//map
				
				for(int k = 0; k < N*N; k++) {//student
					if(map[i][j]!=student[k][0]) continue;
					int cnt=0;
					for(int d = 0; d < 4; d++) {//사방탐색
						int nr = i + dr[d];
						int nc = j + dc[d];
						if(!check(nr,nc)) continue;
						if(map[nr][nc]==student[k][1] || map[nr][nc]==student[k][2] || 
								map[nr][nc]==student[k][3] || map[nr][nc]==student[k][4]) {
							cnt++;
						}
					}
					sum += satisfy.get(cnt);
					break;
				}
				
			}
		}
		
	}

	private static void bfs(int[] friend) {
//        System.out.println(Arrays.toString(friend));
        int person = friend[0];
        
        //1. 비어있는 칸 중에서 좋아하는 학생이 인접한 칸에 가장 많은 칸으로 자리를 정한다.
        Queue<int[]> step1 = new LinkedList<>();
        int like=0;
        for(int i = 0; i < N; i++) {
            for(int j = 0; j < N; j++) {
                if(map[i][j]!=0) continue;//비어있는 칸 중에서
                int cnt=0;
                for(int d = 0; d < 4; d++) {
                    int nr = i+dr[d];
                    int nc = j+dc[d];
                    
                    if(!check(nr,nc)) continue;
                    if(map[nr][nc]==friend[1] || map[nr][nc]==friend[2] || 
                            map[nr][nc]==friend[3] || map[nr][nc]==friend[4]) {
                        cnt++;
                    }
                }
                
                if(cnt==like) {
                	step1.offer(new int[] {i,j});
                }else if(cnt>like) {
                    like = cnt;
                    step1 = new LinkedList<>();
                    step1.offer(new int[] {i,j});
                }
                
            }
        }
    
        if(step1.size()==1) {
            map[step1.peek()[0]][step1.peek()[1]] = person;
            return;
        }
        
//        System.out.println("step1");
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }System.out.println();
        
        //2. 1을 만족하는 칸이 여러 개이면, 인접한 칸 중에서 비어있는 칸이 가장 많은 칸으로 자리를 정한다.
        Queue<int[]> step2 = new LinkedList<>();
        int blank = 0; // 인접한 칸 중에서 비어있는 칸의 개수
        if(step1.size()>1) {
            
            int size = step1.size(); // step1의 크기
            
            for(int i = 0; i < size; i++) { // step1
                int r = step1.peek()[0];
                int c = step1.peek()[1];
                int cnt=0; // r,c에서 인접한, 비어있는 칸의 개수
                
                if(map[r][c]!=0) continue;
                for(int d = 0; d < 4; d++) { // 사방탐색
                    int nr = r+dr[d];
                    int nc = c+dc[d];
                    
                    if(!check(nr,nc)) continue;
                    if(map[nr][nc]!=0) continue;
                    cnt++;
                }
                if(cnt==blank) {
                    step2.offer(new int[] {step1.peek()[0],step1.peek()[1]});
                } else if(cnt>blank) {
                    blank = cnt;
                    step2 = new LinkedList<>();
                    step2.offer(new int[] {step1.peek()[0],step1.peek()[1]});
                }
                step1.poll();
            } // step1
        }
        if(step2.size()==1){
            map[step2.peek()[0]][step2.peek()[1]] = person;
            return;
        }
        
//        System.out.println("step2");
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }System.out.println();
        
        //3.2를 만족하는 칸도 여러 개인 경우에는 행의 번호가 가장 작은 칸으로, 
        //그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다.
        Queue<int[]> step3 = new LinkedList<>();
        if(step2.size()>1) {
            
            step3.offer(new int[] {step2.peek()[0], step2.peek()[1]});
            step2.poll();
            int size = step2.size();
            
            for(int i = 0; i < size; i++) {
                int r = step2.peek()[0];
                int c = step2.peek()[1];
                
                if(map[r][c]!=0) continue;
                if(r==step3.peek()[0]) {
                	step3.offer(new int[] {r,c});
                } else if(r < step3.peek()[0]) {
                    step3 = new LinkedList<>();
                    step3.offer(new int[] {r,c});
                }
                step2.poll();
            }
        }

        if(step3.size()==1) {
            map[step3.peek()[0]][step3.peek()[1]] = person;
            return;
        }
//        System.out.println("step3");
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }System.out.println();
        
        //4.그러한 칸도 여러 개이면 열의 번호가 가장 작은 칸으로 자리를 정한다. -> 수정필요
        Queue<int[]> step4 = new LinkedList<>();
        if(step3.size()>1) {
            
            step4.offer(new int[] {step3.peek()[0], step3.peek()[1]});
            step3.poll();
            int size = step3.size();
            
            for(int i = 0; i < size; i++) {
                int r = step3.peek()[0];
                int c = step3.peek()[1];
                
                if(map[r][c]!=0) continue;
                if(c==step4.peek()[1]) {
                    step4.offer(new int[] {r,c});
                }else if(c < step4.peek()[1]) {
                    step4 = new LinkedList<>();
                    step4.offer(new int[] {r,c});
                }
                step3.poll();
            }
        }
        map[step4.peek()[0]][step4.peek()[1]] = person;
//        System.out.println("step4");
//        for(int i = 0; i < N; i++) {
//            System.out.println(Arrays.toString(map[i]));
//        }System.out.println();
        return;
    }

    private static boolean check(int r, int c) {
        return r>=0 && r<N && c>=0 && c<N;
    }

    static int[] dr = {-1,0,1,0};
    static int[] dc = {0,1,0,-1};
    
    
}