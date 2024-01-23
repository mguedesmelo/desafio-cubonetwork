package com.desafio.cubonetwork.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.desafio.cubonetwork.model.Participant;

public interface ParticipantRepository extends JpaRepository<Participant, Long> {

}
