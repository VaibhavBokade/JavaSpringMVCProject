package org.example.comparators;

import org.example.entities.Task;

import java.util.Comparator;
/*by using this we sort task by due date*/

public class SortByDueDate implements Comparator<Task> {

    @Override
    public int compare(Task o1, Task o2) {
        return o1.getDueDate().compareTo(o2.getDueDate());
    }
}
