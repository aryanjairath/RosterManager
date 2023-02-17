package rosterfiles;

/**
 * This class defines an enum class for the possible majors.
 * The five possible majors are CS, MATH, EE, ITI, and BAIT.
 * Each Major class will be used in the Student class to
 * identify a possible major
 * @author Aryan Jairath, Anis Chihoub
 */
public enum Major {

    /**
     * Computer Science Major in SAS school
     */
    CS("01:198", "SAS"),

    /**
     * Math Major in SAS school
     */
    MATH("01:640", "SAS"),

    /**
     * Electrical Engineering Major in SAS school
     */
    EE("14:332", "SOE"),

    /**
     * Information Technology and Informatics Major in SCI school
     */
    ITI("04:547", "SC&I"),

    /**
     * Business Analytics and Information Technology Major in RBS school
     */
    BAIT("33:136", "RBS");

    /**
     * Instance variables defining the class code
     * and school where the major is housed.
     */
    private final String code; //Class Code
    private final String school; //Department i.e. SAS

    /**
     * Constructor for the Major class.
     * @param code, a string holding class code
     * @param school, a string holding the school for the major
     */
    Major(String code, String school) {
        this.code = code;
        this.school = school;
    }

    /**
     * This is a getter method which returns the Major's code.
     * @return a String containing the code.
     */
    public String getCode(){
        return this.code;
    }

    /**
     * This is a getter method which returns the school.
     * @return a String containing the school string.
     */
    public String getSchool(){
        return this.school;
    }

}