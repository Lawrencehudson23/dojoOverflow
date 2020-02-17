package com.example.dojooverflow.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.dojooverflow.models.TagQuestion;

@Repository
public interface TagQuestionRepo extends CrudRepository<TagQuestion, Long>{
	List<TagQuestion> findAll();
}
