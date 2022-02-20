package model.dao.imp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import db.DbException;
import model.dao.ProductDao;
import model.entities.Product;

public class ProductDaoJDBC implements ProductDao {
	private Connection conn;

	public ProductDaoJDBC(Connection conn) {
		this.conn = conn;
	}

	@Override
	public void insert(Product obj) {
		PreparedStatement st = null;
		try {
			conn.setAutoCommit(false);
			st = conn.prepareStatement(
					"INSERT INTO PRODUCT "
				+   "(NAME, DESCRIPTION, DISCOUNT, BEGIN_DATE) " 
				+ 	"VALUES (?, ?, ?, ?)",
					Statement.RETURN_GENERATED_KEYS);
			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());
			st.setDouble(3, obj.getDiscount());
			st.setDate(4, new java.sql.Date(obj.getBeginDate().getTime()));

			int rowsAffected = st.executeUpdate();
			conn.commit();
			if (rowsAffected > 0) {
				ResultSet rs = st.getGeneratedKeys();
				if (rs.next()) {
					int id = rs.getInt(1);
					obj.setId(id);
				}
				DB.closeResultSet(rs);
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		}

	}

	@Override
	public void update(Product obj) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement(
					"UPDATE PRODUCT "
				+ 	"SET NAME = ?, DESCRIPTION = ?, DISCOUNT = ?, BEGIN_DATE = ? "
				+  	"WHERE ID = ?");
			st.setString(1, obj.getName());
			st.setString(2, obj.getDescription());
			st.setDouble(3, obj.getDiscount());
			st.setDate(4, new java.sql.Date(obj.getBeginDate().getTime()));
			st.setInt(5, obj.getId());
			st.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
				throw new DbException("rollback error!" + e1.getMessage());
			}
		}

	}

	@Override
	public void deleteById(Integer id) {
		PreparedStatement st = null;
		try {
			st = conn.prepareStatement("DELETE FROM PRODUCT WHERE ID = ?");
			st.setInt(1, id);
			int rows = st.executeUpdate();
			if (rows == 0) {
				throw new DbException("Id not found");
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
		}

	}

	@Override
	public Product findById(Integer id) {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement("SELECT * FROM PRODUCT WHERE ID = ?");
			st.setInt(1, id);
			rs = st.executeQuery();
			if (rs.next()) {
				Product obj = instantiateProduct(rs);
				return obj;
			}
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeResultSet(rs);
			DB.closeStatement(st);
		}
		return null;
	}

	@Override
	public List<Product> findAll() {
		PreparedStatement st = null;
		ResultSet rs = null;
		try {
			st = conn.prepareStatement(
					"SELECT * FROM PRODUCT ORDER BY NAME");
			rs = st.executeQuery();
			List<Product> listProduct = new ArrayList<Product>();
			while(rs.next()) {
				Product obj = instantiateProduct(rs);
				listProduct.add(obj);
			}
			return listProduct;
		} catch (SQLException e) {
			throw new DbException(e.getMessage());
		} finally {
			DB.closeStatement(st);
			DB.closeResultSet(rs);
		}
	}

	private Product instantiateProduct(ResultSet rs) throws SQLException {
		Product obj = new Product();
		obj.setId(rs.getInt("ID"));
		obj.setName(rs.getString("NAME"));
		obj.setDescription(rs.getString("DESCRIPTION"));
		obj.setDiscount(rs.getDouble("DISCOUNT"));
		obj.setBeginDate(rs.getDate("BEGIN_DATE"));
		return obj;
	}

}
