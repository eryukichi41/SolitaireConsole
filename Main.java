import java.util.Scanner;

import javax.swing.plaf.synth.SynthSpinnerUI;


public class Main {
    /**
     * Checks the syntax of the user's input
     * @param opt User's input
     * @return Returns true if the user input's syntax is correct 
     */
    public static boolean checkSyntax(String opt){
        //Assigns something to the string if it is empty
        if(opt == ""){
            opt = "Q";
        }
        //Looks at the first character of the string to see the specific syntax for that command
        switch(opt.charAt(0)){
            case 'A'://Syntax for moving from pile to pile, or pile to foundation
                if(opt.length() == 7 && opt.charAt(5) == 'P'){
                    if(opt.charAt(1) == ' ' && opt.charAt(4) == ' '){
                        //Makes sure numbers are in the correct range
                        if(Character.getNumericValue(opt.charAt(3)) < 8 && Character.getNumericValue(opt.charAt(6)) < 8)
                            return true;
                    }
                }
                //Syntax for moving from pile to foundation
                else if(opt.length() == 7 && opt.charAt(5) == 'F'){
                    //Makes sure numbers are in the correct range
                    if(opt.charAt(1) == ' ' && opt.charAt(4) == ' '){
                        if(Character.getNumericValue(opt.charAt(3)) < 8 && Character.getNumericValue(opt.charAt(6)) < 5)
                            return true;
                    }
                }
                else
                    return false;
            break;
            case 'B'://Syntax for moving from foundation to pile
                if(opt.length() == 7 && opt.charAt(5) == 'P'){
                    if(opt.charAt(1) == ' ' && opt.charAt(4) == ' '){
                        //Making sure the destination index is in a valid range
                        if(Character.getNumericValue(opt.charAt(3)) < 7 && Character.getNumericValue(opt.charAt(6)) < 7)
                            return true;
                    }
                }
            break;
            case 'C'://Syntax for cycling through the deck
                if(opt.equals("C")){
                    return true;
                }
            break;
            case 'D'://Syntax for drawing from the deck to a pile or foundation
                if(opt.length() == 4){
                    //Making sure the destination is correct
                    if(opt.charAt(2) == 'P' || opt.charAt(2) == 'F'){
                        int destIndex = Character.getNumericValue(opt.charAt(3));
                        if(destIndex > 0 && destIndex < 8){
                            return true;
                        }
                    }
                }
            break;
            default:
            break;
            
        }
        System.out.println("Type correct syntax!");
        return false;
    }
    /**
     * Takes the user's input and makes the move that they want to make
     * @param gb Holds all of the game information
     * @param opt String containing the user input
     */
    public static void makeMove(GameBoard gb, String opt){
        char com = ' ';
        opt = opt.toUpperCase();
        //Avoid indexing empty string
        if(opt != "")
            com = opt.charAt(0);
        //Makes sure syntax is correct before using it to perfor  action
        boolean syntaxCorrect = checkSyntax(opt);
        switch(com){
            case 'A'://Move from pile to foundation or another pile
                if(syntaxCorrect){
                    Scanner scan = new Scanner(System.in);
                    int carryAmount = 0;
                    if(opt.charAt(5) == 'P'){//How many cards from the pile will be moved
                        System.out.print("How many cards would you like to carry? ");
                        carryAmount = scan.nextInt();
                    }
                    gb.moveFromPile(opt.charAt(5), Character.getNumericValue(opt.charAt(3)) - 1, Character.getNumericValue(opt.charAt(6)) - 1, carryAmount);
                }
            break;
            case 'B'://Move from foundation to pile
                if(syntaxCorrect)
                    gb.moveFromFoundation(Character.getNumericValue(opt.charAt(3)) - 1, Character.getNumericValue(opt.charAt(6)) - 1);
            break;
            case 'C'://Cycle through the deck
                if(syntaxCorrect)
                    gb.cycleDeck();
            break;
            case 'D'://Draw from the deck and place it on a pile or a foundation
                if(syntaxCorrect)
                    gb.drawFromDeck(opt.charAt(2),Character.getNumericValue(opt.charAt(3)) - 1);
            break;
            case 'X'://If player concedes the game will end
                gb.setHasConceded(true);
                System.out.println("Thanks for playing!");
            break;
            default://Incorrect command
                System.out.println("Type correct command!");
            break;
        }
        System.out.println(gb);
    }

    public static void main(String args[]){
        Deck deck = new Deck();
        deck.shuffle();
        GameBoard gb = new GameBoard(deck);

        System.out.println("Welcome to solitare! This is how to play:\n"+
        "The goal is to get all of the cards into their respective foundations in order from ace to king\n"+
        "-You achieve this by revealing all of the cards by moving cards from one pile to another or foundation\n"+
        "-You can also take cards from the deck and place them on a pile or foundation to hel preveal more \n" + 
        "cards\n" +
        "-You can only place a card on a pile card if it is in descending order and the opposite color\n" +
        "-Only kings can be moved to empty piles\n" +
        "You move cards around by typing commands:\n" + 
        "A) Moving a card from one pile to another pile or a foundation\n" +
        "B) Moving a card from the foundation to a pile\n" + 
        "C) Cycle through the deck of cards\n" + 
        "D) Place cards from the deck onto a foundation or pile" + 
        "The syntax is as follows:\n" +
        "The first letter determines the action\n" + 
        "F and P represent the foundation and pile respectively\n" +
        "The number following the F or P specifies which foundation or pile\n" +
        "A) A P1 F1 or A P1 P2\n" +
        "B) B F1 P1\n" +
        "C) C\n" + 
        "D) D F1 or D P1\n" +
        "Enjoy!");




        System.out.println(gb);
        Scanner scan = new Scanner(System.in);
        String opt = "";

        




        //Game continues until player wins or concedes
        while(!gb.endOfGame() && !gb.getHasConceded()){
            opt = scan.nextLine();
            makeMove(gb, opt);
        }
        if(gb.getHasConceded())
            System.out.println("Congrats you win!!");
        else
            System.out.println("Thanks for playing!");
        scan.close();
    }
}
