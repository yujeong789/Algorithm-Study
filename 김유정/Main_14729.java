import java.io.*;
import java.util.*;

public class Main {
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Double> pq = new PriorityQueue<>(Collections.reverseOrder());
        for(int i = 0; i < N; i++) {
        	pq.add(Double.parseDouble(br.readLine()));
        	if(pq.size()>7) pq.poll();
        }
        PriorityQueue<Double> pq2 = new PriorityQueue<>();
        for(int i = 0; i < 7; i++) {
        	pq2.add(pq.poll());
        }
        for(int i = 0; i < 7; i++) {
        	System.out.printf("%.3f\n", pq2.poll());
        }
    }
}
