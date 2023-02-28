package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void addSubtaskTest() {
        Epic epic = new Epic(3, "Поездка" , "Главная задача");
        SubTask subTask = new SubTask(4, "В Германию" , "Подзадача" , Status.NEW);
        epic.addSubtask(subTask);
        Assertions.assertTrue(epic.getSubtasksList().contains(subTask));
        Assertions.assertEquals(epic.getId(), subTask.getEpicId());
    }

    @Test
    void getStatusInProgressTest() {
        Epic epic = new Epic(3, "Поездка" , "Главная задача");
        SubTask subTask = new SubTask(4, "В Германию" , "Подзадача", Status.NEW);
        SubTask subTask2 = new SubTask(2, "В Армению" , "Подзадача", Status.NEW);
        SubTask subTask3 = new SubTask(2, "В Грузию" , "Подзадача", Status.DONE);
        epic.addSubtask(subTask);
        epic.addSubtask(subTask2);
        epic.addSubtask(subTask3);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus());
    }

    @Test
    void getStatusDoneTest() {
        Epic epic = new Epic(3 , "Поездка" , "Главная задача");
        SubTask subTask4 = new SubTask(4, "В Германию" , "Подзадача", Status.DONE);
        SubTask subTask5 = new SubTask(2, "В Армению" , "Подзадача", Status.DONE);
        SubTask subTask6 = new SubTask(2, "В Грузию" , "Подзадача", Status.DONE);
        epic.addSubtask(subTask4);
        epic.addSubtask(subTask5);
        epic.addSubtask(subTask6);

        Assertions.assertEquals(Status.DONE, epic.getStatus());
    }

    @Test
    void getStatusNewTest() {
        Epic epic = new Epic(3 , "Поездка" , "Главная задача");
        SubTask subTask4 = new SubTask(4, "В Германию" , "Подзадача", Status.NEW);
        SubTask subTask5 = new SubTask(2, "В Армению" , "Подзадача", Status.NEW);
        SubTask subTask6 = new SubTask(2, "В Грузию" , "Подзадача" , Status.NEW);
        epic.addSubtask(subTask4);
        epic.addSubtask(subTask5);
        epic.addSubtask(subTask6);

        Assertions.assertEquals(Status.NEW, epic.getStatus());
    }
}