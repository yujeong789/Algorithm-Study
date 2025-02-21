import java.util.*;
import java.io.*;

public class Main {
    static class Lecture implements Comparable<Lecture> {
        int start, end;
        
        Lecture(int start, int end) {
            this.start = start;
            this.end = end;
        }
        
        @Override
        public int compareTo(Lecture o) {
            return this.start - o.start; 
        }
    }
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        
        Lecture[] lectures = new Lecture[N];
        for(int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = Integer.parseInt(st.nextToken());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            lectures[i] = new Lecture(start, end);
        }
        
        Arrays.sort(lectures);
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        pq.offer(lectures[0].end);
        
        for(int i = 1; i < N; i++) {
            if(pq.peek() <= lectures[i].start) {
                pq.poll(); 
            }
            pq.offer(lectures[i].end);  
        }
        
        System.out.println(pq.size());
    }
}