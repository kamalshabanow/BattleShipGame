public class Coordinate {
    private int xCor;
    private int yCor;

    public Coordinate(){
        xCor = 1;
        yCor = 1;
    }

    public Coordinate(int x, int y){
        xCor = x;
        yCor = y;
    }

    public int getxCor() {
        return xCor;
    }

    public void setxCor(int xCor) {
        this.xCor = xCor;
    }

    public int getyCor() {
        return yCor;
    }

    public void setyCor(int yCor) {
        this.yCor = yCor;
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "xCor=" + xCor +
                ", yCor=" + yCor +
                '}';
    }
}
