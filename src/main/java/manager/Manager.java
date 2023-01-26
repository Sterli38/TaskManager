package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.List;

public interface Manager {
    List<Task> getTasks();

    void removeAllTasks();

    Task getTask(int id);

    void taskCreation(Task task);

    void updateTask(Task task);

    void removeTask(int id);

    List<Epic> getEpics();

    void removeAllEpics();

    Epic getEpic(int id);

    void epicCreation(Epic epic);

    void updateEpic(Epic epic);

    void removeEpic(int id);

    List<SubTask> getSubtasks();

    void removeAllSubtasks();

    SubTask getSubtask(int id);

    void subTaskCreation(SubTask subtask);

    void updateSubtask(SubTask subTask);

    void removeSubtask(int id);

    List<SubTask> getAllTheEpicSubtasks(Epic epic);

    void deleteAllTasks();
}
