package com.wgq.MyStack;

import java.util.Stack;

/**
 * 利用栈 实现括号的匹配问题
 */
public class TestArrayStack {
    public static void main(String[] args){
        String s = "{[4444]()}";
        System.out.println(isValid(s));
    }

    public static boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        for(int i=0;i<s.length();i++){
            char c = s.charAt(i);
            if(c=='('||c=='['||c=='{'){
                stack.push(c);
            }
            if(c==']'||c=='}'||c==')'){
                if(stack.isEmpty()){
                    return false;
                }
                char top = stack.pop();
                if(c=='}'&& top!='{'){
                    return false;
                }
                if(c==']'&& top!='['){
                    return false;
                }
                if(c==')'&& top!='('){
                    return false;
                }
            }
        }

        return stack.isEmpty();
    }

}
