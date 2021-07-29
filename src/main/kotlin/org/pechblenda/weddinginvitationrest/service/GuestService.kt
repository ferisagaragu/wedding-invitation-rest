package org.pechblenda.weddinginvitationrest.service

import org.pechblenda.service.Response
import org.pechblenda.weddinginvitationrest.repository.IGuestRepository
import org.pechblenda.weddinginvitationrest.service.`interface`.IGuestService

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class GuestService(
	private val guestRepository: IGuestRepository,
	private val response: Response
): IGuestService {

	override fun findAllLikeGuestOfAndFamilyName(
		guestOf: String?,
		familyName: String?,
		guestStatus: String?
	): ResponseEntity<Any> {
		return response.toListMap(
			guestRepository.findAllLikeGuestOfAndFamilyName(
				"${guestOf?: ""}%",
				"${familyName?: ""}%",
				"${guestStatus?: ""}%"
			)
		).ok()
	}

}