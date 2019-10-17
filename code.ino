
#include <ESP8266WiFi.h>
#include <time.h>
 
#include <ESP8266WiFi.h>

#include <FirebaseArduino.h>
 
// Set these to run example. 
#define FIREBASE_HOST "" 
#define FIREBASE_AUTH "" 
#define WIFI_SSID "" 
#define WIFI_PASSWORD "" 

int timezone = 6 * 3600;
int dst = 0;

 
void setup() { 
  pinMode(D5,OUTPUT);
  pinMode(D6,OUTPUT);
  Serial.begin(115200); 
 
  // connect to wifi. 
  WiFi.begin(WIFI_SSID, WIFI_PASSWORD); 
  Serial.print("connecting"); 
  while (WiFi.status() != WL_CONNECTED) { 
    Serial.print("."); 
    delay(500); 
  } 
  Serial.println(); 
  Serial.print("connected: "); 
  Serial.println(WiFi.localIP()); 
  
  
  configTime(timezone, dst, "pool.ntp.org","time.nist.gov");
  Serial.println("\nWaiting for Internet time");

  while(!time(nullptr)){
     Serial.print("*");
     delay(1000);
  }
  Serial.println("\nTime response....OK"); 
  
   
  Firebase.begin(FIREBASE_HOST, FIREBASE_AUTH); 
  Serial.println("Connected to firebase ");
  Firebase.setString("mode/mode","1");
  
} 
 
int n = 0; 
int i=0;
 
void loop() { 
time_t now = time(nullptr);
  struct tm* p_tm = localtime(&now);
  Serial.println("friebase value set ");
  if (Firebase.failed()) { 
      Serial.print("setting /number failed:"); 
      Serial.println(Firebase.error());
  }	  
   
  int Mode = Firebase.getString("mode/mode").toInt();
  if (Firebase.failed()) { 
      Serial.print("setting /number failed:"); 
      Serial.println(Firebase.error());
  }	
  String _time = Firebase.getString("mode/time");
  int hour = _time.substring(0,2).toInt();
  int min = _time.substring(3,5).toInt();
  int sec = _time.substring(6,8).toInt();
  
  
  int c_hour =  p_tm->tm_hour;
 
  int c_min = p_tm->tm_min;
  
  int c_sec = p_tm->tm_sec;
  Serial.printf("MODE : %d \n",Mode);
  Serial.printf("%d   %d \n",c_hour,hour);
 
  Serial.printf("%d   %d \n",c_min,min);
  
 Serial.printf("%d   %d \n",c_sec,sec);
 
  if(Mode  || (c_hour==hour && c_min ==min &&c_sec == sec ) )
  {
	  Firebase.setString("mode/mode","0");
	  Serial.println("motor started");
	  digitalWrite(D5,HIGH);
	  digitalWrite(D6,LOW);
	  delay(50);
	  Serial.println("motor wait ..");
	  digitalWrite(D5,LOW);
	  digitalWrite(D6,LOW);
	  delay(1000);
	  Serial.println("motor stop");
	  digitalWrite(D5,LOW);
	  digitalWrite(D6,HIGH);
	  delay(50);
	  digitalWrite(D5,LOW);
	  digitalWrite(D6,LOW);
	  
	  
	  
  }
  
	  
}
