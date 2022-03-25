package exam05;

import java.util.Arrays;

public class PersonList {
	private Person[] pArr = new Person[0];
	
	public PersonList(Person[] data) {
		this.pArr = data;
	}
	//pArr 특정 인덱스 출력
	public Person get(int index) {	// 자료형 -> 참조형
		return this.pArr[index];
	}
	// pArr의 길이 재주는 메소드
	public int length() {
		return this.pArr.length;
	}
	public void add(String name) {
		this.pArr = Arrays.copyOf(this.pArr, length() + 1);
		this.pArr[length() - 1] = new Person(name);
	}
	
	//이름 찾아서 그 이름 수정해주는 메소드
	public void update(String find, String change) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Person data = get(i);
			if(find.equals(data.getName())) {
				idx = i;
				break;
			}
		}
		this.pArr[idx].setName(change);
	}
	// 특정 인덱스에 이름 바꿔주기
	public void update(int index, String name) {
		this.pArr[index].setName(name);
	}
	// 이름 찾아서 지워주기
	public void remove(String name) {
		int idx = -1;
		for(int i = 0; i< length(); i++) {
			Person data = get(i);
			if(name.equals(data.getName())) {
				idx = i;
				break;
			}
		}
		
		int index = 0;
		Person[] temp = new Person[length() -1];	//한 개 제거할 거니까 -1
		for(int i = 0; i < length(); i++) {
			if(idx != i) {
				temp[index++] = this.pArr[i];
			}
		}
		this.pArr = temp;
	}
	
	public void remove(int index) {
		
	}
	
	
}
