public class TimeTracker {

    private long currentTime = 0;

    public long getCurrentTimeMilliseconds() {
        return currentTime;
    }

    public long getCurrentTimeSeconds(){
        return currentTime / 1000;
    }

    public long getCurrentTimeMinutesSeconds(){
        return currentTime / 60000;
    }

    public void setCurrentTime(long currentTime) {
        this.currentTime = currentTime;
    }

    public void addSeconds(long seconds) {
        currentTime += (seconds * 1000);
    }

    public void addMilliseconds(long milliseconds) {
        currentTime += milliseconds;
    }

    public void addMinutes(long minutes){
        currentTime += (minutes * 60000);
    }


}
