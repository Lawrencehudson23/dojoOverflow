package com.example.dojooverflow.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dojooverflow.models.Answer;
import com.example.dojooverflow.models.Question;
import com.example.dojooverflow.models.Tag;
import com.example.dojooverflow.models.TagQuestion;
import com.example.dojooverflow.repositories.AnswerRepo;
import com.example.dojooverflow.repositories.QuestionRepo;
import com.example.dojooverflow.repositories.TagQuestionRepo;
import com.example.dojooverflow.repositories.TagRepo;

@Service
public class MainService {
	@Autowired
	private final QuestionRepo qR;
	private final TagRepo tR;
	private final TagQuestionRepo tqR;
	private final AnswerRepo aR;
	
	public MainService(QuestionRepo qR, TagRepo tR, TagQuestionRepo tqR, AnswerRepo aR) {
		this.qR = qR;
		this.tR = tR;
		this.tqR = tqR;
		this.aR = aR;
	}
	public List<Question> allQuestions(){
		return qR.findAll();
	}
	
	public List<Tag> allTags() {
		
		return this.tR.findAll();
	}
	
	public Question createQuestion(Question p) {
		return this.qR.save(p);
	}
	public Tag createTag(Tag t) {
		return this.tR.save(t);
	}
	public TagQuestion createTagQuestion(TagQuestion tq) {
		return this.tqR.save(tq);
	}
	public Answer createAnswer(Answer a) {
		return this.aR.save(a);
	}
	public Question getQuestionById(Long id) {
		Optional<Question> optionalQuestion = this.qR.findById(id);
		if(optionalQuestion.isPresent()) {
			return optionalQuestion.get();
		}
		return null;
	}
	
	public Tag getTagById(Long id) {
		Optional<Tag> optionalTag = this.tR.findById(id);
		if(optionalTag.isPresent()) {
			return optionalTag.get();
		}
		return null;
	}
	public List<Answer> getByQuestionId(Long id) {

		return this.aR.findByQuestionId(id);
	}


	
	
	
}
