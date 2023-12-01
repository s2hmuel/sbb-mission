package com.ll.sbbmission.answer.repository;

import com.ll.sbbmission.answer.entity.Answer;
import com.ll.sbbmission.question.entity.Question;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnswerRepository extends JpaRepository<Answer, Integer> {
    Page<Answer> findAllByQuestion(Question question, Pageable pageable);


}