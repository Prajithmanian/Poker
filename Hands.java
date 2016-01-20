/*
 * This class represents the classfication of the poker hand. This class 
 * ultimately decides the poker hand and classifies it into any of the 9 ones - 
 * Straight Flush, Four of a kind, Full House, Flush, Straight, Three of a kind, 
 * Two pair, One pair, High card. 
 * This class will also have methods for comparing hands if hands have same
 * classification.
 *
 */

public class Hands {
    private final static int NO_OF_RANKS = 13;
    /*
     * straight()
     *   Description:
     *       This method will check the hand and tell whether it is a straight
     *       or not. First sorting out ranks and then finding if they are in 
     *       order
     *   Input:
     *       Cards c[] - The hand itself
     *   Output:
     *       highest rank if straight
     *       -1 if not a straight
     */
    public static int straight(Cards c[]) {
        int i;
        int current_rank = -1, next_rank = -1;
        
        /* Sort cards according to rank */
        c = sortCards(c);
        
        for (i = 0; i < c.length - 1; i++) {
            current_rank = c[i].getRank().ordinal();
            if (current_rank > -1) {
                next_rank = c[i+1].getRank().ordinal();
                if (next_rank != current_rank + 1) {
                    return (-1);
                }
            }
        }
        return (next_rank);
    }
    
    /*
     * flush()
     *   Description:
     *       This method will check the hand and tell whether it is a flush
     *       or not.
     *   Input:
     *       Cards c[] - The hand itself
     *   Output:
     *       return the highest rank if flush
     *       return -1 if not a flush
     */
    public static int flush(Cards c[]) {
        int i;
        int current_suit = -1, next_suit = -1;
        int max_rank;

        for (i = 0; i < c.length - 1; i++) {
            current_suit = c[i].getSuit().ordinal();
            if (current_suit > -1) {
                next_suit = c[i+1].getSuit().ordinal();
                if (next_suit != current_suit) {
                    return (-1);
                }
            }
        }
        
        /* finding the highest rank of the cards*/
        max_rank = maxRank(c, 1);
        
        return max_rank;
    }
    
    /*
     * nOfAKind()
     *   Description:
     *       This method will check the hand and tell whether it is a
     *       n-of-a-kind or not.
     *   Input:
     *       Cards c[] - The hand itself
     *       number - the count. If count is 4 - 4 of a kind
     *                           If count is 3 - 3 of a kind
     *                           If count is 2 - a pair
     *   Output:
     *       return the rank if 4 of a kind, 3 of a kind, pair.
     *       return -1 if not
     */
    public static int[] nOfAKind(Cards c[], int number) {
        int i, j = 0;
        int current_rank = -1;
        int count[] = new int[NO_OF_RANKS];
        int ret[] = new int[2];
        
        for (i = 0; i < NO_OF_RANKS; i++) {
            count[i] = 0;
        }
        
        for (i = 0; i < 2; i++) {
            ret[i] = -1;
        }
        /* number of a kind */
        for (i = 0; i < c.length; i++) {
            current_rank = c[i].getRank().ordinal();
            if (current_rank > -1) {
                count[current_rank]++;
            }
        }
        
        for (i = 0; i < NO_OF_RANKS; i++) {
            if (count[i] == number) {
                ret[j++] = i;
            }
        }
        return ret;
    }
    
    /*
     * maxRank()
     *   Description:
     *       This method will return the nth max rank of the hand.
     *   Input:
     *       Cards c[] - The hand itself
     *       int nth - nth highest rank
     *   Output:
     *       return the nth highest rank
     */
    public static int maxRank(Cards c[], int nth) {
        int n_max_rank;
        c = sortCards(c);
        n_max_rank = c[c.length - nth].getRank().ordinal(); 
        return n_max_rank;
    }
    
    /*
     * sortCards()
     *   Description:
     *       This method will sort the cards according to rank
     *   Input:
     *       Cards c[] - The hand itself
     *   Output:
     *       return the sorted cards.
     */
    public static Cards[] sortCards(Cards c[]) {
        int i, j, min;
        Cards temp;
        /* Sort cards according to rank */
        for (i = 0; i < c.length-1; i++) {
            min = i;
            for (j = i+1; j < c.length; j++) {
                if (c[j].getRank().ordinal() < c[min].getRank().ordinal()) {
                     min = j;
                }
            }
            temp = c[i];
            c[i] = c[min];
            c[min] = temp;
        }
        return c;
    }
    
    /*
     * compareHands()
     *   Description:
     *       This method will compare between two hands.
     *   Input:
     *       int compare1[] - The compare array of player 1
     *       int compare2[] - The compare array of player 2
     *       int player1 - Player 1
     *       int player2 - Player 2
     *   Output:
     *       return the player which has better hand and -1 if all cards
     *           similar.
     */
    public static int compareHands(int compare1[], int compare2[], int player1,
                int player2) {
        int i;
        for (i = 0; i < compare1.length; i++) {
            if (compare1[i] > compare2[i]) {
                return player1;
            } else if(compare1[i] < compare2[i]) {
                return player2;
            }
        }       
        return -1;
    }
    
    /*
     * comparisonCreation()
     *   Description:
     *       This method will create a compare array for the given player. This
                compare array will be used to compare between hands if both
                hands have same classification.
     *   Input:
     *       int compare[] - The compare array of player
     *       int classification[] - The classification array of player which is
                to create compare array.
     *       Cards c[] - Player hand
     *   Output:
     *       return the player which has better hand and -1 if all cards
     *           similar.
     */
    public static int[] comparisonCreation(int compare[], int classification[],
            Cards c[]) {
        int i, j, k;
        int current_rank, next_rank;
        int ret[];
        
        c = sortCards(c);
        
        if (classification[Poker.STRAIGHT_FLUSH] == 1) {
            /* If straight flush, compare array will have all ranks in desc
                order */
            for (i = c.length - 1; i >= 0; i--) {
                compare[c.length - 1 - i] = c[i].getRank().ordinal();
            }
        } else if(classification[Poker.FOUR_OF_A_KIND] == 1) {
            /* If 4-of-a-kind, compare array will just have the rank of the 
                4-of-a-kind card and the rank of the other card */
            ret = nOfAKind(c, 4);
            for (i = 0; i < c.length; i++) {
                current_rank = c[i].getRank().ordinal();
                if (current_rank == ret[0]) {
                    compare[0] = current_rank;
                } else {
                    compare[1] = current_rank;
                }   
            }
        } else if (classification[Poker.FULL_HOUSE] == 1) {
            /* If full house, compare array will just have the rank of the 
                3-of-a-kind card and the rank of the pair card */
            ret = nOfAKind(c, 3);
            for (i = 0; i < c.length; i++) {
                current_rank = c[i].getRank().ordinal();
                if (current_rank == ret[0]) {
                    compare[0] = current_rank;
                } else {
                    compare[1] = current_rank;
                }
            }
        } else if (classification[Poker.FLUSH] == 1) {
            /* If flush, compare array will have all ranks in desc
                order */
            for (i = c.length-1; i >= 0; i--) {
                compare[c.length -1 - i] = c[i].getRank().ordinal();
            }
        } else if(classification[Poker.STRAIGHT] == 1) {
            /* If straight, compare array will have all ranks in desc
                order */
            for (i = c.length-1; i >= 0; i--) {
                compare[c.length -1 - i] = c[i].getRank().ordinal();
            }
        } else if (classification[Poker.THREE_OF_A_KIND] == 1) {
            /* If 3-of-a-kind, compare array will just have the rank of the 
                3-of-a-kind card and the rank of the other cards in desc 
                order */
            ret = nOfAKind(c, 3);
            j = 1;
            for (i = 0; i < c.length; i++) {
                current_rank = c[i].getRank().ordinal();
                if (current_rank == ret[0]) {
                    compare[0] = current_rank;
                    i++;
                } else {
                    compare[j++] = current_rank;
                }
            }
        } else if (classification[Poker.TWO_PAIR] == 1) {
            /* If two pair, compare array will just have the rank of both the 
                pairs and the rank of the other card */
            ret = nOfAKind(c, 2);
            j = 2;
            k = 1;
            for (i = 0; i < c.length; i++) {
                current_rank = c[i].getRank().ordinal();
                if (current_rank == ret[0] || current_rank == ret[1]) {
                    compare[k--] = current_rank;
                    i++;
                } else {
                    compare[j++] = current_rank;
                }
            }
        } else if (classification[Poker.ONE_PAIR] == 1) {
            /* If one pair, compare array will just have the rank of the 
                pair card and the rank of the other cards in desc order */
            ret = nOfAKind(c, 2);
            j = 1;
            for (i = 0; i < c.length; i++) {
                current_rank = c[i].getRank().ordinal();
                if (current_rank == ret[0]) {
                    compare[0] = current_rank;
                    i++;
                } else {
                    compare[j++] = current_rank;
                }
            }
        } else {
            /* If high card, compare array will have all ranks in desc
                order */
            for (i = c.length-1; i >= 0; i--) {
                compare[c.length -1 - i] = c[i].getRank().ordinal();
            } 
        }        
        return compare;
    }    
}
