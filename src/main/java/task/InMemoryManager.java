package task;

import manager.Manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryManager implements Manager {
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, SubTask> subTasks = new HashMap<>();

    public List<Task> getTasks() {
       return new ArrayList<>(tasks.values());
    }

    public void removeAllTasks() {
        tasks.clear();
    }

    public Task getTaskById(int id) {
        return tasks.get(id);
    }

    public void createTask(Task task) {
        tasks.put(task.getId(), task);
    }

    public void updateTask(Task task) {
        if(task == null) {
            System.out.println("Task can not be updated, Task equals null");
            return;
        }
        Task taskForUpdate = tasks.get(task.getId());
        if(taskForUpdate == null) {
            System.out.println("Task can not be updated, no task with this id");
            return;
        }
        taskForUpdate.setName(task.getName() == null ? taskForUpdate.getName() : task.getName());
        taskForUpdate.setDescription(task.getDescription() == null ? taskForUpdate.getDescription() : task.getDescription());
        taskForUpdate.setStatus(task.getStatus() == null ? taskForUpdate.getStatus() : task.getStatus());
    }

    public void removeTaskById(int id) {
        tasks.remove(id);
    }

    public List<Epic> getAllEpics() {
        return new ArrayList<>(epics.values());
    }

    public void removeAllEpics() {
        epics.clear();
    }

    public Epic getEpic(int id) {
        return epics.get(id);
    }

    public void createEpic(Epic epic) {
        epics.put(epic.getId(), epic);
    }

    public void updateEpic(Epic epic) {
        if(epic == null) {
            System.out.println("Epic can not be updated, Epic equals null");
            return;
        }
        Epic epicForUpdate = epics.get(epic.getId());
        if(epicForUpdate == null) {
            System.out.println("Epic can not be updated, no Epic with this id");
            return;
        }
        epicForUpdate.setName(epic.getName() == null ? epicForUpdate.getName() : epic.getName());
        epicForUpdate.setDescription(epic.getDescription() == null ? epicForUpdate.getDescription() : epic.getDescription());
        epicForUpdate.setStatus(epic.getStatus() == null ? epicForUpdate.getStatus() : epic.getStatus());
    }

    public void removeEpicById(int id) {
        epics.remove(id);
    }

    public List<SubTask> getSubtasks() {
        return new ArrayList<>(subTasks.values());
    }

    public void removeAllSubtasks() {
        subTasks.clear();
    }

    public SubTask getSubtaskById(int id) {
        return subTasks.get(id);
    }

    public void createSubtask(SubTask subtask, int epicId) {
        Epic epic = epics.get(epicId);
        if(epic != null) {
            subTasks.put(subtask.getId(), subtask);
            epic.addSubtask(subtask);
        } else {
            throw new RuntimeException("Epic was not found");
        }
    }

    public void updateSubtask(SubTask subtask) {
        if(subtask == null) {
            System.out.println("Epic can not be updated, Epic equals null");
            return;
        }
        SubTask subtaskForUpdate = subTasks.get(subtask.getId());
        if(subtaskForUpdate == null) {
            System.out.println("Epic can not be updated, no Epic with this id");
            return;
        }
        subtaskForUpdate.setName(subtask.getName() == null ? subtaskForUpdate.getName() : subtask.getName());
        subtaskForUpdate.setDescription(subtask.getDescription() == null ? subtaskForUpdate.getDescription() : subtask.getDescription());
        subtaskForUpdate.setStatus(subtask.getStatus() == null ? subtaskForUpdate.getStatus() : subtask.getStatus());
    }

    public void removeSubtaskById(int id) {
        subTasks.remove(id);
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
