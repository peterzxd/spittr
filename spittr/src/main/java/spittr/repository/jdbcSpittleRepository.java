package spittr.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import spittr.domain.Spittle;

@Repository
public class jdbcSpittleRepository implements SpittleRepository {

	private JdbcOperations jdbc;

	@Autowired
	public jdbcSpittleRepository(JdbcOperations jdbc) {
		super();
		this.jdbc = jdbc;
	}
 private static final String COUNT="select count(*) from Spittle";
	@Override
	public long count() {
		return jdbc.queryForObject(COUNT,long.class);

	}

	@Override
	public List<Spittle> findRecent() {
		return null;

	}

	private static final String FINDRECENT="select id,message,created_at,latitude,longitude,spitter"
			+ " from Spittle"
			+ " order by created_at desc limit ? ";
	@Override
	public List<Spittle> findRecent(int count) {
		return jdbc.query(FINDRECENT,
				(rs,rowNum) ->{
					return new Spittle(
					rs.getLong("id"),
					rs.getString("message"),
					rs.getDate("created_at"),
					rs.getDouble("latitude"),
					rs.getDouble("longitude"),
					rs.getLong("spitter"));
				},count);
		
		
	}
private static final String SELECT_SPITTER_BY_ID="select id,message,created_at,latitude,longitude,spitter"
		+ " from Spittle"
		+ " where id=?";
	@Override
	public Spittle findOne(long id) {
		return jdbc.queryForObject(SELECT_SPITTER_BY_ID,
				(rs,rowNum) ->{
					return new Spittle(
				rs.getLong("id"),
				rs.getString("message"),
				rs.getDate("created_at"),
				rs.getDouble("longitude"),
				rs.getDouble("longitude"),
				rs.getLong("spitter"));
				},id);
	}
	
	private static final String FIND_BY_SPITTERID="select e.id,message,created_at,latitude,longitude,spitter"
			+ " from Spittle e inner join Spitter s  on s.id=e.spitter where s.id=?";

	@Override
	public List<Spittle> findBySpitterId(long spitterId) {
		
		return jdbc.query(FIND_BY_SPITTERID,
				(rs,rowNum) ->{
					return new Spittle(
						rs.getLong("id"),
						rs.getString("message"),
						rs.getDate("created_at"),
						rs.getDouble("latitude"),
						rs.getDouble("longitude"),
						rs.getLong("spitter"));
					
				},spitterId);
	}

	

	@Override
	public Spittle save(Spittle spittle) {
		 jdbc.update("insert into Spittle (message ,created_at,latitude,longitude)  values (?,?,?,?)"
				 ,spittle.getMessage(),spittle.getTime(),spittle.getLatitude(),spittle.getLongitude());
		return spittle;
	}

	@Override
	public void delete(long id) {
		jdbc.update("delete from Spittle where id=?", id);

	}

	private static class SpittleRowMapper implements RowMapper<Spittle> {
		public Spittle mapRow(ResultSet rs, int rowNum) throws SQLException {
			return new Spittle(rs.getLong("id"), rs.getString("message"), rs.getDate("created_at"),
					rs.getDouble("longitude"), rs.getDouble("latitude"),rs.getLong("spitter"));
		}
	}

}
