package com.example.deeyu.androidlabs;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.util.Log;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class ChartWindow extends AppCompatActivity {

    ListView lv;
    EditText ed;
    Button button;
    ArrayList<String> list = new ArrayList<String>();
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;
    protected static final String ACTIVITY_NAME = "StartActivity";
    ChatDatabaseHelper dbHelper;
    SQLiteDatabase db;
    private ArrayAdapter<String> listAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart_window);

        // lab7 step 3
        Boolean isExist;//??????????
        FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame);
        if (frameLayout == null){
            Log.i("Check","it wasn't loaded, and you are using phone layout");
        }else{
            Log.i("Check","it was loaded, and you are using tablet layout");
        }


        lv = (ListView) findViewById(R.id.listView);
        ed = (EditText) findViewById(R.id.editText23);
        button = (Button) findViewById(R.id.buttonS);
        dbHelper = new ChatDatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        Cursor cursor = db.rawQuery("SELECT * from TableName", null);
        int columnIndex = cursor.getColumnIndex( "KEY_MESSAGE" );
        cursor.moveToFirst();

        final ChatAdapter messageAdapter = new ChatAdapter(this);

        while (!cursor.isAfterLast()) {
            Log.i(ACTIVITY_NAME, "SQL MESSAGE:" + cursor.getString(cursor.getColumnIndex(ChatDatabaseHelper.MESSAGE)));
                String message = cursor.getString( columnIndex );
                list.add(message);
                cursor.moveToNext();
        }
        Log.i(ACTIVITY_NAME, "Cursor's  column count =" + cursor.getColumnCount() );

        for(int i = 0; i < cursor.getColumnCount(); i++){
            String name = cursor.getColumnName(i);
            Log.i(ACTIVITY_NAME,"Column Name: "  + name);
        }

        messageAdapter.notifyDataSetChanged();//this restarts the process of getCount()/getView()
        lv.setAdapter(messageAdapter);

        button.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String message = ed.getText().toString();
                list.add(message);
                db.execSQL("Insert into TableName(KEY_MESSAGE) values('"+message+"');");
                ed.setText("");
                messageAdapter.notifyDataSetChanged();//this restarts the process of getCount()/getView()
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();

//--------------lab7 step6
//
//        listAdapter = new ArrayAdapter<String>(this, R.layout.activity_chart_window, list);
//        // Setup Adapter for ListActivity using in ListView
//        setListAdapter(listAdapter);
//
//        ListView listView = getListView();
//        // Setup listerner for clicks
//        listView.setOnItemClickListener(this);
//    }
//    @Override
//    public void onItemClick(AdapterView<?> adapter, View arg1, int position, long arg3) {
//        String item = adapter.getItemAtPosition(position).toString();
//        Toast.makeText(ChartWindow.this, "CLICK: " + item, Toast.LENGTH_SHORT).show();
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("ChartWindow Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        db.close();

    }

    private class ChatAdapter extends ArrayAdapter<String> {

        public ChatAdapter(Context ctx) {
            super(ctx, 0);
        }

        public int getCount() {
            return list.size();
        }

        public String getItem(int position) {
            return list.get(position);
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            LayoutInflater inflater = ChartWindow.this.getLayoutInflater();
            View result = null;
            if (position % 2 == 0)
                result = inflater.inflate(R.layout.chat_row_incoming, null);
            else
                result = inflater.inflate(R.layout.chat_row_outgoing, null);

            TextView message = (TextView) result.findViewById(R.id.message_text);
            message.setText(getItem(position)); //get the string at position
            return result;
        }
    }


}
