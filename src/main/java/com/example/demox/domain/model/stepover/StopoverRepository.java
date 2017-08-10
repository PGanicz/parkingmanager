package com.example.demox.domain.model.stepover;

public interface StopoverRepository {
    StopoverId nextStopoverId();
    void store(Stopover stopover);
    void update(Stopover stopover);
    Stopover findById(StopoverId id);
}
