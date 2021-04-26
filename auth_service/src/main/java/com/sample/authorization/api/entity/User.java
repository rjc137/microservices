package com.sample.authorization.api.entity;

import static javax.persistence.FetchType.LAZY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "User")
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "users_sequence_generator", sequenceName = "users_sequence_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "users_sequence_generator")
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    private String password;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "role_id"), foreignKey = @ForeignKey(name = "user_roles_user"), inverseForeignKey = @ForeignKey(name = "user_roles_permission"))
    private Set<Role> roles = new HashSet<>();

    @Version
    @Column(name = "OPTLOCK")
    private Long versionId;

}
