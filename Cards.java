/*
 * This Class represents cards with it's ranks and suits.
 * There are two enums: 
 *      1. rank, which gives the value of the card, from 2 to 10 and face cards
 *      2. suit, which gives the type of the card, Spades, Clubs, Diamonds, 
 *          Hearts.
 *
 */
enum rank {
    Two,
    Three,
    Four,
    Five,
    Six,
    Seven,
    Eight,
    Nine,
    Ten,
    Jack,
    Queen,
    King,
    Ace
}

enum suit {
    Spades,
    Clubs,
    Diamonds,
    Hearts;
}

public class Cards {
    private rank rankOfCard;
    private suit suitOfCard;
    
    /*
     * Empty Constructor for Cards that need not intialize values for now
     */    
    public Cards() {
        
    }
    
    /*
     * Constructor for Cards which initializes values for rank and suit
     */
    public Cards(rank r, suit s) {
        this.rankOfCard = r;
        this.suitOfCard = s;
    }
    
    /* getRank()
     *      Description:
     *           This getter method will return the object's/card's rank
     *       Input:
     *           None
     *       Output:
     *           Return the rank
     */
    public rank getRank() {
        return this.rankOfCard;
    }
    
    /* getSuit()
     *      Description:
     *           This getter method will return the object's/card's suit
     *       Input:
     *           None
     *       Output:
     *           Return the suit
     */
    public suit getSuit() {
        return this.suitOfCard;
    }
    
    /*
     * cardDesc()
     *      Description:
     *           This method describes each hand. It assigns each hand 
     *           ranks and suits for the cards.
     *      Input: 
     *           1. char rank
     *           2. char suit
     *      Output:
     *           Produces the card and then can be used for classification.
     */
    public void cardDesc(char rank, char suit) {
        giveRank(rank, suit);
        giveSuit(suit, rank);
    }
    
    /*
     * giveRank()
     *      Description:
     *           This method assigns a rank for a card.
     *      Input:
     *           1. char r - the rank
                 2. char s - the suit
     *      Output:
     *           returns nothing. object is given a rank enum value
     *           
     */
    public void giveRank(char r, char s) {
        switch(r) {
            case '2': 
                this.rankOfCard = rank.Two;
                break;
            case '3':
                this.rankOfCard = rank.Three;
                break;
            case '4':
                this.rankOfCard = rank.Four;
                break;
            case '5':
                this.rankOfCard = rank.Five;
                break;
            case '6':
                this.rankOfCard = rank.Six;
                break;
            case '7':
                this.rankOfCard = rank.Seven;
                break;
            case '8':
                this.rankOfCard = rank.Eight;
                break;
            case '9':
                this.rankOfCard = rank.Nine;
                break;
            case 'T':
                this.rankOfCard = rank.Ten;
                break;
            case 'J':
                this.rankOfCard = rank.Jack;
                break;
            case 'Q':
                this.rankOfCard = rank.Queen;
                break;    
            case 'K':
                this.rankOfCard = rank.King;
                break;    
            case 'A':
                this.rankOfCard = rank.Ace;
                break;
            default:
                invalidCard(r, s);
                break;
        }
    }
    
    /*
     * giveSuit()
     *      Description: This method assigns a suit for a card.
     *      Input:
     *           1. char s - the suit
                 2. char r - the rank
     *      Output:
     *           reutrns nothing. object is given a suit enum value
     */
    public void giveSuit(char s, char r) {
        switch(s) {
            case 'S':
                this.suitOfCard = suit.Spades;
                break;
            case 'H':
                this.suitOfCard = suit.Hearts;
                break;
            case 'D':
                this.suitOfCard = suit.Diamonds;
                break;    
            case 'C':
                this.suitOfCard = suit.Clubs;
                break;    
            default:
                invalidCard(r, s);
                break;
        }
    }
    
    /*
     * invalidCard()
     *       Description: This method will print out the error if the card is
     *           not valid
     *       Input:
     *          1. rank_invalid - invlid rank
                2. suit_invalid - invalid suit
     *       Output:
     *          None. Prints error message.
     */
    public void invalidCard(char rank_invalid, char suit_invalid) {
        System.out.printf("Error: invalid card name '%c%c'\n", rank_invalid,
                suit_invalid);
        System.exit(1);
    }
}
