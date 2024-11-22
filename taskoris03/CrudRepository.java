package taskoris03;
import java.sql.*;
import java.util.List;
import java.util.Optional;

public interface CrudRepository<T> {
    List<T> findAll() throws SQLException;
    Optional<T> findByid(long id) throws SQLException;


    void save(T entity) throws SQLException;
    void update(T entity) throws SQLException;
    void remove(T entity) throws SQLException;
    void removebyid(Long id) throws SQLException;

    List<T> findByGenre(String genre) throws SQLException;
    void countBycountry(String country) throws SQLException;
    void richestperson() throws SQLException;
}
