package com.desafio.cubonetwork.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.repository.ParticipantRepository;

@Service
public class ParticipantService {
	@Autowired
	private ParticipantRepository participantRepository;

	public List<Participant> findAll() {
		return this.participantRepository.findAll();
	}

	public Participant save(Participant participant) {
		return this.participantRepository.save(participant);
	}

	public void remove(Participant participant) {
		this.participantRepository.delete(participant);
	}

//	public List<Participation> findAllByParticipant(Participant participant) {
//		return this.participantRepository.findAllByParticipant(participant);
//	}
}
