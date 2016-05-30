package messages.objects;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

public class Comments {

	@NotBlank(message = "Name can not be empty!")
	@Size(max = 30, message = "Maximum lenght is 30 symbols!")
	private String name;
	@Email(message = "Email is not valid!")
	private String email;
	@NotBlank(message = "Message can not be empty!")
	@Size(min = 10, max = 255, message = "Message has to contain from 10 to 255 symbols!")
	private String comment;
	private int id;

	public Comments() {
	}

	public Comments(String comment) {
		this.setComment(comment);
	}

	public Comments(String name, String email, String comment) {
		this.setName(name);
		this.setEmail(email);
		this.setComment(comment);
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

}
