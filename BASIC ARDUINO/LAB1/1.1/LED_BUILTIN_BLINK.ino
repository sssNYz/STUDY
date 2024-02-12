#define LED 13
void setup()
{
  Serial.begin(9600);
  pinMode(LED, OUTPUT);
  pinMode(LED_BUILTIN, OUTPUT);
}

void loop()
{
  digitalWrite(LED, HIGH);
  digitalWrite(LED_BUILTIN, HIGH);
  Serial.println("on");
  delay(1000); // Wait for 1000 millisecond(s)
  digitalWrite(LED, LOW);
  digitalWrite(LED_BUILTIN, LOW);
  Serial.println("off");
  delay(1000); // Wait for 1000 millisecond(s)
}
