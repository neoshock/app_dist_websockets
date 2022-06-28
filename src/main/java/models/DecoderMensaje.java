/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.Reader;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.websocket.DecodeException;
import javax.websocket.Decoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

/**
 *
 * @author pideu
 */
public class DecoderMensaje implements Decoder.Text<Mensaje> {

    private static final Gson gson = new Gson();
    
    @Override
    public void init(EndpointConfig config) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Mensaje decode(String s) throws DecodeException {
        System.out.println(s);
        return gson.fromJson(s, Mensaje.class);
    }

    @Override
    public boolean willDecode(String arg0) {
        return (arg0 != null);
    }
}
