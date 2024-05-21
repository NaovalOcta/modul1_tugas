package com.main;

import data.Admin;

import java.util.ArrayList;
import java.util.Scanner;

import data.Student;
import exception.custom.IllegalAdminAccess;

public class LibrarySystem {
    private static Admin objAdmin = new Admin();
    private static Scanner objScanner = new Scanner(System.in);
    private static ArrayList<String> nimStudentList = new ArrayList<>();
    private static String nimStudentListHolder;

    public ArrayList<String> getNimStudentList() {
        return nimStudentList;
    }

    public void setNimStudentList(String nimStudent) {
        nimStudentList.add(nimStudent);
    }

    public String getNimStudentListHolder() {
        return nimStudentListHolder;
    }

    public static void menu() {
        try {
            while (true) {
                System.out.println("====== Library System ======");
                System.out.println("1. Login as Student");
                System.out.println("2. Login as Admin");
                System.out.println("3. Exit");
                System.out.print("Choose menu (1-3): ");
                int menuChoice = objScanner.nextInt();

                switch (menuChoice) {
                    case 1:
                        inputNim();
                        break;
                    case 2:
                        System.out.print("Masukkan Username (admin): ");
                        String username = objScanner.next();
                        System.out.print("Masukkan Password (admin): ");
                        String password = objScanner.next();
                        if (objAdmin.isAdmin(username, password)) {
                            System.out.println("Login berhasil sebagai Admin\n");
                            objAdmin.menu();
                        } else {
                            System.out.println("User Admin tidak ditemukan\n");
                        }
                        break;
                    case 3:
                        System.out.println("Keluar dari program...");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        } catch (IllegalAdminAccess e) {
            System.err.println(e);
        }
    }

    public static void inputNim() {
        do {
            System.out.print("Masukkan NIM Anda (masukkan 99 untuk kembali): ");
            nimStudentListHolder = objScanner.next();
            if (nimStudentListHolder.equals("99")) {
                menu();
            } else {
                if (checkNim()[0].equals(0)) {
                    System.out.println("NIM anda belum terdaftar!\n");
                } else if (checkNim()[0].equals(1)) {
                    System.out.println("Anda telah login ...\n");
                } else if (checkNim()[0].equals(2)) {
                    System.out.println("NIM anda tidak valid! Harus 15 karakter.\n");
                } else {

                }
            }

        } while (!(checkNim()[0].equals(1)));

        Student student = new Student("", "", "", "");
        student.displayInfo();
    }

    public static Object[] checkNim() {
        Object[] object = new Object[1];
        object[0] = 0;

        for (String string : nimStudentList) {
            if (string.equals(nimStudentListHolder)) {
                // nim belum didaftarkan oleh admin
                object[0] = 1;
                return object;
            } else if (string.length() != 15) {
                // panjang nim melebihi atau kurang dari 15 digit
                object[0] = 2;
                return object;
            }
        }

        return object;
    }

    public static void addTempStudent() {
        System.out.println("Daftar Data siswa :");
    }

    public static void addTempBooks() {
        System.out.println("Daftar Buku :");
    }

    public static void main(String[] args) {
        menu();
    }
}
