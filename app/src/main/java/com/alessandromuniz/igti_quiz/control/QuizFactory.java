package com.alessandromuniz.igti_quiz.control;

import com.alessandromuniz.igti_quiz.model.Answer;
import com.alessandromuniz.igti_quiz.model.Question;

import java.util.ArrayList;
import java.util.List;

public class QuizFactory {
    public static List<Question> buildQuizQuestions() {
        List<Question> list = new ArrayList<>();
        list.add(buildOne());
        list.add(buildTwo());
        list.add(buildThree());
        list.add(buildFour());
        list.add(buildFive());
        list.add(buildSix());
        list.add(buildSeven());
        list.add(buildEight());
        return list;
    }

    private static Question buildOne() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Falso"));
        answers.add(new Answer("Verdadeiro"));
        return new Question("A linguagem oficial para desenvolvimento Android Nativo pela Google é a Kotlin.", 2, answers);
    }

    private static Question buildTwo() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Verdadeiro"));
        answers.add(new Answer("Falso"));
        return new Question("O processo de publicação do aplicativo na Google Play é gratuito.", 2, answers);
    }

    private static Question buildThree() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Verdadeiro"));
        answers.add(new Answer("Falso"));
        return new Question("O Brasil possui uma população de quase 210 milhões.", 2, answers);
    }

    private static Question buildFour() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Falso"));
        answers.add(new Answer("Verdadeiro"));
        return new Question("Flutter é uma dos frameworks de desenvolvimento mobile.", 2, answers);
    }

    private static Question buildFive() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Falso"));
        answers.add(new Answer("Verdadeiro"));
        return new Question("A linguagem de programação do Flutter é o Dart.", 2, answers);
    }

    private static Question buildSix() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Verdadeiro"));
        answers.add(new Answer("Falso"));
        return new Question("O Flutter possui interoperabilidade e pode ter projetos em Java e Dart.", 2, answers);
    }

    private static Question buildSeven() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Falso"));
        answers.add(new Answer("Verdadeiro"));
        return new Question("React-Native é uma plataforma para desenvolvimento de aplicativos móveis.", 2, answers);
    }

    private static Question buildEight() {
        List<Answer> answers = new ArrayList<>();
        answers.add(new Answer("Falso"));
        answers.add(new Answer("Verdadeiro"));
        return new Question("O Kotlin possui interoperabilidade oque possibilita implementar projetos em Java e Kotlin.", 2, answers);
    }
}
