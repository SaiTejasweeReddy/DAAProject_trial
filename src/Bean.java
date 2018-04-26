

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

class Bean {
	private final int id;
	private final int type;
	private final int zipcode;

	public Bean(int id, int type, int zipcode) {
		this.id = id;
		this.type = type;
		this.zipcode = zipcode;
	}

	public static void main(final String[] args) throws Exception {
		final Path path = Paths.get("VehicleRequest.txt");
		final List<Bean> parsed;
		List<Integer> id = new ArrayList<>();
		List<Integer> type = new ArrayList<>();
		List<Integer> zipcode = new ArrayList<>();
		try (final Stream<String> lines = Files.lines(path)) {
			parsed = lines.skip(1).map(line -> line.split("\\s*\\|\\s*")).map(line -> {
				final int x = Integer.parseInt(line[0]);
				id.add(x);
				final int y = Integer.parseInt(line[1]);
				type.add(y);
				final int z = Integer.parseInt(line[2]);
				zipcode.add(z);
				return new Bean(x, y, z);
			}).collect(Collectors.toList());
		}
//		 System.out.println(id);
//		 System.out.println(type);
//		 System.out.println(zipcode);
		SearchVehicle sv = new SearchVehicle(1, 2, 3);
		// VehicleDataMatch vd = new VehicleDataMatch();
		// System.out.println(type.size());
		// for (int i = 0; i < type.size(); i++) {
		// System.out.println(type + "--" + zipcode);
		sv.getVehicle(id, type, zipcode);
		// Dijkstra dj = new Dijkstra();
		// dj.getDistance("64108", "64110");
		// }
	}
}
