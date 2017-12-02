import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;

/**
 * @author Egor Zhdan
 */
public class Task27 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("nextbrackets.in"));
        PrintWriter out = new PrintWriter("nextbrackets.out");
        char[] b = in.readLine().toCharArray();

        int balance = 0;
        for (int i = b.length - 1; i > 0; i--) {
            balance += (b[i] == ')') ? 1 : -1;

            if (b[i] == '(' && balance > 0) {
                b[i] = ')';
                balance--;
                for (int j = i + 1; j < b.length; j++) {
                    if (b.length - j > balance) {
                        b[j] = '(';
                        balance++;
                    } else {
                        b[j] = ')';
                        balance--;
                    }
                }

                out.println(new String(b));
                out.close();
                return;
            }
        }
        out.println("-");
        out.close();
    }
}
