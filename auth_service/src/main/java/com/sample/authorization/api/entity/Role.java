package com.sample.authorization.api.entity;

import static javax.persistence.EnumType.STRING;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
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

@Entity(name = "Role")
@Table(name = "roles")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Role {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "roles_sequence_generator", sequenceName = "roles_sequence_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_sequence_generator")
    @Column(name = "id")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "name")
    private Roles name;

    @ManyToMany
    @JoinTable(name = "role_permissions", joinColumns = @JoinColumn(name = "role_id"), inverseJoinColumns = @JoinColumn(name = "permission_id"), foreignKey = @ForeignKey(name = "role_permissions_role"), inverseForeignKey = @ForeignKey(name = "role_permissions_permission"))
    private Set<Permission> permissions = new HashSet<>();

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Version
    @Column(name = "OPTLOCK")
    private Long versionId;

}
