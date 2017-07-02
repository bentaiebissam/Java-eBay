/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Converter;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author asus
 */

@FacesConverter("moneyConverter")
public class moneyConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
      
        String v = (String) value ;
        int x = 0 ;
        if (v.equals("one")) x=1 ;
        if (v.equals("two")) x=2 ;
        if (v.equals("three")) x=3 ;
        if (v.equals("four")) x=4 ;
        if (v.equals("five")) x=5 ;
        if (v.equals("six")) x=6 ;
        if (v.equals("seven")) x=7 ;
        if (v.equals("nine")) x=9 ;
        
        if (x!=0) return x;
                return value; 
        }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
//   return((Integer.toString(this.getAsObject(context, component, (String)value) )));


String v ;
 if (value instanceof String  )
       {
         v = (String)value ;
      
       }
       else
     //est un nombre
           return value.toString() ; 
       

        int x = 0 ;
        if (v.equals("one")) x=1 ;
        if (v.equals("two")) x=2 ;
        if (v.equals("three")) x=3 ;
        if (v.equals("four")) x=4 ;
        if (v.equals("five")) x=5 ;
        if (v.equals("six")) x=6 ;
        if (v.equals("seven")) x=7 ;
        if (v.equals("nine")) x=9 ;
        
       if (x!=0) return Integer.toString(x) ; 
       
          throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, " nombre invalide","doit un nombre valide"));
    
    
    
    }
}
    
    

   
    

