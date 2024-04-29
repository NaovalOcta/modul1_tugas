package books;

public class StoryBook extends Book {
    private String category = "";

    public StoryBook(String bookId, String title, String author, int stock) {
        super(bookId, title, author, stock);
        setCategory(this.category);
    }
}
