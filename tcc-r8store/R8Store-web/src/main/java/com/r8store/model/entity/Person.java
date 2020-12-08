package com.r8store.model.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.r8store.enums.Enum_Gender;

@Entity
@Table(name="person")
public class Person extends NaturalPerson {
	
	private static final long serialVersionUID = 1288229268990326618L;
	
	@OneToOne
    @JoinColumn(name = "fk_user")
	private User user;
	
	@OneToOne
    @JoinColumn(name = "fk_address")
	private Address address;
	
	@OneToMany(mappedBy = "person")
	@JsonIgnore
    private List<Rating> rating = new ArrayList<Rating>();
	
	@OneToMany(mappedBy = "person")
    private List<User_Post> userPosts = new ArrayList<User_Post>();
    
    @OneToMany(mappedBy = "person")
    @JsonIgnore
    private List<Point> points = new ArrayList<Point>();

	public Person(String identifier, Date birthday, Enum_Gender gender, User user, Address address, List<Rating> rating,
			List<User_Post> userPosts, List<Point> points) {
		super(identifier, birthday, gender);
		this.user = user;
		this.address = address;
		this.rating = rating;
		this.userPosts = userPosts;
		this.points = points;
	}

	public Person() {
		super();
	}
	
	public Point pointByShop(Long id) {
		System.out.println("aqui");
		if(points != null && points.size() > 0) {
			for(Point p : points) {
				System.out.println("aqui2");
				if(p.getStore().getId() == id) {
					System.out.println("PONTOS: " + p.getPoints());
					return p;
				}
			}
		}		
		return null;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public List<Rating> getRating() {
		return rating;
	}

	public void setRating(List<Rating> rating) {
		this.rating = rating;
	}

	public List<User_Post> getUserPosts() {
		return userPosts;
	}

	public void setUserPosts(List<User_Post> userPosts) {
		this.userPosts = userPosts;
	}
	
	public List<Point> getPoints() {
		return points;
	}

	public void setPoints(List<Point> points) {
		this.points = points;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((points == null) ? 0 : points.hashCode());
		result = prime * result + ((rating == null) ? 0 : rating.hashCode());
		result = prime * result + ((user == null) ? 0 : user.hashCode());
		result = prime * result + ((userPosts == null) ? 0 : userPosts.hashCode());
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
		Person other = (Person) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (points == null) {
			if (other.points != null)
				return false;
		} else if (!points.equals(other.points))
			return false;
		if (rating == null) {
			if (other.rating != null)
				return false;
		} else if (!rating.equals(other.rating))
			return false;
		if (user == null) {
			if (other.user != null)
				return false;
		} else if (!user.equals(other.user))
			return false;
		if (userPosts == null) {
			if (other.userPosts != null)
				return false;
		} else if (!userPosts.equals(other.userPosts))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Person [user=" + user + ", address=" + address + ", rating=" + rating + ", userPosts=" + userPosts
				+ ", points=" + points + "]";
	}

}
