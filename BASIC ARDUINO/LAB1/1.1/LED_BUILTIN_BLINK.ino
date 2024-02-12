#define LED 13
void setup()
{
  Serial.begin(9600);
  pinMode(LED, OUTPUT);
}

void loop()
{
  digitalWrite(LED, HIGH);
  Serial.println("on");
  delay(1000); // Wait for 1000 millisecond(s)
  digitalWrite(LED, LOW);
  Serial.println("off");
  delay(1000); // Wait for 1000 millisecond(s)
}
