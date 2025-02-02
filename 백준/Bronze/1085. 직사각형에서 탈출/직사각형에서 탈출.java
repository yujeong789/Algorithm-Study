import java.util.*;
import java.io.*;

public class Main {
	
    public static void main(String[] args) throws Exception {
    	Scanner sc = new Scanner(System.in);
    	int x = sc.nextInt();
    	int y = sc.nextInt();
    	int w = sc.nextInt();
    	int h = sc.nextInt();
    	
    	int row = Math.min(x, w-x);
    	int column = Math.min(y, h-y);
    	System.out.println(Math.min(row,column));
	}
}