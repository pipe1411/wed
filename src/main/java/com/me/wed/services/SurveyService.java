package com.me.wed.services;

import com.me.wed.domain.survey.Survey;
import org.springframework.stereotype.Component;

/**
 * Created by pipe on 12/24/17.
 */

@Component
public interface SurveyService {
    Iterable<Survey> findAllSurveys();
}
