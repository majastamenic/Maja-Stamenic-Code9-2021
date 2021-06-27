package com.code9.usermicroservice.user.domain;

import com.code9.usermicroservice.user.validation.DateOfBirth;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "app_user")
public class User //implements UserDetails
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    @Column(unique = true)
    private String email;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @DateOfBirth
    @Column(name = "date_of_birth")
    private Date dateOfBirth;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "app_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private List<Role> roles;
    @Column(name = "deleted")
    private Boolean deleted;
    @Column
    private String username;
    @Column
    private String password;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        Set permissions = new HashSet();
//        for (Role role : roles)
//            permissions.add(new SimpleGrantedAuthority(role.getName()));
//        return permissions;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonExpired() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isAccountNonLocked() {
//        return true;
//    }
//
//    @JsonIgnore
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return true;
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return true;
//    }
}
