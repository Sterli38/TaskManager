package task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Manager {
    private Map<Integer, Task> tasks = new HashMap<>();
    private Map<Integer, Epic> epics = new HashMap<>();
    private Map<Integer, SubTask> subTasks = new HashMap<>();

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Task getTask(int id) {
        Task task = null;
        for (Integer key : tasks.keySet()) {
            if (id == key) {
                task = tasks.get(key);
            }
        }
        if (task == null) {
            System.out.println("Задачи с таким id нет!");
        }

//        Task task = tasks.values().stream()
//            .filter(i -> i.getId() == id)
//            .findFirst()
//            .orElse(null);
//        if(task == null) {
//        System.out.println("Задачи с таким id нет");
//    }
        return task;
    }

    public void taskCreation(Task task) {
        tasks.put(task.getId(),task);
    }

//    public void updateTask(Task task) {
//
//    }

    public void removeTask(int id) {
        boolean flag = false;
        int id1 = 0;
        for (Integer key : tasks.keySet()) {
            if (key == id) {
                flag = true;
                id1 = id;
            }
        }
        if (flag) {
            tasks.remove(id1);
            System.out.println("Удалено");
        } else {
            System.out.println("Задачи с таким id не найдено, удаление не было выполнено");
        }
//        Task task = tasks.values().stream()
//                .filter(i -> i.getId() == id)
//                .findFirst()
//                .orElse(null);
//        if(task == null) {
//            System.out.println("Задачи с таким id не найдено, удаление не было выполнено");
//        } else {
//            tasks.remove(task.getId(), task);
//            System.out.println("Задача:\n" + task + "\nУдалена");
//        }
    }

    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    public void removeAllEpics() {
        epics.clear();
    }

    public Epic getEpic(int id) {
        Epic epic = null;
        for (Integer key : epics.keySet()) {
            if (id == key) {
                epic = epics.get(key);
            }
        }
        if (epic == null) {
            System.out.println("Задачи с таким id нет!");
        }

//        Epic epic = epics.values().stream()
//            .filter(i -> i.getId() == id)
//            .findFirst()
//            .orElse(null);
//        if(epic == null) {
//        System.out.println("Эпика с таким id нет");
//    }
        return epic;
    }

    public void epicCreation(Epic epic) {
        epics.put(epic.getId(), epic);
    }

//    public void updateEpic(Epic epic) {
//
//    }

    public void removeEpic(int id) {
        boolean flag = false;
        int id1 = 0;
        for (Integer key : epics.keySet()) {
            if (key == id) {
                flag = true;
                id1 = id;
            }
        }
        if (flag) {
            epics.remove(id1);
            System.out.println("Удалено");
        } else {
            System.out.println("Эпика с таким id не найдено, удаление не было выполнено");
        }
//        Epic epic = epics.values().stream()
//                .filter(i -> i.getId() == id)
//                .findFirst()
//                .orElse(null);
//        if(epic == null) {
//            System.out.println("Эпик с таким id не найдено, удаление не было выполнено");
//        } else {
//            System.out.println("Эпик:\n" + epic + "\nУдалена");
//            epics.remove(epic.getId(), epic);
//        }
    }

    public Map<Integer, SubTask> getSubtasks() {
        return subTasks;
    }

    public void removeAllSubtasks() {
        subTasks.clear();
    }

    public SubTask getSubtask(int id) {
        SubTask subTask = subTasks.values().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
        if(subTask == null) {
            System.out.println("Подзадачи с таким id нет");
        }
            return subTask;
    }

    public void subTaskCreation(SubTask subtask) {
        subTasks.put(subtask.getId(), subtask);
    }

//    public void updateSubtask(Subtask subtask) {
//
//    }

    public void removeSubtask(int id) {
//        boolean flag = false;
//        int id1 = 0;
//        for (Integer key : subTasks.keySet()) {
//            if (key == id) {
//                flag = true;
//                id1 = id;
//            }
//        }
//        if (flag) {
//            subTasks.remove(id1);
//            System.out.println("Удалено");
//        } else {
//            System.out.println("Подзадачи с таким id не найдено, удаление не было выполнено");
//        }

        SubTask subTask = subTasks.values().stream()
                .filter(i -> i.getId() == id)
                .findFirst()
                .orElse(null);
        if(subTask == null) {
            System.out.println("Подзадачи с таким id не найдено, удаление не было выполнено");
        } else {
            subTasks.remove(subTask.getId(), subTask);
            System.out.println("Подзадача:\n" + subTask + "\nУдалена");
        }
    }

    public List<SubTask> getAllTheEpicSubtasks(Epic epic) {
        return epic.getSubtasksList();
    }

    public void deleteAllTasks() {
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }
}
