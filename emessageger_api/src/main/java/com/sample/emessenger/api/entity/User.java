package com.sample.emessenger.api.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.persistence.Version;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "User")
@Table(name = "user", uniqueConstraints = {
		@UniqueConstraint(columnNames = { "email" }, name = "emessenger_db_user_email_unique") })
public class User {

	@Id
	@SequenceGenerator(name = "emessenger_db_user_seq", sequenceName = "emessenger_db_user_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "emessenger_db_user_seq")
	@Column(name = "id")
	private Long id;

	@Column(name = "name", length = 255)
	private String name;

	@NotBlank
	@Size(max = 50)
	@Email
	@Column(name = "email", length = 50)
	private String email;

	@NotBlank
	@Size(max = 120)
	@Column(name = "password", length = 120)
	private String password;

	@Size(max = 20)
	@Column(name = "mobile_no", length = 20)
	private String mobileNo;

	@Size(max = 200)
	@Column(name = "address", length = 200)
	private String address;

	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(	name = "user_roles", 
				joinColumns = @JoinColumn(name = "user_id"), 
				inverseJoinColumns = @JoinColumn(name = "role_id"))
	private Set<Role> roles = new HashSet<>();
	
	@Version
	@Column(name = "OPTLOCK")
	private Long versionId;
}
