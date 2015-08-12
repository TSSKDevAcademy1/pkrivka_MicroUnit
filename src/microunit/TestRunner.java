package microunit;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class TestRunner {
	Class<? extends Annotation> annotation;

	public void processTestSuite(String filename) throws Exception {
		try (BufferedReader r = new BufferedReader(new FileReader(filename))) {
			String line;
			while ((line = r.readLine()) != null) {
				line = line.trim();
				if (!"".equals(line)) {
					runTests(line);
				}
			}
		}
	}

	public void runTests(String className) throws Exception {
		Class<?> clazz = Class.forName(className);
		Object testCase = clazz.newInstance();
		long start = System.currentTimeMillis();
		int success = 0;
		int count = 0;
		annotation = PostConstruct.class;
		runMethods(clazz, testCase);
		for (Method method : clazz.getMethods()) {
			// TODO: over ci metoda je v tvare public void XXX()

			if (method.isAnnotationPresent(Test.class)) {
				System.out.printf("Test: " + method.getName());
				count++;
				try {
					method.invoke(testCase);
					System.out.println("Test successfull");
					success++;

				} catch (InvocationTargetException e) {
					e.getMessage();
					System.out.println(e);
					e.printStackTrace();

				} catch (Exception e) {
					e.getMessage();
					System.out.println(e);
					e.printStackTrace();
				}

			}

		}
		annotation = PreDestroy.class;
		runMethods(clazz, testCase);
		System.out.println("Cas trvania testu: " + (System.currentTimeMillis() - start));
		System.out.println("Pocet uspesnych testov je: " + success + "/" + count);
	}

	void runMethods(Class<?> clazz, Object testCase) {
		for (Method method : clazz.getMethods()) {
			if (method.isAnnotationPresent(annotation)) {
				try {
					method.invoke(testCase);
				} catch (Exception e) {
					e.getMessage();
					System.out.println(e);
					e.printStackTrace();
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		TestRunner runner = new TestRunner();

		runner.processTestSuite("test");

	}

}
