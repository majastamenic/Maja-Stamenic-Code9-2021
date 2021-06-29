package com.code9.usermicroservice.user.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "roles")
@Data
public class Role implements GrantedAuthority{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column
    private String name;

    @Override
    public String toString() {
        return this.name;
    }

    @Override
    public String getAuthority() {
        return this.name;
    }
}
