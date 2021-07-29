package org.pechblenda.weddinginvitationrest.controller

import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.weddinginvitationrest.service.`interface`.IGuestService

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Guest", value = ["/rest/guests"])
class GuestController(
	private val guestService: IGuestService,
	private val httpExceptionResponse: HttpExceptionResponse
) {

	@GetMapping(value = ["", "/{guestOf}"])
	fun findAllLikeGuestOfAndFamilyName(
		@PathVariable guestOf: String?,
		@RequestParam(name = "family") familyName: String?,
		@RequestParam(name = "status") guestStatus: String?
	): ResponseEntity<Any> {
		return try {
			guestService.findAllLikeGuestOfAndFamilyName(guestOf, familyName, guestStatus)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}