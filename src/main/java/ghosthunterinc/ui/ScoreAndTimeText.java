package ghosthunterinc.ui;

public class ScoreAndTimeText {
    private int score;
    private int minutes;
    private int seconds;
    private double posX;
    private double posY;

    public ScoreAndTimeText(int score, int minutes, int seconds, double posX, double posY) {
        this.score = score;
        this.minutes = minutes;
        this.seconds = seconds;
        this.posX = posX;
        this.posY = posY;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public int getSeconds() {
        return seconds;
    }

    public void setSeconds(int seconds) {
        this.seconds = seconds;
    }

    public double getPosX() {
        return posX;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public double getPosY() {
        return posY;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }
}
