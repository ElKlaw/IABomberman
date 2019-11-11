package utils;

public enum Mouvement {
	MOVE_RIGHT("MOVE_RIGHT","{ \"message\" : \"MOVE right\" }"),
	MOVE_LEFT("MOVE_LEFT","{ \"message\" : \"MOVE left\" }"),
	MOVE_TOP("MOVE_TOP","{ \"message\" : \"MOVE top\" }"),
	MOVE_BOTTOM("MOVE_BOTTOM","{ \"message\" : \"MOVE bottom\" }"),
	BOMB("BOMB","{ \"message\" : \"BOMB\" }"),
	STAY("STAY","{ \"message\" : \"STAY\" }");
	
	private String key="";
	private String messageJson = "";
	
	//Constructeur
	Mouvement(String key, String messageJson){
		this.key= key;
		this.messageJson = messageJson;
  	}
   
	public String getKey(){
  		return key;
  	}
	
  	public String getMessageJson(){
  		return messageJson;
  	}
	
}
