package test;

import javax.annotation.PreDestroy;

import microunit.Test;

public class TestCase2 {

	@Test
	public void method12() {
		System.out.println(" method12");
	}

	@Test
	public void method22() {
		System.out.println(" method22");
	}

	@Test
	public void method32() {
		System.out.println(" method32");
	}
	@PreDestroy
	@Test
	public void method33() {
		System.out.println(" method33pred");
	}
	@PreDestroy
	@Test
	public void method34() {
		System.out.println(" method34pred");
	}
}
