package com.backend.mlopsbackend.Events;

public class DataCollectionReadyEvent {
    public String dataPath;
    public DataCollectionReadyEvent(String Path) {
        dataPath = Path;
    }
}
