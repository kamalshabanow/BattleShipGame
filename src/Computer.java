import java.util.ArrayList;

public class Computer {

    Boards boards = new Boards();
    ArrayList<Coordinate> coordinates = new ArrayList<>();
    ArrayList<Coordinate> chooseFrom = new ArrayList<>();

    public Computer() {
        boards.initBoardA();
        boards.initBoardB();
    }

    public void compSetUp() {
        char direction = randomDirection();
        Coordinate coordinate = randomCoordinate();
        Ship aircraftCarrier = new Ship('A',direction,coordinate);
        while(boards.isValidLocation(coordinate,aircraftCarrier) == false){
            coordinate = randomCoordinate();
        }
        boards.placeShips(coordinate,aircraftCarrier);


        direction = randomDirection();
        coordinate = randomCoordinate();
        Ship battleShip = new Ship('B',direction,coordinate);
        while(boards.isValidLocation(coordinate,battleShip) == false){
            coordinate = randomCoordinate();
        }
        boards.placeShips(coordinate,battleShip);


        direction = randomDirection();
        coordinate = randomCoordinate();
        Ship submarine = new Ship('S',direction,coordinate);
        while(boards.isValidLocation(coordinate,submarine) == false){
            coordinate = randomCoordinate();
        }
        boards.placeShips(coordinate,submarine);


        direction = randomDirection();
        coordinate = randomCoordinate();
        Ship destroyer = new Ship('D',direction,coordinate);
        while(boards.isValidLocation(coordinate,destroyer) == false){
            coordinate = randomCoordinate();
        }
        boards.placeShips(coordinate,destroyer);


        direction = randomDirection();
        coordinate = randomCoordinate();
        Ship patrolBoat = new Ship('P',direction,coordinate);
        while(boards.isValidLocation(coordinate,patrolBoat) == false){
            coordinate = randomCoordinate();
        }
        boards.placeShips(coordinate,patrolBoat);

        System.out.println("The computer has finished setting up its board as well.Time to play!");
        System.out.println("'X' represents where the computer has attacked your board.");

    }

    public void fireAndAttackPlayer(Player p,Coordinate attack){
        printResult(boards.resultHitMissPlayer(attack,p));
    }

    public void printResult(char result){  //successfully or missed
        if(result == 'H'){
            System.out.println("The computer has successfully HIT your ship!");
        }else{
            System.out.println("The computer has MISSED your ship.");
        }
    }

    public Coordinate getPredictedCoordinate(Player p){ //Computerin vurdugu noqtenin koordinatlari
        Coordinate c = randomCoordinate();

        if(coordinates.size() >= 1){
            c = coordinates.get(coordinates.size() - 1);
            if(boards.resultHitMissPlayer(c,p) == 'H'){
                chooseFrom = chooseAdjacentLocations(c);
                int randNum = (int)(Math.random() * chooseFrom.size() + 1);
                while(!boards.isValidAttack(chooseFrom.get(randNum - 1)) || boards.getBoardB()[chooseFrom.get(randNum - 1).getyCor() + 1][chooseFrom.get(randNum - 1).getxCor() + 1] != '~'){
                    randNum = (int) (Math.random() * chooseFrom.size()) + 1;
                }

                coordinates.add(chooseFrom.get(randNum - 1));
                return chooseFrom.get(randNum - 1);
            }
        }

        c = randomCoordinate();
        while(!boards.isValidAttack(c) || boards.getBoardB()[c.getyCor() + 1][c.getxCor() + 1] != '~'){
            c = randomCoordinate();
        }
        coordinates.add(c);
        return c;
    }

    public ArrayList<Coordinate>  chooseAdjacentLocations(Coordinate crd){ //qonsu olub olmadigi
        ArrayList<Coordinate> adjacentCoordinates = new ArrayList<>();
        if(crd.getxCor() == 0 && crd.getyCor() == 0){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() + 1));
        }else if(crd.getxCor() == 0 && crd.getyCor() == 9){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
        }else if(crd.getxCor() == 0){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() + 1));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
        }else if(crd.getxCor() == 9 && crd.getyCor() == 0){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() + 1));
        }else if(crd.getxCor() == 9 && crd.getyCor() == 9){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
        }else if(crd.getxCor() == 9){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
        }else if(crd.getyCor() == 0){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() + 1));
        }else if(crd.getyCor() == 9){
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
        }else{
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() - 1));
            adjacentCoordinates.add(new Coordinate(crd.getxCor(),crd.getyCor() + 1));
            adjacentCoordinates.add(new Coordinate(crd.getxCor() - 1,crd.getyCor()));
            adjacentCoordinates.add(new Coordinate(crd.getxCor() + 1,crd.getyCor()));
        }
        return adjacentCoordinates;
    }

    public boolean verifyCompWin(Player p){
        for (int i = 1; i <p.getBoardA().length ; i++) {
            for (int j = 1; j < p.getBoardA()[0].length; j++) {
                if(p.getBoardA()[i][j] != '~' && boards.getBoardB()[i][j] != 'H'){
                    return false;
                }
            }
        }
        return true;
    }

    public char randomDirection() {
        int dirNum = (int) (Math.random() * 4) + 1;
        if(dirNum == 1){
            return 'u';
        }else if(dirNum == 2){
            return 'd';
        }else if(dirNum == 3){
            return 'r';
        }

        return 'l';
    }

    public Coordinate randomCoordinate() {
        int randNum1 = (int) (Math.random() * 10);
        int randNum2 = (int) (Math.random() * 10);

        return new Coordinate(randNum1, randNum2);

    }

    public char[][] getBoardA(){
        return boards.getBoardA();
    }

    public char[][] getBoardB(){
        return boards.getBoardB();
    }


}
