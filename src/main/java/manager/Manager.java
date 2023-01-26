package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.List;

public interface Manager {
    public List<Task> getTasks();

    public void removeAllTasks();

    public Task getTask(int id);

    public void taskCreation(Task task);

    public void updateTask(Task task);

    public void removeTask(int id);

    public List<Epic> getEpics();

    public void removeAllEpics();

    public Epic getEpic(int id);

    public void epicCreation(Epic epic);

    public void updateEpic(Epic epic);

    public void removeEpic(int id);

    public List<SubTask> getSubtasks();

    public void removeAllSubtasks();

    public SubTask getSubtask(int id);

    public void subTaskCreation(SubTask subtask);

    public void updateSubtask(SubTask subTask);

    public void removeSubtask(int id);

    public List<SubTask> getAllTheEpicSubtasks(Epic epic);

    public void deleteAllTasks();
}
