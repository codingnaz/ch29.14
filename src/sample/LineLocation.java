package sample;

/**
 * Created by SAimon22 on 12/4/2017.
 */
public class LineLocation {
    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    int x1;

    public LineLocation(int x1, int y1) {
        this.x1 = x1;
        this.y1 = y1;
    }

    int y1;
}
