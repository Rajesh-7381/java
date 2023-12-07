package BankSystem;

import java.sql.*;
import java.util.Scanner;

public class Account {
    private Connection connection;
    private Scanner scanner;
    public Account(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public  boolean account_exists(String email){
        String email_query="select * from user where email=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(email_query);
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return  true;
            }else {
                return  false;
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
        return  false;
    }
    public  long open_account(String email){
//        if(account_exists(email)){
        if(!account_exists(email)){
            String account_openingquery="insert into Accounts(account_number,name,email,balance,pin) values(?,?,?,?,?)";
            scanner.nextLine();
            System.out.print("enter your name:");
            String name=scanner.nextLine();
            System.out.println("enter intial amount:");
            double balance=scanner.nextDouble();
            System.out.println("enter your secret pin:");
            int pin=scanner.nextInt();
            try {
                long account_number=generateaccountnumber(email);
                PreparedStatement preparedStatement=connection.prepareStatement(account_openingquery);
                preparedStatement.setLong(1,account_number);
                preparedStatement.setString(2,name);
                preparedStatement.setString(3,email);
                preparedStatement.setDouble(4,balance);
                preparedStatement.setInt(5,pin);
                int affectedrows=preparedStatement.executeUpdate();
                if(affectedrows > 0){
                    System.out.println("account generated successfully");
                    return account_number;
                }else {
                    throw new RuntimeException("account creation failed");
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        throw  new RuntimeException("account already exists");
    }
    public  long generateaccountnumber(String email){
//        String query="select * from Accounts where email=?";
        try {
            Statement statement=connection.createStatement();

            ResultSet resultSet=statement.executeQuery("select account_number from Accounts order by account_number desc limit 1");
            if(resultSet.next()){
                long last_number=resultSet.getLong("account_number");
                return last_number+1;
            }else {
                return 10000100;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return 10000100;
    }
    public long get_accountnumber(String email){
        String query="select account_number from Accounts where email=?";
        try {
            PreparedStatement preparedStatement= connection.prepareStatement(query);
            preparedStatement.setString(1,email);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return resultSet.getLong("account_number");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        throw new RuntimeException("account_number does not exists!");
    }

}
