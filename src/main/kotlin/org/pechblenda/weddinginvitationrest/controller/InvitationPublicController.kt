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
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody

import java.util.UUID

@CrossOrigin(methods = [
	RequestMethod.GET,
	RequestMethod.POST
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

	@PostMapping
	fun generateTicketFromNames(
		@RequestBody guests: ArrayList<String>
	): ResponseEntity<Any> {
		return try {
			invitationService.generateTicketFromNames(guests)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}