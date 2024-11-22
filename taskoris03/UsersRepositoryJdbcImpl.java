package taskoris03;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UsersRepositoryJdbcImpl implements UserRepository{
private final  Connection connection;
private static final String SQL_SELECT_FROM_DRIVER = "SELECT * FROM driver";
private static final String SQL_UPDATE = "UPDATE driver SET  name = ?, surname = ?, age = ? WHERE id = ?";
private static final String SQL_SELECT_BY_ID = "SELECT * FROM driver WHERE id = ?";
private static final String SQL_SAVE = "INSERT INTO driver (name, surname, age) VALUES (?,?,?)";
private static final String SQL_REMOVE = "DELETE FROM driver WHERE id = ? AND name = ? AND surname = ?  AND age = ?";
private static final String SQL_REMOVE_BY_ID = "DELETE FROM driver WHERE id = ?";
private static final String SQL_FIND_BY_AGE = "SELECT * FROM driver WHERE age = ?";
private static final String SQL_FIND_BY_GENRE ="SELECT * FROM driver WHERE genre = ?";
private static final String SQL_COUNT_PEOPLE = "SELECT COUNT(country)  FROM driver WHERE country = ?";
private static final String SQL_FIND_RICHEST = "SELECT name , salary FROM driver" +
        " WHERE salary = (SELECT MAX(salary) FROM driver)";



    public UsersRepositoryJdbcImpl(Connection connection) {
        this.connection = connection;
    }

    @Override
    public List findAll() throws SQLException {
        Statement stament = connection.createStatement();
        ResultSet resultSet = stament.executeQuery(SQL_SELECT_FROM_DRIVER);

        List<User>result = new ArrayList<>();

        while(resultSet.next()) {
            User user = new User(
               resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("genre"),
                    resultSet.getString("country"),
                    resultSet.getInt("salary")
            );
            result.add(user);
        }
return result;
    }

    @Override
    public Optional<User> findByid(long id) throws SQLException{
        PreparedStatement pst = connection.prepareStatement(SQL_SELECT_BY_ID);
        pst.setLong(1,id);

        ResultSet resultSet = pst.executeQuery();

        if (resultSet.next()){
            User user = new User(
                    resultSet.getLong("id"),
                    resultSet.getString("name"),
                    resultSet.getString("surname"),
                    resultSet.getInt("age"),
                    resultSet.getString("genre"),
                    resultSet.getString("country"),
                    resultSet.getInt("salary")
             );
            return Optional.of(user);
        }
        else {
            return Optional.empty();
        }
    }




    @Override
    public void save(User entity) throws SQLException{
        PreparedStatement pst = connection.prepareStatement(SQL_SAVE);
        pst.setString(1,entity.getFirstname());
        pst.setString(2,entity.getLastname());
        pst.setInt(3,entity.getAge());
        pst.executeUpdate();
    }



    @Override
    public void update(User entity) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(SQL_UPDATE);
        pst.setString(1,entity.getFirstname() );
        pst.setString(2, entity.getLastname());
        pst.setInt(3,entity.getAge());
        pst.setLong(4,entity.getId());
        pst.executeUpdate();


    }

    @Override
    public void remove(User entity) throws SQLException {
     PreparedStatement pst = connection.prepareStatement(SQL_REMOVE);
        pst.setLong(1,entity.getId());
        pst.setString(1,entity.getFirstname() );
        pst.setString(2, entity.getLastname());
        pst.setInt(3,entity.getAge());
        pst.setString(4, entity.getGenre());
        pst.setString(2, entity.getCountry());
        pst.setInt(3, entity.getSalary());
    }

    @Override
    public void removebyid(Long id) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(SQL_REMOVE_BY_ID);
        pst.setLong(1,id);
        pst.executeUpdate();

    }

    @Override
    public List<User> findByGenre(String genre) throws SQLException {
      PreparedStatement preparedStatement = connection.prepareStatement(SQL_FIND_BY_GENRE);
      preparedStatement.setString(1,genre);
      ResultSet resultSet = preparedStatement.executeQuery();

      List<User> user = new ArrayList<>();

      while(resultSet.next()){
          User users = new User(
                  resultSet.getLong("id"),
                  resultSet.getString("name"),
                  resultSet.getString("surname"),
                  resultSet.getInt("age"),
                  resultSet.getString("genre"),
                  resultSet.getString("country"),
                  resultSet.getInt("salary")
          );
          user.add(users);
      }
    return user;
    }

    @Override
    public void countBycountry(String country) throws SQLException {
        PreparedStatement pst = connection.prepareStatement(SQL_COUNT_PEOPLE);
        pst.setString(1,country);
        ResultSet resultSet = pst.executeQuery();
        int count = 0;
        if(resultSet.next()){
             count = resultSet.getInt(1);
        }
        System.out.println("number of people born in " + country + " : " + count);
    }

    @Override
    public void richestperson() throws SQLException {
        Statement stament = connection.createStatement();
        ResultSet resultSet = stament.executeQuery(SQL_FIND_RICHEST);



        String salaryperson = null;
        int salary = 0;
        if(resultSet.next()){
            salaryperson = resultSet.getString(1);
            salary = resultSet.getInt(2);

        }
        System.out.println(" the person with the best salary is : " + salaryperson +  "(" + salary + ")");
    }

    @Override
    public List<User> findAllbyAge(Integer age) throws SQLException {
        List<User>result = new ArrayList<>();
        PreparedStatement pst = connection.prepareStatement(SQL_FIND_BY_AGE);
        pst.setInt(1,age);
        ResultSet resultSet = pst.executeQuery();

        while(resultSet.next()){
            User user = new User(
                    resultSet.getLong("id"),
                   resultSet.getString("name"),
                   resultSet.getString("surname"),
                   resultSet.getInt("age"),
                    resultSet.getString("genre"),
                    resultSet.getString("country"),
                    resultSet.getInt("salary")
            );
            result.add(user);
        }
        return result;
    }




}
