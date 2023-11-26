package com.ll.sbbmission.question.service;

import com.ll.sbbmission.question.entity.Question;
import com.ll.sbbmission.question.repository.QuestionRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class QuestionServiceTest {

    @Mock
    private QuestionRepository questionRepository;

    @InjectMocks
    private QuestionService questionService;

    @Test
    public void testGetList() {
        // 가상의 Question 데이터를 생성
        Question question = new Question();
        question.setId(1);
        question.setSubject("Question1");
        question.setContent("Content1");

        List<Question> questionList = new ArrayList<>();
        questionList.add(question);

        Mockito.when(questionRepository.findAll()).thenReturn(questionList);
        // QuestionService의 getList 메서드 호출
        List<Question> result = questionService.getList();

        // 결과 확인
        assertEquals("Question1", result.get(0).getSubject());
        assertEquals("Content1", result.get(0).getContent());
    }
}