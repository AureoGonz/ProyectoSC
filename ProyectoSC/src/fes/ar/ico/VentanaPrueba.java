/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fes.ar.ico;

import com.panamahitek.ArduinoException;
import com.panamahitek.PanamaHitek_Arduino;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

/**
 *
 * @author Benjiman
 */
public class VentanaPrueba extends javax.swing.JFrame {

    final XYSeries serie = new XYSeries("oscilograna");
    final XYSeries amp = new XYSeries("espectro de frecuencia");
    final XYSeriesCollection coleccion = new XYSeriesCollection();
    final XYSeriesCollection ampcol = new XYSeriesCollection();
    JFreeChart grafica;
    JFreeChart ampgra;
    JFrame ampven = new JFrame("Osciloscopio");
    JFrame ventana = new JFrame("Analizador de espectros");
    PanamaHitek_Arduino arduino = new PanamaHitek_Arduino();
    String[] tomo = new String[4];
    int i = 0;
    int iam = 0;
    int ifm = 0;
    int iap = 0;
    int ifp = 0;
    SerialPortEventListener evento = new SerialPortEventListener() {
        @Override
        public void serialEvent(SerialPortEvent spe) {
            try {
                if (arduino.isMessageAvailable() == true) {
                    tomo = arduino.printMessage().split("/");
                    texto.setText(Arrays.toString(tomo));
                    iam = Integer.parseInt(tomo[0]);
                    am.setText(iam + "");
                    ifm = (int) Double.parseDouble(tomo[1]);
                    fm.setText(ifm + "");
                    iap = Integer.parseInt(tomo[2]);
                    ap.setText(iap + "");
                    ifp = (int) Double.parseDouble(tomo[3]);
                    fp.setText(ifp + "");
                }
            } catch (SerialPortException | ArduinoException ex) {
                Logger.getLogger(VentanaPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };

    public VentanaPrueba() {
        try {
            initComponents();
            try {
                arduino.arduinoRX("COM3", 9600, evento);
            } catch (ArduinoException ex) {
                Logger.getLogger(VentanaPrueba.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (SerialPortException ex) {
            Logger.getLogger(VentanaPrueba.class.getName()).log(Level.SEVERE, null, ex);
        }
        coleccion.addSeries(serie);
        ampcol.addSeries(amp);
        grafica = ChartFactory.createXYLineChart("", "Frecuencia", "Amplitud", coleccion, PlotOrientation.VERTICAL, false, true, true);
        ampgra = ChartFactory.createXYLineChart("", "Tiempo", "Amplitud", ampcol, PlotOrientation.VERTICAL, false, true, true);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        texto = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        am = new javax.swing.JTextField();
        fm = new javax.swing.JTextField();
        ap = new javax.swing.JTextField();
        fp = new javax.swing.JTextField();
        osciloscopio = new javax.swing.JButton();
        analizador = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Amplitudm");

        jLabel2.setText("Frecuenciam");

        jLabel3.setText("Amplitudp");

        jLabel4.setText("Frecuenciap");

        osciloscopio.setText("Oscilograma");
        osciloscopio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                osciloscopioMouseClicked(evt);
            }
        });

        analizador.setText("Espectro");
        analizador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                analizadorMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel1)
                                .addComponent(jLabel2)
                                .addComponent(jLabel3)
                                .addComponent(jLabel4))
                            .addGap(31, 31, 31)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(am, javax.swing.GroupLayout.DEFAULT_SIZE, 117, Short.MAX_VALUE)
                                .addComponent(fm)
                                .addComponent(ap)
                                .addComponent(fp)))
                        .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(analizador)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(osciloscopio)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(texto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(am, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fm, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ap, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(osciloscopio)
                    .addComponent(analizador))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void osciloscopioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_osciloscopioMouseClicked
        i = 0;
        ampven.revalidate();
        amp.clear();
        amp.add(0, 0);
        ampcol.removeAllSeries();
        ampcol.addSeries(amp);
        ampgra = ChartFactory.createXYLineChart("", "Tiempo", "Amplitud", ampcol, PlotOrientation.VERTICAL, false, true, true);

        ChartPanel panel1 = new ChartPanel(ampgra);
        ampven.getContentPane().add(panel1);
        ampven.pack();
        ampven.setVisible(true);

        while (i < 10000) {
            double moduladora = iam * (Math.sin(6.28 * i * ifm));
            double modulada = (iap + moduladora) * (Math.sin(6.28 * i * ifp));
            amp.add(i, modulada);
            i++;
        }
    }//GEN-LAST:event_osciloscopioMouseClicked

    private void analizadorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_analizadorMouseClicked
        i = 0;
        ventana.revalidate();
        serie.clear();
        serie.add(0, 0);
        coleccion.removeAllSeries();
        coleccion.addSeries(serie);
        grafica = ChartFactory.createXYLineChart("", "Frecuencia", "Amplitud", coleccion, PlotOrientation.VERTICAL, false, true, true);

        ChartPanel panel = new ChartPanel(grafica);
        ventana.getContentPane().add(panel);
        ventana.pack();
        ventana.setVisible(true);

        while (i < (ifp * 2)) {
            int fpfm1 = ifp - ifm;
            int fpfm2 = ifp + ifm;

            if (i == fpfm1) {
                serie.add(fpfm1, iam);
            } else {
                serie.add(i, 0);
            }
            if (i == ifp) {
                serie.add(ifp, iap);
            } else {
                serie.add(i, 0);
            }
            if (i == fpfm2) {
                serie.add(fpfm2, iam);
            } else {
                serie.add(i, 0);
            }
            i++;
        }
    }//GEN-LAST:event_analizadorMouseClicked

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(VentanaPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(VentanaPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(VentanaPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(VentanaPrueba.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new VentanaPrueba().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField am;
    private javax.swing.JButton analizador;
    private javax.swing.JTextField ap;
    private javax.swing.JTextField fm;
    private javax.swing.JTextField fp;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JButton osciloscopio;
    private javax.swing.JTextField texto;
    // End of variables declaration//GEN-END:variables
}
