import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 행렬
 * 백준 - 1080
 * 실버 - 1
 */
public class Main {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());

		int answer = 0;
		int[][] board = new int[N][M];
		int[][] memo = new int[N][M];
		int[][] target = new int[N][M];

		init(br, N, M, board);
		init(br, N, M, target);

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((board[i][j] + memo[i][j]) % 2 != target[i][j]) { //이때까지 실행한 행렬 뒤집는 연산의 반복으로 target을 만들지 못하면
					if (N - i < 3 || M - j < 3) { // 뒤집는 연산은 무조건 3x3 실행해야하므로 현재 위치에서는 실행할 수 없음. -> 불가능
						System.out.println(-1);
						return;
					} else { //현재위치를 3x3 행렬의 0,0으로 잡고 행렬 뒤집기.
						answer++;
						flipBoard(memo, i, j);
					}
				}
			}
		}

		System.out.println(answer);
	}

	private static void init(BufferedReader br, int N, int M, int[][] board) throws IOException {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				board[i][j] = br.read() - '0';
			}
			br.readLine();
		}
	}

	private static void flipBoard(int[][] memo, int i, int j) { //(i,j) 기준으로 3x3사이즈 영역에대하여 뒤집은 횟수를 증가.
		for (int k = i; k < i + 3; k++) {
			for (int l = j; l < j + 3; l++) {
				memo[k][l]++;
			}
		}
	}
}
