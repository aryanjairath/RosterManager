package rosterfiles;

/**
 * This class provides a main method for running the tuition system.
 * @author Anis Chihoub, Aryan Jairath
 */
public class RunProject1 {

    /**
     * This method is the main method, which will call the run method in RosterManager.
     * @param args an array of strings for any command line arguments
     */
    public static void main(String[] args) {
        new RosterManager().run();
    }
}