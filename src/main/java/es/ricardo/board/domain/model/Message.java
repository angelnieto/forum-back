package es.ricardo.board.domain.model;


import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "BME_MESSAGE")
public class Message {

	@Id
	@Column(name = "ID", unique = true, nullable = false)
	@SequenceGenerator(name = "SEQ_MESSAGE", sequenceName = "SEQ_MESSAGE", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_MESSAGE")
    private Long id;
	
	@Column(name = "TEXT", nullable = false)
    private String text;
	
	@ManyToOne
	@JoinColumn(name = "CATEGORY", nullable = false)
	private Category category;
	
	@Column(name = "TIME", nullable = false)
	@CreationTimestamp
	private Date date;
	
	@Column(name = "CREATED_BY", nullable = false)
	private String author;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}


	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}
}
