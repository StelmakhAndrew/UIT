package project.dao.impl;

import project.Entity.User;
import project.dao.CustomerDAO;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcCustomerDAO implements CustomerDAO
{
	private DataSource dataSource;
	
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	public void insert(User user){
		
		String sql = "INSERT INTO USERSER " +
				"(ID, NAME) VALUES (?, ?)";
		Connection conn = null;
		
		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setLong(1, user.getId());
			ps.setString(2, user.getName());
			ps.executeUpdate();
			ps.close();
			
		} catch (SQLException e) {
 			throw new RuntimeException(e);
			
		} finally {
			if (conn != null) {
				try {
					conn.close();
				} catch (SQLException e) {}
			}
		}
	}
	
	public User findByCustomerId(int Id){

		String sql = "SELECT * FROM userser WHERE id = ?";

		Connection conn = null;

		try {
			conn = dataSource.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, Id);
			User customer = null;
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				customer = new User(
						rs.getLong("id"),
						rs.getString("name")
				);
			}
			rs.close();
			ps.close();
			return customer;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (conn != null) {
				try {
				conn.close();
				} catch (SQLException e) {}
			}
		}
	}
}



