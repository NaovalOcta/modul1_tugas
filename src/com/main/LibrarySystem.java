package com.main;

import data.Admin;
import data.Student;

import java.util.Scanner;

public class LibrarySystem {
    public void menu() {
        Admin objAdmin = new Admin();
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
                    objAdmin.menu();
                } else {
                    System.out.println("Username or password invalid");
                    this.menu();
                }
                break;
            case 3:
                System.out.println("Keluar dari program...");
                System.exit(0);
                break;
        }
    }

    public void inputNim() {
        Student objStudent = new Student("", "", "", "");
        Scanner objScanner = new Scanner(System.in);

        String NIM = "";
        do {
            System.out.print("Enter your NIM (input 99 untuk kembali): ");
            NIM = objScanner.next();
            if (NIM.equals("99")) {
                this.menu();
            } else {
                if ((Boolean) checkNim(NIM)) {
                    objStudent.menu();
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

                    Student.setName(Admin.getStudentList()[i][0]);
                    Student.setFaculty(Admin.getStudentList()[i][1]);
                    Student.setNim(Admin.getStudentList()[i][2]);
                    Student.setProgramStudi(Admin.getStudentList()[i][3]);
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

    public void addTempStudent() {

    }

    public static void addTempBooks(int tempBorrowedBookIndex, Object idBuku, String nim, int duration) {
        Student.borrowedBooks[tempBorrowedBookIndex][0] = nim;
        Student.borrowedBooks[tempBorrowedBookIndex][1] = idBuku.toString();
        Student.borrowedBooks[tempBorrowedBookIndex][2] = duration;
    }

    public static void main(String[] args) {
        LibrarySystem objLibrarySystem = new LibrarySystem();

        objLibrarySystem.menu();
    }
}