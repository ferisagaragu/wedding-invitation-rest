package org.pechblenda.weddinginvitationrest.entity

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.Table
import javax.persistence.ManyToMany

import java.util.Date
import java.util.UUID

@Entity
@Table(name = "events")
class Event (
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	var uuid: UUID,
	var name: String,
	var description: String,
	var urlDataBase: String,
	var endPointInvitation: String,
	var primaryColor: String,
	var accentColor: String,
	var customTicket: Boolean,
	var endDate: Date?,
	var createDate: Date?,

	@ManyToMany
	var users: MutableList<User>?
) {

	constructor(): this(
		uuid = UUID.randomUUID(),
		name = "",
		description = "",
		urlDataBase = "",
		endPointInvitation = "",
		primaryColor = "",
		accentColor = "",
		customTicket = false,
		endDate = null,
		createDate = null,
		users = null
	)

	@PrePersist
	fun onPrePersist() {
		this.createDate = Date()
	}

}