package com.backend.mlopsbackend.Events;

import com.backend.mlopsbackend.Entities.Assessment;

public class NewFeedbackEvent {
    Assessment req;

    public NewFeedbackEvent(Assessment req) {
        this.req = req;
    }

    public Assessment getRequest(){
        return req;
    }
}
