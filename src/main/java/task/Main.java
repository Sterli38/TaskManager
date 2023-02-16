package task;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        InMemoryManager manager = new InMemoryManager();
//        manager.taskCreation(new Task("Поездка в Августе", "Едем в грузию на две недели", 3, Status.NEW));
//        manager.taskCreation(new Task("Поездка в Октябре", "Едем в Тайланд на три недели по работе", 5, Status.NEW));
//        System.out.println(manager.getTasks());
//        System.out.println("======");
//        System.out.println(manager.getTask(2));
//        System.out.println("======");
//        System.out.println(manager.getTask(3));
//        System.out.println("======");
//        manager.removeTask(3);
//        System.out.println(manager.getTasks());
//        System.out.println("======");
//        manager.removeAllTasks();
//        System.out.println(manager.getTasks());
//        System.out.println("======");

        SubTask subTask = new SubTask(1, "В грузию" , "Поездка", Status.DONE);
        SubTask subTask1 = new SubTask(2, "В Германию" , "Поездка", Status.NEW);
        SubTask subTask2 = new SubTask(3, "В Армению" , "Поездка", Status.DONE);

        Epic epic = new Epic(1, "На Февраль" , "Планы");
        epic.addSubtask(subTask);
        epic.addSubtask(subTask1);
        epic.addSubtask(subTask2);

        System.out.println(epic.getStatus());
        List<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        System.out.println(list);


    }
}
