//  #include <VirtualWire.h>
//  #include <L298N.h>  
  
//  byte message[VW_MAX_MESSAGE_LEN]; // a buffer to store the incoming messages  
//  byte messageLength = VW_MAX_MESSAGE_LEN; // the size of the message  
//  char messTrue[4]="1.1";
//  char messFalse[4]="1.0";
//  bool status_bulb=0;
//  const unsigned int IN3=5;
//  const unsigned int IN4=4;
//  const unsigned int ENB=6;

//  L298N motor(ENB,IN3,IN4);
  
//  void setup()  
//  {  
//   Serial.begin(9600);  
//   Serial.println("Receptorul e pregatit sa primeasca mesaje...");  
//   // Initialize the IO and ISR  
//   vw_setup(3000); // viteza de comunicatie (in baud)
//   vw_rx_start(); // pornim receptorul
//   DDRD|=(1<<PD7) | (1<<PD3); //init pin pentru comandare releu si led fade in fade out
//   //setariile pe registrii pentru semnalul PWM corespunzator led-ului de fade in fade out 
//   TCCR2A = (1<<COM2B1) | (1<<WGM21) | (1<<WGM20)| (1<<CS21) | (1<<CS20); 
//   TCCR2B = (1<<CS22) | (1<<WGM22); // prescaler = 64 deoarece valoarea de comparare ajunge la 249; 
//   motor.setSpeed(100);
  
//  } 
  
//  void loop()  {
  
//  motor.setSpeed(200);  
//  //delay(800);
//  if ( vw_get_message(message, &messageLength )) // Non-blocking  
//  { 
//    char messTransmiter[messageLength]; 
//    memcpy(messTransmiter,message,messageLength); 
//    Serial.println(messTransmiter);
   
//    if(strcmp(messTransmiter,"1.1")==0){
//     PORTD|=1<<PD7; //aprindem becul
//     motor.forward(); //pornim motorasul
//     if(status_bulb==0){
//       for (int i = 0; i < 256; i++)
//         {
//          OCR2A = i;
//          _delay_ms(10);
//         }
//       status_bulb=1; 
//     }
   
//    }
  
//    if(strcmp(messTransmiter,"1.0")==0)
//     {  PORTD=0x00; //stingem becul
//        motor.stop();//oprim motorul
//        if(status_bulb==1){ 
//         for (int i = 255; i >= 0; i--){
//         OCR2A = i; //setam factor de umplere
//         _delay_ms(10);
//       }
//         status_bulb=0;
//        }
     
//       delay(300);   
//    } 
//  }
// }  
