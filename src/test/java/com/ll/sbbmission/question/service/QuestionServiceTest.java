package com.ll.sbbmission.question.service;

import com.ll.sbbmission.exception.DataNotFoundException;
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
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

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

        when(questionRepository.findAll()).thenReturn(questionList);
        // QuestionService의 getList 메서드 호출
        List<Question> result = questionService.getList();

        // 결과 확인
        assertEquals("Question1", result.get(0).getSubject());
        assertEquals("Content1", result.get(0).getContent());
    }

    @Test
    public void testQuestionFound(){
        Question question = new Question();
        question.setId(1);
        question.setSubject("Question 1");
        question.setContent("Content 1");

        // Mock Repository의 findById 메서드가 호출될 때 반환할 데이터 설정
        when(questionRepository.findById(1)).thenReturn(Optional.of(question));

        // QuestionService의 getQuestion 메서드 호출
        Question result = questionService.getQuestion(1);

        // 결과 확인
        assertEquals(1, result.getId());
        assertEquals("Question 1", result.getSubject());
        assertEquals("Content 1", result.getContent());
    }

    @Test
    public void testQuestionNotFound() {
        // Mock Repository의 findById 메서드가 호출될 때 반환할 데이터 설정
        when(questionRepository.findById(1)).thenReturn(Optional.empty());

        // 특정 ID에 해당하는 Question이 없을 때, DataNotFoundException이 발생하는지 확인
        assertThrows(DataNotFoundException.class, () -> {
            questionService.getQuestion(1);
        });
    }



}