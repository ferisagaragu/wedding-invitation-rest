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

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Guest", value = ["/rest/invitations"])
class InvitationController(
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

}