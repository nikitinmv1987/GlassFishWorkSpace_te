package energy.ces.vacations2.adm;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import energy.ces.vacations2.adm.models.Accrual;

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
	private int actionId;

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
	    	System.out.println("�� ������� ������");
	    }
	}
	
	public String editAccrual() {
		System.out.println("preparing to edit");
		String result ="editAccrual?faces-redirec=true"; 
		
		if (rowIndex > 0) {
			actionDesc = "����������� ������";
			setActionId(1); 
	    } else {
	    	
	    	System.out.println("�� ������� ������");
	    	result = "";
	    }
						
		return result;		
	}
	
public String saveAccrual() throws SQLException {		
		
		if (!(itemAccrual.getIdRecord()>0))	{
			System.out.println("preparring to insert");
			Connection conn = ds.getConnection();
	        try {
				PreparedStatement insertAccrual = conn.prepareStatement(						
						"INSERT INTO Vacations(" +
							"IdEmloyee, " +
							"Date, " +
							"Reason, " +
							"Volume, " +
							"Note) " +
						"VALUES(?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);						
				
				insertAccrual.setInt(1, employeesBean.getItemEmployee().getIdEmploees());
				insertAccrual.setDate(2, new java.sql.Date(itemAccrual.getDate().getTime()));
				insertAccrual.setString(3, itemAccrual.getReason());
				insertAccrual.setDouble(4, itemAccrual.getVolume());
				insertAccrual.setString(5, itemAccrual.getNote());	
				
				insertAccrual.executeUpdate();			
				
				ResultSet generatedKeys = insertAccrual.getGeneratedKeys();
				
				int returnId = 0;
				if (generatedKeys.next()) {
					returnId = generatedKeys.getInt(1);
				}		
				
				refreshWithSetPosion(returnId);				
			}
			finally {
				conn.close();
			}	        
		}
		else {
			System.out.println("preparring to update");	
	        Connection conn = ds.getConnection();
	        try {
				PreparedStatement updateAccrual = conn.prepareStatement(
						"UPDATE Vacations " +
						"SET IdEmloyee = ?, " +
						     "Date = ?, " +
						     "Reason = ?, " +
						     "Volume = ?, " +
						     "Note = ? " +
						"WHERE IdRecord = ?");
				
				updateAccrual.setInt(1, itemAccrual.getIdEmployee());
				updateAccrual.setDate(2, new java.sql.Date(itemAccrual.getDate().getTime()));
				updateAccrual.setString(3, itemAccrual.getReason());
				updateAccrual.setDouble(4, itemAccrual.getVolume());
				updateAccrual.setString(5, itemAccrual.getNote());
				updateAccrual.setInt(6, itemAccrual.getIdRecord());				
				
				updateAccrual.execute();	
				
				refreshWithSetPosion(itemAccrual.getIdRecord());
			}
			finally {
				conn.close();
			}
		}	
		
        return "accruals"; // Navigation case.
    }
	
	private void refreshWithSetPosion(int id) throws SQLException {
		getAccrualsList();
		for (Accrual acr : accrualsList) {
			if (acr.getIdRecord() == id ) {
				setRowIndex(accrualsList.indexOf(acr) + 1);
				itemAccrual = acr;
			}
		}
	
	}
	
	public String addAccrualPos() throws SQLException {
		actionDesc = "����������� ������";
		setActionId(2);
		itemAccrual = new Accrual();
		setRowIndex(0);
		
		return "editAccrual?faces-redirec=true";
	}
	
	public String addAccrualNeg() throws SQLException {
		actionDesc = "������������ ������";
		setActionId(3);
		itemAccrual = new Accrual();
		setRowIndex(0);
		
		return "editAccrual?faces-redirec=true";
	}
	
	public String removeAccrual() throws SQLException {
		System.out.println("preparing to delete");
		
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");	
		
		if (rowIndex != null && rowIndex.trim().length() != 0 && (Integer.parseInt(rowIndex) != 0)) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemAccrual = accrualsList.get(this.rowIndex - 1);			
	        
	        Connection conn = ds.getConnection();
	        try {
				PreparedStatement deleteEmployee = conn.prepareStatement(
						"DELETE FROM Vacations " +
						"WHERE IdRecord = ?");
				
				deleteEmployee.setInt(1, itemAccrual.getIdRecord());							
				deleteEmployee.execute();				
			}
			finally {
				conn.close();
			}		
	        
	    } else {
	    	System.out.println("�� ������� ������");
	    }
		
		setRowIndex(0);
		return "accruals";
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

	public HtmlDataTable getDataTableAccruals() {
		return dataTableAccruals;
	}

	public void setDataTableAccruals(HtmlDataTable dataTableAccruals) {
		this.dataTableAccruals = dataTableAccruals;
	}

	public int getActionId() {
		return actionId;
	}

	public void setActionId(int actionId) {
		this.actionId = actionId;
	}

}
