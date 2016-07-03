package com.naman14.algovisualizer.codeview;

/**
 * Created by yzr on 16/6/20.
 */
public class Code {


    String code;
    Language language;

    public Code(String code){
       this(code,Language.AUTO);
    }

    public Code(String code,Language language){
        this.code=code;
        this.language=language;
    }

    public  enum Language{
        AUTO;
        private  Language(){
        }
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Language getLanguage() {
        return language;
    }

    public void setLanguage(Language language) {
        this.language = language;
    }

}