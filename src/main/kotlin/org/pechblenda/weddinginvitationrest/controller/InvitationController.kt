package org.pechblenda.weddinginvitationrest.controller

import org.pechblenda.exception.HttpExceptionResponse
import org.pechblenda.weddinginvitationrest.service.`interface`.IInvitationService

import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Guest", value = ["/rest/invitations"])
class InvitationController(
	private val invitationService: IInvitationService,
	private val httpExceptionResponse: HttpExceptionResponse
)