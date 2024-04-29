package books;

public class Book {
    private static String bookId;
    private static String title;
    private static String author;
    private static String category;
    private static int stock;
    private static int duration;

    public Book(String bookId, String title, String author, int stock) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
        this.stock = stock;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setStock(int stock) {
        this.stock = stock;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public static String getBookId() {
        return bookId;
    }
    public static String getTitle() {
        return title;
    }
    public static String getAuthor() {
        return author;
    }
    public static String getCategory() {
        return category;
    }
    public static int getStock() {
        return stock;
    }
    public static int getDuration() {
        return duration;
    }
}
