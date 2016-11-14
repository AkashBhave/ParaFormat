import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Scanner;

/**
 * Created by akash on 11/10/2016.
 */
public class Researcher {

    // Constructor
    public Researcher() {

    }

    public static void main(String[] args) throws IOException {
        googleSearch(generateSearchTerm(), generateNumResults());
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

    private static int generateNumResults() {
        Scanner userInput = new Scanner(System.in);
        String userNum;
        int finalNum = 0;

        boolean inputCorrect = false;
        while (!inputCorrect) {
            System.out.println("Enter the number of results you want.");

            userNum = userInput.nextLine();
            try {
                finalNum = Integer.parseInt(userNum);
                inputCorrect = true;
            } catch (NumberFormatException e) {
                inputCorrect = false;
            }
        }

        return finalNum;
    }

    // Searches Google using the search term given
    private static void googleSearch(String searchTerm, int numResults) throws IOException {
        // Array lists to hold the results of the parsing
        ArrayList<String> linkHrefs = new ArrayList<String>();
        ArrayList<String> linkTitles = new ArrayList<String>();
        ArrayList<String> linkBodys = new ArrayList<String>();

        String googleUrl = "https://www.google.com/search" + "?q=" + searchTerm + "&num=" + numResults;

        // Start JSoup parsing
        Document googleDocument = Jsoup.connect(googleUrl).userAgent("Mozilla/5.0").get();

        // Elements to parse link title and URL
        Elements titleResults = googleDocument.select("h3.r > a");
        for (Element result : titleResults) {
            String linkHref = result.attr("href");
            linkHref = linkHref.substring(7, linkHref.indexOf("&"));
            String linkText = result.text();

            // Append results to ArrayLists
            if (!linkHref.contains("?q=")) {
                linkHrefs.add(linkHref);
            }
            linkTitles.add(linkText);

        }

        // Elements to parse the body
        Elements bodyResults = googleDocument.select("span.st");
        for (Element bResult : bodyResults) {
            String bodyResult = bResult.text();

            linkBodys.add(bodyResult);
        }

        // Prints the results of the query (for debug purposes)
        for(int i = 0; i < linkHrefs.size(); i++) {
            System.out.println(linkHrefs.get(i));
            System.out.println(linkTitles.get(i));
            System.out.println(linkBodys.get(i));
        }
    }

}
