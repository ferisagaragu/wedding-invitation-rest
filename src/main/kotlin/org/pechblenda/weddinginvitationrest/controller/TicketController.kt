package org.pechblenda.weddinginvitationrest.controller

import org.pechblenda.service.Response
import org.pechblenda.util.Report

import org.springframework.core.io.ClassPathResource
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

import java.util.UUID

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Client", value = ["/rest/tickets"])
class TicketController(
	private val report: Report,
	private val response: Response
) {

	@GetMapping
	fun findAllCompaniesByUserUuid(): ResponseEntity<Any> {


		val parameters = mutableMapOf<String, Any>()
		val fields = mutableListOf<MutableMap<String, Any>>()

		parameters["background"] = ClassPathResource("template/report/background.png").inputStream
		val data1 = mutableMapOf<String, Any>()
		data1["name"] = "Fernando Isaías García Aguirre"
		val data2 = mutableMapOf<String, Any>()
		data2["name"] = "Ofelia Alejandra Lozano Silva"

		fields.add(data1)
		fields.add(data2)

		return response.file(
			"application/pdf",
			"${UUID.randomUUID()}.pdf",
			report.exportPdf(
				ClassPathResource("template/report/ticket.jrxml").inputStream,
				parameters,
				fields.toMutableList()
			)
		)
	}

}