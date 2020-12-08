package com.r8store.model.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

@Entity
@Table(name="question")
public class Question extends MasterEntity {
	
	private static final long serialVersionUID = 6333264618627905459L;

	@ManyToOne
    @JoinColumn(name = "fk_form")
	private Form form;
	
	@OneToMany(mappedBy = "question")
    private List<Answer> answers = new ArrayList<Answer>();
	
	private String question;
	
	private int weight;

	public Question(Long id, UploadedFile file, Form form, List<Answer> answers, String question, int weight) {
		super(id, file);
		this.form = form;
		this.answers = answers;
		this.question = question;
		this.weight = weight;
	}

	public Question() {
		super();
	}

	public Form getForm() {
		return form;
	}

	public void setForm(Form form) {
		this.form = form;
	}

	public List<Answer> getAnswers() {
		return answers;
	}

	public void setAnswers(List<Answer> answers) {
		this.answers = answers;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((answers == null) ? 0 : answers.hashCode());
		result = prime * result + ((form == null) ? 0 : form.hashCode());
		result = prime * result + ((question == null) ? 0 : question.hashCode());
		result = prime * result + weight;
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
		Question other = (Question) obj;
		if (answers == null) {
			if (other.answers != null)
				return false;
		} else if (!answers.equals(other.answers))
			return false;
		if (form == null) {
			if (other.form != null)
				return false;
		} else if (!form.equals(other.form))
			return false;
		if (question == null) {
			if (other.question != null)
				return false;
		} else if (!question.equals(other.question))
			return false;
		if (weight != other.weight)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Question [form=" + form + ", answers=" + answers + ", question=" + question + ", weight=" + weight
				+ "]";
	}
	
}
