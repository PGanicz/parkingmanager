package com.example.demox.infrastructure;

import com.example.demox.domain.model.Stopover;
import com.example.demox.domain.model.StopoverId;
import com.example.demox.domain.model.StopoverRepository;

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
    public Stopover find(StopoverId id) {
        return inMemoryBase.get(id);
    }
}
