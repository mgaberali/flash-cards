import xml.etree.ElementTree as ET
import socket
FILE_LOCATION = "C:\Data\Coding\\flash-cards-android\\app\\src\main\\res\\values\strings.xml"
IP_KEY = "flash_cards_api_url"
tree = ET.parse(FILE_LOCATION)
root = tree.getroot()

#getting ip
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.connect(("8.8.8.8", 80))
my_ip_address = s.getsockname()[0]
s.close()

ip_string_not_found = True
for string_value in root.findall('string'):
    if string_value.attrib["name"]==IP_KEY:
        # print(dir(string_value))
        # print(string_value.text)
        start = 7
        end = string_value.text.find(':8080')
        string_value.text = string_value.text.replace(string_value.text[7:end], my_ip_address)
        tree.write(FILE_LOCATION)
        ip_string_not_found = False

if ip_string_not_found:
    print("There is no String resource represent ip address, or the key :"+IP_KEY+" has been changed ! Please contact someone or even better be proactive and find a fix, asd yalla fe eh!!!")

import subprocess
subprocess.Popen(["C:\\xampp\\mysql\\bin\\mysqld.exe"],
                creationflags=subprocess.CREATE_NEW_PROCESS_GROUP)

import os
os.system('mvn clean spring-boot:run')

print("Please move the token to MainActivity")