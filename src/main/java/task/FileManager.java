package task;

import manager.Manager;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileManager implements Manager {

    private final String path;

    public FileManager(String path) {
        this.path = path;
    }

    @Override
    public List<Task> getTasks() {
        List<Task> tasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                tasks.add(buildTask(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return tasks;
    }

    @Override
    public void removeAllTasks() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Task getTaskById(int id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Task task = buildTask(line);
                if (task.getId() == id) {
                    return task;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void createTask(Task task) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME, true))) {
            bufferedWriter.write(task.getId() + "," + task.getName() + "," + task.getDescription() + "," + task.getStatus());
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        } // Перезаписывает значение первого таска на новый таск
    }

    @Override
    public void updateTask(Task task) {
        if (task == null) {
            System.out.println("Task can not be updated, Task equals null");
            return;
        }
        Task taskForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                taskForUpdate = buildTask(line);
                if (task.getId() == taskForUpdate.getId()) {
                    taskForUpdate.setName(task.getName() == null ? taskForUpdate.getName() : task.getName());
                    taskForUpdate.setDescription(task.getDescription() == null ? taskForUpdate.getDescription() : task.getDescription());
                    taskForUpdate.setStatus(task.getStatus() == null ? taskForUpdate.getStatus() : task.getStatus());
                }
            }
            if (taskForUpdate == null) {
                System.out.println("Task can not be updated, no task with this id");
                return;
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME))) {
                bufferedWriter.write(taskForUpdate.getId() + "," + taskForUpdate.getName() + "," + taskForUpdate.getDescription() + "," + taskForUpdate.getStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeTaskById(int id) {
        Task task = getTaskById(id);
//        Task task;
//        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
//        String line;
//        while((line = bufferedReader.readLine()) != null) {
//            task = buildTask(line);
        if (task.getId() == id) {
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME))) {
                bufferedWriter.write("");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public List<Epic> getEpics() {
        List<Epic> list = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                list.add(buildEpic(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

    @Override
    public void removeAllEpics() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.EPIC_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Epic getEpicById(int id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                Epic epic = buildEpic(line);
                if (epic.getId() == id) {
                    return epic;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void createEpic(Epic epic) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.EPIC_NAME, true))) {
//            List<SubTask> subTasks = epic.getSubtasksList();
//            String stringSubtasks = "";
//            for(int i = 0; i < subTasks.size(); i++) {
//                if(i != subTasks.size() - 1) {
//                    stringSubtasks += subTasks.get(i).getId() + ",";
//                } else {
//                    stringSubtasks += subTasks.get(i).getId();
//                }
//            }
            String stringSubtasks = epic.getSubtasksList().stream()
                            .map(i -> String.valueOf(i.getId()))
                                    .collect(Collectors.joining(","));
            bufferedWriter.write(epic.getId() + "," + epic.getName() + "," + epic.getDescription() + "," + epic.getStatus() + ":" + stringSubtasks);
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateEpic(Epic epic) {
        if (epic == null) {
            System.out.println("Epic can not be updated, Epic equals null");
            return;
        }
        Task epicForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                epicForUpdate = buildTask(line);
                if (epic.getId() == epicForUpdate.getId()) {
                    epicForUpdate.setName(epic.getName() == null ? epicForUpdate.getName() : epic.getName());
                    epicForUpdate.setDescription(epic.getDescription() == null ? epicForUpdate.getDescription() : epic.getDescription());
                    epicForUpdate.setStatus(epic.getStatus() == null ? epicForUpdate.getStatus() : epic.getStatus());
                }
            }
            if (epicForUpdate == null) {
                System.out.println("Epic can not be updated, no Epic with this id");
                return;
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.EPIC_NAME))) {
                bufferedWriter.write(epicForUpdate.getId() + "," + epicForUpdate.getName() + "," + epicForUpdate.getDescription() + "," + epicForUpdate.getStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public void removeEpicById(int id) {
        Epic epic;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                epic = buildEpic(line);
                if (epic.getId() == id) {
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.EPIC_NAME))) {
                        bufferedWriter.write("");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubTask> getSubtasks() {
        List<SubTask> subTasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                subTasks.add(buildSubtask(line));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return subTasks;
    }

    @Override
    public void removeAllSubtasks() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.SUBTASK_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public SubTask getSubtaskById(int id) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                SubTask subtask = buildSubtask(line);
                if (subtask.getId() == id) {
                    return subtask;
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public void createSubtask(SubTask subtask, int epicId) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.SUBTASK_NAME, true))) {
            bufferedWriter.write(subtask.getId() + "," + subtask.getName() + "," + subtask.getDescription() + "," + subtask.getStatus());
            bufferedWriter.write(System.lineSeparator());
            Epic epic = getEpicById(epicId);
            if (epic != null) {
                epic.addSubtask(subtask);
            } else {
                throw new RuntimeException("Epic was not found");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateSubtask(SubTask subTask) {
        if (subTask == null) {
            System.out.println("SubTask can not be updated, SubTask equals null");
            return;
        }
        SubTask subTaskForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                subTaskForUpdate = buildSubtask(line);
                if (subTask.getId() == subTaskForUpdate.getId()) {
                    subTaskForUpdate.setName(subTask.getName() == null ? subTaskForUpdate.getName() : subTask.getName());
                    subTaskForUpdate.setDescription(subTask.getDescription() == null ? subTaskForUpdate.getDescription() : subTask.getDescription());
                    subTaskForUpdate.setStatus(subTask.getStatus() == null ? subTaskForUpdate.getStatus() : subTask.getStatus());
                }
            }
            if (subTaskForUpdate == null) {
                System.out.println("SubTask can not be updated, no SubTask with this id");
                return;
            }
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.SUBTASK_NAME))) {
                bufferedWriter.write(subTaskForUpdate.getId() + "," + subTaskForUpdate.getName() + "," + subTaskForUpdate.getDescription() + "," + subTaskForUpdate.getStatus());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void removeSubtaskById(int id) {
        Task subTask;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                subTask = buildTask(line);
                if (subTask.getId() == id) {
                    try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME))) {
                        bufferedWriter.write("");
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<SubTask> getAllTheEpicSubtasks(Epic epic) {
        return null;
    }

    @Override
    public void deleteAllTasks() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.TASK_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.SUBTASK_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + FileNames.EPIC_NAME))) {
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private Task buildTask(String line) {
        String[] elementsOfTasks = line.split(",");
        int id = Integer.parseInt(elementsOfTasks[0]);
        String name = elementsOfTasks[1];
        String description = elementsOfTasks[2];
        Status status = Status.valueOf(elementsOfTasks[3]);
        return new Task(id, name, description, status);
    }

    private Epic buildEpic(String line) {
        SubTask subtask = null;
        String[] elementsOfEpic = line.split(":");

            String epicLine = elementsOfEpic[0];
            String subtaskLine = elementsOfEpic[1];
            String[] epicsElements = epicLine.split(",");
            String[] subtaskElements = subtaskLine.split(",");


        int id = Integer.parseInt(epicsElements[0]);
        String name = epicsElements[1];
        String description = epicsElements[2];
        Epic epic = new Epic(id, name, description);

        if (subtaskElements.length != 0) {
            List<Long> subtasksId = Arrays.stream(subtaskElements)
                    .mapToLong(Long::parseLong).boxed()
                    .collect(Collectors.toList());
            readSubtasksById(subtasksId).forEach(epic::addSubtask);
        }
        return epic;
    }

    private SubTask buildSubtask(String line) {
        String[] elementsOfSubtasks = line.split(",");
        int id = Integer.parseInt(elementsOfSubtasks[0]);
        String name = elementsOfSubtasks[1];
        String description = elementsOfSubtasks[2];
        Status status = Status.valueOf(elementsOfSubtasks[3]);
        return new SubTask(id, name, description, status);
    }

    private List<SubTask> readSubtasksById(List<Long> subtasksId) {
        List<SubTask> subTasks = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            SubTask subtask;
            String subtaskLine;
            while ((subtaskLine = bufferedReader.readLine()) != null) {
                subtask = buildSubtask(subtaskLine);
                for (int j = 0; j < subtasksId.size(); j++) {
                    if (subtasksId.get(j) == subtask.getId()) {
                        subTasks.add(subtask);
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return subTasks;
    }
}
