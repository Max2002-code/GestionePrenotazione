package service;

import entity.Postazione;
import entity.Utente;
import entity.prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PostazioneRepository;
import repository.PrenotazioneRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PrenotazioneService {

    @Autowired
    private PrenotazioneRepository prenotazioneRepository;

    public prenotazione creaPrenotazione(Postazione postazione, String utente, LocalDate dataPrenotazione) {
        if (isPostazioneLibera(postazione, dataPrenotazione)) {
            prenotazione prenotazione = new prenotazione();
            prenotazione.setPostazione(postazione);
            prenotazione.setUtente(utente);
            prenotazione.setDataPrenotazione(dataPrenotazione);
            return prenotazioneRepository.save(prenotazione);
        }
        return null;
    }

    public boolean isPostazioneLibera(Postazione postazione, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByPostazioneAndDataPrenotazione(postazione, dataPrenotazione).isEmpty();
    }

    public boolean isUtenteLibero(Utente utente, LocalDate dataPrenotazione) {
        return prenotazioneRepository.findByUtenteAndDataPrenotazione(utente, dataPrenotazione).isEmpty();
    }

    public Optional<prenotazione> leggiPrenotazione(Long id) {
        return prenotazioneRepository.findById(id);
    }

    public List<prenotazione> leggiTuttePrenotazioni() {
        return prenotazioneRepository.findAll();
    }

    public prenotazione aggiornaPrenotazione(Long id, Postazione nuovaPostazione, String nuovoUtente, LocalDate nuovaDataPrenotazione) {
        Optional<prenotazione> prenotazioneOpt = leggiPrenotazione(id);
        if (prenotazioneOpt.isPresent()) {
            prenotazione prenotazione = prenotazioneOpt.get();
            if (isPostazioneLibera(nuovaPostazione, nuovaDataPrenotazione)) {
                prenotazione.setPostazione(nuovaPostazione);
                prenotazione.setUtente(nuovoUtente);
                prenotazione.setDataPrenotazione(nuovaDataPrenotazione);
                return prenotazioneRepository.save(prenotazione);
            }
        }
        return null;
    }

    public boolean eliminaPrenotazione(Long id) {
        if (prenotazioneRepository.existsById(id)) {
            prenotazioneRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Service
    public class PostazioneService {

        @Autowired
        private PostazioneRepository postazioneRepository;

        public List<Postazione> cercaPostazioni(String tipo, String città) {
            return postazioneRepository.findByTipoAndEdificio_Città(tipo, città);
        }
    }
}