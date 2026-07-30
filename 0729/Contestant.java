public class Contestant {
    private String id;
    private String name;
    private int score;
    private double timeSeconds;

    public Contestant(String id, String name, int score, double timeSeconds) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.timeSeconds = timeSeconds;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }

    public double getTimeSeconds() {
        return timeSeconds;
    }

    @Override
    public String toString() {
        return String.format("編號: %-5s | 姓名: %-8s | 分數: %3d 分 | 完成秒數: %5.1f 秒",
                id, name, score, timeSeconds);
    }
}
