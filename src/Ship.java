public class Ship {

    //Aircraft Carrier of size 5 cells represented by the letter "A"
    //Battleship of size 4 cells represented by the letter "B"
    //Submarine of size 3 cells represented by the letter "S"
    //Destroyer of size 3 cells represented by the letter "D"
    //Patrol Boat of size 2 cells represented by the letter "P"


    private int size;
    private char letter;
    private char direction;
    private Coordinate coordinate;

    public Ship(char letter,char direction,Coordinate coordinate){
        if(letter == 'A'){
            size = 5;
        }else if(letter == 'B'){
            size = 4;
        }else if(letter == 'S' || letter == 'D'){
            size = 3;
        }else {
            size = 2;
        }
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }

    public char getDirection() {
        return direction;
    }

    public void setDirection(char direction) {
        this.direction = direction;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }
}
