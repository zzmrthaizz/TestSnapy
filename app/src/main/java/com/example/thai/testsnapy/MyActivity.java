package com.example.thai.testsnapy;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.snappydb.DB;
import com.snappydb.DBFactory;
import com.snappydb.SnappydbException;

import java.util.List;

import model.Session;
import network.NetworkClient;
import util.Logger;


public class MyActivity extends Activity {

	DB snappydb;
	Button mLoginButton;
	EditText etemail;
	EditText etpassword;

//	@OnClick(R.id.btn_login)
//	void doLogin() {
//		String email = etemail.getText().toString();
//		String password = etpassword.getText().toString();
//		String token = NetworkClient.getInstance().getToken(this, email, password);
//		Log.e("Results: ", token);
//		List<Session> user = NetworkClient.getInstance().getContainerSessionsByPage(this, "Token " + token, 0, "");

//		Logger.e(user.toString());
//		if (null != token) {
//			addNewAccount(email, password, token, AccountGeneral.AUTHTOKEN_TYPE);
//		}
//		String currentUser = NetworkClient.getInstance().getCurrentUser(this,"Token "+token);
//		Log.e("Current User: ", currentUser);
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my);

		try {
			snappydb  = DBFactory.open(this);
		} catch (SnappydbException e) {
			e.printStackTrace();
		}


		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads()
				.detectDiskWrites()
				.detectNetwork()   // or .detectAll() for all detectable problems
				.penaltyLog()
				.build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects()
				.detectLeakedClosableObjects()
				.penaltyLog()
				.penaltyDeath()
				.build());

		mLoginButton = (Button) findViewById(R.id.btn_login);
		etemail = (EditText) findViewById(R.id.email);
		etpassword = (EditText) findViewById(R.id.password);

		mLoginButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				String email = etemail.getText().toString();
				String password = etpassword.getText().toString();
				String token = NetworkClient.getInstance().getToken(getApplicationContext(), email, password);
				Log.e("Results: ", token);
				List<Session> user = NetworkClient.getInstance().getContainerSessionsByPage(getApplicationContext(), "Token " + token,1, "");
//				List<AuditReportItem> auditReportItem = user.getAudit_report_items();
				Logger.e(user.toString());
				for (Session container: user){
					try {
						snappydb.put(String.valueOf(container.getId()), container);
					} catch (SnappydbException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}


	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.my, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
