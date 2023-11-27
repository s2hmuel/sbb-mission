package com.ll.sbbmission.question.controller;

import com.ll.sbbmission.question.entity.Question;
import com.ll.sbbmission.question.repository.QuestionRepository;
import com.ll.sbbmission.question.service.QuestionService;
import com.ll.sbbmission.user.entity.SiteUser;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@WebMvcTest
public class QuestionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private QuestionRepository questionRepository;
    @MockBean
    private QuestionService questionService;

    @Test
    public void testList() throws Exception {
        Question question = new Question();
        question.setId(1);
        question.setSubject("Question1");
        question.setContent("Content1");

        List<Question> questionList = new ArrayList<>();
        questionList.add(question);

        // Mock Repository의 findAll 메서드가 호출될 때 반환할 값 설정
        when(questionRepository.findAll()).thenReturn(questionList);

        // GET /question/list 요청 수행 및 검증
        mockMvc.perform(MockMvcRequestBuilders.get("/question/list"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("question_list"))
                .andExpect(MockMvcResultMatchers.model().attributeExists("questionList"))
                .andExpect(MockMvcResultMatchers.model().attribute("questionList", questionList));
    }
}
