package com.example.guesstag;

import android.app.Activity;
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
import android.widget.EditText;


/**
 * This class implements a popup fragment which is used to enable
 * players to input their names in game for the first time they
 * access the app.
 * 
 * @author group 6
 *
 */
public class PopupFragment extends DialogFragment {
	
	NoticeDialogListener mListener;
	
	public interface NoticeDialogListener {
        public void onPopupOkClick(DialogFragment popupFragment);
    }
	
	public static PopupFragment newInstance() {
		PopupFragment p = new PopupFragment();
	    return p;
	}
	
	public Dialog onCreateDialog(Bundle savedInstanceState) {
	    AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	    // Get the layout inflater
	    LayoutInflater inflater = getActivity().getLayoutInflater();

	    // Inflate and set the layout for the dialog
	    // Pass null as the parent view because its going in the dialog layout
	    builder.setView(inflater.inflate(R.layout.user_name_popup, null))
	    // Add action buttons
	           .setPositiveButton(R.string.button_ok, new DialogInterface.OnClickListener() {
	               @Override
	               public void onClick(DialogInterface dialog, int id) {
	            	 
	            	   mListener.onPopupOkClick(PopupFragment.this);
	               }
	           });
	               
	    return builder.create();
	}
	
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mListener = (NoticeDialogListener) activity;
        }
        catch(ClassCastException e) {

            throw new ClassCastException(activity.toString()
                    + " must implement NoticeDialogListener");
        }
    }
}