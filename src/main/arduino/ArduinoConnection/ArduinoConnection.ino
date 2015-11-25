String data[5];

void setup() {
  Serial.begin(9600);
  for(int i = 8; i<=12; i++){
    pinMode(i, OUTPUT); 
    digitalWrite(i, HIGH);
  }
}

void loop() {
 if(Serial.available() > 5)  {
  if(Serial.readStringUntil('\n') == "s"){
      for(int i = 0; i<5; i++) {
        data[i] = Serial.readStringUntil('\n');
        //Serial.print(data[i]);
      }
  }
 
 for(int i = 0; i<5; i++){
  if(data[i] == "1"){
    digitalWrite(8+i, LOW);
  }
  else digitalWrite(8+i, HIGH);
  }
 }
}
