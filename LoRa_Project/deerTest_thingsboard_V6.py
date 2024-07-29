import paho.mqtt.client as mqtt
import json
import time
import requests
from datetime import datetime, timedelta

# ฟังก์ชันสำหรับส่งข้อความแจ้งเตือนไปที่ LINE
def send_line_notification(message, line_token):
    url = "https://notify-api.line.me/api/notify"
    headers = {
        "Authorization": f"Bearer {line_token}"
    }
    data = {
        "message": message
    }
    response = requests.post(url, headers=headers, data=data)
    if response.status_code != 200:
        print(f"Error sending notification: {response.status_code} {response.text}")

##########################################################################################

# กำหนดการตั้งค่า MQTT
thingsboard_host = 'demo.thingsboard.io'  # หรือใช้ IP ของ ThingsBoard Server ของคุณ
access_token = 'xQtlcR0LAgwuvN6cL3tG'      # ใช้ Token ของอุปกรณ์ที่ลงทะเบียนใน ThingsBoard
line_token = 'SEkMlPbgXr4XvQYK0hSGq3eUBlJC0T0PhzTCyTR2E4p'


# ฟังก์ชันเพื่อค้นหาข้อมูลล่าสุด
def find_latest_data(lines):
    latest_time = None
    latest_data = {}
    for line in lines:
        if line.startswith("Time"):
            time_str = line.split("Time")[1].strip()
            if latest_time is None or time_str > latest_time:
                latest_time = time_str
                latest_data["time"] = time_str
        elif line.startswith("Receive as String") and "time" in latest_data:
            latest_data["receive_as_string"] = line.split(":", 1)[1].strip()
        elif line.startswith("SNR") and "time" in latest_data:
            try:
                snr_value = float(line.split(":")[1].strip().replace('\x00', ''))
                latest_data["SNR"] = snr_value
            except ValueError:
                print(f"Error converting SNR value: {line}")
                latest_data["SNR"] = None
        elif line.startswith("RSSI") and "time" in latest_data:
            try:
                rssi_value = int(line.split(":")[1].strip())
                latest_data["RSSI"] = rssi_value
            except ValueError:
                print(f"Error converting RSSI value: {line}")
                latest_data["RSSI"] = None
    return latest_data

last_notification_time = datetime.now() - timedelta(minutes=1)

while True:
    try:
        # อัปเดตวันที่ในเส้นทางไฟล์อัตโนมัติ
        today = datetime.today().strftime('%Y-%m-%d')
        file_path = f'/home/LoRaGW3/Documents/LoRaRice_Spare/log/logfile_Receive_LoRaRice_{today}.txt'
        
        # อ่านข้อมูล .txt จากไฟล์
        with open(file_path, 'r') as file:
            lines = file.readlines()

        # ค้นหาข้อมูลล่าสุด
        latest_data = find_latest_data(lines)

        # แยกค่าแต่ละตัวและเก็บในตัวแปร
        values = latest_data.get("receive_as_string", "").split(',')
        if len(values) >= 7:
            distance = values[0]
            temperature = values[1]
            humidity = values[2]
            voltage = values[3]
            humSoil = values[4]
            tempSoil = values[5]
            ec = values[6]
            snr = latest_data.get("SNR", None)
            rssi = latest_data.get("RSSI", None)

            # แปลงค่า distance เป็นจำนวนเต็มและทำการคำนวณ
            try:
                distance_int = int(float(distance))
                distance_calculated = distance_int
            except ValueError:
                print("Error converting distance to integer.")
                distance_calculated = None

            # กำหนดสถานะ SNR และ RSSI
            snr_status = True if snr is not None else False
            # This code for checking Pi temperature
            res = os.popen('vcgencmd measure_temp').readline()
            temp_pi = float(res.replace("temp=","").replace("'C\n",""))
            # This code for printing time
            current_time = datetime.now()
            formatted_time = current_time.strftime("%H:%M:%S")

            # แสดงค่าที่ได้เพื่อยืนยัน
            print(formatted_time)
            print("Distance:", distance_calculated)
            print("Temperature:", temperature)
            print("Humidity:", humidity)
            print("Voltage:", voltage)
            print("HumSoil:", humSoil)
            print("TempSoil:", tempSoil)
            print("EC:", ec)
            print("SNR:", snr)
            print("RSSI:", rssi)
            print("SNR Status:", snr_status)
            print("Pi_temp", temp_pi)
            print("==============================================")

            # กำหนด payload ที่จะส่งไปยัง ThingsBoard
            payload = {
                "time": latest_data.get("time"),
                "distance": distance_calculated,
                "temperature": temperature,
                "humidity": humidity,
                "voltage": voltage,
                "humSoil": humSoil,
                "tempSoil": tempSoil,
                "EC": ec,
                "SNR": snr,
                "SNR_Status": snr_status,
                "RSSI": rssi,
                "Pi_temp": temp_pi
            }

            # ลบข้อมูล None ออกจาก payload
            payload = {k: v for k, v in payload.items() if v is not None}

            # สร้างการเชื่อมต่อ MQTT
            client = mqtt.Client()
            client.username_pw_set(access_token)
            client.connect(thingsboard_host, 1883, 60)

            # ส่งข้อมูล
            client.publish('v1/devices/me/telemetry', json.dumps(payload))

        else:
            print("Error: Not enough data received.")

#################################################################################

        # แปลงค่า distance เป็นจำนวนเต็มและทำการคำนวณ
        try:
            distance_int = int(float(distance))
            distance_calculated = distance_int
        except ValueError:
            print("Error converting distance to integer.")
            distance_calculated = None

        # แปลงค่า voltage เป็นจำนวนทศนิยม
        try:
            voltage_float = float(voltage)
        except ValueError:
            print("Error converting voltage to float.")
            voltage_float = None

        current_time = datetime.now()
        if current_time - last_notification_time >= timedelta(minutes=1):
            if distance_calculated <= 0:
                send_line_notification("ระดับน้ำเหลือ 0 cm", line_token)
            elif distance_calculated > 10:
                send_line_notification("ระดับน้ำมากกว่า 15 cm", line_token)
            if voltage_float and voltage_float < 3.0:
                send_line_notification("แบตต่ำ", line_token)

            last_notification_time = current_time
        
            
    except Exception as e:
        print(f"Error: {e}")

    # รอ 10 วินาทีแล้วลองใหม่
    time.sleep(10)

#############################################################################
