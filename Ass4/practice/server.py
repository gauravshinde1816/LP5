import time
import socket

# Server address and port
SERVER_ADDR = '127.0.0.1'
SERVER_PORT = 12345

# Function to handle client requests and send server time
def handle_client_request(client_socket):
    server_time = str(time.time()).encode()
    client_socket.send(server_time)

# Start the server
def start_server():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.bind((SERVER_ADDR, SERVER_PORT))
    server_socket.listen(1)
    print("Server started and listening on {}:{}".format(SERVER_ADDR, SERVER_PORT))

    while True:
        client_socket, client_address = server_socket.accept()
        print("Client connected from {}:{}".format(client_address[0], client_address[1]))
        handle_client_request(client_socket)
        client_socket.close()

# Start the server
start_server()
