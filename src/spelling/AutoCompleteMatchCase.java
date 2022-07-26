package spelling;

import java.util.*;
//import java.util.Set;
//
//import static org.junit.Assert.assertEquals;

//import java.util.ArrayList;
//import java.util.Collection;
//import java.util.HashMap;
//import java.util.LinkedList;
import static org.junit.Assert.*;

public class AutoCompleteMatchCase implements  Dictionary, AutoComplete {

    private TrieNode root;
    private int size;

    public AutoCompleteMatchCase()
	{
		root = new TrieNode();
		size = 0;
		
	}
	
	
	/** Insert a word into the trie.
	 * It should appropriately use existing nodes in the trie, only creating new 
	 * nodes when necessary. E.g. If the word "no" is already in the trie, 
	 * then adding the word "now" would add only one additional node 
	 * (for the 'w').
	 * 
	 * @return true if the word was successfully added or false if it already exists
	 * in the dictionary.
	 */
	public boolean addWord(String word)
	{
		if (word == null) {			
			throw new NullPointerException();		
		}
		
		TrieNode cur = root;
		TrieNode temp = new TrieNode();
		
	    for(char c : word.toLowerCase().toCharArray()) {

	    	temp = cur.getChild(c);
	    	if (temp == null) {
	    		temp = cur.insert(c);	    		
	    	}
	    	cur = temp;
	    }
	    	
    	if(cur.endsWord()) {
    		return false; 		
    	}
		else {
			cur.setEndsWord(true);
			size++;		
			return true;
		}
	 }
	    	
	    
	    
	/** 
	 * Return the number of words in the dictionary.  This is NOT necessarily the same
	 * as the number of TrieNodes in the trie.
	 */
	public int size()
	{
		return size;
	}
	
	
	/** Returns whether the string is a word in the trie */
	@Override
	public boolean isWord(String s) 
	{
		if (s == null) {			
			throw new NullPointerException();		
		}
		
		TrieNode cur = root;
		
	    for(char c : s.toLowerCase().toCharArray()) {
	    	
	    	cur = cur.getChild(c);
	    
	    	if (cur == null) {
	    		return false;
	    	}

	    }
	    if(cur.endsWord()) {
    		return true;
    	}
	    else {
	    	return false;
	    }
	}

	/** 
     * Return a list, in order of increasing (non-decreasing) word length,
     * containing the numCompletions shortest legal completions 
     * of the prefix string. All legal completions must be valid words in the 
     * dictionary. If the prefix itself is a valid word, it is included 
     * in the list of returned words. 
     * 
     * The list of completions must contain 
     * all of the shortest completions, but when there are ties, it may break 
     * them in any order. For example, if there the prefix string is "ste" and 
     * only the words "step", "stem", "stew", "steer" and "steep" are in the 
     * dictionary, when the user asks for 4 completions, the list must include 
     * "step", "stem" and "stew", but may include either the word 
     * "steer" or "steep".
     * 
     * If this string prefix is not in the trie, it returns an empty list.
     * 
     * @param prefix The text to use at the word stem
     * @param numCompletions The maximum number of predictions desired.
     * @return A list containing the up to numCompletions best predictions
     */@Override
     public List<String> predictCompletions(String prefix, int numCompletions) 
     {
    	TrieNode cur = root;
 		
 		for(char c : prefix.toLowerCase().toCharArray()) {
 			cur = cur.getChild(c);
		 }
	     
    	 LinkedList<TrieNode> queue = new LinkedList<TrieNode>();
    	 queue.addLast(cur);

    	 List<String> completions = new ArrayList<String>();

    	 while(!queue.isEmpty() && completions.size() < numCompletions) {

    		 TrieNode firstNode = queue.removeFirst();

    		 if (firstNode != null) {
    			 if (firstNode.endsWord()) {
    				 String w = firstNode.getText();
        			 completions.add(w);
    			 }
    			 
    			 Set<Character> firstChild = firstNode.getValidNextCharacters();
    			 for (char e: firstChild) {
    				 queue.addLast(firstNode.getChild(e));
    			 }
    		 }
    	 }	 
         return completions;
     }

 	// For debugging
 	public void printTree()
 	{
 		printNode(root);
 	}
 	
 	/** Do a pre-order traversal from this node down */
 	public void printNode(TrieNode curr)
 	{
 		if (curr == null) 
 			return;
 		
 		System.out.println(curr.getText());
 		
 		TrieNode next = null;
 		for (Character c : curr.getValidNextCharacters()) {
 			next = curr.getChild(c);
 			printNode(next);
 		}
 	}
 	

	
}
