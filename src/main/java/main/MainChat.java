/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import javax.websocket.EncodeException;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import models.DecoderMensaje;
import models.EncoderMensaje;
import models.Mensaje;

/**
 *
 * @author pideu
 */
@ServerEndpoint(value = "/chat", decoders = DecoderMensaje.class, encoders = EncoderMensaje.class)
public class MainChat {

    private Session session;
    private static Set<MainChat> clients = new CopyOnWriteArraySet<>();
    private static HashMap<String, String> users = new HashMap<>();

    @OnOpen
    public void onOpen(Session session) throws IOException {
        this.session = session;
        clients.add(this);
        users.put(session.getId(), "Nuevo Usuario");
    }

    @OnClose
    public void onClose(Session session) throws IOException {
        clients.remove(this);
    }

    @OnMessage
    public void onMessage(Mensaje mensaje, Session session) throws IOException, EncodeException {
        broadcast(mensaje);
    }

    private static void broadcast(Mensaje message)
            throws IOException, EncodeException {
        for (MainChat client : clients) {
            synchronized (client) {
                try {
                    client.session.getBasicRemote().sendObject(message);
                } catch (IOException | EncodeException ex) {
                    ex.printStackTrace();
                }
            }
        }
    }
}
