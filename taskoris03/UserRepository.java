package taskoris03;
import java.sql.SQLException;
import java.util.List;

public interface UserRepository extends CrudRepository<User> {
    List <User> findAllbyAge(Integer age) throws SQLException;
}
