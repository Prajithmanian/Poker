/*
 * This program is a computerized version of a Poker game.
 * This Project will characterize a poker hand according to the list,
 * Straight Flush, Four of a kind, Full House, Flush, Straight, Three of a kind,
 * Two pair, One pair, High card.
 * The project will also decide which hand wins, if there is more than one hand.
 * This Poker class contains the main function.
 *
 */

public class Poker {
    /* Number of cards for each player is 5 */
    private final static int NUM_OF_CARDS = 5;
    /* These 3 final integers are used for n-of-a-kind classifcation */
    private final static int FOUR = 4;
    private final static int THREE = 3;
    private final static int TWO = 2;
    /* Variables below are made public so that it can be used by other
        classes */
    public final static int NO_OF_CLASSIFICATION = 9;
    public final static int STRAIGHT_FLUSH = 0;
    public final static int FOUR_OF_A_KIND = 1;
    public final static int FULL_HOUSE = 2;
    public final static int FLUSH = 3;
    public final static int STRAIGHT = 4;
    public final static int THREE_OF_A_KIND = 5;
    public final static int TWO_PAIR = 6;
    public final static int ONE_PAIR = 7;
    public final static int HIGH_CARD = 8;
    /**
     * @param args the command line arguments
     * The args will have the hands: 1 or more.
     * For each card in the hand, a rank and suit is given. This is done for all
     * the cards.
     * Each argument is split into a rank and a suit, and each card is made.
     * 
     */
    public static void main(String[] args) {
        /* Check if there are no command line arguments and if the number of
           command line arguments is not a multiple of 5(number of cards per
           hand) */
        if ((args.length > 0) && (args.length % NUM_OF_CARDS != 0) ||
                (args.length == 0)) {
            System.out.println("Error: wrong number of arguments; "
                    + "must be a multiple of 5");
            System.exit(1);
        }

        String playerCard[] = new String[args.length];
        int i, j = 0, k = 0;
        int ret, high_rank = -1;
        int high_rank_double[] = new int[TWO];
        int high_rank_three_kind = -1;
        int not_high_card_flag = 0;
        /* this return value is used for n-of-a-kind, two pair, one pair
            classification */
        int ret_n_of_kind[] = new int[TWO];
        char ch1, ch2;
                
        /* Cards creation according to the number of args. Double array is used, 
            one for each player and other will have the cards */
        int numberOfPlayers = args.length / NUM_OF_CARDS;
        Cards c[][] = new Cards[numberOfPlayers][NUM_OF_CARDS];
        
        for (i = 0; i < args.length; i++) {
            /* Each argument is put into a string with all uppercase chars */
            playerCard[i] = args[i].toUpperCase();
            /* The string is split into 2 chars */
            ch1 = playerCard[i].charAt(0);
            ch2 = playerCard[i].charAt(1);
            
            /* For a player, Card is created and established with a rank and
                suit. If there are more than 5 args, another player is joined 
                and cards isestablished for that player. */
            if ((i != 0 )&& ((i % NUM_OF_CARDS) == 0)) {
                j++;
                k = i % 5;
            }
                /* empty constructor Cards */
                c[j][k] = new Cards();
                c[j][k].cardDesc(ch1, ch2);
            k++;
        }
        
        /* The classification array will have bits set to 1 if the hand falls
            onto any of the 9 classifications. Array value 0 is for Straight
            flush, 1 is for 4-of-a-kind, 2 is for full house, 3 is for flush,
            4 is for straight, 5 is for 3-of-a-kind, 6 is for two pair, 7 is 
            for one pair, 8 is for high card. */
        int classification[][] = new int[numberOfPlayers][NO_OF_CLASSIFICATION];
        for (j = 0; j < numberOfPlayers; j++)
            for (i = 0; i < NO_OF_CLASSIFICATION; i++)
                classification[j][i] = 0;
        
        /* compare array will be used to compare hands of different players */
        int compare[][] = new int[numberOfPlayers][NUM_OF_CARDS];
        for (j = 0; j < numberOfPlayers; j++) 
            for (i = 0; i < NUM_OF_CARDS; i++)
                compare[j][i] = -1;
        
        /* Classification of Hands for each player. Each hand checks against
            every possible classification. */
        for (j = 0; j < numberOfPlayers; j++) {
        
            /* Check if hand is a straight */
            ret = Hands.straight(c[j]);
            if (ret > -1) {
                classification[j][STRAIGHT] = 1;
                high_rank = ret;
            }
        
            /* Check if hand is a flush. If hand is already a straight and now
                if it is a flush too, then that means it is a straight flush. */
            ret = Hands.flush(c[j]);
            if (ret > -1) {
                if (classification[j][STRAIGHT] == 1) {
                    /* straight flush */
                    classification[j][STRAIGHT_FLUSH] = 1;
                    classification[j][STRAIGHT] = 0;
                } else {
                    classification[j][FLUSH] = 1;
                }
                high_rank = ret;
            }
        
            /* Check if hand is a Four of a kind. 
            Note: the return value is an array of 2 integers. For 4-of-a-kind 
                case only the first position of array will be required, the 
                second position will always be -1, because there cant be two 
                4-of-a-kinds in one hand.*/
            ret_n_of_kind = Hands.nOfAKind(c[j], FOUR);
            if (ret_n_of_kind[0] > -1) {
                classification[j][FOUR_OF_A_KIND] = 1;
                high_rank = ret_n_of_kind[0];
            }
        
            /* Check if hand is a Three of a kind
            Note: the return value is an array of 2 integers. For 3-of-a-kind 
                case only the first position of array will be required, the 
                second position will always be -1, because there cant be two 
                3-of-a-kinds in one hand */
            ret_n_of_kind = Hands.nOfAKind(c[j], THREE);
            if (ret_n_of_kind[0] > -1) {
                classification[j][THREE_OF_A_KIND] = 1;
                high_rank = ret_n_of_kind[0];
                high_rank_three_kind = high_rank;
            }
        
            /* Check if hand is a pair - one pair or two pair
            Note: the return value is an array of 2 integers. For a one pair 
                case only the first position of array will be required, the 
                second position will always be -1 and for two pair both the 
                positions of array will be required. If hand is already a three
                of a kind and now it is a pair, then that means it is a full
                house. */
            ret_n_of_kind = Hands.nOfAKind(c[j], TWO);
            for (i = 0; i < TWO; i++) {
                if (ret_n_of_kind[i] > -1) {
                    if (i == 0) {
                        if (classification[j][THREE_OF_A_KIND] == 1) {
                            classification[j][FULL_HOUSE] = 1;
                            classification[j][THREE_OF_A_KIND] = 0;
                            high_rank_double[0] = high_rank_three_kind;
                            high_rank_double[1] = ret_n_of_kind[0];
                            break;
                        }
                        classification[j][ONE_PAIR] = 1;
                        high_rank = ret_n_of_kind[i];
                        high_rank_double[i] = ret_n_of_kind[i];
                    }
                    if (i > 0) {
                        classification[j][TWO_PAIR] = 1;
                        //if (ret_n_of_kind[i] > ret_n_of_kind[i-1]) {
                        high_rank_double[i] = ret_n_of_kind[i];
                    }    
                }
            }
        
            /* If hand is not classified against anything, then that means it
                is high hand, HIGH_CARD bit is set */
            for (i = 0; i < HIGH_CARD; i++) {
                if (classification[j][i] != 0) {
                    not_high_card_flag = 1;
                }
            }
            if (not_high_card_flag != 1) {
                classification[j][HIGH_CARD] = 1;
                high_rank = Hands.maxRank(c[j], 1);
            }
            not_high_card_flag = 0;
            
            /* Printing output: hand classification */
            Print.printMessage(classification, high_rank, high_rank_double, j);
        }    
        
        /* Finding out the winner if number of players is greater than 1 */
        if (numberOfPlayers > 1) {
            Print.winner(classification, numberOfPlayers, c, compare);
        }
    }
}
