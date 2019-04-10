package com.sbmlmerge.gui;

public class Step {
    private int index;
    private String name;
    private SBMLPanel panel;
    
    public Step(int index, String name, SBMLPanel panel) {
        this.index = index;
        this.name = name;
        this.panel = panel;
    }

    public int getIndex() {
        return index;
    }

    public String getName() {
        return name;
    }

    public SBMLPanel getPanel() {
        return panel;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPanel(SBMLPanel panel) {
        this.panel = panel;
    }
}
