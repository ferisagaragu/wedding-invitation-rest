package org.pechblenda.weddinginvitationrest.repository

import org.pechblenda.weddinginvitationrest.entity.Invitation

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

import java.util.UUID

interface IInvitationRepository: JpaRepository<Invitation, UUID> {

	@Query(
		"select invitation from Invitation invitation " +
		"where upper(invitation.guestOf) like upper(:guestOf)"
	)
	fun findAllLikeGuestOf(guestOf: String): List<Invitation>

	@Query(
		"select invitation from Invitation invitation " +
		"order by invitation.familyName"
	)
	fun findAllOrderByFamilies(): List<Invitation>

}
