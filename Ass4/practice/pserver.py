import socket
import time

def handle_client_request(client_socket):
    server_time = str(time.time()).encode()
    client_socket.send(server_time)

def start_server():
    server_socket = socket.socket(socket.AF_INET , socket.SOCK_STREAM)
    server_socket.bind(('127.0.0.1' , 8080))
    server_socket.listen(1)
    print("Server started on 127.0.0.1:8080")

    while True:
        client_socket , client_address = server_socket.accept()
        print("client is connected from " , client_address[0]  ,  ":" , client_address[1])
        handle_client_request(client_socket)
        client_socket.close()


start_server()
