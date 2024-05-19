package entity;

import javax.persistence.*;
import lombok.Data;

@Entity
@Data
public class Postazione {
    @Id
    private String codiceUnivoco;

    private String descrizione;

    @Enumerated(EnumType.STRING)
    private TipoPostazione tipo;

    private int numeroMaxOccupanti;

    @ManyToOne
    @JoinColumn(name = "edificio_id")
    private Edificio edificio;
}

enum TipoPostazione {
    PRIVATO, OPENSPACE, SALA_RIUNIONI;
}