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
    Task testTask;
    Epic testEpic;
    SubTask testSubTask;
    List<Task> taskExpectedList = new ArrayList<>();
    List<Task> epicExpectedList = new ArrayList<>();
    List<Task> subtaskExpectedList = new ArrayList<>();


    @BeforeEach
    void setup() {
        Task task = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
        Epic epic = new Epic(2, "Описание", "Эпик");
        SubTask subTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);
        testTask = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
        testEpic = new Epic(2, "Описание", "Эпик");
        testSubTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);
        taskExpectedList.add(testTask);
        epicExpectedList.add(testEpic);
        subtaskExpectedList.add(testSubTask);
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
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
    }

    @Test
    void removeAllTasks() {
        manager.removeAllTasks();
        taskExpectedList.clear();
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
    }

    @Test
    void getTaskById() {
        Task expected = taskExpectedList.get(0);
        Task actual = manager.getTaskById(1);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void createTask() {
        taskExpectedList.add(new Task(10, "Задача", "Описание", Status.NEW));
        manager.createTask(new Task(10, "Задача", "Описание", Status.NEW));
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
    }

    @Test
    void updateTask() {
        taskExpectedList.set(0, new Task(1, "Новая задача", "Новое Описание", Status.NEW));
        Task updateTask = new Task(1, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateTask(updateTask);
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
    }

    @Test
    void removeTaskById() {
        Task task1 = new Task(6, "Задача", "Описание", Status.NEW);
        Task testTask1 = new Task(6, "Задача", "Описание", Status.NEW);
        manager.createTask(task1);
        taskExpectedList.add(testTask1);
        taskExpectedList.remove(testTask1);
        manager.removeTaskById(6);
        Assertions.assertEquals(taskExpectedList, manager.getTasks());

    }

    @Test
    void getEpics() {
        List<Epic> actual = manager.getEpics();
        Assertions.assertEquals(epicExpectedList, actual);
    }

    @Test
    void removeAllEpics() {
        manager.removeAllEpics();
        epicExpectedList.clear();
        Assertions.assertEquals(epicExpectedList, manager.getEpics());
    }

    @Test
    void getEpicById() {
        Assertions.assertEquals(epicExpectedList.get(0), manager.getEpicById(2));
    }

    @Test
    void createEpic() {
        epicExpectedList.add(new Epic(10, "Задача", "Описание"));
        manager.createEpic(new Epic(10, "Задача", "Описание"));
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
    }

    @Test
    void updateEpic() {
        epicExpectedList.set(0,new Epic(2, "Новая задача", "Новое Описание"));
        Epic updateEpic = new Epic(2, "Новая задача", "Новое Описание");
        manager.updateEpic(updateEpic);
        Assertions.assertEquals(epicExpectedList, manager.getEpics());
    }

    @Test
    void removeEpicById() {
        Epic epic1 = new Epic(6, "Задача", "Описание");
        Epic testEpic1 = new Epic(6, "Задача", "Описание");
        manager.createEpic(epic1);
        epicExpectedList.add(testEpic1);
        epicExpectedList.remove(testEpic1);
        manager.removeEpicById(6);
        Assertions.assertEquals(epicExpectedList, manager.getEpics());
    }

    @Test
    void getSubtasks() {
        List<SubTask> actual = manager.getSubtasks();
        Assertions.assertEquals(subtaskExpectedList, actual);
    }

    @Test
    void removeAllSubtasks() {
        manager.removeAllSubtasks();
        subtaskExpectedList.clear();
        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
    }

    @Test
    void getSubtaskById() {
        Assertions.assertEquals(subtaskExpectedList.get(0), manager.getSubtaskById(3));
    }

    @Test
    void createSubtask() {
        SubTask subtask = new SubTask(10, "Задача", "Описание", Status.IN_PROGRESS);
        subtaskExpectedList.add(subtask);
        manager.createSubtask(subtask, testEpic.getId());
        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());

//        testEpic.addSubtask(subtask);
//        manager.getEpicById(2).addSubtask(subtask);
//        Assertions.assertEquals(testEpic.getSubtasksList(), manager.getEpicById(2).getSubtasksList());
        //
    }

    @Test
    void updateSubtask() {
        subtaskExpectedList.add(0, new SubTask(3, "Новая задача", "Новое Описание", Status.NEW));
        SubTask updatesubTask = new SubTask(3, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateSubtask(updatesubTask);
        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
    }

    @Test
    void removeSubtaskById() {
        SubTask subTask = new SubTask(6, "Задача", "Описание", Status.NEW);
        SubTask testSubTask1 = new SubTask(6, "Задача", "Описание", Status.NEW);
        manager.createSubtask(subTask, manager.getEpicById(2).getId());
        subtaskExpectedList.add(testSubTask1);
        subtaskExpectedList.remove(testSubTask);
        manager.removeSubtaskById(3);
        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
    }

    @Test
    void getAllTheEpicSubtasks() {
        SubTask expectedSubTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);
        List<SubTask> expected = new ArrayList<>();
        expected.add(expectedSubTask);
        List<SubTask> actual = manager.getEpicById(2).getSubtasksList();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void deleteAllTasks() {
        taskExpectedList.clear();
        epicExpectedList.clear();
        subtaskExpectedList.clear();
        manager.deleteAllTasks();
        Assertions.assertEquals(taskExpectedList, manager.getTasks());
        Assertions.assertEquals(epicExpectedList, manager.getEpics());
        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
    }
}
