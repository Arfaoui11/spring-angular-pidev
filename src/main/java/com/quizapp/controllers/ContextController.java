package com.quizapp.controllers;

import java.util.List;

import com.quizapp.models.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class ContextController {

	public static Learner Learner;
	public static Admin admin;
	public static Former former;
	public static Session session;
	public static Quiz quiz;
	public static List<Question> questions;
	
	public static Admin getAdmin() {
		return admin;
	}
	public static void setAdmin(Admin admin) {
		ContextController.admin = admin;
	}
	public static Learner getLearner() {
		return Learner;
	}
	public static void setLearner(Learner Learner) {
		ContextController.Learner = Learner;
	}
	public static Session getSession() {
		return session;
	}
	public static void setSession(Session session) {
		ContextController.session = session;
	}
	public static Quiz getQuiz() {
		return quiz;
	}
	public static void setQuiz(Quiz quiz) {
		ContextController.quiz = quiz;
	}
	public static Former getformer() {
		return former;
	}
	public static void setformer(Former former) {
		ContextController.former = former;
	}
	
	
}
