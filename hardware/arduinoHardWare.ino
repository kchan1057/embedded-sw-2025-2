#include <IRremote.h>
#include <RtcDS1302.h>
#include <Stepper.h>

#define IR_RECEIVE_PIN 3  // IR 리모컨 수신기 핀

// 리모컨 버튼 (사용 중인 리모컨 값으로 바꿔야 함)
#define REMOTE_BTN_1 0xF30CFF00
#define REMOTE_BTN_0 0xE916FF00


// RTC 모듈 (DAT, CLK, RST)
ThreeWire myWire(4, 5, 2);
RtcDS1302<ThreeWire> Rtc(myWire);

const int stepsPerRevolution = 2048;  // Step Motor 설정
Stepper motor1(stepsPerRevolution, A0, A2, A1, A3); // 모터1
Stepper motor2(stepsPerRevolution, A4, 6, A5, 7); // 모터2


// 상태 관리 변수
enum State { STATE_SETUP, STATE_OPERATIONAL };
State current_state = STATE_SETUP;

int motor_to_setup = 1;
int binary_input_count = 0;

// 모터별 스케쥴 저장 [아침, 점심, 저녁]
bool motor1_schedule[3] = {false, false, false};
bool motor2_schedule[3] = {false, false, false};

int last_run_second = -1;

void setup() {
  Serial.begin(57600);
  IrReceiver.begin(IR_RECEIVE_PIN, ENABLE_LED_FEEDBACK);  // IR 리모컨 시작

  // RTC 시작
  Rtc.Begin();
  if (!Rtc.IsDateTimeValid()) {
    Rtc.SetDateTime(RtcDateTime(__DATE__, __TIME__));
  }
  if (Rtc.GetIsWriteProtected()) {
    Rtc.SetIsWriteProtected(false);
  }
  if (!Rtc.GetIsRunning()) {
    Rtc.SetIsRunning(true);
  }

  // 모터 속도 설정
  motor1.setSpeed(10);
  motor2.setSpeed(10);

  Serial.println("===== 모터 1 설정 =====");
  Serial.println("아침(매분10초)에 동작시키려면 1, 아니면 0을 누르세요.");
}

void loop() {
  if (current_state == STATE_SETUP) {
    handleRemoteInput();
  } else if (current_state == STATE_OPERATIONAL) {
    checkScheduleAndRunMotors();
  }
}


// 리모컨 입력 처리
void handleRemoteInput() {
  if (!IrReceiver.decode()) return;

  unsigned long remote_code = IrReceiver.decodedIRData.decodedRawData;

  if (remote_code == REMOTE_BTN_1 || remote_code == REMOTE_BTN_0) {
    bool run_motor = (remote_code == REMOTE_BTN_1);
    bool* schedule = (motor_to_setup == 1) ? motor1_schedule : motor2_schedule;
    schedule[binary_input_count] = run_motor;
    binary_input_count++;

    if (binary_input_count < 3) {
      if (binary_input_count == 1) {
        Serial.println("점심(매분30초)에 동작시키려면 1, 아니면 0을 누르세요.");
      } else if (binary_input_count == 2) {
        Serial.println("저녁(매분50초)에 동작시키려면 1, 아니면 0을 누르세요.");
      }
    } else {
      Serial.print("설정값: ");
      Serial.print(schedule[0]); Serial.print(schedule[1]); Serial.println(schedule[2]);

      if (motor_to_setup == 1) {
        motor_to_setup = 2;
        binary_input_count = 0;
        Serial.println("\n===== 모터 2 설정 =====");
        Serial.println("아침(매분10초)에 동작시키려면 1, 아니면 0을 누르세요.");
      }
      else {
        Serial.println("\n===== 모든 설정 완료. 약통 동작 시작 =====");
        current_state = STATE_OPERATIONAL;
      }
    }
  }

  IrReceiver.resume();
}


// 스케줄 체크 + 모터 실행
void checkScheduleAndRunMotors() {
  RtcDateTime now = Rtc.GetDateTime();
  int current_second = now.Second();

  if (current_second == last_run_second) return;

  int time_index = -1;
  if (current_second == 10) {
    Serial.println("\n[ 아침 시간(매분10초) 동작 ]");
    time_index = 0;
  }
  else if (current_second == 30) {
    Serial.println("\n[ 점심 시간(매분30초) 동작 ]");
    time_index = 1;
  }
  else if (current_second == 50) {
    Serial.println("\n[ 저녁 시간(매분50초) 동작 ]");
    time_index = 2;
  }

  if (time_index != -1) {
    bool motor1_scheduled = motor1_schedule[time_index];
    bool motor2_scheduled = motor2_schedule[time_index];

    if (motor1_scheduled || motor2_scheduled) {
      Serial.println("스케줄된 약통 실행 시작");

      if (motor1_scheduled) {
        Serial.println("   - 약통 1: 약 배출 시작");
        motor1.step(stepsPerRevolution);
        Serial.println("   - 약통 1: 약 배출 완료");
      }
      else {
        Serial.println("   - 약통 1: 스케줄되지 않음");
      }

      if (motor2_scheduled) {
        Serial.println("   - 약통 2: 약 배출 시작");
        motor2.step(stepsPerRevolution);
        Serial.println("   - 약통 2: 약 배출 완료");
      }
      else {
        Serial.println("   - 약통 2: 스케줄되지 않음");
      }
    }
    else {
      Serial.println(">> 이 시간에는 동작할 약통이 없습니다.");
    }

    last_run_second = current_second;
  }
}


// 시간 출력 함수
#define countof(a) (sizeof(a) / sizeof(a[0]))

void printDateTime(const RtcDateTime& dt) {
  char datestring[26];
  snprintf_P(datestring,
             countof(datestring),
             PSTR("%02u/%02u/%04u %02u:%02u:%02u"),
             dt.Month(), dt.Day(), dt.Year(),
             dt.Hour(), dt.Minute(), dt.Second());
  Serial.print(datestring);
}