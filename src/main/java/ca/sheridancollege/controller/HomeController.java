package ca.sheridancollege.controller;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import ca.sheridancollege.bean.Member;
import ca.sheridancollege.bean.Task;
import ca.sheridancollege.database.DatabaseAccess;

@Controller

public class HomeController {
	
	
	
	@RequestMapping("/showingTymleafTextInJavaScript")
	public String thankYou(Model model){
	 model.addAttribute("showTextFromJavaController","dummy text");
	 return "showingTymleafTextInJavaScript";
	}
	
	

	public CopyOnWriteArrayList<Member> membersList = new CopyOnWriteArrayList<>();
	public CopyOnWriteArrayList<Task> taskList = new CopyOnWriteArrayList<>();
	private DatabaseAccess database;
	
	//private List<Member> allMembers=database.getAllMembers();
	
	public HomeController(DatabaseAccess database) {
	this.database = database;
	}


	@GetMapping("/")
	public String goIndex() {

		return "index";
	}

	@PostMapping("/makeTeam")
	public String makeTeam(@RequestParam String nameOfMember,
			@ModelAttribute Member member, Model model ) {
		String[] arrSplit = nameOfMember.split(",");
		if(nameOfMember.length()>0) {
		for (int i = 0; i < arrSplit.length; i++) {
			System.out.println(arrSplit[i].trim());
			member = new Member();
			String name =arrSplit[i].trim();
			member.setMemberName(name);
			database.addNewMember(member.getMemberName());
			database.getMember(name);
			membersList.add(	database.getMember(name));
			}	
		}
		List<Task> task=database.getAllTasks();
	   List <Member> members=database.getAllMembers();
		model.addAttribute("membersList", membersList);
		model.addAttribute("allTask", task);
		
		return "assign_Task";
		}
	
	@PostMapping("/createTask")
	public String createTask(Model model, @ModelAttribute Task task ,@RequestParam String taskHolder) {
		database.addNewTask(task);
		return "index";
	}
	
	

	
	
	@GetMapping("/memberName")
	public String memberName(Model model ,@ModelAttribute Member showMen) {
		
		
		System.out.print("all good");
		Member showMem= new Member();
		List <Member> members=database.getAllMembers();
		//List <Task> task= database.getAllTasks();
		model.addAttribute("showMember",showMem);
		model.addAttribute("members", members);
		model.addAttribute("membersList", membersList);
	//	model.addAttribute("member",member);
		
		return "members";
	}
	
	

	
	@GetMapping("/aboutus")
	public String goAboutus() {

		return "/information/aboutUs";
	}

	
	
	@GetMapping("/tasktomember/{taskId}")
	public String assignTaskToMember(Model model, @RequestParam long taskId ) {
		System.out.print("task id is "+taskId);

		return "index";
	}
	
	@GetMapping("/addTask")
	public String goAddTask(Model model) {
		
		//make it short 
		List <Member> members=database.getAllMembers();
		model.addAttribute("task", new Task());
		model.addAttribute("members",members);
		return "addTask";
	}
	
	
	@GetMapping("/delete/{taskId}")
	public String deleteTask(Model model, @PathVariable Long taskId) {
		
	    database.deleteTask(taskId);
		
		List<Task> task=database.getAllTasks();
		model.addAttribute("allTask", task);
		
		return "index";
	}
	

	
	
}
