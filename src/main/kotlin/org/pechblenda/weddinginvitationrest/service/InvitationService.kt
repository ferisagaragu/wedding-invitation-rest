package org.pechblenda.weddinginvitationrest.service

import org.pechblenda.exception.BadRequestException
import org.pechblenda.service.Response
import org.pechblenda.util.Type
import org.pechblenda.weddinginvitationrest.repository.IInvitationRepository
import org.pechblenda.weddinginvitationrest.service.`interface`.IInvitationService
import org.pechblenda.util.Report
import org.pechblenda.weddinginvitationrest.enum.GuestStatus

import org.springframework.data.domain.PageRequest
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.core.io.ClassPathResource
import org.springframework.transaction.annotation.Transactional

import java.util.UUID

@Service
class InvitationService(
	private val invitationRepository: IInvitationRepository,
	private val response: Response,
	private val report: Report
): IInvitationService {

	@Transactional(readOnly = true)
	override fun findAllLikeGuestOf(guestOf: String?): ResponseEntity<Any> {
		if (guestOf != null && Type.isInteger(guestOf)) {
			 val pageable = PageRequest.of(0, guestOf.toInt())

			return response.toListMap(invitationRepository.findAll(pageable).toList()).ok()
		}

		if (guestOf != null && Type.isUUID(guestOf)) {
			val invitation = invitationRepository.findById(
				UUID.fromString(guestOf)
			).orElseThrow{
				BadRequestException("Ops no se encuentra la invitación")
			}

			return response.toMap(invitation).ok()
		}

		val invitationOut = invitationRepository.findAllLikeGuestOf("${guestOf?: ""}%")

		if (invitationOut.isEmpty()) {
			throw BadRequestException("Ops no se encontró ninguna invitación")
		}

		return response.toListMap(invitationOut).ok()
	}

	@Transactional(readOnly = true)
	override fun generateTicket(invitationUuid: UUID): ResponseEntity<Any> {
		val invitation = invitationRepository.findById(invitationUuid).orElseThrow {
			BadRequestException("Ops no se encontró la invitación")
		}
		val parameters = mutableMapOf<String, Any>()
		val fields = mutableListOf<MutableMap<String, Any>>()

		invitation.guests?.forEach { guest ->
			if (guest.status == GuestStatus.ACCEPT) {
				val guestData = mutableMapOf<String, Any>()
				guestData["name"] = "${guest.name} ${guest.surname} ${guest.motherSurname}"
				guestData["authUrl"] = guest.createdDate.time.toString()
				fields.add(guestData)
			}
		}
		parameters["background"] = ClassPathResource("template/report/background.png").inputStream

		return response.file(
			"application/pdf",
			"boletos_${
				invitation.familyName.lowercase().replace(" ", "_")
			}.pdf",
			report.exportPdf(
				ClassPathResource("template/report/ticket.jrxml").inputStream,
				parameters,
				fields.toMutableList()
			)
		)

	}

	override fun generateTicketFromNames(
		guests: ArrayList<String>
	): ResponseEntity<Any> {
		val parameters = mutableMapOf<String, Any>()
		val fields = mutableListOf<MutableMap<String, Any>>()

		guests?.forEach { guest ->
			val guestData = mutableMapOf<String, Any>()
			guestData["name"] = guest
			fields.add(guestData)
		}

		parameters["background"] = ClassPathResource(
			"template/report/background-isnela-berrocal.png"
		).inputStream

		return response.file(
			"application/pdf",
			"boletos.pdf",
			report.exportPdf(
				ClassPathResource(
					"template/report/ticket-isnela-berrocal.jrxml"
				).inputStream,
				parameters,
				fields.toMutableList()
			)
		)
	}

	override fun generateTicketFromNamesLizbeth(
		guests: ArrayList<String>
	): ResponseEntity<Any> {
		val parameters = mutableMapOf<String, Any>()
		val fields = mutableListOf<MutableMap<String, Any>>()

		guests?.forEach { guest ->
			val guestData = mutableMapOf<String, Any>()
			guestData["name"] = guest.uppercase()
			fields.add(guestData)
		}

		parameters["background"] = ClassPathResource(
			"template/report/background-lizbeth-francisco.png"
		).inputStream

		return response.file(
			"application/pdf",
			"boletos.pdf",
			report.exportPdf(
				ClassPathResource(
					"template/report/lizbeth-francisco.jrxml"
				).inputStream,
				parameters,
				fields.toMutableList()
			)
		)
	}

	override fun generateTicketFromNamesAmerica(guests: ArrayList<String>): ResponseEntity<Any> {
		val parameters = mutableMapOf<String, Any>()
		val fields = mutableListOf<MutableMap<String, Any>>()

		guests?.forEach { guest ->
			val guestData = mutableMapOf<String, Any>()
			guestData["name"] = guest.uppercase()
			fields.add(guestData)
		}

		parameters["background"] = ClassPathResource(
			"template/report/background-jessica-americal.png"
		).inputStream

		return response.file(
			"application/pdf",
			"boletos.pdf",
			report.exportPdf(
				ClassPathResource(
					"template/report/jessica-america.jrxml"
				).inputStream,
				parameters,
				fields.toMutableList()
			)
		)
	}

}