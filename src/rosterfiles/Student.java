package rosterfiles;

/**
 * This class defines a student with a profile, a major,
 * and the number of credits completed.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Student implements Comparable<Student> {

    private Profile profile;
    private Major major; //Major is an enum type
    private int creditCompleted;

    public static final int LESSTHAN = -1;
    public static final int EQUALTO = 0;
    public static final int GREATERTHAN = 1;

    public static final int FRESHMANCREDITS = 29;

    public static final int SOPHOMORECREDITS = 30;

    private static final int JUNIORCREDITS = 60;

    public static final int SENIORRCREDITS = 90;

    /**
     * Constructor for the Student class
     * Defines the profile, major, and
     * creditsCompleted for the student.
     * @param profile A profile representing the name,lastname, and dob of the student
     * @param major An enum type representing the major of the student
     * @param creditCompleted An integer representing the number of credits completed by the student
     */
    public Student(Profile profile, Major major, int creditCompleted){
        this.profile = profile;
        this.major = major;
        this.creditCompleted = creditCompleted;
    }

    /**
     * This method returns the profile of a particular student
     * @return A Profile object of a particular student
     */
    public Profile getProfile(){
        return this.profile;
    }

    /**
     * This method returns the standing of a student
     * based on the number of credits he or she has.
     * @return A string representing the standing of a
     * student.
     */
    public String collegeYear(){
        if(this.creditCompleted <= FRESHMANCREDITS){
            return "(Freshman)";
        }else if(this.creditCompleted >= SOPHOMORECREDITS && this.creditCompleted < JUNIORCREDITS){
            return "(Sophomore)";
        }
        else if(this.creditCompleted >= JUNIORCREDITS && this.creditCompleted < SENIORRCREDITS){
            return "(Junior)";
        }
        else {
            return "(Senior)";
        }
    }

    /**
     * This method returns the major of a particular student
     * @return A Major object of a particular student
     */
    public Major getMajor(){
        return this.major;
    }

    /**
     * Sets the major of a student to a new major when changing
     * @param newMajor The major being associated with the student
     */
    public void setMajor(Major newMajor){
        this.major = newMajor;
    }

    /**
     * This method returns the number of credits completed by a student
     * @return An int representing the number of credits completed associated with a student
     */
    public int getCreditCompleted(){
        return this.creditCompleted;
    }

    /**
     * This method provides a string representation of a student
     * @return A string containing the attributes of the Student object
     */
    @Override
    public String toString(){
        return this.profile.toString() + " (" + this.major.getCode() + " " + this.major + " " +
                this.major.getSchool() +") credits completed: " + this.creditCompleted + " " + collegeYear();
    }

    /**
     * Compares two Student objects
     * @param compareStudent the object to be compared.
     * @return int, an integer representing if one Student
     * is greater than the other in the set (-1,1).
     */
    @Override
    public int compareTo(Student compareStudent) {
        if(compareStudent == null){
            return GREATERTHAN;
        }

        if(this.profile.compareTo(compareStudent.profile) > EQUALTO){
            return GREATERTHAN;
        }
        else if(this.profile.compareTo(compareStudent.profile) < EQUALTO)
            return LESSTHAN;
        else
            return EQUALTO;
    }

    /**
     * This method determines if two student objects are equal
     * @param objToCbeck The object that we are comparing
     * @return a boolean, true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object objToCbeck){
        if(objToCbeck instanceof Student){
            return ((Student) objToCbeck).profile.equals(this.profile);
        }
        return false;
    }

    /**
     * Compares the results for the testing of the compareTo method for
     * two students
     * @param expectedOutput the output that shoud be observed given the two students
     * @param actualOutput the result of the comparison that occurs from running
     * compareTo in main with two students
     */
    public static void testResult(int expectedOutput, int actualOutput){
        if(expectedOutput == actualOutput){
            System.out.println("For the two students provided, both " +
                    "the expectedOutput and actualOutput are " + actualOutput);
        }else{
            System.out.println("For the two students provided, the actual output is " +
                    expectedOutput + " and the actualOutput is " + actualOutput);
        }

    }

    /**
     * This method executes test cases one through three as seen
     * in the test case write up.
     */
    private static void  executeTestCasesOneThroughThree(){
        Date dob = new Date("6/20/2002");
        Profile profile = new Profile("Chihoub","Anis", dob);
        Major major = Major.CS;
        Student studentOne = new Student(profile, major, 60);
        Date compDob = new Date("6/20/2002");
        Profile compProfile = new Profile("Chihoub","Anis", compDob);
        Major compMajor = Major.CS;
        Student studentTwo = new Student(compProfile, compMajor, 60);
        int expectedOutput = EQUALTO;
        int actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #1: Both students are the same. ");
        testResult(expectedOutput,actualOutput);

        dob = new Date("6/20/2002");
        profile = new Profile("Chihoub","Anis", dob);
        major = Major.CS;
        studentOne = new Student(profile, major, 60);
        compDob = new Date("6/20/2003");
        compProfile = new Profile("Dhihoub","Anis", compDob);
        compMajor = Major.CS;
        studentTwo = new Student(compProfile, compMajor, 60);
        expectedOutput = LESSTHAN;
        actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #2:" +
                " Same student names with different date of births. ");
        testResult(expectedOutput,actualOutput);

        studentTwo = null;
        expectedOutput=GREATERTHAN;
        actualOutput=studentOne.compareTo(studentTwo);
        System.out.print("**Test case #3: Comparing a student to an empty student will always" +
                " return greater than. ");
        testResult(expectedOutput,actualOutput);
    }

    /**
     * This method executes test cases four through six as seen
     * in the test case write up.
     */
    private static void  executeTestCasesFourThroughFive(){
        Date dob = new Date("6/20/2002");
        Profile profile = new Profile("Chihoub","Anis", dob);
        Major major = Major.CS;
        Student studentOne = new Student(profile, major, 60);
        Date compDob = new Date("6/20/2002");
        Profile compProfile = new Profile("Dhihoub","Anis", compDob);
        Major compMajor = Major.CS;
        Student studentTwo = new Student(compProfile, compMajor, 60);
        int expectedOutput = LESSTHAN;
        int actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #4: Student A with the same DOB as another " +
                "is greater than Student B by having a lastname that comes first.  ");
        testResult(expectedOutput,actualOutput);

        dob = new Date("6/20/2003");
        profile = new Profile("Chihoub","Anis", dob);
        major = Major.CS;
        studentOne = new Student(profile, major, 60);
        compDob = new Date("6/20/2002");
        compProfile = new Profile("Chihoub","Anis", compDob);
        compMajor = Major.CS;
        studentTwo = new Student(compProfile, compMajor, 60);
        expectedOutput = GREATERTHAN;
        actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #5:" +
                " Same student names with different date of births part two. ");
        testResult(expectedOutput,actualOutput);
    }

    /**
     * Executes testcases Six and Seven on the design document.
     */
    private static void executeSixAndSeven(){
        Date dob = new Date("6/20/2002");
        Profile profile = new Profile("Chihoub","Anis", dob);
        Major major = Major.CS;
        Student studentOne = new Student(profile, major, 60);
        Date compDob = new Date("6/20/2002");
        Profile compProfile = new Profile("Ahihoub","Anis", compDob);
        Major compMajor = Major.CS;
        Student studentTwo = new Student(compProfile, compMajor, 60);
        int expectedOutput = GREATERTHAN;
        int actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #6:" +
                " Student A last name comes before Student B lastname. ");
        testResult(expectedOutput,actualOutput);

        dob = new Date("6/20/2002");
        profile = new Profile("Chihoub","Anis", dob);
        major = Major.CS;
        studentOne = new Student(profile, major, 60);
        compDob = new Date("6/20/2002");
        compProfile = new Profile("Chihoub","Anis", compDob);
        compMajor = Major.CS;
        studentTwo = new Student(compProfile, compMajor, 61);
        expectedOutput = EQUALTO;
        actualOutput = studentOne.compareTo(studentTwo);
        System.out.print("**Test case #7:" +
                " Two students with same Name,DOB are equal despite credits");
        testResult(expectedOutput,actualOutput);
    }


    /**
     * Testbed main to exercise the compareTo method. Calls
     * several smaller methods.
     * @param args command line arguments
     */
    public static void main(String[] args) {

        executeTestCasesOneThroughThree();

        executeTestCasesFourThroughFive();

        executeSixAndSeven();

    }
}
