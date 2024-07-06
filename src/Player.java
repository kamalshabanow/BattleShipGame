import java.util.Scanner;

public class Player {
    Boards boards = new Boards();
    Scanner sc = new Scanner(System.in);

    public Player(){
        boards.initBoardA();
        boards.initBoardB();
    }

    public void playerSetup(){
        System.out.println("Now it is time to set your ships!");
        boards.printBoardA();

        //Placing Aircraft Carrier
        System.out.println("The first ship is an aircraft carrier of size 5.");
        System.out.print("What direction would you like to place this ship in? Enter 'u' for up,'d' for down,'r' for right,'l' for left: ");
        char direction = sc.next().charAt(0);

        System.out.print("Now enter the X-coordinate of where the location is: ");
        int xCord = sc.nextInt();
        System.out.print("Now enter the Y-coordinate of where the location is: ");
        int yCord = sc.nextInt();

        Coordinate crd = new Coordinate(xCord,yCord);

        Ship aircraftCarrier = new Ship('A',direction,crd);

        while(boards.isValidLocation(crd,aircraftCarrier) == false){
            System.out.println("That is an invalid location. Please enter again!");

            System.out.print("Enter the X-coordinate of where the location is: ");
            int newX = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where the location is: ");
            int newY = sc.nextInt();

            crd = new Coordinate(newX,newY);
        }

        boards.placeShips(crd,aircraftCarrier);
        boards.printBoardA();


        //Place the battleship
        System.out.println("The second ship is a battle ship of size 4.");
        System.out.print("What direction would you like to place this ship in? Enter 'u' for up,'d' for down,'r' for right,'l' for left: ");
        direction = sc.next().charAt(0);

        System.out.print("Now enter the X-coordinate of where the location is: ");
        xCord = sc.nextInt();
        System.out.print("Now enter the Y-coordinate of where the location is: ");
        yCord = sc.nextInt();

        crd = new Coordinate(xCord,yCord);

        Ship battleShip = new Ship('B',direction,crd);

        while(boards.isValidLocation(crd,battleShip) == false){
            System.out.println("That is an invalid location. Please enter again!");

            System.out.print("Enter the X-coordinate of where the location is: ");
            int newX = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where the location is: ");
            int newY = sc.nextInt();

            crd = new Coordinate(newX,newY);
        }

        boards.placeShips(crd,battleShip);
        boards.printBoardA();


        //Place the submarine
        System.out.println("The third ship is a submarine of size 3.");
        System.out.print("What direction would you like to place this ship in? Enter 'u' for up,'d' for down,'r' for right,'l' for left: ");
        direction = sc.next().charAt(0);

        System.out.print("Now enter the X-coordinate of where the location is: ");
        xCord = sc.nextInt();
        System.out.print("Now enter the Y-coordinate of where the location is: ");
        yCord = sc.nextInt();

        crd = new Coordinate(xCord,yCord);
        Ship submarine = new Ship('S',direction,crd);
        while(boards.isValidLocation(crd,submarine) == false){
            System.out.println("That is an invalid location. Please enter again!");
            System.out.print("Enter the X-coordinate of where the location is: ");
            int newX = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where the location is: ");
            int newY = sc.nextInt();

            crd = new Coordinate(newX,newY);
        }
        boards.placeShips(crd,submarine);
        boards.printBoardA();


        //Place the destroyer
        System.out.println("The fourth ship is a destroyer of size 3.");
        System.out.print("What direction would you like to place this ship in? Enter 'u' for up,'d' for down,'r' for right,'l' for left: ");
        direction = sc.next().charAt(0);

        System.out.print("Now enter the X-coordinate of where the location is: ");
        xCord = sc.nextInt();
        System.out.print("Now enter the Y-coordinate of where the location is: ");
        yCord = sc.nextInt();

        crd = new Coordinate(xCord,yCord);
        Ship destroyer = new Ship('D',direction,crd);
        while(boards.isValidLocation(crd,destroyer) == false){
            System.out.println("That is an invalid location. Please enter again!");
            System.out.print("Enter the X-coordinate of where the location is: ");
            int newX = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where the location is: ");
            int newY = sc.nextInt();

            crd = new Coordinate(newX,newY);
        }
        boards.placeShips(crd,destroyer);
        boards.printBoardA();


        //Place the patrol boat

        System.out.println("The fifth and final ship is a destroyer of size 2.");
        System.out.print("What direction would you like to place this ship in? Enter 'u' for up,'d' for down,'r' for right,'l' for left: ");
        direction = sc.next().charAt(0);

        System.out.print("Now enter the X-coordinate of where the location is: ");
        xCord = sc.nextInt();
        System.out.print("Now enter the Y-coordinate of where the location is: ");
        yCord = sc.nextInt();

        crd = new Coordinate(xCord,yCord);
        Ship patrolBoat = new Ship('P',direction,crd);
        while(boards.isValidLocation(crd,patrolBoat) == false){
            System.out.println("That is an invalid location. Please enter again!");
            System.out.print("Enter the X-coordinate of where the location is: ");
            int newX = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where the location is: ");
            int newY = sc.nextInt();

            crd = new Coordinate(newX,newY);
        }
        boards.placeShips(crd,patrolBoat);
        boards.printBoardA();


        System.out.println("You have finished setting up your board!");
    }

    public Coordinate playerGuessAttack(){
        System.out.println("------------------------------------------------------------------------------");
        System.out.print("Enter the X-coordinate of where you want to attack the computer's board: ");
        int attackX = sc.nextInt();
        System.out.print("Enter the Y-coordinate of where you want to attack the computer's board: ");
        int attackY = sc.nextInt();

        Coordinate attack = new Coordinate(attackX,attackY);

        while(boards.isValidAttack(attack) == false){
            System.out.println("That is an invalid attack location.Please enter again. ");

            System.out.print("Enter the X-coordinate of where you want to attack the computer's board: ");
            int newXAttack = sc.nextInt();
            System.out.print("Enter the Y-coordinate of where you want to attack the computer's board: ");
            int newYAttack = sc.nextInt();

            attack = new Coordinate(newXAttack,newYAttack);
        }
        return attack;
    }


    public void fireAndAttackComp(Computer comp,Coordinate attack){ //facilitate attack
        boards.printResult(boards.resultHitMissComputer(attack,comp));
    }
    public void printBoard(char[][] board){  //Her attackdan sonra boardi tesvir edir
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                System.out.print(board[i][j] + "       ");
            }
            System.out.println();
        }
    }

    public boolean verifyPlayerWin(Computer c){   //Playerin qazanib qazanmadigini check edir
        for (int i = 1; i < c.getBoardA().length; i++) {
            for (int j = 1; j < c.getBoardA()[0].length; j++) {
                if(c.getBoardA()[i][j] != '~' && boards.getBoardB()[i][j] != 'H'){
                    return false;
                }
            }
        }
        return true;
    }

    public char[][] getBoardA(){
        return boards.getBoardA();
    }

    public char[][] getBoardB(){
        return boards.getBoardB();
    }
}
