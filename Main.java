import java.util.Scanner;

public class Main {
    public static boolean checkSyntax(String opt){
        if(opt == ""){
            opt = "Q";
        }
        switch(opt.charAt(0)){
            case 'A':
                if(opt.length() == 7 && opt.charAt(5) == 'P'){
                    if(opt.charAt(1) == ' ' && opt.charAt(4) == ' '){
                        if(Character.getNumericValue(opt.charAt(3)) < 7 && Character.getNumericValue(opt.charAt(6)) < 7)
                            return true;
                    }
                }
                else if(opt.length() == 7 && opt.charAt(5) == 'F'){
                    if(opt.charAt(1) == ' ' && opt.charAt(4) == ' '){
                        if(Character.getNumericValue(opt.charAt(3)) < 7 && Character.getNumericValue(opt.charAt(6)) < 7)
                            return true;
                    }
                }
                else
                    return false;
            break;
            case 'B':
            break;
            case 'C':

            break;
            case 'D':

            break;
            default:
            break;
            
        }
        return true;
    }

    public static void makeMove(GameBoard gb, String opt){
        char com = ' ';
        opt = opt.toUpperCase();
        if(opt != "")
            com = opt.charAt(0);
        boolean syntaxCorrect = checkSyntax(opt);
        switch(com){
            case 'A'://Move from pile to foundation or another pile
                if(syntaxCorrect){
                    Scanner scan = new Scanner(System.in);
                    int carryAmount = 0;
                    if(opt.charAt(5) == 'P'){
                        System.out.print("How many cards would you like to carry? ");
                        carryAmount = scan.nextInt();
                    }
                    gb.moveFromPile(opt.charAt(5), Character.getNumericValue(opt.charAt(3)) - 1, Character.getNumericValue(opt.charAt(6)) - 1, carryAmount);
                }
                com = 'q';
            break;
            case 'B':
                if(syntaxCorrect){
                    gb.moveFromFoundation(Character.getNumericValue(opt.charAt(3)) - 1, Character.getNumericValue(opt.charAt(6)) - 1);
                }
            break;
            case 'C':
                gb.cycleDeck();
            break;
            case 'D':
                gb.drawFromDeck(opt.charAt(2),Character.getNumericValue(opt.charAt(3)) - 1);
            break;
            default:
                System.out.println("Type correct command!");
            break;
        }
        System.out.println(gb);
        
    }

    public static void main(String args[]){


        Deck deck = new Deck();
        deck.shuffle();
        GameBoard gb = new GameBoard(deck);
        System.out.println(gb);
        Scanner scan = new Scanner(System.in);
        String opt = "";
        while(!gb.isDone()){
            opt = scan.nextLine();
            makeMove(gb, opt);
            if(gb.endOfGame()){
                System.out.println("Congrats you win!");
                break;
            }
        }
        //System.out.println(deck);
        scan.close();
    }
}
