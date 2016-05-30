package messages.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import messages.objects.Comments;

public class DaoImpl implements Dao {

	private JdbcTemplate jdbcTemplate;

	public DaoImpl(DataSource dataSource) {
		jdbcTemplate = new JdbcTemplate(dataSource);
	}

	@Override
	public void create(Comments comments) {
		String sql = "INSERT INTO DATA (name, email, comment)" + " VALUES (?, ?, ?)";
		jdbcTemplate.update(sql, comments.getName(), comments.getEmail(), comments.getComment());

	}

	@Override
	public Comments select(int id) {
		String sql = "SELECT comment FROM DATA WHERE id = " + id;
		return jdbcTemplate.query(sql, new ResultSetExtractor<Comments>() {

			@Override
			public Comments extractData(ResultSet rs) throws SQLException, DataAccessException {
				if (rs.next()) {
					Comments acomments = new Comments();

					// comments.setId(rs.getInt("id"));
					// comments.setName(rs.getString("name"));
					// comments.setEmail(rs.getString("email"));
					acomments.setComment("\"" + rs.getString("comment") + "\"" + "\n");

					return acomments;
				}

				return null;
			}

		});
	}

	@Override
	public List<Comments> selectAll() {
		String sql = "select * from DATA";
		List<Comments> listComm = jdbcTemplate.query(sql, new RowMapper<Comments>() {

			@Override
			public Comments mapRow(ResultSet rs, int rowNum) throws SQLException {
				Comments comments = new Comments();

				comments.setId(rs.getInt("id"));
				comments.setName(rs.getString("name"));
				comments.setEmail(rs.getString("email"));
				comments.setComment(rs.getString("comment"));

				return comments;
			}

		});

		return listComm;
	}

}
