/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Validator;

import Beans.* ;
import static java.lang.String.format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author asus
 */
@FacesValidator("dateValidator")
public class DateValidator implements Validator {

    @Override
    public void validate(FacesContext context, UIComponent component, Object value) throws ValidatorException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    
        String d = (String) value ;
         Date date = null;
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            date = sdf.parse(d);
            if (!d.equals(sdf.format(date))) {
            throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Mauvaise date", "Date doit etre en format dd/MM/yyyy"));
    
            }
            else 
                //Verifier que c'est dans le future
            {
                //DATE DONNE
                Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(d);
                //DATE ACTUELLE
                String dateNowString= new SimpleDateFormat("dd/MM/yyyy").format(new Date());
                Date dateNow=new SimpleDateFormat("dd/MM/yyyy").parse(dateNowString); 
                
                
                if (dateNow.compareTo(date)> 0)
                      throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Ancienne  date", "La date doit etre dans le futur!!"));
    
                    
                
            }  
            
        
        
        
        } catch (ParseException ex) {
          throw new ValidatorException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " Mauvaise date", "Date doit etre en format dd/MM/yyyy"));
    
    
        }
    
    }
    
}
