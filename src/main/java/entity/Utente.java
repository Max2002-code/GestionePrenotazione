package entity;

import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Utente {
    @Id
    private String username;

    private String nomeCompleto;
    private String email;
}