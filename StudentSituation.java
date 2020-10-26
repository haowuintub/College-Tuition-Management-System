import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.util.*;

public class StudentSituation extends JPanel implements ActionListener
{ 
  Hashtable ѧ�ѻ�����Ϣ��=null;                           
  JTextField ѧ��,����,����ѧ��,ס�޷�,�鱾��;                 
  JRadioButton ��,��;
  Student  ѧ��=null;
  ButtonGroup group=null;
  JButton ����,����;
  FileInputStream inOne=null;
  ObjectInputStream inTwo=null;
  FileOutputStream outOne=null;
  ObjectOutputStream outTwo=null;
  File file=null;                                           
  public StudentSituation(File file)
  {
  
   this.file=file;
   ѧ��=new JTextField(10);
   ����=new JTextField(10);
   ����ѧ��=new JTextField(10);
   ס�޷�=new JTextField(10);
   �鱾��=new JTextField(10);
   group=new ButtonGroup();
   ��=new JRadioButton("Yes",true);
   ��=new JRadioButton("No",false);
   group.add(��);
   group.add(��);
   ����=new JButton("Input");
   ����=new JButton("Reset");
   ����.addActionListener(this);
   ����.addActionListener(this);
   Box box1=Box.createHorizontalBox();              
   box1.add(new JLabel("Student ID:",JLabel.CENTER));
   box1.add(ѧ��);
   Box box2=Box.createHorizontalBox();              
   box2.add(new JLabel("Name:",JLabel.CENTER));
   box2.add(����);
   Box box3=Box.createHorizontalBox();              
   box3.add(new JLabel("Whether tuition has been paid:",JLabel.CENTER));
   box3.add(��);
   box3.add(��);
   Box box4=Box.createHorizontalBox();              
   box4.add(new JLabel("Basic tuition:",JLabel.CENTER));
   box4.add(����ѧ��);
   Box box5=Box.createHorizontalBox();              
   box5.add(new JLabel("Accommodation fee:",JLabel.CENTER));
   box5.add(ס�޷�);
   Box box6=Box.createHorizontalBox();              
   box6.add(new JLabel("Cost of books:",JLabel.CENTER));
   box6.add(�鱾��);
   Box boxH=Box.createVerticalBox();              
   boxH.add(box1);
   boxH.add(box2);
   boxH.add(box3);
   boxH.add(box4);
   boxH.add(box5);
   boxH.add(box6);
   boxH.add(Box.createVerticalGlue());          
   JPanel pCenter=new JPanel();
   pCenter.add(boxH);
   setLayout(new BorderLayout());
   add(pCenter,BorderLayout.CENTER);
   JPanel pSouth=new JPanel();
   pSouth.add(����);
   pSouth.add(����);
   add(pSouth,BorderLayout.SOUTH);
   validate();
  }
 public void actionPerformed(ActionEvent e)
  {
    if(e.getSource()==����)
      {
        String number="";
        number=ѧ��.getText();
        
         if(number.length()>0)
            {
              try {
                    inOne=new FileInputStream(file);
                    inTwo=new ObjectInputStream(inOne);
                    ѧ�ѻ�����Ϣ��=(Hashtable)inTwo.readObject();
                    inOne.close();
                    inTwo.close();
                  }
               catch(Exception ee)
                   {
                   }
              if(ѧ�ѻ�����Ϣ��.containsKey(number))          
                 {
                   String warning="The basic tuition information of this student already exists. If you want to modify it, please go to the modification page to modify it!";
                    
                   JOptionPane.showMessageDialog(this,warning,"Warning!",JOptionPane.WARNING_MESSAGE);
                 }
              else
                 {  
                   String m="Basic tuition information will be entered!";
                   int ok=JOptionPane.showConfirmDialog(this,m,"Confirm",JOptionPane.YES_NO_OPTION,
                                                 JOptionPane.INFORMATION_MESSAGE);
                  if(ok==JOptionPane.YES_OPTION)
                    {
                     String name=����.getText();
                     String basictuition=����ѧ��.getText();
                     String lodgingexpenses=ס�޷�.getText();
                     String bookexpenses=�鱾��.getText();
                     String whetherhandingexpenses=null;
                        if(��.isSelected())
                           {
                             whetherhandingexpenses=��.getText();
                           }
                        else
                           {
                             whetherhandingexpenses=��.getText();
                           }
                     ѧ��=new Student();
                     ѧ��.setNumber(number);
                     ѧ��.setName(name);
                     ѧ��.setBasictuition(basictuition);
                     ѧ��.setLodgingexpenses(lodgingexpenses);
                     ѧ��.setBookexpenses(bookexpenses);
                     ѧ��.setWhetherhandingexpenses(whetherhandingexpenses);
                       try{
                           outOne=new FileOutputStream(file);
                           outTwo=new ObjectOutputStream(outOne);
                           ѧ�ѻ�����Ϣ��.put(number,ѧ��);
                           outTwo.writeObject(ѧ�ѻ�����Ϣ��);
                           outTwo.close();
                           outOne.close();
                           ѧ��.setText(null);
                           ����.setText(null);                                
                           ����ѧ��.setText(null);
                           ס�޷�.setText(null);
                           �鱾��.setText(null);
                          }
                       catch(Exception ee)
                          { 
                           System.out.println(ee);
                          }
                   
                   }
                } 
            }
        else
            { 
              String warning="You must enter your student ID!";
              JOptionPane.showMessageDialog(this,warning,"Warning!",JOptionPane.WARNING_MESSAGE);
            }
      } 
    if(e.getSource()==����)
      { 
        ѧ��.setText(null);
        ����.setText(null);
        ����ѧ��.setText(null);
        ס�޷�.setText(null);
        �鱾��.setText(null);
        
      }
  }
}
