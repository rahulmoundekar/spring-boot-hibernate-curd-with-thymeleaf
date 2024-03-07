package com.app.model;

import java.io.Serializable;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "clc_employee")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@EqualsAndHashCode
public class Employee implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 5469337390135469295L;

	@Id
	@Column(name = "id")
	@GeneratedValue(generator = "abc")
	@GenericGenerator(name = "abc", strategy = "org.hibernate.id.UUIDGenerator")
	private String id;

	@NotEmpty(message = "Name can not be empty")
	private String name;

	@Min(value = 18, message = "Age should not be less than 18")
	@Max(value = 60, message = "Age should not be greater than 150")
	private int age;

	@DecimalMin(value = "1.0", message = "Please Enter a valid Amount")
	private double salary;

	@Min(value = 10, message = "Mobile number can be 10 digit")
	private long mobile;

}
