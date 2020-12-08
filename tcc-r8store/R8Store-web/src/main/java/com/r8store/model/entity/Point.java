package com.r8store.model.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.primefaces.model.UploadedFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="point")
public class Point extends MasterEntity {
	
	@ManyToOne
    @JoinColumn(name = "fk_store")
	private Store store;
	
	@ManyToOne
    @JoinColumn(name = "fk_person")
	private Person person;
	
	@OneToMany(mappedBy = "point")
	@JsonIgnore
	private List<Game> games;
	
	private Double points;
	
	private Double accumulated;

	public Point() {
		super();
		this.accumulated = 0d;
	}

	public Point(Long id, UploadedFile file, Store store, Person person, Double points, List<Game> games, Double accumulated) {
		super(id, file);
		this.store = store;
		this.person = person;
		this.points = points;
		this.games = games;
		this.accumulated = accumulated;
	}

	public Store getStore() {
		return store;
	}

	public void setStore(Store store) {
		this.store = store;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public Double getPoints() {
		return points;
	}

	public void setPoints(Double points) {
		this.points = points;
	}

	public List<Game> getGames() {
		return games;
	}

	public void setGames(List<Game> games) {
		this.games = games;
	}

	public Double getAccumulated() {
		return accumulated;
	}

	public void setAccumulated(Double accumulated) {
		this.accumulated = accumulated;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((person == null) ? 0 : person.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((store == null) ? 0 : store.hashCode());
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
		Point other = (Point) obj;
		if (person == null) {
			if (other.person != null)
				return false;
		} else if (!person.equals(other.person))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (store == null) {
			if (other.store != null)
				return false;
		} else if (!store.equals(other.store))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Point [store=" + store + ", person=" + person + ", points=" + points + "]";
	}
	
}
