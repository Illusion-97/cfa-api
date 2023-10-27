package fr.dawan.AppliCFABack.tools;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class TimerCache {
    ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    Map<Long, LocalDateTime> cacheUserTimer = Collections.synchronizedMap(new WeakHashMap<>());

    public boolean startTimerForUserConnected(long idUser, long timerDuration){
        if (cacheUserTimer.containsKey(idUser)){
            return false;
        }

        LocalDateTime currentTime = LocalDateTime.now();
        LocalDateTime expirationTime = currentTime.plusHours(timerDuration);

        cacheUserTimer.put(idUser, expirationTime);
        scheduler.schedule(() -> {
            cacheUserTimer.remove(idUser);
        },timerDuration, TimeUnit.HOURS);
        return true;
    }
}
