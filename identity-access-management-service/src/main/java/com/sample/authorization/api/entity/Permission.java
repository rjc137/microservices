package com.sample.authorization.api.entity;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.FetchType.LAZY;

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

import lombok.Data;

@Entity(name = "Permission")
@Table(name = "permissions")
@Data
public class Permission {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "permissions_sequence_generator", sequenceName = "permissions_sequence_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "permissions_sequence_generator")
    @Column(name = "id")
    private Long id;

    @Enumerated(STRING)
    @Column(name = "name")
    private Permissions name;

    @ManyToMany(fetch = LAZY)
    @JoinTable(name = "permission_activities", joinColumns = @JoinColumn(name = "permission_id"), inverseJoinColumns = @JoinColumn(name = "activity_id"), foreignKey = @ForeignKey(name = "permission_activities_activity"), inverseForeignKey = @ForeignKey(name = "permission_activities_permission"))
    private Set<Activity> activities = new HashSet<>();

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Version
    @Column(name = "OPTLOCK")
    private Long versionId;
}
