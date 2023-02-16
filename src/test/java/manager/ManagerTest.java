package manager;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.List;

public abstract class ManagerTest<T extends Manager> {

    protected T manager;
    private Task task;
    private Epic epic;
    private SubTask subTask;
    private Epic epicSubtasks;


    @BeforeEach
    void setup() {
        task = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
        epic = new Epic(2, "Описание", "Эпик");
        subTask =  new SubTask(3, "Описание", "Подзадача", Status.NEW);
        epicSubtasks = new Epic(2, "Описание", "Эпик");
        epicSubtasks.addSubtask(subTask);
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createSubtask(subTask, epic.getId());
    }

    @AfterEach
    void clear() {
        manager.removeAllTasks();
        manager.removeAllEpics();
        manager.removeAllSubtasks();
    }

    @Test
    void getTasks() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        Assertions.assertEquals(tasks, manager.getTasks());
    }

    @Test
    void removeAllTasks() {
        manager.removeAllTasks();
        Assertions.assertEquals(0, manager.getTasks().size());
    }

    @Test
    void getTaskById() {
        Assertions.assertEquals(task, manager.getTaskById(1));
    }

    @Test
    void createTask() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(task);
        tasks.add(new Task(10, "Задача", "Описание", Status.NEW));
        manager.createTask(new Task(10, "Задача", "Описание", Status.NEW));
        Assertions.assertEquals(tasks, manager.getTasks());
    }

    @Test
    void updateTask() {
        Task task1 = new Task(1, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateTask(task1);
        Assertions.assertEquals(task1, manager.getTaskById(1));
    }

    @Test
    void removeTaskById() {
        manager.removeTaskById(1);
        Assertions.assertEquals(0, manager.getTasks().size());
    }

    @Test
    void getEpics() {
        List<Epic> epics = new ArrayList<>();
        epics.add(epic);
        Assertions.assertEquals(epics, manager.getEpics());
    }

    @Test
    void getEpicsWithSubtasks() {
        List<Epic> epics = new ArrayList<>();
        epics.add(epicSubtasks);
        Assertions.assertEquals(epics, manager.getEpics());
    }


    @Test
    void removeAllEpics() {
        manager.removeAllEpics();
        Assertions.assertEquals(0, manager.getEpics().size());
    }

    @Test
    void getEpicById() {
        Assertions.assertEquals(epic, manager.getEpicById(2));
    }

    @Test
    void createEpic() {
        List<Epic> epics = new ArrayList<>();
        epics.add(epic);
        epics.add(new Epic(10, "Задача", "Описание"));
        manager.createEpic(new Epic(10, "Задача", "Описание"));
        Assertions.assertEquals(epics, manager.getEpics());
    }

    @Test
    void updateEpic() {
        Epic epic1 = new Epic(2, "Новый эпик", "Новое Описание эпика");
        epic1.addSubtask(subTask);
        Epic updateEpic = new Epic(2, "Новый эпик", "Новое Описание эпика");
        manager.updateEpic(updateEpic);
        Assertions.assertEquals(epic1, manager.getEpicById(2));
    }

    @Test
    void removeEpicById() {
        manager.removeEpicById(2);
        Assertions.assertEquals(0, manager.getEpics().size());
    }

    @Test
    void addSubtasksToEpicTest() {
        Epic epic1 = new Epic(2, "Описание", "Эпик");
        SubTask subTask1 = new SubTask(3, "Описание", "Подзадача", Status.NEW);
        epic1.addSubtask(subTask1);
        Assertions.assertEquals(epic1.getSubtasksList(), manager.getEpicById(2).getSubtasksList());
    }

    @Test
    void getSubtasks() {
        List<SubTask> subtasks = new ArrayList<>();
        subtasks.add(subTask);
        Assertions.assertEquals(subtasks, manager.getSubtasks());
    }

    @Test
    void removeAllSubtasks() {
        manager.removeAllSubtasks();
        Assertions.assertEquals(0, manager.getSubtasks().size());
    }

    @Test
    void getSubtaskById() {
        Assertions.assertEquals(subTask, manager.getSubtaskById(3));
    }

    @Test
    void createSubtask() {
        SubTask subTask1 = new SubTask(10, "Задача", "Описание", Status.IN_PROGRESS);
        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(subTask);
        subTasks.add(subTask1);
        epic.addSubtask(subTask1);
        manager.createSubtask(subTask1, epic.getId());
        Assertions.assertEquals(subTasks, manager.getSubtasks()); // Проверяем наличие нового subTask
        Assertions.assertEquals(epic.getSubtasksList(), manager.getEpicById(2).getSubtasksList()); // Проверяем наличие нового subTask внутри Epic
    }

    @Test
    void updateSubtask() {
        SubTask subTask1 = new SubTask(3, "Новая задача", "Новое Описание", Status.NEW);
        subTask1.setEpicId(epic.getId());
        SubTask updateSubTask = new SubTask(3, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateSubtask(updateSubTask);
        Assertions.assertEquals(subTask1, manager.getSubtaskById(3));
    }

    @Test
    void removeSubtaskById() {
        manager.removeSubtaskById(3);
        Assertions.assertEquals(0, manager.getSubtasks().size());
    }

    @Test
    void getAllTheEpicSubtasks() {
        Epic epic1 = new Epic(2, "Описание", "Эпик");
        SubTask expectedSubTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);
        epic1.addSubtask(subTask);
        epic1.addSubtask(expectedSubTask);
        List<SubTask> expected = new ArrayList<>();
        expected.add(expectedSubTask);
        List<SubTask> actual = manager.getEpicById(2).getSubtasksList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteAllTasks() {
        manager.deleteAllTasks();
        Assertions.assertEquals(0, manager.getTasks().size());
        Assertions.assertEquals(0, manager.getEpics().size());
        Assertions.assertEquals(0, manager.getSubtasks().size());
    }
}
