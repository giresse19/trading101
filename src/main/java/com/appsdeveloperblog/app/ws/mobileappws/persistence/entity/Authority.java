package com.appsdeveloperblog.app.ws.mobileappws.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "authorities")
public class Authority implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(nullable = false, length = 30)
    private String name;

    @ManyToMany(mappedBy = "authorities")
    private Collection<Role> roles;

    public Authority(String name) {
        this.name = name;
    }
}
