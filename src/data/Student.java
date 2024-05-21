package data;

import java.util.ArrayList;
import java.util.Scanner;

import com.main.LibrarySystem;
import exception.custom.IllegalInvalidChoice;
import util.iMenu;
import books.*;

public class Student extends User implements iMenu {
    private String name;
    private String faculty;
    private String nim;
    private String programStudi;
    private static ArrayList<Book> borrowedBooks = new ArrayList<>();
    private Scanner objScanner = new Scanner(System.in);
    private int lock = 0;
    private boolean isLogout = false;

    public String getName() {
        return name;
    }

    public String getFaculty() {
        return faculty;
    }

    public String getNim() {
        return nim;
    }

    public String getprogramStudi() {
        return programStudi;
    }

    public static ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public Student(String name, String faculty, String nim, String programStudi) {
        this.name = name;
        this.faculty = faculty;
        this.nim = nim;
        this.programStudi = programStudi;
    }

    public void displayInfo() {
        if (LibrarySystem.checkNim()[0].equals(1) ) {
            System.out.println("Login sebagai Mahasiswa berhasil\n");
            menu();
        } else {
            System.out.println("NIM Mahasiswa tidak valid atau tidak ditemukan\n");
        }
        menu();
    }

    @Override
    public void menu() {
        try {
            int choice;
            do {
                System.out.println("====== Student Menu ======");
                System.out.println("1. Buku Terpinjam");
                System.out.println("2. Pinjam Buku");
                System.out.println("3. Kembalikan Buku");
                System.out.println("4. Logout atau pinjam buku");
                System.out.print("Choose menu (1-4): ");
                choice = objScanner.nextInt();

                switch (choice) {
                    case 1:
                        showBorrowedBooks();
                        break;
                    case 2:
                        isLogout = false;
                        choiceBook();
                        break;
                    case 3:
                        returnBook();
                        break;
                    case 4:
                        logout();
                        break;
                    default:
                        throw new IllegalInvalidChoice("Invalid parameter choice!");
                }
            } while(choice != 4);
        } catch (IllegalInvalidChoice e) {
            System.err.println(e);
        }
    }

    public void showBorrowedBooks() {
        LibrarySystem librarySystem = new LibrarySystem();
        int iterator = 1;
        for (Book book : borrowedBooks) {
            if (lock == 0) {
                Admin admin = new Admin();
                for (Student student : admin.getStudentList()) {
                    System.out.println("\nProfil Mahasiswa\n=============================\nNama : " + student.getName() + "\nNIM : " + student.getNim() + "\n=============================");
                    System.out.println("Daftar Buku yang dipinjam :");
                    template("No", "ID Buku", "Nama Buku", "Author", "Kategori");
                    break;
                }
                lock = 1;
            }

            if(librarySystem.getNimStudentListHolder().equals(book.getNimStudent())) {
                System.out.println("|| " + iterator++ + "  || " + book.getbookId() + "\t\t|| " + book.getTitle() + "\t\t|| " + book.getAuthor() + "\t\t|| " + book.getCategory() + "\t|| " + book.getDuration() + "\t ||");
//                System.out.println("<> " + book.getbookId() + " - " + book.getTitle() + " - " + book.getAuthor() + " - " + book.getCategory() + " - " + book.getDuration());
            }
        }

        System.out.println();
        lock = 0;
        menu();
    }

    public void choiceBook() {
        super.displayBooks();

        if(isLogout == true) {
            System.out.print("Apakah anda yakin ingin meminjam buku tersebut (y/n) ? ");
            String choice = objScanner.next();
            if (choice.equals("n")) {
                logout();
                return;
            }
            else {
                System.out.println("Masukkan pilihan yang tepat");
                menu();

            }
        }

        System.out.print("Masukkan ID Buku yang ingin dipinjam : ");
        String bookId = objScanner.next();

        int duration;
        do {
            System.out.print("Berapa lama buku ingin dipinjam?\nInput lama (maksimal 14 hari) : ");
            duration = objScanner.nextInt();
            if (duration <= 0) {
                System.out.println("\ndurasi buku harus lebih dari 0 !\n");
            }
            else if (duration > 14) {
                System.out.println("\nBuku tidak boleh dipinjam lebih dari 14 hari !\n");
            }
        } while(duration <= 0 || duration > 14);
        objScanner.nextLine();

        User user = new User();
        for (int i = 0; i < (user.getBookList().size()); i++) {
            Book book = getBookList().get(i);
            LibrarySystem librarySystem = new LibrarySystem();

            if (book.getbookId().equals(bookId)) {
                book.setDuration(duration);
                borrowedBooks.add(new Book(book.setNimStudent(librarySystem.getNimStudentListHolder()), book.getbookId(), book.getTitle(), book.getAuthor(), book.getCategory(), book.getStock(), book.getDuration()));
                break;
            }
        }

        Book selectedBook = idBookFinder(bookId); // get all the selected book information
        if (selectedBook != null && selectedBook.getStock() > 0) {
            selectedBook.setStock(selectedBook.getStock() - 1);
            System.out.println("Berhasil meminjam buku: " + selectedBook.getTitle());
        } else {
            System.out.println("Buku tidak tersedia atau ID buku tidak ditemukan ...");
        }

        System.out.println();
        menu();
    }

    private void returnBook() { // NEED FIXING
        if (getBorrowedBooks().isEmpty()) {
            System.out.println("Anda belum meminjam buku ...");
            return;
        }

        int i = 0;
        int iterator = 1;
        System.out.println("Buku yang Anda pinjam:");
        for (Book book : getBorrowedBooks()) {
            LibrarySystem librarySystem = new LibrarySystem();

            if(i == getBorrowedBooks().size()) {
                break;
            }
            else if (book.getNimStudent() != null) {
                if(librarySystem.getNimStudentListHolder().equals(book.getNimStudent())) {
                    System.out.println("<" + iterator + "> " + getBorrowedBooks().get(i).getbookId() + " - " + getBorrowedBooks().get(i).getTitle());
                }
            }
            iterator++;
            i++;
        }

        System.out.print("Pilih buku yang akan dikembalikan (nomor): ");
        int choice = objScanner.nextInt();
        objScanner.nextLine();

        LibrarySystem lS = new LibrarySystem();
        for(Book book : getBorrowedBooks()) {
            if (choice > 0 && choice <= getBorrowedBooks().size() && lS.getNimStudentListHolder().equals(book.getNimStudent())) {
                Book returnedBook = getBorrowedBooks().remove(choice - 1);
                returnedBook.setStock(returnedBook.getStock() + 1);

                System.out.println("Buku " + returnedBook.getbookId() + " berhasil dikembalikan ...");
                break;
            }
        }

        System.out.println();
        menu();
    }

    public Book idBookFinder(String id) {
        User user = new User();
        for (Book book : user.getBookList()) {
            if (book != null && book.getbookId().equals(id)) {
                return book;
            }
        }
        return null;
    }

    public void logout() {
        int logoutChoice;
        do {
            System.out.print("Apakah anda ingin [langsung logout] atau [logout dan pinjam buku] (1 / 2) ? ");
            logoutChoice = objScanner.nextInt();
            objScanner.nextLine();

            switch (logoutChoice) {
                case 1:
                    if (getBorrowedBooks().isEmpty()) {
                        System.out.println("Logout berhasil dan semua buku yang dipinjam akan dikembalikan secara otomatis. \n");
                        LibrarySystem.menu();
                    } else {
                        getBorrowedBooks().clear();
                    }

                    break;
                case 2:
                    isLogout = true;
                    int i = 0;
                    int iterator = 1;
                    System.out.println("Buku yang Anda pinjam:");
                    for (Book book : getBorrowedBooks()) {
                        LibrarySystem librarySystem = new LibrarySystem();

                        if(i == getBorrowedBooks().size()) {
                            break;
                        }
                        else if (book.getNimStudent() != null) {
                            if(librarySystem.getNimStudentListHolder().equals(book.getNimStudent())) {
                                System.out.println("===================================================================");
                                System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t||");
                                System.out.println("===================================================================");
                                System.out.println("|| " + iterator + "  || " + getBorrowedBooks().get(i).getbookId() + "\t\t|| " + getBorrowedBooks().get(i).getTitle() + "\t||");
                                System.out.println("<" + iterator + "> " + getBorrowedBooks().get(i).getbookId() + " - " + getBorrowedBooks().get(i).getTitle());
                            }
                        }
                        iterator++;
                        i++;
                    }

                    System.out.print("Apakah anda yakin ingin meminjam buku tersebut (y/n) ? ");
                    String choice = objScanner.next();
                    if (choice.equals("n")) {
                        getBorrowedBooks().clear();
                        LibrarySystem.menu();
                        System.out.println("Logout berhasil dan semua buku yang dipinjam akan dikembalikan secara otomatis");
                        return;
                    } else if (choice.equals("y")) {
                        System.out.println("Buku berhasil dipinjam dan anda berhasil logout.");
                        LibrarySystem.menu();
                    }
                    else {
                        System.out.println("Masukkan pilihan yang tepat");
                        menu();
                    }
                    break;
                default:
                    System.out.println("Pilihlah pilihan yg tepat !");
                    break;
            }
        } while(logoutChoice > 2 || logoutChoice < 1);
    }
}
