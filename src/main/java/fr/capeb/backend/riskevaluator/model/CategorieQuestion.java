package fr.capeb.backend.riskevaluator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.capeb.backend.riskevaluator.model.dto.*;
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
@Table(name = "categorie_question")
public class CategorieQuestion {
    @Id
    @Column(name = "id_categorie")
    public Integer idCategorie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_questionnaire", nullable=false)
    public Questionnaire questionnaire;

    @Column(name = "libelle", nullable=false)
    public String libelle;


    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<ScoreCategory> scoreEvaluations=new HashSet<>();

    @OrderBy("contenu")
    @OneToMany(mappedBy = "categorieQuestion",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Set<PreconisationCategorie> preconisationsCategorie= new HashSet<>();

    @OrderBy("libelleQuestion")
    @OneToMany(mappedBy = "categorieQuestion", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    //@JsonIgnore
    private Set<Question> questions= new HashSet<>();

    public static CategorieQuestion from(CategorieQuestionDto categorieQuestionDto){
        CategorieQuestion categorieQuestion = new CategorieQuestion();

        Set<PlainQuestionDto> plainQuestionDtos = categorieQuestionDto.getQuestions();
        Set<Question> questions1 = new HashSet<>();
        plainQuestionDtos.forEach(plainQuestionDto -> {
            Question question = new Question();
            question.setLibelleQuestion(plainQuestionDto.getLibelleQuestion());
            question.setIdQuestion(plainQuestionDto.getIdQuestion());
            question.setScoreMaxPossibleQuestion(plainQuestionDto.getScoreMaxPossibleQuestion());
            question.setTypeQuestion(plainQuestionDto.getTypeQuestion());
            questions1.add(question);
        });
        categorieQuestion.setQuestions(questions1);

        Set<PlainPreconisationCategorieDto> plainPreconisationsCategorieDtos =  categorieQuestionDto.getPreconisationsCategorie();
        Set<PreconisationCategorie> preconisationsCategories1 = new HashSet<>();
        plainPreconisationsCategorieDtos.forEach(planPreconisationsCategorie -> {
            PreconisationCategorie preconisationCategorie = new PreconisationCategorie();
            preconisationCategorie.setIdPreconisation(planPreconisationsCategorie.getIdPreconisation());
            preconisationCategorie.setContenu(planPreconisationsCategorie.getContenu());
            preconisationCategorie.setViewIfPourcentageScoreLessThan(planPreconisationsCategorie.getViewIfPourcentageScoreLessThan());
            preconisationsCategories1.add(preconisationCategorie);
        });
        categorieQuestion.setPreconisationsCategorie(preconisationsCategories1);


        Set<PlainScoreCategoryDto> plainScoreEvaluationDtos = categorieQuestionDto.getScoreEvaluations();
        Set<ScoreCategory> scoreEvaluations1 =  new HashSet<>();
        plainScoreEvaluationDtos.forEach(plainScoreEvaluation -> {
            ScoreCategory scoreCategory = new ScoreCategory();
            scoreCategory.setKey(plainScoreEvaluation.getKey());
            scoreCategory.setNbPoints(plainScoreEvaluation.getNbPoints());
            scoreEvaluations1.add(scoreCategory);
        });
        categorieQuestion.setScoreEvaluations(scoreEvaluations1);

        categorieQuestion.setLibelle(categorieQuestionDto.getLibelle());
        PlainQuestionnaireDto plainQuestionnaireDto =categorieQuestionDto.getQuestionnaire();
        Questionnaire questionnaire1 = new Questionnaire();
        questionnaire1.setIdQuestionnaire(plainQuestionnaireDto.getIdQuestionnaire());
        questionnaire1.setThematique(plainQuestionnaireDto.getThematique());
        categorieQuestion.setQuestionnaire(questionnaire1);
        categorieQuestion.setScoreEvaluations(categorieQuestion.getScoreEvaluations());
        return categorieQuestion;
    }


}
