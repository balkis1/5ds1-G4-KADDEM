package tn.esprit.spring.kaddem.entities;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@ToString
public class DetailEquipeDTO implements Serializable{
    private Integer salle;
    private String thematique;


    public DetailEquipeDTO(Integer salle, String thematique) {
        super();
        this.salle = salle;
        this.thematique = thematique;
    }


}
