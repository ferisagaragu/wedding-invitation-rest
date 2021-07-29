package org.pechblenda.weddinginvitationrest.entity

import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.OneToMany

import java.util.Date
import java.util.UUID

@Entity
@Table(name = "invitation")
class Invitation(
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var uuid: UUID,
	var createdDate: Date,
	var familyName: String,
	var guestOf: String,

	@OneToMany(mappedBy = "invitation")
	var guests: List<Guest>
) {

	@PrePersist
	fun onPrePersist() {
		this.createdDate = Date()
	}

}