
public class Berry {
	
	String name;
	int size;
	int growthTime;

	public Berry(String name, int size, int growthTime) {
		this.name = name;
		this.size = size;
		this.growthTime = growthTime;
	}

	public String getName() {
		return name;
	}

	public int getSize() {
		return size;
	}

	public int getGrowthTime() {
		return growthTime;
	}

	
	@Override
	public String toString() {
		return " {name: " + name + "; size: " + size + "; growth time: "
				+ growthTime + " hours}";
	}

}