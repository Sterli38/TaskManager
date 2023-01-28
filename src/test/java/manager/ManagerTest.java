package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import static org.junit.jupiter.api.Assertions.*;

public abstract class ManagerTest<T extends Manager> {

    protected T manager;

    @BeforeEach
    void setup() {
        Task task = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
        Epic epic = new Epic("Эпик", "Описание", 2);
        SubTask subTask = new SubTask("Подзадача", "Описание", 3, Status.NEW);
        manager.createEpic(epic);
        manager.createTask(task);
        manager.createSubtask(subTask, epic.getId());
    }

    @AfterEach
    void clear() {
        manager.removeAllEpics();
        manager.removeAllSubtasks();
        manager.removeAllTasks();
    }

    @Test
    void getTasks() {
        manager.createTask();
    }

    @Test
    void removeAllTasks() {
    }

    @Test
    void getTaskById() {
    }

    @Test
    void createTask() {
    }

    @Test
    void updateTask() {
    }

    @Test
    void removeTaskById() {
    }

    @Test
    void getAllEpics() {
    }

    @Test
    void removeAllEpics() {
    }

    @Test
    void getEpic() {
    }

    @Test
    void createEpic() {
    }

    @Test
    void updateEpic() {
    }

    @Test
    void removeEpicById() {
    }

    @Test
    void getSubtasks() {
    }

    @Test
    void removeAllSubtasks() {
    }

    @Test
    void getSubtaskById() {
    }

    @Test
    void createSubtask() {
    }

    @Test
    void updateSubtask() {
    }

    @Test
    void removeSubtaskById() {
    }

    @Test
    void getAllTheEpicSubtasks() {
    }

    @Test
    void deleteAllTasks() {
    }
}