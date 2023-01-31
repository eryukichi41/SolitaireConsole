import java.util.ArrayList;

public class Pile {
    ArrayList<Card> pile;
    boolean pileEmpty;
    /**
     * Adds the first card to the pile
     * @param c The card that's added to the pile
     */
    public Pile(Card c){
        pile = new ArrayList<>();
        pile.add(c);
        pileEmpty = false;
    }
    /**
     * Adds card to the pile
     * @param c The card that is being added to the pile
     * @param rev If the card is going to be revealed or not
     */
    public void addToPile(Card c, boolean rev){
        c.isRevealed = rev;
        this.pile.add(c);
    }
    /**
     * Checks if the card can be stacked on the pile
     * @param c The card thats being checked
     * @return returns true if card can be stacked, returns false otherwise
     */
    public boolean canStack(Card c){
        System.out.println("Stacking Card: " + c.name);
        //Kings can be stacked on an empty pile
        if(c.value == 13 && this.pileEmpty){
            this.pileEmpty = false;
            return true;
        }
        //If the pile an empty only a king can stack
        else if (this.pileEmpty && c.value != 13)
            return false;
        else if(!c.color.equals(this.pile.get(this.pile.size() - 1).color) && c.value + 1 == this.pile.get(this.pile.size() - 1).value){
            return true;
        }
        return false;
    }
    /**
     * Setter for pileEmpty
     * @param empt true if the pile is empty
     */
    public void setPileEmpty(boolean empt){
        this.pileEmpty = empt;
    }

    public String toString(){
        String output = "";
        for(int i = 0; i < pile.size(); i++){
            output += pile.get(i).name + ", ";
        }
        return output;
    }
}
