package main;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.servlet.http.HttpSessionListener;

/**
 *
 * @author Jose Manuel
 */
public class SessionController implements HttpSessionListener{
    
    private static ArrayList<Session> lista;
    private HashMap<String,ArrayList<String>> map;
    
    public SessionController() {
        lista = new ArrayList<Session>();
        map = new HashMap<String,ArrayList<String>>();
    }

    private boolean contiene(Session param1){
        boolean sol = false;
        for(int i = 0; i<lista.size() && !sol;i++){
            if(param1.isEqual(lista.get(i)))sol = true;
        }
        return sol;
    }
    
    public static int getNumberOfUsersOnline() { 
        return lista.size();
    }

    public void sessionCreated(Session event) {
        if(!contiene(event)){
            synchronized (this) {
                lista.add(event);
            }
        }
    }
    
    public void sessionDestroyed(Session event) {
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
    public boolean isPossible(String newUri, Session actualSession){
        boolean sol = false;
        
        for(String checkSession : map.get(actualSession.getLastURI())){
            if(newUri.equals(checkSession)){
                sol = true;
                actualSession.setLastURI(newUri);
            }
        }
        return sol;
    }
    public void createMap(String ruta) throws IOException{
        String cadena;
        FileReader f = new FileReader(ruta);
        BufferedReader b = new BufferedReader(f);
        while((cadena = b.readLine())!=null) {
            String[] splitText = cadena.split(" ");
            ArrayList<String> aux = new ArrayList<String>();
            for(int i = 1; i<splitText.length;i++)aux.add(splitText[i]);
            map.put(splitText[0], aux);
        }
        b.close();
    }
}
