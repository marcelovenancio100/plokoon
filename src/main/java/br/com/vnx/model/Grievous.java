package br.com.vnx.model;

import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;

@DynamoDbBean
public class Grievous {
	private String name;
	private String email;
	private Integer batles;
	private Integer victories;
	private Integer defeats;

	public Grievous() {}

	@DynamoDbPartitionKey
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getBatles() {
		return batles;
	}

	public void setBatles(Integer batles) {
		this.batles = batles;
	}

	public Integer getVictories() {
		return victories;
	}

	public void setVictories(Integer victories) {
		this.victories = victories;
	}

	public Integer getDefeats() {
		return defeats;
	}

	public void setDefeats(Integer defeats) {
		this.defeats = defeats;
	}

	@Override
	public String toString() {
		return "Grievous [name=" + name + ", email=" + email + ", batles=" + batles + ", victories=" + victories + ", defeats=" + defeats + "]";
	}
}
