package com.example.mapmarvels.ui;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import com.example.mapmarvels.R;

import java.io.File;
import java.io.Serializable;
import java.util.List;

public class Fragment_photos_ok extends DialogFragment {



    private NavController navController;
    public List<File> capturedPhotos;
    public Fragment_photos_ok(NavController navController, List<File> capturedPhotos) {
        this.navController = navController;
        this.capturedPhotos = capturedPhotos;
    }

    public Fragment_photos_ok(NavController navController) {
        this.navController = navController;
    }
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        String title = "Сделать еще фотографию?";
        String button1String = "Да";
        String button2String = "Нет";

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle(title);
        builder.setPositiveButton(button1String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                dismiss();
            }
        });
        builder.setNegativeButton(button2String, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                Bundle args = new Bundle();
                args.putSerializable("capturedPhotos", (Serializable) capturedPhotos);

                NavHostFragment navHostFragment = (NavHostFragment) getActivity().getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment);
                NavController navController = navHostFragment.getNavController();
                navController.navigate(R.id.action_cameraFragment_to_descFragment, args);
            }
        });
        builder.setCancelable(true);

        return builder.create();
    }
}
