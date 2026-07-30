public class StableSortDemo {
    public static void main(String[] args) {
        Participant[] participants = {
            new Participant("Amy", 90),
            new Participant("Ben", 80),
            new Participant("Cara", 90),
            new Participant("Dan", 80)
        };

        insertionSortByScore(participants);

        for (Participant participant : participants) {
            System.out.println(participant);
        }
    }

    public static void insertionSortByScore(Participant[] values) {
        for (int index = 1; index < values.length; index++) {
            Participant key = values[index];
            int position = index - 1;

            while (position >= 0 &&
                   values[position].getScore() < key.getScore()) {
                values[position + 1] = values[position];
                position--;
            }
            values[position + 1] = key;
        }
    }
}

class Participant {
    private String name;
    private int score;

    public Participant(String name, int score) {
        this.name = name;
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    @Override
    public String toString() {
        return name + " " + score;
    }
}
