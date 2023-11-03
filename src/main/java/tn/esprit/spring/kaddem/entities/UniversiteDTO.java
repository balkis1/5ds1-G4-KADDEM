package tn.esprit.spring.kaddem.entities;
import lombok.*;


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
