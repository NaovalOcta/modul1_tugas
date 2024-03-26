public class User {
    private static Object[][] bookList = {
            {"A123-B456-C789", "Buku Satu", "Author 1", "Kategori Pertama", "21"},
            {"D234-E567-F890", "Buku Dua", "Author 2", "Kategori Pertama", "30"},
            {"G345-H678-I012", "Buku Tiga", "Author 3", "Kategori Kedua", "29"},
            {"J456-K789-L345", "Buku Empat", "Author 4", "Kategori Kedua", "40"},
            {"M567-N890-O123", "Buku Lima", "Author 5", "Kategori Ketiga", "47"},
    };
    private static int bookListIndex;

    public static Object[][] getBookList() {
        return bookList;
    }

    public void displayBooks() {
        int no = 1;
        System.out.println("====================================================================================================");
        System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t\t\t|| Author\t\t|| Category\t\t|| Stock\t  ||");
        System.out.println("====================================================================================================");
        for (int i = 0; i < bookList.length; i++) {
            System.out.println("|| " + no++ + "  || " + bookList[i][0] + "\t\t|| " + bookList[i][1] + "\t\t\t|| " + bookList[i][2] + "\t\t|| " + bookList[i][3] + "\t\t|| " + bookList[i][4] + " ||\t");
        }
        System.out.println("====================================================================================================");
    }

    public static void addBook() {
        // input data ke array
        bookList[bookListIndex][0] = Book.getBookId();
        bookList[bookListIndex][1] = Book.getTitle();
        bookList[bookListIndex][2] = Book.getAuthor();
        bookList[bookListIndex][3] = Book.getCategory();
        bookList[bookListIndex][4] = Book.getStock();

        bookListIndex += 1;
        System.out.println(bookListIndex);
        System.out.println("Buku berhasil ditambahkan.");

        Main.menuAdmin();
    }
}
