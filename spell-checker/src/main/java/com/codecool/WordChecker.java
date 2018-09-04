/**
 *
 * ICS 23 Summer 2004
 * Project #5: Lost for Words
 *
 * Implement your word checker here.  A word checker has two responsibilities:
 * given a word list, answer the questions "Is the word 'x' in the wordlist?"
 * and "What are some suggestions for the misspelled word 'x'?"
 *
 * WordChecker uses a class called WordList that I haven't provided the source
 * code for.  WordList has only one method that you'll ever need to call:
 *
 *     public boolean lookup(String word)
 *
 * which returns true if the given word is in the WordList and false if not.
 */
package com.codecool;

import java.util.ArrayList;

public class WordChecker {
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
    int pointer = 0;
	WordList wordList;

	public WordChecker(WordList wordList) {
        this.wordList = wordList;
	}


	/**
   * Returns true if the given word is in the WordList passed to the
   * constructor, false otherwise.
   *
   * @param word Word to chack against the internal word list
   * @return bollean indicating if the word was found or not.
   */
	public boolean wordExists(String word) {
		return wordList.lookup(word);
	}


	/**
   * Returns an ArrayList of Strings containing the suggestions for the
   * given word.  If there are no suggestions for the given word, an empty
   * ArrayList of Strings (not null!) should be returned.
   *
   * @param word String to check against
   * @return A list of plausible matches
   */
	public ArrayList<String> getSuggestions(String word) {
	    ArrayList<String> suggestions = new ArrayList<>();

        while(this.pointer < word.length()-1) {
            String swapped = swapAdjacentPairOfChars(word);
            if(wordExists(swapped)) {
                suggestions.add(swapped);
            }
            this.pointer++;
        }
        pointerToZero();

        for(int i = 0; i < word.length(); i++) {
            String afterInsertion;
            for(Character character: alphabet) {
                afterInsertion = insertFromAToZ(word, character, i);
                if(wordExists(afterInsertion) && !suggestions.contains(afterInsertion)) {
                    suggestions.add(afterInsertion);
                }
            }
        }
        for(int j = 0; j < word.length(); j++) {
            String afterDeletion = removeCharByChar(word, j);
            if(wordExists(afterDeletion) && !suggestions.contains(afterDeletion)) {
                suggestions.add(afterDeletion);
            }
        }
        for(int k = 0; k < word.length(); k++) {
            String afterReplace;
            for(Character character: alphabet) {
                afterReplace = replaceCharacter(word, character, k);
                if(wordExists(afterReplace)) {
                    suggestions.add(afterReplace);
                }
            }
        }

		return suggestions;
	}

    private String replaceCharacter(String word, char character, int index) {
	    char[] charArray = word.toCharArray();
	    charArray[index] = Character.toUpperCase(character);

	    return new String(charArray);
    }

	private String removeCharByChar(String word, int index) {
        return word.substring(0, index) + word.substring(index + 1);
    }

    private String insertFromAToZ(String word, char character, int index) {
	    char letterFromAlphabet = Character.toUpperCase(character);
        String afterInsert;

        if(index == 0) {
            afterInsert = letterFromAlphabet + word;
        } else if(index == word.length() - 1) {
            afterInsert = word + letterFromAlphabet;
            pointerToZero();
        } else {
            String beforeIndex =  word.substring(0, index);
            String indexToEnd = word.substring(index);
            afterInsert = beforeIndex + letterFromAlphabet + indexToEnd;
        }
        return afterInsert;
    }

	private String swapAdjacentPairOfChars(String word) {
	    char[] charactersArray = word.toCharArray();
	    if(charactersArray.length > 1) {
            char pointed = charactersArray[this.pointer];
            charactersArray[this.pointer] = charactersArray[this.pointer + 1];
            charactersArray[this.pointer + 1] = pointed;
        }
        return new String(charactersArray);
    }

    private void pointerToZero() {
	    this.pointer = 0;
    }
}
