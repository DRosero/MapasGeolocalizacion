package com.example.mapasgeolocalizacion;

import android.app.AlertDialog;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.widget.TextView;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;

public class MainActivity extends FragmentActivity implements LocationListener {

	private GoogleMap mMap;
	AlertDialog alert = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
				
		            // Getting reference to the SupportMapFragment of activity_main.xml
		            SupportMapFragment fm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
		 
		            // Getting GoogleMap object from the fragment
		            mMap = fm.getMap();
		 
		            // Enabling MyLocation Layer of Google Map
		            mMap.setMyLocationEnabled(true);
		 
		            // Getting LocationManager object from System Service LOCATION_SERVICE
		            LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
		            /*if ( !locationManager.isProviderEnabled( LocationManager.GPS_PROVIDER ) ) {
		                AlertNoGps();
		            }*/
		            // Creating a criteria object to retrieve provider
		            Criteria criteria = new Criteria();
		            
		            // Getting the name of the best provider
		            String provider = locationManager.getBestProvider(criteria, true);
		 
		            // Getting Current Location
		            Location location = locationManager.getLastKnownLocation(provider);
		 
		            if(location!=null){
		                onLocationChanged(location);
		            }
		            locationManager.requestLocationUpdates(provider, 20, 0, this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}		

	@Override
	public void onLocationChanged(Location location) {
		// TODO Auto-generated method stub
		TextView tvLocation = (TextView) findViewById(R.id.tv_location);
		 
        // Getting latitude of the current location
        double latitude = location.getLatitude();
 
        // Getting longitude of the current location
        double longitude = location.getLongitude();
 
        // Creating a LatLng object for the current location
        LatLng latLng = new LatLng(latitude, longitude);
 
        // Showing the current location in Google Map
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15));
 
        // Zoom in the Google Map
        //mMap.animateCamera(CameraUpdateFactory.zoomTo(15));
 
        // Setting latitude and longitude in the TextView tv_location
        tvLocation.setText("Latitude:" +  latitude  + ", Longitude:"+ longitude );
        
 
		
	}

	@Override
	public void onProviderDisabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onProviderEnabled(String provider) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onStatusChanged(String provider, int status, Bundle extras) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		
		  if(alert != null) 
		  {
		      alert.dismiss ();
		  }
		
	}
}
