package taskoris03;

public class User {
    private long id;
    private String firstname;
    private String  lastname;
    private Integer age;
    private String genre;
    private String country;
    private Integer salary;



    public User(long id, String firstname, String lastname, Integer age, String genre, String country, Integer salary) {
        this.id = id;
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.genre = genre;
        this.country = country;
        this.salary = salary;

    }

    public long getId() {
        return id;
    }


    public String getFirstname() {
        return firstname;
    }



    public String getLastname() {
        return lastname;
    }



    public Integer getAge() {
        return age;
    }

    public String getGenre(){
        return genre;
    }

    public String getCountry(){
        return country;
    }

    public Integer getSalary(){
        return salary;
    }

}
