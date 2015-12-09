package com.zhaoyan.ladderball.app.event;

import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionRequest;
import com.zhaoyan.ladderball.domain.eventofmatch.http.EventCollectionResponse;
import com.zhaoyan.ladderball.service.event.EventService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/match")
public class EventController {
    Logger logger = LoggerFactory.getLogger(EventController.class);

    @Autowired
    EventService eventService;

    @RequestMapping(value = "/eventcollection", method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
    public
    @ResponseBody
    EventCollectionResponse addEvent(@RequestBody EventCollectionRequest request) {
        logger.debug("addEvent() EventCollectionRequest: " + request);
        EventCollectionResponse response = eventService.addEvent(request);
        return response;
    }
}
