
import javax.swing.JSlider;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * LeftPanel.java
 *
 * Created on May 7, 2011, 6:59:28 PM
 */
/**
 *
 * @author aliasbind
 */
public class LeftPanel extends javax.swing.JPanel {

    /** Creates new form LeftPanel */
    
    private ColorChooser chooser;
    private PaintCanvas canvas;
    
    /**
     * Creaza panoul din stanga si initializeaza
     * ColorChooser-ul.
     */
    public LeftPanel() {
        initComponents();
        chooser = new ColorChooser();
    }
    
    /**
     * 
     * Seteaza canvas-ul peste care se vor
     * aplica butoanele de zoom si de alegere a culorii, precum si
     * slider-ul de modificare a grosimii brush-ului.
     *
     */
    public void setCanvas(PaintCanvas canvas){
        this.canvas=canvas;
        chooser.setCanvas(this.canvas);
    }
    
    /**
     * 
     * Returneaza obiectul slider-ului.
     */
    public JSlider getSlider() {
        return jSlider1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSlider1 = new javax.swing.JSlider();
        jLabel1 = new javax.swing.JLabel();
        ZoomIn = new javax.swing.JButton();
        ColorPickerButton = new javax.swing.JButton();
        ZoomOut = new javax.swing.JButton();
        SelectButton = new javax.swing.JButton();

        jSlider1.setMaximum(21);
        jSlider1.setMinimum(1);
        jSlider1.setValue(2);

        jLabel1.setText("Brush Size:");

        ZoomIn.setText("+");
        ZoomIn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomInActionPerformed(evt);
            }
        });

        ColorPickerButton.setText("Color Picker");
        ColorPickerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ColorPickerButtonActionPerformed(evt);
            }
        });

        ZoomOut.setText("-");
        ZoomOut.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ZoomOutActionPerformed(evt);
            }
        });

        SelectButton.setText("Select");
        SelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(ZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(ZoomOut, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(ColorPickerButton, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(29, 29, 29))
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(SelectButton)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSlider1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(ColorPickerButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ZoomIn, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ZoomOut, javax.swing.GroupLayout.DEFAULT_SIZE, 36, Short.MAX_VALUE))
                .addGap(42, 42, 42)
                .addComponent(SelectButton)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void ZoomInActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomInActionPerformed
        canvas.setScale(1);
}//GEN-LAST:event_ZoomInActionPerformed

    private void ColorPickerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ColorPickerButtonActionPerformed
        chooser.setVisible(true);
}//GEN-LAST:event_ColorPickerButtonActionPerformed

    private void ZoomOutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ZoomOutActionPerformed
        canvas.setScale(-1);
}//GEN-LAST:event_ZoomOutActionPerformed

    private void SelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButtonActionPerformed
        canvas.setSelectionMode(true);
        canvas.setFocusable(true);
    }//GEN-LAST:event_SelectButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ColorPickerButton;
    private javax.swing.JButton SelectButton;
    private javax.swing.JButton ZoomIn;
    private javax.swing.JButton ZoomOut;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSlider jSlider1;
    // End of variables declaration//GEN-END:variables
}
