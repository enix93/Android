package projet.yankiba;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TelephonyManager mTelephonyMgr;  
		mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   

		String yourNumber = mTelephonyMgr.getLine1Number();  
        TextView lol=new TextView(this);
		String imsi = mTelephonyMgr.getSubscriberId();
		String imei = mTelephonyMgr.getDeviceId(); 
		lol.setText(yourNumber);
	  setContentView(lol);
	 
	  
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	
}
}