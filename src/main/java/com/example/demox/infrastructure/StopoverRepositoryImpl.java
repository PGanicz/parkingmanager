package com.example.demox.infrastructure;

import com.example.demox.domain.model.stepover.NumberPlate;
import com.example.demox.domain.model.stepover.Stopover;
import com.example.demox.domain.model.stepover.StopoverId;
import com.example.demox.domain.model.stepover.StopoverRepository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class StopoverRepositoryImpl implements StopoverRepository {
    public static Map<StopoverId, Stopover> inMemoryBase = new ConcurrentHashMap<>();

    public StopoverId nextStopoverId() {
        final String rand = UUID.randomUUID().toString().toUpperCase();
        return new StopoverId(rand.toString());
    }

    @Override
    public void store(Stopover stopover) {
        inMemoryBase.put(stopover.stopoverId(), stopover);
    }

    @Override
    public void update(Stopover stopover) {

    }

    @Override
    public Stopover findById(StopoverId id) {
        return null;
    }

    @Override
    public Stopover findByNumberPlate(NumberPlate plate) {
        return null;
    }
}
