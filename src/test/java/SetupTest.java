import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONException;
import org.junit.Test;

public class SetupTest {

	@Test
	public void testApiConnection() throws JSONException, MalformedURLException, IOException {
		assertTrue(BerryUtil.connectToApi("https://pokeapi.co/api/v2/berry/").toString().contains("count"));
		assertEquals(64, BerryUtil.connectToApi("https://pokeapi.co/api/v2/berry/").get("count"));
	}

	@Test
	public void testGetBerries() throws MalformedURLException, IOException {
		assertEquals(new Berry("enigma", 155, 24).toString(),
				BerryUtil.getBerries("https://pokeapi.co/api/v2/berry/", 64).get(59).toString());
	}
	
	@Test
	public void testGetOptimalBerry() {
		List<Berry> test= new ArrayList<Berry>();
		test.add(new Berry("b1", 140, 8));
		test.add(new Berry("b2", 180, 1));
		test.add(new Berry("b3", 200, 6));
		test.add(new Berry("b4", 360, 5));
		Optional<Berry> toTest = BerryUtil.getOptimalBerry(test);
		assertEquals(toTest, BerryUtil.getOptimalBerry(test));
	}

}



