package org.pechblenda.weddinginvitationrest.service

import org.pechblenda.service.Response
import org.pechblenda.weddinginvitationrest.repository.IInvitationRepository
import org.pechblenda.weddinginvitationrest.service.`interface`.IInvitationService
import org.springframework.http.ResponseEntity

import org.springframework.stereotype.Service

@Service
class InvitationService(
	private val invitationRepository: IInvitationRepository,
	private val response: Response
): IInvitationService {

	override fun findAllLikeGuestOf(guestOf: String?): ResponseEntity<Any> {
		return response.toListMap(invitationRepository.findAllLikeGuestOf("${guestOf?: ""}%")).ok()
	}

}