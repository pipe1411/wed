package com.me.wed.persistance;

import com.me.wed.domain.survey.Survey;
import com.me.wed.repositories.SurveyRepository;
import com.me.wed.services.SurveyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by pipe on 12/24/17.
 */

@Service
@Transactional
public class SurveyImpl implements SurveyService {
    @Autowired
    private SurveyRepository surveyRepository;

    @Override
    public Iterable<Survey> findAllSurveys() {
        return surveyRepository.findAll();
    }
}
