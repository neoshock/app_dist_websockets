/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.io.IOException;
import java.io.Writer;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.google.gson.Gson;

/**
 *
 * @author pideu
 */
public class EncoderMensaje implements Encoder.Text<Mensaje> {

    private static Gson gson = new Gson();

    @Override
    public void init(EndpointConfig config) {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void destroy() {
         //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String encode(Mensaje arg0) throws EncodeException {
        return gson.toJson(arg0);
    }
    
}
