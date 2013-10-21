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
import javax.inject.Inject;
import javax.inject.Named;
import javax.sql.DataSource;

import vac.adm.models.Accrual;

@Named
@SessionScoped
public class VacationsBean implements Serializable{
	private static final long serialVersionUID = 1L;

	@Resource(name="vacRes")
	private DataSource ds;
	private List<Accrual> accrualsList;	
	private HtmlDataTable dataTableAccruals;

	@Inject
	private EmployeesBean employeesBean;	
	
	public List<Accrual> getAccrualsList() throws SQLException {
		System.out.println("preparing to get accruals");
		System.out.println(employeesBean.getItemEmployee().getName());
		Connection conn = ds.getConnection();
		try {
			PreparedStatement stmt = conn.prepareStatement(
					"Use Vacations " +
					"SELECT " +
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
	
	public EmployeesBean getEmployeesBean() {
		return employeesBean;
	}

	public void setEmployeesBean(EmployeesBean employeesBean) {
		this.employeesBean = employeesBean;
	}
	
	public HtmlDataTable getDataTableAccruals() {
		return dataTableAccruals;
	}

	public void setDataTableAccruals(HtmlDataTable dataTableAccruals) {
		this.dataTableAccruals = dataTableAccruals;
	}	
}
