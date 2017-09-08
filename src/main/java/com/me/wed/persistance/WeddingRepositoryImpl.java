package com.me.wed.persistance;

import com.me.wed.DemoApplication;
import com.me.wed.domain.Guest;
import com.me.wed.domain.Wedding;
import com.me.wed.repositories.WeddingRepository;
import com.me.wed.services.WeddingService;
import com.mongodb.WriteResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Created by pipe on 8/8/17.
 */

@Service
@Transactional
public class WeddingRepositoryImpl implements WeddingService {
    @Autowired
    private WeddingRepository weddingRepository;

    @Autowired
    private MongoTemplate mongoTemplate;

    private Logger log = Logger.getLogger(DemoApplication.class);

    @Override
    public Wedding saveWedding(Wedding wedding) {
        if (!weddingRepository.exists(wedding.getEmail())) {
            return weddingRepository.insert(wedding);
        }
        else {
            log.warn("UNABLE TO ADD WEDDING, LIMITED TO ONE AT THE MOMENT");
            return null;
        }
    }

    @Override
    public Iterable<Wedding> findAllWeddings() {
        return weddingRepository.findAll();
    }

    @Override
    public Wedding getWeddingById(String id) {
        return weddingRepository.findOne(id);
    }

    @Override
    public void deleteAll() {
        weddingRepository.deleteAll();
    }

    @Override
    public boolean addGuest(String id, Guest guest) {
        Query query = new Query(Criteria.where("guuid").is(id));
        Update update = new Update();
        update.addToSet("guests",guest);


        WriteResult writeResult = mongoTemplate.updateFirst(query,update,Wedding.class);

        if (writeResult.isUpdateOfExisting()) {
            log.debug("ADDED GUEST TO FOLLOWING ACCOUNT GUUID: " + id);
            return true;
        }else {
            log.warn("UNABLE TO ADD GUEST");
            return false;
        }


    }

    @Override
    public boolean addGuests(String id, List<Guest> guests) {
        Query query = new Query(Criteria.where("guuid").is(id));
        Update update = new Update();
        update.addToSet("guests",guests);

        WriteResult writeResult = mongoTemplate.updateFirst(query,update,Wedding.class);
        return writeResult.getN() == 0 ? false:true;
    }


}
