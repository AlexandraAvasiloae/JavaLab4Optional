package optional.student;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Comparator;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class Student implements Comparator<Student> {

    private String name;

    /**
     * Constructor
     *
     * @param name
     */
    public Student(String name) {
        this.name = name;
    }

    public int compare(Student o1, Student o2) {
        return o1.getName().compareTo(o2.getName());
    }

}
