package org.pechblenda.weddinginvitationrest.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Entity
import javax.persistence.Table
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.PrePersist
import javax.persistence.OneToMany

import org.pechblenda.service.Response
import org.pechblenda.service.annotation.Key
import org.pechblenda.service.enum.DefaultValue
import org.pechblenda.service.helper.ResponseList

import java.util.Date
import java.util.UUID
import org.pechblenda.weddinginvitationrest.enum.GuestStatus

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
	var guests: MutableList<Guest>?
) {

	@PrePersist
	fun onPrePersist() {
		this.createdDate = Date()
	}

	@Key(name = "guests", autoCall = true, defaultNullValue = DefaultValue.JSON_ARRAY)
	fun guests(): ResponseList? {
		return guests?.let { Response().toListMap(it) }
	}

	@Key(name = "send", autoCall = true, defaultNullValue = DefaultValue.BOOLEAN)
	fun send(): Boolean {
		var out = false

		guests?.forEach { guest ->
			if (
				guest.status == GuestStatus.ACCEPT ||
				guest.status == GuestStatus.DENY
			) {
				out = true;
			}
		}

		return out
	}

}