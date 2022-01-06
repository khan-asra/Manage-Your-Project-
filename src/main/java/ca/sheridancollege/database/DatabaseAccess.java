package ca.sheridancollege.database;

import java.util.List;

import javax.sql.DataSource;


import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.PreparedStatementCallback;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import ca.sheridancollege.bean.Member;
import ca.sheridancollege.bean.Task;





@Repository
public class DatabaseAccess {
	
	
	private NamedParameterJdbcTemplate jdbc;
	
	@Bean
	public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource dataSource) {
		return new NamedParameterJdbcTemplate(dataSource);
	}
	public DatabaseAccess(DataSource dataSource) {
		this.jdbc = new NamedParameterJdbcTemplate(dataSource);
	}



	public int addNewMember(String member) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
		String query = "INSERT INTO members (memberName) "
				+ "VALUES (:memberName)";
		System.out.print(member);
		namedParameters
		.addValue("memberName", member);
		int returnValue =jdbc.update(query, namedParameters);
		return returnValue;
		
	}


	public List<Member> getAllMembers() {
		
		
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM members";
			
		BeanPropertyRowMapper<Member> memberMapper = new BeanPropertyRowMapper<Member>(Member.class);
		List<Member> members = jdbc.query(query, namedParameter, memberMapper);
		return members ;
		
	}
	
	public int addNewTask(Task task) {
		MapSqlParameterSource namedParameters = new MapSqlParameterSource();
	   String query = "INSERT INTO Tasks (taskTitle, taskHolder, memberId , task) "
				+ "VALUES (:taskTitle, :taskHolder, :memberId, :task)";
		System.out.print(task.getTaskTitle());
		
		Member member=	getMember(task.getTaskHolder());
		
		

		
		
	//	long memberId=1;
		namedParameters
		.addValue("taskTitle", task.getTaskTitle()).addValue("task", task.getTask()).addValue("memberId", member.getId()).addValue("taskHolder", task.getTaskHolder());
		int returnValue =jdbc.update(query, namedParameters);
		
		return returnValue;
		
	}
	
	
	
	public List<Task> getAllTasks() {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM tasks";
			
		BeanPropertyRowMapper<Task> memberMapper = new BeanPropertyRowMapper<Task>(Task.class);
		List<Task> tasks = jdbc.query(query, namedParameter, memberMapper);
	
		return tasks ;
	}
	
	public Task getTask(String title) {
		
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM tasks where taskTitle= :title";
		namedParameter
		.addValue("title", title);
			
		BeanPropertyRowMapper<Task> taskMapper = new BeanPropertyRowMapper<Task>(Task.class);
		List<Task> tasks = jdbc.query(query, namedParameter,taskMapper);
	
		return tasks.get(0) ;
		
	
		
	}
	
	public Member getMember(String memberName) {
		
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT * FROM members where memberName= :memberName";
		namedParameter
		.addValue("memberName", memberName);
		BeanPropertyRowMapper<Member> taskMapper = new BeanPropertyRowMapper<Member>(Member.class);
		List<Member> members = jdbc.query(query, namedParameter,taskMapper);
		return members.get(0) ;
		
		
	}
	
	
	public List<String> getMembersName() {
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query = "SELECT memberName FROM members";	
		BeanPropertyRowMapper<String> memberMapper = new BeanPropertyRowMapper<String>(String.class);
		List<String> members = jdbc.query(query, namedParameter, memberMapper);
		return members ;

	}
	public int deleteTask(Long taskId) {
		
		MapSqlParameterSource namedParameter = new MapSqlParameterSource();
		String query ="Delete From tasks WHERE taskId = :taskId ";
		namedParameter.addValue("taskId", taskId);
		int returnValue=jdbc.update(query, namedParameter);
		System.out.print(returnValue);
		return returnValue;
	
		
		

		
	}
	
	
	
	

	

	
	

}
