import java.util.Scanner;

public class Main {
    public static void Menu() {
        Scanner objScanner = new Scanner(System.in);

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

                if (Admin.isAdmin(inputUsername, inputPassword)) {
                    menuAdmin();
                } else {
                    System.out.println("Username or password invalid");
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
            for (int i = 0; i < Admin.studentListIndex; i++) {
                if (Admin.getStudentList()[i][2].equals(nimInput)) {
                    isNIMExist = true;
                    new Student(Admin.getStudentList()[i][0], Admin.getStudentList()[i][2], Admin.getStudentList()[i][1], Admin.getStudentList()[i][3]);
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

    public static void menuStudent(String NIM) {
        Scanner objScanner = new Scanner(System.in);
        int tempBorrowedBookIndex = 0;

        System.out.println("====== Student Menu ======");
        System.out.println("1. Buku Terpinjam");
        System.out.println("2. Pinjam Buku");
        System.out.println("3. Kembalikan Buku");
        System.out.println("4. Pinjam Buku atau Logout");
        System.out.print("Choose menu (1-4): ");
        int choice = objScanner.nextInt();

        switch (choice) {
            case 1:
                Student.showBorrowedBooks();
                Main.menuStudent(Student.nim);
                break;
            case 2:
                User objUser = new User();
                Book objBook = new Book("", "", "", 0);

                objUser.displayBooks();
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

                    if (inputIDBuku.equals("99")) {
                        menuAdmin();
                    } else {
                        for (Object[] book : User.getBookList()) {
                            if (book[0].equals(inputIDBuku)) {
                                if (book[4].equals(0)) {
                                    System.out.println("Stock buku kosong!");
                                    menuStudent(Student.nim);
                                } else {
                                    System.out.println("Berapa lama buku akan dipinjam? (max 14 hari)");
                                    System.out.print("Input lama (hari): ");
                                    objBook.setDuration(objScanner.nextInt());
                                    if (Book.getDuration() <= 14) {
                                        addTempBooks(tempBorrowedBookIndex, book[0].toString(), book[1].toString(), book[2].toString(), book[3].toString(), Integer.toString(Book.getDuration()));
                                        tempBorrowedBookIndex += 1;
                                        menuStudent(Student.nim);
                                    } else {
                                        System.out.println("Lama buku dipinjam melebihi batas waktu, maksimal 14 hari");
                                    }
                                }
                            } else {
                                System.out.println("Buku tidak ditemukan");
                                menuStudent(Student.nim);
                            }
                        }
                    }
                } while (inputIDBuku.equals("99"));
                break;
            case 3:
                // Kode untuk menghapus semua data dari array
                for (int i = 0; i < Student.borrowedBooks.length; i++) {
                    for (int j = 0; j < Student.borrowedBooks[i].length; j++) {
                        Student.borrowedBooks[i][j] = null;
                    }
                }
                System.out.println("Semua buku berhasil dikembalikan");
                menuStudent(Student.nim);
                break;
            case 4:
                Student.showBorrowedBooks();
                System.out.println("Apakah kamu yakin untuk meminjam semua buku tersebut?");
                System.out.print("Input Y (iya) atau T (tidak): ");
                String noYesAnswer = objScanner.next();

                if (noYesAnswer.equals("Y")) {
                    Student.logout();
                } else if (noYesAnswer.equals("N")) {
                    Student.returnBooks();
                } else {
                    System.out.println("Inputan tidak sesuai");
                }
                break;
        }
    }

    public static void menuAdmin() {
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
                Admin.addStudent();
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
                Admin.displayStudents();
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

    public static void addTempStudent() {

    }

    public static void addTempBooks(int tempBorrowedBookIndex, String idBuku, String title, String author, String category, String duration) {
        Student.borrowedBooks[tempBorrowedBookIndex][0] = idBuku;
        Student.borrowedBooks[tempBorrowedBookIndex][1] = title;
        Student.borrowedBooks[tempBorrowedBookIndex][2] = author;
        Student.borrowedBooks[tempBorrowedBookIndex][3] = category;
        Student.borrowedBooks[tempBorrowedBookIndex][4] = duration;
    }

    public static void main(String[] args) {
        Menu();
    }
}