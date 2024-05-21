package books;

public class StoryBook extends Book {
    private final String category = "Cerita";
    public StoryBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}
