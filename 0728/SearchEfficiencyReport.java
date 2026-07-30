public class SearchEfficiencyReport {
    public static void main(String[] args) {
        int[] sizes = {16, 128, 1024};

        for (int size : sizes) {
            // 建立已排序資料：2, 4, 6, ..., size * 2
            int[] data = new int[size];
            for (int i = 0; i < size; i++) {
                data[i] = (i + 1) * 2;
            }

            int first = data[0];
            int last = data[size - 1];
            int notFound = size * 2 + 1; // 奇數肯定不在陣列中

            System.out.println("==================================================");
            System.out.println("【資料規模 N = " + size + "】");
            System.out.println("--------------------------------------------------");
            
            runTest(data, "搜尋第一筆 (" + first + ")", first);
            runTest(data, "搜尋最後一筆 (" + last + ")", last);
            runTest(data, "搜尋不存在資料 (" + notFound + ")", notFound);
            System.out.println();
        }

        printReportSummary();
    }

    public static void runTest(int[] data, String testLabel, int target) {
        int seqChecks = sequentialSearchChecks(data, target);
        int binChecks = binarySearchChecks(data, target);

        System.out.printf("%-20s | 循序搜尋比對: %4d 次 | 二分搜尋比對: %2d 次%n",
                testLabel, seqChecks, binChecks);
    }

    public static int sequentialSearchChecks(int[] data, int target) {
        int checks = 0;
        for (int val : data) {
            checks++;
            if (val == target) {
                break;
            }
        }
        return checks;
    }

    public static int binarySearchChecks(int[] data, int target) {
        int low = 0;
        int high = data.length - 1;
        int checks = 0;

        while (low <= high) {
            checks++;
            int mid = low + (high - low) / 2;
            if (data[mid] == target) {
                break;
            }
            if (target < data[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return checks;
    }

    public static void printReportSummary() {
        System.out.println("==================================================");
        System.out.println("【搜尋效率分析結論與觀察結果】");
        System.out.println("1. 循序搜尋 (Sequential Search - O(N)):");
        System.out.println("   - 最佳情況：搜尋第一筆資料時只需 1 次比對，與 N 無關。");
        System.out.println("   - 最壞情況：搜尋最後一筆或不存在資料時，需要 N 次完整掃描。");
        System.out.println("   - 比較次數隨資料量增加呈直線性成長。");
        System.out.println();
        System.out.println("2. 二分搜尋 (Binary Search - O(log N)):");
        System.out.println("   - 無論是搜尋中間、邊界或不存在資料，比對次數上限均受限於 log2(N) + 1。");
        System.out.println("   - 資料量從 16 成長 64 倍至 1024 時，最壞比對次數僅從 5 次增加到 11 次。");
        System.out.println();
        System.out.println("3. 綜合評估與演算法選擇：");
        System.out.println("   - 已排序且需要多次查詢時，二分搜尋展現極高優勢。");
        System.out.println("   - 僅搜尋第一筆資料時，循序搜尋比對次數比二分搜尋更少，證明演算法選擇需考量實際情境。");
        System.out.println("==================================================");
    }
}
