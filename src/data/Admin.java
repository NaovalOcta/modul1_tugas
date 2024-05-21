package data;

import exception.custom.IllegalAdminAccess;
import exception.custom.IllegalInvalidChoice;
import util.iMenu;
import com.main.LibrarySystem;
import books.*;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private final String adminUsername = "Admin";
    private final String adminPassword = "Admin";
    private static ArrayList<Student> studentList = new ArrayList<>();
    private Scanner objScanner = new Scanner(System.in);

    @Override
    public void menu() {
        try {
//            System.out.print("===== Menu Admin =====\n1. Tambah Mahasiswa\n2. Tampilkan Mahasiswa\n3. Input Buku\n4. Tampilkan Daftar Buku\n5. Logout\nPilih antara (1-5): ");
            System.out.println("====== Admin Menu ======");
            System.out.println("1. Add Student");
            System.out.println("2. Add Book");
            System.out.println("3. Display Registered Student");
            System.out.println("4. Display Available Books");
            System.out.println("5. Logout");
            System.out.print("Choose menu (1-5): ");
            int choice = objScanner.nextInt();

            switch (choice) {
                case 1:
                    addStudent();
                    break;
                case 2:
                    inputBook();
                    break;
                case 3:
                    LibrarySystem.addTempStudent();
                    displayStudents();
                    break;
                case 4:
                    LibrarySystem.addTempBooks();
                    displayBooks();
                    break;
                case 5:
                    System.out.println("Logout berhasil.\n");
                    break;
                default:
                    throw new IllegalInvalidChoice("Invalid parameter choice!");
            }
        } catch (IllegalInvalidChoice e) {
            System.err.println(e);
        }
    }
    public boolean isAdmin(String username, String password) throws IllegalAdminAccess {
        if (username.equals(adminUsername) && password.equals(adminPassword)) {
            return true;
        } else {
            throw new IllegalAdminAccess("Invalid parameter!");
        }
    }
    public void addStudent() {
        System.out.print("Masukkan Nama: ");
        String name = objScanner.next();
        System.out.print("Masukkan NIM: ");
        String nim = objScanner.next();
        if (nim.length() != 15) {
            System.out.println("NIM tidak valid! Harus 15 karakter.\n");
            menu();
        }

        LibrarySystem librarySystem = new LibrarySystem();
        for(int i = 0; i < librarySystem.getNimStudentList().size(); i++ ) {
            if(nim.equals(librarySystem.getNimStudentList().get(i))) {
                System.out.println("Nim anda sudah terdaftar !");
                menu();
            }
        }

        librarySystem.setNimStudentList(nim);

        System.out.print("Masukkan Fakultas: ");
        String faculty = objScanner.next();
        System.out.print("Masukkan Program Studi: ");
        String programStudi = objScanner.next();

        studentList.add(new Student(name, faculty, nim, programStudi));

        System.out.println("Mahasiswa dengan NIM " + nim + " berhasil ditambahkan ...\n");
        menu();
    }

    public void displayStudents() {
        for (Student student : studentList) {
//            System.out.println("===================================");
            System.out.println("Nama            : " + student.getName());
            System.out.println("NIM             : " + student.getNim());
            System.out.println("Fakultas        : " + student.getFaculty());
            System.out.println("Program Studi   : " + student.getprogramStudi());
            System.out.println("===================================");
        }

        System.out.println();
        menu();
    }

    public void inputBook() {
        int bookType;
        do {
            System.out.println("\nPilih jenis buku:");
            System.out.println("1. History Book");
            System.out.println("2. Story Book");
            System.out.println("3. Text Book");
            System.out.print("Pilih jenis buku (1-3): ");
            bookType = objScanner.nextInt();
            objScanner.nextLine();
        } while(bookType > 3 || bookType < 1);

        String bookId;
        System.out.print("Masukkan title buku: ");
        String title = objScanner.nextLine();
        System.out.print("Masukkan author buku: ");
        String author = objScanner.nextLine();
        System.out.print("Masukkan stock buku: ");
        int stock = objScanner.nextInt();
        objScanner.nextLine();

        String category;
        switch (bookType) {
            case 1:
                HistoryBook historyBook = new HistoryBook("", "", "", "", 0);
                category = historyBook.getCategory();
                break;
            case 2:
                StoryBook storyBook = new StoryBook("", "", "", "", 0);
                category = storyBook.getCategory();
                break;
            case 3:
                TextBook textBook = new TextBook("", "", "", "", 0);
                category = textBook.getCategory();
                break;
            default:
                System.out.println("Pilihan tidak valid!");
                return;
        }
        bookId = generateId();
        User.addBooks(bookId, title, author, category, stock);

        System.out.println("Buku berhasil ditambahkan.\n");
        menu();
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        menu();
    }

    public String generateId() {
        StringBuilder idBuilder = new StringBuilder(); // object to construct String more efficiently and customizable
        Random random = new Random();

        for (int i = 0; i < 12; i++) {
            idBuilder.append(random.nextInt(10));
            if ((i + 1) % 4 == 0 && i != 11) {
                idBuilder.append("-");
            }
        }

        return idBuilder.toString();
    }

    public ArrayList<Student> getStudentList() {
        return studentList;
    }
}
