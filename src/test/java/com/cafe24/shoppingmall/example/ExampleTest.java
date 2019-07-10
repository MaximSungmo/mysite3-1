package com.cafe24.shoppingmall.example;

import org.junit.After;
import org.junit.AfterClass;

import org.junit.Before;
import org.junit.BeforeClass;

import org.junit.Test;

public class ExampleTest {

	@BeforeClass
	public void setUpBefore() {
		System.out.println("@BeforeClass");
	}
	
	@AfterClass
	public void tearDownAfter() {
		System.out.println("@AfterClass");
	}
	@Before
	public void setUp() {
		System.out.println("@Before");
	}
	@After
	public void tearDown() {
		System.out.println("@After");
	}
	
	@Test
	public void myATest() {
		System.out.println("@Test:A");
	}
	
	@Test
	public void myBTest() {
		System.out.println("@Test:B");
	}

	@Test
	public void myCTest() {
		System.out.println("@Test:C");
	}

	
	
}
