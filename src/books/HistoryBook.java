package books;

public class HistoryBook extends Book {
    private final String category = "Sejarah";
    public HistoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}