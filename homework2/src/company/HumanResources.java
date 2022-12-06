package company;

import exceptions.NoWorkersAvailableException;
import people.Worker;

import java.util.HashMap;
import java.util.HashSet;

public final class HumanResources {
    private final HashMap<Class<? extends Worker>, Integer> baseSalaries = new HashMap<>();
    private final HashMap<Class<? extends Worker>, HashSet<Worker>> workers = new HashMap<>();

    public void addWorker(Worker worker) {
        Class<? extends Worker> workerClass = worker.getClass();
        HashSet<Worker> workersSet = workers.getOrDefault(workerClass, new HashSet<>());
        workersSet.add(worker);
        workers.put(workerClass, workersSet);
    }

    public void removeWorker(Worker worker) {
        Class<? extends Worker> workerClass = worker.getClass();
        HashSet<Worker> workersSet = workers.getOrDefault(workerClass, new HashSet<>());
        workersSet.remove(worker);
        workers.put(workerClass, workersSet);
    }

    public Worker getWorker(Class<? extends Worker> workerClass) throws NoWorkersAvailableException {
        if (workers.get(workerClass).isEmpty()) {
            throw new NoWorkersAvailableException("No workers available.");
        } else {
            Worker worker = workers.get(workerClass).iterator().next();
            workers.get(workerClass).remove(worker);
            return worker;
        }
    }

    public int getSalary(Worker worker) {
        if (worker.getSalary() == 0)
            return baseSalaries.get(worker.getClass());
        else
            return worker.getSalary();
    }

    public void setBaseSalary(Class<? extends Worker> workerClass, int salary) {
        baseSalaries.put(workerClass, salary);
    }
}
