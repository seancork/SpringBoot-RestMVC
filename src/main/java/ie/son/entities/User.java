package ie.son.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class User {

	@OneToOne 
	@JoinColumn(name = "roleEmail", nullable = false)
	Role userRole;

	public User(String email, String name, String password, String phoneNum, boolean userEnabled, Role roleEmail ) {
		this.email = email;
		this.name = name;
		this.password = password;
		this.phoneNum = phoneNum;
		this.userEnabled = userEnabled;
		this.userRole = roleEmail;
	}
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int userId;
	
	@Column(nullable=false)
	private String email;
	
	@Column(nullable=false)
	private String phoneNum;
	
	@Column(nullable=false)
	@Size(min=8) 
	private String password;
	
	@Column(nullable=false)
	private String name;
	
	@Column boolean userEnabled;

}
