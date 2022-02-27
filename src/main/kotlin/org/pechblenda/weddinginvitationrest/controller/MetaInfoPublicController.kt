package org.pechblenda.weddinginvitationrest.controller

import org.pechblenda.service.Response

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(methods = [
	RequestMethod.GET
])
@RestController
@RequestMapping(name = "Invitation", value = ["/rest/public/meta-info"])
class MetaInfoPublicController {

	@GetMapping
	fun getVersion(): ResponseEntity<Any> {
		return Response().ok("1.0.0")
	}

}