package controller;

import java.time.LocalDate;
import entity.Utente;
import entity.Postazione;
import entity.prenotazione;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import service.PrenotazioneService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/prenotazioni")
public class PrenotazioneController {

    @Autowired
    private PrenotazioneService prenotazioneService;

    @PostMapping
    public ResponseEntity<prenotazione> creaPrenotazione(@RequestBody PrenotazioneRequest request) {
        prenotazione prenotazione = prenotazioneService.creaPrenotazione(
                request.getPostazione(),
                request.getUtente(),
                request.getDataPrenotazione()
        );
        return prenotazione != null ? ResponseEntity.ok(prenotazione) : ResponseEntity.badRequest().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<prenotazione> leggiPrenotazione(@PathVariable Long id) {
        Optional<prenotazione> prenotazione = prenotazioneService.leggiPrenotazione(id);
        return prenotazione.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<prenotazione>> leggiTuttePrenotazioni() {
        return ResponseEntity.ok(prenotazioneService.leggiTuttePrenotazioni());
    }

    @PutMapping("/{id}")
    public ResponseEntity<prenotazione> aggiornaPrenotazione(@PathVariable Long id, @RequestBody PrenotazioneRequest request) {
        prenotazione prenotazione = prenotazioneService.aggiornaPrenotazione(
                id,
                request.getPostazione(),
                request.getUtente(),
                request.getDataPrenotazione()
        );
        return prenotazione != null ? ResponseEntity.ok(prenotazione) : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminaPrenotazione(@PathVariable Long id) {
        boolean deleted = prenotazioneService.eliminaPrenotazione(id);
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    @RestController
    @RequestMapping("/api/postazioni")
    public class PostazioneController {

        @Autowired
        private PrenotazioneService.PostazioneService postazioneService;

        @GetMapping("/cerca")
        public ResponseEntity<List<Postazione>> cercaPostazioni(@RequestParam String tipo, @RequestParam String città) {
            List<Postazione> postazioni = postazioneService.cercaPostazioni(tipo, città);
            return ResponseEntity.ok(postazioni);
        }
    }
}

