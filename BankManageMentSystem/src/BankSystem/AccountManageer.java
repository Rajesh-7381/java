package BankSystem;
//import java.sql.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class AccountManageer {
    private Connection connection;
    private Scanner scanner;
    AccountManageer(Connection connection,Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public  void credit_money(long account_number) throws SQLException{
        scanner.nextLine();
        System.out.print("enter amount:");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.print("enter secruity pin:");
        int pin=scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            if (account_number !=0){
                PreparedStatement preparedStatement=connection.prepareStatement("select * from Accounts where account_number = ? and pin=?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setInt(2,pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    String credit_query="update Accounts set balance= + ? where account_number=?";
                    PreparedStatement preparedStatement1=connection.prepareStatement(credit_query);
                    preparedStatement1.setDouble(1,amount);
                    preparedStatement1.setLong(2,account_number);
                    int rows_affected=preparedStatement1.executeUpdate();
                    if(rows_affected > 0){
                        System.out.println("Rs::"+amount+ "credited successfully!");
                        connection.commit();
                        connection.setAutoCommit(true);
                        return;
                    }else {
                        System.out.println("transaction failed");
                        connection.rollback();
                        connection.setAutoCommit(true);
                    }
                }else {
                    System.out.println("invalid security Pin!");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();

        }
        connection.setAutoCommit(true);

    }
    public  void  debit_money(long account_number) throws SQLException{
        scanner.nextLine();
        System.out.print("enter amount:");
        double amount=scanner.nextDouble();
        scanner.nextLine();
        System.out.print("enter secruity pin:");
        int pin=scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            if(account_number !=0){
                PreparedStatement preparedStatement=connection.prepareStatement("select * from Accounts where account_number=? and  pin=?");
                preparedStatement.setLong(1,account_number);
                preparedStatement.setInt(2,pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    double current_balance= resultSet.getDouble("balance");
                    if(amount <= current_balance){
                        String debit_query="update  Accounts set balance=balance - ? where account_number=?";
                        PreparedStatement preparedStatement1= connection.prepareStatement(debit_query);
                        preparedStatement1.setDouble(1,amount);
                        preparedStatement1.setLong(2,account_number);
                        int rows_affected=preparedStatement1.executeUpdate();
                        if(rows_affected > 0){
                            System.out.println("Rs::"+amount+ "debited successfully!");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }else {
                            System.out.println("transaction failed!");
                            connection.commit();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("now your current balance is::"+current_balance+"please enter  below current balance");

                    }
                }else {
                    System.out.println("invalid security pin");
                }
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);

    }
    public  void  transfer_money(long sender_account_number) throws SQLException{
        scanner.nextLine();
        System.out.println("enter reciver account number");
        long reciver_account_number=scanner.nextLong();
        scanner.nextLine();
        System.out.print("enter amount:");
        double amount=scanner.nextDouble();

        scanner.nextLine();
        System.out.print("enter secruity pin:");
        int pin=scanner.nextInt();
        try {
            connection.setAutoCommit(false);
            if(sender_account_number!=0 && reciver_account_number!=0){
                PreparedStatement preparedStatement=connection.prepareStatement("select * from Accounts where account_number=? and  pin=?");
                preparedStatement.setLong(1,sender_account_number);
                preparedStatement.setInt(2,pin);
                ResultSet resultSet=preparedStatement.executeQuery();
                if(resultSet.next()){
                    double current_balance=resultSet.getDouble("balance");
                    if(amount <= current_balance){
                        String credit_query="update Accounts set balance= + ? where account_number=?";
                        String debit_query="update Accounts set balance= - ? where account_number=?";
                        PreparedStatement creditpreparedstatement=connection.prepareStatement(credit_query);
                        PreparedStatement debitpreparedstatement=connection.prepareStatement(debit_query);
                        creditpreparedstatement.setDouble(1,amount);
                        creditpreparedstatement.setLong(2,reciver_account_number);
                        debitpreparedstatement.setDouble(1,amount);
                        debitpreparedstatement.setLong(22,sender_account_number);
                        int affected_rows=debitpreparedstatement.executeUpdate();
                        int affected_rows2=creditpreparedstatement.executeUpdate();
                        if(affected_rows > 0 && affected_rows2 > 0){
                            System.out.println("transaction successful!");
                            System.out.println("Rs "+amount+ "Transfered suessfully!");
                            connection.commit();
                            connection.setAutoCommit(true);
                            return;
                        }else {
                            System.out.println("transaction failed!");
                            connection.rollback();
                            connection.setAutoCommit(true);
                        }
                    }else {
                        System.out.println("insufficent balance!");
                    }
                }else {
                    System.out.println("invalid security pin");
                }
            }else {
                System.out.println("invalid account number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        connection.setAutoCommit(true);
    }

    public  void getbalance(long account_number)   {
        scanner.nextLine();
        System.out.print("enter security pin::");
        int pin=scanner.nextInt();
        try {
            PreparedStatement preparedStatement= connection.prepareStatement("select balance from Accounts where account_number=? and pin=?");
            preparedStatement.setLong(1,account_number);
            preparedStatement.setInt(2,pin);
            ResultSet resultSet= preparedStatement.executeQuery();
            if(resultSet.next()){
                double balance=resultSet.getDouble("balance");
                System.out.println("balance::"+balance);

            }else {
                System.out.println("invalid pin!");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

}
