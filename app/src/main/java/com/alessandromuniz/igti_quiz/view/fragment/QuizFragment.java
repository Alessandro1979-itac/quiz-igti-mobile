package com.alessandromuniz.igti_quiz.view.fragment;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.alessandromuniz.igti_quiz.R;
import com.alessandromuniz.igti_quiz.control.QuizFactory;
import com.alessandromuniz.igti_quiz.databinding.FragmentQuizBinding;
import com.alessandromuniz.igti_quiz.model.Question;
import com.alessandromuniz.igti_quiz.view.IgtiQuizActivity;

import java.util.List;
import java.util.Objects;

public class QuizFragment extends Fragment {
    private static final String LAST_QUESTION = "last_question";
    private int lastQuestion;
    private FragmentQuizBinding binding;
    private Question question;
    private int current = 0;

    public QuizFragment() {
        // Required empty public constructor
    }

    public static QuizFragment newInstance(int lastQuestion) {
        QuizFragment fragment = new QuizFragment();
        Bundle args = new Bundle();
        args.putInt(LAST_QUESTION, lastQuestion);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            lastQuestion = getArguments().getInt(LAST_QUESTION);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_quiz, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        question = getQuestion();
        binding.tvQuestion.setText(question.getDescription());
        String[] values = new String[question.getOptions().size()];

        for (int i = 0; i < question.getOptions().size(); i++) {
            values[i] = question.getOptions().get(i).getOption();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(Objects.requireNonNull(getActivity()), android.R.layout.simple_list_item_1, android.R.id.text1, values);
        binding.listview.setAdapter(adapter);
        binding.listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                IgtiQuizActivity activity = ((IgtiQuizActivity) getActivity());
                if (activity != null) {
                    if (position + 1 == question.getCorrectAnswer()) {
                        activity.correct(current);
                    } else {
                        activity.incorrect(lastQuestion);
                    }
                }
            }
        });
    }

    private Question getQuestion() {
        List<Question> questions = QuizFactory.buildQuizQuestions();
        if (lastQuestion + 1 > questions.size() - 1) {
            current = 0;
        } else {
            current = lastQuestion + 1;
        }
        return questions.get(current);
    }
}
