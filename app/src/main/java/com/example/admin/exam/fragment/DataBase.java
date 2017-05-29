package com.example.admin.exam.fragment;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.admin.exam.DatabaseMyHelper;
import com.example.admin.exam.MyAdapter;
import com.example.admin.exam.Parameters;
import com.example.admin.exam.R;


public class DataBase extends Fragment implements View.OnClickListener {
    private OnFragmentInteractionListener mListener;

    EditText etParam1, etParam2;
    Button btAdd;

    ListView MyList;

    DatabaseMyHelper dbHelper;

    public DataBase() {
    }
    public static DataBase newInstance(String param1, String param2) {
        DataBase fragment = new DataBase();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbHelper= new DatabaseMyHelper(this.getContext());

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View myView = inflater.inflate(R.layout.fragment_data_base, container, false);
        btAdd=(Button)myView.findViewById(R.id.button2);
        MyList = (ListView)myView.findViewById(R.id.ListView1);
        btAdd.setOnClickListener(this);
        etParam1=(EditText)myView.findViewById(R.id.editText);
        etParam2=(EditText)myView.findViewById(R.id.editText2);
        Refresh();
        return myView;
    }


    public void Refresh()
    {
       final SQLiteDatabase database = dbHelper.getWritableDatabase();
        registerForContextMenu(MyList);
        Cursor cursor = database.query(DatabaseMyHelper.TABLE_EXAM, null, null, null, null, null, null);

        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(dbHelper.KEY_ID);
            int wordIndex = cursor.getColumnIndex(dbHelper.KEY_COLUMN_ONE);
            int translateIndex = cursor.getColumnIndex(dbHelper.KEY_COLUMN_TWO);
            Parameters[] NewParam = new Parameters[cursor.getCount()];
            int i=0;
            do {
                NewParam[i]= new Parameters (cursor.getString(wordIndex),cursor.getString(translateIndex), cursor.getInt(idIndex));
                i++;
            } while (cursor.moveToNext());

            MyAdapter adapter = new MyAdapter(this.getContext(),NewParam);
            MyList.setAdapter(adapter);
        }
        cursor.close();
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(Menu.NONE,1,1,"Удалить");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case 1:
                ContextMenu.ContextMenuInfo menuInfo = item.getMenuInfo();
                AdapterView.AdapterContextMenuInfo acmi = (AdapterView.AdapterContextMenuInfo) menuInfo;
                Parameters obj = (Parameters) MyList.getItemAtPosition(acmi.position);
                SQLiteDatabase db = new DatabaseMyHelper(DataBase.this.getContext()).getReadableDatabase();
                db.delete(dbHelper.TABLE_EXAM,"_id="+obj.getId(),null);
                db.close();
                Toast.makeText(this.getContext(),"Удалено", Toast.LENGTH_SHORT).show();
                Refresh();
                break;
            default:
                return super.onContextItemSelected(item);
        }
        return true;
    }



    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }
    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        String Param1=etParam1.getText().toString();
        String Param2=etParam2.getText().toString();

        SQLiteDatabase database = dbHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        switch (view.getId())
        {
            case R.id.button2:
                contentValues.put(dbHelper.KEY_COLUMN_ONE, Param1);
                contentValues.put(dbHelper.KEY_COLUMN_TWO, Param2);
                etParam1.setText("");
                etParam2.setText("");
                database.insert(dbHelper.TABLE_EXAM,null, contentValues);
                Toast.makeText(DataBase.this.getContext(), "Добавлено",Toast.LENGTH_LONG).show();
                Refresh();
                break;
        }
        dbHelper.close();
    }

    public interface OnFragmentInteractionListener {
        void onFragmentInteraction(Uri uri);
    }
}
