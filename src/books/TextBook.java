package books;

import com.main.LibrarySystem;

public class TextBook extends Book {
    private final String category = "Novel";
    public TextBook(String bookId, String title, String author, String category, int stock) {
        super(bookId, title, author, category, stock);
    }
    public String getCategory() {
        return category;
    }
}
