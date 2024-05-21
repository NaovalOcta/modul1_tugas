package data;

import java.util.ArrayList;
import books.Book;

public class User {
    private static ArrayList<Book> bookList = new ArrayList<>();
    
    public void displayBooks() {
        int iterator = 1;
        if(bookList.isEmpty()) {
            System.out.println("Belum ada buku yang diinput ...");
            System.out.println("Kembali ke menu");

            Student student = new Student("", "", "", "");
            student.menu();
        }
        else {
            template("No.", "ID Buku", "Nama Buku", "Author", "Kategori", "Stock");

            for (Book book : bookList) {
                if (book != null) {
//                    System.out.println("<" + iterator + "> " + book.getbookId() + " - " + book.getTitle() + " - " + book.getAuthor() + " - " + book.getCategory() + " - " + book.getStock());
                    System.out.println("|| " + iterator + "  || " + book.getbookId() + "\t\t|| " + book.getTitle() + "\t\t|| " + book.getAuthor() + "\t\t|| " + book.getCategory() + "\t|| " + book.getStock() + "\t ||");
                }
                iterator++;
            }
        }
    }

    public static void addBooks(String bookId, String title, String author, String category, int stock) {
        bookList.add(new Book(bookId, title, author, category, stock));
    }

    public ArrayList<Book> getBookList() {
        return bookList;
    }

    // Method Overload
    public void template(String no, String bookId, String judul, String author, String category) {
        System.out.println("====================================================================================================");
        System.out.println("|| " + no + " || "+ bookId +"\t\t\t|| "+ judul +"\t\t|| "+ author +"\t\t|| "+ category +"\t|| Durasi\t||");
        System.out.println("====================================================================================================");
        System.out.println();
    }
    // Method Overload
    public void template(String no, String bookId, String judul, String author, String category, String stock) {
        System.out.println("====================================================================================================");
        System.out.println("|| " + no + " || "+ bookId +"\t\t\t|| "+ judul +"\t\t|| "+ author +"\t\t|| "+ category +"\t|| "+ stock +"\t||");
        System.out.println("====================================================================================================");
        System.out.println();
    }
}
