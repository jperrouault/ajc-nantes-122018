package fr.codenames.restcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import fr.codenames.projection.Views;

@Service
public class Notifier {
	private Map<String, List<SseEmitter>> emitters = new HashMap<String, List<SseEmitter>>();
	
	
	public SseEmitter add(String name) {
		SseEmitter myEmitter = new SseEmitter();
		
		this.emitters.putIfAbsent(name, new ArrayList<SseEmitter>());
		this.emitters.get(name).add(myEmitter);
		
		myEmitter.onCompletion(() -> this.emitters.get(name).remove(myEmitter));
		myEmitter.onTimeout(() -> this.emitters.get(name).remove(myEmitter));
		
		return myEmitter;
	}
	
	
	
	public void publish(String name, Object message, Class<? extends Views.Common> view) {
	    List<SseEmitter> deadEmitters = new ArrayList<>();
	    
		for (SseEmitter emitter : this.emitters.get(name)) {
			try {
				MappingJacksonValue myMessage = new MappingJacksonValue(message);
				myMessage.setSerializationView(view);
				
				emitter.send(myMessage);
			} catch (Exception e) {
				e.printStackTrace();
		        deadEmitters.add(emitter);
			}
		}
		
		//ON NETTOIE LES CLIENTS S'ILS SONT DECONNECTES
		this.emitters.get(name).removeAll(deadEmitters);
	}
}
