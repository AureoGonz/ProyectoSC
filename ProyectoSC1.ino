void setup() {
  // put your setup code here, to run once:
  Serial.begin(9600);
}

void loop() {
  // put your main code here, to run repeatedly:
  int ampm =(int) map(analogRead(0),0,1023,0,128);
  double fm = map(analogRead(1),0,1023,0,5);
  int ampp = (int) map(analogRead(2),0,1023,0,128);
  double fp = map(analogRead(3),0,1023,0,50);
  String tor = "/";
  String p = ampm +tor+ fm +tor + ampp+tor + fp;
  Serial.println(p);
  delay(100);
}
