import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class LibraryManagementSystem {

    private List<Book> bookList = new ArrayList<>();
    private Set<String> bookIdSet = new HashSet<>();

    public boolean addBook(String bookId, String title, String category, int borrowCount) {
        if (bookIdSet.contains(bookId)) {
            System.out.printf("  [系統警告] 新增失敗！書籍編號 %s 已存在，不可重複新增。%n", bookId);
            return false;
        }

        Book newBook = new Book(bookId, title, category, borrowCount);
        bookList.add(newBook);
        bookIdSet.add(bookId);
        System.out.printf("  [系統成功] 書籍 [%s - %s] 已成功加入館藏。%n", bookId, title);
        return true;
    }

    public void displayAllBooks() {
        if (bookList.isEmpty()) {
            System.out.println("  [館藏資訊] 目前沒有任何書籍資料。");
            return;
        }
        for (Book book : bookList) {
            System.out.println("  " + book);
        }
    }

    public void sortAndDisplayById() {
        System.out.println("--- 依書籍編號升冪排列 (Merge Sort) ---");
        if (bookList.isEmpty()) {
            System.out.println("  [館藏資訊] 館藏為空，無法進行排序。");
            return;
        }

        BookAlgorithms.sortByIdAscending(bookList, 0, bookList.size() - 1);
        displayAllBooks();
    }

    public void sortAndDisplayByBorrowCount() {
        System.out.println("--- 依借閱次數降冪排列 (Merge Sort) ---");
        if (bookList.isEmpty()) {
            System.out.println("  [館藏資訊] 館藏為空，無法進行排序。");
            return;
        }

        BookAlgorithms.sortByBorrowCountDescending(bookList, 0, bookList.size() - 1);
        displayAllBooks();
    }

    public void searchByIdBinary(String targetId) {
        System.out.printf("--- 二分搜尋 (Binary Search) 依編號查詢 [%s] ---%n", targetId);
        if (bookList.isEmpty()) {
            System.out.println("  [搜尋失敗] 目前館藏資料為空。");
            return;
        }

        // 確保執行二分搜尋前資料已按編號升冪排序
        BookAlgorithms.sortByIdAscending(bookList, 0, bookList.size() - 1);
        int index = BookAlgorithms.binarySearchById(bookList, targetId);

        if (index != -1) {
            System.out.printf("  [搜尋成功] 位於排序後索引 %d：%n    %s%n", index, bookList.get(index));
        } else {
            System.out.printf("  [搜尋失敗] 查無書籍編號為 [%s] 的館藏。%n", targetId);
        }
    }

    public void searchByCategorySequential(String category) {
        System.out.printf("--- 循序搜尋 (Sequential Search) 依分類查詢 [%s] ---%n", category);
        List<Book> results = BookAlgorithms.sequentialSearchByCategory(bookList, category);

        if (results.isEmpty()) {
            System.out.printf("  [搜尋結果] 查無分類為 [%s] 的任何書籍。%n", category);
        } else {
            System.out.printf("  找到 %d 筆符合分類的書籍：%n", results.size());
            for (Book book : results) {
                System.out.println("    " + book);
            }
        }
    }

    public static void main(String[] args) {
        LibraryManagementSystem library = new LibraryManagementSystem();

        System.out.println("==================================================================");
        System.out.println("         課 後 作 業 一 ： 圖 書 借 閱 資 料 管 理 系 統");
        System.out.println("==================================================================\n");

        System.out.println("=== 1. 空資料庫邊界情境測試 ===");
        library.displayAllBooks();
        library.searchByIdBinary("BK001");
        library.searchByCategorySequential("資訊工程");
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 2. 新增書籍與重複編號測試 ===");
        library.addBook("BK804", "Java 程式設計演算法", "資訊工程", 15);
        library.addBook("BK105", "資料結構大師指南", "資訊工程", 42);
        library.addBook("BK930", "品牌行銷實務", "企業管理", 8);
        library.addBook("BK212", "Python 數據分析", "資訊工程", 30);
        library.addBook("BK501", "管理學：理論與實務", "企業管理", 25);
        library.addBook("BK101", "網頁前端開發入門", "資訊工程", 50);

        // 重複編號測試
        library.addBook("BK105", "重複書籍名稱", "其他分類", 99);
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 3. 依書籍編號升冪排列 (Merge Sort) ===");
        library.sortAndDisplayById();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 4. 依借閱次數降冪排列 (Merge Sort) ===");
        library.sortAndDisplayByBorrowCount();
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 5. Binary Search 依編號查詢測試 (第一筆、最後一筆與不存在) ===");
        library.searchByIdBinary("BK101"); // 第一筆 (最小編號)
        library.searchByIdBinary("BK930"); // 最後一筆 (最大編號)
        library.searchByIdBinary("BK999"); // 不存在的編號
        System.out.println("------------------------------------------------------------------\n");

        System.out.println("=== 6. Sequential Search 依分類查詢測試 (多筆與不存在分類) ===");
        library.searchByCategorySequential("資訊工程");
        library.searchByCategorySequential("西洋文學");
    }
}
