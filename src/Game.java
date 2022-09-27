import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Game {

    private String movies[];
    private int i,wordCount = 0,findCount = 0;
    private int movieNo;
    private char[] choosenMovie;
    char[] missingWords;
    private File file;
    Scanner fileScanner;
    Scanner scanner = new Scanner(System.in);
    char word;

    Game() {

        movies = new String[100];
        file = new File("movies.txt");
        
        try {
            fileScanner = new Scanner(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while(fileScanner.hasNextLine())
        {
            movies[i++] = fileScanner.nextLine();
        }
        movieNo = (int) (Math.random() * i) + 1;
        choosenMovie = movies[movieNo].toCharArray();
        missingWords = new char[choosenMovie.length];
        for(int i=0;i<choosenMovie.length;i++)
        {
            if(choosenMovie[i] != ' '){
                missingWords[i]  = '_';
                wordCount++;
            }
            else {
                missingWords[i] = ' ';
            }
        }
        fileScanner.close();
    }

    String startGame() {
        
        int guess = 10;
        while(guess > 0)
        {
            System.out.println("Guess the words that are blank");
            display();
            word = scanner.next().charAt(0);
            
            if(findIndex(word))
            {
                System.out.println("You guessed correct");
                findCount++;
            }
            else{
                guess--;
                System.out.println("Wrong guess! Only "+guess+" guess left ");
            }

            if(wordCount == findCount)
            {
                return "You Win! Congratulations";
            }
        }
        scanner.close();
        return "You Lose Better luck next time!";

    }

    void display() {
        for(i=0;i<missingWords.length;i++)
        {
            System.out.print(missingWords[i]);
        }
        System.out.println();
    }
    
    boolean findIndex(char word){

        for(i=0;i<choosenMovie.length;i++)
        {
            if(word == choosenMovie[i] && missingWords[i]=='_')
            {
                missingWords[i] = word;
                return true;
            }
        }
        return false;
    }
    
}

