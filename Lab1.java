/** 
 * Lab Assignment 1
 * By: Shelby Jorgensen
 * CS302
 */

package LabAssignment1;

import java.io.*;
import java.util.*;

public class LabAssignment1 {
    /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Iterative Solution
     */
    private static boolean problem1Iterative(Node t1, Node t2) {
    	Stack<Node> s1 = new Stack<>();
    	Stack<Node> s2 = new Stack<>();
    	ArrayList<Integer> val1 = new ArrayList<Integer>();
    	Stack<Integer> val2 = new Stack<Integer>();
        Node temp1 = t1;
        Node temp2 = t2;
        
        // Traverse tree 1, add each values into a stack
        while(!s1.isEmpty() || temp1 != null) {
        	
        	while(temp1 != null) {
        		s1.push(temp1);
        		temp1 = temp1.left;
        	}
        	
        	temp1 = s1.pop();
        	// Add value of tree 1 into an array list
        	val1.add(temp1.key);
        	temp1 = temp1.right;
        }
        
        // Traverse tree 2, add each values into a array 
        while(!s2.isEmpty() || temp2 != null) {
        	
        	while(temp2 != null) {
        		s2.push(temp2);
        		temp2 = temp2.left;
        	}
        	
        	temp2 = s2.pop();
        	// Add value of tree 2 into a stack
        	val2.push(temp2.key);
        	temp2 = temp2.right;
        }
        
        // Compare each value from the stack and array, if the array doesn't contain a value from the stack, return false
        while(!val2.isEmpty()) {
        	int tempVal = val2.pop();
        	if(!val1.contains(tempVal)) {
        		return false;
        	}
        }
        
        // If all elements in the stack and array match, return true
        return true;
    }
    
    
    
     /**
     *  Problem 1: Determine if two given binary search trees store the same numbers.
        Recursive Solution
     */
    private static boolean problem1Recursive(Node t1, Node t2) {
    	// Base case, when tree 1 reaches null, returns true
    	if(t1 == null) {
    		return true;
    	}
    	
    	// traverse through the first tree, checking each value in the tree with the values in tree 2 using the find function
    	return problem1Recursive(t1.left, t2) && problem1Recursive(t1.right, t2) && find(t2, t1.key);
    }
    
    
    /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Iterative Solution
     */
    private static int problem2Iterative(Node root, int min, int max) {
        Stack<Node> s = new Stack<>();
        Node temp = root;
        int sum = 0;
        
        // Traverse the tree using a stack
        while(!s.isEmpty() || temp != null) {
        	
        	while(temp != null) {
        		s.push(temp);
        		temp = temp.left;
        	}
        	
        	temp = s.pop();
        	// Add the value of each temp node into the sum, if within the desired range
        	if((temp.key >= min) && (temp.key <= max)) {
        		sum += temp.key;
        	}
        	
        	temp = temp.right;
        }
        
        // Return the sum of all nodes at the end of the traversal
        return sum;
    }
    
    
    
     /**
     *  Problem 2: Determine the sum of all node's keys between min and max (inclusive).
        Recursive Solution
     */
    private static int problem2Recursive(Node root, int min, int max) {
    	// If root is null, return 0
    	if(root == null) {
    		return 0;
    	}
    	
    	// If the root is within the value, return the key value, along with matching values in the right and left tree
    	if(root.key <= max && root.key >= min) {
    		return root.key + problem2Recursive(root.right, min, max) + problem2Recursive(root.left, min, max);
    	} 
    	// If the root isn't within the desired range, only return the left and right tree
    	else {
    		return problem2Recursive(root.right, min, max) + problem2Recursive(root.left, min, max);
    	}
    }
    // ---------------------------------------------------------------------
    // BELOW IS THE PROVIDED CODE FOR THIS LAB
    // Do not change any of the code below!
    private static class Node
    {
        public Node left = null;
        public Node right = null;
        public int key;
        public Node(int key)
        {
            this.key = key;
        }
    }
    private static void insert(Node root, int key)
    {
        if (root == null)
        {
            root = new Node(key);
            return;
        }
        for (Node node = root;;)
        {
            if (key < node.key)
            {
                if (node.left == null)
                {
                    node.left = new Node(key);
                }
                node = node.left;
            }
            else if (key > node.key)
            {
                if (node.right == null)
                {
                    node.right = new Node(key);
                }
                node = node.right;
            }
            else // key = node.key
            {
                // Nothing to do, because no value to update.
                break;
            }
        }
    }
    
    
    private static boolean find(Node root, int key){
      
      if(root == null)
      {
         return false;
      }
    
      if(root.key == key)
      {
           return true;
      }
      
      if(key < root.key)
      {
         return find(root.left, key);
      }
      else
      {
         return find(root.right, key);
      }
    
    }
    
    private static int[] getInOrder(Node root)
    {
        if (root == null) return new int[] { };
        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();
        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;
                node = stack.pop();
                orderList.add(node.key);
                node = node.right;
            }
            else
            {
                stack.push(node);
                node = node.left;
            }
        }
        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }
        return order;
    }
    private static int[] getPreOrder(Node root)
    {
        if (root == null) return new int[] { };
        Stack<Node> stack = new Stack<Node>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();
        for (Node node = root;;)
        {
            if (node == null)
            {
                if (stack.empty()) break;
                node = stack.pop();
                node = node.right;
            }
            else
            {
                orderList.add(node.key);
                stack.push(node);
                node = node.left;
            }
        }
        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }
        return order;
    }
    private static int[] getPostOrder(Node root)
    {
        if (root == null) return new int[] { };
        Stack<Node> stack = new Stack<Node>();
        Stack<Integer> stackCtr = new Stack<Integer>();
        ArrayList<Integer> orderList = new ArrayList<Integer>();
        stack.push(root);
        stackCtr.push(0);
        while (!stack.empty())
        {
            int ctr = stackCtr.pop();
            Node node = stack.peek();
            if (ctr == 0)
            {
                // First visit.
                stackCtr.push(1);
                if (node.left != null)
                {
                    stack.push(node.left);
                    stackCtr.push(0);
                }
            }
            else if (ctr == 1)
            {
                // Second visit.
                // Left subtree done.
                stackCtr.push(2);
                if (node.right != null)
                {
                    stack.push(node.right);
                    stackCtr.push(0);
                }
            }
            else // ctr >= 2
            {
                // Third visit.
                // Right subtree done.
                stack.pop();
                orderList.add(node.key);
            }
        }
        int[] order = new int[orderList.size()];
        for (int i = 0; i < order.length; i++)
        {
            order[i] = orderList.get(i);
        }
        return order;
    }
    // ---------------------------------------------------------------------
    private static final int LabNo = 1;
    private static final String classNum = "CS 302";
    private static final Random rng = new Random(654321);
    public static void main(String args[])
    {
        System.out.println(classNum + " -- Lab " + LabNo);
        testProblems(1, 1);
        testProblems(1, 2);
        testProblems(2, 1);
        testProblems(2, 2);
    }
    private static boolean testProblem1(int[][] testCase, int style)
    {
        int[] tree1 = testCase[0];
        int[] tree2 = testCase[1];
        boolean solution = testCase[2][0] == 1;
        Node root1 = new Node(tree1[0]);
        Node root2 = new Node(tree2[0]);
        for (int i = 1; i < tree1.length; i++)
        {
            insert(root1, tree1[i]);
            insert(root2, tree2[i]);
        }
        
        boolean answer;
        
        if(style == 1)
        {
           answer = problem1Iterative(root1, root2); 
        }else{
           answer = problem1Recursive(root1, root2);
        } 
        return solution == answer;
    }
    private static boolean testProblem2(int[][] testCase, int style)
    {
        int[] tree = testCase[0];
        int[] range = testCase[1];
        int solution = testCase[2][0];
        Node root = new Node(tree[0]);
        for (int i = 1; i < tree.length; i++)
        {
            insert(root, tree[i]);
        }
        int answer;
        
        if(style == 1)
        {
           answer = problem2Iterative(root, range[0], range[1]); 
        }else{
           answer = problem2Recursive(root, range[0], range[1]);
        } 
        return answer == solution;
    }
    private static void testProblems(int prob, int style)
    {
        int noOfLines = 100000;
        System.out.println("-- -- -- -- --");
        
        switch (style)
        {
            case 1:
                  System.out.println(noOfLines + " test cases for problem " + prob 
+ " iterative solution.");
                  break;
            case 2:
                  System.out.println(noOfLines + " test cases for problem " + prob 
+ " recursive solution.");
                  break;
        }
        
        boolean passedAll = true;
        for (int i = 1; i <= noOfLines; i++)
        {
            boolean passed = false;
            boolean exce = false;
            int[][] testCase = null;
            try
            {
                switch (prob)
                {
                    case 1:
                        testCase = createProblem1(i);
                        passed = testProblem1(testCase, style);
                        break;
                    case 2:
                        testCase = createProblem2(i);
                        passed = testProblem2(testCase, style);
                        break;
                }
            }
            catch (Exception ex)
            {
                passed = false;
                exce = true;
            }
            if (!passed)
            {
                System.out.println("Test " + i + " failed!" + (exce ? " (Exception)" : ""));
                if (prob == 1)
                {
                    System.out.println("  tree 1: " + 
Arrays.toString(testCase[0]));
                    System.out.println("  tree 2: " + 
Arrays.toString(testCase[1]));
                }
                else
                {
                    System.out.println("    tree: " + 
Arrays.toString(testCase[0]));
                    System.out.println("   range: " + 
Arrays.toString(testCase[1]));
                }
                passedAll = false;
                break;
            }
        }
        if (passedAll)
        {
            System.out.println("All test passed.");
        }
    }
    private static void shuffle(int[] arr)
    {
        for (int i = 0; i < arr.length; i++)
        {
            int rndInd = rng.nextInt(arr.length - i) + i;
            int tmp = arr[i];
            arr[i] = arr[rndInd];
            arr[rndInd] = tmp;
        }
    }
    private static int[] getRandomNumbers(int size)
    {
        int maxSize = size * 10;
        int[] integers = new int[maxSize];
        for (int i = 0; i < maxSize; i++)
        {
            integers[i] = i;
        }
        shuffle(integers);
        return Arrays.copyOf(integers, size);
    }
    private static int[][] createProblem1(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;
        int equal = rng.nextInt(2);
        int[] tree1 = getRandomNumbers(size);
        int[] tree2 = tree1.clone();
        if (equal == 0)
        {
            int ind = rng.nextInt(tree1.length);
            tree1[ind]+=6543;    
        }
        
        int chance = rng.nextInt(10);
        
        if(chance == 2)
        {
            int ind1 = rng.nextInt(tree1.length);
            int ind2 = rng.nextInt(tree1.length);
            int temp = tree1[ind1];
            tree1[ind1] = tree1[ind2];
            tree1[ind2] = temp; 
        
        }
        return new int[][]
        {
            tree1,
            tree2,
            new int[] { equal }
        };
    }
    private static int[][] createProblem2(int max)
    {
        int maxSize = max < 250 ? max : 250;
        int size = rng.nextInt(maxSize) + 5;
        int[] keys = getRandomNumbers(2 * size);
        int minKey = keys[rng.nextInt(2 * size)];
        int maxKey = keys[rng.nextInt(2 * size)];
        shuffle(keys);
        int[] tree = Arrays.copyOf(keys, size);
        int sum = 0;
        for (int i = 0; i < tree.length; i++)
        {
            if (tree[i] >= minKey && tree[i] <= maxKey)
            {
                sum += tree[i];
            }
        }
        return new int[][]
        {
            tree,
            new int[] { minKey, maxKey },
            new int[] { sum }
        };
    }
}
