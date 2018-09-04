/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your hash table here.  You are required to use the separate
 * chaining strategy that we discussed in lecture, meaning that collisions
 * are resolved by having each cell in the table be a linked list of all of
 * the strings that hashed to that cell.
 */
package com.codecool;

import java.util.LinkedList;

public class HashTable {
    private int size;
    private int count = 0;
    private StringHasher hasher;
    private LinkedList<WordNode>[] elements;
	/**
   * The constructor is given a table size (i.e. how big to make the array)
   * and a StringHasher, which is used to hash the strings.
   *
   * @param tableSize number of elements in the hash array
   *        hasher    Object that creates the hash code for a string
   * @see StringHasher
   */
	public HashTable(int tableSize, StringHasher hasher) {
        this.size = tableSize;
        this.hasher = hasher;
	    this.elements = new LinkedList[size];
	}

    private int getPosition(int hashcode) {
        if(hashcode < 0) {
            hashcode = hashcode * -1;
        }
        return hashcode % size;
    }


	/**
   * Takes a string and adds it to the hash table, if it's not already
   * in the hash table.  If it is, this method has no effect.
   *
   * @param s String to add
   */
	public void add(String s) {
        int position = getPosition(hasher.hash(s));

        LinkedList<WordNode> list = elements[position];

        if(list == null) {
            list = new LinkedList<>();
            elements[position] = list;
            list.add(new WordNode(s));
        } else  {
            for(WordNode wordNode:list) {
                if(wordNode.getData().equals(s)) {
                    return;
                }
            }
            list.add(new WordNode(s));
        }
        count++;
	}
	

	/**
  * Takes a string and returns true if that string appears in the
	* hash table, false otherwise.
  *
  * @param s String to look up
  */
	public boolean lookup(String s) {
        LinkedList<WordNode> list = elements[getPosition(hasher.hash(s))];

        if(list != null) {
            for(WordNode wordNode: list) {
                if(wordNode.getData().equals(s)) return true;
            }
        }
        return false;
	}
	

	/**
   * Takes a string and removes it from the hash table, if it
   * appears in the hash table.  If it doesn't, this method has no effect.
   *
   * @param s String to remove
  */
	public void remove(String s) {
        int position = getPosition(hasher.hash(s));

        LinkedList<WordNode> list = elements[position];

        if(list == null) {
            return;
        }

        int listSize = list.size();

        if(listSize < 2) {
            elements[position] = null;
        } else {
            for(WordNode wordNode: list) {
                if(wordNode.getData().equals(s)) wordNode = null;
            }
        }
        count --;
	}
}
