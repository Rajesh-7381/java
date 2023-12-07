package BankSystem;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class User {
    private  Connection connection;
    private  Scanner scanner;
    public  User(Connection connection, Scanner scanner){
        this.connection=connection;
        this.scanner=scanner;
    }
    public  void Register(){
        scanner.nextLine();
        System.out.println("*****************register here********************");

        System.out.println("1.enter your name:");
        String name=scanner.nextLine();
        System.out.println("2.enter your email:");
        String email=scanner.nextLine();
        System.out.println("3.enter your password:");
        String password=scanner.nextLine();
        if(user_exists(email)){
            System.out.println("user exists for this email address");
            return;
        }
        String register_query="insert into user(name,email,password) values(?,?,?)";
        try {
            PreparedStatement preparedStatement=connection.prepareStatement(register_query);
            preparedStatement.setString(1,name);
            preparedStatement.setString(2,email);
            preparedStatement.setString(3,password);
            int affectedrows=preparedStatement.executeUpdate();
            if(affectedrows > 0){
                System.out.println("registration suucessfull");
            }else{
                System.out.println("registration not suucessfull");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
    public String Login(){
        scanner.nextLine();
        System.out.println("*********login here**********");
        System.out.print("enter your email:");
        String email=scanner.nextLine();
        System.out.print("enter your password:");
        String password=scanner.nextLine();
        String login_query="select * from user where email=? and password=? ";
        try{
            PreparedStatement preparedStatement=connection.prepareStatement(login_query);

            preparedStatement.setString(1,email);
            preparedStatement.setString(2,password);
            ResultSet resultSet=preparedStatement.executeQuery();
            if(resultSet.next()){
                return email;
            }else {
                return null;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }
    public  boolean user_exists(String email){
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
}
