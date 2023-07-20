package org.diegodev.hotelalurabackend.models.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "hotels")
public class Hotel extends AuditableEntity {

	@Column
	@NotNull
	private String name;

	@Column(unique = true)
	@NotNull
	private String email;

	@Column
	@NotNull
	private String phoneNumber;

	@Column
	@NotNull
	private String telephoneNumber;

	@Column
	@NotNull
	private String address;

	@Column
	@NotNull
	private String location;

	@Column
	@NotNull
	private Double rating;

	@OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
	private List<Room> rooms;

	@OneToMany(mappedBy = "hotel", fetch = FetchType.EAGER)
	private List<Reservation> reservations;

}