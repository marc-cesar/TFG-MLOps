package com.mlopsservice.Events;

import com.mlopsservice.Entities.Request;

public class NewFeedbackEvent {
    Request req;

    public NewFeedbackEvent(Request req) {
        this.req = req;
    }

    public Request getRequest(){
        return req;
    }
}
