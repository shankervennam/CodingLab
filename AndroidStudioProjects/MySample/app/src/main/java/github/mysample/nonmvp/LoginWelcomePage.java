package github.mysample.nonmvp;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import github.mysample.R;

public class LoginWelcomePage extends ListActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_welcome);

        ListView listView = getListView();

        String[] values = new String[] {"List1", "List 2", "List3", "List4", "List5"};
        setListAdapter(new ArrayAdapter<>(this, R.layout.support_simple_spinner_dropdown_item, values));
    }

    @Override
    protected void onListItemClick(ListView l, View v, int position, long id) {
        String item = (String) getListAdapter().getItem(position);
        Toast.makeText(this, item + "selected", Toast.LENGTH_LONG).show();
    }
}
