package task;

import manager.Manager;
import manager.ManagerTest;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InMemoryManagerTest extends ManagerTest<InMemoryManager> {
    public InMemoryManagerTest() {
        manager = new InMemoryManager();
    }
}