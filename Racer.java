public class Racer implements Runnable {
    private String name;
    private int distance;
    private int finishLine;
    private int speed;
    private boolean completed;
    private RaceTrack raceTrack;
    
    public Racer(String name, int finishLine, RaceTrack raceTrack) {
        this.name = name;
        this.finishLine = finishLine;
        this.distance = 0;
        this.speed = RandUtils.randInt(1, 5);
        this.completed = false;
        this.raceTrack = raceTrack;
    }

    public void advanceTurn() {
        distance += speed;
        System.out.println("[" + name + "] Progress: " + distance + "/" + finishLine);
    }

    public boolean isCompleted() {
        return completed;
    }



    @Override
    public void run() {

        while (distance < finishLine) {
            try {
                Thread.sleep(RandUtils.randInt(100, 1000));
                advanceTurn();
            }
            catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
        this.completed = true;
        raceTrack.crossFinishLine(this.name);

    }


    public String getName() {
        return name;
    }
}
