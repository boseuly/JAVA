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
	//추가메소드
	public void add(String name) {
		this.pArr = Arrays.copyOf(this.pArr, length() + 1);
		this.pArr[length() - 1] = new Person(name);
	}
	
	//이름 찾아서 그 이름 수정해주는 메소드
	public void update(String find, String change) {
		int idx = findIndex(find);
		this.pArr[idx].setName(change);
	}
	
	// 특정 인덱스에 이름 바꿔주기
	public void update(int index, String name) {
		this.pArr[index].setName(name);
	}
	
	// 이름 찾아서 지워주기
	public void remove(String name) {
		int idx = findIndex(name);
		
		int index = 0;
		Person[] temp = new Person[length() -1];	//한 개 제거할 거니까 -1
		for(int i = 0; i < length(); i++) {
			if(idx != i) {		//만약 삭제할 이름이 아니라면
				temp[index++] = this.pArr[i];	//temp에 넣어라
			}
		}
		this.pArr = temp;
	}
	
	public void remove(int index) {
		int idx = 0;
		Person[] temp = new Person[length() - 1];
		for(int i = 0; i < length(); i++) {
			if(index != i) {
				temp[idx++] = this.pArr[i];
			}
		}
		this.pArr =temp;
	}
	
	public int findIndex(String name) {
		int idx = -1;
		for(int i = 0; i < length(); i++) {
			Person data = get(i);
			if(name.equals(data.getName())) {
				idx = i;
				break;
			}
		}
		return idx;
	}
	
}
