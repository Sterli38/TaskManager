package manager;

import manager.FileNames;
import manager.Manager;
import task.Epic;
import task.Status;
import task.SubTask;
import task.Task;

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
        }
    }

    @Override
    public void updateTask(Task task) {
        if (task == null) {
            System.out.println("Task can not be updated, task equals null");
            return;
        }
        Task taskForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Task.Buffer"))) {
                while ((line = bufferedReader.readLine()) != null) {
                    taskForUpdate = buildTask(line);
                    if (task.getId() == taskForUpdate.getId()) {
                        taskForUpdate.setName(task.getName() == null ? taskForUpdate.getName() : task.getName());
                        taskForUpdate.setDescription(task.getDescription() == null ? taskForUpdate.getDescription() : task.getDescription());
                        taskForUpdate.setStatus(task.getStatus() == null ? taskForUpdate.getStatus() : task.getStatus());
                    }
                    bufferedWriter.write(taskForUpdate.getId() + "," + taskForUpdate.getName() + "," + taskForUpdate.getDescription() + "," + taskForUpdate.getStatus());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File(path + FileNames.TASK_NAME);
        File bufferFile = new File(path + "Task.buffer");
        file.delete();
        bufferFile.renameTo(file);
    }

    @Override
    public void removeTaskById(int id) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.TASK_NAME))) {
            String line;
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Task.buffer"))) {
                    while ((line = bufferedReader.readLine()) != null) {
                        Task task = buildTask(line);
                        if(task.getId() == id) {
                            continue;
                        } else {
                            bufferedWriter.write(task.getId() + "," + task.getName() + "," + task.getDescription()  + "," + task.getStatus());
                            bufferedWriter.newLine();
                        }
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File sourceFile = new File(path + FileNames.TASK_NAME);
        File bufferFile = new File(path + "Task.buffer");
        sourceFile.delete();
        bufferFile.renameTo(sourceFile);
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
        Epic epicForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Epics.buffer"))) {
                while ((line = bufferedReader.readLine()) != null) {
                    epicForUpdate = buildEpic(line);
                    if (epic.getId() == epicForUpdate.getId()) {
                        epicForUpdate.setName(epic.getName() == null ? epicForUpdate.getName() : epic.getName());
                        epicForUpdate.setDescription(epic.getDescription() == null ? epicForUpdate.getDescription() : epic.getDescription());
                    }
                    String stringSubtasks = epicForUpdate.getSubtasksList().stream()
                            .map(i -> String.valueOf(i.getId()))
                            .collect(Collectors.joining(","));
                    bufferedWriter.write(epicForUpdate.getId() + "," + epicForUpdate.getName() + "," + epicForUpdate.getDescription() + ":" + stringSubtasks);
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File(path + FileNames.EPIC_NAME);
        File bufferFile = new File(path + "Epics.buffer");
        file.delete();
        bufferFile.renameTo(file);
    }

    @Override
    public void removeEpicById(int id) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
            String line;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Epic.buffer"))) {
                while ((line = bufferedReader.readLine()) != null) {
                    Epic epic = buildEpic(line);
                    if(epic.getId() == id) {
                        continue;
                    } else {
                        String stringSubtasks = epic.getSubtasksList().stream()
                                .map(i -> String.valueOf(i.getId()))
                                .collect(Collectors.joining(","));
                        bufferedWriter.write(epic.getId() + "," + epic.getName() + "," + epic.getDescription() + "," + epic.getStatus() + ":" + stringSubtasks);
                        bufferedWriter.write(System.lineSeparator());
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File souceFile = new File(path + FileNames.EPIC_NAME);
        File bufferFile = new File(path + "Epic.buffer");
        souceFile.delete();
        bufferFile.renameTo(souceFile);;
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

    public void addSubtask(int epicId, SubTask subTask) {
        createSubtask(subTask, epicId);
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.EPIC_NAME))) {
                try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Epic.buffer"))) {
                    String line;
                    Epic currentEpic;
                    while ((line = bufferedReader.readLine()) != null) {
                        currentEpic = buildEpic(line);
                        if (currentEpic.getId() == epicId) {
                            currentEpic.addSubtask(subTask);
                        }
                        String stringSubtasks = currentEpic.getSubtasksList().stream()
                                .map(i -> String.valueOf(i.getId()))
                                .collect(Collectors.joining(","));
                        bufferedWriter.write(currentEpic.getId() + "," + currentEpic.getName() + "," + currentEpic.getDescription() + ":" + stringSubtasks);
                        bufferedWriter.newLine();
                    }
                }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File(path + FileNames.EPIC_NAME);
        File bufferFile = new File(path + "Epic.buffer");
        file.delete();
        bufferFile.renameTo(file);
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
            bufferedWriter.write(subtask.getId() + "," + subtask.getName() + "," + subtask.getDescription() + "," + subtask.getStatus() + "," + epicId);
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
        SubTask subtaskForUpdate = null;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            String line;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Subtask.Buffer"))) {
                while ((line = bufferedReader.readLine()) != null) {
                    subtaskForUpdate = buildSubtask(line);
                    if (subTask.getId() == subtaskForUpdate.getId()) {
                        subtaskForUpdate.setName(subTask.getName() == null ? subtaskForUpdate.getName() : subTask.getName());
                        subtaskForUpdate.setDescription(subTask.getDescription() == null ? subtaskForUpdate.getDescription() : subTask.getDescription());
                        subtaskForUpdate.setStatus(subTask.getStatus() == null ? subtaskForUpdate.getStatus() : subTask.getStatus());
                    }
                    bufferedWriter.write(subtaskForUpdate.getId() + "," + subtaskForUpdate.getName() + "," + subtaskForUpdate.getDescription() + "," + subtaskForUpdate.getStatus() + "," + subtaskForUpdate.getEpicId());
                    bufferedWriter.newLine();
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File file = new File(path + FileNames.SUBTASK_NAME);
        File bufferFile = new File(path + "Subtask.buffer");
        file.delete();
        bufferFile.renameTo(file);
    }

    @Override
    public void removeSubtaskById(int id) {
        try(BufferedReader bufferedReader = new BufferedReader(new FileReader(path + FileNames.SUBTASK_NAME))) {
            String line;
            try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(path + "Subtask.buffer"))) {
                while ((line = bufferedReader.readLine()) != null) {
                    SubTask subTask = buildSubtask(line);
                    if(subTask.getId() == id) {
                        continue;
                    } else {
                        bufferedWriter.write(subTask.getId() + "," + subTask.getName() + "," + subTask.getDescription()  + "," + subTask.getStatus() + "," + subTask.getEpicId());
                        bufferedWriter.newLine();
                    }
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        File souceFile = new File(path + FileNames.SUBTASK_NAME);
        File bufferFile = new File(path + "Subtask.buffer");
        souceFile.delete();
        bufferFile.renameTo(souceFile);
    }

    @Override
    public List<SubTask> getAllTheEpicSubtasks(Epic epic) {
        return epic.getSubtasksList();
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
        String[] epicsElements = epicLine.split(",");
        int id = Integer.parseInt(epicsElements[0]);
        String name = epicsElements[1];
        String description = epicsElements[2];
        Epic epic = new Epic(id, name, description);
        if (elementsOfEpic.length > 1) {
            String subtaskLine = elementsOfEpic[1];
            String[] subtaskElements = subtaskLine.split(",");

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
        int epicId = Integer.parseInt(elementsOfSubtasks[4]);
        SubTask subtask = new SubTask(id, name, description, status);
        subtask.setEpicId(epicId);
        return subtask;
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
