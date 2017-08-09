package com.example.demox.domain.model;

public interface StopoverRepository {
    StopoverId nextStopoverId();
    void store(Stopover stopover);
    void update(Stopover stopover);
    Stopover find(StopoverId id);
}
