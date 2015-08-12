package test;

import static microunit.Assert.assertEquals;

import javax.annotation.PostConstruct;

import microunit.Test;

public class TestCase1 {

	@Test(Expected=Exception.class)
	public void method1() {
		System.out.println(" method1");
//		assertEquals(2, 2);
		assertEquals(3, 2);
	}
	
	@Test
	public void method2() {
		System.out.println(" method2");
	}

	@Test
	public void method3() {
		System.out.println(" method3");
	}

	@Test
	public void method4() {
		System.out.println(" method4");
	}
	@PostConstruct
	@Test
	public void methodpost1() {
		System.out.println("methodpost1");
	}
	@PostConstruct
	@Test
	public void methodpost2() {
		System.out.println("methodpost2");
	}

}
