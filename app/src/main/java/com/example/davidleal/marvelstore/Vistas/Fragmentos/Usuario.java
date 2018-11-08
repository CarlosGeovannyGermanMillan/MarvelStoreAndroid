package com.example.davidleal.marvelstore.Vistas.Fragmentos;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.davidleal.marvelstore.Aplicacion.preferences;
import com.example.davidleal.marvelstore.R;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Usuario extends Fragment {


    private OnFragmentInteractionListener mListener;
    @BindView(R.id.usuario_txt_correo)
    TextView txtCorreo;
    @BindView(R.id.usuario_txt_nombre)
    TextView txtNombre;
    @BindView(R.id.usuario_txt_telefono)
    TextView txtTelefono;

    public Usuario() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this
        View view = inflater.inflate(R.layout.fragment_usuario, container, false);
        ButterKnife.bind(this,view);
        preferences.getInstance().Initialize(this.getActivity());
        txtCorreo.setText(preferences.getInstance().getCorreo());

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
