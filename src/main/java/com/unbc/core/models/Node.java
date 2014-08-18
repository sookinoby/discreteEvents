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
    private NodeType type;
    

    public int getGroupId() {
        return groupId;
    }

    public void setGroupId(int groupId) {
        this.groupId = groupId;
    }

     
    public Node(String name, int id,NodeType type,int groupId) {
        this.name = name;
        this.id = id;
        this.groupId = groupId;
        this.nodestates = new ArrayList<>();
        this.type = type;
    }
    
     
    public Node(String name, int id) {
      this(name,id,NodeType.DEFAULT,1);
    }
    
     public Node(String name, int id,NodeType type) {
      this(name,id,type,1);
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
    
   public ArrayList<NodeState> getAllStates()
    {
        return nodestates;
    }

    public NodeType getType() {
        return type;
    }

    public void setType(NodeType type) {
        this.type = type;
    }
   
}
