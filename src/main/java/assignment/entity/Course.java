package assignment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "course")
@Data
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String title;
    private String description;
    private double price;
    private Date createAt;
    private Date updateAt;
    private String image;

    @OneToOne
    private User creator;
}
