package taskoris02;

import java.sql.*;
import java.util.Scanner;

class Main{
    private static final String DB_USERNAME = "postgres", DB_URL = "jdbc:postgresql://localhost:5432/oris02";
    public static void main(String [] args) throws SQLException {
        System.out.println("please enter your password :");
        Scanner sc = new Scanner(System.in);
        String DB_PASSWORD = sc.nextLine();


        Connection connection  = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT * FROM driver");

        while(resultSet.next()){
            System.out.println(resultSet.getInt("id") + " " +  resultSet.getString("name") + " "
            + resultSet.getString("surname") + " " + resultSet.getString("age"));
        }

    String name;
    String surname;
    String age;

     for(int n = 0; n < 6 ; n++) {
         System.out.println(" Name :");
         name = sc.nextLine();
         System.out.println("Surname");
         surname = sc.nextLine();
         System.out.println("Age");
         age = sc.nextLine();

         String query = "INSERT INTO driver (name , surname, age)" + "values (? , ?, ?)";
         PreparedStatement preparedStatement = connection.prepareStatement(query);

         preparedStatement.setString(1, name);
         preparedStatement.setString(2, surname);
         preparedStatement.setString(3, age);

         preparedStatement.executeUpdate();
     }


     String query1 = "SELECT id , name FROM driver WHERE name LIKE ? OR name LIKE ?";
     PreparedStatement preparedStatement1 = connection.prepareStatement(query1);
     preparedStatement1.setString(1, "%A%");
     preparedStatement1.setString(2, "%a%");

     ResultSet resultSet1 = preparedStatement1.executeQuery();

     while(resultSet1.next()){
         System.out.println(resultSet1.getInt("id") + " "  + " " + resultSet1.getString("name"));
     }



    }
}