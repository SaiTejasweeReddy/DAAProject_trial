

import java.io.*;
import java.util.*;

public class Dijkstra {
	static int g = 7;

	private static final Graph.Edge[] GRAPH = {
			// a = 64108
			// b = 64109
			// c = 64110
			// d = 64111
			// e = 64112
			// f = 64113
			// new Graph.Edge("64108", "64110", 6), new Graph.Edge("64109", "64110", 10),
			// new Graph.Edge("64108", "64113", 1), new Graph.Edge("64109", "64111", 15),
			// new Graph.Edge("64110", "64111", 1), new Graph.Edge("64110", "64111", 5),
			// new Graph.Edge("64111", "64112", 6), new Graph.Edge("64113", "64109", 6),
			// new
			new Graph.Edge("64108", "64119", 6), new Graph.Edge("64109", "64112", 5),
			new Graph.Edge("64119", "64108", 6), new Graph.Edge("64112", "64109", 5),
			
			new Graph.Edge("64110", "64109", 4), new Graph.Edge("64110", "64112", 3),
			new Graph.Edge("64109", "64110", 4), new Graph.Edge("64112", "64110", 3),
			
			new Graph.Edge("64111", "64112", 3), new Graph.Edge("64112", "64108", 4),
			new Graph.Edge("64112", "64111", 3), new Graph.Edge("64108", "64112", 4),
			
			new Graph.Edge("64112", "64119", 12), new Graph.Edge("64113", "64118", 3),
			new Graph.Edge("64119", "64112", 12), new Graph.Edge("64118", "64113", 3),
			
			new Graph.Edge("64113", "64124", 6), new Graph.Edge("64114", "64120", 2),
			new Graph.Edge("64124", "64113", 6), new Graph.Edge("64120", "64114", 2),
			
			new Graph.Edge("64114", "64122", 4), new Graph.Edge("64114", "64127", 7),
			new Graph.Edge("64122", "64114", 4), new Graph.Edge("64127", "64114", 7),
			
			new Graph.Edge("64115", "64108", 4), new Graph.Edge("64116", "64128", 4),
			new Graph.Edge("64108", "64115", 4), new Graph.Edge("64128", "64116", 4),
			
			new Graph.Edge("64116", "64122", 5), new Graph.Edge("64117", "64123", 3),
			new Graph.Edge("64122", "64116", 5), new Graph.Edge("64123", "64117", 3),
			
			new Graph.Edge("64117", "64119", 10), new Graph.Edge("64117", "64124", 2),
			new Graph.Edge("64119", "64117", 10), new Graph.Edge("64124", "64117", 2),
			
			new Graph.Edge("64118", "64123", 6), new Graph.Edge("64119", "64114", 6),
			new Graph.Edge("64123", "64118", 6), new Graph.Edge("64114", "64119", 6),
			
			new Graph.Edge("64119", "64127", 5), new Graph.Edge("64120", "64119", 5),
			new Graph.Edge("64127", "64119", 5), new Graph.Edge("64119", "64120", 5),
			
			new Graph.Edge("64121", "64125", 1), new Graph.Edge("64122", "64123", 8),
			new Graph.Edge("64125", "64121", 1), new Graph.Edge("64123", "64122", 8),
			
			new Graph.Edge("64123", "64120", 1), new Graph.Edge("64124", "64118", 1),
			new Graph.Edge("64120", "64123", 1), new Graph.Edge("64118", "64124", 1),
			
			new Graph.Edge("64125", "64122", 1), new Graph.Edge("64126", "64110", 3),
			new Graph.Edge("64122", "64125", 1), new Graph.Edge("64110", "64126", 3),
			
			new Graph.Edge("64126", "64111", 3), new Graph.Edge("64127", "64128", 6),
			new Graph.Edge("64111", "64126", 3), new Graph.Edge("64128", "64127", 6),
			
			new Graph.Edge("64128", "64114", 5), new Graph.Edge("64128", "64122", 7),
			new Graph.Edge("64114", "64128", 5), new Graph.Edge("64122", "64128", 7),

	};
	private static String START;
	private static String END;

/*	public static void main(String[] args) {

		// int[] data = readFiles("File.txt");
		// // System.out.println(Arrays.toString(data));
		// int[] series = { 4, 2 };
		// series = addElement(series, 3);
		// series = addElement(series, 1);
		// for (int i = 0; i < data.length; i++) {
		// System.out.println(data[i]);
		// }
		Graph g = new Graph(GRAPH);
		START = "64108";
		END = "64109";
		g.dijkstra(START);
		g.printPath(END);
		// g.printAllPaths();
	}*/

	public int getDistance(String START, String END) {
		Graph g = new Graph(GRAPH);
		// this.START = START;
		// this.END = END;
		g.dijkstra(START);
		int distance = g.printPath(END);
		// return this.dist;
		return distance;
	}

	static int[] addElement(int[] a, int e) {
		a = Arrays.copyOf(a, a.length + 1);
		a[a.length - 1] = e;
		return a;
	}

	public static int[] readFiles(String file) {
		try {
			File f = new File(file);
			Scanner s = new Scanner(f);
			int ctr = 0;
			while (s.hasNextInt()) {
				ctr++;
				s.nextInt();
			}
			int[] arr = new int[ctr];
			Scanner s1 = new Scanner(f);
			for (int i = 0; i < arr.length; i++)
				arr[i] = s1.nextInt();
			return arr;
		} catch (Exception e) {
			return null;
		}
	}
}

class Graph {
	private final Map<String, Vertex> graph; // mapping of vertex names to Vertex objects, built from a set of Edges

	/** One edge of the graph (only used by Graph constructor) */
	public static class Edge {
		public final String v1, v2;
		public final int dist;

		public Edge(String v1, String v2, int dist) {
			this.v1 = v1;
			this.v2 = v2;
			this.dist = dist;
		}
	}

	/** One vertex of the graph, complete with mappings to neighbouring vertices */
	public static class Vertex implements Comparable<Vertex> {
		public final String name;
		public int dist = Integer.MAX_VALUE; // MAX_VALUE assumed to be infinity
		public Vertex previous = null;
		public final Map<Vertex, Integer> neighbours = new HashMap<>();

		public Vertex(String name) {
			this.name = name;
		}

		private int printPath() {
			if (this == this.previous) {
				System.out.printf("%s", this.name);
			} else if (this.previous == null) {
				System.out.printf("(There is no Path.. to %s is not available)", this.name);
			} else {
				this.previous.printPath();

				System.out.printf(" -> %s(%d)", this.name, this.dist);
				return this.dist;
			}
			return 0;
		}

		public int compareTo(Vertex other) {
			if (dist == other.dist)
				return name.compareTo(other.name);

			return Integer.compare(dist, other.dist);
		}

		@Override
		public String toString() {
			return "(" + name + ", " + dist + ")";
		}
	}

	/** Builds a graph from a set of edges */
	public Graph(Edge[] edges) {
		graph = new HashMap<>(edges.length);

		// one pass to find all vertices
		for (Edge e : edges) {
			if (!graph.containsKey(e.v1))
				graph.put(e.v1, new Vertex(e.v1));
			if (!graph.containsKey(e.v2))
				graph.put(e.v2, new Vertex(e.v2));
		}

		// another pass to set neighbouring vertices
		for (Edge e : edges) {
			graph.get(e.v1).neighbours.put(graph.get(e.v2), e.dist);
			// graph.get(e.v2).neighbours.put(graph.get(e.v1), e.dist); // also do this for
			// an undirected graph
		}
	}

	/** Runs dijkstra using a specified source vertex */
	public void dijkstra(String startName) {
		if (!graph.containsKey(startName)) {
			System.err.printf("Graph doesn't contain start vertex \"%s\"\n", startName);
			return;
		}
		final Vertex source = graph.get(startName);
		NavigableSet<Vertex> q = new TreeSet<>();

		// set-up vertices
		for (Vertex v : graph.values()) {
			v.previous = v == source ? source : null;
			v.dist = v == source ? 0 : Integer.MAX_VALUE;
			q.add(v);
		}

		dijkstra(q);
	}

	/** Implementation of dijkstra's algorithm using a binary heap. */
	private void dijkstra(final NavigableSet<Vertex> q) {
		Vertex u, v;
		while (!q.isEmpty()) {

			u = q.pollFirst(); // vertex with shortest distance (first iteration will return source)
			if (u.dist == Integer.MAX_VALUE)
				break; // we can ignore u (and any other remaining vertices) since they are unreachable

			// look at distances to each neighbour
			for (Map.Entry<Vertex, Integer> a : u.neighbours.entrySet()) {
				v = a.getKey(); // the neighbour in this iteration

				final int alternateDist = u.dist + a.getValue();
				if (alternateDist < v.dist) { // shorter path to neighbour found
					q.remove(v);
					v.dist = alternateDist;
					v.previous = u;
					q.add(v);
				}
			}
		}
	}

	/** Prints a path from the source to the specified vertex */
	public int printPath(String endName) {
		if (!graph.containsKey(endName)) {
			System.err.printf("Graph doesn't contain end vertex \"%s\"\n", endName);
			return 1;
		}

		int x = graph.get(endName).printPath();
		System.out.println();
		return x;
	}

	/**
	 * Prints the path from the source to every vertex (output order is not
	 * guaranteed)
	 */
	public void printAllPaths() {
		for (Vertex v : graph.values()) {
			v.printPath();
			System.out.println();
		}
	}
}