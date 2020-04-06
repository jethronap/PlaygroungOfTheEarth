package datastructures;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Stack;
import java.util.Queue;

public class BinarySearchTree<T extends Comparable<T>> {

    /**
     * The number of nodes
     */
    private int numOfNodes = 0;

    /**
     * This is a rooted tree so we want to keep track of the root
     */
    private Node root = null;

    /**
     * Internal class that keeps node references
     * and the actual node data.
     */
    private class Node {
        T data;
        Node left;
        Node right;

        public Node(T elem, Node left, Node right) {
            this.data = elem;
            this.left = left;
            this.right = right;
        }
    }


    /**
     * @return the number of nodes
     */
    public int size() {
        return numOfNodes;
    }


    /**
     * @return true is BST is empty.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Adds an element.
     *
     * @param element
     * @return true if element is inserted successfully
     */
    public boolean add(T element) {

        // check if element already exists; if yes, ignore it. this bst has unique values.
        if (contains(element)) {
            return false;

            // otherwise add it to the bst
        } else {
            root = add(root, element);
            numOfNodes++;
            return true;

        }
    }

    /**
     * Recursively adds a value to the bst
     *
     * @param node
     * @param element
     * @return a node
     */
    private Node add(Node node, T element) {

        // base case: find a leaf node:
        if (node == null) {
            node = new Node(element, null, null);
        } else {
            // place lower elements in the left subtree
            if (element.compareTo(node.data) < 0) {
                node.left = add(node.left, element);
            } else {
                node.right = add(node.right, element);
            }
        }
        return node;
    }


    /**
     * Removes an element
     *
     * @param element
     * @return true if element removed successfully
     */
    public boolean remove(T element) {
        // make sure the node exists
        if (contains(element)) {
            root = remove(root, element);
            numOfNodes--;
            return true;
        }
        return false;
    }

    /**
     * Recursively remove a node
     *
     * @param node
     * @param element
     * @return
     */
    private Node remove(Node node, T element) {

        if (node == null) return null;

        int cmp = element.compareTo(node.data);

        // dig into the left subtree, the value we're looking for
        // is smaller than the current value
        if (cmp < 0) {
            node.left = remove(node.left, element);

            // otherwise look into the right subtree, the value we're after
            // is greater than the current one
        } else if (cmp > 0) {
            node.right = remove(node.right, element);

            // found the node we wish to remove
        } else {
            // this is the case of a right subtree or no subtree at all.
            // just swap  the node we want to remove with its right child

            if (node.left == null) {
                Node rightChild = node.right;

                node.data = null;
                node = null;

                return rightChild;

                // this is the case of a left subtree or no subtree at all.
                // just swap  the node we want to remove with its left child
            } else if (node.right == null) {
                Node leftChild = node.left;

                node.data = null;
                node = null;

                return leftChild;

                // When removing a node from a binary tree with two links the
                // successor of the node being removed can either be the largest
                // value in the left subtree or the smallest value in the right
                // subtree. In this implementation the smallest value in the right
                // subtree which can be found by traversing as far left as possible
                // in the right subtree.
            } else {
                // the leftmost node in the  subtree
                Node tmp = findMin(node.right);

                // swap the data
                node.data = tmp.data;

                // Go into the right subtree and remove the leftmost node we
                // found and swapped data with. This prevents us from having
                // two nodes in our tree with the same value.
                node.right = remove(node.right, element);
            }
        }
        return node;
    }

    /**
     * Helper method to find the leftmost node
     * with the smallest value
     *
     * @param node
     * @return
     */
    private Node findMin(Node node) {
        while (node.left != null) node = node.left;
        return node;
    }

    /**
     * Helper method to find the rightmost node
     * with the highest value
     *
     * @param node
     * @return
     */
    private Node findMax(Node node) {
        while (node.right != null) node = node.right;
        return node;
    }

    /**
     * @param elem
     * @return true if element is in tree
     */
    public boolean contains(T elem) {
        return contains(root, elem);
    }

    /**
     * Recursively check if element
     * exists in bst
     *
     * @param node
     * @param element
     * @return
     */
    private boolean contains(Node node, T element) {

        // base case: reached bottom value not found
        if (node == null) return false;

        int cmp = element.compareTo(node.data);

        // dig into the left subtree, the value we're looking for
        // is smaller than the current value
        if (cmp < 0) return contains(node.left, element);

            // otherwise look into the right subtree, the value we're after
            // is greater than the current one
        else if (cmp > 0) return contains(node.right, element);

            // the element is found
        else return true;
    }

    /**
     * Computes the height of the tree, O(n)
     *
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * Recursively compute the height of the tree.
     *
     * @param node
     * @return
     */
    private int height(Node node) {
        if (node == null) return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    /**
     * Tree traversal: preorder, inorder, postorder and levelorder.
     *
     * @param order
     * @return an Iterator for a given TreeTraversalOrder.
     */
    public java.util.Iterator<T> traverse(TreeTraversalOrder order) {
        switch (order) {
            case PRE_ORDER:
                return preOrderTraversal();
            case IN_ORDER:
                return inOrderTraversal();
            case POST_ORDER:
                return postOrderTraversal();
            case LEVEL_ORDER:
                return levelOrderTraversal();
            default:
                return null;
        }
    }

    /**
     * https://github.com/williamfiset/Algorithms/blob/master/src/main/java/com/williamfiset/algorithms/datastructures/binarysearchtree/BinarySearchTree.java
     *
     * @return as iterator to traverse the tree in pre order
     */
    private Iterator<T> preOrderTraversal() {
        final int expectedNumOfNodes = numOfNodes;
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                Node node = stack.pop();
                if (node.right != null) stack.push(node.right);
                if (node.left != null) stack.push(node.left);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * @return as iterator to traverse the tree in order
     */
    private Iterator<T> inOrderTraversal() {

        final int expectedNumOfNodes = numOfNodes;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            Node trav = root;

            @Override
            public boolean hasNext() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {

                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();

                // Dig left
                while (trav != null && trav.left != null) {
                    stack.push(trav.left);
                    trav = trav.left;
                }

                Node node = stack.pop();

                // Try moving down right once
                if (node.right != null) {
                    stack.push(node.right);
                    trav = node.right;
                }

                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * @return as iterator to traverse the tree post order
     */
    private Iterator<T> postOrderTraversal() {
        final int expectedNumOfNodes = numOfNodes;
        final Stack<Node> stack1 = new Stack<>();
        final Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                return stack2.pop().data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    /**
     * @return as iterator to traverse the tree level order
     */
    private Iterator<T> levelOrderTraversal() {
        final int expectedNumOfNodes = numOfNodes;
        final Queue<Node> queue = new LinkedList<>();
        queue.offer(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNumOfNodes != numOfNodes) throw new ConcurrentModificationException();
                Node node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
                return node.data;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }
}
