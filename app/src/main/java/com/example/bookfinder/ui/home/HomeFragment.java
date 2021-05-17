package com.example.bookfinder.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookfinder.R;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.widget.Spinner;

public class HomeFragment extends Fragment {

    int filtru;
    Spinner mySpinner;
    Spinner anotherSpinner;
    ArrayAdapter<String> adapter;
    ArrayAdapter<String> adapterDisplay;
    ListView myListView;
    int ok=0;
    String[] cat={"All","Autor","Editura", "Categorie"};
    String[] categories={"All","Autor","Editura", "Categorie"};
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        ArrayList<String> list = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ArrayList<String> listTitlu = new ArrayList<>();
        ArrayList<String> listAutor = new ArrayList<>();
        ArrayList<String> listEditura = new ArrayList<>();
        ArrayList<String> listCategorie = new ArrayList<>();
        SearchView searchView = view.findViewById(R.id.searchView);

        mySpinner = view.findViewById(R.id.mySpinner);
        mySpinner.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categories));


        myListView = view.findViewById(R.id.listView);
        myListView.setAdapter(new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list));

        Log.i("Test123", String.valueOf(list));

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override

            public boolean onQueryTextSubmit(String query) {
                if (list.contains(query)) {
                    adapter.getFilter().filter(query);
                } else {
                    Toast.makeText(getActivity(),
                            "No Match found",
                            Toast.LENGTH_LONG).show();
                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        myListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
                public void onItemClick(AdapterView<?> parent, View view,
                int position, long id) {
                if (position == 0) {
                    Fragment bookView = new povesteDeCraciun();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, bookView); // give your fragment container id in first parameter
                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                    transaction.commit();
                }
                else if (position == 1) {
                    Fragment bookView = new benHur();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, bookView); // give your fragment container id in first parameter
                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                    transaction.commit();
                }
                else if (position == 2) {
                    Fragment bookView = new jurnalulAnneiFrank();
                    FragmentTransaction transaction = getFragmentManager().beginTransaction();
                    transaction.replace(R.id.nav_host_fragment, bookView); // give your fragment container id in first parameter
                    transaction.addToBackStack(null);  // if written, this transaction will be added to backstack
                    transaction.commit();
                }

            }
            });
        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long itemID) {
                BufferedInputStream bufferedInputStream = new BufferedInputStream(getResources().openRawResource(R.raw.carti));
                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(bufferedInputStream));
if(ok==0){
                try {
                    String line;
                    String headerLine = bufferedReader.readLine();
                    while ((line = bufferedReader.readLine()) != null) {
                        String[] lista = line.trim().split(",");
                        list.add(line);
                        listTitlu.add(lista[0]);
                        listAutor.add(lista[1]);
                        listEditura.add(lista[2]);
                        listCategorie.add(lista[3]);
                        ok=1;
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
}Log.i("test123", String.valueOf(list));
                if (position >= 0 && position < categories.length) {
                    getSelectedCategoryData(position, list, listTitlu, listAutor, listEditura,listCategorie);
                } else {
                    Toast.makeText(getContext(), "Selected Category Does not Exist!", Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        return view;
    }
    private void getSelectedCategoryData(int categoryID, ArrayList<String> list,ArrayList<String> titlu,ArrayList<String> autor,ArrayList<String> editura,ArrayList<String> categorie) {

        if(categoryID == 0)
        {
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, list);
        }
        else if(categoryID==1) {
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, autor);
        }
        else if(categoryID==2) {
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, editura);
        }
        else if(categoryID==3) {
            adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, categorie);
        }

        //set the adapter to GridView
        myListView.setAdapter(adapter);
    }
}





