public class Battleship {
    public static void main(String[] args) {
        Player p = new Player();
        Computer c = new Computer();

        System.out.println("Welcome to the BattleShip Game! You will be playing against the computer.");
        p.playerSetup();
        c.compSetUp();

        while(!p.verifyPlayerWin(c) && !c.verifyCompWin(p)){
            //Player attacking the computer

            Coordinate attackOnComputer = p.playerGuessAttack();
            p.fireAndAttackComp(c,attackOnComputer);

            //Computer attacking the player

            Coordinate attackOnPlayer = c.getPredictedCoordinate(p);
            c.fireAndAttackPlayer(p,attackOnPlayer);

            //Print out Player's Boards

            System.out.println("\nYour Board A: ");
            p.printBoard(p.getBoardA());

            System.out.println("Your Board B: ");
            p.printBoard(p.getBoardB());
        }

        if(p.verifyPlayerWin(c)){
            System.out.println("GAME OVER! CONGRATS YOU WON!");
        }else if(c.verifyCompWin(p)){
            System.out.println("GAME OVER! YOU LOST, BETTER LUCK NEXT TIME!");
        }
    }
}
