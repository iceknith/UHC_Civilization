package fr.iceknith.uhc_civilization;

import org.bukkit.Bukkit;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Timer {
    public long ticks;
    public long millis;
    public long startTime;
    public long lastTime;
    private List<TimerTask> tasks;

    public Timer() {
        startTime = System.currentTimeMillis();
        lastTime = System.currentTimeMillis();
        tasks = new ArrayList<>();
        Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(Main.instance, () -> {
            millis = System.currentTimeMillis() - startTime;
            ticks++;
            long deltaTime = System.currentTimeMillis() - lastTime;
            lastTime = System.currentTimeMillis();
            List<TimerTask> remove = new LinkedList<>();
            for (TimerTask task : tasks) {
                if (task.delay <= 0) {

                    if (task.isLimited) {
                        if (task.maxRuns <= 0) {
                            remove.add(task);
                        } else {
                            task.run();
                            task.maxRuns--;
                        }
                    }
                    task.delay = task.interval;
                }
                switch (task.mode) {
                    case TICKS:
                        task.delay--;
                        break;
                    case MILLISECONDS:
                        task.delay -= deltaTime;
                }
            }
            tasks.removeAll(remove);
        }, 0, 0);
    }

    public void addTask(TimerTask task) {
        tasks.add(task);
    }
}
