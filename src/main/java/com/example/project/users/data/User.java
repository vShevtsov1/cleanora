package com.example.project.users.data;
import com.example.project.users.data.help.userRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="users",uniqueConstraints = { @UniqueConstraint(columnNames = { "userEmail"})})
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "userId")
    private Long userId;

    @Column(name = "userName")
    private String userName;

    @Column(name = "userEmail", unique = true)
    private String userEmail;

    @Column(name="userPhoneNumber")
    private String userPhoneNumber;

    @Column(name = "userRole")
    private userRole userRole;

    @Column(name = "userPassword")
    private String userPassword;




}
