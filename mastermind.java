import java.util.Random;
import java.util.Scanner;
class Interactive {
 //these are global variables
   public static final String ANSI_RESET = "\u001B[0m";
   public static final String ANSI_BLACK = "\u001B[30m";
   public static final String ANSI_RED = "\u001B[31m";
   public static final String ANSI_GREEN = "\u001B[32m";
   public static final String ANSI_YELLOW = "\u001B[33m";
   public static final String ANSI_NAVY = "\u001B[34m";
   public static final String ANSI_PURPLE = "\u001B[35m";
   public static final String ANSI_CYAN = "\u001B[36m";
   public static final String ANSI_WHITE = "\u001B[37m";
  
   public static char[] answerColours = {'R','C','B','Y','P','W','G','N'};
   public static int gameCounter = 0;
   public static int winCounter = 0;  
  
   //main method here
   public static void main(String[] args) {
     Scanner s = new Scanner(System.in);
     //Menu screen
     char c = printWelcome(s);
    
     while (c != 'P') {
      clearScreen();
      if (c == 'I') {
       printInstructions(s);
       clearScreen();
      }
     
      c = printWelcome(s);
     }
     char endGame = 'P';
    
     do {
      clearScreen();
      gameCounter++;
      playGame(s);
      endGame = printEnd(s);
     }
     while (endGame == 'P' );
     
     System.out.println("Thank you for playing Mastermind :-)");
   }
   
 //other methods below
   public static void playGame(Scanner s) {
     printColours();
     //Build Answer String
     String answer = buildAnswer(4);
     //System.out.println(answer);
     //uncomment if you want to see solution
    
     for(int i = 0; i < 10; i++) {
      String input = "";
      while (input.length() != 4) {
        System.out.println("Enter a valid code of 4 upper case characters:");
        input = s.nextLine();
      }
      String result = compareToAnswer(input, answer, 4);
      System.out.println("|" + result);  
      System.out.println("------------");
      if (answer.equals(input)) {
        System.out.println("You won.");
        winCounter++;
        break;
      }
      else if (i == 9) {
        System.out.println("Sorry, you lost!");
        break;
      }
     }
    System.out.println("You have won " + winCounter + " games.");
    System.out.println("out of " + gameCounter + " games.");
   }
  
   public static String buildAnswer(int n){//generate the answer key for the round
     Random r = new Random();
     String x = "";
     for(int i = 0; i < n; i++) {
      //picking 4 random colours to make code sequence
      char c = (answerColours[r.nextInt(8)]);

      x = x + String.valueOf(c);
     }
     return x;
   }
  
  
   public static String compareToAnswer(String in, String answer, int n){
    //to count for right colour and right spot
    int Xcount = 0;
    //to count for only right colour
    int Ocount = 0;
    String result = "";
    //flag logic is to not reuse the same letter in answer key
    boolean[] flag = new boolean[n];
    //if already X, should not count for O, vice-versa
    for (int i = 0; i < n; i++) {
     char a = in.charAt(i);
     char b = answer.charAt(i);
     if (a == b && flag[i] == false) {
      flag[i] = true;
      Xcount++;
     }
     else {
      for (int j = 0; j < n; j++) {
       b = answer.charAt(j);
      
       if (a == b && flag[j] == false) {
        flag[j] = true;
        Ocount++;
       }
      }
     }
    
    }
   
    //to ensure that all X's are printed first, before O's
    for (int i = 0; i < Xcount; i++) {
     result += "X";
    }
    for (int k = 0; k < Ocount; k++) {
     result += "O";
    }
    return result;
   }
   public static void clearScreen() {
    //System.out.print("\033[H\033[2J");
    //System.out.flush();
    try {
    Runtime.getRuntime().exec("cls");
    }
    catch (final Exception e) {
    
    }
   }
  
   public static char printWelcome (Scanner s) {
    System.out.println("*============*============*");
    System.out.println("|                         |");
    System.out.println("|  Welcome to Mastermind  |");
    System.out.println("|    I for Instructions   |");
    System.out.println("|    P for Play           |");
    System.out.println("|                         |");
    System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    char userChoice = s.next().charAt(0); s.nextLine();
    return userChoice;
   }
  
  
   public static char printInstructions (Scanner s) {
    System.out.println("Instructions");
    System.out.println("\n Mastermind is a fun game based on luck and logic.");
    System.out.println("- The computer picks a sequence of 4 colors.");
    System.out.println("- You win the game when you manage to guess all the colors in the code sequence and when they all in the right position. (within 10 tries)");
    System.out.println("- You lose the game if you use all 10 attempts without guessing the computer code sequence.");
    System.out.println("- Please note that the computer will allow duplicates. This means that any color can be used any number of times in the code sequence.");
    System.out.println("- After filling a line with your guesses and pressing 'Enter', the computer responses with the result of your guess.");
    System.out.println("- For each color in your guess that is in the correct color and correct position in the code sequence, the computer display an 'X'.");
    System.out.println("- For each color in your guess that is in the correct color but is NOT in the correct position in the code sequence, the computer display an 'O'.");
    System.out.println("\n Press any key to close instructions");
    char userChoice = s.next().charAt(0); s.nextLine();
    return userChoice;
   }
  
  
   public static void printColours () { //List of colours for user to refer to
    System.out.println("NOTE: letters are CASE-SENSITIVE! only input capital letters.");
    System.out.println(ANSI_RED + "R for Red" + ANSI_RESET);
    System.out.println(ANSI_CYAN + "C for Cyan" + ANSI_RESET);
    System.out.println(ANSI_BLACK + "B for Black" + ANSI_RESET);
    System.out.println(ANSI_YELLOW + "Y for Yellow" + ANSI_RESET);
    System.out.println(ANSI_PURPLE + "P for Purple" + ANSI_RESET);
    System.out.println(ANSI_WHITE + "W for White" + ANSI_RESET);
    System.out.println(ANSI_GREEN + "G for Green" + ANSI_RESET);
    System.out.println(ANSI_NAVY + "N for Navy blue" + ANSI_RESET);
   }
  
   public static char printEnd(Scanner s) {
    System.out.println("Would you like to play again?");
    System.out.println("Press P for Play again, E for Exit Game.");


    char userChoice = s.next().charAt(0); s.nextLine();
    return userChoice;
   }
}