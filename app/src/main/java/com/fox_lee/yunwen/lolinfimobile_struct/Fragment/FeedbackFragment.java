package com.fox_lee.yunwen.lolinfimobile_struct.Fragment;

import android.app.Fragment;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.fox_lee.yunwen.lolinfimobile_struct.Activity.MainActivity;
import com.fox_lee.yunwen.lolinfomobile_struct.R;

/**
 * Created by Yunwen on 4/18/2016.
 */
public class FeedbackFragment extends Fragment {

    private EditText recipient;
    private EditText subject;
    private EditText body;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_feedback, container, false);
        return view;
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recipient = (EditText) view.findViewById(R.id.recipient);
        subject = (EditText) view.findViewById(R.id.subject);
        body = (EditText) view.findViewById(R.id.body);
        setEmail();

        Button sendBtn = (Button) view.findViewById(R.id.sendEmail);
        sendBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                sendEmail();
                // after sending the email, clear the fields
            }
        });
    }

    protected void setEmail() {
        recipient.setText("fox1025lee@gmail.com");
        subject.setText("Feedback for Interview Handbook");
        subject.setHint("Enter Subject");
        String sth = body.getEditableText().toString();
        body.setText(sth);
        subject.setHint("Enter Comments");
    }
    protected void sendEmail() {

        String[] recipients = {recipient.getText().toString()};
        Intent email = new Intent(Intent.ACTION_SEND, Uri.parse("mailto:"));
        // prompts email clients only
        email.setType("message/rfc822");
        email.putExtra(Intent.EXTRA_EMAIL, recipients);
        email.putExtra(Intent.EXTRA_SUBJECT, subject.getText().toString());
        email.putExtra(Intent.EXTRA_TEXT, body.getText().toString());

        try {
            // the user can choose the email client
            startActivity(Intent.createChooser(email, "Choose an email client from..."));
        } catch (android.content.ActivityNotFoundException ex) {
            Toast.makeText(getActivity(), "No email client installed.",
                    Toast.LENGTH_LONG).show();
        }
    }
}
