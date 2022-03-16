package com.quizapp.controllers;

import java.util.ArrayList;
import java.util.List;

import com.quizapp.models.*;
import com.quizapp.services.FormerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.quizapp.dto.QuestionsDTO;
import com.quizapp.repositories.OptionRepository;
import com.quizapp.services.QuestionService;
import com.quizapp.services.SessionService;
import com.quizapp.services.LearnerService;


@Controller
public class FormerController {

	private FormerService formerService;

	@Autowired
	private LearnerService LearnerService;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private QuestionService questionService;
	
	@Autowired
	private OptionRepository optionRepository;

	public FormerController(FormerService formerService) {
		super();
		this.formerService = formerService;
	}

	@GetMapping("/Former/dashboard")
	public String showFormerDashboard(Model model) {
		List<Session> sessions = new ArrayList<>();
		for (Session session : sessionService.getAllSessions()) {
			if (session.getEnd_time() != null) {
				sessions.add(session);
			}
		}

		model.addAttribute("allSessions", sessions);
		model.addAttribute("former", formerService.getFormerById(ContextController.getformer().getId()));
		return "former-dashboard";
	}

	@GetMapping("/Former/edit/{id}")
	public String showFormerEditPage(@PathVariable Long id, Model model) {
		Former former = formerService.getFormerById(id);
		model.addAttribute("former", former);
		return "admin-former-edit";
	}

	@PostMapping("/Former/edit/{id}")
	public String updateAdminDetails(@PathVariable Long id, @ModelAttribute("admin") Admin admin, Model model) {
		Former existingformer = formerService.getFormerById(id);
		existingformer.setFirstName(admin.getFirstName());
		existingformer.setLastName(admin.getLastName());
		existingformer.setUsername(admin.getUsername());
		existingformer.setPassword(admin.getPassword());
		formerService.saveFormer(existingformer);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/Former/delete/{id}")
	public String deleteformerDetails(@PathVariable Long id, Model model) {
		formerService.deleteFormerById(id);
		return "redirect:/admin/dashboard";
	}

	@GetMapping("/Former/question")
	public String showAddQuestionForm(Model model) {
		QuestionsDTO questionsDTO = new QuestionsDTO();
		model.addAttribute("questionsDto", questionsDTO);
		model.addAttribute("former", formerService.getFormerById(ContextController.getformer().getId()));
		return "former-add-question";
	}

	@PostMapping("/Former/question/add")
	public String addQuestion(@ModelAttribute QuestionsDTO questionsDto, Model model) {
		
		Question question = new Question();
		List<Option> options = new ArrayList<>();
		Option optionOne = new Option(questionsDto.getOptionOne());
		Option optionTwo = new Option(questionsDto.getOptionTwo());
		Option optionThree = new Option(questionsDto.getOptionThree());
		Option optionFour = new Option(questionsDto.getOptionFour());
		options.add(optionOne);
		options.add(optionTwo);
		options.add(optionThree);
		options.add(optionFour);
		question.setTitle(questionsDto.getQuestionTitle());
		question.setSubject(questionsDto.getSubject());
		
		switch (questionsDto.getCorrectAnswer()) {
		case "1":
			question.setOptionCorrect(questionsDto.getOptionOne());
			break;
		case "2":
			question.setOptionCorrect(questionsDto.getOptionTwo());
			break;
		case "3":
			question.setOptionCorrect(questionsDto.getOptionThree());
			break;
		case "4":
			question.setOptionCorrect(questionsDto.getOptionFour());
			break;
		}
		
		for(Option option: options) {
			option.setQuestion(question);
		}
		question.setOptions(options);
		questionService.saveQuestion(question);
		return "redirect:/former/questions/list";
	}

	@GetMapping("/Former/question/{id}")
	public String showModifyQuestionForm(@PathVariable Long id, Model model) {
		Question existingQuestion = questionService.findQuestionByquestionId(id);
		QuestionsDTO questionsDto = new QuestionsDTO();
		questionsDto.setQuestionTitle(existingQuestion.getTitle());
		List<Option> options = existingQuestion.getOptions();
		questionsDto.setOptionOne(options.get(0).getOptionText());
		questionsDto.setOptionTwo(options.get(1).getOptionText());
		questionsDto.setOptionThree(options.get(2).getOptionText());
		questionsDto.setOptionFour(options.get(3).getOptionText());
		questionsDto.setCorrectAnswer(existingQuestion.getOptionCorrect());
		questionsDto.setSubject(existingQuestion.getSubject());
		questionsDto.setQuestionId(existingQuestion.getQuestionId());
		model.addAttribute("questionsDto", questionsDto);
		return "former-questions-edit";
	}

	@PostMapping("/Former/question/{id}")
	public String updateQuestionDetails(@PathVariable Long id, @ModelAttribute QuestionsDTO questionsDto, Model model) {

		Question existingQuestion = questionService.findQuestionByquestionId(id);
		List<Option> existingOptions = existingQuestion.getOptions();
		existingQuestion.setTitle(questionsDto.getQuestionTitle());
		existingQuestion.setSubject(questionsDto.getSubject());
			existingOptions.get(0).setOptionText(questionsDto.getOptionOne());
			existingOptions.get(1).setOptionText(questionsDto.getOptionTwo());
			existingOptions.get(2).setOptionText(questionsDto.getOptionThree());
			existingOptions.get(3).setOptionText(questionsDto.getOptionFour());
		switch (questionsDto.getCorrectAnswer()) {
		case "1":
			existingQuestion.setOptionCorrect(questionsDto.getOptionOne());
			break;
		case "2":
			existingQuestion.setOptionCorrect(questionsDto.getOptionTwo());
			break;
		case "3":
			existingQuestion.setOptionCorrect(questionsDto.getOptionThree());
			break;
		case "4":
			existingQuestion.setOptionCorrect(questionsDto.getOptionFour());
			break;
		}
		
		questionService.saveQuestion(existingQuestion);
		return "redirect:/former/questions/list";

	}

	@GetMapping("/Former/questions/list")
	public String showAllQuestionsPage(Model model) {
		List<Question> scienceQuestions = questionService.getAllQuestionsBySubject("science");
		List<Question> mathsQuestions = questionService.getAllQuestionsBySubject("maths");
		List<Question> physicsQuestions = questionService.getAllQuestionsBySubject("physics");
		List<Question> englishQuestions = questionService.getAllQuestionsBySubject("english");
		model.addAttribute("scienceQuestions", scienceQuestions);
		model.addAttribute("mathsQuestions", mathsQuestions);
		model.addAttribute("englishQuestions", englishQuestions);
		model.addAttribute("physicsQuestions", physicsQuestions);

		return "formers-questions-list";
	}
	
	
	@GetMapping("/Former/question/delete/{id}")
	public String deleteQuestion(@PathVariable Long id, Model model) {
		Question question = questionService.findQuestionByquestionId(id);
		List<Option> options = question.getOptions();
		for(Option option: options) {
			optionRepository.delete(option);
		}
		questionService.deleteQuestionByquestionId(id);
		return "redirect:/former/questions/list";
	}

}
