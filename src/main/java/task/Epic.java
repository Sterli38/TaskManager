package task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subtasks = new ArrayList<>();

    public Epic(String name, String description, int id, Status status) {
        super(name, description, id, status);
    }

    public List<SubTask> getSubtasksList() {
        return subtasks;
    }

    public void add(SubTask subTask) {
        subtasks.add(subTask);
    }


}
