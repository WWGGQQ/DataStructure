package com.wgq2.Trie;

import java.util.TreeMap;

public class Trie {
    /**
     * 内部类
     *    字典树中的每一个节点
     */
    private class Node{
        public boolean isWord;
        public TreeMap<Character,Node> next;

        public Node(boolean isWord){
            this.isWord = isWord;
            next = new TreeMap<>();
        }

        public Node(){
            this(false);
        }
    }

    private Node root;
    private int size;

    public Trie(){
        root = new Node();
        size = 0;
    }

    /**
     * 获得  单词的数量
     */
    public int getSize(){
        return size;
    }

    /**
     * 向 Trie中添加一个word
     * @param word
     */
    public void add(String word){
        Node cur = root;
        for(int i=0;i<word.length();i++){
            Character c = word.charAt(i);
            if(cur.next.get(c) == null){
                cur.next.put(c,new Node());
            }
            cur = cur.next.get(c);
        }
    }

}
