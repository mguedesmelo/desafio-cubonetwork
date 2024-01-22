package com.desafio.cubonetwork.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cubonetwork.model.Participant;
import com.desafio.cubonetwork.model.Participation;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {
	List<Participation> findAllByParticipant(Participant participant);
}
