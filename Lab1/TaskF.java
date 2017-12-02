import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author Egor Zhdan
 */
public class TaskF {
    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(in.readLine());
        int[][] var = new int[1 << n][n];
        int[] eq = new int[1 << n];
        for (int i = 0; i < (1 << n); i++) {
            String[] input = in.readLine().split(" ");
            for (int j = 0; j < n; j++) {
                var[i][j] = input[0].charAt(j);
            }
            eq[i] = Integer.parseInt(input[1]);
        }

        int[][] tr = new int[1 << n][1 << n];
        for (int row = 0; row < eq.length; row++) {
            tr[row][0] = eq[row];
        }
        for (int col = 1; col < eq.length; col++) {
            for (int row = 0; row < eq.length - col; row++) {
                tr[row][col] = tr[row][col - 1] ^ tr[row + 1][col - 1];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int col = 0; col < eq.length; col++) {
            String bin = Integer.toBinaryString(col);
            for (int i = 0; i < n - bin.length(); i++) {
                sb.append('0');
            }
            sb.append(bin).append(' ').append(tr[0][col]).append('\n');
        }
        System.out.println(sb);
    }
}
