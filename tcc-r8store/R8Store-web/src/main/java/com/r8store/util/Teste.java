package com.r8store.util;

import java.util.List;

import com.r8store.api.OrderedList;

import com.r8store.model.entity.Store;

public class Teste {

	public static void main(String[] args) {
		OrderedList l = new OrderedList();
		l.setLat(-27.6002875);
		l.setLng(-48.5890942);
		
		Store s1 = new Store();
		s1.setCoordinateX("-27.6019706");
		s1.setCoordinateY("-48.5906699");
		
		Store s2 = new Store();
		s2.setCoordinateX("-27.5747016");
		s2.setCoordinateY("-48.6070735");
		
		Store s3 = new Store();
		s3.setCoordinateX("-27.5499919");
		s3.setCoordinateY("-48.6279999");
		
		Store s4 = new Store();
		s4.setCoordinateX("-27.5129373");
		s4.setCoordinateY("-48.6571141");
		
		l.addStore(s2);
		l.addStore(s4);
		l.addStore(s3);
		l.addStore(s1);
		
		List<Store> stores = l.returnList();
		for(Store s : stores) {
			System.out.println(s.getCoordinateX());
		}
	}

}
