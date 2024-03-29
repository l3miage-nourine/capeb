package fr.capeb.backend.riskevaluator.model.dto;


import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import fr.capeb.backend.riskevaluator.model.Questionnaire;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainPreconisationGlobaleDto {

    private Integer idPreconisationG;
    private String contenu;
    private Integer viewIfPourcentageScoreLessThan;

    public static PlainPreconisationGlobaleDto from(PreconisationGlobale preconisationGlobale){
        PlainPreconisationGlobaleDto preconisationGlobaleDto = new PlainPreconisationGlobaleDto();
        preconisationGlobaleDto.setViewIfPourcentageScoreLessThan(preconisationGlobale.getViewIfPourcentageScoreLessThan());
        preconisationGlobaleDto.setIdPreconisationG(preconisationGlobale.getIdPreconisationG());
        preconisationGlobaleDto.setContenu(preconisationGlobale.getContenu());
        return preconisationGlobaleDto;
    }
}
