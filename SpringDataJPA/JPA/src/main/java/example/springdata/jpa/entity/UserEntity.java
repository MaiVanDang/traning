package example.springdata.jpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "users")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    public UserEntity() {}

    public UserEntity(String name) {
        this.name = name;
    }

    public UserEntity(int id, String name) {
        this.id = id;
        this.name = name;
    }

    // getter/setter
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
}
