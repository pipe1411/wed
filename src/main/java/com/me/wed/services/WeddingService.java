package com.me.wed.services;


import com.me.wed.domain.Guest;
import com.me.wed.domain.Wedding;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by pipe on 8/8/17.
 */

@Component
public interface WeddingService  {
    Wedding saveWedding(Wedding wedding);

    Iterable<Wedding> findAllWeddings();

    Wedding getWeddingById(String id);

    void deleteAll();

    boolean addGuest(String id, Guest guest);

    boolean addGuests(String id, List<Guest> guests);

    boolean updateGuest(String id,Guest guest);

}
