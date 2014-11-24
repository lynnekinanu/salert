package com.lynn.salert;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.salert.R;
import com.lynn.models.Users_Model;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignupFragment extends Fragment implements View.OnClickListener {
	EditText etfirstname, etlastname, etEmail, etPassword, etCPassword;
	Button signUp;
	String firstname, email, lastname, pass, cPass;
DatabaseHandler handler;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.sign_up, container, false);
		handler=new DatabaseHandler(getActivity());
		initializeViews(view);
		signUp.setOnClickListener(this);

		return view;
	}

	private void initializeViews(View view) {
		etfirstname = (EditText) view.findViewById(R.id.etFirstname);
		etlastname = (EditText) view.findViewById(R.id.etLastname);
		etEmail = (EditText) view.findViewById(R.id.etEmail);
		etPassword = (EditText) view.findViewById(R.id.etPassword);
		etCPassword = (EditText) view.findViewById(R.id.etCPassword);
		signUp = (Button) view.findViewById(R.id.btnSignUp);

	}

	@Override
	public void onClick(View v) {
		 switch (v.getId()) {
		case R.id.btnSignUp:
			if(validateViews()){
				Users_Model user= new Users_Model();
				user.setUserEmail(email);
				user.setUserFirstname(firstname);
				user.setUserPassword(pass);
				user.setUserLastname(lastname);
				if(handler.createUser(user)){
					Toast.makeText(getActivity(), "Successfully Created Account", Toast.LENGTH_LONG).show();
					startActivity(new Intent(getActivity(),MainActivity.class));
				
				}else{
					Toast.makeText(getActivity(), "Failed Creating account", Toast.LENGTH_LONG).show();
					
				}
			}
			break;

		default:
			break;
		}

	}

	// validate email
	public boolean validateEmail(String email) {
		boolean y = false;
		String expression = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
		CharSequence inputemail = email;
		Pattern pattern = Pattern.compile(expression);
		Matcher matcher = pattern.matcher(inputemail);
		if (matcher.matches()) {
			y = true;
		}

		else {
			y = false;
		}

		return y;

	}

	// validate views
	private boolean validateViews() {
		firstname = etfirstname.getText().toString();
		lastname = etlastname.getText().toString();
		email = etEmail.getText().toString();
		pass = etPassword.getText().toString();
		cPass = etCPassword.getText().toString();

		boolean y = false;
		if (firstname.equals("")) {
			etfirstname.setError("Firstname Empty!");
			etfirstname.requestFocus();
		} else if (lastname.equals("")) {
			etlastname.setError("Lastname Empty!");
			etlastname.requestFocus();
		} else if (email.equals("")) {
			etEmail.setError("Email Empty!");
			etEmail.requestFocus();
		} else if (pass.equals("")) {
			etPassword.setError("Password Empty!");
			etPassword.requestFocus();
		} else if (pass.equals("")) {
			etPassword.setError("Password Empty!");
			etPassword.requestFocus();
		} else if (cPass.equals("")) {
			etCPassword.setError("Confirm Password Empty");
			etCPassword.requestFocus();
		} else if (validateEmail(email) == false) {
			etEmail.setError("Invalid Email Format ");
			etEmail.requestFocus();

		} else if (!pass.equals(cPass)) {
			etPassword.setError("Password Dont Match");

			etPassword.setText("");
			etCPassword.setText("");
			etPassword.requestFocus();
		} else {
			y = true;
		}

		return y;

	}
}
