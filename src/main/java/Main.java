import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        if(checkArgMoves(args)){
            boolean isRunning = true;
            while(isRunning){
                String moveOfComputer = makeMoveOfComputer(args);
                HMAC hmac = new HMAC();
                System.out.println("HMAC: " + hmac.makeHmac(moveOfComputer));
                String moveOfUser = makeMove(args);
                switch(moveOfUser){
                    case "0": {
                        isRunning = false;
                        break;
                    }
                    case "?":{
                        Regulation.show(args);
                        break;
                    }
                    default:{
                        isRunning = false;
                        moveOfUser = args[Integer.parseInt(moveOfUser.trim())-1];
                        System.out.println("Your move: " + moveOfUser + ".");
                        System.out.println("Computer move: " + moveOfComputer + ".");
                        findWinner(moveOfUser,moveOfComputer,args);
                        System.out.println("HMAC key:" + hmac.getKey());
                        break;
                    }
                }
            }
        }
        else System.out.println("Input Error. Try again. For example: rock paper spoke.");
    }

    static boolean checkArgMoves(String[] moves){
        if(moves.length<3||moves.length % 2 == 0)
            return false;
        for(int i=0; i<moves.length; i++) {
            for (int j=i+1; j<moves.length; j++)
                if(moves[i].equals(moves[j])) return false;
        }
        return true;
    }

    static void showMenu(String[] moves){
        for(int i = 0;i<moves.length;i++)
            System.out.println(i+1+" - "+ moves[i]);
        System.out.println("0 - exit");
        System.out.println("? - help");
        System.out.print("Enter your move: ");
    }

    static String makeMove(String[] moves){
        Scanner in = new Scanner(System.in);
        String moveOfUser;
        showMenu(moves);
        while(!checkMoveOfUser(moves.length, moveOfUser = in.nextLine())){
            System.out.println("Input error. Try again.");
            showMenu(moves);
        }
        return moveOfUser;
    }

    static boolean checkMoveOfUser(int numberOfMoves, String moveOfUser){
        if(moveOfUser.equals("?")) return true;
        else{
            try {
                int moveOfUserInt = Integer.parseInt(moveOfUser.trim());
                if(moveOfUserInt >= 0 && moveOfUserInt <= numberOfMoves) return true;
            }
            catch (NumberFormatException nfe) {
                System.out.print("");
            }
        }
        return false;
    }

    static String makeMoveOfComputer(String[] moves){
        SecureRandom rnd = new SecureRandom();
        try {
            rnd = SecureRandom.getInstance("SHA1PRNG");
        } catch (NoSuchAlgorithmException ex) {
            System.out.print("");
        }
        String moveOfComputer = moves[rnd.nextInt(moves.length-1)];
        return moveOfComputer;
    }

    static void findWinner(String moveOfUser, String moveOfComputer, String[] moves){
        int indexMoveOfUser = 0,indexMoveOfComputer = 0;
        for (int i = 0; i < moves.length; i++) {
            if (moves[i].equals(moveOfUser))  indexMoveOfUser = i;
            if (moves[i].equals(moveOfComputer))  indexMoveOfComputer = i;
        }
        Winner winner = new Winner(indexMoveOfUser,indexMoveOfComputer,moves.length);
        System.out.println(winner.findWinner() + "!");
    }
}
