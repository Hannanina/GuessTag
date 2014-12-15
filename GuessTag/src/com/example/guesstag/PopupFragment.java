package com.example.guesstag;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class PopupFragment extends DialogFragment {
	
	private String value;
	private EditText userName;
	private Dialog d;
	
	public static PopupFragment newInstance() {
		PopupFragment p = new PopupFragment();
	    return p;
	}
	
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
        
		d = new Dialog(getActivity(),R.layout.activity_start);
		d.setContentView(R.layout.user_name_popup);
		d.show();
		
		userName = (EditText)d.findViewById(R.id.input_alias);
		
		Button okButton = (Button)d.findViewById(R.id.button_ok);
		okButton.setOnClickListener(new View.OnClickListener() {
		    public void onClick(View v) {
		    	value = userName.getText().toString();
		        SessionManager.getSessionManager().setUserName(value);
		    	d.dismiss();
		    }
		});
		
		
		return d;
    }
	/*
	public void onClickOk() {
		
		value = userName.getText().toString();
		SessionManager.getSessionManager().setUserName(value);
		d.dismiss();
	}
	*/
}