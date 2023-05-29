import socket
import time


def request_time_from_server():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect(('127.0.0.1', 8080))
    server_time = client_socket.recv(1024)
    return float(server_time)

def adjustClockSkew(clockSkews):
    return sum(clockSkews) / len(clockSkews)


clock_skews = []


def snychronize_time(client):
    print("Client ", client,  " is start sending time to server")
    server_time = request_time_from_server()
    clock_skews.append(time.time() - server_time)

    print("Server time : ", server_time)
    print("Local time : ",  time.time())




client = 4
cnt = 0

while cnt < client:
    snychronize_time(cnt)
    cnt = cnt + 1
    time.sleep(2)

adjusted_time = adjustClockSkew(clock_skews)
print("Avarage skew: {}".format(adjusted_time))
print("Adjusted time: {}".format(time.time() + adjusted_time))
