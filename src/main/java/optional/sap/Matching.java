package optional.sap;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import optional.school.School;
import optional.student.Student;

import java.util.*;
import java.util.stream.Collectors;

@Setter
@Getter
@EqualsAndHashCode
@ToString

public class Matching {

    private Problem problem;

    /**
     * Constructor
     *
     * @param p
     */
    public Matching(Problem p) {
        this.problem = p;
    }

    /**
     * This method sorts the schoolTop by the position of the student in the top of the students preferred by that school
     *
     * @param schoolTop top of the school which need to be sorted
     * @return the new top of the school sorted by position of the student in the top of the students preferred by that school
     */
    public Map<School, Integer> sortShoolTopForStudent(Map<School, Integer> schoolTop) {
        Map<School, Integer> schoolTopSortedByPosition = schoolTop.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toMap(
                        Map.Entry::getKey,
                        Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return schoolTopSortedByPosition;
    }

    /**
     * This method returns the first in schools from the already sorted top in which the student is in the same position
     *
     * @param schoolSortedTop
     * @return
     */
    public List<School> getTheNFirstSchoolFromSchoolTop(Map<School, Integer> schoolSortedTop) {
        List<School> schoolList = new ArrayList<>();
        int studentPosition = -1;
        for (Map.Entry<School, Integer> entry : schoolSortedTop.entrySet()) {

            if (studentPosition == -1) {
                schoolList.add(entry.getKey());
            }
            if (studentPosition == entry.getValue()) {
                schoolList.add(entry.getKey());
            }
            if (studentPosition != entry.getValue() && studentPosition != -1) {
                break;
            }
            studentPosition = entry.getValue();
        }
        return schoolList;
    }

    /**
     * This method creates a top of the schools for each student:
     * it adds to the top each available school and the position that the student is in the preferences of the school
     *
     * @param std - the student for whom I create the top
     * @return the top
     */
    public Map<School, Integer> getSchoolTopForStudent(Student std) {

        Map<School, Integer> schoolTop = new HashMap<>();
        for (Map.Entry<School, List<Student>> entry : problem.getSchoolsPreferences().entrySet()) {
            int position = 0;
            for (Student s : entry.getValue()) {
                if (s.equals(std)) {
                    break;
                } else {
                    position++;
                }
            }
            schoolTop.put(entry.getKey(), position);
        }

        return schoolTop;
    }

    /**
     * This method display the solution (the matching) of this problem:
     * the preferences of each potential student in order are covered.
     * the student will go to the first school that will be in his preferences, but also in the first schools in his top (schools that have the student in the first positions in preferences).
     * The student will go to that school only if there are still places (capacity> 0), otherwise, he will move to the next school in the top of the student's schools
     */
    public void getMatching() {

        for (Student std : problem.getStudents()) {

            Iterator iter = problem.getListOfAcceptableSchools(std.getName()).iterator();

            for (School h : problem.getListOfAcceptableSchools(std.getName())) {

                if (getTheNFirstSchoolFromSchoolTop(sortShoolTopForStudent(getSchoolTopForStudent(std))).contains(h) && h.getCapacity() > 0) {
                    System.out.println(std.getName() + " will study at " + h.getName());
                    h.setCapacity(h.getCapacity() - 1);
                    break;
                }
            }
        }

    }
}
