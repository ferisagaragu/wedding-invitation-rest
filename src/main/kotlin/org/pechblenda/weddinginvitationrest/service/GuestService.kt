package org.pechblenda.weddinginvitationrest.service

import org.pechblenda.exception.BadRequestException
import org.pechblenda.service.Response
import org.pechblenda.weddinginvitationrest.repository.IGuestRepository
import org.pechblenda.weddinginvitationrest.service.`interface`.IGuestService
import org.pechblenda.service.Request
import org.pechblenda.service.helper.Validation
import org.pechblenda.service.helper.ValidationType
import org.pechblenda.service.helper.Validations
import org.pechblenda.weddinginvitationrest.entity.Guest
import org.pechblenda.weddinginvitationrest.enum.GuestStatus

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

import java.util.UUID
import java.util.Date
import javax.servlet.http.HttpServletResponse
import org.pechblenda.weddinginvitationrest.repository.IInvitationRepository

@Service
class GuestService(
	private val guestRepository: IGuestRepository,
	private val invitationRepository: IInvitationRepository,
	private val response: Response
): IGuestService {

	@Transactional(readOnly = true)
	override fun getNotResponse(httpServletResponse: HttpServletResponse): String {
		var data = this.guestRepository.findAllByStatusNotConfirm()
		var guest = this.guestRepository.findAll()
		var out = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/" +
				"bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9Jv" +
				"oRxT2MZw1T\" crossorigin=\"anonymous\">" +
				"<div class=\"p-4\"><h5 class=\"text-danger\">Faltan ${data.size} invitado(s) por confirmar</h5>" +
				"<h5 class=\"text-info\">Invidos confirmados ${guest.size - data.size}</h5></div><ol>"

		data.forEach { guest -> out += "<li>${guest.name}</li> <hr> " }

		out += "</ol>"

		return response.html(httpServletResponse, out)
	}

	@Transactional(readOnly = true)
	override fun getAlphaList(httpServletResponse: HttpServletResponse): String {
		var data = this.invitationRepository.findAllOrderByFamilies()
		var out = "<link rel=\"stylesheet\" href=\"https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/" +
				"bootstrap.min.css\" integrity=\"sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9Jv" +
				"oRxT2MZw1T\" crossorigin=\"anonymous\">" +
				"<ol>"

		data.forEach { data ->
			out += "<li><b>${data.familyName.replace("*", "").replace("_m", "")}</b></li> <hr>"
			out += "<ul>"

			guestRepository.findAllByInvitationUuid(data.uuid).forEach { guest ->
				out += "<li>${guest.name}</li>"
			}

			out += "</ul> <br><br>"
		}

		out += "</ol>"

		return response.html(httpServletResponse, out)
	}

	@Transactional(readOnly = true)
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

	@Transactional
	override fun validateTicket(guestTime: Long): ResponseEntity<Any> {
		val guestFind = guestRepository.findAll().find { guest ->
			guest.createdDate.time == guestTime
		} ?: return response.ok(2)

		if (guestFind.status == GuestStatus.ACCEPT) {
			guestFind.status = GuestStatus.USED
			return this.response.ok(0)
		}

		return this.response.ok(1)
	}

	@Transactional
	override fun changeGuestsStatus(request: Request): ResponseEntity<Any> {
		val guests = request.toList<Guest>(
			"guests",
			Guest::class,
			Validations(
				Validation(
					"status",
					"Ops estatus no es requerido",
					ValidationType.EXIST,
					ValidationType.NOT_NULL,
					ValidationType.NOT_BLANK
				)
			)
		)

		guests.forEach { guest ->
			val guestFind = guestRepository.findById(guest.uuid).orElseThrow {
				BadRequestException("Ops no se encuentra el invitado")
			}

			guestFind.confirmDate = Date()
			guestFind.status = if (guest.status == GuestStatus.ACCEPT)
				GuestStatus.ACCEPT else
				GuestStatus.DENY
		}

		return response.ok()
	}

	@Transactional
	override fun deleteGuestByUuid(guestUuid: UUID): ResponseEntity<Any> {
		val deleteGuest = guestRepository.findById(guestUuid).orElseThrow {
			BadRequestException("Upss no se encuentra el invitado")
		}

		guestRepository.delete(deleteGuest)

		return response.ok()
	}

}