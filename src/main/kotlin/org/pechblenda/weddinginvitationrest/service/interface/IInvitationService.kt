package org.pechblenda.weddinginvitationrest.service.`interface`

import java.util.UUID

import org.springframework.http.ResponseEntity

interface IInvitationService {
	fun findAllLikeGuestOf(guestOf: String?): ResponseEntity<Any>
	fun generateTicket(invitationUuid: UUID): ResponseEntity<Any>
	fun generateTicketFromNames(guests: ArrayList<String>): ResponseEntity<Any>
}