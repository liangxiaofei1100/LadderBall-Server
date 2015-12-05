package com.zhaoyan.ladderball.service.recordermatch;

import com.zhaoyan.ladderball.dao.recordermatch.RecorderMatchDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecorderMatchService {

    @Autowired
    RecorderMatchDao recorderMatchDao;

    public void getRecorderMatch() {
        
    }

}
