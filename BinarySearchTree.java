/**
 * @author rbk, sa
 * Binary search tree (starter code)
 **/

// replace package name with your netid
package jrs220002;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;
import java.util.Stack;
import sa.Timer;


public class BinarySearchTree<T extends Comparable<? super T>> implements Iterable<T> {
    static class Entry<T> {
        T element;
        Entry<T> left, right;

        public Entry(T x, Entry<T> left, Entry<T> right) {
            this.element = x;
            this.left = left;
            this.right = right;
        }
    }

    Entry<T> root;
    int size;
    // define stack
    Stack<Entry<T>> stack;

    public BinarySearchTree() {
        root = null;
        size = 0;
    }


    /** TO DO: Is x contained in tree?
     */
    public boolean contains(T x) {
        // find x in tree
        Entry<T> node = root;
        while (node != null){
            int cmp = x.compareTo(node.element); // == 0 found  >0 left <0 right
            if (cmp == 0){
                return true;
            }
            else if (cmp < 0){
                node = node.left;
            }
            else{
                node = node.right;
            }
        }
        return false;
    }


    /** TO DO: Add x to tree. 
     *  If tree contains a node with same key, replace element by x.
     *  Returns true if x is a new element added to tree.
     */

    public boolean add(T x) {
        Entry<T> node = root;
        Entry<T> parent = null;
        while (node != null){
            int cmp = x.compareTo(node.element);
            if (cmp == 0){
                node.element = x;
                return false;
            }
            else if (cmp < 0){
                parent = node;
                node = node.left;
            }
            else{
                parent = node;
                node = node.right;
            }
        }
        Entry<T> newNode = new Entry<T>(x, null, null);
        if (parent == null){
            root = newNode;
        }
        else if (x.compareTo(parent.element) < 0){
            parent.left = newNode;
        }
        else{
            parent.right = newNode;
        }
        size++;
        return true;
    }

    /** TO DO: Remove x from tree. 
     *  Return x if found, otherwise return null
     */
    public T remove(T x) {
        //remove x from tree
        Entry<T> node = root;
        Entry<T> parent = null;
        while (node != null){
            int cmp = x.compareTo(node.element);
            if (cmp == 0){
                break;
            }
            else if (cmp < 0){
                parent = node;
                node = node.left;
            }
            else{
                parent = node;
                node = node.right;
            }
        }
        if (node == null){
            return null;
        }
        T result = node.element;
        if (node.left == null || node.right == null){
            Entry<T> child;
            if (node.left == null){
                child = node.right;
            }
            else{
                child = node.left;
            }
            if (node == root){
                root = child;
            }
            else if (parent.left == node){
                parent.left = child;
            }
            else{
                parent.right = child;
            }
            size--;
        }
        else{
            Entry<T> pred = node.left;
            Entry<T> predParent = node;
            while (pred.right != null){
                predParent = pred;
                pred = pred.right;
            }
            node.element = pred.element;
            if (predParent.right == pred){
                predParent.right = pred.left;
            }
            else{
                predParent.left = pred.left;
            }
            size--;
        }
        return result;

    }


 


// Start of Optional problems

    /** Optional problem : Iterate elements in sorted order of keys
     Solve this problem without creating an array using in-order traversal (toArray()).
     */
    public Iterator<T> iterator() {
        return null;
    }

    // Optional problem
    public T min() {
        return null;
    }

    public T max() {
        return null;
    }

    // Optional problem.  Find largest key that is no bigger than x.  Return null if there is no such key.
    public T floor(T x) {
        return null;
    }

    // Optional problem.  Find smallest key that is no smaller than x.  Return null if there is no such key.
    public T ceiling(T x) {
        return null;
    }

    // Optional problem.  Find predecessor of x.  If x is not in the tree, return floor(x).  Return null if there is no such key.
    public T predecessor(T x) {
        return null;
    }

    // Optional problem.  Find successor of x.  If x is not in the tree, return ceiling(x).  Return null if there is no such key.
    public T successor(T x) {
        return null;
    }

   // Optional: Create an array with the elements using in-order traversal of tree
    public Comparable[] toArray() {
        Comparable[] arr = new Comparable[size];
        /* write code to place elements in array here */
        return arr;
    }
	
// End of Optional problems

    public static void main(String[] args) throws FileNotFoundException {
        BinarySearchTree<Long> bst = new BinarySearchTree<>();
        Scanner sc;
        if (args.length > 0) {
            File file = new File(args[0]);
            sc = new Scanner(file);
        } else {
            sc = new Scanner(System.in);
        }
        String operation = "";
        long operand = 0;
        int modValue = 999983;
        long result = 0;
        // Initialize the timer
        Timer timer = new Timer();

        while (!((operation = sc.next()).equals("End"))) {
            switch (operation) {
                case "Add": {
                    operand = sc.nextInt();
                    if (bst.add(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Remove": {
                    operand = sc.nextInt();
                    if (bst.remove(operand) != null) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
                case "Contains": {
                    operand = sc.nextInt();
                    if (bst.contains(operand)) {
                        result = (result + 1) % modValue;
                    }
                    break;
                }
            }
        }

        // End Time
        timer.end();

        System.out.println(result);
        System.out.println(timer);
    }


    public void printTree() {
        System.out.print("[" + size + "]");
        printTree(root);
        System.out.println();
    }

    // Inorder traversal of tree
    void printTree(Entry<T> node) {
        if (node != null) {
            printTree(node.left);
            System.out.print(" " + node.element);
            printTree(node.right);
        }
    }
}




