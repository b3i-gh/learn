package com.b3i.monsterspring.enumeration;

public enum Language {
    LANG_IT("IT"), LANG_SI("SI"), LANG_EN("EN");

    private final String lang;
    Language(String lang)  {this.lang = lang;}

    public String getLang(){
        return this.lang;
    }
}
