package tn.esprit.spring.kaddem.entities;

import lombok.*;

import java.io.Serializable;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
@Table( name = "DetailEquipe")
public class DetailEquipe implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idDetailEquipe;
    private Integer salle;
    private String thematique;


    public DetailEquipe(Integer salle, String thematique) {
        super();
        this.salle = salle;
        this.thematique = thematique;
    }


}
