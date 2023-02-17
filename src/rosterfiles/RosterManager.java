package rosterfiles;
import java.util.Calendar;
import java.util.Scanner;

/**
 * This class reads input from command line and
 * takes a series of different options such as adding a student,
 * removing a student, changing a major, and printing students
 * in different ways.
 * @author Anis Chihoub, Aryan Jairath
 */
public class RosterManager {
    private static final int FIRSTINDEX = 0;
    private static final int SECONDINDEX = 1;
    private static final int TWOCHARLENGTH = 2;
    private static final int NOSTRINGLENGTH = 0;

    private static final int CREDITINDEX = 5;
    private static final int MAJORINDEX = 4;
    private static final int DOBINDEX = 3;
    private static final int LNAMEINDEX = 2;
    private static final int FNAMEINDEX = 1;
    private static final int SCHOOLINDEX = 1;
    private static final int MINIMUMAGE = 16;

    private static final int ZEROVALUE = 0;
    private static final int NEGATIVEMULTIPLIER = -1;

    private Roster studentRoster = new Roster();

    /**
     * This method checks is the command is a one letter command from the
     * valid options except P, which is checked for in the following method.
     * @param command a string representing a command
     * @return a boolean, true if the command is valid. False otherwise
     */
    private boolean isOneChar(String command){
        return (command.charAt(FIRSTINDEX) == 'A' || command.charAt(FIRSTINDEX) == 'R'
                || command.charAt(FIRSTINDEX) == 'L'
                || command.charAt(FIRSTINDEX) == 'C' || command.charAt(FIRSTINDEX) == 'Q'
                || command.charAt(FIRSTINDEX) == 'P');
    }

    /**
     * This method checks is the command is a two letter command.
     * Since two letter commands can start with a P, we check for
     * the P command as well as PS and PC.
     * @param command a string representing a command
     * @return a boolean, true if the command is valid. False otherwise
     */
    private boolean isTwoChars(String command){
        if(command.length() < TWOCHARLENGTH){
            return false;
        }
        if (isOneChar(command)){
            return true;
        }else{
            String firstTwoChars = command.substring(FIRSTINDEX,TWOCHARLENGTH);
            if(firstTwoChars.equals("PS") || firstTwoChars.equals("PC")){
                return true;
            }else{
                return false;
            }
        }
    }

    /**
     * This method receives a string containing a command and checks
     * if it is valid based on the ruleset provided in the writeup.
     * @param command: a string containing a command read in from the scanner
     * class.
     * @return a boolean, indicating if the command is valid or not.
     */
    private boolean checkInvalid(String command){
        if(command.length() > NOSTRINGLENGTH && (isOneChar(command)
                || isTwoChars(command))){
            return false;
        }
        else{
            return true;
        }
    }

    /**
     * This method checks the date n years ago from today
     * @param nYears An integer representing the number of years
     * ago from today to calculate the date
     * @return A date object representing the date n years ago
     */
    private Date wasNYearsAgo(int nYears){
        Calendar nYearsAgo = Calendar.getInstance();
        nYearsAgo.add(Calendar.YEAR, NEGATIVEMULTIPLIER * nYears);
        int year = nYearsAgo.get(Calendar.YEAR);
        int month = nYearsAgo.get(Calendar.MONTH)+SECONDINDEX;
        int day = nYearsAgo.get(Calendar.DATE);
        Date dateNYearsAgo = new Date(month + "/" + day + "/" + year);
        return dateNYearsAgo;
    }

    /**
     * This method validates if the input number is an integer
     * @param checkCredit a String representing an integer to be checked
     * @return a boolean value, true if the value is an integer, otherwise false
     */
    public static boolean isInteger(String checkCredit) {
        try {
            Integer.parseInt(checkCredit);
        } catch(NumberFormatException exception) {
            return false;
        } catch(NullPointerException exception) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    /**
     * This method checks if an input string qualifies as a valid major based on enum class
     * @param testMajor A string that is being checked for whether it is a major or not
     * @return A boolean value returning true if the string parameter is a valid major
     */
    public static boolean isValidMajor(String testMajor) {
        try {
            Major studentMajor = Major.valueOf(testMajor.toUpperCase());
            return true;
        } catch (IllegalArgumentException exception) {
            System.out.println("Major code invalid:" + testMajor);
            return false;
        }
    }

    /**
     * This method checks if an input string is a valid school
     * @param testSchool A string that is being checked for
     * whether it is an existing school or not
     * @return A boolean value returning true if the string parameter is a valid school
     */
    public static boolean isValidSchool(String testSchool) {
        for (Major day : Major.values()) {
            if(day.getSchool().equals(testSchool.toUpperCase()))
                return true;
        }
        return false;
    }

    /**
     * Makes a student object. Checks for invalid conditions and
     * prints out if there is an issue.
     * @param parsedCommand an Array of Strings where each index
     * contains student information as an individual String
     * @return an object of type Student after making it based on the parsed input
     * and checking if it can be a valid student for the roster
     */
    private Student makeStudent(String[] parsedCommand){
        int numberOfYears = MINIMUMAGE;
        Date dob = new Date(parsedCommand[DOBINDEX]);
        Date sixteenYearsAgo = wasNYearsAgo(numberOfYears);
        if(!dob.isValid()){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " not a valid calendar date!");
            return null;
        }
        if(!isInteger(parsedCommand[CREDITINDEX])){
            System.out.println("Credits completed invalid: not an integer!");
            return null;
        }
        if(Integer.parseInt(parsedCommand[CREDITINDEX]) < ZEROVALUE){
            System.out.println("Credits completed invalid: cannot be negative!");
            return null;
        }
        if(dob.compareTo(sixteenYearsAgo) > ZEROVALUE){
            System.out.println("DOB is invalid: " + dob.toString() +
                    " is younger than 16 years old!");
            return null;
        }
        Profile profile =
                new Profile(parsedCommand[LNAMEINDEX],parsedCommand[FNAMEINDEX],dob);
        Major studentMajor = null;
        if(isValidMajor(parsedCommand[MAJORINDEX])){
            studentMajor = Major.valueOf(parsedCommand[MAJORINDEX].toUpperCase());
        }else{
            return null;
        }
        int creditsCompleted = Integer.parseInt(parsedCommand[CREDITINDEX]);
        Student newStudent = new Student(profile, studentMajor, creditsCompleted);
        return newStudent;
    }

    /**
     * Executes a command of type A, given a parsed student information.
     * @param parsedCommand, an Array of Strings where each index
     * contains student information as an individual String
     */
    private void executeA(String[] parsedCommand){
        Student addStudent = makeStudent(parsedCommand);
        if(addStudent == null){
            return;
        }
        if (studentRoster.add(addStudent)) {
            System.out.println(parsedCommand[FNAMEINDEX] + " "
                    + parsedCommand[LNAMEINDEX] + " "
                    + parsedCommand[DOBINDEX] + " added to the roster.");
        }else {
            System.out.println(parsedCommand[FNAMEINDEX] + " "
                    + parsedCommand[LNAMEINDEX] + " "
                    + parsedCommand[DOBINDEX] + " is already in the roster.");
        }
    }

    /**
     * This method executes the R command, which removes a provided student.
     * Prints out an error message if there is no valid student.
     * @param parsedCommand an array of Strings containing the tokenized command.
     */
    private void executeR(String[] parsedCommand){
        Date parsedDate = new Date(parsedCommand[DOBINDEX]);
        Profile parsedProfile = new Profile(parsedCommand[LNAMEINDEX],
                parsedCommand[FNAMEINDEX], parsedDate);
        Student studentCopy = new Student(parsedProfile, null, ZEROVALUE);
        if(studentRoster.remove(studentCopy)){
            System.out.println(studentCopy.getProfile().toString() +
                    " removed from the roster.");
        }else{
            System.out.println(studentCopy.getProfile().toString() +
                    " is not in the roster.");
        }
    }

    /**
     * This method is in response to the L command which prints students by a
     * specified school
     * @param parsedCommand an array of Strings containing the tokenized command
     */
    private void executeL(String[] parsedCommand){
        String school = parsedCommand[SCHOOLINDEX];
        boolean schoolExists = isValidSchool(school);
        if(!schoolExists) {
            System.out.println("School doesn't exist: " + school);
            return;
        }
        studentRoster.sortByName();
        System.out.println("* Students in " + school + " *");
        for(int i = 0; i < studentRoster.getRoster().length; i++){
            if(studentRoster.getRoster()[i] != null && school.toUpperCase()
                    .equals(studentRoster.getRoster()[i].getMajor().getSchool()))
                System.out.println(studentRoster.getRoster()[i].toString());
        }
        System.out.println("* end of list **");
    }

    /**
     * This method changes the major of specified student as picked
     * intended by the input
     * @param parsedCommand an array of Strings containing
     * the tokenized command
     */
    private void executeC(String[] parsedCommand){
        Student[] rosterCopy = studentRoster.getRoster();
        Date parsedDate = new Date(parsedCommand[DOBINDEX]);
        Profile parsedProfile =
                new Profile(parsedCommand[LNAMEINDEX], parsedCommand[FNAMEINDEX], parsedDate);
        Student studentToChange = new Student(parsedProfile, null, ZEROVALUE);
        String newMajor=parsedCommand[MAJORINDEX];
        Major maj = null;
        if(!(studentRoster.contains(studentToChange))) {
            System.out.println(studentToChange.getProfile().getFname() + " "
                    + studentToChange.getProfile().getLname() + " " +
                    studentToChange.getProfile().getDob().toString() + " is not in the roster.");
            return;
        }
        if(isValidMajor(newMajor) && studentRoster.contains(studentToChange)){
            maj = Major.valueOf(parsedCommand[MAJORINDEX].toUpperCase());
            for(int i = 0; i < rosterCopy.length; i++){
                if(rosterCopy[i] != null && rosterCopy[i].equals(studentToChange)){
                    studentToChange = new Student(parsedProfile, maj, rosterCopy[i].getCreditCompleted());
                    studentRoster.remove(rosterCopy[i]);
                    studentRoster.add(studentToChange);
                }
            }
            System.out.println(studentToChange.getProfile().getFname() + " "
                    + studentToChange.getProfile().getLname() + " " +
                    studentToChange.getProfile().getDob().toString() + " major changed to "
                    + newMajor);
        }
    }

    /**
     * This method executes a command based on the string input.
     * @param parsedCommand an array of strings containing the parsed command
     */
    public void executeCommand(String[] parsedCommand){
        String commandToExecute = parsedCommand[FIRSTINDEX];
        switch (commandToExecute){
            case "A":
                executeA(parsedCommand);
                break;
            case "R":
                executeR(parsedCommand);
                break;
            case "P":
                studentRoster.print();
                break;
            case "PS":
                studentRoster.printByStanding();
                break;
            case "PC":
                studentRoster.printBySchoolMajor();
                break;
            case "L":
                executeL(parsedCommand);
                break;
            case "C":
                executeC(parsedCommand);
                break;
        }
        return;
    }

    /**
     * This method runs the tuition manager by listening for commands. This is
     * done using the scanner class and parsing the string input. In addition,
     * we check for invalid input using the checkInvalid method.
     */
    public void run(){
        Scanner reader = new Scanner(System.in);
        System.out.println("Roster Manager running...");
        while(true){
            String command = reader.nextLine();
            String[] tokenizedCommand = command.split("\\s+");
            if(command.equals("Q")){
                System.out.println("Roster Manager terminated.");
                break;
            }else if(checkInvalid(command)){
                System.out.println(tokenizedCommand[FIRSTINDEX] + " is an invalid command!");
            }else{
                executeCommand(tokenizedCommand);
            }
        }
        reader.close();
    }
}
