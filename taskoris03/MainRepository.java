package taskoris03;
import java.util.List;
import java.sql.*;
import java.util.Optional;
import java.util.Scanner;

public class MainRepository {
    public static void main(String []  args) throws SQLException {
        Scanner scanner = new Scanner(System.in);
        String USERNAME =  "postgres";
        String URL  = "jdbc:postgresql://localhost:5432/oris02";

        System.out.println("please enter your password :");
        String PASSWORD = scanner.nextLine();

        Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);

        UserRepository userRepository = new UsersRepositoryJdbcImpl(connection);

//        System.out.println("findall method");
//        List<User> users = userRepository.findAll();
//        for (User user : users) {
//            System.out.println(user.getId() + user.getFirstname() + user.getLastname()
//            + user.getAge());
//        }
//        System.out.println("please, enter the number of drivers to be added");
//        int n = scanner.nextInt();
//
//
//        System.out.println("save method");
//        for(int i = 0; i < n; i++) {
//            System.out.println("please enter the driver id :");
//            int id = scanner.nextInt();
//            System.out.println("please enter the name :");
//            String firstname = scanner.next();
//            System.out.println("please enter the lastname :");
//            String lastname = scanner.next();
//            System.out.println("please enter the age :");
//            int age = scanner.nextInt();
//
//            User user = new User(id, firstname, lastname, age, null, null, 0);
//            userRepository.save(user);
//        }


//        System.out.println("Findbyage method");
//        int agenumber = scanner.nextInt();
//        System.out.println(" found people of " + agenumber +  " age :");
//        List  <User> users1 = userRepository.findAllbyAge(agenumber);
//       for(User user : users1){
//           System.out.println(user.getId() + user.getFirstname() + user.getLastname());
//       }
//
//
//        System.out.println("findbyid method :");
//        Long idwanted = scanner.nextLong();
//        System.out.println(" found people with" +  idwanted + "id :");
//        Optional<User> users2 = userRepository.findByid(idwanted);
//        if(users2.isPresent()){
//            System.out.println(users2.get().getId() + users2.get().getFirstname() + users2.get().getLastname());
//        }
//        else {
//            System.out.println("people with " + idwanted + " not found");
//        }
//
//        System.out.println("update method :");
//        System.out.println("introduce the id :");
//        long idwanted1 = scanner.nextLong();
//        User users3 = new User(idwanted1, "Rodrigo", "Casas" , 30, null, null, 0);
//        userRepository.update(users3);
//
//
//
//        System.out.println("removebyid method :");
//        System.out.println("introduce the id :");
//        long idwanted2 = scanner.nextLong();
//        userRepository.removebyid(idwanted2);
//



        System.out.println("remove method");
        System.out.println("introduce user data for remove :");
        User users4 = new User(20, "Jorge", "Galvan", 21, "Man", "Perú", 2500);
        userRepository.remove(users4);


        System.out.println("findbygenre method");
        System.out.println("introduce the genre");
        String genre = scanner.nextLine();
        List<User> userslist =  userRepository.findByGenre(genre);
        for(User user : userslist){
            System.out.println(user.getFirstname() + user.getLastname() + user.getAge() + user.getGenre());
        }


        System.out.println("countbycountry method");
        System.out.println("introduce the country");
        String country = scanner.nextLine();
        userRepository.countBycountry(country);


        System.out.println("richestperson method");
        userRepository.richestperson();

    }
}
