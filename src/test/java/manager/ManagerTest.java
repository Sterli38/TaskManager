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
    private SubTask subTaskForEpic;


    @BeforeEach
    void setup() {
        task = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
        epic = new Epic(2, "Описание", "Эпик");
        subTask =  new SubTask(3, "Описание", "Подзадача", Status.NEW);
        epicSubtasks = new Epic(5, "Описание", "Эпик c сабтасками");
        subTaskForEpic = new SubTask(6, "Подзадача", "Подзадача для эпика", Status.NEW);
        epicSubtasks.addSubtask(subTaskForEpic);
        manager.createTask(task);
        manager.createEpic(epic);
        manager.createEpic(epicSubtasks);
        manager.createSubtask(subTask, epic.getId());
        manager.createSubtask(subTaskForEpic, epicSubtasks.getId());
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
        tasks.add(new Task(10, "Новая созданная задача", "Новая созданная задача", Status.NEW));
        manager.createTask(new Task(10, "Новая созданная задача", "Новая созданная задача", Status.NEW));
        Assertions.assertEquals(tasks, manager.getTasks());
    }

    @Test
    void updateTask() {
        Task updateTask = new Task(1, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateTask(updateTask);
        Assertions.assertEquals(updateTask, manager.getTaskById(1));
    }

    @Test
    void removeTaskById() {
        manager.removeTaskById(1);
        Assertions.assertEquals(0, manager.getTasks().size()); // Ожидается 0, так как создана только одна задача
    }

    @Test
    void getEpics() {
        List<Epic> epics = new ArrayList<>();
        epics.add(epic);
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
        Assertions.assertEquals(epicSubtasks, manager.getEpicById(5));
    }

    @Test
    void createEpic() {
        List<Epic> epics = new ArrayList<>();
        Epic testEpic = new Epic(15, "Тестовый эпик", "Эпик для теста");
        testEpic.addSubtask(subTask);
        epics.add(epic);
        epics.add(epicSubtasks);
        epics.add(testEpic);
        manager.createEpic(testEpic);
        Assertions.assertEquals(epics, manager.getEpics());
        Assertions.assertEquals(testEpic.getSubtasksList(), manager.getEpicById(15).getSubtasksList());
    }

    @Test
    void updateEpic() {
        Epic epic1 = new Epic(2, "Новый эпик", "Новое Описание эпика");
        manager.updateEpic(epic1);
        Assertions.assertEquals(epic1, manager.getEpicById(2));
    }

    @Test
    void removeEpicById() {
        manager.removeEpicById(2);
        Assertions.assertEquals(1, manager.getEpics().size());
    }

    @Test
    void addSubtasksToEpicTest() {
        List<SubTask> epics = new ArrayList<>();
        SubTask testSubtask = new SubTask(11, "Новая подзадача", "Новая подзадача эпика", Status.NEW);
        manager.addSubtask(5, testSubtask);
        epics.add(subTaskForEpic);
        epics.add(testSubtask);
        Assertions.assertEquals(epics, manager.getEpicById(5).getSubtasksList());
    }

    @Test
    void getSubtasks() {
        List<SubTask> subtasks = new ArrayList<>();
        subtasks.add(subTask);
        subtasks.add(subTaskForEpic);
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
        SubTask testSubtask = new SubTask(10, "Новая задача", "Новая задача", Status.IN_PROGRESS);
        List<SubTask> subTasks = new ArrayList<>();
        subTasks.add(subTask);
        subTasks.add(subTaskForEpic);
        subTasks.add(testSubtask);
        manager.createSubtask(testSubtask, epicSubtasks.getId());
        Assertions.assertEquals(subTasks, manager.getSubtasks());
    }

    @Test
    void updateSubtask() {
        SubTask subTask1 = new SubTask(3, "Новая Подзадача", "Новое Описание Подзадачи", Status.NEW);
        subTask1.setEpicId(epic.getId());
        manager.updateSubtask(subTask1);
        Assertions.assertEquals(subTask1, manager.getSubtaskById(3));
    }

    @Test
    void removeSubtaskById() {
        manager.removeSubtaskById(6);
        Assertions.assertEquals(1, manager.getSubtasks().size());
    }

    @Test
    void getAllTheEpicSubtasks() {
        Assertions.assertEquals(epicSubtasks.getSubtasksList(), manager.getAllTheEpicSubtasks(epicSubtasks));
    }

    @Test
    void deleteAllTasks() {
        manager.deleteAllTasks();
        Assertions.assertEquals(0, manager.getTasks().size());
        Assertions.assertEquals(0, manager.getEpics().size());
        Assertions.assertEquals(0, manager.getSubtasks().size());
    }
}
