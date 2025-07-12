package com.example.inventario;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.inventario.Producto;
import Proyect.Model.Inventario;
import java.util.ArrayList;

public class InterfazInventario extends AppCompatActivity {

    String criterio = "";
    Button btnBuscar = findViewById(R.id.btnBuscar);
    Button btnOrdenar = findViewById(R.id.btnOrdenar);
    EditText campoBuscar = findViewById(R.id.campoBuscar);
    TextView MensajeError = findViewById(R.id.labelError);
    TableLayout TablaProductos = findViewById(R.id.tablaProductos);
    Spinner spinner = findViewById(R.id.SpinnerOrdenar);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.interfazinventario);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<String> filtros = new ArrayList<>();
        filtros.add("Cantidad");
        filtros.add("Categoría");
        filtros.add("Código");
        filtros.add("Nombre");
        filtros.add("Precio");

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                filtros
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                criterio = filtros.get(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) { }
        });

        btnOrdenar.setOnClickListener(v -> {
            ArrayList<Producto> productosSorted = new ArrayList<>(Inventario.productos);

            switch (criterio) {
                case "Código":
                    productosSorted.sort((a, b) -> Integer.compare(a.getCodigo(), b.getCodigo()));
                    break;
                case "Nombre":
                    productosSorted.sort((a, b) -> a.nombre.compareToIgnoreCase(b.nombre));
                    break;
                case "Categoría":
                    productosSorted.sort((a, b) -> a.categoria.compareToIgnoreCase(b.categoria));
                    break;
                case "Precio":
                    productosSorted.sort((a, b) -> Double.compare(a.precio, b.precio));
                    break;
                case "Cantidad":
                    productosSorted.sort((a, b) -> Integer.compare(a.cantidad, b.cantidad));
                    break;
                default:
                    break;
            }

            int totalFilas = TablaProductos.getChildCount();
            for (int i = totalFilas - 1; i > 0; i--) {
                TablaProductos.removeViewAt(i);
            }
            for (Producto p : productosSorted) {
                TableRow fila = new TableRow(this);
                fila.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvCodigo = new TextView(this);
                tvCodigo.setText(String.valueOf(p.getCodigo()));
                tvCodigo.setPadding(4, 4, 4, 4);

                TextView tvNombre = new TextView(this);
                tvNombre.setText(String.valueOf(p.nombre));
                tvNombre.setPadding(4, 4, 4, 4);

                TextView tvCategoria = new TextView(this);
                tvCategoria.setText(String.valueOf(p.categoria));
                tvCategoria.setPadding(4, 4, 4, 4);

                TextView tvPrecio = new TextView(this);
                tvPrecio.setText(String.valueOf(p.precio));
                tvPrecio.setPadding(4, 4, 4, 4);

                TextView tvPrecioC = new TextView(this);
                tvPrecioC.setText(String.valueOf(p.precioC));
                tvPrecioC.setPadding(2, 4, 2, 4);

                TextView tvCantidad = new TextView(this);
                tvCantidad.setText(String.valueOf(p.cantidad));
                tvCantidad.setPadding(4, 4, 4, 4);

                fila.addView(tvCodigo);
                fila.addView(tvNombre);
                fila.addView(tvCategoria);
                fila.addView(tvPrecio);
                fila.addView(tvPrecioC);
                fila.addView(tvCantidad);
                TablaProductos.addView(fila);
            }

        });

        btnBuscar.setOnClickListener(v -> {
            String NombreCodigo = campoBuscar.getText().toString();
            Producto encontrado = null;
            try {
                int codigoBuscado = Integer.parseInt(NombreCodigo); // evaluar si es código
                for (Producto p : Inventario.productos) {
                    if (p.getCodigo() == codigoBuscado) {
                        encontrado = p;
                        break;
                    }
                }
            } catch (NumberFormatException e) { // si no es número, buscar por nombre
                for (Producto p : Inventario.productos) {
                    if (p.nombre.equalsIgnoreCase(NombreCodigo)) {
                        encontrado = p;
                        break;
                    }
                }
            }

            if (encontrado != null) {
                MensajeError.setVisibility(View.INVISIBLE);
                //DefaultTableModel modelo = (DefaultTableModel) TablaProductos.getModel();
                int totalFilas = TablaProductos.getChildCount();
                for (int i = totalFilas - 1; i > 0; i--) {
                    TablaProductos.removeViewAt(i);
                }
                TableRow fila = new TableRow(this);
                fila.setLayoutParams(new TableRow.LayoutParams(
                        TableRow.LayoutParams.MATCH_PARENT,
                        TableRow.LayoutParams.WRAP_CONTENT));

                TextView tvCodigo = new TextView(this);
                tvCodigo.setText(String.valueOf(encontrado.getCodigo()));
                tvCodigo.setPadding(4, 4, 4, 4);

                TextView tvNombre = new TextView(this);
                tvNombre.setText(String.valueOf(encontrado.nombre));
                tvNombre.setPadding(4, 4, 4, 4);

                TextView tvCategoria = new TextView(this);
                tvCategoria.setText(String.valueOf(encontrado.categoria));
                tvCategoria.setPadding(4, 4, 4, 4);

                TextView tvPrecio = new TextView(this);
                tvPrecio.setText(String.valueOf(encontrado.precio));
                tvPrecio.setPadding(4, 4, 4, 4);

                TextView tvPrecioC = new TextView(this);
                tvPrecioC.setText(String.valueOf(encontrado.precioC));
                tvPrecioC.setPadding(2, 4, 2, 4);

                TextView tvCantidad = new TextView(this);
                tvCantidad.setText(String.valueOf(encontrado.cantidad));
                tvCantidad.setPadding(4, 4, 4, 4);

                fila.addView(tvCodigo);
                fila.addView(tvNombre);
                fila.addView(tvCategoria);
                fila.addView(tvPrecio);
                fila.addView(tvPrecioC);
                fila.addView(tvCantidad);
                TablaProductos.addView(fila);
            } else {
                MensajeError.setVisibility(View.INVISIBLE);
                //MensajeError.setText("Producto no registrado en el inventario");
            }
        });
    }
}

