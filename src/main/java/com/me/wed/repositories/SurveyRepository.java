package com.me.wed.repositories;

import com.me.wed.domain.survey.Survey;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by pipe on 12/24/17.
 */

@Repository
public interface SurveyRepository extends MongoRepository<Survey,String>{
}
