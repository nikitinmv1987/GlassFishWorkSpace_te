package energy.ces.vacations2.adm;

import hib.ManageEmployee;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.sun.jna.platform.win32.Secur32;
import com.sun.jna.platform.win32.Secur32Util;

import java.io.IOException;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import model.Employee;
import model.Vacation;

@Named
@SessionScoped
public class EmployeesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Resource(name="vacRes")
	private DataSource ds;
	private List<Employee> employeesList;	
	private HtmlDataTable dataTableEmployees;
	private Employee itemEmployee = new Employee();
	private String actionDesc;
	private int selectedItem;
	private Employee currentEmpl;
	
	private int rowIndex;
	
	private ManageEmployee ME;
	
	public EmployeesBean() {
		ME = new ManageEmployee();
	}
	
	public ManageEmployee getME() {
		return ME;
	}

	public void setME(ManageEmployee mE) {
		ME = mE;
	}

	public void setItemEmployeeByLogin(String login) throws IOException {		
		for (Employee empl : getEmployeesList()) {
			if (empl.getLogin().equalsIgnoreCase(login)) {
				itemEmployee = empl;
				setRowIndex(employeesList.indexOf(empl) + 1);	
				selectedItem = empl.getIdEmploees();
				currentEmpl = empl;
			}
		} 
	}
	
	public List<Employee> getEmployeesList() throws IOException { 
		getAcces();
		if (employeesList == null) { 
			updateEmplList();
		}
		return employeesList;			 						
	}
	
	public void setItemEmployee() {
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemEmployee = employeesList.get(this.rowIndex - 1);
			selectedItem = itemEmployee.getIdEmploees();
	        System.out.println(rowIndex);
	        System.out.println(itemEmployee.getName());
	    } 
	}
	
	public void setItemEmployeeDropDown() {		    	        
        for (Employee empl : employeesList) {
        	if (empl.getIdEmploees() == selectedItem) {
        		setRowIndex(employeesList.indexOf(empl) + 1);
        		itemEmployee = empl;
				System.out.println("index=" + employeesList.indexOf(empl));
			}
		} 
	}

	public String editEmployee() {
		String result ="editEmployee?faces-redirec=true"; 
		
		if (rowIndex > 0) {
			actionDesc = "Редагування співробітника";
	    } else {
	    	
	    	System.out.println(actionDesc);
	    	result = "";
	    }
						
		return result;
	}
	
	public String addEmployee() {
		actionDesc = "Додавання нового співробітника";		
		itemEmployee = new Employee();
		setRowIndex(0);
		
		return "editEmployee?faces-redirec=true";
	}

	public String removeEmployee() {
		System.out.println("preparing to delete");
		
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");	
		
		if (rowIndex != null && rowIndex.trim().length() != 0 && (Integer.parseInt(rowIndex) != 0)) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemEmployee = employeesList.get(this.rowIndex - 1);				        
			ME.deleteEmployee(itemEmployee);
			updateEmplList();
	    } else {
	    	System.out.println("удалено");
	    }
		
		setRowIndex(0);
		return "employees";
	}

	public void updateEmplList() {			
		employeesList = ME.getEmplList();
	}
	
	public String saveEmployee() {		
		if (!(itemEmployee.getIdEmploees() > 0))	{		
			int returnId = ME.addEmployee(itemEmployee);										
			updateEmplList();
			for (Employee empl : employeesList) {
				if (empl.getIdEmploees() == returnId ) {
					setRowIndex(employeesList.indexOf(empl) + 1);
					itemEmployee = empl;
					}
				}        
		}
		else {
	        ME.update(itemEmployee);
		}	
		
        return "employees"; 
    }
	
	public BigDecimal getPosSum() {
		BigDecimal result = BigDecimal.ZERO;
		for (Vacation vac: itemEmployee.getVacations()) {
			if (vac.getVolume().compareTo(BigDecimal.ZERO) > 0) {
				result = result.add(vac.getVolume());
			}			
		}
		return result;
	}
	
	public BigDecimal getNegSum() {
		BigDecimal result = BigDecimal.ZERO;
		for (Vacation vac: itemEmployee.getVacations()) {
			if (vac.getVolume().compareTo(BigDecimal.ZERO) < 0) {
				result = result.add(vac.getVolume());
			}			
		}
		return result;
	}
	
	public BigDecimal getSum() {
		BigDecimal result = BigDecimal.ZERO;
		for (Vacation vac: itemEmployee.getVacations()) {
			result = result.add(vac.getVolume());	
		}
		return result;
	}
	
	public void getAcces() throws IOException {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		ExternalContext externalContext = facesContext.getExternalContext();
		HttpServletRequest origRequest = (HttpServletRequest) externalContext.getRequest();
		
		System.out.println(origRequest.getRequestURI());
				
		
		switch (origRequest.getRequestURI()) {
		case "/vacations2/accruals.xhtml":
				if (!currentEmpl.getAdmin()) {
					externalContext.redirect("accessDenied.xhtml");
				}
			break;
			
		case "/vacations2/vacations.xhtml":			
			if (!currentEmpl.getAdmin()) {
				externalContext.redirect("accessDenied.xhtml");
			}
		break;
			
		case "/vacations2/employees.xhtml":			
			if (!currentEmpl.getAdmin()) {
				externalContext.redirect("accessDenied.xhtml");
			}
			break;
			
		default:
			break;
		}		
	}
	
	public String getUser() {
		String result = Secur32Util.getUserNameEx(Secur32.EXTENDED_NAME_FORMAT.NameSamCompatible);
		return result;
	}
	
	public Employee getItemEmployee() {
		return itemEmployee;
	}

	public String getActionDesc() {
		return actionDesc;
	}
	
	public HtmlDataTable getDataTableEmployees() {
		return dataTableEmployees;
	}

	public void setDataTableEmployees(HtmlDataTable dataTableEmployees) {
		this.dataTableEmployees = dataTableEmployees;
	}

	public int getRowIndex() {
		return rowIndex;		
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public int getSelectedItem() {
		return selectedItem;
	}

	public void setSelectedItem(int selectedItem) {
		this.selectedItem = selectedItem;
	}
}
