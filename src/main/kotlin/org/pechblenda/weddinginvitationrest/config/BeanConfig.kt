package org.pechblenda.weddinginvitationrest.config

import org.pechblenda.auth.AuthController
import org.pechblenda.doc.Documentation
import org.pechblenda.doc.entity.ApiInfo
import org.pechblenda.doc.entity.Credential
import org.pechblenda.weddinginvitationrest.controller.GuestPublicController
import org.pechblenda.weddinginvitationrest.controller.InvitationController

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration

@Configuration
@ComponentScan("org.pechblenda.bean")
class BeanConfig {

	@Bean
	fun documentation(): Documentation {
		val bodyRequest = LinkedHashMap<String, String>()
		bodyRequest["userName"] = "ferisagaragu@gmail.com"
		bodyRequest["password"] = "fernnypay95"

		return Documentation(
			ApiInfo(
				title = "Wedding App",
				description = "Servicios REST de mi boda",
				iconUrl = "",
				version = "0.0.1",
				credentials = listOf(
					Credential(
						name = "User Root",
						endPoint = "http://localhost/rest/auth/sign-in",
						bodyRequest = bodyRequest,
						tokenMapping = "data.session.token"
					)
				)
			),
			AuthController::class,
			GuestPublicController::class,
			InvitationController::class
		)
	}

}