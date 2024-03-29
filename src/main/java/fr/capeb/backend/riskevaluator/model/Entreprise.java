package fr.capeb.backend.riskevaluator.model;

import fr.capeb.backend.riskevaluator.model.dto.EntrepriseDto;
import fr.capeb.backend.riskevaluator.model.dto.EvaluationDto;
import fr.capeb.backend.riskevaluator.model.dto.PlainEvaluationDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "entreprise")
public class Entreprise {

    @Id
    @Basic
    @Column(name = "nosiret")
    public Long noSiret;

    @Basic
    @Column(name = "nom_entreprise")
    public String nomEntreprise;


    @Basic
    @Column(name = "effectif")
    public Integer effectifEntreprise;

    @Basic
    @Column(name = "anneedecreation")
    public Integer anneeDeCreation;
    @OrderBy("idEvaluation")
    @OneToMany(mappedBy = "entreprise",cascade = CascadeType.ALL)
    private Set<Evaluation> evaluations=new HashSet<>();


    public static Entreprise from(EntrepriseDto entrepriseDto){
        Entreprise entreprise = new Entreprise();
        entreprise.setNomEntreprise(entrepriseDto.getNomEntreprise());
        entreprise.setEffectifEntreprise(entrepriseDto.getEffectifEntreprise());
        Set<PlainEvaluationDto> plainEvaluationDtos = entrepriseDto.getEvaluations();
        Set<Evaluation> evaluations1 = new HashSet<>();
        plainEvaluationDtos.forEach(plainEvaluationDto -> {
                    Evaluation evaluation = new Evaluation();
                    evaluation.setIdEvaluation(plainEvaluationDto.getIdEvaluation());
                    evaluation.setScoreGeneraleEvaluation(plainEvaluationDto.getScoreGeneraleEvaluation());
                    evaluations1.add(evaluation);
        });

        entreprise.setEvaluations(evaluations1);
        entreprise.setAnneeDeCreation(entrepriseDto.getAnneeDeCreation());
        return entreprise;
    }

}
