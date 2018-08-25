import xml.etree.ElementTree as ET
import socket
#for get_access_token
import requests
#for starting mysql and java server
import subprocess

STRINGS_FILE_LOCATION = "C:\Data\Coding\\flash-cards-android\\app\\src\main\\res\\values\strings.xml"
IP_KEY = "flash_cards_api_url"
ACCESS_TOKEN_KEY = "flash_cards_access_token"

#############################################
############Function Definitions#############
#############################################
def get_ip_address():
    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    s.connect(("8.8.8.8", 80))
    my_ip_address = s.getsockname()[0]
    s.close()
    return my_ip_address

def change_ip(STRINGS_FILE_LOCATION, IP_KEY, new_ip_address):
    tree = ET.parse(STRINGS_FILE_LOCATION)
    root = tree.getroot()
    ip_string_not_found = True
    for string_value in root.findall('string'):
        if string_value.attrib["name"]==IP_KEY:
            start = 7
            end = string_value.text.find(':8080')
            string_value.text = string_value.text.replace(string_value.text[start:end], new_ip_address)
            tree.write(STRINGS_FILE_LOCATION)
            ip_string_not_found = False

    if ip_string_not_found:
        print("There is no String resource represent ip address, or the key :"+IP_KEY+" has been changed ! Please contact someone or even better be proactive and find a fix, asd yalla fe eh!!!")

def get_access_token():
    url = 'http://localhost:8080/oauth/token'
    payload = {'grant_type':'password','username':'mohamed@gmail.com','password':'123456'}
    headers = {'Authorization': 'Basic d2ViOjEyMzQ1Ng=='}
    r = requests.post(url, data=payload, headers=headers)
    response_json = r.json()
    access_token = response_json['access_token']
    return access_token

def change_access_token(STRINGS_FILE_LOCATION, ACCESS_TOKEN_KEY, new_access_token):
    tree = ET.parse(STRINGS_FILE_LOCATION)
    root = tree.getroot()
    access_token_string_not_found = True
    for string_value in root.findall('string'):
        if string_value.attrib["name"]==ACCESS_TOKEN_KEY:
            string_value.text = access_token
            tree.write(STRINGS_FILE_LOCATION)
            access_token_string_not_found = False
    if access_token_string_not_found:
        print("There is no String resource represent access_token, or the key :"+ACCESS_TOKEN_KEY+" has been changed ! Please contact someone or even better be proactive and find a fix, asd yalla fe eh!!!")


###########################################
###########################################
###########################################
#############changing ip adress############
my_ip_address = get_ip_address()
change_ip(STRINGS_FILE_LOCATION, IP_KEY, my_ip_address)
#############starting mysql###############
subprocess.Popen(["C:\\xampp\\mysql\\bin\\mysqld.exe"],
                creationflags=subprocess.CREATE_NEW_PROCESS_GROUP)

#############stopping java server############
subprocess.Popen(["mvn", "spring-boot:stop"], shell=True, 
                creationflags=subprocess.CREATE_NEW_PROCESS_GROUP)
#############starting java server############
subprocess.Popen(["mvn", "clean", "spring-boot:run"], shell=True, 
                creationflags=subprocess.CREATE_NEW_PROCESS_GROUP)
##############adding access token#############
access_token = get_access_token()
change_access_token(STRINGS_FILE_LOCATION, ACCESS_TOKEN_KEY, access_token)


