package datastructures;

public class UnionFind {

    /**
     * Number of elements in union find
     */
    private int size;

    /**
     * Array to keep track of the size of each component
     */
    private int[] sz;

    /**
     * id[i] points to the parent of i,
     * id[i] = i, then i is a root node
     */
    private int[] id;

    /**
     * The number of components
     */
    private int numOfComponents;

    public UnionFind(int size) {
        if (size <= 0) throw new IllegalArgumentException("Negative size not accepted");

        this.size = numOfComponents = size;
        sz = new int[size];
        id = new int[size];

        for (int i = 0; i < size; i++) {
            id[i] = i; // link to itself (self root)
            sz[i] = 1; // each component is originally of size 1
        }
    }

    /**
     * Which component p belongs to takes amortized constant time,
     * https://stackoverflow.com/questions/200384/constant-amortized-time
     *
     * @param p
     * @return the root of the component/set
     */
    public int find(int p) {

        // find the root of the component or set
        int root = p;
        while (root != id[root]) {
            root = id[root];
        }

        // path compression all nodes point to root,
        // it's what allows for amortized time
        while (p != root) {
            int next = id[p];
            id[p] = root;
            p = next;
        }
        return root;
    }

    /**
     * @return the size of this union find / disjoint set
     */
    public int size() {
        return size;
    }

    /**
     * @param p
     * @return the size of the component p belongs to
     */
    public int componentSize(int p) {
        return sz[find(p)];
    }

    /**
     * @param p
     * @param q
     * @return true if p and q are in the same component
     */
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    /**
     * @return the number of remaining components/sets
     */
    public int numOfRemainingComponents() {
        return numOfComponents;
    }

    /**
     * unify the components/sets that p and q belong to
     *
     * @param p
     * @param q
     */
    public void unify(int p, int q) {

        int root1 = find(p);
        int root2 = find(q);

        // these elements are in the same group
        if (root1 == root2) return;

        // merge the smaller component to the bigger one
        if (sz[root1] < sz[root2]) {
            sz[root2] += sz[root1];
            id[root1] = root2;
        } else {
            sz[root1] += sz[root2];
            id[root2] = root1;
        }

        // since we unify components the overall
        // number of components decrease by one
        numOfComponents--;
    }

}
