package emilie.algorithms;

import java.util.*;

/**
 * Created by lijiayu on 11/13/16.
 * Assume all nodes in BST have unique value
 */
public class BST {
    private Node root;

    private class Node {
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    public BST() {
        this.root = null;
    }

    public BST(int[] array) {
        Arrays.sort(array);
        this.root = constructBST(array, 0, array.length - 1);
    }

    public void insert(int val) {
        root = insert(root, val);
    }

    public void delete(int val) {
        root = delete(root, val);
    }


    private Node constructBST(int[] array, int lo, int hi) {
        if (lo > hi)
            return null;
        else if (lo == hi) {
            return new Node(array[lo]);
        }
        else {
            int mid = lo + (hi - lo) / 2;
            Node node = new Node(array[mid]);
            node.left = constructBST(array, lo, mid - 1);
            node.right = constructBST(array, mid + 1, hi);
            return node;
        }
    }

    private Node insert(Node node, int val) {
        if (node == null) return new Node(val);
        if (val < node.val) node.left = insert(node.left, val);
        else if (val == node.val) node.right = insert(node.right, val);
        else node.val = val;
        return node;
    }

    private Node delete(Node node, int val) {
        if (node == null) return root;
        if (val < node.val) node.left = delete(node.left, val);
        else if (val > node.val) node.right = delete(node.right, val);
        else {
            if (node.left == null) return node.right;
            else if (node.right == null) return node.left;
            else {
                Node rightMin = getMin(node.right);
                rightMin.right = deleteMin(node.right);
                rightMin.left = node.left;
                node = rightMin;
            }
        }
        return node;
    }

    public int getMin() {
        Node n = getMin(root);
        if (n == null) throw new NoSuchElementException("The tree is empty");
        return n.val;
    }

    private Node getMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node;
        else return getMin(node.left);
    }

    public Node deleteMin(Node node) {
        if (node == null) return null;
        if (node.left == null) return node.right;
        node.left = deleteMin(node.left);
        return node;
    }

    public static void main(String[] args) {
        int[] nums = {3,2,5,1,4};
        BST bst = new BST(nums);
        System.out.println(bst.getMin());
        bst.delete(1);
        System.out.println(bst.getMin());
        bst.insert(9);
        bst.insert(-2);
        bst.insert(12);
        System.out.println(bst.getMin());

    }
}
