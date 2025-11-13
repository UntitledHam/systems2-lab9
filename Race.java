import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Race {
    private List<Racer> racers;
    private int finishLine;
    private Set<String> racerNames;
    private List<Thread> racerThreads;
    private RaceTrack raceTrack;

    public Race(int finishLine) {
        this.racers = new ArrayList<Racer>();
        this.racerNames = new HashSet<String>();
        this.finishLine = finishLine;
        this.racerThreads = new ArrayList<>();
        this.raceTrack = new RaceTrack();
    }
    public Race(int finishLine, List<Racer> racers) {
        this.racers = racers;
        this.finishLine = finishLine;
        this.racerNames = new HashSet<>();
        this.racerThreads = new ArrayList<>();
        this.raceTrack = new RaceTrack();

        for (Racer racer : racers) {
            if (racerNames.contains(racer.getName())) {
                throw new IllegalArgumentException("Racer " + racer.getName() + " already exists");
            }
            this.racerNames.add(racer.getName());
        }
    }

    public void addRacer(int finishLine, Racer racer) {
        racers.add(racer);
        this.finishLine = finishLine;
        racerNames.add(racer.getName());
    }
    public void addRacer(String racerName) {
        if (racerNames.contains(racerName)) {
            throw new IllegalArgumentException("Racer \"+"+racerName+"\" already exists");
        }
        racers.add(new Racer(racerName, this.finishLine, this.raceTrack));
        racerNames.add(racerName);
    }
    public void addRacer() {
        String name = getRandomRacerName();
        racers.add(new Racer(name, this.finishLine, this.raceTrack));
        racerNames.add(name);
    }
    public void addRacers(List<Racer> racers) {
        this.racers.addAll(racers);
        for (Racer racer : racers) {
            if (!racerNames.contains(racer.getName())) {
                this.racerNames.add(racer.getName());
            }
            else {
                throw new IllegalArgumentException("Racer \"+"+racer.getName()+"\" already exists");
            }
        }
    }
    public void addRacers(int numRacers) {
        for  (int i = 0; i < numRacers; i++) {
            addRacer();
        }
    }



    public void runRace() {
        raceTrack.resetPositions();

        for (Racer racer : racers) {
            Thread thread = new Thread(racer);
            racerThreads.add(thread);
            thread.start();
        }

        for (Thread racerThread : racerThreads) {
            try {
                racerThread.join();
            }
            catch (InterruptedException e) {
                System.out.println("Race interrupted!");
            }
        }
        System.out.println("Race finished!");
    }


    private String getRandomRacerName() {
        String[] animals = {
                "ant", "bear", "beaver", "bee", "bison", "buffalo", "camel", "cat", "cheetah", "chicken",
                "chimpanzee", "cobra", "cow", "coyote", "crab", "crocodile", "crow", "deer", "dog", "dolphin",
                "donkey", "duck", "eagle", "elephant", "falcon", "ferret", "fish", "flamingo", "fox", "frog",
                "gazelle", "giraffe", "goat", "goose", "gorilla", "hamster", "hawk", "hedgehog", "hippopotamus",
                "horse", "hyena", "iguana", "jackal", "jaguar", "kangaroo", "koala", "lemur", "leopard", "lion",
                "lizard", "llama", "lobster", "manatee", "meerkat", "mole", "monkey", "moose", "mouse", "octopus",
                "opossum", "orangutan", "ostrich", "otter", "owl", "panda", "panther", "parrot", "peacock", "pelican",
                "penguin", "pig", "pigeon", "porcupine", "rabbit", "raccoon", "rat", "reindeer", "rhino", "rooster",
                "seal", "shark", "sheep", "skunk", "sloth", "snail", "snake", "sparrow", "spider", "squid", "squirrel",
                "starfish", "swan", "tiger", "toad", "tortoise", "turkey", "turtle", "vulture", "walrus", "weasel",
                "whale", "wolf", "wombat", "woodpecker", "yak", "zebra"
        };

        String[] adjectives = {
                "agile", "brave", "busy", "bright", "bold", "calm", "clever", "curious", "cunning", "cute",
                "daring", "diligent", "eager", "elegant", "energetic", "fierce", "friendly", "funny", "gentle", "giant",
                "graceful", "greedy", "happy", "hardy", "helpful", "hungry", "inquisitive", "jolly", "joyful", "keen",
                "lazy", "lively", "loyal", "lucky", "majestic", "mighty", "mysterious", "nervous", "noisy", "obedient",
                "patient", "peaceful", "playful", "plucky", "polite", "powerful", "proud", "quick", "quiet", "quirky",
                "rapid", "reckless", "relaxed", "restless", "rough", "royal", "rude", "sarcastic", "shaggy", "shy",
                "silly", "sincere", "sleepy", "slimy", "slow", "smart", "sneaky", "speedy", "spiky", "spirited",
                "strong", "stubborn", "sweet", "swift", "talented", "tame", "tenacious", "thoughtful", "tiny", "tough",
                "trusty", "vain", "vicious", "vigorous", "vocal", "wary", "weak", "weird", "wild", "wise",
                "witty", "wonderful", "woolly", "yappy", "young", "zany", "zealous", "zippy", "zesty", "zonal"
        };

        String adjective =  adjectives[RandUtils.randInt(0, adjectives.length - 1)];
        String animal =  animals[RandUtils.randInt(0, adjectives.length - 1)];

        adjective = adjective.substring(0, 1).toUpperCase() + adjective.substring(1);
        animal = animal.substring(0, 1).toUpperCase() + animal.substring(1);

        String name = adjective+animal;

        return racerNames.contains(name) ? getRandomRacerName() : name;
    }

    public void printPositions() {
        List<String> positions = raceTrack.getRacerPositions();

        System.out.println("\n*** Racers positions: ***");
        for (int i = 0; i < positions.size(); i++) {
            System.out.println(i+1 + ": "+ positions.get(i));
        }
    }

    public static void main(String[] args) {
        Race race = new Race(100);
        race.addRacers(8);
        race.runRace();

        race.printPositions();


    }
}