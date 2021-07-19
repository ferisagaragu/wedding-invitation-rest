package org.pechblenda.weddinginvitationrest.entity

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.Table
import javax.persistence.PrePersist
import javax.persistence.ManyToOne

import java.util.Date
import java.util.UUID

@Entity
@Table(name = "guest")
class Guest(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var uuid: UUID,
	var createdDate: Date,
	var name: String,
	var surname: String,
	var motherSurname: String,
	var confirmDate: Date,

	@Column(columnDefinition = "boolean default false")
	var confirm: Boolean,

	@ManyToOne
	var invitation: Invitation
) {

	@PrePersist
	fun onPrePersist() {
		this.createdDate = Date()
	}

}