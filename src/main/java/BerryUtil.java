import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

public class BerryUtil {
	
	public static List<Berry> getBerries(String url, int amountOfBerries)
			throws MalformedURLException, IOException {
		List<Berry> berries = new ArrayList<Berry>();

		int i = 1;
		while (i <= amountOfBerries) {
			String URL = url + i + "/";
			JSONObject berryAtr = connectToApi(URL);
			String name = (String) berryAtr.get("name");
			int size = (int) berryAtr.get("size");
			int growthTime = (int) berryAtr.get("growth_time");
			berries.add(new Berry(name, size, growthTime));
			i++;
		}
		return berries;
	}
	
public static Optional<Berry> getOptimalBerry(List<Berry> berries) {
		
		List<Berry> sortedByTime = berries.stream().sorted((b1, b2) -> b1.growthTime - b2.growthTime)
				.collect(Collectors.toList());
		int minTime = sortedByTime.get(0).growthTime;

		List<Berry> sortedByMinTime = sortedByTime.stream().filter(t -> t.getGrowthTime() == minTime)
				.collect(Collectors.toList());

		Optional<Berry> berryByMaxGrowth = sortedByMinTime.stream().max(Comparator.comparing(Berry::getSize));
		return berryByMaxGrowth;
		
	}

	public static JSONObject connectToApi(String url) throws MalformedURLException, IOException {
		URL req = new URL(url);
		InputStream openStream = req.openStream();
		String res = IOUtils.toString(openStream);
		JSONObject root = new JSONObject(res);
		return root;
	}
	
	public static void main(String[] args) throws IOException {

		String url = "https://pokeapi.co/api/v2/berry/";

		JSONObject json = connectToApi(url);
		int count = (int) json.get("count");

		List<Berry> berries = getBerries(url, count);

		Optional<Berry> optimalBerry = getOptimalBerry(berries);

		System.out.println("Q: Which berry grows the most in the least amount of time? " 
							+ "\n" + "A:" + optimalBerry.get());
	}

}