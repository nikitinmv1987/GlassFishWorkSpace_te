package energy.ces.vacations2.adm;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import model.Vacation;

@Named
@SessionScoped
public class VacationsBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Resource(name="vacRes")
	private DataSource ds;

	@Inject
	private EmployeesBean employeesBean;
	
	public String getItemEmployeeByLogin() {			
		employeesBean.setItemEmployeeByLogin("nikitinmaksi");
		
		return employeesBean.getItemEmployee().getName();
	}
	
	public List<Vacation> getVacationList() {	
		return employeesBean.getItemEmployee().getVacations();					 
	}
	
	public EmployeesBean getEmployeesBean() {
		return employeesBean;
	}

	public void setEmployeesBean(EmployeesBean employeesBean) {
		this.employeesBean = employeesBean;
	}
	
}
