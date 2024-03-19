import java.util.Scanner;

public class Admin {
    String adminUsername = "Admin";
    String adminPassword = "Admin";
    static String studentList = "Berikut ini adalah list student: ";
    static int userStudentIndex;

    static void displayStudents() {
        String[][] studentArray = Main.userStudent;

        if (studentArray == null || studentArray.length == 0) {
            System.out.println("Tidak terdapat data student!");
        } else {
            System.out.println(studentList);
            for (int i = 0; i < userStudentIndex; i++) {
                System.out.println("Nama: " + studentArray[i][0]);
                System.out.println("Fakultas: " + studentArray[i][1]);
                System.out.println("NIM: " + studentArray[i][2]);
                System.out.println("Program Studi: " + studentArray[i][3]);
                System.out.println("");
            }
            Main.menuAdmin();
        }
    }

    static void addStudent() {
        Student objStudent = new Student();
        Scanner objScanner = new Scanner(System.in);
        String[][] studentArray = Main.userStudent;

        boolean isNIMExist = false;
        do {
            // input data dari user
            System.out.print("Name: ");
            objStudent.name = objScanner.nextLine();

            System.out.print("Faculty: ");
            objStudent.faculty = objScanner.nextLine();

            System.out.print("NIM: ");
            String inputNIM = objScanner.nextLine();

            System.out.print("Program: ");
            objStudent.programStudi = objScanner.nextLine();

            // cek nim apakah sudah 15 digit & tidak terdapat di dalam array
            if (inputNIM.length() == 15) {
                for (int i = 0; i < userStudentIndex; i++) {
                    if (studentArray[i][2].equals(inputNIM)) {
                        isNIMExist = true;
                    } else {
                        isNIMExist = false;
                    }
                }
                if (!isNIMExist) {
                    System.out.println("NIM valid dan berhasil ditambahkan.");

                    // input data ke array
                    studentArray[userStudentIndex][0] = objStudent.name;
                    studentArray[userStudentIndex][1] = objStudent.faculty;
                    studentArray[userStudentIndex][2] = inputNIM;
                    studentArray[userStudentIndex][3] = objStudent.programStudi;

                    userStudentIndex += 1;
                    Main.menuAdmin();
                } else {
                    System.out.println("NIM valid tetapi NIM sudah terdapat di dalam data.");
                }
            } else {
                System.out.println("NIM tidak valid. Harus berjumlah 15 digit.");
            }
        } while (isNIMExist);
    }
}
