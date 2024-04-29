package data;

import books.Book;

public class User {
    static Admin objAdmin = new Admin();
    private static Object[][] bookList = new Object[100][100];
    private static int bookListIndex;

    public static Object[][] getBookList() {
        return bookList;
    }

    public void displayBooks() {
        int no = 1;
        boolean isEmpty = true;
        for (Object[] row : bookList) {
            for (Object cell : row) {
                if (cell != null) {
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }

        if (isEmpty) {
            System.out.println("Tidak terdapat data buku!");
        } else {
            System.out.println("====================================================================================================");
            System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t\t\t|| Author\t\t|| Category\t\t|| Stock\t  ||");
            System.out.println("====================================================================================================");
            for (int i = 0; i < bookList.length; i++) {
                if (bookList[i][0] != null && bookList[i][1] != null && bookList[i][2] != null && bookList[i][3] != null && bookList[i][4] != null) {
                    System.out.println("|| " + no++ + "  || " + bookList[i][0] + "\t\t|| " + bookList[i][1] + "\t\t\t|| " + bookList[i][2] + "\t\t|| " + bookList[i][3] + "\t\t|| " + bookList[i][4] + " ||\t");
                }
            }
            System.out.println("====================================================================================================");
        }
    }

    public static void addBook() {
        // input data ke array
        bookList[bookListIndex][0] = Book.getBookId();
        bookList[bookListIndex][1] = Book.getTitle();
        bookList[bookListIndex][2] = Book.getAuthor();
        bookList[bookListIndex][3] = Book.getCategory();
        bookList[bookListIndex][4] = Book.getStock();

        bookListIndex += 1;
//        System.out.println(bookListIndex);
        System.out.println("Buku berhasil ditambahkan.");

        objAdmin.menu();
    }
}
