package com.example.tap2025.reportes;

import com.example.tap2025.Modelos.Conexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class GenerarReporte {
    public GenerarReporte() {
        try {
            Conexion.createConnection();
            Connection connection = Conexion.connection;

            //ordenes
            String query = "SELECT o.idOrden, o.idCte, o.idEmpl, o.idMesa, o.precioOrden, o.fechHora, e.nomEmpl " +
                    "FROM orden o JOIN empleado e ON o.idEmpl = e.idEmpl ORDER BY fechHora";
            Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query);

            //pdr
            String outputPath = "./reporte.pdf";
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(outputPath));
            document.open();
            document.add(new Paragraph("Reporte de Órdenes y Detalles\n\n"));

            //llenarlo
            while (rs.next()) {
                int idOrden = rs.getInt("idOrden");
                int idCte = rs.getInt("idCte");
                int idMesa = rs.getInt("idMesa");
                String nombre = rs.getString("nomEmpl");
                float precioOrden = rs.getFloat("precioOrden");
                String fechHora = rs.getTimestamp("fechHora")+"";

                PdfPTable infoOrden = new PdfPTable(6);
                infoOrden.setSpacingBefore(10);
                infoOrden.addCell("IDOrden");
                infoOrden.addCell("IDCliente");
                infoOrden.addCell("Empleado");
                infoOrden.addCell("IDMesa");
                infoOrden.addCell("Precio");
                infoOrden.addCell("FechaHora");
                infoOrden.addCell(String.valueOf(idOrden));
                infoOrden.addCell(String.valueOf(idCte));
                infoOrden.addCell(String.valueOf(nombre));
                infoOrden.addCell(String.valueOf(idMesa));
                infoOrden.addCell(String.format("%.2f", precioOrden));
                infoOrden.addCell(fechHora);
                document.add(infoOrden);

                //detalles
                String detalles = "SELECT d.idProducto, d.cantidad, p.nomProd, p.precioProd " +
                        "FROM DetalleOrden d JOIN Producto p " +
                        "ON d.idProducto = p.idProducto " +
                        "WHERE d.idOrden = " + idOrden;
                Statement stmt2 = connection.createStatement();
                ResultSet rsDetalles = stmt2.executeQuery(detalles);

                PdfPTable detallesOrden = new PdfPTable(4);
                detallesOrden.addCell("Producto");
                detallesOrden.addCell("Cant.");
                detallesOrden.addCell("Precio U.");
                detallesOrden.addCell("Subtotal");

                while (rsDetalles.next()) {
                    String nomProd = rsDetalles.getString("nomProd");
                    int cantidad = rsDetalles.getInt("cantidad");
                    float precioUnitario = rsDetalles.getFloat("precioProd");
                    float subtotal = precioUnitario * cantidad;

                    detallesOrden.addCell(nomProd);
                    detallesOrden.addCell(String.valueOf(cantidad));
                    detallesOrden.addCell(String.format("%.2f", precioUnitario));
                    detallesOrden.addCell(String.format("%.2f", subtotal));
                }
                rsDetalles.close();
                stmt2.close();

                document.add(detallesOrden);
                //espaciado
                document.add(new Paragraph("\n\n\n\n\n"));
            }

            document.close();
            rs.close();
            stmt.close();

            //abrir archivo
            File file = new File(outputPath);
            Desktop.getDesktop().open(file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}