package fr.iceknith.uhc_civilization;

public abstract class TimerTask {
    public CountdownMode mode;
    public long interval;
    public long delay;
    public boolean isLimited;
    public int maxRuns;

    public TimerTask() {
        this.mode = CountdownMode.TICKS;
        this.interval = 0;
        this.delay = 0;
        this.isLimited = false;
        this.maxRuns = 0;
    }

    public TimerTask(CountdownMode mode, long interval) {
        this.mode = mode;
        this.interval = interval;
        this.delay = 0;
        this.isLimited = false;
        this.maxRuns = 0;
    }

    public TimerTask(CountdownMode mode, long interval, long delay) {
        this.mode = mode;
        this.interval = interval;
        this.delay = delay;
        this.isLimited = false;
        this.maxRuns = 0;
    }

    public TimerTask(CountdownMode mode, long interval, long delay, boolean isLimited, int maxRuns) {
        this.mode = mode;
        this.interval = interval;
        this.delay = delay;
        this.isLimited = isLimited;
        this.maxRuns = maxRuns;
    }

    public TimerTask(CountdownMode mode, long interval, boolean isLimited, int maxRuns) {
        this.mode = mode;
        this.interval = interval;
        this.delay = 0;
        this.isLimited = isLimited;
        this.maxRuns = maxRuns;
    }

    abstract void run();

    public void debug() {
        System.out.println("--- Outputting debug information ---");
        System.out.println("Countdown mode: " + mode);
        System.out.println("Interval: " + interval);
        System.out.println("Delay until next execution: " + delay);
        System.out.println("Is limited: " + isLimited);
        if (isLimited) {
            System.out.println("Maximum runs" + maxRuns);
        }
    }
}
