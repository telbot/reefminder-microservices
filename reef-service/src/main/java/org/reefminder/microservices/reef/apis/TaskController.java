package org.reefminder.microservices.reef.apis;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import org.reefminder.microservices.reef.dtos.TaskDTO;

@RestController
@RequestMapping("/")
public class TaskController {

	@Autowired
	private CommentsService commentsService;

	private List<TaskDTO> tasks = Arrays.asList(new TaskDTO("task11", "description11", "1"),
			new TaskDTO("task12", "description12", "1"), new TaskDTO("task13", "description13", "1"),
			new TaskDTO("task21", "description21", "2"), new TaskDTO("task22", "description22", "2"));

	@RequestMapping(method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TaskDTO> getTasks() {
		return tasks;
	}

	@RequestMapping(value = "{reefId}", method = RequestMethod.GET, headers = "Accept=application/json")
	public TaskDTO getReefByTaskId(@PathVariable("reefId") String taskId) {
		TaskDTO taskToReturn = null;
		for (TaskDTO currentTask : tasks) {
			if (currentTask.getTaskId().equalsIgnoreCase(taskId)) {
				taskToReturn = currentTask;
				break;
			}
		}

		if (taskToReturn != null) {
			taskToReturn.setComments(this.commentsService.getCommentsForTask(taskId));
		}
		return taskToReturn;
	}

	@RequestMapping(value = "/reef/{userName}", method = RequestMethod.GET, headers = "Accept=application/json")
	public List<TaskDTO> getReefByUserName(@PathVariable("userName") String userName) {
		List<TaskDTO> taskListToReturn = new ArrayList<TaskDTO>();
		for (TaskDTO currentTask : tasks) {
			if (currentTask.getUserName().equalsIgnoreCase(userName)) {
				taskListToReturn.add(currentTask);
			}
		}

		return taskListToReturn;
	}
}
