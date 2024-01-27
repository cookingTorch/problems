package jmb;

import static java.lang.Integer.parseInt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;
public class Sopung {

    private static List<Pair> pairs;
    private static Set<Pair> isVisited;
    private static int count;

    public static void main(String[] args) throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        int testcase = parseInt(bf.readLine());

        while (testcase-- > 0) {
            StringTokenizer stringTokenizer = new StringTokenizer(bf.readLine());
            int studentCount = parseInt(stringTokenizer.nextToken());
            int pairCount = parseInt(stringTokenizer.nextToken());
            count = 0;

            stringTokenizer = new StringTokenizer(bf.readLine());
            pairs = getPairs(stringTokenizer);
            isVisited = new HashSet<>();
            search(studentCount / 2, 0);

            System.out.println(count);
        }
    }

    private static void search(int length, int depth) {
        if(length == isVisited.size()) {
            count++;
            return ;
        }

        for (int i = depth; i < pairs.size(); i++) {
            Pair target = pairs.get(i);
            if (isVisited.stream().noneMatch(pair -> pair.isDuplicate(target))) {
                isVisited.add(target);
                search(length, i + 1);
                isVisited.remove(target);
            }
        }
    }

    private static List<Pair> getPairs(StringTokenizer stringTokenizer) {
        List<Pair> pairs = new ArrayList<>();
        while (stringTokenizer.hasMoreElements()) {
            int left = parseInt(stringTokenizer.nextToken());
            int right = parseInt(stringTokenizer.nextToken());
            pairs.add(new Pair(left, right));
        }
        pairs.sort(Comparator.comparingInt(Pair::getLeft));
        return pairs;
    }
}

class Pair {
    private int left;
    private int right;

    public Pair(int left, int right) {
        this.left = left;
        this.right = right;
    }

    public boolean isDuplicate(Pair target) {
        return this.left == target.getLeft() || this.right == target.getRight()
                || this.left == target.getRight() || this.right == target.getLeft();
    }

    public int getLeft() {
        return left;
    }

    public int getRight() {
        return right;
    }
}
