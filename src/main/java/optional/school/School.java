package optional.school;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@EqualsAndHashCode
@ToString
public class School implements Comparable<School> {
    private String name;
    private int capacity;

    /**
     * Constructor
     *
     * @param name
     */
    public School(String name) {
        this.name = name;
        this.capacity = capacity;
    }


    /**
     * compare schools by their names
     *
     * @param o
     * @return
     */

    @Override
    public int compareTo(School o) {
        return this.name.compareTo(o.getName());
    }

}

