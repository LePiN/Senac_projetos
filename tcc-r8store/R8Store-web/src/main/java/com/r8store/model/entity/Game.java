package com.r8store.model.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.r8store.enums.Enum_Game;

@Entity
@Table(name="game")
public class Game extends MasterEntity {
	
	private Long id;
	
	@ManyToOne
    @JoinColumn(name = "fk_point")
	private Point point;
	
	@Enumerated(EnumType.STRING)
	private Enum_Game type;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date date;
	
	private double points;
	
	public Game() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}

	public Enum_Game getType() {
		return type;
	}

	public void setType(Enum_Game type) {
		this.type = type;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getPoints() {
		return points;
	}

	public void setPoints(double points) {
		this.points = points;
	}
	
}
