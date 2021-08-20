public class Sensor_UmidadeDeSolo[]{
    void setup() {
  	
  	Serial.begin(9600);
  
  	//Definir pinos de saída
	pinMode(vermelho, OUTPUT);
  	pinMode(azul, OUTPUT);
  	pinMode(verde, OUTPUT);
  	pinMode(motor, OUTPUT);
  
}

void loop() {
	
  	//Ler do sinal do sensor
  	float sinal_umd = analogRead(sensor_umidade);
  
  	monitorar_umd(sinal_umd);
  
}

void monitorar_umd(float sinal) {
  	
  	//Transformar o valor analógico recebido em porcentagem
  	int umidade = map(sinal, 1023, 0, 0, 100);
  
  	Serial.print("UMD (%): ");
  	Serial.println(umidade);
  
  	if(umidade > 40) {
    	
      	VERDE - Umidade registrada maior que 60%
        AMARELO - Umidade registrada maior que 40%
    	digitalWrite(vermelho, umidade > 60? LOW : HIGH);
    	digitalWrite(azul, LOW);
    	digitalWrite(verde, HIGH);
    
      	//Desligar sistema de irrigação
  		digitalWrite(motor, LOW);
    
  	}
  
  	else {
    
    	//VERMELHO - Umidade registrada menor ou igual a 40%
    	digitalWrite(vermelho, HIGH);
      	digitalWrite(azul, LOW);
      	digitalWrite(verde, LOW);
    	
      	//Acionar sistema de irrigação
      	digitalWrite(motor, HIGH);
      	Serial.println("Irrigando...");
    
 
  	}
  
  	return;
}
}