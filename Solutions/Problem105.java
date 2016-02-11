import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Problem105 {

    private static void getIntegerSubsets(Set<Set<Integer>> set, final Set<Integer> lon) {
        if (lon.size() == 1) {
            set.add(lon);
        } else {
            for (int i = 0; i < lon.size(); i++) {
                Set<Integer> subset = new HashSet<Integer>();
                Iterator<Integer> iterator = lon.iterator();
                for (int j = 0; j < lon.size(); j++) {
                    if (j != i) {
                        subset.add(iterator.next());
                    } else {
                        iterator.next();
                    }
                }

                if (set.add(subset)) {
                    getIntegerSubsets(set, subset);
                }
            }
        }
    }

    private static Set<Integer> getIntegerSet(String line) {
        String[] numberString = line.split(",");
        Integer[] numbers = new Integer[numberString.length];
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = Integer.parseInt(numberString[i]);
        }
        Set<Integer> ints = new HashSet<Integer>(Arrays.asList(numbers));
        return ints;
    }

    private static boolean disjoint(Set<Integer> b, Set<Integer> c) {
        for (Integer i : b) {
            if (c.contains(i)) return false;
        }
        return true;
    }

    private static boolean subsetsValid(Set<Integer> b, Set<Integer> c) {
        if (sum(b) == sum(c)) return false;
        if (b.size() > c.size() && sum(b) <= sum(c)) return false;
        return true;
    }

    private static boolean isSpecial(Set<Set<Integer>> sets) {
        for (Set<Integer> b : sets) {
            for (Set<Integer> c : sets) {
                if (b != c && disjoint(b,c) && !subsetsValid(b, c)) return false;
            }
        }
        return true;
    }

    private static int sum(Set<Integer> s) {
        int total = 0;
        for (Integer i : s) {
            total += i;
        }
        return total;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader("p105_sets.txt"));
        String line = br.readLine();
        int total = 0;

        while (line != null) {
            Set<Integer> ints = getIntegerSet(line);
            Set<Set<Integer>> set = new HashSet<Set<Integer>>();
            getIntegerSubsets(set, ints);
            if (isSpecial(set)) {
                total += sum(ints);
            }

            line = br.readLine();
        }
        System.out.println(total);
    }
}
