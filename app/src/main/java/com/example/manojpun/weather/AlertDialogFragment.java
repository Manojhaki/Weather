package com.example.manojpun.weather;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;

/**
 * Created by manojpun on 7/18/17.
 */

public class AlertDialogFragment extends DialogFragment {

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        Context context = getActivity();
        AlertDialog.Builder builder= new AlertDialog.Builder(context)
                .setTitle("Small Thing")
                .setMessage("DONT WORRY")
                .setPositiveButton("OK",null);

        AlertDialog dialog =builder.create();
        return dialog;
    }
}
