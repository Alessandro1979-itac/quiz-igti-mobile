package com.alessandromuniz.igti_quiz.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.alessandromuniz.igti_quiz.R;
import com.alessandromuniz.igti_quiz.control.Preferences;
import com.alessandromuniz.igti_quiz.control.media.AudioPlayer;
import com.alessandromuniz.igti_quiz.databinding.ActivityIgtiQuizBinding;
import com.alessandromuniz.igti_quiz.view.fragment.QuizFragment;

public class IgtiQuizActivity extends AppCompatActivity {
    private AudioPlayer audioPlayer;
    private int lastQuestion;
    private ActivityIgtiQuizBinding binding;
    private boolean isFirst = true;
    private int correctsAnswers, incorrectAnswers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_igti_quiz);
        audioPlayer = new AudioPlayer();
        if (Preferences.isPrimeiraVez(this)) {
            Toast.makeText(this, "Bem-vindo ao IGTI Quiz!", Toast.LENGTH_SHORT).show();
            Preferences.setPrimeiraVezFalse(this);
        }
        pushFragment();
    }

    public void pushFragment() {
        FragmentManager fm = getSupportFragmentManager();
        QuizFragment frag = QuizFragment.newInstance(lastQuestion);
        if (isFirst) {
            isFirst = false;
            FragmentTransaction ft = fm.beginTransaction();
            ft.add(R.id.fragment_content, frag);
            ft.commit();
        } else {
            FragmentTransaction ft = fm.beginTransaction();
            ft.replace(R.id.fragment_content, frag);
            ft.commit();
        }
    }

    public void clear(View view) {
        Preferences.clear(this);
        incorrectAnswers = 0;
        correctsAnswers = 0;
        lastQuestion = 0;
        pushFragment();
        binding.textView.setText(String.format("Acertos: %d | Erros: %d", correctsAnswers, incorrectAnswers));
    }

    public void correct(int question) {
        correctsAnswers++;
        lastQuestion = question;
        audioPlayer.play(this, R.raw.cashregister);
        pushFragment();
        binding.textView.setText(String.format("Acertos: %d | Erros: %d", correctsAnswers, incorrectAnswers));
    }

    public void incorrect(int question) {
        incorrectAnswers++;
        lastQuestion = question;
        audioPlayer.play(this, R.raw.buzzer);
        pushFragment();
        binding.textView.setText(String.format("Acertos: %d | Erros: %d", correctsAnswers, incorrectAnswers));
    }
}