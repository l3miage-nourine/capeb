package fr.capeb.backend.riskevaluator.model.dto;


import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class PlainScoreCategoryDto {
    private ScoreCategoryPK key = new ScoreCategoryPK();
    private Integer nbPoints;

    public static PlainScoreCategoryDto from(ScoreCategory scoreCategory){
        PlainScoreCategoryDto scoreCategoryDto = new PlainScoreCategoryDto();
        scoreCategoryDto.setKey(scoreCategory.getKey());
        scoreCategoryDto.setNbPoints(scoreCategory.getNbPoints());
        return scoreCategoryDto;
    }

}
