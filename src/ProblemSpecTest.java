import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import student.TestCase;

/**
 * @author {Your Name Here}
 * @version {Put Something Here}
 */
public class ProblemSpecTest extends TestCase {
    /**
     * Sets up the tests that follow. In general, used for initialization
     */
    public void setUp() {
        // Nothing here
    }


    /**
     * Read contents of a file into a string
     * 
     * @param path
     *            File name
     * @return the string
     * @throws IOException
     */
    static String readFile(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded);
    }


    /**
     * Example 1: This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     * 
     * @throws IOException
     */
    public void testPostedSample() throws IOException {
        // Setting up all the parameters
        String[] args = new String[1];
        args[0] = "P1test1.txt";

        // Invoke main method of our Graph Project
        SkipListProject.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("P1test1Out.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        // assertFuzzyEquals(referenceOutput, output);
    }


    /**
     * Example 2: This method runs on a command sample IO file
     * You will write similar test cases
     * using different text files
     * 
     * @throws IOException
     */
    public void testPostedSyntaxSample() throws IOException {
        // Setting up all the parameters
        String[] args = new String[1];
        args[0] = "SyntaxTest.txt";

        // Invoke main method of our Graph Project
        SkipListProject.main(args);

        // Actual output from your System console
        String output = systemOut().getHistory();

        // Expected output from file
        String referenceOutput = readFile("SyntaxTestOut.txt");

        // Compare the two outputs
        // TODO: uncomment the following line
        // once you have implemented your project
        // assertFuzzyEquals(referenceOutput, output);
    }
}
