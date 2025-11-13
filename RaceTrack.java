import java.util.*;

public class RaceTrack {
    private int finishPosition;
    private List<String> racers;

    public RaceTrack() {
        this.finishPosition = 0;
        this.racers = new ArrayList<>();
    }
    public synchronized int crossFinishLine(String racerName) {
        finishPosition++;
        racers.add(racerName);
        System.out.println("*** " + racerName + " FINISHED in " + getOrdinal(finishPosition) + " place! ***");
        return finishPosition;
    }

    public void resetPositions() {
        this.racers = new ArrayList<>();
    }

    public List<String> getRacerPositions() {
        return racers;
    }

    private String getOrdinal(int finishPosition) {
        String str = "th";
        if (finishPosition == 1) {
            str = "st";
        }
        else if  (finishPosition == 2) {
            str = "nd";
        }
        else if (finishPosition == 3) {
            str = "rd";
        }

        return "" + finishPosition + str;
    }
}