package com.lynn.salert;

import com.example.salert.R;
import com.lynn.models.Users_Model;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.Toast;

public class SigninFragment extends Fragment implements View.OnClickListener {
	EditText etEmail, etPassword;
	Button btnLogin;
	CheckBox chkRememberMe;
	public String email, pass;
	boolean checked = true;
	SharedPreferences prefs;
	DatabaseHandler handler;

	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		prefs = getActivity().getSharedPreferences("myprefs",
				getActivity().MODE_PRIVATE);
		handler = new DatabaseHandler(getActivity());
		View view = inflater.inflate(R.layout.login, container, false);
		initializeviews(view);
		btnLogin.setOnClickListener(this);

		return view;
	}

	public void initializeviews(View view) {
		etEmail = (EditText) view.findViewById(R.id.etEmailLogin);
		etPassword = (EditText) view.findViewById(R.id.etPasswordLogin);
		chkRememberMe = (CheckBox) view.findViewById(R.id.chkRemberMe);
		btnLogin = (Button) view.findViewById(R.id.btnLogin);

	}

	public boolean validateView() {
		boolean y = false;
		if (email.equals("")) {
			etEmail.setError("Email empty");
			etEmail.requestFocus();
		} else if (!new SignupFragment().validateEmail(email)) {
			etEmail.setError("Invalid email! ");
			etEmail.requestFocus();
		} else if (pass.equals("")) {
			etPassword.setError("Password email");
			etPassword.requestFocus();
		} else {
			y = true;
		}
		return y;
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.btnLogin:
			email = etEmail.getText().toString();
			pass = etPassword.getText().toString();
			if (validateView()) {
				Users_Model user = new Users_Model();
				user = handler.getUserDetails(email, pass); 
				handler.close();
 
				if (user.getUserFirstname().equals("")) {
					Toast.makeText(getActivity(), "Invalid User Details",
							Toast.LENGTH_LONG).show();
				} else {
					if (checked) {
						Editor edit = prefs.edit();
						edit.putBoolean("remember", true);
						edit.putString("firstname", user.getUserFirstname());
						edit.putString("lastname", user.getUserLastname());
						edit.putString("email", email);
						edit.commit();
					}
					startActivity(new Intent(getActivity(), MainActivity.class));
				}
			}

			break;

		default:
			break;
		}

	}
}
