package com.collegeboard.game;

import java.io.Console;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * This is a number-guessing game program.
 * 
 * @author Laxmidhara Mallik
 * 
 */
public class NumberGuessing {

	/**
	 * Range for the NumberGuessing Game. low to high.
	 */
	private int low, high;

	private Integer guessedNumber = null;
	Properties prop = new Properties();

	/**
	 * Console to capture the user input
	 */
	Console console ;

	/**
	 * Default Constructor. This defaults the number range for the game by reading 
	 * External properties file.
	 * Main purpose of this default constructor to run the game from command line.
	 * This also sets the Console for user input.
	 * 
	 */
	public NumberGuessing()	{
		//Read from Properties file.
		try {
			String propPath = System.getProperty("my.app.properties");
			if ( propPath != null )	{
			     FileInputStream in = new FileInputStream( propPath );

			         prop.load( in );
			}
		}catch (IOException ex) {
    		ex.printStackTrace();
		}
		this.low = Integer.parseInt(prop.getProperty("lower"));
		this.high = Integer.parseInt(prop.getProperty("higher"));
		console = System.console();
	}

	/**
	 * Parametrised constructor to set the number range for the game and it is for testing purpose.
	 * This also sets the Console for user input.
	 * 
	 * @param min range from
	 * @param max range to
	 */
	public NumberGuessing(int min, int max)	{
		console = System.console();
		this.low=min;
		this.high=max;
	}


    /**
     * Main processing for the Guess the number game.
     * This displays initial prompts for the Game including the range to choose the number from.
     * This also calls the guess method which actually guesses the number.
     * 
     * @param none
     * 
     */
    public void guessTheNumber(){
        if (console == null){
            System.err.println("No console available.");
        }

        System.out.println("\nGuess a number between "+low+" and "+high+".");
		String ready = console.readLine("Hit Enter when you are ready.\n");
        System.out.println("Instruction:");
        System.out.println("Enter h/H if your number is Higher\nEnter l/L if your number is Lower\nEnter y/Y if this is guess number");
        System.out.println("If you forget the guess number while playing the game then you may play long time!\n");
        find();
	}


    /**
     * This method reads the user feedback on the guessed number.
     * If the feedback is y (Yes) then it ends the game otherwise it calls the process method.
     */
    public void find(){
		String feedback = "l";
		if(null != guessedNumber){
			feedback = console.readLine("Computer: Is the number "+guessedNumber+" ?"+"\nUser: ");
		}else{
			guessedNumber = high;
		}

		if("y".equalsIgnoreCase(feedback)){
			console.readLine("Your guess number is "+guessedNumber+".\n\nThank you for playing!\n\nHit enter to exit game.");
			System.exit(0);
		}else{
			guessedNumber = process(feedback, guessedNumber);
			find();
		}
	}

	/**
	 * This method returns the number guess based on the user feedback.
	 * The feedback can only be one of the following.
	 * enter h/H if your number is Higher
	 * enter l/L if your number is Lower
	 * enter y/Y if this is your number
	 *
	 * @param feedback user input(feedback) on the guessed number.
  	 * @param guessedNumber guessed number.
  	 * 
  	 * @exception none
  	 * 
	 * @return it returns the next guess number based on the feedback.
	 * 
	 */
	public int process(String feedback, int guessedNumber){
		if("h".equalsIgnoreCase(feedback)){
			low = guessedNumber;
		}else if("l".equalsIgnoreCase(feedback)){
			high = guessedNumber;
		}else{
			System.out.println("\nEnter h/H if your number is Higher\nEnter l/L if your number is Lower\nEnter y/Y if this is guess number\n");
			System.out.println("If you forget the guess number while playing the game then you may play long time!\n");
		}

		return (low +(high-low)/2);
	}

    /**
     * Main Method.
     */
	public static void main(String[] args) {
		NumberGuessing numberGuessing = new NumberGuessing();
		numberGuessing.guessTheNumber();
	}
}
