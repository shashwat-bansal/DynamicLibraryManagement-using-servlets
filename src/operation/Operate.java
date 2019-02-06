package operation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import data.*;

public class Operate<E> {
	Connection con = null;

	public Operate() {
		try {
			con = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/library", "root",
					"infoobjects");
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	public boolean IsIdExist(int id, String className) throws SQLException {
		String str = "Select id from " + className + " ;";
		PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(str);
		ResultSet rs = pstmt.executeQuery();
		while (rs.next()) {
			if (rs.getInt(1) == id) {
				return true;
			}
		}
		return false;
	}

	public void create(int id, E Temp) {

		try {
			String className = Temp.getClass().getSimpleName();
			Map<String, String> map = ((Library) Temp).tomap();
			StringBuilder str = new StringBuilder("insert into " + className + " values (?");
			for (int i = 1; i < map.size(); i++) {
				str.append(",?");
			}
			str.append(");");
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(str.toString());
			int j = 1;
			Iterator<Map.Entry<String, String>> itr = map.entrySet().iterator();
			while (itr.hasNext()) {
				Map.Entry<String, String> entry = itr.next();
				pstmt.setString(j++, entry.getValue().toString());
			}
			pstmt.executeUpdate();
			pstmt.close();
		} catch (Exception e) {
			System.out.println(e.toString());
		}

	}

	@SuppressWarnings("unchecked")
	public List<E> viewAll(E temp) {
		List<E> list = new ArrayList<>();
		try {
			String query = "Select * from " + temp.getClass().getSimpleName() + ";";
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			if (temp instanceof Book) {
				while (rs.next()) {
					list.add((E) new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getLong(6)));
				}
			} else if (temp instanceof Issuer) {
				while (rs.next()) {
					list.add((E) new Issuer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5)));
				}
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
		return list;

	}

	public void delete(int id, E temp) {
		try {
			String query = "Delete from " + temp.getClass().getSimpleName() + " where id = ?;";
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

	@SuppressWarnings("unchecked")
	public E viewOne(int id, E temp) {
		String query = "Select * from " + temp.getClass().getSimpleName() + " where id = ?;";
		try {
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(query);
			pstmt.setInt(1, id);
			ResultSet rs = pstmt.executeQuery();
			if (temp instanceof Book) {
				while (rs.next()) {
					return (E) new Book(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5), rs.getLong(6));
				}
			} else if (temp instanceof Issuer) {
				while (rs.next()) {
					return (E) new Issuer(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4),
							rs.getString(5));
				}
			}
		} catch (Exception e) {System.out.println(e);
		}
		return temp;
	}

	public void update(int id, E temp) throws SQLException {
		String className = temp.getClass().getSimpleName();
		Map<String, String> map = ((Library) temp).tomap();
		StringBuilder str = new StringBuilder("update " + className + " set ");
		for (Map.Entry<String, String> entry : map.entrySet()) {
			str.append(entry.getKey() + "= '" + entry.getValue() + "', ");
		}
		str.deleteCharAt(str.length() - 2);
		str.append(" where id = ? ;");
		try {
			PreparedStatement pstmt = (PreparedStatement) con.prepareStatement(str.toString());
			pstmt.setInt(1, id);
			pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}
}
