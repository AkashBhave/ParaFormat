import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by akash on 11/10/2016.
 */
public class Researcher {

    // Constructor
    public Researcher() {

    }

    public static void main(String[] args) throws MalformedURLException {
        googleSearch(generateSearchTerm());
    }

    // Creates a valid search term that can be used in URLs
    private static String generateSearchTerm() {
        String searchTerm = "";
        String finalSearchTerm = "";
        Scanner userInput = new Scanner(System.in);

        boolean inputCorrect = false;
        while (!inputCorrect) {
            System.out.println("Enter a search term.");

            searchTerm = userInput.nextLine();
            if (!searchTerm.isEmpty() && searchTerm.trim().length() > 0) {
                inputCorrect = true;
            }
        }

        if (searchTerm.contains(" ")) {
            String[] fullSearchTerm = searchTerm.split(" ");
            for (String s : fullSearchTerm) {
                finalSearchTerm += s;
                finalSearchTerm += "%20";
            }

        } else {
            finalSearchTerm = searchTerm;
        }

        if (finalSearchTerm.contains("%20")) {
            finalSearchTerm = finalSearchTerm.substring(0, finalSearchTerm.length() - 3);
        }

        return finalSearchTerm;

    }

    // Searches Google using the search term given
    private static void googleSearch(String searchTerm) throws MalformedURLException {
        URL googleUrl = new URL("https://www.google.com/#q=" + searchTerm);
    }

}
