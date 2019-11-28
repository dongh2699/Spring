package com.sist.model;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
@Repository
public class bbsDAOImpl implements bbsDAO {
	@Autowired
	private JdbcTemplate template;
	String sql=null;
	
	@Override
	public List<bbsDTO> getBoardList() {
		List<bbsDTO> list = null;
		sql = "select * from jsp_bbs order by board_group desc, board_step";
		return list = this.template.query(sql, new RowMapper<bbsDTO>() {

			@Override
			public bbsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				bbsDTO dto = new bbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_group(rs.getInt("board_group"));
				return dto;
			}						
		});
		
	
	}

	@Override
	public int insertBoard(final bbsDTO dto) {			
			int res = 0;
			sql = "insert into jsp_bbs values(bbs_seq.nextval,?,?,?,?,default,sysdate,bbs_seq.currval,0,0)";
			res = this.template.update(sql, new PreparedStatementSetter() {
				
				@Override
				public void setValues(PreparedStatement ps) throws SQLException {
					ps.setString(1, dto.getBoard_writer());
					ps.setString(2, dto.getBoard_title());
					ps.setString(3, dto.getBoard_cont());
					ps.setString(4, dto.getBoard_pwd());
					
				}
			});
			return res;

	}

	@Override
	public bbsDTO boardCont(int no) {
		sql = "select * from jsp_bbs where board_no=?";
		return this.template.queryForObject(sql, new RowMapper<bbsDTO>() {

			@Override
			public bbsDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				bbsDTO dto = new bbsDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_date(rs.getString("board_date"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_indent(rs.getInt("board_indent"));
				dto.setBoard_step(rs.getInt("board_step"));
				dto.setBoard_group(rs.getInt("board_group"));
				return dto;
			}
			
		},no);
		
	}

	@Override
	public void readCount(final int no) {
		sql = "update jsp_bbs set board_hit = board_hit+1 where board_no=?";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, no);
				
			}
		});

	}

	@Override
	public int updateBoard(bbsDTO dto) {
		int res=0;
		
		sql="update jsp_bbs set board_title=? , board_cont=? where board_no=?";
		res = this.template.update(sql,new Object[]{dto.getBoard_title(),dto.getBoard_cont(),dto.getBoard_no()});
			
		return res;
	}

	@Override
	public int deleteBoard(final int no) {
		int res=0;
		
		sql="delete from jsp_bbs where board_no=?";
		res = this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, no);				
			}
		});
		
		return res;
	}

	@Override
	public void replyUpdate(int board_group, int board_step) {
		sql = "update jsp_bbs set board_step = board_step + 1"
				+ " where board_group=? and board_step> ?";
		this.template.update(sql, new Object[] {board_group, board_step});
	}

	@Override
	public int replyBoard(final bbsDTO dto) {
		int res=0;
		sql = "insert into jsp_bbs values(bbs_seq.nextval,?,?,?,?,default,sysdate,?,?,?)";
		res = this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				
				ps.setString(1,dto.getBoard_writer());
				ps.setString(2,dto.getBoard_title());
				ps.setString(3,dto.getBoard_cont());
				ps.setString(4,dto.getBoard_pwd());
				ps.setInt(5, dto.getBoard_group());
				ps.setInt(6, dto.getBoard_step() + 1);
				ps.setInt(7, dto.getBoard_indent() + 1);
			}
		});
		
		
		return res;

	}

}
