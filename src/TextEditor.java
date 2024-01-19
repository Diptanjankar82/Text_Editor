import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

public class TextEditor implements ActionListener {
    //Declaring properties of textEditor
    JFrame frame;

    JMenuBar menuBar;
    //Initiliaze the menu
    JMenu file,edit;

    //File menu items
    JMenuItem newFile, OpenFile, saveFile;

    //Edit menu Iteams
    JMenuItem cut, copy, paste, selectAll, close;

    JTextArea textArea;
    TextEditor(){
        //Initilize a frame
        frame = new JFrame();
        //Initiliaze menybar
        menuBar = new JMenuBar();
        //Initilize textArea
        textArea = new JTextArea();
        //Initiliaze menus
        file = new JMenu("File");
        edit = new JMenu("Edit");

        //Initiliaze file menu iteams
        newFile = new JMenuItem("New Window");
        OpenFile = new JMenuItem("Open File");
        saveFile = new JMenuItem("Save File");

        //Add action listen to file menu iteams
        newFile.addActionListener(this); // this == keyword refering to object of TextEditor
        OpenFile.addActionListener(this);
        saveFile.addActionListener(this);
        //add these menu iteams to file menu
        file.add(newFile);
        file.add(OpenFile);
        file.add(saveFile);

        //Initiliaze edit menu iteam
        cut = new JMenuItem("Cut");
        copy = new JMenuItem("copy");
        paste = new JMenuItem("paste");
        selectAll = new JMenuItem("SelectAll");
        close = new JMenuItem("Close");

        //Adding action listener to edit menu iteams
        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        selectAll.addActionListener(this);
        close.addActionListener(this);


        //Adding to edit menu
        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(selectAll);
        edit.add(close);

        //Add menu to menuBar
        menuBar.add(file);
        menuBar.add(edit);

        //Set menuBar to frame
        frame.setJMenuBar(menuBar);
        //  Create content pen
        JPanel panal = new JPanel();
        panal.setBorder(new EmptyBorder(5,5,5,5));
        panal.setLayout(new BorderLayout(0,0));
        //Add text area to panal
        panal.add(textArea,BorderLayout.CENTER);
        //Create scroll pane
        JScrollPane scrollPane = new JScrollPane(textArea,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        //Add scrollPna e to panal
        panal.add(scrollPane);
        //Add panal to frame
        frame.add(panal);
        //Set dimensission of frame
        frame.setBounds(0,0,400,400);
        frame.setTitle("Text Editor");
        frame.setVisible(true);
        frame.setLayout(null);

    }
    @Override
    public void actionPerformed(ActionEvent actionEvent){
        if(actionEvent.getSource()==cut){
            //Perform cut operation
            textArea.cut();
        }

        if(actionEvent.getSource() == copy){
            //Performe copy operation
            textArea.copy();
        }

        if(actionEvent.getSource() == paste){
             //Performe paste operation
            textArea.paste();
        }
        if(actionEvent.getSource() == selectAll){
            //Performe selectAll Operation
            textArea.selectAll();
        }

        if(actionEvent.getSource()== close){
            //exit from window
            System.exit(0);
        }

        if(actionEvent.getSource() == OpenFile){
            //Open a filechooser
            JFileChooser fileChooser = new JFileChooser("C:");
            int chooseOption = fileChooser.showOpenDialog(null);
            //If we have clicked open bytton
            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Getting selected file
                File file =fileChooser.getSelectedFile();
                //Get the path of selected file
                String filpath = file.getPath();


                try{
                    //Initilize file reader
                    FileReader fileReader = new FileReader(filpath);
                    //Initilize buffered readder
                    BufferedReader bufferedReader = new BufferedReader(fileReader);

                    String intermediate = " ", output = "";
                    //Read content of file line by line
                    while((intermediate = bufferedReader.readLine())!= null){
                        output+=intermediate+"\n";
                    }
                    //Set the output string to text area
                     textArea.setText(output );
                }catch (FileNotFoundException fileNotFoundException){
                    fileNotFoundException.printStackTrace();
                }


                catch (IOException ioException){
                    ioException.printStackTrace();
                }
            }

        }

        if(actionEvent.getSource() == saveFile){
            //inilicilize file chooser
            JFileChooser fileChooser = new JFileChooser("C");
            //Get choose option from file chooser
            int chooseOption = fileChooser.showSaveDialog(null);
            //Check if we clicked in save button

            if(chooseOption == JFileChooser.APPROVE_OPTION){
                //Create a new file with directory path and file name
                File file = new File(fileChooser.getSelectedFile().getAbsolutePath()+".txt");
                try{
                    //Initilize the file writer
                    FileWriter fileWriter = new FileWriter(file);
                    //Initialize Buffered writer
                    BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
                    //Write content of text area to file
                    textArea.write(bufferedWriter);
                    bufferedWriter.close();
                }catch (IOException ioException){
                      ioException.printStackTrace();
                }
            }

        }
        if(actionEvent.getSource()== newFile){
            TextEditor newTextEditor = new TextEditor();
        }
    }
    public static void main(String[] args)
    {
        TextEditor textEditor = new TextEditor();
    }
}