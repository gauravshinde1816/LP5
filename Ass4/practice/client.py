import time
import socket

# Server address and port
SERVER_ADDR = '127.0.0.1'
SERVER_PORT = 12345

# Function to request time from the server


def request_time_from_server():
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    client_socket.connect((SERVER_ADDR, SERVER_PORT))
    server_time = client_socket.recv(1024)
    client_socket.close()
    return float(server_time)

# Function to adjust local clock based on the clock skew


def adjust_local_clock(clock_skews):
    print(sum(clock_skews))
    return sum(clock_skews) / len(clock_skews)
# Function to synchronize clocks using the Berkeley algorithm

clock_skews = []


def synchronize_clocks(client):
    print("Client ", client,  " has started sending time to server")
    server_time = request_time_from_server()
    local_time = time.time()
    clock_skew = abs(server_time - local_time) 
    clock_skews.append(clock_skew)
    
    print("Server time: {}".format(server_time))
    print("Local time: {}".format(local_time))
    

# Synchronize clocks with the server

client = 4
cnt = 0

while cnt < client:
    synchronize_clocks(cnt)
    cnt = cnt + 1
    time.sleep(2)

adjusted_time = adjust_local_clock(clock_skews)
print("Avarage skew: {}".format(adjusted_time))
print("Adjusted time: {}".format(time.time() + adjusted_time))

