package task;

public class SubTask extends Task {
    private int epicId;

    public SubTask(int id, String name, String description, Status status) {
        super(id, name , description, status);

    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }
}
