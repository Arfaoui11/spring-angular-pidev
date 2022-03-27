package com.javachinna.controller;


import com.javachinna.model.QuestionCandidacy;
import com.javachinna.model.ResultQuiz;
import com.javachinna.service.ServiceQuizz;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/quiz")
public class QuizCandidacyController {/*

	@Autowired
	private ServiceQuizz iQuizServiceC;



	public QuizCandidacyController(ServiceQuizz serviceQuizz) {
		super();
		this.iQuizServiceC = serviceQuizz;
	}


//	@PostMapping("/addQuiz")
//	@ApiOperation(value = " add Quiz ")
//	public void addQuiz(@RequestBody Quiz quiz)
//	{
//		iServicesQuiz.addQuiz(quiz);
//	}

	@PostMapping("/addQuestionAndAsigntoQuiz/{idQuiz}")
	@ApiOperation(value = " add Question And Asign To Quiz ")
	public void addQuestionAndAsigntoQuiz(@RequestBody QuestionCandidacy question, @PathVariable(name = "idQuiz") Integer idQuiz)
	{
		iQuizServiceC.addQuestionAndAsigntoQuiz(question, idQuiz);
	}




	@ApiOperation(value = "get Quiz Question")
	@GetMapping("/getQuizQuestion/{id}")
	public List<QuestionCandidacy> getQuizQuestion(@PathVariable("id") Integer idQuiz)
	{
		return iQuizServiceC.getQuizQuestion(idQuiz);
	}



	@ApiOperation(value = "get Quiz Questions")
	@GetMapping("/getQuizQuestions")
	public List<QuestionCandidacy> getQuestions()
	{

		return iQuizServiceC.getQuestions();
	}


	@PostMapping("/SaveScore/{idUser}/{idQuiz}")
	@ApiOperation(value = " Save Score Quiz ")
	public Integer saveScore(@RequestBody ResultQuiz result, @PathVariable(name = "idUser") Integer idUser, @PathVariable(name = "idQuiz") Integer idQuiz)
	{
		return   this.iQuizServiceC.SaveScore(result,idUser,idQuiz);
	}
	@ApiOperation(value = "Delete Quiz")
	@GetMapping("/DeleteQuiz/{id}")
	@ResponseBody
	public void DeleteQuiz(@PathVariable("id") Integer idQuiz)
	{
		this.iQuizServiceC.DeleteQuiz(idQuiz);
	}
	@ApiOperation(value = " get Score By User  ")
    @GetMapping("/getScore/{id}")
    @ResponseBody
    public Integer getScore(@PathVariable("id") Integer idUser)
    {
        return iQuizServiceC.getScoreByUser(idUser);
    }
*/


}
