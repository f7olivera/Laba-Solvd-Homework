package com.solvd.itcompany;

import com.solvd.itcompany.applications.AppDetails;
import com.solvd.itcompany.applications.DesktopApp;
import com.solvd.itcompany.applications.MobileApp;
import com.solvd.itcompany.applications.Website;
import com.solvd.itcompany.company.ITCompany;
import com.solvd.itcompany.enums.CompanyType;
import com.solvd.itcompany.people.Developer;
import com.solvd.itcompany.people.ProductOwner;
import com.solvd.itcompany.people.ScrumMaster;
import com.solvd.itcompany.people.Worker;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Consumer;

public final class Initializer {
    private final static Logger LOGGER = LogManager.getLogger(Initializer.class);

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
        Consumer<Worker> consumer = (worker) -> worker.setSalary(worker.getSalary() - 500);
        itCompany.getHumanResources().processWorkers(Developer.class, consumer);

        itCompany.hireWorker(new ProductOwner(15));
        itCompany.hireWorker(new ProductOwner(16));
        itCompany.hireWorker(new ProductOwner(17));
        itCompany.hireWorker(new ScrumMaster(18));
        itCompany.hireWorker(new ScrumMaster(19));
        itCompany.hireWorker(new ScrumMaster(20));

        return itCompany;
    }
}
