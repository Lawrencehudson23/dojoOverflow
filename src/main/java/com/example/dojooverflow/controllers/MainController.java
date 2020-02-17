package com.example.dojooverflow.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.dojooverflow.models.Answer;
import com.example.dojooverflow.models.Question;
import com.example.dojooverflow.models.Tag;
import com.example.dojooverflow.models.TagQuestion;
import com.example.dojooverflow.services.MainService;

@Controller
public class MainController {
	private final MainService mS;

	public MainController(MainService mS) {
		this.mS = mS;
	}

	@GetMapping("/api/questions")
	public List<Question> getAllQuestions() {
		return this.mS.allQuestions();
	}

	@PostMapping("/api/questions")
	public Question createQuestion(@RequestParam("question") String question) {
		Question q = new Question();
		q.setQuestion(question);

		return this.mS.createQuestion(q);
	}

	@PostMapping("/api/tags")
	public Tag createTag(@RequestParam("subject") String subject) {
		Tag tag = new Tag();
		tag.setSubject(subject);
		return this.mS.createTag(tag);
	}

	@PostMapping("/api/answers")
	public Answer createAnswer(@RequestParam("answer") String answer) {
		Answer a = new Answer();
		a.setAnswer(answer);
		return this.mS.createAnswer(a);
	}

	@PostMapping("/api/someroute")
	public TagQuestion createTagQuestion(@RequestParam("questionId") Long questionId,
			@RequestParam("tagId") Long tagId) {
		Question question = this.mS.getQuestionById(questionId);
		Tag tag = this.mS.getTagById(tagId);
		TagQuestion tq = new TagQuestion();
		tq.setQuestion(question);
		tq.setTag(tag);
		return this.mS.createTagQuestion(tq);
	}

	@GetMapping("/questions")
	public String dashboard(Model model) {
		List<Question> allQuestions = this.mS.allQuestions();
		model.addAttribute("allQuestions", allQuestions);

		return "/questions/dashboard.jsp";
	}

	@GetMapping("/questions/new")
	public String newQuestion(@ModelAttribute("newQuestion") Question question) {
		return "/questions/newQuestion.jsp";
	}

	@PostMapping("/questions/new")
	public String createQuestion(@Valid @ModelAttribute("newQuestion") Question question, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/questions/newQuestion.jsp";
		}
		this.mS.createQuestion(question);
		redirectAttributes.addFlashAttribute("success", "you create a question!");
		return "redirect:/questions/new";
	}

	@GetMapping("/tags/new")
	public String newTag(@ModelAttribute("newTag") Tag tag) {
		return "/tags/newTag.jsp";
	}

	@PostMapping("/tags/new")
	public String createQuestion(@Valid @ModelAttribute("newTag") Tag tag, BindingResult result,
			RedirectAttributes redirectAttributes) {
		if (result.hasErrors()) {
			return "/tags/newTag.jsp";
		}
		this.mS.createTag(tag);
		redirectAttributes.addFlashAttribute("success", "you create a tag!");
		return "redirect:/tags/new";
	}

	@GetMapping("/questions/{id}")
	public String displayQuestion(@Valid @ModelAttribute("newAnswer") Answer newAnswer, @PathVariable("id") Long id, Model model) {
		Question question = this.mS.getQuestionById(id);
		model.addAttribute("question", question);
		List<Answer> answers = question.getAnswers();
		model.addAttribute("answers", answers);
		List<Tag> allTags = this.mS.allTags();
		model.addAttribute("allTags", allTags);
		List<Tag> qt = question.getTags();
		model.addAttribute("qt", qt);
		model.addAttribute("questionId", id);

		return "/questions/questionProfile.jsp";
	}

	@PostMapping("/questions/{id}")
	public String createRelationship(@Valid @ModelAttribute("newAnswer") Answer newAnswer,
			@PathVariable("id") Long questionId, @RequestParam("tagId") Long tagId, Model model, BindingResult result) {
		Question question = this.mS.getQuestionById(questionId);
		Tag tag = this.mS.getTagById(tagId);
		TagQuestion tq = new TagQuestion();
		tq.setQuestion(question);
		tq.setTag(tag);
		this.mS.createTagQuestion(tq);
		return "redirect:/questions/{id}";
	}

	@GetMapping("/tags/{id}")
	public String displayTag(@PathVariable("id") Long id, Model model) {
		Tag tag = this.mS.getTagById(id);
		model.addAttribute("tag", tag);
		List<Question> allQuestions = this.mS.allQuestions();
		model.addAttribute("allQuestions", allQuestions);
		List<Question> tq = tag.getQuestions();
		model.addAttribute("tq", tq);
		model.addAttribute("TagId", id);

		return "/tags/displayTag.jsp";
	}

	@PostMapping("/tags/{id}")
	public String createRelationship2(@PathVariable("id") Long tagId, @RequestParam("questionId") Long questionId,
			Model model) {
		Tag tag = this.mS.getTagById(tagId);
		Question question = this.mS.getQuestionById(questionId);
		TagQuestion tq = new TagQuestion();
		tq.setTag(tag);
		tq.setQuestion(question);
		this.mS.createTagQuestion(tq);
		return "redirect:/tags/{id}";
	}
}
