package entity;

import javax.persistence.*;
import java.time.LocalDate;
import lombok.Data;

@Entity
@Data
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = { "postazione_id", "dataPrenotazione" }) })
public class prenotazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "postazione_id")
    private Postazione postazione;

    @ManyToOne
    @JoinColumn(name = "utente_username")
    private Utente utente;

    private LocalDate dataPrenotazione;
}