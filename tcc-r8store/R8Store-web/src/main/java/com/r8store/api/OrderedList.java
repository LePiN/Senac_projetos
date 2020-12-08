package com.r8store.api;

import java.util.ArrayList;
import java.util.List;

import com.r8store.model.entity.Store;

public class OrderedList {
	
	private Store[] stores;
	private int length;
	private double lat;
	private double lng;
	
	public OrderedList() {
		this.stores = new Store[2];
		length = 0;
	}
	
	public void populateList(List<Store> stores) {
		this.stores = new Store[2];
		length = 0;
		
		for(Store s : stores) {
			this.addStore(s);
		}
	}
	
	public void addStore(Store s) {
		if (s.getCoordinateX() == null || s.getCoordinateX().isEmpty() || s.getCoordinateY() == null || s.getCoordinateY().isEmpty()) {
			return;
		}
		
		verificaTamanho();
		if(length == 0) {
			stores[0] = s;
			length++;
			return;
		}
		for(int i = 0; i < stores.length; i++) {
			if(stores[i] == null) {
				stores[i] = s;
				break;
			}
			if(calcularDistancia(s.getCoordinateX(), s.getCoordinateY()) < calcularDistancia(stores[i].getCoordinateX(), stores[i].getCoordinateY())) {
				moverArray(i);
				stores[i] = s;
				break;
			}
		}
		length++;
	}
	
	public double calcularDistancia(String lat, String lng) {
		Double lt = Double.valueOf(lat);
		Double ln = Double.valueOf(lng);
		return distance(lt, ln, this.lat, this.lng, "K");
	}
	
	private void moverArray(int position) {
		for(int i = length - 1; i >= position; i--) {
			stores[i + 1] = stores[i];
		}
	}
	
	private void verificaTamanho() {
		if(length == stores.length - 1) {
			Store[] newArray = new Store[this.stores.length + 10];
			for(int i = 0; i < stores.length; i++) {
				newArray[i] = stores[i];
			}
			stores = newArray;
		}
	}
	
	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == "K") {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}
	
	public List<Store> returnList() {
		List<Store> list = new ArrayList<Store>();
		for(int i = 0; i < length; i++) {
			list.add(stores[i]);
		}
		return list;
	}

	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}

	public Store[] getStores() {
		return stores;
	}

	public void setStores(Store[] stores) {
		this.stores = stores;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}

	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	
}
