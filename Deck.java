import java.util.ArrayList;

public class Deck {
    ArrayList<Card> deck;
    int placeIndex;
    public Deck(){
        deck = new ArrayList<>();
        String[] suits = {"Diamond","Heart","Club","Spade"};
        String col = "Red";
        for(String s : suits){
            if(s.equals("Diamond") || s.equals("Heart"))
                col = "Red";
            else
                col = "Black";
            for(int v = 0; v < 13; v++){
                Card c = new Card(v + 1, col, s);
                deck.add(c);
            }
        }
    }
    public String toString(){
        String output = "";
        int count = 1;
        for(Card c : deck){

            output = output + "\nCount " + count + ": " + c.name;
            count++;
        }
        return output;
    }
    /**
     * Takes card from the top of the deck
     * @return Returns card at the top of the deck
     */
    public Card drawCard(){
        Card c = this.deck.get(0);
        this.deck.remove(0);
        return c;
    }

    /**
     * Shuffles the deck so the cards are in a random order
     */
    public void shuffle(){
        ArrayList<Card> newDeck = new ArrayList<>();
        for(int i = 0; i < 52; i++){
            double rand = Math.random() * deck.size();
            Card c = deck.get((int)rand);
            newDeck.add(c);
            deck.remove((int)rand);
        }
        this.deck = newDeck;
    }
    /**
     * Pulls the card from the top of the deck for the user to look at, and places it at the bottom
     * of the deck afterwards
     * @return returns true if successful, return false otherwise
     */
    public boolean cycle(){
        if(this.deck.size() > 0){
            Card c = this.deck.get(0);
            System.out.println("Cycle Card is: " + c.name);
            this.deck.remove(0);
            this.deck.add(c);
            return true;
        }
        
        return false;
    }
}
