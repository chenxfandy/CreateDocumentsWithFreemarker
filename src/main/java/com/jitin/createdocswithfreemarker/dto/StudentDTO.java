package com.jitin.createdocswithfreemarker.dto;

public class StudentDTO {
	private String college;
	private Integer rollNumber;
	private String name;
	private Double percentage;
	private Boolean isPass;
	private AddressDTO address;

	public StudentDTO() {

	}

	public StudentDTO(String college, Integer rollNumber, String name, Double percentage, Boolean isPass,
			AddressDTO address) {
		super();
		this.college = college;
		this.rollNumber = rollNumber;
		this.name = name;
		this.percentage = percentage;
		this.isPass = isPass;
		this.address = address;
	}

	public String getCollege() {
		return college;
	}

	public void setCollege(String college) {
		this.college = college;
	}

	public Integer getRollNumber() {
		return rollNumber;
	}

	public void setRollNumber(Integer rollNumber) {
		this.rollNumber = rollNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Boolean getIsPass() {
		return isPass;
	}

	public void setIsPass(Boolean isPass) {
		this.isPass = isPass;
	}

	public AddressDTO getAddress() {
		return address;
	}

	public void setAddress(AddressDTO address) {
		this.address = address;
	}

}
