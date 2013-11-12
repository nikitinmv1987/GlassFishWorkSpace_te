package energy.ces.vacations2.adm;

import hib.ManageEmployee;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.DataSource;

import java.io.Serializable;
import java.util.List;

import model.Employee;

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
	
	private int rowIndex;
	
	private ManageEmployee ME;
	
	public EmployeesBean() {
		ME = new ManageEmployee();
	}
	
	public void setItemEmployeeByLogin(String login) {
		
		for (Employee empl : getEmployeesList()) {
			if (empl.getLogin().equalsIgnoreCase(login)) {
				itemEmployee = empl;
				setRowIndex(employeesList.indexOf(empl) + 1);	
				selectedItem = empl.getIdEmploees();
			}
		} 
	}
	
	public List<Employee> getEmployeesList() { 	
		
		if (employeesList == null) { 
			employeesList = ME.getEmplList();
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
	    } else {
	    	System.out.println("--lala-");
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
		System.out.println("preparing to edit");
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
			employeesList = ME.getEmplList();
	    } else {
	    	System.out.println("удалено");
	    }
		
		setRowIndex(0);
		return "employees";
	}
	
	public String saveEmployee() {		
		
		if (!(itemEmployee.getIdEmploees()>0))	{
			System.out.println("preparring to insert");
			
			int returnId = ME.addEmployee(itemEmployee);										
			employeesList =  ME.getEmplList();
			for (Employee empl : employeesList) {
				if (empl.getIdEmploees() == returnId ) {
					setRowIndex(employeesList.indexOf(empl) + 1);
					itemEmployee = empl;
					}
				}        
		}
		else {
			System.out.println("preparring to update");	
	        ME.update(itemEmployee);
		}	
		
        return "employees"; 
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
