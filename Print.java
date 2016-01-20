/*
 * This class Print will just print the necessary messages on the console.
 *  
 */
public class Print {
    
    /*
     * printMessage()
     *   Description:
     *       This method will print according the output according to the 
     *       classification
     *   Input:
     *       int classification[] - classification array will have the type of
     *           classification hand bit set
     *       int high rank - highest rank of the hand, used for all hands except
     *           full house and two pair.
     *       int high_rank_double[] - highest rank, used for full house and
     *           two-pair.
     *       int player - Player number
     *   Output:
     *       prints the message on console according to hand classification.
     */
    public static void printMessage(int classification[][], int high_rank,
                    int high_rank_double[], int player) {
        String temp1, temp2;
        int j = player;
        int temp1_ordinal = -1, temp2_ordinal = -1;
        if (classification[j][Poker.STRAIGHT_FLUSH] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: %s-high straight flush\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: %s-high straight flush\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.FOUR_OF_A_KIND] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: Four %ss\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: Four %ss\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.FULL_HOUSE] == 1) {
            if (high_rank_double[0] < 9) {
                temp1 = Integer.toString(high_rank_double[0] + 2);
            } else {
                temp1 = (rank.values()[high_rank_double[0]]).toString();
            }
            
            if (high_rank_double[1] < 9) {
                temp2 = Integer.toString(high_rank_double[1] + 2);
            } else {
                temp2 = (rank.values()[high_rank_double[1]]).toString();
            }
            
            System.out.printf("Player %d: %ss full of %ss\n",
                    j+1, temp1, temp2);
        } else if (classification[j][Poker.FLUSH] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: %s-high flush\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: %s-high flush\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.STRAIGHT] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: %s-high straight\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: %s-high straight\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.THREE_OF_A_KIND] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: Three %ss\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: Three %ss\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.TWO_PAIR] == 1) {
            if (high_rank_double[0] < 9) {
                temp1 = Integer.toString(high_rank_double[0] + 2);
            } else {
                temp1 = (rank.values()[high_rank_double[0]]).toString();
                temp1_ordinal = (rank.values()[high_rank_double[0]]).ordinal();
            }
            
            if (high_rank_double[1] < 9) {
                temp2 = Integer.toString(high_rank_double[1] + 2);
            } else {
                temp2 = (rank.values()[high_rank_double[1]]).toString();
                temp2_ordinal = (rank.values()[high_rank_double[1]]).ordinal();
            }
            if(temp1_ordinal > temp2_ordinal) {
                System.out.printf("Player %d: %ss over %ss\n",
                        j+1, temp1, temp2);
            } else {
                System.out.printf("Player %d: %ss over %ss\n",
                        j+1, temp2, temp1);
            }
        } else if (classification[j][Poker.ONE_PAIR] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: Pair of %ss\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: Pair of %ss\n",
                        j+1, rank.values()[high_rank]);
            }
        } else if (classification[j][Poker.HIGH_CARD] == 1) {
            if (high_rank < 9) {
                System.out.printf("Player %d: %s-high\n",
                        j+1, high_rank + 2);
            } else {
                System.out.printf("Player %d: %s-high\n",
                        j+1, rank.values()[high_rank]);
            }
        }       
    }
    
    /*
     * winner()
     *   Description:
     *       This method will find out the winner or a draw and print
     *       accordingly.
     *   Input:
     *       int classification[][] - classification double array will have the
     *       classification bits set for all the players
     *       int numberOfPlayers - number of players
     *       Cards c[][] - cards of every player
     *       int compare[][] - this will have comparison array for each player
     *           and it used for comparing between hands.
     *   Output:
     *       prints the winner or draw message on console.
     */
    public static void winner(int classification[][], int numberOfPlayers,
                    Cards c[][], int compare[][]) {
        int i, j = 0;
        int win[] = new int[numberOfPlayers];
        int winner = -1, winner_temp;
        int winner_class = Poker.NO_OF_CLASSIFICATION + 1;
        int draw[] = new int[numberOfPlayers], draw_flag = -1;
        int same_class_hand_max[] = new int[numberOfPlayers];
        int count = 0, same_hand_flag = 0;
        int win_same_hand[] = new int[numberOfPlayers];
        int no_winner = 0;
        
        /* For each player win[] will have the hand classification*/
        for (i = 0; i < numberOfPlayers; i++) {
            for (j = 0; j < Poker.NO_OF_CLASSIFICATION; j++) {
                if(classification[i][j] == 1) {
                    win[i] = j;
                }
            }    
        }
        
        winner = 0;
        /* Finding the player with the best classification of hand. winner_class
            will have the best hand classification and winner will have the 
            player number */
        for (i = 0; i < numberOfPlayers; i++) {
            if (win[i] <= winner_class) {
                winner_class = win[i];
                winner = i;
            }
            /* comparison creation used for comparing between player hands */
            compare[i] = Hands.comparisonCreation(compare[i], classification[i],
                    c[i]);
            same_class_hand_max[i] = -1;
            win_same_hand[i] = -1;
            draw[i] = -1;
        }
        
        
        /* If players have same classification of best hands, increment count
            and set a flag indicating same hand classification, and then use 
            another array same_class_hand_max[] indicating draw for players */
        j = 0;
        for (i = 0; i < numberOfPlayers; i++) {
            if (winner_class == win[i]) {
                if (count > 0) {
                    same_hand_flag = 1;
                }
                same_class_hand_max[i] = 1;
                count++;
            }
        }
        
        /* Use the same_class_hand_max[] calculated previously to pick a winner
            between the same classification of hands. Each player for a draw is
            compared with the other and an array win_same_hand[] will have 
            number of wins for each player */
        if (same_hand_flag == 1) {
            for (i = 0; i < numberOfPlayers - 1; i++) {
                for (j = i + 1; j < numberOfPlayers; j++) {
                    if (same_class_hand_max[j] == 1 &&
                                same_class_hand_max[i] == 1) {
                        winner_temp = Hands.compareHands(compare[j],
                                    compare[i], j, i);
                        if (winner_temp == j)
                            win_same_hand[j]++;
                        else if (winner_temp == i)
                            win_same_hand[i]++;
                        else {
                            if (win_same_hand[i] < 0 && win_same_hand[j] < 0) {
                                win_same_hand[i] = win_same_hand[j] = 0;
                            } 
                        }
                            
                    }
                }
            }
            
            /* A winner is found between the drawn hands according to the 
                previously calculated win_same_hand[] */
            int temp_win = -2;
            for(i = 0; i < numberOfPlayers; i++) {
                if (win_same_hand[i] > temp_win){
                    temp_win = win_same_hand[i];
                    winner = i;
                } 
            }
            
            /* The below will indicate a draw between hands and an array draw[]
                is used to store all drawn hands. */
            j = 0;
            count = 0;
            for (i = 0; i < numberOfPlayers; i++) {
                if (win_same_hand[i] == temp_win) {
                    draw[j++] = i;
                    if (count > 0)
                        draw_flag = temp_win;
                    count++;
                }
            }
            
            /* This will print draw messages */
            if ((draw_flag >= 0) && (win_same_hand[winner] == draw_flag)) {
                System.out.printf("Players");
                count = 0;
                for (i = 0; i < numberOfPlayers; i++) {
                    if (i == 0) {
                        System.out.printf(" %d", draw[i] + 1);
                    } else if (i == numberOfPlayers-1 && draw[i] > -1) {
                        System.out.printf(" and %d ", draw[i] + 1);
                    } else {
                        if (draw[i] > -1 && draw[i+1] > -1) {
                            System.out.printf(", %d", draw[i] + 1);
                            //count++;
                        } else if (draw[i] > -1 && draw[i+1] == -1) {
                            System.out.printf(" and %d ", draw[i] + 1);
                        }
                    }
                }
                System.out.printf("draw.\n");
                no_winner = 1;
            }
        }
        
        /* If not a draw, then a player has won. Print the winner message */
        if (no_winner == 0) {
            System.out.printf("Player %d wins.\n", winner + 1);
        }
    }
}
