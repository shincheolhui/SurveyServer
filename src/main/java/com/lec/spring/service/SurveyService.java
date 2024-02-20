package com.lec.spring.service;

import com.lec.spring.domain.Survey;
import com.lec.spring.repository.SurveyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class SurveyService {

    private final SurveyRepository surveyRepository;

    public List<Survey> list() {
        return surveyRepository.findAll(Sort.by(Sort.Order.desc("id")));
    }

    public Survey write(Survey survey) {
        survey.setCreatedAt(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        return surveyRepository.saveAndFlush(survey);
    }

    public Survey selectById(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("설문의 번호를 확인해주세요."));
    }

    public Survey update(Survey survey) {
        Survey updatedSurvey = surveyRepository
                .findById(survey.getId())
                .orElseThrow(() -> new IllegalArgumentException("수정할 설문의 번호를 확인해주세요."));

        updatedSurvey.setGender(survey.getGender());
        updatedSurvey.setArea(survey.getArea());
        updatedSurvey.setFavorite(survey.getFavorite());

        return updatedSurvey;
    }

    public int deleteById(Long id) {
        boolean existence = surveyRepository.existsById(id);
        if (!existence) {
            return 0;
        }
        surveyRepository.deleteById(id);
        return 1;
    }
}
