package com.capgemini.trainingPortal.entity;

import java.util.List;

import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="users")
@Builder
public class Users {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    
    private String userName;
    private String email;
    private String password;
    
    private String role;
    private String grade; // Add field for grade
    
//    @ElementCollection
//    private List<Long> assignedTrainings; 
//    // Constructor, getters, setters

//  8  private List<Long> completedTrainings;

	public List<Long> getAssignedTrainings() {
		// TODO Auto-generated method stub
		return null;
	}

	
	
}
