package com.example.marwen.projetpidevfinal2017;

import android.os.AsyncTask;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.marwen.projetpidevfinal2017.loginRegisterreset.Login;

import javax.mail.AuthenticationFailedException;
import javax.mail.MessagingException;

import spencerstudios.com.fab_toast.FabToast;

public class Reclamation extends AppCompatActivity {
    String emailadrs ;
    private EditText subject;
    private EditText body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation);


        emailadrs = "maw@admin.com" ; //new SessionManager(getApplicationContext()).getUserDetail().get("email");
        subject = (EditText) findViewById(R.id.subject);
        body = (EditText) findViewById(R.id.body);

    }



    public void Send(View view) {

        String[] recipients = { "esprit.emobile@gmail.com" };
        SendEmailAsyncTask email = new SendEmailAsyncTask();
        email.activity = this;
        email.m = new Mail("esprit.emobile@gmail.com","azerty123456");
        email.m.set_from(emailadrs);
        email.m.setBody("From : "+emailadrs+" :   "+body.getText().toString());
        email.m.set_to(recipients);
        email.m.set_subject(subject.getText().toString());
        email.execute();
        FabToast.makeText(getApplicationContext(), "Check ", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();

    }

    public void displayMessage(String message) {
        Snackbar.make(findViewById(R.id.send), message, Snackbar.LENGTH_LONG)
                .setAction("Action", null).show();
    }


}
class SendEmailAsyncTask extends AsyncTask<Void, Void, Boolean> {
    Mail m;
    Reclamation activity;

    public SendEmailAsyncTask() {}

    @Override
    protected Boolean doInBackground(Void... params) {
        try {
            if (m.send()) {
                activity.displayMessage("Email sent.");
                FabToast.makeText(activity, "Check this ", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();

            } else {
                activity.displayMessage("Email failed to send.");
                FabToast.makeText(activity, "Check ", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
            }

            return true;
        } catch (AuthenticationFailedException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Bad account details");
            e.printStackTrace();
            activity.displayMessage("Authentication failed.");
            return false;
        } catch (MessagingException e) {
            Log.e(SendEmailAsyncTask.class.getName(), "Email failed");
            e.printStackTrace();
            activity.displayMessage("Email failed to send.");
            FabToast.makeText(activity, "Check ", FabToast.LENGTH_SHORT, FabToast.ERROR,  FabToast.POSITION_DEFAULT).show();
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            activity.displayMessage("Unexpected error occured.");
            return false;
        }
    }
}
