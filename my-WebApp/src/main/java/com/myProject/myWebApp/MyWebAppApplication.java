package com.myProject.myWebApp;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.sendgrid.Method;
import com.sendgrid.Request;
import com.sendgrid.Response;
import com.sendgrid.SendGrid;
import com.sendgrid.helpers.mail.Mail;
import com.sendgrid.helpers.mail.objects.Content;
import com.sendgrid.helpers.mail.objects.Email;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping; // added
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping; // added
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This application can use both local mySQL server 
 * or Google's Firestore server
 */
@SpringBootApplication
@RestController
@Controller
public class MyWebAppApplication {
	public static void main(String[] args) {
		SpringApplication.run(MyWebAppApplication.class, args);
	}

	@GetMapping("/hello") // added
    public String hello() { // added
        return "Hello world!"; // added
    } // added

	@Autowired
	private UserRepository userRepository;

	@PostMapping("/sign-up") //Post mapping since we are using POST
	public @ResponseBody String signUp(@RequestBody Map<String, String> body) throws 
		IOException, InterruptedException, ExecutionException{
		//return "{\"message\": \"Sign Up!\"}"; //need to send JSON formatted data
		//otherwise client side cannot read file
		//here we're using a map to map the request body from the client
		//the ObjectMapper() will write back data as String, no need to use message
		//then return back to the client!
		//return new ObjectMapper().writeValueAsString(body);
		//now we will use the Firestore database
		//Firestore db = getFirestore(); //get our database
		String email = body.get("email");
		//add the email to the database
		//DocumentReference doc = db.collection("emails").document(body.get("email"));
		//ApiFuture<WriteResult> data = doc.set(body); //set email back
		//System.out.println(data.get().getUpdateTime());
		
		//init mySQL db instance
		User user = new User(); //new user table
		user.setEmail(email); //set the email
		
		userRepository.save(user);
		sendEmail(email);

		return new ObjectMapper().writeValueAsString(body);
		
	}

	//send email using SendGrid API
	private void sendEmail(String email) throws IOException {
		Email from = new Email("vuhoanglong1512.ca@gmail.com");
    	String subject = "RENTTO Updates Registration";
    	Email to = new Email(email);
    	Content content = new Content("text/plain", "Thank you for subscribing! " + 
		"You are now added to the list. We will notify you as soon as RENTTO goes live!");
    	Mail mail = new Mail(from, subject, to, content);

		SendGrid sg = new SendGrid(System.getenv("SENDGRID_API_KEY"));
		Request request = new Request();
		try {
			request.setMethod(Method.POST);
			request.setEndpoint("mail/send");
			request.setBody(mail.build());
			Response response = sg.api(request);
			System.out.println(response.getStatusCode());
			System.out.println(response.getBody());
			System.out.println(response.getHeaders());
		} catch (IOException ex) {
			throw ex;
		}
	}

	@GetMapping(path="/all")
	public @ResponseBody Iterable<User> getAllUsers() {
	  // This returns a JSON or XML with the users
	  return userRepository.findAll();
	}
		
	//for Firestore db
	/**
	 * This method will create a new instance of the 
	 * Firestore database
	 * @return
	 * @throws IOException
	 */
	/*
	private Firestore getFirestore() throws IOException{
		FirestoreOptions firestoreOptions =
		FirestoreOptions.getDefaultInstance().toBuilder()
			.setProjectId("webapp-service")
			.setCredentials(GoogleCredentials.getApplicationDefault())
			.build();
		return firestoreOptions.getService();
	}
	*/
}
