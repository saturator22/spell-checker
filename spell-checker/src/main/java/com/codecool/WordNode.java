package com.codecool.resources;

public class WordNode {
    String data;
    WordNode next;
    WordNode previous;

    public WordNode(String data) {
        this.data = data;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public WordNode getNext() {
        return next;
    }

    public void setNext(WordNode next) {
        this.next = next;
    }

    public WordNode getPrevious() {
        return previous;
    }

    public void setPrevious(WordNode previous) {
        this.previous = previous;
    }
}
