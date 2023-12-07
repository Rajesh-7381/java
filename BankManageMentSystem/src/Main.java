//package BankSystem;
////
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//import java.util.Scanner;
//
//public class BankingApp {
//    private static final String url="jdbc:mysql://localhost:3307/bankmanagementsystem";
//    private static final String username="root";
//    private static final String password="1234";
//
//    public static void main(String[] args) throws  ClassNotFoundException, SQLException {
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            System.out.println("driver loaded successfully");
//        }catch (ClassNotFoundException e){
//            System.out.println(e.getMessage());
//        }try {
//            Connection connection = DriverManager.getConnection(url,username,password);
//            System.out.println("connection established");
//        }catch (SQLException e){
//            e.printStackTrace();
//        }
//
//
//        while (true) {
//            System.out.println("Enter your choice:");
//            System.out.println("1. Register");
//            System.out.println("2. Login");
//            System.out.println("3. Exit");
//            System.out.println("please choose");
//
//            Scanner scanner = new Scanner(System.in);
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    register();
//                    break;
//                case 2:
//                    login();
//                    break;
//                case 3:
//                    exit();
//                    break;
//                default:
//                    System.out.println("Invalid choice");
//                    break;
//            }
//        }
//    }
//
//    private static void register() {
//        System.out.println("Registering new user...");
//        // Placeholder for registration logic
//        // Prompt user for registration details (username, password, etc.)
//        // Store the information in a database or file
//        // Provide appropriate feedback to the user after registration
//    }
//
//    private static void login() {
//        System.out.println("Logging in...");
//        // Placeholder for login logic
//        // Prompt user for login credentials (username, password, etc.)
//        // Verify the credentials by checking in a database or file
//        // Grant access or deny based on the validation
//        Scanner log=new Scanner(System.in);
//        System.out.println("enter your name");
//        String name=log.nextLine();
//        System.out.println("enter your email");
//        String email=log.nextLine();
//        System.out.println("thank you" +name + "sucessfully registering our banking system");
//        System.out.println("1.open your bank account:");
//        System.out.println("2.exit");
//        Scanner scn=new Scanner(System.in);
//        int open_acc=scn.nextInt();
//        while (true){
//            switch (open_acc){
//                case 1:
//                    open_bank_account();
//                    break;
//                case 2:
//                    exit();
//                    break;
//                default:
//                    System.out.println("invalid choice");
//            }
//            break;
//
//        }
//    }
//
//    private static void exit() {
//        System.out.println("Exiting the program.");
//        System.exit(0); // Terminate the program
//    }
//    private  static  void  debit_money(){
//        System.out.println("debit money");
//    }
//    private  static  void  credit_money(){
//        System.out.println("credit money");
//    }
//    private  static  void  check_balance(){
//        System.out.println("check");
//    }
//
//    private static void  open_bank_account(){
//        System.out.println("open your bank account");
//        System.out.println("enter your choice");
//        Scanner open_account=new Scanner(System.in);
//        System.out.println("1.debit money");
//
//        System.out.println("2.credit money");
//
//
//        System.out.println("3.check balance");
//
//
//        int choice_acc=open_account.nextInt();
//        while (true){
//            switch (choice_acc){
//                case 1:
//                    debit_money();
//                    break;
//                case 2:
//                    credit_money();
//                    break;
//                case 3:
//                    check_balance();
//                    break;
//                default:
//                    System.out.println("invalid choice");
//            }
//            break;
//        }
//    }
//}
//
//
//
//
//
//
//
//
//
//
////import java.sql.Connection;
////import java.sql.DriverManager;
////import java.sql.SQLException;
////import java.util.Scanner;
////
////public class BankingApp {
////    private static final String url="jdbc:mysql://localhost:3307/bankmanagementsystem";
////    private static final String username="root";
////    private static final String password="1234";
////
////    public static void main(String[] args) throws ClassNotFoundException,SQLException {
////        try {
////            Class.forName("com.mysql.cj.jdbc.Driver");
////            System.out.println("driver updated successfully");
////        }catch (ClassNotFoundException e){
////            System.out.println(e.getMessage());
////        }
////        try {
////            Connection connection= DriverManager.getConnection(url,username,password);
////            System.out.println("connection established");
////            Scanner scanner=new Scanner(System.in);
////            while (true){
////                System.out.println("welcome to banking system app");
////                System.out.println("1. Register");
////                System.out.println("2. login");
////                System.out.println("2. exit");
////                Scanner choice=scanner.nextInt();
////               switch (choice){
////                   case 1:
////               }
////            }
////        }catch (SQLException e){
////            e.printStackTrace();
////        }
////    }
////}
