package com.rk.mtms.entity;
import lombok.Data;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import java.io.Serializable;
import javax.persistence.*;

@Entity
@Data
@Table(name = "user")
@EntityListeners(value = AuditingEntityListener.class)
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name="id")
    private int id;
    @Column(name="first_name")
    private String firstName;
    @Column(name="last_name")
    private String lastName;
    @Column(name="email")
    private String email;
    @Column(name="user_type")
    private String userType;
    @Column(name="phone_no")
    private String phoneNo;
    @Column(name="password")
    private String password;
    @Column(name="address_id")
    private int addressId;
    @Column(name="user_name")
    private String userName;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }


}
