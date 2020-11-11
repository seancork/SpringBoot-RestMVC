package ie.son.formobjects;

import javax.persistence.Column;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterForm {
	
	@NotNull
	@Email
	@Column(unique=true) // this checks if the email is already in the db and gives an error if so.
	@Size(min=1, max=200)
	private String email;	
	
	@NotNull
	@Size(min=1, max=60)
	private String password;	
	
	@NotNull
	@Digits(integer = Integer.MAX_VALUE, fraction = 0)
	@Size(min=6, max=20)
	private String phonenum;
	
	@NotNull
	@Size(min=1, max=200)
	private String name;	
}