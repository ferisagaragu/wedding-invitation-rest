package org.pechblenda.weddinginvitationrest.service.`interface`

import org.springframework.http.ResponseEntity

interface IInvitationService {
	fun findAllLikeGuestOf(guestOf: String?): ResponseEntity<Any>
}