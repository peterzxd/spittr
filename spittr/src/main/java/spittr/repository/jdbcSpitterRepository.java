package spittr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import spittr.domain.Spitter;


@Repository
public class jdbcSpitterRepository implements SpitterRepository {

	private JdbcOperations jdbc;

	@Autowired
	public jdbcSpitterRepository(JdbcOperations jdbc) {
		super();
		this.jdbc = jdbc;
	}

	@Override
	public Spitter save(Spitter spitter) {
		jdbc.update("insert into Spitter (username, password, first_name,last_name,fullName,email,updateByEmail)  values (?,?,?,?,?,?)",
				spitter.getUsername(), spitter.getPassword(), spitter.getFirstName(), spitter.getLastName(),
				spitter.getFullName(),spitter.getEmail(),spitter.getUpdateByEmail());
		return spitter;
	}

	private static final String FIND_BY_USERNAME = "select id,username,password,first_name,last_name,fullName,email,updateByEmail form Spitter where username=?";

	@Override
	public Spitter findByUsername(String username) {
		return jdbc.queryForObject(FIND_BY_USERNAME, new SpittleRowMapper(), username);
	}

	@Override
	public List<Spitter> findAll() {

		return jdbc.query("select id, username,password,first_name,last_name,fullName,email, updateByEmail form Spitter", (rs, rowNum) -> {
			return new Spitter(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
					rs.getString("firstName"), rs.getString("lastName"),rs.getString("fullName"), rs.getString("email"),rs.getBoolean("updateByEmail"));
		});
	}

	static final String SELECT_SPITTER_BY_ID = "select id, username, password, first_name, last_name,fullName,email,updateByEmail from spitter"
			+ " where id=?";

	public Spitter findOne(long id) {
		return jdbc.queryForObject(SELECT_SPITTER_BY_ID, (rs, rowNum) -> {

			return new Spitter(rs.getLong("id"), rs.getString("username"), rs.getString("password"), rs.getString("firstName"),
					rs.getString("lastName"),rs.getString("fullName"), rs.getString("email"),rs.getBoolean("updateByEmail"));
		},id);
	}
	
	private static class SpittleRowMapper implements RowMapper<Spitter> {
		public Spitter mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spitter(rs.getLong("id"), rs.getString("username"), rs.getString("password"),
					rs.getString("firstname"), rs.getString("lastname"),rs.getString("fullName"),rs.getString("email"),rs.getBoolean("updateByEmail"));
		}
	}

}
