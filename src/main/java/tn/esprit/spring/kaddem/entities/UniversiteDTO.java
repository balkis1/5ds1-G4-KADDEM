package tn.esprit.spring.kaddem.entities;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Data
@Builder
@ToString
public class UniversiteDTO {
    private String nomUniv;
    public UniversiteDTO(String nomun) {
        super();
        this.nomUniv = nomun;
    }
}
