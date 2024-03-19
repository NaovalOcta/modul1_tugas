public class Student {
    String name;
    String faculty;
    String programStudi;



    static void logout()
    {
        Main.Menu();
    }

    static void displayBooks()
    {
        int no = 1;
        System.out.println("====================================================================================================");
        System.out.println("|| No. || ID Buku\t\t\t|| Nama Buku\t\t\t|| Author\t\t|| Category\t\t|| Stock\t  ||");
        System.out.println("====================================================================================================");
        for (int i = 0; i < 5; i++) {
            System.out.println("|| "+ no++ +"  || " + Main.bookList[i][0] +"\t\t|| " + Main.bookList[i][1] +"\t\t\t|| " + Main.bookList[i][2] +"\t\t|| " + Main.bookList[i][3] +"\t\t|| " + Main.bookList[i][4] +" ||\t");
        }
        System.out.println("====================================================================================================");
    }
}
