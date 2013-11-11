package hib;

import java.util.List;

import model.Employee;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

public class ManageEmployee {
	private static SessionFactory sessionFactory;
	private static ServiceRegistry serviceRegistry;
	private Session session;
	
	public ManageEmployee() {
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
	public List<Employee> getEmplList() {				
		@SuppressWarnings("rawtypes")
		List result = session.createQuery( "from Employee" ).list();
		for ( Employee employee : (List<Employee>) result ) {
		    System.out.println(employee.getName() + " " + employee.getPosition());
		}	
		return result;
	}
	
	public void update(Employee employee) {
		Transaction tx = session.beginTransaction();			
        session.update(employee);   
        tx.commit();
	}
	public short addEmployee(Employee employee) {
		Short employeeID = null;
		Transaction tx = session.beginTransaction();		
	    employeeID = (Short) session.save(employee);
	    tx.commit();
	    
		return employeeID;
	}
	
	public void deleteEmployee(Employee employee) {
		Transaction tx = session.beginTransaction();
		session.delete(employee); 
	    tx.commit();
	}
	
    public Employee getById(short s) {
        return (Employee) session.get(Employee.class, s);
    }

}
