package com.desafio.cubonetwork;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.service.ParticipantService;

@SpringBootApplication
public class DesafioCubonetworkApplication implements CommandLineRunner {
	@Autowired
	private ParticipantService participantService;

	public static void main(String[] args) {
		SpringApplication.run(DesafioCubonetworkApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		createMockParticipants();
	}

	private void createMockParticipants() {
		Participant carlosMoura = new Participant("Carlos", "Moura", BigDecimal.valueOf(200));
		carlosMoura.addParticipation(BigDecimal.valueOf(2));
		carlosMoura.addParticipation(BigDecimal.valueOf(3));

		Participant fernandaOliveira = new Participant("Fernanda", "Oliveira", BigDecimal.valueOf(100));
		fernandaOliveira.addParticipation(BigDecimal.valueOf(10));
		fernandaOliveira.addParticipation(BigDecimal.valueOf(5));

		Participant hugoSilva = new Participant("Hugo", "Silva", BigDecimal.valueOf(100));
		hugoSilva.addParticipation(BigDecimal.valueOf(10));
		hugoSilva.addParticipation(BigDecimal.valueOf(5));
		hugoSilva.addParticipation(BigDecimal.valueOf(5));

		Participant elizaSouza = new Participant("Eliza", "Souza", BigDecimal.valueOf(100));
		elizaSouza.addParticipation(BigDecimal.valueOf(20));

		Participant andersonSantos = new Participant("Anderson", "Santos", BigDecimal.valueOf(100));
		andersonSantos.addParticipation(BigDecimal.valueOf(50));
		andersonSantos.addParticipation(BigDecimal.valueOf(-10));

		this.participantService.save(carlosMoura);
		this.participantService.save(fernandaOliveira);
		this.participantService.save(hugoSilva);
		this.participantService.save(elizaSouza);
		this.participantService.save(andersonSantos);
		
//		System.out.println("Participants created");
	}
}
