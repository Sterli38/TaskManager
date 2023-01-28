package task;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    @Test
    void addSubtask() {
        Epic epic = new Epic("Главная задача" , "Поездка" , 3);
        SubTask subTask = new SubTask("Подзадача" , "В Германию" , 4 , Status.NEW);
        epic.addSubtask(subTask);
        Assertions.assertTrue(epic.getSubtasksList().contains(subTask));
        Assertions.assertEquals(epic.getId(), subTask.getEpicId());
    }

    @Test
    void getStatusInProgress() {
        Epic epic = new Epic("Главная задача" , "Поездка" , 3);
        SubTask subTask = new SubTask("Подзадача" , "В Германию" , 4 , Status.NEW);
        SubTask subTask2 = new SubTask("Подзадача" , "В Армению" , 2 , Status.NEW);
        SubTask subTask3 = new SubTask("Подзадача" , "В Грузию" , 2 , Status.DONE);
        epic.addSubtask(subTask);
        epic.addSubtask(subTask2);
        epic.addSubtask(subTask3);

        Assertions.assertEquals(Status.IN_PROGRESS, epic.getStatus());
    }

    @Test
    void getStatusDone() {
        Epic epic = new Epic("Главная задача" , "Поездка" , 3);
        SubTask subTask4 = new SubTask("Подзадача" , "В Германию" , 4 , Status.DONE);
        SubTask subTask5 = new SubTask("Подзадача" , "В Армению" , 2 , Status.DONE);
        SubTask subTask6 = new SubTask("Подзадача" , "В Грузию" , 2 , Status.DONE);
        epic.addSubtask(subTask4);
        epic.addSubtask(subTask5);
        epic.addSubtask(subTask6);

        Assertions.assertEquals(Status.DONE, epic.getStatus());
    }

    @Test
    void getStatusNew() {
        Epic epic = new Epic("Главная задача" , "Поездка" , 3);
        SubTask subTask4 = new SubTask("Подзадача" , "В Германию" , 4 , Status.NEW);
        SubTask subTask5 = new SubTask("Подзадача" , "В Армению" , 2 , Status.NEW);
        SubTask subTask6 = new SubTask("Подзадача" , "В Грузию" , 2 , Status.NEW);
        epic.addSubtask(subTask4);
        epic.addSubtask(subTask5);
        epic.addSubtask(subTask6);

        Assertions.assertEquals(Status.NEW, epic.getStatus());
    }
}