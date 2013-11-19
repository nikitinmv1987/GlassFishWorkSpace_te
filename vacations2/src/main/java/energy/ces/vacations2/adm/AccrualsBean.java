package energy.ces.vacations2.adm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import model.Vacation;

@Named
@SessionScoped
public class AccrualsBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Resource(name="vacRes")
	private DataSource ds;
	private List<Vacation> vacationsList;	
	private HtmlDataTable dataTableVacations;
	private Vacation itemVacation = new Vacation();
	private String actionDesc;
	private int actionId;		

	@Inject
	private EmployeesBean employeesBean;
	
	private int rowIndex;
	
	public List<Vacation> getVacationsList() {		
		return employeesBean.getItemEmployee().getVacations();								 
	}


/*	public void updateVacList() {			
		vacationsList = employeesBean.getME().getVacList(employeesBean.getItemEmployee().getIdEmploees());
	}
*/
	
	public Vacation getItemVacation() {
		return itemVacation;
	}
	
	public void setItemVacation() {
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemVacation = vacationsList.get(this.rowIndex - 1);			
	    }
	}
	
	public String editVacation() {
		String result = ""; 
		
		if (rowIndex > 0) {
			actionDesc = "Редагування начислення";
			result = "editAccrual?faces-redirec=true";
			setActionId(1); 
	    }						
		return result;		
	}
	
	public String saveVacation() throws SQLException {				
		employeesBean.getItemEmployee().addVacation(itemVacation);			
		int returnId = employeesBean.getME().addVacation(itemVacation);									

		for (Vacation vac : getVacationsList()) {
			if (vac.getIdRecord() == returnId ) {
				setRowIndex(vacationsList.indexOf(vac) + 1);
				itemVacation = vac;
			}
		}    
			
        return "accruals"; // Navigation case.
    }
	
	public String addVacationPos() throws SQLException {
		actionDesc = "Нарахування відгула";
		setActionId(2);
		itemVacation = new Vacation();
		setRowIndex(0);
		
		return "editAccrual?faces-redirec=true";
	}
	
	public String addVacationNeg() throws SQLException {
		actionDesc = "Виробіток відгула";
		setActionId(3);
		itemVacation = new Vacation();
		setRowIndex(0);
		
		return "editAccrual?faces-redirec=true";
	}
	
	public String removeVacation() throws SQLException {
		System.out.println("preparing to delete");
		
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");	
		
		if (rowIndex != null && rowIndex.trim().length() != 0 && (Integer.parseInt(rowIndex) != 0)) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemVacation = vacationsList.get(this.rowIndex - 1);			
	        
	        Connection conn = ds.getConnection();
	        try {
				PreparedStatement deleteEmployee = conn.prepareStatement(
						"DELETE FROM Vacations " +
						"WHERE IdRecord = ?");
				
				deleteEmployee.setInt(1, itemVacation.getIdRecord());							
				deleteEmployee.execute();				
			}
			finally {
				conn.close();
			}		
	        
	    } else {
	    	System.out.println("�� ������� ������");
	    }
		
		setRowIndex(0);
		return "vacation";
	}
	
	public EmployeesBean getEmployeesBean() {
		return employeesBean;
	}

	public void setEmployeesBean(EmployeesBean employeesBean) {
		this.employeesBean = employeesBean;
	}
	
	public int getRowIndex() {
		return rowIndex;
	}

	public void setRowIndex(int rowIndex) {
		this.rowIndex = rowIndex;
	}

	public String getActionDesc() {
		return actionDesc;
	}

	public void setActionDesc(String actionDesc) {
		this.actionDesc = actionDesc;
	}

	public HtmlDataTable getDataTableVacations() {
		return dataTableVacations;
	}

	public void setDataTableVacations(HtmlDataTable dataTableVacations) {
		this.dataTableVacations = dataTableVacations;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

}
