package es.ricardo.board.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "BME_CATEGORY")
public class Category {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name = "SEQ_CATEGORY", sequenceName = "SEQ_CATEGORY", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_CATEGORY")
    private Long id;
	
	@Column(name = "DESCRIPTION", nullable = false)
    private String description;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
