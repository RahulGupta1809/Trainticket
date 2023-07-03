package com.cglia.register;

import java.sql.*;



import com.cglia.dao.DBConnection;

public class RegisterDAO {
	public int k = 0;

	public int register(UserBean ub) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("insert into users(name,email,password,age,mobileNo) values(?,?,?,?,?)");
			ps.setString(1, ub.getName());
			ps.setString(2, ub.getEmail());
			ps.setString(3, ub.getPassword());
			ps.setInt(4, ub.getAge());
			ps.setString(5, ub.getMobileNo());
			k = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return k;
	}
}
