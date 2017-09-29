package com.me.wed;

import com.me.wed.domain.Guest;
import com.me.wed.domain.Wedding;
import com.me.wed.services.WeddingService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

/**
 * Created by pipe on 9/22/17.
 */


@RunWith(SpringRunner.class)
@SpringBootTest()
public class ddguuid {
    @Autowired
    private WeddingService weddingService;


    public void addGuuidToGuest() throws Exception {
        /*Wedding wedding = weddingService.getWeddingById("3439435f-7299-40ee-b103-28d1159dd282");
        for(Guest guest : wedding.getGuests()) {
            guest.setGuuid(UUID.randomUUID().toString());
        }
        wedding.setGuuid(UUID.randomUUID().toString());
        weddingService.saveWedding(wedding);*/
    }

}
