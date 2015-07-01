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

    private javax.swing.JButton buttonStart;
    private javax.swing.JLabel labelSelectModel;
    private javax.swing.JComboBox selectModel;
    //private javax.swing.JPanel spacePanel;	
    
	private Timer modelStepTimer;
	
    private Controller controller;
    
    private boolean isStarted=false; 
    
    public MainForm() throws Exception {
        initComponents();                
        
        initKeyboard();            
        
  //      initController();
        
        controller = new ControllerSimple();
        
        String[] modelNames = controller.getModelNames();
        
        for(String modelName : modelNames)
        {
            selectModel.addItem(modelName);    
        }
        
/*        modelStepTimer = new Timer(1000 / 30, spacePanel);
        
        modelStepTimer.start();*/
        
    }

    private void initController() throws Exception
    {
/*        controller = new ControllerSimple();
        
/*        String[] modelNames = controller.getModelNames();
        
        for(String modelName : modelNames)
        {
            System.out.println(modelName);    
        }
        
        controller.createModel(modelNames[0]);
        controller.initModel();*/
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

/*        spacePanel = new SpaceViewPanel();

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

        pack();*/
        
        spacePanel = new SpaceViewPanel();
        

        labelSelectModel = new javax.swing.JLabel();
        selectModel = new javax.swing.JComboBox();
        buttonStart = new javax.swing.JButton();
        

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(600, 600));

        labelSelectModel.setText("model:");

        selectModel.setModel(new javax.swing.DefaultComboBoxModel(new String[] {}));
        selectModel.setName("selectModel"); // NOI18N

        buttonStart.setText("start");
        buttonStart.setName("buttonStart"); // NOI18N
        buttonStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonStartActionPerformed(evt);
            }
        });

        spacePanel.setName("spacePanel"); // NOI18N

        javax.swing.GroupLayout spacePanelLayout = new javax.swing.GroupLayout(spacePanel);
        spacePanel.setLayout(spacePanelLayout);
        spacePanelLayout.setHorizontalGroup(
            spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        spacePanelLayout.setVerticalGroup(
            spacePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 245, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(spacePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelSelectModel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(selectModel, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(buttonStart)
                        .addGap(0, 96, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelSelectModel)
                    .addComponent(selectModel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buttonStart))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spacePanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();

    }           

    private void buttonStartActionPerformed(java.awt.event.ActionEvent evt) {                                            
        try{
            //initController();

            controller.createModel(selectModel.getSelectedItem().toString());
            controller.initModel();
            
            
            modelStepTimer = new Timer(1000 / 30, spacePanel);
            
            modelStepTimer.start();
            
            isStarted=true;
        }catch(Exception ex){
            System.out.println(ex);   
        }
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
            
            if(isStarted)
            {
                controller.getView().paint(controller.getModel(), g, getWidth(), getHeight());
            }
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
