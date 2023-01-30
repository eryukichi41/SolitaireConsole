import java.util.ArrayList;

public class Pile {
    ArrayList<Card> pile;
    boolean pileEmpty;

    public Pile(Card c){
        pile = new ArrayList<>();
        pile.add(c);
        pileEmpty = false;
    }

    public void addToPile(Card c, boolean rev){
        c.isRevealed = rev;
        this.pile.add(c);
    }

    public boolean canStack(Card c){
        System.out.println("Stacking Card: " + c.name);
        if(c.value == 13 && this.pileEmpty){
            this.pileEmpty = false;
            return true;
        }
        else if(!c.color.equals(this.pile.get(this.pile.size() - 1).color) && c.value + 1 == this.pile.get(this.pile.size() - 1).value){
            System.out.println("Can stack");
            return true;
        }
        System.out.println("Can't stack");
        return false;
    }

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
