import java.util.ArrayList;

public class Foundation {
    String foundationSuit;
    ArrayList<Card> foundation;
    boolean foundationEmpty;

    //Constructor
    public Foundation(){
        foundationSuit = "";
        this.foundationEmpty = true;
        foundation = new ArrayList<>();
    }
    /**
     * Setter for foundationSuit
     * @param s String that sets the suit of foundation
     */
    public void setFoundationSuit(String s){
        this.foundationSuit = s;
    }
    /**
     * Checks if a card can be set on the foundation
     * @param c the card object that is being checked
     * @return returns true if card can be stacked
     */
    public boolean canStack(Card c){
        if(c.suit == this.foundationSuit && c.value == this.foundation.get(foundation.size() - 1).value + 1){
            System.out.println("Can foundation");
            return true;
        }
        else if(c.value == 1 && foundationEmpty){
            this.foundationSuit = c.suit;
            System.out.println("Can foundation Ace");
            return true;
        }
        System.out.println("Can't foundation");
        return false;
    }
    /**
     * Adds card to foundation
     * @param c This card will be added to the foundation
     */
    public void addToFoundation(Card c){
        //If there is a card in the foundation, it is not empty
        this.foundationEmpty = false;
        foundation.add(c);
    }
    /**
     * If card can be stacked it is added to the foundation
     * @param c Card that is being added to the foundation
     */
    public void setFoundation(Card c){
        if(canStack(c)){
            foundation.add(c);
        }
        else{
            System.out.println("Cannot stack");
        }
    }
}
