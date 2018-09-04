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
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class WordChecker {
	/**
   * Constructor that initializes a new WordChecker with a given WordList.
   *
   * @param wordList Initial word list to check against.
   * @see WordList
   */
    char[] alphabet = "abcdefghijklmnopqrstuvwxyz".toCharArray();
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
	public Set<String> getSuggestions(String word) {
	    Set<String> suggestions = new TreeSet<>();

        suggestions.addAll(splitWordCharacterByCharacter(word));
        suggestions.addAll(swapAdjacentPairOfChars(word));
        suggestions.addAll(insertFromAToZ(word));
        suggestions.addAll(replaceCharacter(word));
        suggestions.addAll(removeCharByChar(word));

		return suggestions;
	}

	private List<String> splitWordCharacterByCharacter(String word) {
        List<String> existingWords = new ArrayList<>();
	    for(int i = 1; i < word.length(); i++) {
            String first = word.substring(0, i);
            String second = word.substring(i);
            if(wordExists(first) && wordExists(second)) {
                existingWords.add(first);
                existingWords.add(second);
            }
        }
        return existingWords;
    }

    private List<String> replaceCharacter(String word) {
	    char[] charArray = word.toCharArray();
	    List<String> existingWords = new ArrayList<>();

        for(int k = 0; k < word.length(); k++) {
            for(int i = 0; i < alphabet.length; i++) {
                charArray[k] = Character.toUpperCase(alphabet[i]);
                String afterReplace = new String(charArray);
                if(wordExists(afterReplace)) {
                    existingWords.add(afterReplace);
                }
            }
        }
	    return existingWords;
    }

	private List<String> removeCharByChar(String word) {
	    List<String> existingWords = new ArrayList<>();
	    StringBuilder afterDeletion = new StringBuilder();

        for(int j = 0; j < word.length(); j++) {
            afterDeletion.append(word.substring(0, j));
            afterDeletion.append(j+1);
            if(wordExists(afterDeletion.toString()) && !existingWords.contains(afterDeletion)) {
                existingWords.add(afterDeletion.toString());
            }
        }
        return existingWords;
    }

    private List<String> insertFromAToZ(String word) {
	    List<String> existingWords = new ArrayList<>();
        StringBuilder afterInsert = new StringBuilder();
        String beforeIndex;
        String indexToEnd;

        for(int i = 0; i < word.length(); i++) {
            for(int index = 0; index < alphabet.length; index++) {
                if(index == 0) {
                    afterInsert.append(Character.toUpperCase(alphabet[index]));
                    afterInsert.append(word);
                } else if(index == word.length() - 1) {
                    afterInsert.append(word);
                    afterInsert.append(Character.toUpperCase(alphabet[index]));
                } else {
                    beforeIndex =  word.substring(0, i);
                    indexToEnd = word.substring(i);
                    afterInsert.append(beforeIndex);
                    afterInsert.append(Character.toUpperCase(alphabet[index]));
                    afterInsert.append(indexToEnd);
                }
                if(wordExists(afterInsert.toString()) && !existingWords.contains(afterInsert)) {
                    existingWords.add(afterInsert.toString());
                }
                afterInsert = new StringBuilder();
            }
        }
        return existingWords;
    }

	private List<String> swapAdjacentPairOfChars(String word) {
	    List<String> existingWords = new ArrayList<>();
	    String swapped;
	    char[] charactersArray = new char[]{};

        for(int i = 0; i < word.length()-1; i++) {
	        if(word.length() > 1) {
	            charactersArray = word.toCharArray();
                char pointed = charactersArray[i];
                charactersArray[i] = charactersArray[i + 1];
                charactersArray[i + 1] = pointed;
            }
            swapped = new String(charactersArray);
            if(wordExists(swapped)) {
                existingWords.add(swapped);
            }
        }
        return existingWords;
    }
}
