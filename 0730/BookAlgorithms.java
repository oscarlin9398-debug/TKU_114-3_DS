import java.util.ArrayList;
import java.util.List;

public class BookAlgorithms {

    public static void sortByIdAscending(List<Book> books, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortByIdAscending(books, left, mid);
        sortByIdAscending(books, mid + 1, right);
        mergeByIdAscending(books, left, mid, right);
    }

    private static void mergeByIdAscending(List<Book> books, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<Book> leftList = new ArrayList<>();
        List<Book> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) {
            leftList.add(books.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(books.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).getBookId().compareTo(rightList.get(j).getBookId()) <= 0) {
                books.set(k, leftList.get(i));
                i++;
            } else {
                books.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            books.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            books.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static void sortByBorrowCountDescending(List<Book> books, int left, int right) {
        if (left >= right) return;

        int mid = left + (right - left) / 2;
        sortByBorrowCountDescending(books, left, mid);
        sortByBorrowCountDescending(books, mid + 1, right);
        mergeByBorrowCountDescending(books, left, mid, right);
    }

    private static void mergeByBorrowCountDescending(List<Book> books, int left, int mid, int right) {
        int leftSize = mid - left + 1;
        int rightSize = right - mid;

        List<Book> leftList = new ArrayList<>();
        List<Book> rightList = new ArrayList<>();

        for (int i = 0; i < leftSize; i++) {
            leftList.add(books.get(left + i));
        }
        for (int j = 0; j < rightSize; j++) {
            rightList.add(books.get(mid + 1 + j));
        }

        int i = 0, j = 0, k = left;

        while (i < leftSize && j < rightSize) {
            if (leftList.get(i).getBorrowCount() >= rightList.get(j).getBorrowCount()) {
                books.set(k, leftList.get(i));
                i++;
            } else {
                books.set(k, rightList.get(j));
                j++;
            }
            k++;
        }

        while (i < leftSize) {
            books.set(k, leftList.get(i));
            i++;
            k++;
        }

        while (j < rightSize) {
            books.set(k, rightList.get(j));
            j++;
            k++;
        }
    }

    public static int binarySearchById(List<Book> books, String targetId) {
        if (books == null || books.isEmpty() || targetId == null) {
            return -1;
        }

        int left = 0;
        int right = books.size() - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            int cmp = books.get(mid).getBookId().compareTo(targetId);

            if (cmp == 0) {
                return mid;
            } else if (cmp < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return -1;
    }

    public static List<Book> sequentialSearchByCategory(List<Book> books, String category) {
        List<Book> results = new ArrayList<>();
        if (books == null || category == null) {
            return results;
        }

        for (Book book : books) {
            if (book.getCategory().equalsIgnoreCase(category)) {
                results.add(book);
            }
        }
        return results;
    }
}
