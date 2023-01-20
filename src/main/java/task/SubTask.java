package task;

public class SubTask extends Task {
    private Epic epic;

    public SubTask(String name, String description, int id, Status status) {
        super(name, description, id, status);
    }

    public Epic getEpic() {
        return epic;
    }

    public void setEpic(Epic epic) {
        this.epic = epic;
    }
}
