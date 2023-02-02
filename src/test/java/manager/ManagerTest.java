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
    private final static Task task = new Task(1, "Задача", "Описание", Status.IN_PROGRESS);
    private final static Epic epic = new Epic(2, "Описание", "Эпик");
    private final static SubTask subTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);

    @BeforeEach
    void setup() {
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
        Task updateTask = new Task(1, "Новая задача", "Новое Описание", Status.NEW);
        manager.updateTask(updateTask);
        Assertions.assertEquals(task1, manager.getTaskById(1));
    }

    @Test
    void removeTaskById() {
        manager.removeTaskById(1);
        Assertions.assertEquals(0, manager.getTasks().size());
    }
//
    @Test
    void getEpics() {
        List<Epic> epics = new ArrayList<>();
        epics.add(epic);
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
        Epic epic1 = new Epic(2, "Новая задача", "Новое Описание");
        epic1.addSubtask(subTask);
        Epic updateEpic = new Epic(2, "Новая задача", "Новое Описание");
        manager.updateEpic(updateEpic);
        Assertions.assertEquals(epic1, manager.getEpicById(2));
    }

    @Test
    void removeEpicById() {
        manager.removeEpicById(2);
        Assertions.assertEquals(0, manager.getEpics().size());
    }
    void addSubtasksToEpicTest() {

    }
//
//    @Test
//    void getSubtasks() {
//        List<SubTask> actual = manager.getSubtasks();
//        Assertions.assertEquals(subtaskExpectedList, actual);
//    }
//
//    @Test
//    void removeAllSubtasks() {
//        manager.removeAllSubtasks();
//        subtaskExpectedList.clear();
//        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
//    }
//
//    @Test
//    void getSubtaskById() {
//        Assertions.assertEquals(subtaskExpectedList.get(0), manager.getSubtaskById(3));
//    }
//
//    @Test
//    void createSubtask() {
//        SubTask subtask = new SubTask(10, "Задача", "Описание", Status.IN_PROGRESS);
//        subtaskExpectedList.add(subtask);
//        manager.createSubtask(subtask, testEpic.getId());
//        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
//
////        testEpic.addSubtask(subtask);
////        manager.getEpicById(2).addSubtask(subtask);
////        Assertions.assertEquals(testEpic.getSubtasksList(), manager.getEpicById(2).getSubtasksList());
//        //
//    }
//
//    @Test
//    void updateSubtask() {
//        subtaskExpectedList.add(0, new SubTask(3, "Новая задача", "Новое Описание", Status.NEW));
//        SubTask updatesubTask = new SubTask(3, "Новая задача", "Новое Описание", Status.NEW);
//        manager.updateSubtask(updatesubTask);
//        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
//    }
//
//    @Test
//    void removeSubtaskById() {
//        SubTask subTask = new SubTask(6, "Задача", "Описание", Status.NEW);
//        SubTask testSubTask1 = new SubTask(6, "Задача", "Описание", Status.NEW);
//        manager.createSubtask(subTask, manager.getEpicById(2).getId());
//        subtaskExpectedList.add(testSubTask1);
//        subtaskExpectedList.remove(testSubTask);
//        manager.removeSubtaskById(3);
//        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
//    }
//
//    @Test
//    void getAllTheEpicSubtasks() {
//        SubTask expectedSubTask = new SubTask(3, "Описание", "Подзадача", Status.NEW);
//        List<SubTask> expected = new ArrayList<>();
//        expected.add(expectedSubTask);
//        List<SubTask> actual = manager.getEpicById(2).getSubtasksList();
//        Assertions.assertEquals(expected, actual);
//    }
//
//    @Test
//    void deleteAllTasks() {
//        taskExpectedList.clear();
//        epicExpectedList.clear();
//        subtaskExpectedList.clear();
//        manager.deleteAllTasks();
//        Assertions.assertEquals(taskExpectedList, manager.getTasks());
//        Assertions.assertEquals(epicExpectedList, manager.getEpics());
//        Assertions.assertEquals(subtaskExpectedList, manager.getSubtasks());
//    }
}
