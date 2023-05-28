#include <TimerFreeTone.h>
#include <LiquidCrystal.h>
#include <VirtualWire.h>
#include <VirtualWire_Config.h>


// LiquidCrystal lcd(7,6,5,4,3,2);

int ok=0;
int nr_timer=0,nr_state=0;
bool state=0,check_status=0;
int i,nr_led=0;

void adc_init_light_sensor() //adc initialization for light sensor
{
ADCSRA |= ((1<<ADPS2)|(1<<ADPS1)|(1<<ADPS0));
//selectam divizor de frecventa(prescalar) de 128
ADMUX |= (1<<REFS0); //aleferea tensiunii de ref
ADCSRA |= (1<<ADEN); //activare ADC
ADCSRA |= (1<<ADSC); //declansare start conversie
}

uint16_t read_adc_light_sensor(uint8_t channel) //read ADC function for light sensor
// uint8_t is „a type of unsigned integer of length 8 bits”
{
ADMUX &= 0xF0; //setam input A0 pana la A5
ADMUX |= channel; //selectam pin A0 pana la A5
ADCSRA |= (1<<ADSC); //start conversie
while(ADCSRA&(1<<ADSC));//asteptam incheierea operatie de conversie
return ADC; //read and return
}

void init_timer_1(){
 //init registrii
 TCCR2A = 0;
 TCCR2B = 0;
 TCNT2 = 0;
 
  //setam valoarea de comparatie
 OCR2A=249;
 //setam bitul corespunzator modului de lucru CTC
 //TCCR-TIMER/COUNTER CONTROL REGISTER
 TCCR2A |= (1 << WGM21);
 //setam bitii coresp prentru prescaler-ul de 1024
 TCCR2B |=(1<<CS21) | (1<<CS20);
sei(); //permite intreruperi
 //setam bitul corespunzator pentru validarea  intreruperii
 //TIMSK-TIMER INTERRUPT MASK REGISTER
 //OCIE2A-TIMER/COUNTER_N OUTPUT COMPARE MATCH INTERRUPT ENABLE
 TIMSK2 |= (1 << OCIE2A);
}

 //tratam intreruperea
 ISR(TIMER2_COMPA_vect)
{
nr_timer++;
}

// void setup()
// {
 DDRB |= (1<<PB5) | (1<<PB0); //setam pinii pe output in registrul B
 init_timer_1();
 adc_init_light_sensor();
 vw_setup(3000);//setam viteza de comunicatie dintre placi (in baud)
 Serial.begin(9600);
// }

// void loop()
// {
// //utilizam timer-ul de 8 biti pentru a numara pana la 2-3sec si a crea un delay
// //pentru initializarea sistemului
//  if(nr_timer>=1000 && ok==0){
//   state=!state;  
//   nr_timer=0;
//   nr_state++;
// } 

// //aprindem si stingem led-ul rosu utilizand timer-ul 2 in perioada de initiere
//   if(state && ok==0){
//   PORTB=(1<<PB5); 
//   }
//   else{
//     PORTB=0X00;
//   }

//   //Verificam daca sistemul e perfect functional
//  if(nr_state>=10&& check_status==0){
//   if(read_adc_light_sensor(0)>10){
//     lcd.clear();
//     lcd.setCursor(0,0);
//     lcd.print("Sist functional");
//     PORTB|=(1<<PB0);
//     ok=1;
//     check_status=1;
//     TCCR2B=0;//am oprit timer
//   }
//  }

//  if(check_status==1){
    
//     if(read_adc_light_sensor(0)>=900){
//       PORTD=0x00;
//       lcd.setCursor(0,1);
//       lcd.print("Lumin redusa.");
//       send((char*)"1.1");
//       PORTB=1<<PB5;
//       TimerFreeTone(9,650,250);
//       delay(250);
//       PORTB=0X00;
//       PORTB|=(1<<PB0);
//       TimerFreeTone(9,500,250);
//       delay(250);
      
//     }else{
//       PORTB=1<<PB0;
//         send((char*)"1.0");  
//       lcd.setCursor(0,1);
//       lcd.print("              ");
//       lcd.setCursor(0,1);
//       lcd.print("Lumin destula.");
    
      
//       delay(400);
//     }
//   }
// }

//  void send (char *message)  
//  {  
//  vw_send((uint8_t *)message, strlen(message));  
//  vw_wait_tx(); // Wait until the whole message is gone   
//  }
