package com.BitProject.usermanagementservice.domain;

import jakarta.annotation.Nonnull;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users", schema = "public")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String hashPassword;

    private String saltPassword;

    private boolean isDeleted = false;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Role> roles;


}
