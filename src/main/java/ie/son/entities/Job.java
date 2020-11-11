package ie.son.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Job {

	@ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "userid",nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
	@JsonIgnore
	private User user;
	
	public Job(String jobName, String description, LocalDate timestamp,  User User, boolean jobActive) {
		this.jobName = jobName;
		this.description = description;
		this.timestamp = timestamp;
		this.user = User;
		this.jobActive = jobActive;
	}
     
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int jobId;
	
	@Column(nullable=false)
	private String jobName;
	
	@Column (nullable=false)
	boolean jobActive;
	
	@Column(nullable=false)
	private String description;
	
	@Column(nullable=false)
	private LocalDate timestamp;
	
}
