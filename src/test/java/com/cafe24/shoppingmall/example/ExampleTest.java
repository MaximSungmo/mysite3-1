package com.cafe24.shoppingmall.example;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.both;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.not;
import static org.hamcrest.Matchers.startsWith;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNotSame;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

public class ExampleTest {

	
	// 테스트케이스(메소드)끼리 공유해야 할 변수가 있으면 static으로 선언.
	private static StringBuilder output = new StringBuilder();
	
	
	@BeforeClass
	public static void setUpBefore() {
		System.out.println("@BeforeClass");
	}
	
	@AfterClass
	public static void cleanUp() {
//		gusetbookDao.deleteAll();
		System.out.println("@AfterClass:" + output);
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
		output.append("A");
	}
	
	@Test
	public void myBTest() {
		System.out.println("@Test:B");
		output.append("B");
	}

	@Test
	public void myCTest() {
		System.out.println("@Test:C");
		output.append("C");
	}

	
//	TEST 하고 싶지 않은 경우에는 @Ignore 을 사용한다. 
	@Ignore
	@Test
	public void ignoreTest() {
		assertTrue(false);
	}
	
	/**
	 * assertXYZ 테스트
	 */
	@Test
	public void testAssert() {
		assertTrue(true);
		assertFalse(false);
		
		assertNull(null);
		assertNotNull(new Object());
		
		assertEquals(1+2, 3);
		assertEquals(new String("hi"), "hi");
		
		assertNotEquals(true, false);
		
		assertSame("hello", "hello");
		// 다른 객체임, equals로 비교하지 않기에 not same이 통과
		assertNotSame("hello",new String("hello"));
		
		// assertThat : is
		assertThat(1+2, is(3));

		// assertThat : not
		assertThat("this is never", is(not(3)));

		// assertThat : allof  모든 조건에 해당하는 정보가 맞아야 한다. 
		assertThat("hello world", allOf(startsWith("hell"), containsString("lo")));
		
		// assertThat : anyOf
		assertThat("Hello world", anyOf(startsWith("heaven"), containsString("lo")));
		
		// assertThat : both
		assertThat("ABC", both(containsString("A")).and(containsString("B")));
	
		// assertThat : either
		assertThat("ABC", either(containsString("A")).or(containsString("B")));
		
		// assertThat : everyItem
		assertThat(Arrays.asList("apple","application","apples"), everyItem(startsWith("ap")));
		
		// assertThat : hasItem
		assertThat(Arrays.asList("apple","application","apples"), hasItem("apple"));

	}
	
	
	@Test
	public void testAssert1() {
		Object[] a = {"apple","application","apples"};
		Object[] b = {"apple","application","apples"};
		
		assertArrayEquals(a,b);
	}
	
}
