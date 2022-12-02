package interfaces;

import people.Worker;

import java.util.HashSet;

public interface IEmploy {
    void hireWorker(Worker worker);

    void fireWorker(Worker worker);

    HashSet<Worker> getWorkers();
}
