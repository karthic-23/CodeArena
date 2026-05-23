import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Integer> nums = new ArrayList<>();

        while (sc.hasNextInt()) {
            nums.add(sc.nextInt());
        }

        int target = nums.remove(nums.size() - 1);

        for (int i = 0; i < nums.size(); i++) {
            for (int j = i + 1; j < nums.size(); j++) {
                if (nums.get(i) + nums.get(j) == target) {
                    System.out.println("[" + i + "," + j + "]");
                    return;
                }
            }
        }
    }
}