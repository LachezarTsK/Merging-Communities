import java.util.Scanner;

public class Solution {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		int numberOfPeople = scanner.nextInt();
		int numberOfQueries = scanner.nextInt();
		DisjointUnionSets disjointUnionSet = new DisjointUnionSets(numberOfPeople);

		for (int i = 0; i < numberOfQueries; i++) {
			switch (scanner.next()) {
			case "Q":
				int person = scanner.nextInt();
				int rootOfSet = disjointUnionSet.findRootOfSet(person);
				System.out.println(disjointUnionSet.getSizeOfSet(rootOfSet));
				break;
			case "M":
				int personOne = scanner.nextInt();
				int personTwo = scanner.nextInt();
				disjointUnionSet.createUnionBetweenTwoSets(personOne, personTwo);
				break;
			default:
				System.out.println("incorrect input!");
				return;
			}
		}
		scanner.close();
	}
}

class DisjointUnionSets {
	private int numberOfElements;
	private int[] parent;
	private int[] sizeOfSets;

	public DisjointUnionSets(int numberOfPeople) {
		this.numberOfElements = numberOfPeople;
		this.parent = new int[numberOfPeople + 1];
		this.sizeOfSets = new int[numberOfPeople + 1];
		initializeSets();
	}

	private void initializeSets() {
		for (int i = 1; i <= numberOfElements; i++) {
			parent[i] = i;
			sizeOfSets[i] = 1;
		}
	}

	public int findRootOfSet(int x) {
		if (parent[x] != x) {
			parent[x] = findRootOfSet(parent[x]);
		}
		return parent[x];
	}

	public void createUnionBetweenTwoSets(int x, int y) {
		int xRoot = findRootOfSet(x);
		int yRoot = findRootOfSet(y);

		if (xRoot == yRoot) {
			return;
		}

		if (sizeOfSets[xRoot] < sizeOfSets[yRoot]) {
			parent[xRoot] = yRoot;
			sizeOfSets[yRoot] += sizeOfSets[xRoot];
		} else {
			parent[yRoot] = xRoot;
			sizeOfSets[xRoot] += sizeOfSets[yRoot];
		}
	}

	public int getSizeOfSet(int rootOfSet) {
		return sizeOfSets[rootOfSet];
	}
}
