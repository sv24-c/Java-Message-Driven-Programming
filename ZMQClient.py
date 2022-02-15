from email import message
from multiprocessing import context
from socket import socket
from sys import flags


import zmq

try:
    socket = context.socket(zmq.REQ)
    socket.connect("tcp://localhost:2222")
    print("Sending request")
    socket.send("send message")
    message = socket.recv(flags= zmq.NOBLOCK)
    print("Received reply %s "%message)
    
except Exception as e:
    print(str(e))