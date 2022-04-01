package org.pechblenda.weddinginvitationrest.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

class GuestGenerator {

	public static void main(String[] args) throws JsonProcessingException {
		String data = "SR. ANGEL HURTADO BAUTISTA Y FAMILIA,5-" +
				"SR. JOSE HURTADO BAUTISTA Y FAMILIA,3-" +
				"SR. CESAR HURTADO ESLAVA ,2-" +
				"SRA. EVA BAUTISTA OROZCO,1-" +
				"SRA. MERCEDES VARGAS IBARRA,1-" +
				"SRA. TERESA VARGAS IBARRA ,2-" +
				"SR. LEOBARDO HECTOR GALINDO GUERRERO Y ESPOSA,2-" +
				"SRA. ESTHER VARGAS IBARRA ,2-" +
				"SR. ARMANDO  DIAZ VARGAS Y ESPOSA,2-" +
				"DR. ENRIQUE GARCIA MARTINEZ Y ESPOSA ,2-" +
				"SRA. MARGARITA CONTRERAS DORANTES Y FAMILIA,3-" +
				"DR. EDGAR BALBUENA HERRERA Y ESPOSA,2-" +
				"DR. ROBERTO ALFREDO RUIZ RUIZ  Y ESPOSA,2-" +
				"SRA. VICTORIA CAMPOS GONZALEZ Y FAMILIA,4-" +
				"SR. JOSE ANDRES SERNA SALVADOR Y FAMILIA,5-" +
				"SR. EDGAR VARGAS CAMPOS Y ESPOSA,2-" +
				"SR. JOSAFAT VARGAS MUÑOZ Y FAMILIA,3-" +
				"SR. HECTOR VARGAS MARTINEZ Y FAMILIA,3-" +
				"SR. RAMIRO BURGOS GOMEZ Y ESPOSA,2-" +
				"SR. PEDRO CLAVEL RODRIGUEZ Y FAMILIA,3-" +
				"SRA. XOCHIL GAYTAN VICARIO,2-" +
				"PROFR. EVERARDO PAREDES HERNANDEZ Y ESPOSA,3-" +
				"C. FLORENCIO MEJIA VAZQUEZ Y ACOMPAÑANTE,2-" +
				"SRA. MARLÉN ONOFRE Y SR. ANTONIO LOPEZ,2-" +
				"SRA. RUFINA VARGAS Y ACOMPAÑANTE,2-" +
				"SRITA. DIANA HERNANDEZ Y ACOMPAÑANTE,2-" +
				"C. JHONATAN VAZQUEZ Y NELLY BELLO,2-" +
				"C. JONADAD FIGUEROA VAZQUEZ,2-" +
				"SR. JUAN CARLOS VAZQUEZ PIEDRA  Y FAMILIA,6-" +
				"SRA. DELIA AZUCENA SANTIAGO MEJIA Y FAMILIA,4-" +
				"SRA. EVELYN ANDREA  GARCIA SANTIAGO Y ESPOSO,2-" +
				"SRA. ANGELA VAZQUEZ JIMENEZ,1-" +
				"SRA. MARIA DE LOS ANGELES SANTIAGO MEJIA Y FAMILIA,4-" +
				"SRA. DELIA MEJIA GARCIA,1-" +
				"SR. MIGUEL ANGEL SANTIAGO MEJIA Y FAMILIA,3-" +
				"SRA. ARACELY GARCIA ,2-" +
				"SRA. MAYRA ADAME DE LOS SANTOS,2-" +
				"SR. ALVARO BELLO GONZALEZ Y FAMILIA,5-" +
				"SRA. MARIA FRANCISCA MUJICA VALDERRAMA ,3-" +
				"SR. RAUL CRUZ SAAVEDRA Y FAMILIA,3-" +
				"SR. CRECENCIANO FLORES VINALAY Y ESPOSA,2-" +
				"SRA. AMPARO JIMENEZ Y ACOMPAÑANTE,2-" +
				"SRA. ADRIANA MELO PALMA Y ACOMPAÑANTE,2-" +
				"SRITA . YAZMIN RODRIGUEZ CASTILLO,1-" +
				"PADRE GREGORIO FAJARDO RIOS,4-" +
				"SR. PIERRE CASTILO GARCIA Y ESPOSA,2-" +
				"SR. JAVIER MEJIA GARCIA Y ESPOSA ,2-" +
				"SRA. MINERVA PALACIOS JIMENEZ,2-" +
				"SRA. ENEYDA MEJIA VAZQUEZ ,2-" +
				"SR. ALEX SAMUEL ALVARADO CRUZ Y ESPOSA,2-" +
				"C. JONADAD FIGUEROA VAZQUEZ,2-" +
				"SR. AMADO MIGUEL NAJERA BAUTISTA Y FAMILIA,3-" +
				"SR. ALEJANDRO VAZQUEZ VAZQUEZ Y FAMILIA,4-" +
				"LIC. AMADO MIGUEL NAJERA IBARRA ,1-" +
				"FRANCISCO TADEO MEJIA NAJERA,1-" +
				"SRA. SANDRA LUZ NAJERA  MENDEZ,1";

		String[] info = data.split("-");
		LinkedHashMap out = new LinkedHashMap();

		for (int i = 0; i < info.length; i++) {
			LinkedHashMap main = new LinkedHashMap();
			ArrayList guests = new ArrayList();
			String uuid = UUID.randomUUID().toString();
			String familyName = info[i].split(",")[0];
			int adult = Integer.parseInt(info[i].split(",")[1]);
			/*int child;

			try {
				child = Integer.parseInt(info[i].split(",")[2]);
			} catch (Exception e) {
				child = 0;
			}*/

			main.put("uuid", uuid);
			main.put("send", false);
			main.put("familyName", familyName);

			for (int i1 = 0; i1 < adult; i1++) {
				LinkedHashMap ticket = new LinkedHashMap();
				ticket.put("name", "BOLETO PERSONAL " + (i1 + 1));
				ticket.put("status", 0);
				guests.add(ticket);
			}

			/*for (int i1 = 0; i1 < child; i1++) {
				LinkedHashMap ticket = new LinkedHashMap();
				ticket.put("name", "Boleto de niño " + (i1 + 1));
				ticket.put("status", 0);
				guests.add(ticket);
			}*/

			main.put("guests", guests);
			out.put(uuid, main);
			System.out.println("https://isnela-and-fredy.web.app/#/invitation/" + uuid);
		}

		String json = new ObjectMapper().writeValueAsString(out);
		System.out.println(json);
	}

}