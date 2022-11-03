package com.trivia.Refacto;

import java.util.ArrayList;
import java.util.List;

public class CategorieQuestion {
    public String categorie;
    public List<Integer> questions;


    public CategorieQuestion() {
    }

    public CategorieQuestion(String categorie, Integer nombreQuestion) {
        this.categorie = categorie;
        this.questions = new ArrayList<>();
        for (int i = 0; i < nombreQuestion; i++) {
            questions.add(i);
        }
    }

    public String getCategorie() {
        return this.categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public List<Integer> getQuestions() {
        return this.questions;
    }

    public void setQuestions(List<Integer> questions) {
        this.questions = questions;
    }

    public void removeQuestion(){
        this.questions.remove(this.questions.size()-1);
    }

}
