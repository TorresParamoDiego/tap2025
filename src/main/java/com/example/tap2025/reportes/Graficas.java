package com.example.tap2025.reportes;

import com.example.tap2025.Modelos.Conexion;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfWriter;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartFrame;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Graficas {

    public static void masVendidos() throws SQLException {
        Conexion.createConnection();
        Connection connection = Conexion.connection;
        Statement stmt = connection.createStatement();

        String query =
                "SELECT p.nomProd, SUM(do.cantidad) AS total " +
                "FROM DetalleOrden do " +
                "JOIN Producto p ON do.idProducto = p.idProducto " +
                "GROUP BY p.nomProd " +
                "ORDER BY total DESC ";// +
                //"LIMIT 10";

        ResultSet rs = stmt.executeQuery(query);
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.addValue(rs.getInt("total"), "Productos", rs.getString("nomProd"));
        }
        JFreeChart grafica = ChartFactory.createBarChart(
                "Productos más vendidos",
                "Productos",
                "Cantidad",
                dataset
        );
        ChartFrame frame = new ChartFrame("Productos más vendidos", grafica);
        frame.pack();
        frame.setVisible(true);

        rs.close();
        stmt.close();
    }

    public static void ventasPDia() throws SQLException {
        Conexion.createConnection();
        Connection connection = Conexion.connection;
        Statement stmt = connection.createStatement();

        String query = """
                SELECT DATE(fechHora) AS fecha, COUNT(*) AS total
                FROM Orden
                GROUP BY DATE(fechHora)
                ORDER BY fecha
            """;

        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(query);
            throw new RuntimeException(e);
        }
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.addValue(rs.getInt("total"), "Órdenes", rs.getString("fecha"));
        }
        JFreeChart grafica = ChartFactory.createLineChart(
                "Ventas por día",
                "Fecha",
                "Número de órdenes",
                dataset
        );
        ChartFrame frame = new ChartFrame("Ventas por día", grafica);
        frame.pack();
        frame.setVisible(true);

        rs.close();
        stmt.close();
    }

    public static void empleadoMasVentas() throws SQLException {
        Conexion.createConnection();
        Connection connection = Conexion.connection;
        Statement stmt = connection.createStatement();

        String query = """
                SELECT e.nomEmpl, COUNT(*) AS total
                FROM Orden o
                JOIN Empleado e ON o.idEmpl = e.idEmpl
                GROUP BY e.nomEmpl
                ORDER BY total DESC
                -- LIMIT 5
            """;

        ResultSet rs = stmt.executeQuery(query);
        DefaultPieDataset dataset = new DefaultPieDataset();
        while (rs.next())
            dataset.setValue(rs.getString("nomEmpl"), rs.getInt("total"));
        JFreeChart grafica = ChartFactory.createPieChart(
                "Ventas de empleados",
                dataset,
                true,
                true,
                false
        );
        ChartFrame frame = new ChartFrame("Ventas de empleados", grafica);
        frame.pack();
        frame.setVisible(true);

        rs.close();
        stmt.close();
    }

    public static void exportarGraficasAPDF(String outputPath) throws SQLException, DocumentException, IOException {
        Conexion.createConnection();
        Connection connection = Conexion.connection;

        Document document = new Document(PageSize.A4.rotate());
        PdfWriter.getInstance(document, new FileOutputStream(outputPath));
        document.open();

        JFreeChart chart1 = crearChartMasVendidos(connection);
        Image img1 = chartToImage(chart1, 800, 600);
        document.add(img1);
        document.newPage();

        JFreeChart chart2 = crearChartVentasPorDia(connection);
        Image img2 = chartToImage(chart2, 800, 600);
        document.add(img2);
        document.newPage();

        JFreeChart chart3 = crearChartEmpleadoMasVentas(connection);
        Image img3 = chartToImage(chart3, 800, 600);
        document.add(img3);

        document.close();
    }

    private static JFreeChart crearChartMasVendidos(Connection con) throws SQLException {
        String sql = "SELECT p.nomProd, SUM(do.cantidad) AS total " +
                "FROM DetalleOrden do JOIN Producto p ON do.idProducto = p.idProducto " +
                "GROUP BY p.nomProd ORDER BY total DESC LIMIT 9";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.addValue(rs.getInt("total"), "Productos", rs.getString("nomProd"));
        }
        rs.close();
        stmt.close();
        return ChartFactory.createBarChart(
                "Productos más vendidos",
                "Productos",
                "Cantidad",
                dataset
        );
    }

    private static JFreeChart crearChartVentasPorDia(Connection con) throws SQLException {
        String sql = "SELECT DATE(fechHora) AS fecha, COUNT(*) AS total " +
                "FROM Orden GROUP BY fecha ORDER BY fecha";
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DefaultCategoryDataset dataset= new DefaultCategoryDataset();
        while (rs.next()) {
            dataset.addValue(rs.getInt("total"), "Órdenes", rs.getString("fecha"));
        }
        rs.close();
        stmt.close();
        return ChartFactory.createLineChart(
                "Ventas por día",
                "Fecha",
                "Número de órdenes",
                dataset        );
    }

    private static JFreeChart crearChartEmpleadoMasVentas(Connection conn) throws SQLException {
        String sql = "SELECT e.nomEmpl, COUNT(*) AS total " +
                "FROM Orden o JOIN Empleado e ON o.idEmpl = e.idEmpl " +
                "GROUP BY e.nomEmpl ORDER BY total DESC";// LIMIT 5";
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        DefaultPieDataset dataset= new DefaultPieDataset();
        while (rs.next()) {
            dataset.setValue(rs.getString("nomEmpl"), rs.getInt("total"));
        }
        rs.close();
        stmt.close();
        return ChartFactory.createPieChart(
                "Ventas de empleados",
                dataset,
                true, true, false
        );
    }

    private static Image chartToImage(JFreeChart chart, int width, int height) throws IOException, DocumentException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ChartUtils.writeChartAsPNG(baos, chart, width, height);
        return Image.getInstance(baos.toByteArray());
    }
}
