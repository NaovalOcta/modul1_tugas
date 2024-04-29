package data;

import books.Book;
import com.main.LibrarySystem;
import util.iMenu;

import java.util.Scanner;

public class Student extends User implements iMenu {
    private static String name;
    private static String faculty;
    private static String nim;
    private static String programStudi;
    public static Object[][] borrowedBooks = new Object[100][100];

    private static int tempBorrowedBookIndex = 0;

    public Student(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public static void setName(String name) {
        Student.name = name;
    }

    public static void setFaculty(String faculty) {
        Student.faculty = faculty;
    }

    public static void setNim(String nim) {
        Student.nim = nim;
    }

    public static void setProgramStudi(String programStudi) {
        Student.programStudi = programStudi;
    }

    public static String getName() {
        return name;
    }

    public static String getFaculty() {
        return faculty;
    }

    public static String getNim() {
        return nim;
    }

    public static String getProgramStudi() {
        return programStudi;
    }

    public void displayInfo() {
        System.out.println("------ Data Diri Pengguna ------");
        System.out.println("Nama\t\t\t: " + getName());
        System.out.println("NIM\t\t\t\t: " + getNim());
        System.out.println("Fakultas\t\t: " + getFaculty());
        System.out.println("Program Studi\t: " + getProgramStudi());
    }

    @Override
    public void menu() {
        Scanner objScanner = new Scanner(System.in);

        System.out.println("====== Student Menu ======");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Pinjam Buku atau Logout");
        System.out.println("5. Tampilkan Data Diri");
        System.out.print("Choose menu (1-5): ");
        int choice = objScanner.nextInt();

        switch (choice) {
            case 1:
                Student.showBorrowedBooks(getNim());
                this.menu();
                break;
            case 2:
                choiceBook();
                break;
            case 3:
                returnBooks();
                this.menu();
                break;
            case 4:
                Student.showBorrowedBooks(getNim());
                System.out.println("Apakah kamu yakin untuk meminjam semua buku tersebut?");
                System.out.print("Input Y (iya) atau T (tidak): ");
                String noYesAnswer = objScanner.next();

                if (noYesAnswer.equals("Y")) {
                    Student.logout();
                } else if (noYesAnswer.equals("T")) {
                    Student.returnBooks();
                } else {
                    System.out.println("Inputan tidak sesuai");
                }
                break;
            case 5:
                displayInfo();
                this.menu();
                break;
        }
    }

    public static void showBorrowedBooks(String nim) {
        int no = 1;
        boolean isEmpty = true;

        for (Object[] row : borrowedBooks) {
            for (Object book : row) {
                if (book != null) {
                    isEmpty = false;
                    break;
                }
            }
            if (!isEmpty) {
                break;
            }
        }
        if (isEmpty) {
            System.out.println("Silahkan pilih buku terlebih dahulu");
        } else {
            System.out.println("====================================================================================================");
            System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t\t|| Author\t\t|| Category\t|| Durasi\t||");
            System.out.println("====================================================================================================");
            for (Object[] borrowBook : borrowedBooks) {
                if (borrowBook != null && borrowBook[0] != null && borrowBook[0].equals(nim)) {
                    for (Object[] book : User.getBookList()) {
                        if (book != null && book[0] != null && book[0].equals(borrowBook[1])) {
                            System.out.println("|| " + no++ + "  || " + book[0] + "\t\t|| " + book[1] + "\t\t|| " + book[2] + "\t\t|| " + book[3] + "\t|| " + book[4] + "\t ||");
                        }
                    }
                }
            }
        }
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        this.menu();
    }

    public void choiceBook() {
        super.displayBooks();

        Admin objAdmin = new Admin();
        Scanner objScanner = new Scanner(System.in);
        Book objBook = new Book("", "", "", 0);
        String inputIDBuku;
        do {
            System.out.println("Masukkan ID Buku yang akan dipinjam (input 99 untuk kembali): ");
            System.out.print("4 digit / 4 karakter pertama ID Buku: ");
            String inputIDBuku1 = objScanner.next();
            System.out.print("4 digit / 4 karakter kedua ID Buku: ");
            String inputIDBuku2 = objScanner.next();
            System.out.print("4 digit / 4 karakter ketiga ID Buku: ");
            String inputIDBuku3 = objScanner.next();

            // menggabungkan semua inputan
            inputIDBuku = inputIDBuku1 + "-" + inputIDBuku2 + "-" + inputIDBuku3;
            System.out.println(inputIDBuku);

            if (inputIDBuku.equals("99")) {
                objAdmin.menu();
            } else {
                boolean foundBook = false;
                for (Object[] book : User.getBookList()) {
                    if (book[0].equals(inputIDBuku)) {
                        foundBook = true;
                        if (book[4].equals(0)) {
                            System.out.println("Stock buku kosong!");
                            this.menu();
                        } else {
                            System.out.println("Berapa lama buku akan dipinjam? (max 14 hari)");
                            System.out.print("Input lama (hari): ");
                            objBook.setDuration(objScanner.nextInt());
                            if (Book.getDuration() <= 14) {
                                LibrarySystem.addTempBooks(tempBorrowedBookIndex, book[0], getNim(), Book.getDuration());
                                tempBorrowedBookIndex += 1;
                                System.out.println(tempBorrowedBookIndex);
                                this.menu();
                            } else {
                                System.out.println("Lama buku dipinjam melebihi batas waktu, maksimal 14 hari");
                                this.menu();
                            }
                        }
                    } else {
                        foundBook = false;
                    }
                }
                if (!foundBook) {
                    System.out.println("Buku tidak ditemukan");
                    this.menu();
                }
            }
        } while (inputIDBuku.equals("99"));

        this.menu();
    }

    public static void logout() {
        LibrarySystem objLibrarySystem = new LibrarySystem();

        objLibrarySystem.menu();
    }

    public static void returnBooks() {
        Scanner objScanner = new Scanner(System.in);

        // Jika hanya ada satu buku, hapus semua
        if (Student.borrowedBooks.length == 1) {
            for (int i = 0; i < Student.borrowedBooks.length; i++) {
                for (int j = 0; j < Student.borrowedBooks[i].length; j++) {
                    Student.borrowedBooks[i][j] = null;
                }
            }
            System.out.println("Semua buku berhasil dikembalikan");
        } else {
            // Jika ada lebih dari satu buku, tampilkan buku dan minta pengguna memilih buku mana yang akan dihapus
            showBorrowedBooks(getNim());

            System.out.println("Masukkan ID buku yang ingin Anda kembalikan: ");
            System.out.print("4 digit / 4 karakter pertama ID Buku: ");
            String inputIDBuku1 = objScanner.next();
            System.out.print("4 digit / 4 karakter kedua ID Buku: ");
            String inputIDBuku2 = objScanner.next();
            System.out.print("4 digit / 4 karakter ketiga ID Buku: ");
            String inputIDBuku3 = objScanner.next();

            // menggabungkan semua inputan
            String inputIDBuku = inputIDBuku1 + "-" + inputIDBuku2 + "-" + inputIDBuku3;
            System.out.println(inputIDBuku);

            boolean foundBook = false;
            for (Object[] borrowBook : borrowedBooks) {
                if (borrowBook[1] != null && borrowBook[1].equals(inputIDBuku)) {
                    foundBook = true;
                    borrowBook[0] = null;
                    borrowBook[1] = null;
                    borrowBook[2] = null;
                    System.out.println("Buku berhasil dikembalikan");
                }
            }

            if (!foundBook) {
                System.out.println("Buku dengan ID " + inputIDBuku + " tidak ditemukan.");
            }
        }
    }
}
