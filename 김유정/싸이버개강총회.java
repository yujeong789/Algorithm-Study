import java.io.*;
import java.util.*;

public class Main {
	static int result;
	static Set<String> set;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		String aa = st.nextToken();
		String bb = st.nextToken();
		String cc = st.nextToken();
		
		int a = Integer.parseInt(aa.substring(0,2))*60+Integer.parseInt(aa.substring(3,5));
		int b = Integer.parseInt(bb.substring(0,2))*60+Integer.parseInt(bb.substring(3,5));
		int c = Integer.parseInt(cc.substring(0,2))*60+Integer.parseInt(cc.substring(3,5));

		result = 0;
		set = new HashSet<>();
		
		String line;
		while((line=br.readLine())!=null) {
			st = new StringTokenizer(line);
			
			if(!st.hasMoreTokens()) break;
			
			String timeString = st.nextToken();
			String name = st.nextToken();
			int time = Integer.parseInt(timeString.substring(0,2))*60+Integer.parseInt(timeString.substring(3,5));
			
			if(time <= a ) {
				set.add(name);
			}else if(b <= time && time <= c) {
				if(set.contains(name)) {
					set.remove(name);
					result++;
				}
			}
			
		}
		System.out.println(result);
		
		
	}
}
