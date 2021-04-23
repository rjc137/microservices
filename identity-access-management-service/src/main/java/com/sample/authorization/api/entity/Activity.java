package com.sample.authorization.api.entity;

import static javax.persistence.EnumType.STRING;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import org.springframework.http.HttpMethod;

import lombok.Data;

@Entity(name = "Activity")
@Table(name = "activities")
@Data
public class Activity {

    @Id
    @SequenceGenerator(allocationSize = 1, name = "activities_sequence_generator", sequenceName = "activities_sequence_generator")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "activities_sequence_generator")
    private Long id;

    @Column(name = "url")
    private String url;

    @Enumerated(STRING)
    @Column(name = "method")
    private HttpMethod method;

    @Column(name = "url_regex")
    private String urlRegex;

    @Version
    @Column(name = "OPTLOCK")
    private Long versionId;
}
