package com.cglia.search;

import java.sql.*;


import javax.servlet.http.HttpServletRequest;

import com.cglia.dao.DBConnection;

public class SearchDAO {

	public SearchBean sb = null;

	public SearchBean searchtrain(HttpServletRequest req) {
		try {
			Connection con = DBConnection.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from addtrain where trainnumber=?");
			ps.setString(1, req.getParameter("trainnumber"));
			

			ResultSet rs = ps.executeQuery();

			if (rs.next()) {
				sb = new SearchBean();
				sb.setTrainnumber(rs.getString(1));
				sb.setTrainname(rs.getString(2));
				sb.setStartingstation(rs.getString(3));
				sb.setEndingstation(rs.getString(4));
				sb.setStarttime(rs.getString(5));
				sb.setEndtime(rs.getString(6));
				sb.setDuration(rs.getString(7));

			}
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sb;

	}

}
