package com.isn.jetlist;

        import android.content.Intent;
        import android.support.design.widget.FloatingActionButton;
        import android.support.v4.app.Fragment;
        import android.os.Bundle;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;

public class DocumentsFragment extends Fragment {
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.documents_fragment, container, false);
    }

    public void onViewCreated(View view, Bundle savedInstanceState) {
        FloatingActionButton addButton = view.findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createDocument = new Intent(getActivity(), DocumentsAddActivity.class);
                startActivity(createDocument);
            }
        });
    }
}
