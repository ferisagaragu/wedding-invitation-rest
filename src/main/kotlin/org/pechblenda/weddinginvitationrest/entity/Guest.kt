package org.pechblenda.weddinginvitationrest.entity

import org.pechblenda.service.annotation.Key
import org.pechblenda.service.enum.DefaultValue

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
import org.pechblenda.weddinginvitationrest.enum.GuestStatus

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

	@Column(columnDefinition = "int default 0")
	var status: GuestStatus,

	@ManyToOne
	var invitation: Invitation
) {

	@PrePersist
	fun onPrePersist() {
		this.createdDate = Date()
	}

	@Key(name = "familyName", autoCall = true, defaultNullValue = DefaultValue.NULL)
	fun familyName(): String {
		return invitation.familyName
	}

	@Key(name = "status", autoCall = true, defaultNullValue = DefaultValue.NUMBER)
	fun status(): Int {
		return status.ordinal
	}

}