package rosterfiles;
/**
 * This class defines a roster consisting of multiple students.
 * The roster can add, delete and remove students.
 * An end user can also request students be printed
 * by some order.
 * @author Aryan Jairath, Anis Chihoub
 */
public class Roster {

    public static final int INITIALSIZE = 4;
    public static final int ARRAYINDEXMIUSONE = 1;
    public static final int SMALLER = 0;

    public static final int EQUALSTUDENTS = 0;
    public static final int EQUALMAJORS = 0;
    public static final int EQUALSTANDING = 0;
    public static final int NOT_FOUND = -1;

    private Student[] roster;
    private int size;

    /**
     * Initializes a Roster object, initializes the roster array,
     * and sets the initial size to four.
     */
    public Roster(){
        this.size = INITIALSIZE;
        roster = new Student[this.size];
    }

    /**
     * This method looks for a certain student and the
     * associated index.
     * @param student The student that is being searched for
     * @return The index if the student is in the array,
     * otherwise, -1 is returned.
     */
    private int find(Student student) {
        for(int i = 0; i < roster.length; i++){
            if(roster[i] == null)
                return NOT_FOUND;
            if(roster[i].equals(student)) {
                int studentIndex = i;
                return studentIndex;
            }
        }
        return NOT_FOUND;
    } //search the given student in roster

    /**
     * Updates a student's major
     * @param studentUpdate Student whose major is to be updated
     * @param newMajor The major which the student is switching to
     */
    public void updateMajor(Student studentUpdate, Major newMajor){
        int index = find(studentUpdate);

        if(index == NOT_FOUND){
            System.out.println(studentUpdate.getProfile().toString() + " is not in the roster.");
        }else{
            this.roster[index].setMajor(newMajor);
            System.out.println(studentUpdate.getProfile().toString() + " major changed to " + newMajor.name());
        }
    }

    /**
     * Increases the size of the roster array by 4
     */
    private void grow() {
        this.size = this.size + INITIALSIZE;
        Student[] largerStudentRoster = new Student[this.size];
        for(int i = 0; i < roster.length; i++){
            largerStudentRoster[i] = this.roster[i];
        }
        this.roster = largerStudentRoster;

    } //increase the array capacity by 4

    /**
     * Adds a student to the roster array if
     * student is not already in the array
     * @param student The student to be added to the array
     * @return A boolean representing if student
     * was successfully added
     */
    public boolean add(Student student){
        if(contains(student))
            return false;
        if(roster[size-ARRAYINDEXMIUSONE] != null) {
            grow();
        }
        for(int i = 0; i < roster.length; i++){
            if(roster[i] == null){
                roster[i] = student;
                return true;
            }
        }
        return false;
    } //add student to end of array

    /**
     * Removes a specified student from the roster
     * @param student The student to be removed from the roster.
     * @return A boolean representing whether the student
     * was successfully removed from the roster array.
     */
    public boolean remove(Student student){
        if(!(contains(student)))
            return false;
        Student[] smallerStudentRoster = new Student[this.size-ARRAYINDEXMIUSONE];
        int populateSmaller = SMALLER;
        for(int i = 0; i < roster.length; i++){
            if(roster[i] == null)
                break;
            if(!(roster[i].equals(student))) {
                smallerStudentRoster[populateSmaller] = roster[i];
                populateSmaller++;
            }
        }
        size--;
        this.roster = smallerStudentRoster;
        return true;
    }//maintain the order after remove

    /**
     * This method checks if the array contains a certain student.
     * @param student The student being checked for presence in array.
     * @return A boolean representing if the student is in array.
     */
    public boolean contains(Student student){
        for(int i = 0; i < roster.length; i++){
            if(roster[i] == null)
                return false;
            if(roster[i].equals(student)) {
                return true;
            }
        }
        return false;
    } //if the student is in roster

    /**
     * Sorts the array based on the students of the roster array.
     * Students are compared and sorted based on their profiles.
     */
    public void sortByName(){
        for(int i = 0; i < roster.length-ARRAYINDEXMIUSONE; i++){
            int minIndex = i;
            for(int j = i + 1; j < roster.length; j++){
                boolean notNull = (roster[i] != null && roster[j] != null);
                if(notNull && roster[j].compareTo(roster[minIndex]) < EQUALSTUDENTS)
                    minIndex=j;
            }
            Student tempStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tempStudent;

        }
    }

    /**
     * Sorts the array based on the standing/year of the roster array.
     */
    public void sortByStanding(){
        for(int i = 0; i < roster.length-ARRAYINDEXMIUSONE; i++) {
            int minIndex = i;
            for (int j = i + 1; j < roster.length; j++) {
                boolean notNull = (roster[i] != null && roster[j] != null);
                if (notNull && roster[j].collegeYear()
                        .compareTo(roster[minIndex].collegeYear()) < EQUALSTANDING)
                    minIndex = j;
            }
            Student tempStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tempStudent;
        }
    }

    /**
     * Sorts the array based on the major of the roster array.
     */
    public void sortByMajor(){
        for(int i = 0; i < roster.length-ARRAYINDEXMIUSONE; i++){
            int minIndex = i;
            for(int j = i + 1; j < roster.length; j++) {
                boolean notNull = (roster[i] != null && roster[j] != null);
                if (notNull && roster[j].getMajor().getSchool()
                        .compareTo(roster[minIndex].getMajor().getSchool()) < EQUALMAJORS){
                    minIndex = j;
                }else{ //both are same majors
                    if(notNull && roster[j].getMajor().getSchool()
                            .compareTo(roster[minIndex].getMajor().getSchool()) == EQUALMAJORS) {
                        if (notNull && roster[j].getMajor()
                                .compareTo(roster[minIndex].getMajor()) < EQUALMAJORS)
                            minIndex = j;
                    }
                }
            }
            Student tempStudent = roster[minIndex];
            roster[minIndex] = roster[i];
            roster[i] = tempStudent;
        }
    }

    /**
     * Checks if the roster array contains atleast one student
     * @return A boolean representing if the array contains
     * one or more students
     */
    private boolean isEmpty(){
        boolean allNull = true;
        for(int j = 0; j < this.roster.length; j++){
            if(this.roster[j] != null){
                allNull = false;
            }
        }
        return allNull;
    }

    /**
     * Print the roster array sorted by students. They are sorted
     * by last name, first name, and DOB.
     */
    public void print() {
        if(isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by last name," +
                "first name, DOB **");
        sortByName();
        for(int i = 0; i < roster.length; i++){
            if(roster[i] != null)
                System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    } //print roster sorted by profiles

    /**
     * Print the roster array sorted by major of each
     * student
     */
    public void printBySchoolMajor() {
        if(isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by school" +
                ", major **");
        sortByMajor();
        for(int i = 0; i < roster.length; i++){
            if(roster[i] != null)
                System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    } //print roster sorted by school major

    /**
     * Print the roster array sorted by the standing of
     * each student. Freshman, Sophomore, etc.
     */
    public void printByStanding() {
        if(isEmpty()){
            System.out.println("Student roster is empty!");
            return;
        }
        System.out.println("* Student roster sorted by standing**");
        sortByStanding();
        for(int i = 0; i < roster.length; i++){
            if(roster[i] != null)
                System.out.println(roster[i].toString());
        }
        System.out.println("* end of roster **");
    } //print roster sorted by standing

    /**
     * This method returns the roster array.
     * @return A student array representing the roster
     * of all students.
     */
    public Student[] getRoster(){
        return this.roster;
    }

    /**
     * Return the size of the roster array
     * @return An integer representing the size of
     * the array of students.
     */
    public int getSize(){
        return this.size;
    }
}