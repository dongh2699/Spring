package com.test.di08;

import java.util.Map;
import java.util.Set;

public class MapTest {

			private Map<Integer, String> map;

			public Map<Integer, String> getMap() {
				return map;
			}

			public void setMap(Map<Integer, String> map) {
				this.map = map;
			}
			
			public void print() {
					Set<Integer> set =  map.keySet(); // keySet() : Map에 있는 키를 다 가져오는 메서드
					for(Integer k : set) {
						System.out.println(k + " : " + map.get(k));
					}
			}
	
}
