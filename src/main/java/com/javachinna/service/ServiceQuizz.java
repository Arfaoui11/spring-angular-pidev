package com.javachinna.service;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


@Service
@Slf4j
public class ServiceQuizz implements IQuizServiceC{
    /*
    @Autowired
    private RepoUser repoUser;
    @Autowired
    private RepoCandidacy repoCandidacy;
    @Autowired
    private RepoQuestionCandidacy repoQuestionCandidacy;
    @Autowired
    private RepoQuiz repoQuiz;
    @Autowired
    private RepoResultQuiz repoResultQuiz;



    @Override
    public void addQuiz(QuizCandidacy quiz, Integer id) {
        Candidacy candidacy = this.repoCandidacy.findById(id).orElse(null);
        quiz.setCandidacy(candidacy);
        quiz.setCreateAt(new Date());
        repoQuiz.save(quiz);

    }

    @Override
    public void addQuestionAndAsigntoQuiz(QuestionCandidacy question, Integer idQuiz) {
        QuizCandidacy quiz = repoQuiz.findById(idQuiz).orElse(null);

        question.setQuizCandidacy(quiz);

        repoQuestionCandidacy.save(question);

    }

    @Override
    public List<QuestionCandidacy> getQuizQuestion(Integer idQuiz) {

        List<QuestionCandidacy> allQues = (List<QuestionCandidacy>) repoQuestionCandidacy.findAll();
        List<QuestionCandidacy> qList = new ArrayList<>();

        Random random = new Random();

        for(int i=0; i<5; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        return qList;
    }

    @Override
    public List<QuestionCandidacy> getQuestions() {
        List<QuestionCandidacy> allQues = (List<QuestionCandidacy>) repoQuestionCandidacy.findAll();
        List<QuestionCandidacy> qList = new ArrayList<QuestionCandidacy>();

        Random random = new Random();

        for(int i=0; i<5; i++) {
            int rand = random.nextInt(allQues.size());
            qList.add(allQues.get(rand));
            allQues.remove(rand);
        }

        return qList;
    }


    public int getResult(QuestionCandidacy questionCandidacy) {
        int correct = 0;

        for (QuestionCandidacy q : questionCandidacy.getQuestions())
            if (q.getAns() == q.getChose())
                correct++;

            return correct;

        }


   @Override
    public Integer SaveScore(ResultQuiz resultQuiz, Integer idUser, Integer idQuiz) {
        ResultQuiz saveResult = new ResultQuiz();

        User user = this.repoUser.findById(idUser).orElse(null);
        QuizCandidacy quiz = this.repoQuiz.findById(idQuiz).orElse(null);
        if (RepoResultQuiz.findUserQuiz(idUser,idQuiz) == 0)
        {
            saveResult.setSUser(user);
            saveResult.setQuiz(quiz);

            saveResult.setUsername(resultQuiz.getUsername());
            saveResult.setTotalCorrect(resultQuiz.getTotalCorrect());
            saveResult.setCorrectAnswer(resultQuiz.getCorrectAnswer());
            saveResult.setInCorrectAnswer(resultQuiz.getInCorrectAnswer());
            repoResultQuiz.save(saveResult);
            return 1;

        }else{
            log.info("This user is tested with this quiz");
            return 0;
        }
    }

    @Override
    public Integer MaxScoreInCandidacy() {
        return this.repoUser.MaxScoreInCandidacy();
    }

    @Override
    public List<Object> getCandidacyWithScoreQuiz(Integer id) {
        return null;
    }

    @Override
    public List<Object> getCandidacytWithScoreQuiz(Integer id) {

        return this.repoUser.getCandidacytWithScoreQuiz(id);
    }

    @Override
    public List<ResultQuiz> getTopScore() {
        List<ResultQuiz> sList = (List<ResultQuiz>) repoResultQuiz.findAll(Sort.by(Sort.Direction.DESC, "totalCorrect"));

        return sList;
    }

    @Override
    public Integer getScore(Long idU) {
        return repoResultQuiz.getScore(idU);
    }

    @Override
    public List<QuizCandidacy> getQuizByCandidacy(Integer id) {
        return this.repoQuiz.getQuizByCandidacy(id);
    }

    @Override
    public void DeleteQuiz(Integer idQ) {
        this.repoQuiz.deleteById(idQ);

    }

    @Override
    public Integer getScoreByUser(Integer idUser) {
        return null;
    }


   /* @Override
    @Scheduled(cron = "0 0/1 * * * *")
    // @Scheduled(cron = "0 0 20 ? * *") //every day 20:00
    public void giftsToUserMaxScoreInCourses() {
        User user = new User();

        Date date = new Date();
        boolean status=false;

        for(Candidacy form : repoCandidacy.findAll())
        {
            if(repoUser.getApprenantWithScoreForGifts(form.getIdCandidacy()).size()!=0 )
            {
                user = repoUser.getApprenantWithScoreForGifts(form.getIdCandidacy()).get(0);

                Date tomorrow = new Date(form.getEnd().getTime() + (1000 * 60 * 60 * 48));
                log.info("Date : "+tomorrow);
                if (date.after(form.getEnd()) && date.before(tomorrow) && repoResultQuiz.getNbrQuiz(user.getId()) == 5)
                {
                    status=true;
                }

                if (status)
                {
                    this.emailSenderService.sendSimpleEmail(user.getEmail(), " we have max Score in courses   ", "Congratulations Mr's : " + user.getLastName() + "--" + user.getFirstName() + " We have Courses free and access in all domain Formation Id : "+ form.getIdFormation() + " .");
                    status=false;
                }
            }

        }

    }*/
}
