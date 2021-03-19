package optional.sap;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optional.school.School;
import optional.student.Student;

import java.util.*;

@Setter
@Getter
@EqualsAndHashCode
@ToString

public class Problem {

    private Student[] students;
    private School[] schools;
    private Map<Student, List<School>> studentsPreferences = new HashMap<>();
    private Map<School, List<Student>> schoolsPreferences = new HashMap<>();

    /**
     * constructor default
     */
    public Problem() {

    }

    /**
     * Constructor
     *
     * @param students
     * @param schools
     */
    public Problem(Student[] students, School[] schools) {
        this.schools = schools;
        this.students = students;
    }

    /**
     * The method add a list of schools to the student's preferences
     *
     * @param student
     * @param schoolList
     */
    public void addStudentPreferences(Student student, List<School> schoolList) {
        studentsPreferences.put(student, schoolList);
    }

    /**
     * The method add a list of students to the school's preferences
     *
     * @param school
     * @param studentList
     */
    public void addSchoolPreferences(School school, List<Student> studentList) {
        schoolsPreferences.put(school, studentList);
    }

    /**
     * From from request: Using Java Stream API, write queries that display the students who find acceptable a given list of schools
     * this method will be called in the main to display what is what is required
     *
     * @param studentName
     * @return
     */
    public List<School> getListOfAcceptableSchools(String studentName) {

        Optional<List<School>> listOfAcceptableSchools = studentsPreferences.entrySet().stream().filter(e -> e.getKey().getName().equals(studentName)).map(Map.Entry::getValue).findFirst();

        if (listOfAcceptableSchools.isPresent()) {
            return listOfAcceptableSchools.get();
        } else {
            return null;
        }
    }

    /**
     * From from request: Using Java Stream API, write queries that display the schools that have a given student as their top preference
     * this method will be called in the main to display what is what is required
     *
     * @param schoolName
     * @return
     */
    public Student getFavoriteStudent(String schoolName) {

        Optional<List<Student>> favoriteStudent = schoolsPreferences.entrySet().stream().filter(e -> e.getKey().getName().equals(schoolName)).map(Map.Entry::getValue).findFirst();

        if (favoriteStudent.isPresent()) {

            Iterator iter = favoriteStudent.get().iterator();
            return (Student) iter.next();

        } else {
            return null;
        }
    }

}
