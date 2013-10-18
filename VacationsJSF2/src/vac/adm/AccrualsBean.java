package vac.adm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import vac.adm.models.Accrual;

@Named
@SessionScoped
public class AccrualsBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Resource(name="vacRes")
	private DataSource ds;
	private List<Accrual> accrualsList;	
	private HtmlDataTable dataTableAccruals;
	private Accrual itemAccrual = new Accrual();
	private String actionDesc;

	@Inject
	private EmployeesBean employeesBean;
	
	private int rowIndex;
	
	public List<Accrual> getAccrualsList() throws SQLException {
		System.out.println("preparing to get accruals");
		System.out.println(employeesBean.getItemEmployee().getName());
		Connection conn = ds.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"Use Vacations " +
					"SELECT TOP 20 " +
						"IdRecord, " +
						"IdEmloyee, " +
						"Date, " +
						"Reason, " +
						"Volume, " +
						"Note " +
					"FROM Vacations " +
					"WHERE IdEmloyee = ? " +
					"ORDER BY Date DESC");
			stmt.setInt(1, employeesBean.getItemEmployee().getIdEmploees());
			
			ResultSet result = stmt.executeQuery();
			
			accrualsList = new ArrayList<Accrual>();			
			while (result.next()) {
				accrualsList.add(new Accrual(
						result.getInt("IdRecord"),						
						result.getInt("IdEmloyee"),
						result.getDate("Date"),
						result.getString("Reason"),
						result.getDouble("Volume"),
						result.getString("Note")
						));
			}
						
			return accrualsList;
		} 
		
		finally
		{
			conn.close();
		}				 
	}
	
	public Accrual getItemAccrual() {
		return itemAccrual;
	}
	
	public void setItemAccrual() {
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemAccrual = accrualsList.get(this.rowIndex - 1);			
	        System.out.println(rowIndex);
	        System.out.println(itemAccrual.getNote());
	    } else {
	    	System.out.println("Не выбрана строка");
	    }
	}
	
	public String editAccrual() {
		
		return "editAccrual";		
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

	public HtmlDataTable getDataTableAccruals() {
		return dataTableAccruals;
	}

	public void setDataTableAccruals(HtmlDataTable dataTableAccruals) {
		this.dataTableAccruals = dataTableAccruals;
	}
}
