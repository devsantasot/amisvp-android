package com.example.amisvp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.os.Handler;

import com.example.amisvp.helper.ImageHelper;
import com.example.amisvp.pojo.Exam;
import com.google.android.material.textfield.TextInputLayout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CandidateActivity extends AppCompatActivity {

    IAPIClient apiClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_candidate);

        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        Exam examInfo = (Exam)intent.getSerializableExtra(FullscreenActivity.EXTRA_EXAM_INFO);
        setExamInfo(examInfo);
    }

    private void setExamInfo(Exam examInfo){
        // Capture the layout's TextView and set the string as its text
        TextView textView = findViewById(R.id.tokenTextView);
        textView.setText("Token: " + examInfo.token.toUpperCase());

        textView = findViewById(R.id.fullNameTextView);
        textView.setText(String.format("%s %s %s", examInfo.persona.nombres,
                examInfo.persona.apellidoPaterno, examInfo.persona.apellidoMaterno));

        TextInputLayout editText = findViewById(R.id.license_textinput);
        editText.getEditText().setText(examInfo.nroRegistroLicencia, TextView.BufferType.EDITABLE);

        editText = findViewById(R.id.evaluationdate_textinput);
        editText.getEditText().setText(examInfo.fechaEvaluacion, TextView.BufferType.EDITABLE);

        editText = findViewById(R.id.category_textinput);
        editText.getEditText().setText(String.format("%s-%s", examInfo.clase, examInfo.categoria),
                TextView.BufferType.EDITABLE);

        ImageView imageView = findViewById(R.id.candidate_imageView);
        new ImageHelper.DownloadImageFromInternet(imageView).execute(examInfo.rutaFoto);

    }

    /**
     * Called when the user touches the search button available
     */
    public void searchByToken_onClick(View view) {
        finish();
    }


    public void confirm_onClick(View view) {
        finish();
    }
}