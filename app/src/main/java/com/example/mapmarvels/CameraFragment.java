package com.example.mapmarvels;
import android.os.Bundle;
import androidx.camera.core.Camera;
import androidx.fragment.app.Fragment;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.camera.core.CameraSelector;
import androidx.camera.core.ImageCapture;
import androidx.camera.core.ImageCaptureException;
import androidx.camera.core.Preview;
import androidx.camera.lifecycle.ProcessCameraProvider;
import androidx.camera.view.PreviewView;
import androidx.core.content.ContentValuesKt;
import androidx.core.content.ContextCompat;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;
import com.google.common.util.concurrent.ListenableFuture;
import java.io.File;
import java.util.concurrent.ExecutionException;
public class CameraFragment extends Fragment {
    public class CameraActivity extends AppCompatActivity {

        private PreviewView previewView;
        private ImageCapture imageCapture;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.fragment_camera);

            previewView = findViewById(R.id.pv_camera);

            ListenableFuture<ProcessCameraProvider> cameraProviderFuture = ProcessCameraProvider.getInstance(this);

            cameraProviderFuture.addListener(() -> {
                try {
                    ProcessCameraProvider cameraProvider = cameraProviderFuture.get();

                    bindPreview(cameraProvider);

                    imageCapture = new ImageCapture.Builder().build();

                    findViewById(R.id.bottom_bar).setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            CameraActivity.this.takePhoto();
                        }
                    });
                } catch (ExecutionException | InterruptedException e) {
                    e.printStackTrace();
                }
            }, ContextCompat.getMainExecutor(this));
        }

        private void bindPreview(ProcessCameraProvider cameraProvider) {
            Preview preview = new Preview.Builder().build();

            CameraSelector cameraSelector = new CameraSelector.Builder()
                    .requireLensFacing(CameraSelector.LENS_FACING_BACK)
                    .build();

            preview.setSurfaceProvider(previewView.getSurfaceProvider());

            Camera camera = cameraProvider.bindToLifecycle(this, cameraSelector, preview);
        }

        private void takePhoto() {
            File outputDirectory = getOutputDirectory();
            ImageCapture.OutputFileOptions outputFileOptions =
                    new ImageCapture.OutputFileOptions.Builder(
                            new File(outputDirectory, "photo.jpg")).build();

            imageCapture.takePicture(outputFileOptions, ContextCompat.getMainExecutor(this),
                    new ImageCapture.OnImageSavedCallback() {
                        @Override
                        public void onImageSaved(@NonNull ImageCapture.OutputFileResults outputFileResults) {
                            Toast.makeText(CameraActivity.this, "Photo captured!", Toast.LENGTH_SHORT).show();
                        }

                        @Override
                        public void onError(@NonNull ImageCaptureException exception) {
                            exception.printStackTrace();
                        }
                    });
        }

        private File getOutputDirectory() {
            File mediaDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
            File outputDir = new File(mediaDir, "CameraXApp");

            if (!outputDir.exists()) {
                outputDir.mkdirs();
            }

            return outputDir;
        }

        public void finish() {
        }
    }
}