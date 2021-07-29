package org.pechblenda.weddinginvitationrest.service.`interface`

import org.springframework.http.ResponseEntity

interface IGuestService {
	fun findAllLikeGuestOfAndFamilyName(guestOf: String?, familyName: String?, guestStatus: String?): ResponseEntity<Any>
}