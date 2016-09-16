package org.reefminder.microservices.reef.apis;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.stereotype.Service;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.reefminder.microservices.reef.model.CommentCollectionResource;
import org.reefminder.microservices.reef.model.CommentResource;

@Service
public class CommentsService {

	@Autowired
	private OAuth2RestOperations restTemplate;

	@HystrixCommand(fallbackMethod = "getFallbackCommentsForTask", commandProperties = {
			@HystrixProperty(name = "execution.isolation.strategy", value = "SEMAPHORE"),
			@HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
			@HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "1000") })
	public CommentCollectionResource getCommentsForTask(String taskId) {
		// Get the comments for this reef
		return restTemplate.getForObject(String.format("http://comments-webservice/comments/%s", taskId),
				CommentCollectionResource.class);

	}

	@SuppressWarnings("unused")
	private CommentCollectionResource getFallbackCommentsForTask(String taskId) {

		CommentCollectionResource comments = new CommentCollectionResource();
		comments.addComment(new CommentResource(taskId, "default comment", Calendar.getInstance().getTime()));

		return comments;
	}
}
