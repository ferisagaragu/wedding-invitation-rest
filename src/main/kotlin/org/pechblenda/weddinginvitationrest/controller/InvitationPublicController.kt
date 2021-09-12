package org.pechblenda.weddinginvitationrest.controller

import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.weddinginvitationrest.service.`interface`.IInvitationService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

import java.util.UUID
import javax.servlet.http.HttpServletResponse

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Invitation", value = ["/rest/public/invitations"])
class InvitationPublicController(
	private val invitationService: IInvitationService,
	private val httpExceptionResponse: HttpExceptionResponse
) {

	@GetMapping(value = ["", "/{guestOf}"])
	fun findAllLikeGuestOfAndFamilyName(
		@PathVariable guestOf: String?
	): ResponseEntity<Any> {
		return try {
			invitationService.findAllLikeGuestOf(guestOf)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

	@GetMapping("/ticket/{invitationUuid}")
	fun generateTicket(
		@PathVariable invitationUuid: UUID
	): ResponseEntity<Any> {
		return try {
			invitationService.generateTicket(invitationUuid)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}