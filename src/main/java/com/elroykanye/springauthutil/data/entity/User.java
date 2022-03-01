package com.elroykanye.springauthutil.data.entity;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class User {
	@Id
	private Long id;

	@Column(name = "username", nullable = false, length = 128)
	private String username;

	@Column(name = "password", nullable = false)
	private String password;

	@Column(name = "email", nullable = false, length = 128)
	private String email;

	@Column(name = "first_name", nullable = false, length = 128)
	private String firstName;

	@Column(name = "last_name", nullable = false, length = 128)
	private String lastName;

	@Column(name = "time_created", nullable = false)
	private Date timeCreated = new Date();
}
