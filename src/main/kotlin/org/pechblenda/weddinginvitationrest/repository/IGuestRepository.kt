package org.pechblenda.weddinginvitationrest.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import org.pechblenda.weddinginvitationrest.entity.Guest

import java.util.UUID

interface IGuestRepository: JpaRepository<Guest, UUID> {

	@Query(
		"select guest from Guest guest " +
		"inner join guest.invitation invitation " +
		"where upper(invitation.guestOf) like upper(:guestOf) and " +
		"upper(invitation.familyName) like upper(:familyName) and " +
		"CAST(guest.status as text) like :guestStatus " +
		"order by guest.createdDate"
	)
	fun findAllLikeGuestOfAndFamilyName(
		guestOf: String,
		familyName: String,
		guestStatus: String
	): List<Guest>

	@Query(
		"select guest from Guest guest where guest.status = 1"
	)
	fun findAllByStatusNotConfirm(): List<Guest>

}
