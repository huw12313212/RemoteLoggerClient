package com.example.remotelogapi;

import remotelogapi.RemoteLogAPI;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends ActionBarActivity {

	public RemoteLogAPI logAPI;
	public Button button;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        logAPI = new RemoteLogAPI("ws://wsremotelog.herokuapp.com:80");
        
        logAPI.Start();
        
        button = (Button)this.findViewById(R.id.button1);
        
        button.setOnClickListener(new Button.OnClickListener(){

			@Override
			public void onClick(View v) {
				
				logAPI.Log("fuck you two");
			}});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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
