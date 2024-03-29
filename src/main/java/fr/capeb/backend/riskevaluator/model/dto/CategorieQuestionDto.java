package fr.capeb.backend.riskevaluator.model.dto;

import fr.capeb.backend.riskevaluator.model.*;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Getter
@Setter
@NoArgsConstructor
public class CategorieQuestionDto {
    public Integer idCategorie;
    public PlainQuestionnaireDto questionnaire;
    public String libelle;
    private Set<PlainScoreCategoryDto> scoreEvaluations=new HashSet<>();
    private Set<PlainPreconisationCategorieDto> preconisationsCategorie= new HashSet<>();
    private Set<PlainQuestionDto> questions= new HashSet<>();



    public static CategorieQuestionDto from(CategorieQuestion categorieQuestion){
        CategorieQuestionDto categorieQuestionDto = new CategorieQuestionDto();
        categorieQuestionDto.setIdCategorie(categorieQuestion.getIdCategorie());
        Set<PlainQuestionDto> plainQuestionDtos = new HashSet<>();
        Set<Question> questions1 = categorieQuestion.getQuestions();
        questions1.forEach(question -> {
            PlainQuestionDto plainQuestionDto = new PlainQuestionDto();
            plainQuestionDto.setLibelleQuestion(question.getLibelleQuestion());
            plainQuestionDto.setIdQuestion(question.getIdQuestion());
            plainQuestionDto.setScoreMaxPossibleQuestion(question.getScoreMaxPossibleQuestion());
            plainQuestionDto.setTypeQuestion(question.getTypeQuestion());
            plainQuestionDtos.add(plainQuestionDto);
        });
        categorieQuestionDto.setQuestions(plainQuestionDtos);

        Set<PlainPreconisationCategorieDto> plainPreconisationsCategorieDtos = new HashSet<>();
        Set<PreconisationCategorie> preconisationsCategories1 = categorieQuestion.getPreconisationsCategorie();
        preconisationsCategories1.forEach(preconisationsCategorie -> {
            PlainPreconisationCategorieDto plainPreconisationsCategorieDto = new PlainPreconisationCategorieDto();
            plainPreconisationsCategorieDto.setIdPreconisation(preconisationsCategorie.getIdPreconisation());
            plainPreconisationsCategorieDto.setContenu(preconisationsCategorie.getContenu());
            plainPreconisationsCategorieDto.setViewIfPourcentageScoreLessThan(preconisationsCategorie.getViewIfPourcentageScoreLessThan());
            plainPreconisationsCategorieDtos.add(plainPreconisationsCategorieDto);
        });
        categorieQuestionDto.setPreconisationsCategorie(plainPreconisationsCategorieDtos);
        categorieQuestionDto.setLibelle(categorieQuestion.getLibelle());
        PlainQuestionnaireDto plainQuestionnaireDtos = new PlainQuestionnaireDto();
        Questionnaire questionnaire1 = categorieQuestion.getQuestionnaire();
        plainQuestionnaireDtos.setIdQuestionnaire(questionnaire1.getIdQuestionnaire());
        plainQuestionnaireDtos.setThematique(questionnaire1.getThematique());
        categorieQuestionDto.setQuestionnaire(plainQuestionnaireDtos);


        Set<PlainScoreCategoryDto> plainScoreEvaluationDtos = new HashSet<>();
        Set<ScoreCategory> scoreEvaluations1 = categorieQuestion.getScoreEvaluations();
        scoreEvaluations1.forEach(scoreEvaluation -> {
            PlainScoreCategoryDto plainScoreEvaluationDto = new PlainScoreCategoryDto();
            plainScoreEvaluationDto.setKey(scoreEvaluation.getKey());
            plainScoreEvaluationDto.setNbPoints(scoreEvaluation.getNbPoints());
            plainScoreEvaluationDtos.add(plainScoreEvaluationDto);
        });
        categorieQuestionDto.setScoreEvaluations(plainScoreEvaluationDtos);
        return categorieQuestionDto;
    }
}
