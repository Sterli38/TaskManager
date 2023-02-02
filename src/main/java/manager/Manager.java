package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.List;

public interface Manager {
    List<Task> getTasks();

    void removeAllTasks();

    Task getTaskById(int id);

    void createTask(Task task);

    void updateTask(Task task);

    void removeTaskById(int id);

    List<Epic> getEpics();

    void removeAllEpics();

    Epic getEpicById(int id);

    void createEpic(Epic epic);

    void updateEpic(Epic epic);

    void removeEpicById(int id);

    List<SubTask> getSubtasks();

    void removeAllSubtasks();

    SubTask getSubtaskById(int id);

    void createSubtask(SubTask subtask, int epicId);

    void updateSubtask(SubTask subTask);

    void removeSubtaskById(int id);

    List<SubTask> getAllTheEpicSubtasks(Epic epic);

    void deleteAllTasks();
}
