package beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
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
	
	public String getItemEmployeeByLogin() throws IOException {					
		employeesBean.setItemEmployeeByLogin(employeesBean.getUser());
		
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		
		if (employeesBean.getItemEmployee().getLogin() == null) {
			
			externalContext.redirect("accessDenied.xhtml");
		}
		else {
			if (employeesBean.getItemEmployee().getAdmin()) {
				externalContext.redirect("vacations.xhtml");
			}
		}
		
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
