package assignment.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table( name = "transaction" )
@Data
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createAt;
    private Date updateAt;
    private int status;
    private double price;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User buyer;

    @OneToOne
    @JoinColumn(name = "course_id", referencedColumnName = "id")
    private Course course;
}
