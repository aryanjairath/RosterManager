package rosterfiles;
/**
 * This class defines a student profile with a lastname, a first name,
 * and a date of birth.
 * @author Anis Chihoub, Aryan Jairath
 */
public class Profile implements Comparable<Profile> {
    private String lname;
    private String fname;
    private Date dob; //use the Date class described in (f)

    public static final int LESSTHAN = -1;
    public static final int EQUALTO = 0;
    public static final int GREATERTHAN = 1;

    /**
     * Constructor for the Profile class. Defines
     * the first and last names, as well as a
     * date of birth.
     * @param lname A string representing a last name of a student.
     * @param fname A string representing a first name of a student.
     * @param dob   A date object representing the date of birth of a student.
     */
    public Profile(String lname, String fname, Date dob) {
        this.lname = lname;
        this.fname = fname;
        this.dob = dob;
    }

    /**
     * Compare the date of births for two profiles.
     * @param profile, a profile object for another student
     * @return an integer in the set {1,-1}, which correspond to greater than
     * or less than respectively.
     */
    private int checkDateOfBirths(Profile profile) {
        if (this.dob.compareTo(profile.getDob()) > EQUALTO) {
            return GREATERTHAN;
        } else if (this.dob.compareTo(profile.getDob()) == EQUALTO) {
            return EQUALTO;
        }
        return LESSTHAN;
    }

    /**
     * Compare firstNames of two students lexographically
     * @param profile a profile object for another student
     * @return an integer in the set {1,0,-1}, which correspond to greater than,
     * equal to , or less than respectively.
     */
    private int checkFirstNames(Profile profile){
        if(this.fname.compareTo(profile.getFname()) > EQUALTO){
            return GREATERTHAN;
        }else if(this.fname.compareTo(profile.getFname()) == EQUALTO){
            int dateOfBirthsComparisons = checkDateOfBirths(profile);
            if(dateOfBirthsComparisons == EQUALTO){
                return EQUALTO;
            }else{
                return dateOfBirthsComparisons;
            }
        }else{
            return LESSTHAN;
        }
    }


    /**
     * Compares two Profile objects.
     * @param profile the object to be compared.
     * @return int, an integer representing if one Profile
     * is greater than the other in the set (-1,1).
     */
    @Override
    public int compareTo(Profile profile){
        if(this.lname.compareTo(profile.getLname()) > EQUALTO){
            return GREATERTHAN;
        }else if(this.lname.compareTo(profile.getLname()) == EQUALTO){
            int firstNameComparison = checkFirstNames(profile);
            if(firstNameComparison == EQUALTO){
                return checkDateOfBirths(profile);
            }else{
                return firstNameComparison;
            }
        }else{
            return LESSTHAN;
        }
    }

    /**
     * Compares two profile objects to see if they are equal based on the first name,
     * last name, and date of birth
     * @param objToCheck an object we want to compare
     * @return a boolean, representing if the profile equals the other obj.
     */
    @Override
    public boolean equals(Object objToCheck){
        if(objToCheck instanceof Profile){
            return this.fname.equalsIgnoreCase(((Profile) objToCheck).getFname())
                    && this.lname.equalsIgnoreCase(((Profile) objToCheck).getLname())
                    && this.dob.equals(((Profile) objToCheck).getDob());
        }else{
            return false;
        }
    }

    /**
     * Generates a string version of the profile object.
     * @return a string containing the details of this profile object.
     */
    @Override
    public String toString(){
        return this.fname + " " + this.lname + " " + this.dob.toString();
    }

    /**
     * Returns the last name for this student
     * @return a string representing this student's last name
     */
    public String getLname(){
        return this.lname;
    }

    /**
     * Returns the first name for this student
     * @return fname, a string representing this stundent's first name
     */
    public String getFname(){
        return this.fname;
    }

    /**
     * Returns the date of birth object
     * @return Date object, the date of birth object for this student
     */
    public Date getDob(){
        return this.dob;
    }
}