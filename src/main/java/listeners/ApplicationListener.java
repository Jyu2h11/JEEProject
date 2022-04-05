package listeners;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

    private static EntityManagerFactory emf;

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public void setEmf(EntityManagerFactory emf) {
        this.emf = emf;
    }

    public void contextInitialized(ServletContextEvent sce) {
        this.emf = Persistence.createEntityManagerFactory("default");
    }
    public void contextDestroyed(ServletContextEvent sce) {
        this.emf.close();
    }

}
