package com.tom.atm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    boolean logon = false;
    public static final int FUNC_LOGIN = 1;
    String func[] = {"餘額查詢", "交易明細", "最新消息", "投資理財", "離開"};
    int[] icons = {R.drawable.func_balance,
                    R.drawable.func_history,
                    R.drawable.func_news,
                    R.drawable.func_finance,
                    R.drawable.func_exit};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //使用GridView
        GridView grid = (GridView)findViewById(R.id.grid);
//        ArrayAdapter gAdapter =
//                new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        IconAdapter gAdapter = new IconAdapter();
        grid.setAdapter(gAdapter);
        grid.setOnItemClickListener(this);
        //使用Spinner
        Spinner notify = (Spinner)findViewById(R.id.notify_spinner);
        final ArrayAdapter<CharSequence> nAdapter = ArrayAdapter.createFromResource(
                this, R.array.notify_array, android.R.layout.simple_spinner_item );
        nAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        notify.setAdapter(nAdapter);
        notify.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(
                        MainActivity.this,
                        nAdapter.getItem(position), Toast.LENGTH_LONG)
                        .show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        notify.getSelectedItem().toString();
        //使用ListView
        ListView list = (ListView)findViewById(R.id.list);
        ArrayAdapter adapter =
                new ArrayAdapter(this, android.R.layout.simple_list_item_1, func);
        list.setAdapter(adapter);
        if (!logon){  //如果未登入, 則開啟LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
//            startActivity(intent);
            startActivityForResult(intent, FUNC_LOGIN);
        }
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == FUNC_LOGIN){
            if (resultCode == RESULT_OK){
                String uid = data.getStringExtra("LOGIN_USERID");
                String pw = data.getStringExtra("LOGIN_PASSWD");
                Log.d("RESULT", uid + "/" + pw);
            }else{
                finish();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class IconAdapter extends BaseAdapter{
        @Override
        public int getCount() {
            return func.length;
        }
        @Override
        public Object getItem(int position) {
            return func[position];
        }
        @Override
        public long getItemId(int position) {
            return icons[position];
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View row = convertView;
            if (row == null){
                row = getLayoutInflater().inflate(R.layout.item_row, null);
                ImageView image = (ImageView) row.findViewById(R.id.item_image);
                TextView text = (TextView) row.findViewById(R.id.item_text);
                image.setImageResource(icons[position]);
                text.setText(func[position]);
            }
            return row;
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch((int)id){
            case R.drawable.func_balance:
                break;
            case R.drawable.func_history:
                break;
            case R.drawable.func_news:
                break;
            case R.drawable.func_finance:
                break;
            case R.drawable.func_exit: //結束
                finish();
                break;
        }
    }
}
