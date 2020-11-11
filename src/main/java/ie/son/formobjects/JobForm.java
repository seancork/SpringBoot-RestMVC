package ie.son.formobjects;

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
public class JobForm {
	@NotNull
	@Size(min=1, max=30)
	private String jobName;	
	
	@NotNull
	@Size(min=1, max=200)
	private String description;	

}