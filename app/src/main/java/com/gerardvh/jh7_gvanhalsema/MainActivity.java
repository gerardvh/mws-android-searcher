package com.gerardvh.jh7_gvanhalsema;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gerardvh.jh7_gvanhalsema.SLConstants.CIParameterConstants;
import com.gerardvh.jh7_gvanhalsema.SLConstants.ServiceLinkRequestConstants;
import com.gerardvh.jh7_gvanhalsema.model.Computer;
import com.gerardvh.jh7_gvanhalsema.parsers.ComputerJSONParser;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity implements AdapterView.OnItemClickListener{
//    static final String DETAIL_ACTIVITY_CALL = "com.gerardvh.jh7_gvanhalsema.ComputerDetailActivity";
    static final int DETAIL_ACTIVITY_INTENT = 10;

    TextView output;
    ListView listView;
    ProgressBar pb;
    List<MyTask> tasks;

    List<Computer> computerList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = (ListView) findViewById(R.id.listView);
        listView.setOnItemClickListener(this);

        pb = (ProgressBar) findViewById(R.id.progressBar);
        pb.setVisibility(View.INVISIBLE);

        tasks = new ArrayList<>();

        if (computerList != null) {
            ComputerAdapter adapter =
                    new ComputerAdapter(MainActivity.this, R.layout.item_computer, computerList);
            listView.setAdapter(adapter);
        }

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == DETAIL_ACTIVITY_INTENT && resultCode == Activity.RESULT_OK) {
//            Coming back from the Detail view, possibly with some data
//            TODO: Do something when returning from the Detail?
        } else if (data != null) {
            Log.d("Mine", "Returned from Barcode Scanner");
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null) {
                String scanContent = result.getContents();
                requestData(CIParameterConstants.TABLE_NAME, scanContent, "GET");
//            TODO: Method to request data by serial number or asset tag
            }
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        if (item.getItemId() == R.id.action_do_task) {
            if (isOnline()) {
                requestTestData(ServiceLinkRequestConstants.DEV_URL + CIParameterConstants.TABLE_NAME);
            } else {
                Toast.makeText(this, "Network not available", Toast.LENGTH_LONG).show();
            }
        } else if (item.getItemId() == R.id.action_scan_barcode) {
            IntentIntegrator scanIntegrator = new IntentIntegrator(this);
            scanIntegrator.initiateScan();
        }

        return super.onOptionsItemSelected(item);
    }

    private void requestTestData(String uri) {
//        TODO: RequestParameters params = new RequestParameters();
//        p.setParams(params.getMap());

        MyTask task = new MyTask();

        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, getTestParameters(uri));
    }

    private RequestPackage getTestParameters(String uri) {
        RequestPackage p = new RequestPackage();
        p.setMethod("GET");
        p.setUri(uri);

        p.setParam(ServiceLinkRequestConstants.SYSPARM_LIMIT, "200");
        p.setParam(ServiceLinkRequestConstants.SYSPARM_DISPLAY_VALUE, "true");
        p.setParam(ServiceLinkRequestConstants.SYSPARM_EXCLUDE_REFERENCE_LINK, "true");
        p.setParam(ServiceLinkRequestConstants.SYSPARM_FIELDS, CIParameterConstants.FIELDS_TO_RETURN);
        p.setParam(ServiceLinkRequestConstants.SYSPARM_QUERY,
                CIParameterConstants.SERIAL_NUMBER + "ISNOTEMPTY^" +
                        CIParameterConstants.ASSET_TAG + "ISNOTEMPTY^" +
                        "ORDERBY" + CIParameterConstants.ASSET_TAG);

        return p;

    }

    private RequestPackage getParameters(String tableName, String tagOrSerial, String method) {
        RequestPackage p = new RequestPackage();
        p.setMethod(method);
        p.setUri(ServiceLinkRequestConstants.PROD_URL + tableName);

        p.setParam(ServiceLinkRequestConstants.SYSPARM_DISPLAY_VALUE, "true");
        p.setParam(ServiceLinkRequestConstants.SYSPARM_EXCLUDE_REFERENCE_LINK, "true");
        p.setParam(ServiceLinkRequestConstants.SYSPARM_FIELDS, CIParameterConstants.FIELDS_TO_RETURN);
        p.setParam(ServiceLinkRequestConstants.SYSPARM_QUERY,
                CIParameterConstants.ASSET_TAG + "LIKE" + tagOrSerial + "^OR" +
                CIParameterConstants.SERIAL_NUMBER + "LIKE" + tagOrSerial);

        return p;
    }

    private void requestData(String tableName, String tagOrSerial, String method) {
        MyTask task = new MyTask();

        task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR,
                getParameters(tableName, tagOrSerial, method));

    }

    protected boolean isOnline() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = cm.getActiveNetworkInfo();
        return netInfo != null && netInfo.isConnectedOrConnecting();

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Log.d("Mine", "Item clicked at position: " + position + " and view: " + view +
        " and parent AdapterView: " + parent);
        Computer computer = computerList.get(position);
        Intent intent = new Intent(this, ComputerDetailActivity.class);
        if (computer.getSerialNumber() != null) {
            intent.putExtra("serialNumber", computer.getSerialNumber());
        }
        if (computer.getAssetTag() != null) {
            intent.putExtra("assetTag", computer.getAssetTag());
        }
        if (computer.getSerialNumber() != null) {
            intent.putExtra("assignedTo", computer.getAssignedTo());
        }
        if (computer.getSerialNumber() != null) {
            intent.putExtra("location", computer.getLocation());
        }
        if (computer.getSerialNumber() != null) {
            intent.putExtra("macAddress", computer.getMacAddress());
        }
        if (computer.getSerialNumber() != null) {
            intent.putExtra("comments", computer.getComments());
        }
        startActivityForResult(intent, DETAIL_ACTIVITY_INTENT);
    }



    //    ****** Inner class for AsyncTask ******
    private class MyTask extends AsyncTask<RequestPackage, String, String> {

        @Override
        protected void onPreExecute() {
//            Runs before the doInBackground() method, has access to items in the main thread and UI
//            updateDisplay("Starting Task");
            Log.d("Mine","Starting AsyncTask");
            if (tasks.size() == 0) {
                pb.setVisibility(View.VISIBLE);
            }

            tasks.add(this);
        }

        @Override
        protected String doInBackground(RequestPackage... params) {
//            Runs automatically when creating an AsyncTask
//            Passes the results to onPostExecute()
            String content = HttpManager.getSLData(params[0]);
            computerList = ComputerJSONParser.parseFeed(content);
            return content;
        }

        @Override
        protected void onPostExecute(String s) {
//            Runs after the background process is finished, has access to main thread.
            pb.setVisibility(View.INVISIBLE);

            Log.d("Mine", "JSON Has been returned to onPostExecute()");
            tasks.remove(this);
            ComputerAdapter adapter =
                    new ComputerAdapter(MainActivity.this, R.layout.item_computer, computerList);
            listView.setAdapter(adapter);
        }
}

}
