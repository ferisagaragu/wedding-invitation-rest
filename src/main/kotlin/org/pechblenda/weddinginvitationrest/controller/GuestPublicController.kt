package org.pechblenda.weddinginvitationrest.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.GetMapping

import org.pechblenda.service.Request
import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.weddinginvitationrest.service.`interface`.IGuestService

import javax.servlet.http.HttpServletResponse

@CrossOrigin(methods = [
	RequestMethod.GET,
	RequestMethod.PATCH
])
@RestController
@RequestMapping(name = "Public Guest", value = ["/rest/public/guests"])
class GuestPublicController(
	private val guestService: IGuestService,
	private val httpExceptionResponse: HttpExceptionResponse
) {

	@RequestMapping("/not-response")
	fun notResponse(httpServletResponse: HttpServletResponse): String {
		return guestService.getNotResponse(httpServletResponse)
	}

	@RequestMapping("/alpha-list")
	fun getAlphaList(httpServletResponse: HttpServletResponse): String {
		return guestService.getAlphaList(httpServletResponse)
	}

	@GetMapping("/validate-ticket/{guestTime}")
	fun validateTicket(
		@PathVariable guestTime: Long
	): ResponseEntity<Any> {
		return try {
			guestService.validateTicket(guestTime)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

	@PatchMapping("/change-status")
	fun changeGuestsStatus(
		@RequestBody request: Request
	): ResponseEntity<Any> {
		return try {
			guestService.changeGuestsStatus(request)
		} catch (e: ResponseStatusException) {
			httpExceptionResponse.error(e)
		}
	}

}