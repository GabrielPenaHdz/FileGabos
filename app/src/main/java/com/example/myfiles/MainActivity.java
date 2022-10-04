package com.example.myfiles;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity
{
    private Button buttonWrite;
    private Button buttonRead;
    private EditText editTextWrite;
    private EditText editTextRead;
    public static final String archivo = "gerdoc.json";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonWrite = findViewById( R.id.buttonWrite );
        buttonRead = findViewById( R.id.buttonRead );
        editTextRead = findViewById( R.id.editTextRead );
        editTextWrite = findViewById( R.id.editTextWrite );
        addListeners( );
    }

    private void addListeners( )
    {
        buttonWrite.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( editTextWrite.getText().length() == 0 )
                {
                    Toast.makeText( getApplicationContext() , "Vacio" , Toast.LENGTH_LONG ).show();
                    return;
                }
                if( writeFile( editTextWrite.getText().toString() ) )
                {
                    editTextWrite.setText( "" );
                    Toast.makeText( getApplicationContext() , "Ok" , Toast.LENGTH_LONG ).show();
                    return;
                }
                Toast.makeText( getApplicationContext() , "Error" , Toast.LENGTH_LONG ).show();
            }
        });
        buttonRead.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                if( !isFileExits() )
                {
                    Toast.makeText( getApplicationContext() , "No existe archivo" , Toast.LENGTH_LONG ).show();
                    return;
                }
            }
        });
    }

    private File getFile( )
    {
        return new File( getDataDir() , archivo );
    }

    private boolean isFileExits( )
    {
        File file = getFile( );
        if( file == null )
        {
            return false;
        }
        return file.isFile() && file.exists();
    }

    private boolean writeFile(String text)
    {
        File file = null;
        FileOutputStream fileOutputStream = null;
        try
        {
            file = getFile();
            fileOutputStream = new FileOutputStream( file );
            fileOutputStream.write( text.getBytes(StandardCharsets.UTF_8) );
            fileOutputStream.close();
            return true;
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return false;
    }















}