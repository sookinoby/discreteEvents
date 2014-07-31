/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.unbc.core.models;

import java.util.ArrayList;

/**
 *
 * @author sooki
 */
public class Node {
    private String name;
    private int id;
    private ArrayList<NodeState> nodestates;
    private int groupId;

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

     
    public Node(String name, int id,int groupId) {
        this.name = name;
        this.id = id;
        this.groupId = groupId;
        this.nodestates = new ArrayList<>();
    }
    
     
    public Node(String name, int id) {
      this(name,id,1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public void addState(NodeState state)
    {
        nodestates.add(state);
    }
    
   public ArrayList<NodeState> getAllStates(NodeState state)
    {
        return nodestates;
    }
}
