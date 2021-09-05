package main;

import java.util.ArrayList;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Jose Manuel
 */

public class sessionListener implements HttpSessionListener{
    
   private static ArrayList<Object> lista;
    
    public sessionListener() {
        lista = new ArrayList<Object>();
    }

    private boolean contiene(Object param1){
        boolean sol = false;
        for(int i = 0; i<lista.size() && !sol;i++){
            if(param1.equals(lista.get(i)))sol = true;
        }
        return sol;
    }
    
    public static int getNumberOfUsersOnline() { 
        return lista.size();
    }

    public void sessionCreated(Object event) {
        if(!contiene(event)){
            synchronized (this) {
                lista.add(event);
            }
        }
    }
    
    public void sessionDestroyed(Object event) {
        int sol = -1;
        for(int i = 0; i<lista.size() && sol == -1;i++){
            if(event.equals(lista.get(i)))sol = i;
        }
        if(sol != -1){
            synchronized (this) {
                lista.remove(sol);
            }
        }
    }
}
