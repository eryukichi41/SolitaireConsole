import java.util.ArrayList;

public class Foundation {
    String foundationSuit;
    ArrayList<Card> foundation;
    boolean foundationEmpty;

    public Foundation(){
        foundationSuit = "";
        this.foundationEmpty = true;
        foundation = new ArrayList<>();
    }

    public void setFoundationSuit(String s){
        this.foundationSuit = s;
    }

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

    public void addToFoundation(Card c){
        this.foundationEmpty = false;
        foundation.add(c);
    }

    public void setFoundation(Card c){
        if(canStack(c)){
            foundation.add(c);
        }
        else{
            System.out.println("Cannot stack");
        }
    }
}
