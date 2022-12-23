package com.solvd.itcompany.company;

import com.solvd.itcompany.exceptions.NegativeAmountException;
import com.solvd.itcompany.exceptions.NoWorkersAvailableException;
import com.solvd.itcompany.people.Worker;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Predicate;

public final class HumanResources {
    private final HashMap<Class<? extends Worker>, Integer> baseSalaries = new HashMap<>();
    // Workers are stored in a SortedSet by their salary, in ascending order.
    private final HashMap<Class<? extends Worker>, TreeSet<Worker>> workers = new HashMap<>();

    public void addWorker(Worker worker) {
        if (worker.getSalary() == 0)
            worker.setSalary(baseSalaries.get(worker.getClass()));
        Class<? extends Worker> workerClass = worker.getClass();
        TreeSet<Worker> workersSet = workers.getOrDefault(workerClass, new TreeSet<>());
        workersSet.add(worker);
        workers.put(workerClass, workersSet);
    }

    public void removeWorker(Worker worker) {
        Class<? extends Worker> workerClass = worker.getClass();
        TreeSet<Worker> workersSet = workers.getOrDefault(workerClass, new TreeSet<>());
        workersSet.remove(worker);
        workers.put(workerClass, workersSet);
    }

    public Worker getWorker(Class<? extends Worker> workerClass) throws NoWorkersAvailableException {
        if (workers.get(workerClass).isEmpty()) {
            throw new NoWorkersAvailableException("No workers available.");
        } else {
            Worker worker = workers.get(workerClass).first();
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

    public void setBaseSalary(Class<? extends Worker> workerClass, int salary) throws NegativeAmountException {
        if (salary < 0)
            throw new NegativeAmountException("Salary must be a positive number.");
        else
            baseSalaries.put(workerClass, salary);
    }

    public Worker getWorkerWithPredicate(Predicate<Worker> tester) {
        for (Map.Entry<Class<? extends Worker>, TreeSet<Worker>> entry : workers.entrySet())
            for (Worker worker : entry.getValue())
                if (tester.test(worker))
                    return worker;
        return null;
    }
}
