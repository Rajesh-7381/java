package BankSystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public  class BankingApp {
    private  static  final String url="jdbc:mysql://localhost:3307/bankmanagementsystem";
    private static  final  String username="root";
    private static  final  String password="1234";

    public static void main(String[] args) throws ClassNotFoundException,SQLException {
       try {
           Class.forName("com.mysql.cj.jdbc.Driver");
           System.out.println("driver loaded sucessfully");
       }catch (ClassNotFoundException e){
           System.out.println(e.getMessage());
       }try {
            Connection connection= DriverManager.getConnection(url,username,password);
            System.out.println("connection established");
            Scanner scanner=new Scanner(System.in);
//            create parameterized constructor
            User user=new User(connection,scanner);
            Account accounts=new Account(connection,scanner);
            AccountManageer acountmanager=new AccountManageer(connection,scanner);
            String email;
            long account_number;
            while (true){
//
           System.out.println("1. Register");
           System.out.println("2. Login");
           System.out.println("3. Exit");
           System.out.print("please choose:");
           Scanner sc=new Scanner(System.in);
           int choice=sc.nextInt();

           switch (choice){
               case 1:
                   user.Register();
//                      \033[H moves the cursor to the top-left corner of the console (coordinates 0,0).
//                      \033[2J clears the entire console screen.
//                       System.out.print("\033[H\033[2J"); sends these escape sequences to clear the console.
//
//                       System.out.flush(); ensures that any output in the output stream is flushed or cleared immediately.
//                   System.out.print("\033[H\033[2J");
//                   System.out.flush();
                   break;
               case 2:
                   email = user.Login();
                   if (email != null) {
                       System.out.println("User logged in");
//                       if (accounts.account_exists(email)) {
                       if (!accounts.account_exists(email)) {
                           System.out.println("1. Open a new bank account");
                           System.out.println("2. Exit");
                           if (scanner.nextInt() == 1) {
                               account_number = accounts.open_account(email);
                               System.out.println("Account created successfully");
                               System.out.println("Your account number is " + account_number);
                           } else {
                               break;
                           }
                       }
                       account_number = accounts.get_accountnumber(email);
                       int choice2 = 0;
                       while (choice2 != 5) {
                           System.out.println("1. Debit money");
                           System.out.println("2. Credit money");
                           System.out.println("3. Transfer money");
                           System.out.println("4. Check balance");
                           System.out.println("5. Logout");
                           choice2 = scanner.nextInt();
                           switch (choice2) {
                               case 1:
                                   acountmanager.debit_money(account_number);
                                   break;
                               case 2:
                                   acountmanager.credit_money(account_number);
                                   break;
                               case 3:
                                   acountmanager.transfer_money(account_number);
                                   break;
                               case 4:
                                   acountmanager.getbalance(account_number);
                                   break;
                               case 5:
                                   // Handle logout functionality here
                                   // acountmanager.logout();
                                   break;
                               default:
                                   System.out.println("Invalid choice");
                                   break;
                           }
                       }
                   } else {
                       System.out.println("Invalid email or password");
                   }
                   break;

               case 3:
                   System.out.println("thankyou using for banking system");
                   System.out.println("existing system!");
                   return;
               default:
                   System.out.println("invalid choice");
                   break;
           }

            }
        }catch (SQLException e){
            e.printStackTrace(); //printStackTrace thrown error if any error occuered
        }

//

    }
//    public  static  void  Register(){
//
//
//
//
//    }
}