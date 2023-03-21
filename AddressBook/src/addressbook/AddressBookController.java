
package addressbook;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.print.PrintQuality;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextField;
import javafx.stage.Modality;
import javafx.stage.Stage;
import se.chalmers.cse.dat215.lab1.Presenter;
import javafx.beans.value.ChangeListener; 
import javafx.beans.value.ObservableValue;

public class AddressBookController implements Initializable {
    
    @FXML private MenuBar menuBar;
    @FXML private Button newButton;
    @FXML private Button deleteButton;
    @FXML private ListView contactsListView;
    @FXML private TextField ntf;
    @FXML private TextField lntf;
    @FXML private TextField ptf;
    @FXML private TextField etf;
    @FXML private TextField atf;
    @FXML private TextField pctf;
    @FXML private TextField ctf;
    @FXML private Presenter presenter;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        presenter = new Presenter(
            contactsListView,
            ntf,
            lntf,
            ptf,
            etf,
            atf,
            pctf,
            ctf);
        
        presenter.init();
        ntf.focusedProperty().addListener(new TextFieldListener(ntf));
        lntf.focusedProperty().addListener(new TextFieldListener(lntf));
        ptf.focusedProperty().addListener(new TextFieldListener(ptf));
        etf.focusedProperty().addListener(new TextFieldListener(etf));
        atf.focusedProperty().addListener(new TextFieldListener(atf));
        pctf.focusedProperty().addListener(new TextFieldListener(pctf));
        ctf.focusedProperty().addListener(new TextFieldListener(ctf));

        contactsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener() {

            @Override
            public void changed(ObservableValue observable, Object oldValue, Object newValue) {
                presenter.contactsListChanged();
            }

        });
        
    }
    
    
    @FXML 
    protected void openAboutActionPerformed(ActionEvent event) throws IOException{
    
        ResourceBundle bundle = java.util.ResourceBundle.getBundle("addressbook/resources/AddressBook");
        Parent root = FXMLLoader.load(getClass().getResource("address_book_about.fxml"), bundle);
        Stage aboutStage = new Stage();
        aboutStage.setScene(new Scene(root));
        aboutStage.setTitle(bundle.getString("about.title.text"));
        aboutStage.initModality(Modality.APPLICATION_MODAL);
        aboutStage.setResizable(false);
        aboutStage.showAndWait();
    }
    
    @FXML 
    protected void closeApplicationActionPerformed(ActionEvent event) throws IOException{
        
        Stage addressBookStage = (Stage) menuBar.getScene().getWindow();
        addressBookStage.hide();
    }    


    @FXML void deleteContactActionPerformed (ActionEvent event){
        presenter.removeCurrentContact();
    }

    @FXML
    protected void newContactActionPreformed (ActionEvent event){
        presenter.newContact();
    }

    @FXML 
    protected void textFieldActionPerformed (ActionEvent event){
        presenter.textFieldActionPerformed(event);
    }
    private class TextFieldListener implements ChangeListener<Boolean>{

        private TextField textField;
        
        public TextFieldListener(TextField textField){
            this.textField = textField;
        }
        
        @Override
        public void changed(ObservableValue<? extends Boolean> observable, Boolean oldValue, Boolean newValue) {
            if(newValue){
                presenter.textFieldFocusGained(textField);
            
            }
            else{
                presenter.textFieldFocusLost(textField);
            }
        }        
    }
}






