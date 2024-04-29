package data;

import books.HistoryBook;
import books.StoryBook;
import books.TextBook;
import util.iMenu;

import java.util.Random;
import java.util.Scanner;

public class Admin extends User implements iMenu {
    private static String adminUsername = "Admin";
    private static String adminPassword = "Admin";
    private static String[][] studentList = new String[100][100];
    public static int studentListIndex;

    public static String[][] getStudentList() {
        return studentList;
    }

    @Override
    public void menu() {
        Scanner objScanner = new Scanner(System.in);

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
                this.addStudent();
                break;
            case 2:
                System.out.println("1. Story Book");
                System.out.println("2. History Book");
                System.out.println("3. Text Book");
                System.out.print("Choose category (1-3): ");
                int chooseCategory = objScanner.nextInt();

                switch (chooseCategory) {
                    case 1:
                        Admin.inputBook("Story Book");
                        break;
                    case 2:
                        Admin.inputBook("History Book");
                        break;
                    case 3:
                        Admin.inputBook("Text Book");
                        break;
                    default:
                        System.out.println("Angka yang anda masukkan salah!");
                        break;
                }
                break;
            case 3:
                this.displayStudents();
                break;
            case 4:
                Admin objAdmin = new Admin();

                objAdmin.displayBooks();
                break;
            case 5:
                Student.logout();
                break;
        }
    }
    public void addStudent() {
        Scanner objScanner = new Scanner(System.in);

        boolean isNIMExist = false;
        do {
            // input data dari user
            System.out.print("Name: ");
            String name = objScanner.nextLine();

            System.out.print("Faculty: ");
            String faculty = objScanner.nextLine();

            System.out.print("NIM: ");
            String inputNIM = objScanner.nextLine();

            System.out.print("Program: ");
            String programStudi = objScanner.nextLine();

            // cek nim apakah sudah 15 digit & tidak terdapat di dalam array
            if (inputNIM.length() == 15) {
                for (int i = 0; i < studentListIndex; i++) {
                    if (studentList[i][2].equals(inputNIM)) {
                        isNIMExist = true;
                    } else {
                        isNIMExist = false;
                    }
                }
                if (!isNIMExist) {
                    System.out.println("NIM valid dan berhasil ditambahkan.");

                    // input data ke array
                    studentList[studentListIndex][0] = name;
                    studentList[studentListIndex][1] = faculty;
                    studentList[studentListIndex][2] = inputNIM;
                    studentList[studentListIndex][3] = programStudi;

                    studentListIndex += 1;
                    this.menu();
                } else {
                    System.out.println("NIM valid tetapi NIM sudah terdapat di dalam data");
                }
            } else {
                System.out.println("NIM tidak valid. Harus berjumlah 15 digit.");
            }
        } while (isNIMExist);
    }
    public static void inputBook(String category) {
        Scanner objScanner = new Scanner(System.in);

        if (category.equals("Story Book")) {
            StoryBook objStoryBook = new StoryBook("", "", "", 0);

            // input data dari user
            System.out.print("Nama Buku: ");
            objStoryBook.setTitle(objScanner.nextLine());

            System.out.print("Author: ");
            objStoryBook.setAuthor(objScanner.nextLine());

            System.out.print("Stock: ");
            objStoryBook.setStock(objScanner.nextInt());

            objStoryBook.setBookId(generateId());
            objStoryBook.setCategory(category);

            User.addBook();
        } else if (category.equals("History Book")) {
            HistoryBook objHistoryBook = new HistoryBook("", "", "",0);

            // input data dari user
            System.out.print("Nama Buku: ");
            objHistoryBook.setTitle(objScanner.nextLine());

            System.out.print("Author: ");
            objHistoryBook.setAuthor(objScanner.nextLine());

            System.out.print("Stock: ");
            objHistoryBook.setStock(objScanner.nextInt());

            objHistoryBook.setBookId(generateId());
            objHistoryBook.setCategory(category);

            User.addBook();
        } else if (category.equals("Text Book")) {
            TextBook objTextBook = new TextBook("", "", "",0);

            // input data dari user
            System.out.print("Nama Buku: ");
            objTextBook.setTitle(objScanner.nextLine());

            System.out.print("Author: ");
            objTextBook.setAuthor(objScanner.nextLine());

            System.out.print("Stock: ");
            objTextBook.setStock(objScanner.nextInt());

            objTextBook.setBookId(generateId());
            objTextBook.setCategory(category);

            User.addBook();
        } else {
            System.out.println("Category tidak tersedia");
        }
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        this.menu();
    }
    public void displayStudents() {
        if (studentList == null || studentList.length == 0) {
            System.out.println("Tidak terdapat data student!");
        } else {
            System.out.println("List of Registered Student");
            for (int i = 0; i < studentListIndex; i++) {
                System.out.println("Nama: " + studentList[i][0]);
                System.out.println("Fakultas: " + studentList[i][1]);
                System.out.println("NIM: " + studentList[i][2]);
                System.out.println("Program Studi: " + studentList[i][3]);
                System.out.println("");
            }
            this.menu();
        }
    }
    public static boolean isAdmin(String inputUsername, String inputPassword) {
        // cek apakah username dan password sudah sesuai
        if (inputUsername.equals(adminUsername) && inputPassword.equals(adminPassword)) {
            return true;
        } else {
            System.out.println("Username atau password yang anda masukkan salah!");
            return false;
        }
    }
    public static String generateId() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder id = new StringBuilder();

        for (int i = 0; i < 12; i++) {
            int index = random.nextInt(characters.length());
            id.append(characters.charAt(index));
        }

        // Menambahkan strip setiap 4 karakter
        for (int i = 4; i < id.length(); i += 5) {
            id.insert(i, "-");
        }

        return id.toString();
    }
}
