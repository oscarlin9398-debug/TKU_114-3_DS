
public class ContestRankingSystem {
    public static void main(String[] args) {
        Contestant[] contestants = {
            new Contestant("C001", "Alice", 95, 120.5),
            new Contestant("C002", "Bob", 88, 98.0),
            new Contestant("C003", "Charlie", 95, 115.0), // 與 Alice 同分，秒數較少 (排名應在前)
            new Contestant("C004", "David", 72, 150.2),
            new Contestant("C005", "Eve", 95, 120.5),   // 與 Alice 同分同秒 (排名與名次應相同)
            new Contestant("C006", "Frank", 88, 90.5),   // 與 Bob 同分，秒數較少 (排名應在前)
            new Contestant("C007", "Grace", 100, 110.0), // 最高分
            new Contestant("C008", "Hank", 72, 140.0)    // 與 David 同分，秒數較少 (排名應在前)
        };

        System.out.println("=== 排序前的參賽者資料 ===");
        printContestants(contestants);

        insertionSortContestants(contestants);

        System.out.println("\n=== 競賽最終排名與完整資料 ===");
        displayRankings(contestants);
    }

    public static void insertionSortContestants(Contestant[] contestants) {
        if (contestants == null || contestants.length <= 1) {
            return;
        }

        for (int index = 1; index < contestants.length; index++) {
            Contestant key = contestants[index];
            if (key == null) {
                continue;
            }

            int position = index - 1;

            while (position >= 0 && contestants[position] != null && shouldSwap(contestants[position], key)) {
                contestants[position + 1] = contestants[position];
                position--;
            }

            contestants[position + 1] = key;
        }
    }

    private static boolean shouldSwap(Contestant current, Contestant key) {
        if (current.getScore() < key.getScore()) {
            return true;
        }

        if (current.getScore() == key.getScore() && current.getTimeSeconds() > key.getTimeSeconds()) {
            return true;
        }

        return false;
    }

    public static void displayRankings(Contestant[] sortedContestants) {
        if (sortedContestants == null || sortedContestants.length == 0) {
            System.out.println("無參賽者資料可顯示。");
            return;
        }

        System.out.printf("%-6s | %s%n", "名次", "完整參賽者資料");
        System.out.println("------------------------------------------------------------------");

        int currentRank = 1;

        for (int i = 0; i < sortedContestants.length; i++) {
            if (i > 0) {
                Contestant prev = sortedContestants[i - 1];
                Contestant curr = sortedContestants[i];

                if (curr.getScore() < prev.getScore() || curr.getTimeSeconds() > prev.getTimeSeconds()) {
                    currentRank = i + 1;
                }
            }

            System.out.printf("第 %-3d 名 | %s%n", currentRank, sortedContestants[i]);
        }
    }

    public static void printContestants(Contestant[] contestants) {
        if (contestants == null || contestants.length == 0) {
            System.out.println("無參賽者資料。");
            return;
        }

        for (Contestant contestant : contestants) {
            System.out.println(contestant);
        }
    }
}
