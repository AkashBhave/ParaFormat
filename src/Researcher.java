import java.util.Scanner;

/**
 * Created by akash on 11/10/2016.
 */
public class Researcher {

    // Constructor
    public Researcher() {

    }

    public static void main(String[] args) {
        System.out.println(generateSearchTerm());
    }

    /*
    Creates a valid search term that can be used
    in URLs
     */
    private static String generateSearchTerm() {
        String searchTerm = "";
        Scanner userInput = new Scanner(System.in);

        boolean inputCorrect = false;
        while (!inputCorrect) {
            System.out.println("Enter a search term.");

            searchTerm = userInput.nextLine();
            if (!searchTerm.isEmpty()) {
                inputCorrect = true;
            }
        }

        return searchTerm;

    }

}
