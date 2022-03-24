package com.javachinna.controller;

import com.javachinna.dto.AnswersDTO;
import com.javachinna.model.Question;
import com.javachinna.model.Quiz;
import com.javachinna.model.Session;
import com.javachinna.service.QuestionService;
import com.javachinna.service.SessionService;
import com.javachinna.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;





@RestController
@RequestMapping("/quiz")
public class QuizController {

	private QuestionService questionService;

	@Autowired
	private SessionService sessionService;


	public QuizController(QuestionService questionService) {
		super();
		this.questionService = questionService;
	}



}
