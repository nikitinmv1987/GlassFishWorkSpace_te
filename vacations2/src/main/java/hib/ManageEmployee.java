package hib;

import java.util.List;

import model.Employee;
import model.Vacation;

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
		List result = session.createQuery("select E from Employee E order by E.name").list();		
		return result;  
	}
	
	public void update(Employee employee) {
		Transaction tx = session.beginTransaction();			
        session.update(employee);   
        tx.commit();
	}
	
	public int addEmployee(Employee employee) {
		Transaction tx = session.beginTransaction();		
	    int employeeID = (Integer)session.save(employee);
	    tx.commit();
	    
		return employeeID;
	}
	
	public void deleteEmployee(Employee employee) {
		Transaction tx = session.beginTransaction();
		session.delete(employee); 
	    tx.commit();
	}
	
    public Employee getById(int s) {
        return (Employee) session.get(Employee.class, s);
    }
    
/*	@SuppressWarnings("unchecked")
	public List<Vacation> getVacList(int idEmployee) {				
		Query query = session.createQuery( "from Vacation where idEmloyee = :id order by date desc" );
		query.setParameter("id", idEmployee);
		query.setFirstResult(0);
		query.setMaxResults(20);
		@SuppressWarnings("rawtypes")
		List result = query.list();
		
		return result;
	}
*/
	
	public int addVacation(Vacation vacation) {
		Transaction tx = session.beginTransaction();		
		int vacationID = (Integer) session.save(vacation);
	    tx.commit();
	    
		return vacationID;
	}
	
	public void delVacation(Vacation vacation) {
		Transaction tx = session.beginTransaction();		
		session.delete(vacation);
	    tx.commit();	   
	}
        
}
