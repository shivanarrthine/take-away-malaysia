package com.narrthine.takeawaymalaysia;


import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.ActionBar;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.*;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ShareActionProvider;



public class MainActivity extends ListActivity {
	
	// Names of outlets, numbers, and logo
	public String[] outletsName = new String[] {"Burger King", "Canadian Pizza", "Domino's", "Kenny Rogers Roasters", "McDonalds", "Nandos", "Papa John's", "Pizza Hut", "Pizza Hut Delivery" };
	public String[] outletsNum = new String[] {"1300305555", "1300880241", "1300888333", "1300888878", "1300131300", "1300886555", "1300887272", "1300882525", "1300888333"};
	public int[] logos = {R.drawable.burgerking, R.drawable.canadianpizza, R.drawable.domino, R.drawable.kennyrogers, R.drawable.mcd, R.drawable.nandos, R.drawable.papajohns, R.drawable.pizzahut, R.drawable.phd};
	
	private ShareActionProvider mShareActionProvider;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // removes title on view
        //this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        // to view take away malaysia header
       // View header = getLayoutInflater().inflate(R.layout.window_title, null);
        // get list view
        getActionBar().setDisplayShowTitleEnabled(false);
        ListView lv = getListView();
       // lv.addHeaderView(header);
        setListAdapter(new IconicAdapter());
        
        
    }
    
    protected void onStart(){
    	super.onStart();
    }
    
    protected void onResume(){
    	super.onResume();
    }
    
    protected void onPause(){
    	super.onPause();
    }
    
    protected void onStop(){
    	super.onStop();
    }
    
    protected void onDestroy(){
    	super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.activity_main, menu);
        return true;
    }
	
	@SuppressLint("NewApi")
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
		/*MenuInflater inflater = getMenuInflater();
	    inflater.inflate(R.menu.activity_main, menu);
	    MenuItem item = menu.findItem(R.id.menu_item_share);
	    mShareActionProvider = (ShareActionProvider) item.getActionProvider();
	    mShareActionProvider.setShareIntent(createShareIntent());
	    MenuItem about = menu.findItem(R.id.menu_item_about);*/
		switch(item.getItemId()){
		case R.id.menu_item_share:
			mShareActionProvider = (ShareActionProvider) item.getActionProvider();
		    mShareActionProvider.setShareIntent(createShareIntent());
		    return true;
		case R.id.menu_item_about:
			Intent intent = new Intent(this, DisplayAboutActivity.class);
	    	startActivity(intent);
	    	return true;
	    default:
	    	return super.onOptionsItemSelected(item);
		}
	    
    }
    
	/* Call to update the share intent
	@SuppressLint("NewApi")
	private void setShareIntent(Intent shareIntent) {
	    if (mShareActionProvider != null) {
	        mShareActionProvider.setShareIntent(shareIntent);
	    }
	    
	}*/
	
	private Intent createShareIntent(){
		Intent I = new Intent(Intent.ACTION_SEND);
		I.setType("text/plain");
		I.putExtra(android.content.Intent.EXTRA_TEXT, "I'm using Take Away Malaysia to order fast food. Download it today! https://play.google.com/store/apps/details?id=com.narrthine.takeawaymalaysia");
		return I;
	}
   
    
    protected void onListItemClick(ListView l, View v, final int position, long id){
    	super.onListItemClick(l, v, position, id);
    	DialogInterface.OnClickListener dialogClickListener = new DialogInterface.OnClickListener() {
    	    public void onClick(DialogInterface dialog, int which) {
    	        switch (which){
    	        case DialogInterface.BUTTON_POSITIVE:
    	            //Yes button clicked
    	        	String phone = "tel:" + outletsNum[position].toString().trim();
    	        	Intent i = new Intent(Intent.ACTION_CALL, Uri.parse(phone));
    	        	startActivity(i);
    	            break;

    	        case DialogInterface.BUTTON_NEGATIVE:
    	            //No button clicked
    	            break;
    	        }
    	    }
    	};

    	AlertDialog.Builder builder = new AlertDialog.Builder(this);
    	builder.setMessage("Do you want to call " + outletsName[position] + "?").setPositiveButton("Call", dialogClickListener)
    	    .setNegativeButton("No", dialogClickListener).show();
    	
    }
    /*
    public void viewAbout(View view){
    	Intent intent = new Intent(this, DisplayAboutActivity.class);
    	startActivity(intent);
    }
   */

    class IconicAdapter extends ArrayAdapter<String>{
    	public IconicAdapter() {
			super(MainActivity.this, R.layout.takeaway_rows, R.id.text, outletsName);
		}
    
    
	   public View getView(int position, View convertView, ViewGroup parent){
		   View row = super.getView(position, convertView, parent);
		   ImageView logo = (ImageView)row.findViewById(R.id.image);
		   logo.setImageResource(logos[position]);
		   
		   return(row);
	   }
    }
   
    
    
}
