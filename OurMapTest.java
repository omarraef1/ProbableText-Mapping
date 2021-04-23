
// Omar R. Gebril 	SID: 23323978 	CSC210

import static org.junit.Assert.*;

import org.junit.Test;

public class OurMapTest {

	// Write many, more @Tests assertions.
	// Code coverage is part of your grade.
	@Test
	public void testPutAndGet() {
		OurMap<Integer, String> map = new OurMap<Integer, String>();
		assertEquals(null, map.put(123, "This is a value"));
		assertEquals(null, map.put(124, "This is a value4"));
		assertEquals("This is a value", map.put(123, "This is a value2"));
		assertEquals("This is a value2", map.put(123, "This is a value"));
		assertEquals("This is a value", map.get(123));
		assertEquals(2, map.size());
		assertEquals(null, map.get(1234));
		assertTrue(map.containsKey(123));
		assertFalse(map.containsKey(1234));
		assertEquals(null, map.remove(1234));
		assertEquals("This is a value", map.remove(123));
		assertEquals(null, map.get(123));
		assertEquals("This is a value4", map.remove(124));
		assertEquals(null, map.remove(879));
		assertEquals(0, map.size());
	}

	@Test
	public void testPutAndGet2() {
		OurMap<Integer, String> map2 = new OurMap<Integer, String>();
		assertEquals(0, map2.size());
	}
}