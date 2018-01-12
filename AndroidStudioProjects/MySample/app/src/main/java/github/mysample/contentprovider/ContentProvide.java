package github.mysample.contentprovider;

import android.Manifest;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.view.View;
import android.widget.TextView;

import github.mysample.R;

public class ContentProvide extends Activity implements LoaderManager.LoaderCallbacks<Cursor>,
        View.OnClickListener {

    private boolean mFirstTimeLoaded = false;

    TextView mTextViewQuery;

    private String[] mColumnProjection = new String[]{
            ContactsContract.Contacts.DISPLAY_NAME_PRIMARY,
            ContactsContract.Contacts.CONTACT_STATUS,
            ContactsContract.Contacts.HAS_PHONE_NUMBER};

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_content_provider);
        mTextViewQuery = findViewById(R.id.textViewQuery);

        findViewById(R.id.content_provider).setOnClickListener(this);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        if (i == 1) {
            return new CursorLoader(this, ContactsContract.Contacts.CONTENT_URI,
                    mColumnProjection, null, null, null);
        } else {
            return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        if (cursor != null && cursor.getCount() > 0) {
            StringBuilder stringBuilder = new StringBuilder("");
            while (cursor.moveToNext()) {
                stringBuilder.append(cursor.getString(0)).append(",").append(cursor.getString(1)).
                        append(",").append("/n");
            }
            mTextViewQuery.setText(stringBuilder.toString());
        } else {
            mTextViewQuery.setText("No Contacts");
        }

        if (cursor != null) {
            cursor.close();
        }
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }

    @Override
    public void onClick(View view) {
        int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 20;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(
                Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CONTACTS},
                    MY_PERMISSIONS_REQUEST_READ_CONTACTS);
        } else {
            loadContacts();
        }
    }

    private void loadContacts() {
        if (!mFirstTimeLoaded) {
            getLoaderManager().initLoader(1, null, this);
            mFirstTimeLoaded = true;
        } else {
            getLoaderManager().restartLoader(1, null, this);
        }
    }
}