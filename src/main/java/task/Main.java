package task;

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

        SubTask subTask = new SubTask("Поездка" , "В грузию" , 1 , Status.DONE);
        SubTask subTask1 = new SubTask("Поездка" , "В Германию" , 2 , Status.NEW);
        SubTask subTask2 = new SubTask("Поездка" , "В Армению" , 3 , Status.DONE);

        Epic epic = new Epic("Планы", "На Февраль" , 1);
        epic.addSubtask(subTask);
        epic.addSubtask(subTask1);
        epic.addSubtask(subTask2);

        System.out.println(epic.getStatus());

    }
}
