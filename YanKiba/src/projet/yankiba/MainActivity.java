package projet.yankiba;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.view.Menu;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private  Button mButton1 = null;
	private  Button mButton2 = null;
	private TextView number=null;
	private TextView pref=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		String prefixe;
		String numero;
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TelephonyManager mTelephonyMgr;  
		mTelephonyMgr = (TelephonyManager) getSystemService(Context.TELEPHONY_SERVICE);   
		String yourNumber = mTelephonyMgr.getLine1Number();  
        prefixe=yourNumber.substring(0,3);
        numero=yourNumber.substring(3);
		number= (TextView) findViewById(R.id.phone);
		number.setText(numero);
		pref=(TextView) findViewById(R.id.prefixe);
		pref.setText(prefixe);
	    mButton2= (Button) findViewById(R.id.follow);
	    mButton1= (Button) findViewById(R.id.cancel);
	  
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	
}
}