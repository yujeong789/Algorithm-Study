import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class 구간합구하기 {
    private static int N, M, K;
    private static long[] indexedTree;
    private static int leafCnt;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        // 인덱스 트리 초기화
        leafCnt = 1;
        while (leafCnt < N) {
            leafCnt *= 2;
        }
        indexedTree = new long[leafCnt * 2];

        // 리프 노드에 값 저장
        for (int i = 0; i < N; i++) {
            indexedTree[leafCnt + i] = Long.parseLong(br.readLine());
        }

        // 상위 노드 초기화
        init();

        // 쿼리 처리
        for (int i = 0; i < M + K; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            long c = Long.parseLong(st.nextToken());

            if (a == 1) {
                update(leafCnt + b - 1, c);
            } else {
                long result = query(leafCnt + b - 1, leafCnt + (int)c - 1);
                sb.append(result).append("\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    // 트리 초기화
    public static void init() {
        for (int i = leafCnt - 1; i > 0; i--) {
            indexedTree[i] = indexedTree[i * 2] + indexedTree[i * 2 + 1];
        }
    }

    // 값 업데이트
    public static void update(int idx, long val) {
        long diff = val - indexedTree[idx];
        while (idx > 0) {
            indexedTree[idx] += diff;
            idx >>= 1;
        }
    }

    // 구간 합 구하기
    public static long query(int start, int end) {
        long result = 0;
        while (start <= end) {
            if (start % 2 == 1) result += indexedTree[start++];
            if (end % 2 == 0) result += indexedTree[end--];
            start >>= 1;
            end >>= 1;
        }
        return result;
    }
}
