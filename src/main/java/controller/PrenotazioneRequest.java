package controller;

import entity.Postazione;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class PrenotazioneRequest {
    private Postazione postazione;
    private String utente;
    private LocalDate dataPrenotazione;

}
