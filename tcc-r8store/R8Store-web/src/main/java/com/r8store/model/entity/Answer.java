package com.r8store.model.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name="answer")
public class Answer extends MasterEntity {

	@ManyToOne
    @JoinColumn(name = "fk_rating")	
	private Rating rating;
	
	@ManyToOne
    @JoinColumn(name = "fk_question")	
	private Question question;
	
	private String awnser;

	public Answer(Long id, UploadedFile file, Rating rating, Question question, String awnser) {
		super(id, file);
		this.rating = rating;
		this.question = question;
		this.awnser = awnser;
	}

	public Answer() {
		super();
	}

	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	public Question getQuestion() {
		return question;
	}

	public void setQuestion(Question question) {
		this.question = question;
	}

	public String getAwnser() {
		return awnser;
	}

	public void setAwnser(String awnser) {
		this.awnser = awnser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((awnser == null) ? 0 : awnser.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Answer other = (Answer) obj;
		if (awnser == null) {
			if (other.awnser != null)
				return false;
		} else if (!awnser.equals(other.awnser))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Answer [rating=" + rating + ", question=" + question + ", awnser=" + awnser + "]";
	}
	
}
