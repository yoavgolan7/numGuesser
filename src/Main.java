import java.io.IOException;
import java.util.Random;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args)  {
        Random random = new Random();
        int randomNumber = random.nextInt(101);
        System.out.println("Guess a number between 0 and 100 - you have 30 seconds");
        boolean[] success ={false}; // I used arrays as makeshift pointers

        Thread inputThread= new Thread(() ->{
            Scanner scanner = new Scanner(System.in);
            int guess = -1;
            while (guess != randomNumber && !Thread.currentThread().isInterrupted()) {
                try {
                    if (System.in.available() > 0 && scanner.hasNextInt()) {
                        guess = scanner.nextInt();
                        if (guess < randomNumber) {
                            System.out.println("Higher");
                        } else if (guess > randomNumber) {
                            System.out.println("Lower");
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            success[0] = true;
        });
        inputThread.start();
        Timer stopwatch2 = new Timer();
        stopwatch2.start();
        while(stopwatch2.getSecondsWaited()<30&&!success[0]) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                break;
            }
        }
        inputThread.interrupt();
        stopwatch2.interrupt();
        if (!success[0]) {
            System.out.println("Too Slow! The number was: " + randomNumber);
        }
        else  {
            System.out.println("You guessed the number! Took you a total of " + stopwatch2.getSecondsWaited() + " seconds");
        }

    }
}