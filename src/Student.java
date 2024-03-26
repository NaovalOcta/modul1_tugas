public class Student extends User {
    private String name;
    private String faculty;
    public static String nim;
    private String programStudi;
    public static Object[][] borrowedBooks = new Object[100][100];

    public Student(String name, String nim, String faculty, String programStudi) {
        this.name = name;
        this.nim = nim;
        this.faculty = faculty;
        this.programStudi = programStudi;
    }

    public void displayInfo(int inputNIM) {
        for (int i = 0; i < Admin.studentListIndex; i++) {
            if (Admin.getStudentList()[i][2].equals(inputNIM)) {
                System.out.println("Name\t: "+Admin.getStudentList()[i][0]);
                System.out.println("Faculty\t: "+Admin.getStudentList()[i][1]);
                System.out.println("NIM\t\t: "+Admin.getStudentList()[i][2]);
                System.out.println("Program Studi\t: "+Admin.getStudentList()[i][3]);
            } else {
                System.out.println("Data diri mahasiswa tidak ditemukan");
            }
        }
    }

    public static void showBorrowedBooks() {
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
            System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t\t\t|| Author\t\t|| Category\t|| Durasi\t||");
            System.out.println("====================================================================================================");
            for (Object[] book : borrowedBooks) {
                if (book[0] != null) {
                    System.out.println("|| " + no++ + "  || " + book[0] + "\t\t|| " + book[1] + "\t\t\t|| " + book[2] + "\t\t|| " + book[3] + "\t|| " + book[4] + "\t ||");
                }
            }
            System.out.println("====================================================================================================");
        }
    }

    @Override
    public void displayBooks() {
        super.displayBooks();
        Main.menuStudent(nim);
    }

    public static void logout() {
        Main.Menu();
    }

    public static void returnBooks() {
        // Kode untuk menghapus semua data dari array
        for (int i = 0; i < borrowedBooks.length; i++) {
            for (int j = 0; j < borrowedBooks[i].length; j++) {
                borrowedBooks[i][j] = null;
            }
        }
    }
}
