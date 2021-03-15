package com.appsdeveloperblog.app.ws.mobileappws.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "users")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false)
    private String userId;

    @NotBlank(message = "firstName is mandatory")
    @NotNull
    @Column(nullable = false, length = 50)
    private String firstName;

    @NotBlank(message = "lastName is mandatory")
    @NotNull
    @Column(nullable = false, length = 50)
    private String lastName;

    @NotBlank(message = "Email is mandatory")
    @NotNull
    @Column(nullable = false, length = 120, unique = true)
    private String email;

    @Column(nullable = false)
    private String encryptedPassword;

    private String emailVerificationToken;

    @Column(nullable = false)
    private Boolean emailVerificationStatus = false;

    @OneToMany(mappedBy = "userDetails", cascade = CascadeType.ALL)
    private List<Address> addresses;

}
