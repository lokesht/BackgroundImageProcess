package in.digitaslbi.backgroundimageprocess;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Lokesh on 31-10-2015.
 */
public class FragmentMain extends Fragment {

    private ProgressDialog dialog;

    private ListView lsView;

    public static FragmentMain getInstance() {
        FragmentMain main = new FragmentMain();
        return main;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);
        initialise(view);
        return view;
    }

    private void initialise(View view) {

        lsView = (ListView) view.findViewById(R.id.lv_test);

        if (new NetWorkInfoUtility().isNetWorkAvailableNow(getContext())) {
            new FetchList().execute();
        } else {
            Toast.makeText(getActivity(), "No Internet", Toast.LENGTH_SHORT).show();
        }

    }


    class FetchList extends AsyncTask<String, String, List<Item>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressLoading();
        }

        @Override
        protected List<Item> doInBackground(String... params) {

            HttpURLConnectionWebService httpURLConnectionWebService = new HttpURLConnectionWebService();
            String json = httpURLConnectionWebService.getMovieJSON("http://files.ilicco.com/digitaslbi/recruitment/test.json");

            if (json != null) {
                List<Item> items = JSONParser.parseMovieList(json);
                return items;
            } else
                return null;
        }

        @Override
        protected void onPostExecute(List<Item> Items) {
            super.onPostExecute(Items);

            Items.addAll(Items);
            Items.addAll(Items);
            Items.addAll(Items);
            if (Items != null) {
                AdapterList adapterList = new AdapterList(getActivity(), Items);
                lsView.setAdapter(adapterList);
            }
            if (dialog != null && dialog.isShowing())
                dialog.dismiss();
        }
    }

    // Dialog Progress
    private void progressLoading() {
        dialog = new ProgressDialog(getContext(), android.R.style.Theme_DeviceDefault_Dialog_NoActionBar);
        dialog.setIndeterminate(true);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();
    }
}
