public class GameBoard {
    Foundation f[];
    Pile p[];
    Deck deck;
    Card deckCard;

    public GameBoard(Deck d){
        this.deck = d;
         f = new Foundation[4];
        for(int i = 0; i < 4; i++){
            f[i] = new Foundation();
        }
        p = new Pile[7];
        for(int i = 0; i < 7; i++){
            p[i] = new Pile(this.deck.drawCard());
        }
        p[0].pile.get(0).setIsRevealed(true);
        for(int i = 1; i < 7; i++){
            for(int j = i; j < 7; j++){
                p[j].addToPile(this.deck.drawCard(), false);
            }
            p[i].pile.get(i).setIsRevealed(true);//Revealing the top card
        }
    }

    public String toString(){
        String output = "";
        output += "F1\tF2\tF3\tF4\n";
        for(int i = 0; i < 4; i++){
            if(!f[i].foundationEmpty)
                output = output + f[i].foundation.get(f[i].foundation.size() - 1).name + "\t";
            else
                output = output + "_\t";
        }
        output += "\n____________________________________________________\n";
        output += "\nP1\tP2\tP3\tP4\tP5\tP6\tP7\n";

        for(int j = 0; j < 20; j++){
            for(int i = 0; i < 7; i++){
                if(!p[i].pileEmpty && j < p[i].pile.size()){
                    if(p[i].pile.get(j).isRevealed)
                        output += p[i].pile.get(j).name + "\t";
                    else
                        output += "?\t";
                }
                else{
                    output += "\t";
                }
                
            }
            output += "\n";
        }
        output += "____________________________________________________\n";
        if(this.deck.deck.isEmpty()){
            output += "D: -";
        }
        else
            output += "D: " + this.deck.deck.get(0).name;
        output += "\n____________________________________________________\n";
        
        return output;

    }

    public boolean drawFromDeck(char location, int locIndex){
        Card c = this.deck.drawCard();
        if(location == 'P' || location == 'p'){
            if(p[locIndex].canStack(c)){
                p[locIndex].addToPile(c, true);
                System.out.println("The PILE: " + p[locIndex]);
                return true;
            }
        }
        else if(location == 'F'){
            if(f[locIndex].canStack(c)){
                f[locIndex].addToFoundation(c);
                f[locIndex].setFoundationSuit(c.suit);
                return true;
            }
            
        }
        this.deck.deck.add(0, c);
        return false;
    }

    public boolean cycleDeck(){
        return this.deck.cycle();
    }
    public boolean isDone(){
        for(int i = 0; i < 4; i++){
            if(this.f[i].foundationEmpty || this.f[i].foundation.get(this.f[i].foundation.size() - 1).value != 13){
                return false;
            }
        }   
        return true;
    }

    /**
    This method moves cards from either the deck or another pile to another pile
    @param destLoc This determines whether the pile will move to a foundation or another pile
    @param startIndex This is the index of the pile that the cards are being moved from
    @param destIndex This is the index of the pile or foundation the cards are being moved to
    @param carryAmount This is the number of cards being moved from one pile to another defaults to 1
    @return returns true if successfully moves cards returns false otherwise
    */
    public boolean moveFromPile(char destLoc, int startIndex, int destIndex, int carryAmount){
        if(destLoc == 'F'){
            if(f[destIndex].canStack(p[startIndex].pile.get(p[startIndex].pile.size() - 1))){
                f[destIndex].addToFoundation(p[startIndex].pile.get(p[startIndex].pile.size() - 1));
                p[startIndex].pile.remove(p[startIndex].pile.size() - 1);

                if(p[startIndex].pile.size() == 0){
                    p[startIndex].setPileEmpty(true);
                }
            }
            else
                return false;
        }
        else if(destLoc == 'P'){
            int carryStart = p[startIndex].pile.size() - carryAmount;
            if(carryStart < 0)
                return false;
            if(p[destIndex].canStack(p[startIndex].pile.get(carryStart))){
                for(int i = carryStart; i < carryStart + carryAmount; i++){
                    p[destIndex].addToPile(p[startIndex].pile.get(carryStart), true);
                    p[startIndex].pile.remove(carryStart);
                }
                
                if(p[startIndex].pile.size() == 0){
                    p[startIndex].setPileEmpty(true);
                }
            }
        }
        System.out.println(p[startIndex].pile.size() - 1);
        if(!p[startIndex].pileEmpty){
            if(!p[startIndex].pile.get(p[startIndex].pile.size() - 1).isRevealed){
                p[startIndex].pile.get(p[startIndex].pile.size() - 1).setIsRevealed(true);
            }
        }
        return true;
    }


    public boolean moveFromFoundation(int startIndex, int destIndex){
        if(p[destIndex].canStack(f[startIndex].foundation.get(f[startIndex].foundation.size() - 1))){
            p[destIndex].addToPile(f[startIndex].foundation.get(f[startIndex].foundation.size() - 1), true);
            f[startIndex].foundation.remove(f[startIndex].foundation.size() - 1);
            return true;
        }
        return false;
    }

    public boolean endOfGame(){
        for(int i = 0; i < 4; i++){
            if(f[i].foundation.size() < 13)
                return false;
        }
        return true;
    }
}
