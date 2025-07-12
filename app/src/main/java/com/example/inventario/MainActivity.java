package com.example.inventario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setupCardButtons();
    }

    private void setupCardButtons() {
        // Botón Ruta
        setupButton(R.id.cardRuta, () -> {
            Intent intent = new Intent(this, RutaActivity.class);
            startActivity(intent);
        });

        // Botón Facturación
        setupButton(R.id.cardFunc2, () -> {
            try {
                Cliente cliente = new Cliente(
                        "Cliente Ejemplo",
                        "3106830641",
                        "Calle 123 #45-67",
                        "Urgente para mañana"
                );

                List<Producto> productos = new ArrayList<>();
                productos.add(new Producto("Producto A", 2, 25000));
                productos.add(new Producto("Producto B", 1, 50000));

                String fecha = new SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
                        .format(new Date());

                Pedido pedido = new Pedido(cliente, productos, fecha);

                Intent intent = new Intent(this, FacturaActivity.class);
                intent.putExtra("pedido", pedido);
                startActivity(intent);
            } catch (Exception e) {
                showError("Error al crear factura", e);
            }
        });

        // Botón Clientes
        setupButton(R.id.cardFunc3, () ->
                Toast.makeText(this, "Clientes - En desarrollo", Toast.LENGTH_SHORT).show());

        // Botón Reportes
        setupButton(R.id.cardFunc4, () ->
                Toast.makeText(this, "Reportes - En desarrollo", Toast.LENGTH_SHORT).show());

        // Botón Configuración
        setupButton(R.id.cardFunc5, () ->
                Toast.makeText(this, "Configuración - En desarrollo", Toast.LENGTH_SHORT).show());

        // Botón Compras
        setupButton(R.id.cardFunc6, () -> {
            try {
            Intent intent = new Intent(this, ComprasActivity.class);
            startActivity(intent);} catch (Exception e) {
                showError("Error al crear compras", e);
            }
        });
    }

    private void setupButton(int cardId, Runnable action) {
        CardView card = findViewById(cardId);
        card.setOnClickListener(v -> {
            try {
                action.run();
            } catch (Exception e) {
                showError("Error al abrir función", e);
            }
        });
    }

    private void showError(String message, Exception e) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
        Log.e("MainActivity", message, e);
    }
}