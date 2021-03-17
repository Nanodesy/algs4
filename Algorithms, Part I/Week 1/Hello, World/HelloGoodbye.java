/**
 * This class print "Hello" (in straight order) and "Goodbye"
 * (in reversed order) for two program arguments.
 */
public class HelloGoodbye {
    /**
     * @param args read only first two arguments.
     */
    public static void main(String[] args) {
        System.out.printf("Hello %s and %s.\n", args[0], args[1]);
        System.out.printf("Goodbye %s and %s.\n", args[1], args[0]);
    }
}
