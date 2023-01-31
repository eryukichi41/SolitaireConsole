public class Card{
    int value;
    String color;
    String colorCode;
    String suit;
    String name;
    boolean isRevealed;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RED = "\u001B[31m";


    /**
     * Constructor for Card object
     * @param val The value of the card 
     * @param col The color of the card
     * @param st the suit of the card
     */
    public Card(int val, String col, String st){
        this.value = val;
        this.color = col;
        this.suit = st;

        String nam = "";
        switch(val){
            case 1:
                nam = "A";
                break;
            case 10:
                nam = "X";
                break;
            case 11:
                nam = "J";
                break;
            case 12:
                nam = "Q";
                break;
            case 13:
                nam = "K";
                break;
            default:
                nam = "" + val;
                break;
        }
        switch(st){
            case "Club":
                st = "%";
                break;
            case "Diamond":
                st = "*";
                break;
            case "Spade":
                st = "^";
                break;
            case "Heart":
                st = "&";
        }
        if(this.color == "Black")
            this.name = ANSI_BLUE + nam + st + ANSI_RESET;
        else if(this.color == "Red")
            this.name = ANSI_RED + nam + st + ANSI_RESET;
        else
            this.name = nam + st;
        this.isRevealed = false;
    }

    /**
     * Copy constructor for the card
     * @param source card that needs to be copied
     */
    public Card(Card source){
        this.value = source.value;
        this.color = source.color;
        this.suit = source.suit;
        this.name = source.name;
        this.isRevealed = source.isRevealed;
    }
    /**
     * setter for the isRevealed
     * @param rev
     */
    public void setIsRevealed(boolean rev){
        this.isRevealed = rev;
    }
    public String toString(){
        return this.name;
    }
}