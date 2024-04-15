package assignment.entity;

import assignment.enums.Gender;
import assignment.enums.Role;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "user")
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;
    private String fullName;
    @Column(unique = true, nullable = false)
    private String email;
    private String phoneNumber;
    private String role;
    private String gender;
    private String address;
    private String password;
    private Date createAt;
    private Date updateAt;

    public List<String> getRoleList() {
        if (this.role.toString().length() > 0) {
            return Arrays.asList(this.role.toString().split(","));
        }

        return new ArrayList<String>();
    }
}
