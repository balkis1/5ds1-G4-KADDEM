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
@Table( name = "Universite")
public class Universite implements Serializable{
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer idUniv;
    private String nomUniv;


    public Universite(String nomUniv) {
        super();
        this.nomUniv = nomUniv;
    }




    public Integer getIdUniv() {
        return idUniv;
    }
    public void setIdUniv(Integer idUniv) {
        this.idUniv = idUniv;
    }
    public String getNomUniv() {
        return nomUniv;
    }
    public void setNomUniv(String nomUniv) {
        this.nomUniv = nomUniv;
    }

}
