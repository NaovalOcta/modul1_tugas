import java.util.Scanner;

public class Main {
    static String[][] bookList = {
            {"A123-B456-C789", "Buku Satu", "Author 1", "Kategori Pertama", "21"},
            {"D234-E567-F890", "Buku Dua", "Author 2", "Kategori Pertama", "30"},
            {"G345-H678-I012", "Buku Tiga", "Author 3", "Kategori Kedua", "29"},
            {"J456-K789-L345", "Buku Empat", "Author 4", "Kategori Kedua", "40"},
            {"M567-N890-O123", "Buku Lima", "Author 5", "Kategori Ketiga", "47"},
    };
    static String[][] userStudent = new String[100][100];
    static String[][] borrowBooks = new String[100][100];


    static void Menu() {
        Scanner objScanner = new Scanner(System.in);
        Admin objAdmin = new Admin();

        System.out.println("====== Library System ======");
        System.out.println("1. Login as Student");
        System.out.println("2. Login as Admin");
        System.out.println("3. Exit");
        System.out.print("Choose menu (1-3): ");
        int choice = objScanner.nextInt();

        switch (choice) {
            case 1:
                inputNim();
                break;
            case 2:
                System.out.print("Masukkan username: ");
                String inputUsername = objScanner.next();
                System.out.print("Masukkan password: ");
                String inputPassword = objScanner.next();

                // cek apakah username dan password sudah sesuai
                if (inputUsername.equals(objAdmin.adminUsername) && inputPassword.equals(objAdmin.adminPassword)) {
                    menuAdmin();
                } else {
                    System.out.println("Username atau password yang anda masukkan salah!");
                    Menu();
                }

                break;
            case 3:
                System.out.println("Keluar dari program...");
                System.exit(0);
                break;
        }
    }

    static void inputNim() {
        Scanner objScanner = new Scanner(System.in);

        String NIM = "";
        do {
            System.out.print("Enter your NIM (input 99 untuk kembali): ");
            NIM = objScanner.next();
            if (NIM.equals("99")) {
                Menu();
            } else {
                if ((Boolean) checkNim(NIM)) {
                    menuStudent(NIM);
                }
            }
        } while (!NIM.equals("99"));
    }

    static Object checkNim(String nimInput) {
        boolean isNIMExist = false;

        if (nimInput.length() == 15) {
            for (int i = 0; i < Admin.userStudentIndex; i++) {
                if (userStudent[i][2].equals(nimInput)) {
                    isNIMExist = true;
                    break;
                } else {
                    isNIMExist = false;
                }
            }
            if (isNIMExist) {
                System.out.println("NIM valid dan terdapat dalam array.");
                return Boolean.TRUE;
            } else {
                System.out.println("NIM valid tetapi tidak terdapat dalam array.");
                return Boolean.FALSE;
            }
        } else {
            System.out.println("NIM tidak valid. Harus berjumlah 15 digit.");
            return Boolean.FALSE;
        }
    }

    static void menuAdmin() {
        Scanner objScanner = new Scanner(System.in);

        System.out.println("====== Admin Menu ======");
        System.out.println("1. Add Student");
        System.out.println("2. Display Registered Student");
        System.out.println("3. Logout");
        System.out.print("Choose menu (1-3): ");
        int choice = objScanner.nextInt();

        switch (choice) {
            case 1:
                Admin.addStudent();
                break;
            case 2:
                Admin.displayStudents();
                break;
            case 3:
                Student.logout();
                break;
        }
    }

    static void menuStudent(String NIM) {
        Scanner objScanner = new Scanner(System.in);
        int borrowBookIndex = 0;
        boolean isIDBukuExist = false;

        System.out.println("====== Student Menu ======");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Logout");
        System.out.print("Choose menu (1-3): ");
        int choice = objScanner.nextInt();

        switch (choice) {
            case 1:
                for (String[] idBuku : borrowBooks) {
                    for (String[] books : bookList) {
                        if (books[0].equals(idBuku[1])) {
                            System.out.println("ID Buku: " + books[0] + ", Judul: " + books[1] + ", Author: " + books[2] + ", Kategori: " + books[3]);
                        }
                    }
                }
                menuStudent(NIM);
                break;
            case 2:
                Student.displayBooks();
                do {
                    System.out.println("Masukkan ID Buku yang akan dipinjam (input 99 untuk kembali): ");
                    System.out.print("4 digit / 4 karakter pertama ID Buku: ");
                    String inputIDBuku1 = objScanner.next();
                    System.out.print("4 digit / 4 karakter kedua ID Buku: ");
                    String inputIDBuku2 = objScanner.next();
                    System.out.print("4 digit / 4 karakter ketiga ID Buku: ");
                    String inputIDBuku3 = objScanner.next();

                    // menggabungkan semua inputan
                    String inputIDBuku = inputIDBuku1 + "-" + inputIDBuku2 + "-" + inputIDBuku3;

                    if (inputIDBuku.equals("99")) {
                        menuAdmin();
                    } else {
                        for (String[] book : bookList) {
                            if (book[0].equals(inputIDBuku)) {
                                isIDBukuExist = true;
                                break;
                            } else {
                                isIDBukuExist = false;
                            }
                        }
                        if (isIDBukuExist) {
                            borrowBooks[borrowBookIndex][0] = NIM;
                            borrowBooks[borrowBookIndex][1] = inputIDBuku;
                            for (String[] book : bookList) {
                                if (book[0].equals(inputIDBuku)) {
                                    int stock = Integer.parseInt(book[4]);
                                    stock--;
                                    book[4] = Integer.toString(stock);
                                }
                            }
                            System.out.println("Buku berhasil dipinjam");
                            menuStudent(NIM);
                        } else {
                            System.out.println("ID Buku yang anda masukkan salah, silahkan coba kembali!");
                            System.out.println("");
                        }
                    }
                } while (!isIDBukuExist);
                break;
            case 3:
                Student.logout();
                break;
        }
    }

    public static void main(String[] args) {
        Menu();
    }
}