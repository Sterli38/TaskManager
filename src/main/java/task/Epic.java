package task;

import java.util.ArrayList;
import java.util.List;

public class Epic {
    private List<SubTask> subtasks = new ArrayList<>();

    public List<SubTask> getAllSubtasks() {
        return subtasks;
    }

    public void add(SubTask subTask) {
        subtasks.add(subTask);
    }
}
