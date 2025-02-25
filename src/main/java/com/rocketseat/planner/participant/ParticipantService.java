package com.rocketseat.planner.participant;

import com.rocketseat.planner.trip.Trip;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ParticipantService {

    @Autowired
    private ParticipantRepository repository;

    public void registerParticipantsToEvent(List<String> participantsToInvite, Trip trip) {

        //pegando todos os emails , transforma em stream e map pra pegar
        //registrei todos os participantes no evento
        List<Participant> participants = participantsToInvite.stream().map(email -> new Participant(email, trip)).toList();
        this.repository.saveAll(participants);

        System.out.println(participants.get(0).getId());
    }

    //recuperar os participantes de uma viagem e mandar um email para eles
    public void triggerConfirmationEmailToParticipants(UUID tripId) {
    }

    ;

    public void triggerConfirmationEmailToParticipant(String email) {
    }

    ;

    public ParticipantCreateResponse registerParticipantToEvent(String email, Trip trip) {
        Participant newParticipant = new Participant(email, trip);
        this.repository.save(newParticipant);

        return new ParticipantCreateResponse(newParticipant.getId());
    }


    public List<ParticipantData> getAllParticipantsFromEvent(UUID tripId) {
        return this.repository.findByTripId(tripId).stream().map(participant -> new ParticipantData(participant.getId(), participant.getName(), participant.getEmail(), participant.getIsConfirmed())).toList();
    }


}