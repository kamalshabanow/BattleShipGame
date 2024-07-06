public class Boards {
    private char[][] boardA = new char[11][11];
    private char[][] boardB = new char[11][11];

    private int numAirCraftPlayer = 0;
    private int numBattleShipPlayer = 0;
    private int numSubmarinePlayer = 0;
    private int numDestroyerPlayer = 0;
    private int numPatrolBoatPlayer = 0;



    private int numAirCraftComputer = 0;
    private int numBattleShipComputer = 0;
    private int numSubmarineComputer = 0;
    private int numDestroyerComputer = 0;
    private int numPatrolBoatComputer = 0;


    public Boards(){
        initBoardA();
        initBoardB();
    }

    public void initBoardA(){
        boardA[0][0] = ' ';
        //first column
        int num = 1;
        for(int i = 48;i <= 57;i++){
            boardA[num][0] = (char) i;
            num++;
        }

        //first row
        num = 1;
        for(char i = 48; i <= 57;i++){
            boardA[0][num] = i;
            num++;
        }

        //Rest of the board
        for (int i = 1; i < boardA.length; i++) {
            for (int j = 1; j < boardA[0].length; j++) {
                boardA[i][j] = '~';
            }
        }
    }

    public void initBoardB(){
        boardB[0][0] = ' ';
        //first column
        int num = 1;
        for(int i = 48;i <= 57;i++){
            boardB[num][0] = (char) i;
            num++;
        }

        //first row
        num = 1;
        for(char i = 48; i <= 57;i++){
            boardB[0][num] = i;
            num++;
        }

        //Rest of the board
        for (int i = 1; i < boardB.length; i++) {
            for (int j = 1; j < boardB[0].length; j++) {
                boardB[i][j] = '~';
            }
        }
    }

    public boolean isValidLocation(Coordinate coordinate,Ship s){  //Overlap olub olmadigini yoxlayir
        int xCoordinate = coordinate.getxCor() + 1;
        int yCoordinate = coordinate.getyCor() + 1;


        if(xCoordinate < 1 || xCoordinate >= boardA[0].length){  //Koordination colunde olub olmadigini yoxlayir
            return false;
        }

        if(yCoordinate < 1 || yCoordinate >= boardA.length){  //Koordination colunde olub olmadigini yoxlayir
            return false;
        }

        //Checking all the cases of all the directions

        if(s.getDirection() == 'u' ){ //upward
            if(yCoordinate - (s.getSize() + 1) < 1){
                return false;
            }

            for(int i = yCoordinate; i > yCoordinate - (s.getSize()); i--){
                if(boardA[i][xCoordinate] != '~'){
                    return false;
                }
            }
        }else if(s.getDirection() == 'd' ){ //downwards
            if(yCoordinate + (s.getSize() + 1) >=  boardA.length){
                return false;
            }

            for(int i = yCoordinate; i <=  yCoordinate + (s.getSize() - 1); i++){
                if(boardA[i][xCoordinate] != '~'){
                    return false;
                }
            }
        }else if(s.getDirection() == 'r' ){ //right
            if(xCoordinate + (s.getSize() - 1) >= boardA[0].length){
                return false;
            }

            for(int i = xCoordinate; i <= xCoordinate + (s.getSize() - 1); i++){
                if(boardA[yCoordinate][i] != '~'){
                    return false;
                }
            }
        }else if(s.getDirection() == 'l' ){ //right
            if(xCoordinate - (s.getSize() + 1) < 1){
                return false;
            }

            for(int i = xCoordinate; i > xCoordinate - s.getSize(); i--){
                if(boardA[yCoordinate][i] != '~'){
                    return false;
                }
            }
        }

        return true;

    }


    public boolean isValidAttack(Coordinate coordinate){
        int yCoordinate = coordinate.getyCor() + 1;
        int xCoordinate = coordinate.getxCor() + 1;

        //Checking the coordinates are inside the board's boundary or out

        if(xCoordinate < 1 || xCoordinate >= boardA[0].length){
            return false;
        }
        if(yCoordinate < 1 || yCoordinate >= boardA.length){
            return false;
        }

        return true;

    }

    public char resultHitMissComputer(Coordinate coordinate,Computer opposition){
        int yCoordinate = coordinate.getyCor() + 1;
        int xCoordinate = coordinate.getxCor() + 1;

        if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'A'){
            numAirCraftComputer++;
            if(numAirCraftComputer == 5){
                System.out.println("You sunk your opponent's aircraft carrier!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'B'){
            numBattleShipComputer++;
            if(numBattleShipComputer == 4){
                System.out.println("You sunk your opponent's Battleship!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'S'){
            numSubmarineComputer++;
            if(numSubmarineComputer == 3){
                System.out.println("You sunk your opponent's Submarine!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'D'){
            numDestroyerComputer++;
            if(numDestroyerComputer == 3){
                System.out.println("You sunk your opponent's Destroyer!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'P'){
            numPatrolBoatComputer++;
            if(numPatrolBoatComputer == 2){
                System.out.println("You sunk your opponent's Patrol Boat!");
            }
        }

        if(opposition.getBoardA()[yCoordinate][xCoordinate] != '~'){
            this.getBoardB()[yCoordinate][xCoordinate] = 'H'; //Hit
            opposition.getBoardA()[yCoordinate][xCoordinate] = 'X';
            return 'H';
        }
        this.getBoardB()[yCoordinate][xCoordinate] = 'M';  //Miss
        return 'M';
    }


    public char resultHitMissPlayer(Coordinate coordinate,Player opposition){
        int yCoordinate = coordinate.getyCor() + 1;
        int xCoordinate = coordinate.getxCor() + 1;

        if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'A'){
            numAirCraftPlayer++;
            if(numAirCraftPlayer == 5){
                System.out.println("Your opponent sunk your Aircraft Carrier!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'B'){
            numBattleShipPlayer++;
            if(numBattleShipPlayer == 4){
                System.out.println("Your opponent sunk your Battleship!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'S'){
            numSubmarinePlayer++;
            if(numSubmarinePlayer == 3){
                System.out.println("Your opponent sunk your Submarine!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'D'){
            numDestroyerPlayer++;
            if(numDestroyerPlayer == 3){
                System.out.println("Your opponent sunk your Destroyer!");
            }
        }else if(opposition.getBoardA()[yCoordinate][xCoordinate] == 'P'){
            numPatrolBoatPlayer++;
            if(numPatrolBoatPlayer == 2){
                System.out.println("Your opponent sunk your Patrol Boat!");
            }
        }

        if(opposition.getBoardA()[yCoordinate][xCoordinate] != '~'){
            opposition.getBoardA()[yCoordinate][xCoordinate] = 'X';
            this.getBoardB()[yCoordinate][xCoordinate] = 'H'; //hit
            return 'H';
        }else{
            this.getBoardB()[yCoordinate][xCoordinate] = 'M';
            return 'M';
        }
    }


    public void printResult(char result){
        if(result == 'M'){
            System.out.println("\nTough luck soldier! You MISSED!");
        }else{
            System.out.println("\nGreat strike soldier! You successfully HIT the enemy ship!");
        }
    }

    public void placeShips(Coordinate coordinate,Ship s){
        int yCoordinate = coordinate.getyCor() + 1;
        int xCoordinate = coordinate.getxCor() + 1;


        boardA[yCoordinate][xCoordinate] = s.getLetter();

        if(s.getDirection() == 'u'){
            for(int i = yCoordinate;i >= yCoordinate - (s.getSize() - 1);i--){
                boardA[i][xCoordinate] = s.getLetter();
            }
        }else if (s.getDirection() == 'd'){
            for(int i = yCoordinate;i <= yCoordinate + (s.getSize() - 1);i++){
                boardA[i][xCoordinate] = s.getLetter();
            }
        }else if(s.getDirection() == 'r'){
            for(int i = xCoordinate; i <= xCoordinate + (s.getSize() - 1);i++){
                boardA[yCoordinate][i] = s.getLetter();
            }
        }else if(s.getDirection() == 'l'){
            for(int i = xCoordinate; i >= xCoordinate - (s.getSize() - 1);i--){
                boardA[yCoordinate][i] = s.getLetter();
            }
        }
    }

    public char[][] getBoardA(){
        return boardA;
    }

    public char[][] getBoardB(){
        return boardB;
    }

    public void printBoardA(){
        for (int i = 0; i < boardA.length; i++) {
            for (int j = 0; j < boardA[0].length; j++) {
                System.out.print(boardA[i][j] + "       ");
            }
            System.out.println();
        }
    }

    public void printBoardB(){
        for (int i = 0; i < boardB.length; i++) {
            for (int j = 0; j < boardB[0].length; j++) {
                System.out.print(boardA[i][j] + "       ");
            }
            System.out.println();
        }
    }

}
