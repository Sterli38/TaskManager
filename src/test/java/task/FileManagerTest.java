package task;

import manager.ManagerTest;

public class FileManagerTest extends ManagerTest<FileManager> {
    public FileManagerTest() {
        manager = new FileManager("src/test/resources/");
    }
}
