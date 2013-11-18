package hib;

import java.util.List;

import model.Vacation;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ManageAccruals {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private Session session;
	
	public ManageAccruals() {
		session = configureSessionFactory().openSession();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
	    Configuration configuration = new Configuration();
	    configuration.configure();
	    serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();        
	    sessionFactory = configuration.buildSessionFactory(serviceRegistry);
	    return sessionFactory;
	}
	
	@SuppressWarnings("unchecked")
	public List<Vacation> getVacList() {				
		@SuppressWarnings("rawtypes")
		List result = session.createQuery( "from Vacation order" ).list();
		return result;
	}
	
}
