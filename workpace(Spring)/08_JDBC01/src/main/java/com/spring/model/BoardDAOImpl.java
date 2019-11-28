package com.spring.model;

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
public class BoardDAOImpl implements BoardDAO {

	@Autowired
	private JdbcTemplate template;
	String sql = null;

	@Override
	public List<BoardDTO> getBoardList() {
		List<BoardDTO> list = null;
		sql = "select * from board order by board_no desc";
		return list = template.query(sql, new RowMapper<BoardDTO>() {

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				// DTO 클래스 객체 생성
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_regdate"));

				return dto;
			}
		});

	}

	@Override
	public void insertBoard(final BoardDTO dto) {
		sql = "insert into board values(board_seq.nextval,?,?,?,?,default,sysdate)";
		this.template.update(sql, new PreparedStatementSetter() {

			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, dto.getBoard_writer());
				ps.setString(2, dto.getBoard_title());
				ps.setString(3, dto.getBoard_cont());
				ps.setString(4, dto.getBoard_pwd());

			}
		});

	}

	@Override
	public BoardDTO boardCont(int no) {
		sql="select * from board where board_no=?";
		return this.template.queryForObject(sql, new RowMapper<BoardDTO>(){

			@Override
			public BoardDTO mapRow(ResultSet rs, int rowNum) throws SQLException {
				BoardDTO dto = new BoardDTO();
				dto.setBoard_no(rs.getInt("board_no"));
				dto.setBoard_writer(rs.getString("board_writer"));
				dto.setBoard_title(rs.getString("board_title"));
				dto.setBoard_cont(rs.getString("board_cont"));
				dto.setBoard_pwd(rs.getString("board_pwd"));
				dto.setBoard_hit(rs.getInt("board_hit"));
				dto.setBoard_date(rs.getString("board_regdate"));
				return dto;
			
			}
			
		},no);
	}

	@Override
	public void updateBoard(BoardDTO dto) {
		sql="update board set board_title=? , board_cont=? where board_no=? ";
		
		 this.template.update(sql,new Object[]{dto.getBoard_title(),dto.getBoard_cont(),dto.getBoard_no()});

	}

	@Override
	public void deleteBoard(final int no) {
		sql="delete from board where board_no=?";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, no);				
			}
		});
	}

	@Override
	public void readCount(final int no) {

		sql = "update board set board_hit=board_hit+1 where board_no=?";
		this.template.update(sql, new PreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setInt(1, no);
				
				
			}
		});
		

	}

}
