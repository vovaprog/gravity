package gravity;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;
import java.awt.event.KeyEvent;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;

public class MainForm extends javax.swing.JFrame {
	
	private Timer modelStepTimer;
	
    private Controller controller;
    
    public MainForm() throws Exception {
        initComponents();                
        
        initKeyboard();            
        
        initController();
        
        modelStepTimer = new Timer(1000 / 30, spacePanel);
        
        modelStepTimer.start();    
    }

    private void initController() throws Exception
    {
        controller = new ControllerSimple();
        
        controller.createModel();
        controller.initModel();
    }
    
    private void initKeyboard()
    {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(
            new KeyEventDispatcher() {
                @Override
                public boolean dispatchKeyEvent(KeyEvent e) {
                                        
                    if(e.getID() == KeyEvent.KEY_PRESSED)
                    {
                        switch(e.getKeyCode()){
                        case KeyEvent.VK_UP: controller.getView().moveUp(); break;
                        case KeyEvent.VK_DOWN: controller.getView().moveDown(); break;
                        case KeyEvent.VK_LEFT: controller.getView().moveLeft(); break;
                        case KeyEvent.VK_RIGHT: controller.getView().moveRight(); break;
                        case KeyEvent.VK_ADD: 
                        case KeyEvent.VK_EQUALS:    
                            controller.getView().zoomIn(); break;
                        case KeyEvent.VK_MINUS:
                        case KeyEvent.VK_SUBTRACT:
                            controller.getView().zoomOut(); break;
                        case KeyEvent.VK_PAGE_DOWN: controller.timeSlower(); break;
                        case KeyEvent.VK_PAGE_UP: controller.timeFaster(); break;
                        }                       
                    }
                    return false;
                }
            });            
    }
    
    @SuppressWarnings("unchecked")                          
    private void initComponents() {

        spacePanel = new SpaceViewPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));
        getContentPane().setLayout(new javax.swing.BoxLayout(getContentPane(), javax.swing.BoxLayout.LINE_AXIS));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(spacePanel);
        spacePanel.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );

        getContentPane().add(spacePanel);

        pack();
    }           

    public static void main(String args[]) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainForm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try{
                    new MainForm().setVisible(true);
                }catch(Exception ex){
                    ex.printStackTrace(System.out);   
                }
            }
        });
    }
        
	private SpaceViewPanel spacePanel;
    
    private class SpaceViewPanel extends javax.swing.JPanel implements ActionListener {

        public SpaceViewPanel() {
        }

        @Override
        public void paintComponent(Graphics g) {

            super.paintComponent(g);
            
            controller.getView().paint(controller.getModel(), g, getWidth(), getHeight());
        }

        public void actionPerformed(ActionEvent e)
        {            
            try{
                controller.timeStep();
            }catch(Exception ex){
                ex.printStackTrace(System.out);
            }
            
            setTitle(Utils.timeToString(controller.getModel().getTimeFromStart()));
            
            repaint();            
        }        
    }
}
