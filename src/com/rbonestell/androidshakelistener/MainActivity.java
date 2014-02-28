package com.rbonestell.androidshakelistener;

import java.util.Random;
import android.os.Bundle;
import android.app.Activity;
import android.widget.TextView;

public class MainActivity extends Activity
{

	private ShakeListener mShaker;
	private TextView lblMessage;
	private String[] m_Responses;
	private Random m_RandGen;
	private int currentResponseIndex = 0;
	
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initMembers();
        
        try
    	{
        	if (mShaker == null)
        		mShaker = new ShakeListener(this);
        	
        	if (!mShaker.supported)
        	{
        		lblMessage.setText("Sensor required to detect shaking is not available.");
        	}
        	else
        	{
		    	mShaker.setOnShakeListener(new ShakeListener.OnShakeListener () {
			    	@Override
					public void onShake()
			    	{
						//Device is shaking
			    		int lastResponseIndex = currentResponseIndex;
			    		while (currentResponseIndex == lastResponseIndex)
			    			currentResponseIndex = m_RandGen.nextInt(m_Responses.length);
			    		lblMessage.setText(m_Responses[currentResponseIndex]);
					}
		        });
        	}
	
    	}
        catch (Exception e)
        {
    		lblMessage.setText("Exception occured: " + e.getMessage());
    	}   
    }

	private void initMembers() {
        lblMessage = (TextView)this.findViewById(R.id.lblMessage);
		m_Responses = this.getResources().getStringArray(R.array.responses);
        m_RandGen = new Random();
	}

}
