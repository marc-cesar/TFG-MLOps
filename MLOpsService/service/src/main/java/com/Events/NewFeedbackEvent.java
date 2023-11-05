package com.Events;

import com.Entities.Request;

public class NewFeedbackEvent {
    Request req;

    public NewFeedbackEvent(Request req) {
        this.req = req;
    }

    public Request getRequest(){
        return req;
    }
}
