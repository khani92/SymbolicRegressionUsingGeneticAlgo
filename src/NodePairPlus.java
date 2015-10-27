public class NodePairPlus {

	/** Parent node at the clip point */
	public Node parent;

	/** The node below the clip point. */
	public Node child;

	/**
	 * Counter to store number of nodes so far tested in the process of
	 * searching for the clip point.
	 */
	int counter;
}
