package task;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private List<SubTask> subtasks = new ArrayList<>();

    public Epic(int id, String name, String description) {
        super(id, name, description, null);
    }

    public List<SubTask> getSubtasksList() {
        return subtasks;
    }

    public void addSubtask(SubTask subTask) {
        subtasks.add(subTask);
        subTask.setEpicId(getId());
    }

    @Override
    public void setStatus(Status status) {
        System.out.println("Not supported");
    }

    @Override
    public Status getStatus() {
        int counterNewStatus = 0;
        int counterStatusInProgress = 0;
        int counterStatusIsDone = 0;
        for(int i = 0; i < subtasks.size(); i++) {
            Status status = subtasks.get(i).getStatus();
            if(status == Status.NEW) {
                counterNewStatus++;
            } else if(status == Status.IN_PROGRESS) {
                counterStatusInProgress++;
            } else if(status == Status.DONE) {
                counterStatusIsDone++;
            }
        }
        if(counterStatusInProgress > 0) {
            return Status.IN_PROGRESS;
        } else if (counterStatusIsDone == subtasks.size()) {
            return Status.DONE;
        } else if(counterNewStatus == subtasks.size()) {
            return Status.NEW;
        } else if(counterNewStatus != subtasks.size()) {
            return Status.IN_PROGRESS;
        } else {
            return Status.NEW;
        }
    }

}
