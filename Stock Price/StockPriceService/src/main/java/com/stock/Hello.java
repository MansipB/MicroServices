package com.stock;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Student {
	private String name;
	private float percentage;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPercentage() {
		return percentage;
	}

	public void setPercentage(float percentage) {
		this.percentage = percentage;
	}

	public Student(String name, float percentage) {
		super();
		this.name = name;
		this.percentage = percentage;
	}

	@Override
	public String toString() {
		return "{" + name + "," + percentage + "}";
	}

}

class Sorting implements Comparator<Student> {

	@Override
	public int compare(Student stud1, Student stud2) {
		return stud1.getName().compareTo(stud2.getName());
	}

}

class School {
	ArrayList<Student> studentList;

	public ArrayList<Student> sortByName() {
		Collections.sort(this.studentList, new Sorting());
		return studentList;

	}

	public double getAvgPercentage() {
		double average = 0;
		for (int i = 0; i < studentList.size(); i++) {
			average += studentList.get(i).getPercentage();
		}
		return average /= studentList.size();
	}
}

public class Hello {

	public static void main(String[] args) {
		ArrayList<Student> list = new ArrayList<>();
		list.add(new Student("Steve", (float) 56.3));
		list.add(new Student("Bob", (float) 67.3));
		list.add(new Student("Alice", (float) 98.4));
		list.add(new Student("Mark", 40));
		School obj = new School();
		obj.studentList = list;
		ArrayList<Student> sortedStudents = obj.sortByName();
		System.out.println(sortedStudents.toString());
		System.out.println(String.format("%.1f", obj.getAvgPercentage()));

	}

}
