package com.ll.sbbmission.answer.service;


import com.ll.sbbmission.question.entity.Question;
import com.ll.sbbmission.answer.entity.Answer;
import com.ll.sbbmission.answer.repository.AnswerRepository;
import com.ll.sbbmission.user.entity.SiteUser;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Service
public class AnswerService {

    private final AnswerRepository answerRepository;
    public void create(Question question, String content, SiteUser author) {
        Answer answer = new Answer();
        answer.setContent(content);
        answer.setCreateDate(LocalDateTime.now());
        answer.setQuestion(question);
        answer.setAuthor(author);
        this.answerRepository.save(answer);
    }
}
