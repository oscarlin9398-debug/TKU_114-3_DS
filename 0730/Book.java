public class Book {
    private String bookId;
    private String title;
    private String category;
    private int borrowCount;

    public Book(String bookId, String title, String category, int borrowCount) {
        this.bookId = bookId;
        this.title = title;
        this.category = category;
        this.borrowCount = borrowCount;
    }

    public String getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getCategory() {
        return category;
    }

    public int getBorrowCount() {
        return borrowCount;
    }

    @Override
    public String toString() {
        return String.format("書籍編號: %-6s | 書名: %-16s | 分類: %-8s | 借閱次數: %d 次",
                bookId, title, category, borrowCount);
    }
}
