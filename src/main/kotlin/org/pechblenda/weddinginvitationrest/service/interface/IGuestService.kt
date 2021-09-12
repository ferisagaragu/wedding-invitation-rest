package org.pechblenda.weddinginvitationrest.service.`interface`

import org.springframework.http.ResponseEntity

import java.util.UUID

import javax.servlet.http.HttpServletResponse

import org.pechblenda.service.Request

interface IGuestService {
	fun findAllLikeGuestOfAndFamilyName(guestOf: String?, familyName: String?, guestStatus: String?): ResponseEntity<Any>
	fun validateTicket(response: HttpServletResponse, guestUuid: UUID): String
	fun changeGuestsStatus(request: Request): ResponseEntity<Any>
	fun deleteGuestByUuid(guestUuid: UUID): ResponseEntity<Any>
}