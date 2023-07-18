package org.diegodev.hotelalurabackend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.diegodev.hotelalurabackend.models.IUser;

import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User extends AuditableEntity implements IUser {

    @NotEmpty
    @Column(name = "first_name")
    private String firstName;

    @NotEmpty
    @Column(name = "last_name")
    private String lastName;

    @NotEmpty
    @Column(name = "phone_number")
    private String phoneNumber;

    @NotBlank
    @Size(min = 4, max = 12)
    @Column(unique = true)
    private String username;

    @NotBlank
    private String password;

    @NotEmpty
    @Email
    @Column(unique = true)
    private String email;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"),
            uniqueConstraints = {@UniqueConstraint(columnNames = {"user_id", "role_id"})})
    private List<Role> roles;

    @OneToMany(mappedBy = "user", fetch = FetchType.EAGER)
    private List<Reservation> reservations;

    @Transient
    private boolean admin;

}
