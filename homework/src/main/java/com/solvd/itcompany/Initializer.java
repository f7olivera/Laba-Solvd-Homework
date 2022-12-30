package com.solvd.itcompany;

import com.solvd.itcompany.applications.*;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.company.Quotation;
import com.solvd.itcompany.enums.CompanyType;
import com.solvd.itcompany.exceptions.NoWorkersAvailableException;
import com.solvd.itcompany.people.Developer;
import com.solvd.itcompany.people.ProductOwner;
import com.solvd.itcompany.people.ScrumMaster;
import com.solvd.itcompany.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public final class Initializer {
    private final static Logger LOGGER = LogManager.getLogger(Main.class);

    /**
     * Create an instance of ITCompany
     */
    public static ITCompany initITCompany() {
        ITCompany itCompany = new ITCompany("Solvd", CompanyType.CORPORATION);

        // Define base salaries for every worker
        itCompany.setBaseSalary(Developer.class, 1000);
        itCompany.setBaseSalary(ProductOwner.class, 2500);
        itCompany.setBaseSalary(ScrumMaster.class, 1000);

        // Define app details
        itCompany.addApp(new Website(new AppDetails("name, domain and expected number of users", 10000, 0.25F, 4)));
        itCompany.addApp(new MobileApp(new AppDetails("name, operating systems to be supported and expected number of users", 7500, 0.5F, 2)));
        itCompany.addApp(new DesktopApp(new AppDetails("name and platforms to be supported", 5000)));

        // Hire workers
        itCompany.hireWorker(new Developer(1, 5000, "frontend"));
        itCompany.hireWorker(new Developer(2, 5000, "frontend"));
        itCompany.hireWorker(new Developer(3, "frontend"));
        itCompany.hireWorker(new Developer(4, "frontend"));
        itCompany.hireWorker(new Developer(5, "frontend"));
        itCompany.hireWorker(new Developer(6, 5000, "backend"));
        itCompany.hireWorker(new Developer(7, 5000, "backend"));
        itCompany.hireWorker(new Developer(8, "backend"));
        itCompany.hireWorker(new Developer(9, "backend"));
        itCompany.hireWorker(new Developer(10, 5000, "backend"));
        itCompany.hireWorker(new Developer(11, 5000, "full-stack"));
        itCompany.hireWorker(new Developer(12, "full-stack"));
        itCompany.hireWorker(new Developer(13, "full-stack"));
        itCompany.hireWorker(new Developer(14, "full-stack"));

        LOGGER.info("Reducing developers salaries by $500.");
        LOGGER.info(itCompany.getHumanResources().getWorkerWithPredicate((worker) -> worker.getClass() == Developer.class));
        Consumer<Worker> consumer = (worker) -> worker.setSalary(worker.getSalary() - 500);
        itCompany.getHumanResources().processWorkers(Developer.class, consumer);
        LOGGER.info(itCompany.getHumanResources().getWorkerWithPredicate((worker) -> worker.getClass() == Developer.class));

        itCompany.hireWorker(new ProductOwner(15));
        itCompany.hireWorker(new ProductOwner(16));
        itCompany.hireWorker(new ProductOwner(17));
        itCompany.hireWorker(new ScrumMaster(18));
        itCompany.hireWorker(new ScrumMaster(19));
        itCompany.hireWorker(new ScrumMaster(20));

        return itCompany;
    }

    /**
     * Print requirements and quotation for an Application
     */
    public static void logExample(ITCompany itCompany, Application app) throws NoWorkersAvailableException {
        LOGGER.info(String.format("Logging %s example.", app.getClass().getSimpleName()));
        String appRequirements = itCompany.getRequirements(app);
        LOGGER.info(String.format("Requirements to build a %s: ", app.getClass().getSimpleName()) + appRequirements + ".");

        Quotation appQuotation = itCompany.getQuotation(app);
        LOGGER.info("Quotation for this project:\n" + appQuotation);
    }
}
