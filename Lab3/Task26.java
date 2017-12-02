import java.io.BufferedReader;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.TreeSet;
import java.util.stream.IntStream;

/**
 * @author Egor Zhdan
 */
public class Task26 {
    public static void main(String[] args) throws Exception {
        BufferedReader in = new BufferedReader(new FileReader("nextsetpartition.in"));
        PrintWriter out = new PrintWriter("nextsetpartition.out");
        while (true) {
            String[] line1 = in.readLine().split(" ");
            int n = Integer.parseInt(line1[0]);
            int k = Integer.parseInt(line1[1]);
            if (n == 0 && k == 0) break;

            ArrayList<ArrayList<Integer>> sets = new ArrayList<>(k);
            for (int i = 0; i < k; i++) {
                ArrayList<Integer> cur = new ArrayList<>();
                Arrays.stream(in.readLine().split(" ")).mapToInt(Integer::parseInt).forEach(cur::add);
                sets.add(cur);
            }

            StringBuilder sb = new StringBuilder();
            TreeSet<Integer> used = new TreeSet<>();
            boolean finished = false;
            for (int setIdx = k - 1; setIdx >= 0 && !finished; setIdx--) {
                ArrayList<Integer> set = sets.get(setIdx);
                for (int itemIdx = set.size() - 1; itemIdx >= 0 && !finished; itemIdx--) {
                    Integer next = used.ceiling(set.get(itemIdx));
                    if (next == null) {
                        used.add(set.get(itemIdx));
                        continue;
                    }
                    int cnt = used.size() + setIdx;
                    if (itemIdx + 1 == set.size()) {
                        sb.append(n).append(' ').append(cnt).append('\n');
                        IntStream.range(0, setIdx).forEach(i -> {
                            sets.get(i).forEach(j -> sb.append(j).append(' '));
                            sb.append('\n');
                        });
                        IntStream.range(0, itemIdx + 1).forEach(i -> sb.append(set.get(i)).append(' '));
                        sb.append(next).append('\n');
                        used.remove(next);
                        used.forEach(i -> sb.append(i).append('\n'));

                        finished = true;
                    } else if (itemIdx == 0) {
                        used.add(set.get(itemIdx));
                    } else {
                        sb.append(n).append(' ').append(cnt + 1).append('\n');
                        IntStream.range(0, setIdx).forEach(i -> {
                            sets.get(i).forEach(j -> sb.append(j).append(' '));
                            sb.append('\n');
                        });
                        IntStream.range(0, itemIdx).forEach(i -> sb.append(set.get(i)).append(' '));
                        sb.append(next).append('\n');
                        used.remove(next);
                        used.add(set.get(itemIdx));
                        used.forEach(i -> sb.append(i).append('\n'));

                        finished = true;
                    }
                }
            }
            if (!finished) {
                sb.append(n).append(' ').append(n).append('\n');
                IntStream.range(0, n).forEach(i -> sb.append(i + 1).append('\n'));
            }
            out.println(sb);

            in.readLine();
        }
        out.close();
    }
}
