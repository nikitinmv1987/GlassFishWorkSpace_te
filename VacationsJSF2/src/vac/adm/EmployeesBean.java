package vac.adm;

import javax.annotation.Resource;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.html.HtmlDataTable;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.sql.DataSource;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import vac.adm.models.Employee;

@Named
@SessionScoped
public class EmployeesBean implements Serializable{
	private static final long serialVersionUID = 1L;
	@Resource(name="vacRes")
	private DataSource ds;
	private List<Employee> dataList;	
	private HtmlDataTable dataTableEmployees;
	private Employee itemEmployee = new Employee();
	private String actionDesc;
	
	private int rowIndex;
	
	
	public List<Employee> getEmployeesList() throws SQLException { 		
		Connection conn = ds.getConnection();
		try {
			Statement stmt = conn.createStatement();
			ResultSet result = stmt.executeQuery(
					"Use Vacations " +
					"SELECT " +
						"E.IdEmploees, " +
						"E.Name, " +
						"E.Position, " +
						"E.TabNumber, " +
						"E.Telephone, " +
						"E.Login, " +
						"E.Admin " +
					"FROM dbo.Employees E ORDER BY E.Name");
			
			dataList = new ArrayList<Employee>();			
			while (result.next()) {
				dataList.add(new Employee(
						result.getInt("IdEmploees"),
						result.getString("Name"),
						result.getString("Position"),
						result.getString("TabNumber"),
						result.getString("Telephone"),
						result.getString("Login"),
						result.getBoolean("Admin")						
						));
			}
						
			return dataList;
		} 
		
		finally
		{
			conn.close();
		}				 						
	}
	
	public void setItemEmployee() {
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemEmployee = dataList.get(this.rowIndex - 1);			
	        System.out.println(rowIndex);
	        System.out.println(itemEmployee.getName());
	    } else {
	    	System.out.println("Не выбрана строка");
	    }
	}

	public String editEmployee() throws SQLException {		
		String result ="editEmployee?faces-redirec=true"; 
		
		if (rowIndex > 0) {
			actionDesc = "Редагування співробітника";
	    } else {
	    	result = "";
	    }
						
		return result;
	}
	
	public String addEmployee() throws SQLException {
		actionDesc = "Внесення нового співробітника";		
		itemEmployee = new Employee();
		
		return "editEmployee?faces-redirec=true";
	}

	public String removeEmployee() throws SQLException {
		System.out.println("preparing to delete");
		
		String rowIndex = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("rowIndex");	
		
		if (rowIndex != null && rowIndex.trim().length() != 0) {
			setRowIndex(Integer.parseInt(rowIndex)); 
			itemEmployee = dataList.get(this.rowIndex - 1);			
	        
	        Connection conn = ds.getConnection();
	        try {
				PreparedStatement deleteEmployee = conn.prepareStatement(
						"DELETE FROM Employees " +
						"WHERE IdEmploees = ?");
				
				deleteEmployee.setInt(1, itemEmployee.getIdEmploees());							
				deleteEmployee.execute();				
			}
			finally {
				conn.close();
			}		
	        
	    } else {
	    	System.out.println("Не выбрана строка");
	    }
		
		setRowIndex(-1);
		return "employees";
	}
	
	public String saveEmployee() throws SQLException {		
		
		if (!(itemEmployee.getIdEmploees()>0))	{
			System.out.println("preparring to insert");
			Connection conn = ds.getConnection();
	        try {
				PreparedStatement insertEmployee = conn.prepareStatement(						
						"INSERT INTO Employees(" +
							"Name, " +
							"Position, " +
							"TabNumber, " +
							"Telephone, " +
							"Login, " +
							"Admin) " +
						"VALUES(?, ?, ?, ?, ?, ?) ", Statement.RETURN_GENERATED_KEYS);						
				
				insertEmployee.setString(1, itemEmployee.getName());
				insertEmployee.setString(2, itemEmployee.getPosition());
				insertEmployee.setString(3, itemEmployee.getTabNumber());
				insertEmployee.setString(4, itemEmployee.getTelephone());
				insertEmployee.setString(5, itemEmployee.getLogin());
				insertEmployee.setBoolean(6, itemEmployee.getAdmin());			
				
				insertEmployee.executeUpdate();			
				
				ResultSet generatedKeys = insertEmployee.getGeneratedKeys();
				
				int returnId = 0;
				if (generatedKeys.next()) {
					returnId = generatedKeys.getInt(1);
				}		
				
				setRowIndex(-1);
				getEmployeesList();
				for (Employee empl : dataList) {
					if (empl.getIdEmploees() == returnId ) {
						setRowIndex(dataList.indexOf(empl) + 1);
					}
				}
			}
			finally {
				conn.close();
			}	        
		}
		else {
			System.out.println("preparring to update");	
	        Connection conn = ds.getConnection();
	        try {
				PreparedStatement updateEmployee = conn.prepareStatement(
						"UPDATE Employees " +
						"SET Name = ?, " +
						     "Position = ?, " +
						     "TabNumber = ?, " +
						     "Telephone = ?, " +
						     "Login = ?, " +
						     "Admin = ? " +
						"WHERE IdEmploees = ?");
				
				updateEmployee.setString(1, itemEmployee.getName());
				updateEmployee.setString(2, itemEmployee.getPosition());
				updateEmployee.setString(3, itemEmployee.getTabNumber());
				updateEmployee.setString(4, itemEmployee.getTelephone());
				updateEmployee.setString(5, itemEmployee.getLogin());
				updateEmployee.setBoolean(6, itemEmployee.getAdmin());
				updateEmployee.setInt(7, itemEmployee.getIdEmploees());				
				
				updateEmployee.execute();				
			}
			finally {
				conn.close();
			}
		}	
		
        return "employees"; // Navigation case.
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
}
