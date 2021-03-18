package com.appsdeveloperblog.app.ws.mobileappws.persistence.entity;

import com.appsdeveloperblog.app.ws.mobileappws.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity(name = "addresses")
public class Address implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 30 )
    private String addressId;

    @NotBlank(message = "city is mandatory")
    @NotNull
    @Column(nullable = false, length = 15)
    private String city;

    @NotBlank(message = "country is mandatory")
    @NotNull
    @Column(nullable = false, length = 15)
    private String country;

    @Column(nullable = false, length = 100)
    private String streetName;

    @NotBlank(message = "postalCode is mandatory")
    @NotNull
    @Column(nullable = false, length = 7)
    private String postalCode;

    @Column(nullable = false, length = 10)
    private String type;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private User userDetails;
}
